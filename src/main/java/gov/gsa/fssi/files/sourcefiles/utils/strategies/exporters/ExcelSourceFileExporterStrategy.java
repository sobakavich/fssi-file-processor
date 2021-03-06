package main.java.gov.gsa.fssi.files.sourcefiles.utils.strategies.exporters;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.java.gov.gsa.fssi.files.schemas.schemafields.SchemaField;
import main.java.gov.gsa.fssi.files.sourcefiles.SourceFile;
import main.java.gov.gsa.fssi.files.sourcefiles.records.SourceFileRecord;
import main.java.gov.gsa.fssi.files.sourcefiles.records.datas.Data;
import main.java.gov.gsa.fssi.files.sourcefiles.utils.strategies.SourceFileExporterStrategy;
import main.java.gov.gsa.fssi.helpers.FileHelper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class loads a schema from an XML file
 * 
 * @author davidlarrimore
 *
 */
public class ExcelSourceFileExporterStrategy implements
		SourceFileExporterStrategy {
	public static void closeOutputStream(FileOutputStream out, String fileName) {
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("There was an IOException error '{}' with file {}.",
					e.getMessage(), fileName);
		} finally {
			out = null;
		}
	}

	private static final Logger logger = LoggerFactory
			.getLogger(ExcelSourceFileExporterStrategy.class);

	/**
	 *
	 * @return Schema loaded from fileName in schemas_directory
	 */
	@Override
	public void export(String directory, SourceFile sourceFile) {
		String fileOutputType = (sourceFile.getProvider() == null? "XLSX" : sourceFile.getProvider().getFileOutputType());
		logger.info("Exporting File {} as a '{}'", sourceFile.getFileName(), fileOutputType);
		FileOutputStream out = null;

		try {
			out = new FileOutputStream(directory
					+ FileHelper.buildNewFileName(sourceFile.getFileName(),fileOutputType));				


			Workbook wb = fileOutputType
					.equalsIgnoreCase("XLSX") ? new XSSFWorkbook()
					: new HSSFWorkbook(); // create a new workbook

			Sheet s = wb.createSheet(); // create a new sheet
			Row r = null; // declare a row object reference
			Cell c = null; // declare a cell object reference
			r = s.createRow(0); // creating header row
			
			
			Map<Integer, String> headerMap = sourceFile.getSourceHeaders();
			Iterator<?> headerMapIterator = headerMap.entrySet().iterator();
			while (headerMapIterator.hasNext()) {
				Map.Entry<Integer, String> headerMapIteratorPairs = (Map.Entry) headerMapIterator
						.next();
				String fieldName = headerMapIteratorPairs.getValue().toString();
				c = r.createCell(headerMapIteratorPairs.getKey());
				if (sourceFile.getSchema() != null
						&& sourceFile.getSchema().getStatus()) {
					for (SchemaField field : sourceFile.getSchema().getFields()) {
						if (field.getHeaderIndex() == headerMapIteratorPairs
								.getKey()) {
							logger.info(
									"Using Schema name '{}' for field '{}'",
									field.getName(), headerMapIteratorPairs
											.getValue().toString());
							fieldName = field.getName();
						}
					}
				}
				c.setCellValue((fieldName == null ? headerMapIteratorPairs
						.getValue().toString() : fieldName));
			}

			
			
			
			int counter = 0;

			// Now lets put some data in there....
			for (SourceFileRecord sourceFileRecord : sourceFile.getRecords()) {
				counter++;
				r = s.createRow(counter);

				List<Data> records = sourceFileRecord.getDatas();
				for (Data data : records) {
					c = r.createCell(data.getHeaderIndex());
					c.setCellValue(data.getData());
				}
			}

			// write the workbook to the output stream
			// close our file (don't blow out our file handles
			wb.write(out);
			closeOutputStream(out, sourceFile.getFileName());
		} catch (IOException e) {
			logger.error("There was an IOException error '{}' with file {}. ",
					sourceFile.getFileName(), e.getMessage());
		}
	}

}

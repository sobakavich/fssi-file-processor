package main.java.gov.gsa.fssi.files.sourcefiles.utils.strategies.exporters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import main.java.gov.gsa.fssi.files.schemas.schemafields.SchemaField;
import main.java.gov.gsa.fssi.files.sourcefiles.SourceFile;
import main.java.gov.gsa.fssi.files.sourcefiles.records.SourceFileRecord;
import main.java.gov.gsa.fssi.files.sourcefiles.utils.strategies.SourceFileExporterStrategy;
import main.java.gov.gsa.fssi.helpers.FileHelper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class loads a schema from an XML file
 * 
 * @author davidlarrimore
 *
 */
public class CSVSourceFileExporterStrategy implements
		SourceFileExporterStrategy {
	private static final Logger logger = LoggerFactory
			.getLogger(CSVSourceFileExporterStrategy.class);

	/**
	 *
	 * @return Schema loaded from fileName in schemas_directory
	 */
	@Override
	public void export(String directory, SourceFile sourceFile) {
		try {
			
			String newFileName = null;
			
			newFileName = FileHelper.buildNewFileName(sourceFile
					.getFileName(), main.java.gov.gsa.fssi.files.File.FILETYPE_CSV); // Delimiter used in CSV file				

			
			
			String newLineSeparator = "\n";
			CSVPrinter csvFilePrinter = null;
			CSVFormat csvFileFormat = CSVFormat.DEFAULT
					.withRecordSeparator(newLineSeparator);
			File file = new File(FileHelper.getFullPath(directory, newFileName)); 
			Writer writer = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			PrintWriter printWriter = new PrintWriter(writer);
			csvFilePrinter = new CSVPrinter(printWriter, csvFileFormat); 
			
			List<String> csvHeaders = new ArrayList<String>();
			for (int i = 0; i < sourceFile.getSourceHeaders().size(); i++) {
				String fieldName = null;
				if(sourceFile.getSchema() != null && sourceFile.getSchema().getStatus()){
					for (SchemaField field : sourceFile.getSchema().getFields()) {
						if (field.getHeaderIndex() == i) {
							logger.info("Using Schema name '{}' for field '{}'",field.getName(), sourceFile.getSourceHeaderName(i));
							fieldName = field.getName();					
						}
					}
				}
				if (fieldName == null && sourceFile.getSourceHeaders().containsKey(i)){
					if(logger.isDebugEnabled()) logger.debug("Using source file field name '{}'", sourceFile.getSourceHeaderName(i));
					fieldName = sourceFile.getSourceHeaderName(i);
				}else if(fieldName == null) {
					logger.warn("There was an issue finding a fieldname for header index '{}'", i);
					fieldName = "";
				}
				csvHeaders.add(fieldName);
			}

			// Create CSV file header
			csvFilePrinter.printRecord(csvHeaders);

			// Writing Data
			for (SourceFileRecord sourceFileRecord : sourceFile.getRecords()) {
				List<String> csvRecord = new ArrayList<String>();
				for (int i = 0; i < sourceFile.getSourceHeaders().size(); i++) {
					if (sourceFileRecord.getDataByHeaderIndex(i) != null
							&& sourceFileRecord.getDataByHeaderIndex(i)
									.getData() != null) {
						csvRecord.add(sourceFileRecord.getDataByHeaderIndex(i)
								.getData());
					} else {
						csvRecord.add("");
					}
				}

				csvFilePrinter.printRecord(csvRecord);
			}
			csvFilePrinter.close();
			printWriter.close();
			logger.info("{} Created Successfully. {} Records processed",
					sourceFile.getFileName(), sourceFile.recordCount());

		} catch (IOException e) {
			logger.error("Received Exception '{}' while processing {}",
					e.getMessage(), sourceFile.getFileName());
		}
	}
}

package gov.gsa.fssi.fileprocessor;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.gsa.fssi.fileprocessor.providers.Provider;
import gov.gsa.fssi.fileprocessor.providers.ProviderManager;
import gov.gsa.fssi.fileprocessor.sourceFiles.SourceFile;
import gov.gsa.fssi.fileprocessor.sourceFiles.SourceFileManager;
import gov.gsa.fssi.files.schemas.Schema;




/**
 * This is the main class for the FSSI File Processor Project
 * 
 * @author David Larrimore
 * @version 0.1
 */
/**
 * @author davidlarrimore
 *
 */
public class Main {
	static Logger logger = LoggerFactory.getLogger(Main.class);
	static Config config = new Config();	    
	
	public static void main(String[] args) {
	    logger.info("Starting FSSI File Processor");
		ArrayList<Provider> providers = ProviderManager.initializeProviders();
		ProviderManager.printAllProviders(providers);
	    ArrayList<Schema> schemas = SchemaLoader.initializeSchemas();
	    SchemaLoader.printAllSchemas(schemas);
		//ArrayList<SourceFile> sourceFiles = SourceFileManager.initializeSourceFiles();
		//ingestProcessAndExportSourceFiles(providers, schemas, sourceFiles);	    
	    logger.info("Completed FSSI File Processor");	
	}

	/**
	 * The purpose of this function is to process a all files through the entire process from ingestion to processing to validation and finally to output.
	 * @param sourceFileDirectory
	 */
	public static void ingestProcessAndExportSourceFiles(ArrayList<Provider> providers, ArrayList<Schema> schemas, ArrayList<SourceFile> sourceFiles) {	
	    for ( SourceFile sourceFile : sourceFiles) {
	    	ingestProcessAndExportSourceFile(providers, schemas, sourceFile);		    	
		}		    
	}

	/**
	 * The purpose of this function is to process a single file through the entire process from ingestion to processing to validation and finally to output.
	 * @param providers
	 * @param schemas
	 * @param sourceFile
	 */
	private static void ingestProcessAndExportSourceFile(ArrayList<Provider> providers, ArrayList<Schema> schemas, SourceFile sourceFile) {
		logger.debug("Processing sourceFile '{}'", sourceFile.getFileName());	
		if (!sourceFile.getStatus().equals(SourceFile.STATUS_ERROR)){
		    logger.info("Mapping Provider to SourceFile '{}'", sourceFile.getFileName());	
			SourceFileManager.validateSourceFileProvider(providers, sourceFile);	
		    logger.info("Completed Mapping Provider to SourceFile '{}'", sourceFile.getFileName());			
		}
		if (!sourceFile.getStatus().equals(SourceFile.STATUS_ERROR)){
		    logger.info("Mapping Schema to SourceFile '{}'", sourceFile.getFileName());	
		    SourceFileManager.validateSourceFileSchema(schemas, sourceFile); 
		    logger.info("Completed Mapping Schema to SourceFile '{}'", sourceFile.getFileName());	
		}
		if (!sourceFile.getStatus().equals(SourceFile.STATUS_ERROR)){
		    logger.info("Ingesting SourceFile '{}'", sourceFile.getFileName());	
			sourceFile.ingest();
		    logger.info("Completed Ingesting SourceFile '{}'", sourceFile.getFileName());	
		}
		
		if (!sourceFile.getStatus().equals(SourceFile.STATUS_ERROR)){
		    logger.info("Processing SourceFile '{}'", sourceFile.getFileName());	
			sourceFile.processToSchema();
		    logger.info("Completed Processing SourceFile '{}'", sourceFile.getFileName());	
		}	
		
		if (!sourceFile.getStatus().equals(SourceFile.STATUS_ERROR)){
		    logger.info("Outputting SourceFile '{}'", sourceFile.getFileName());	
		    sourceFile.outputStagedSourceFile();
		    logger.info("Completed Outputting SourceFile '{}'", sourceFile.getFileName());	
		}
	}	
}

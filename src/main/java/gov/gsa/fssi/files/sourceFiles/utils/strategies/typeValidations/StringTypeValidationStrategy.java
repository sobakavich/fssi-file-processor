package main.java.gov.gsa.fssi.files.sourceFiles.utils.strategies.typeValidations;

import main.java.gov.gsa.fssi.files.File;
import main.java.gov.gsa.fssi.files.schemas.schemaFields.SchemaField;
import main.java.gov.gsa.fssi.files.sourceFiles.records.datas.Data;
import main.java.gov.gsa.fssi.files.sourceFiles.utils.strategies.TypeValidationStrategy;

public class StringTypeValidationStrategy implements TypeValidationStrategy {

	@Override
	public void validate(SchemaField field, Data data) {

		if(data.getStatus() == null || data.getStatus().isEmpty() || data.getStatus().equals("")){
			data.setStatus(File.STATUS_PASS);
		}	
			
		if(data.getValidatorStatus() == null || data.getValidatorStatus().isEmpty() || data.getValidatorStatus().equals("")){
			data.setValidatorStatus(File.STATUS_PASS);
		}	
	}

	@Override
	public boolean isValid(SchemaField field, Data data) {
		// TODO Auto-generated method stub
		return false;
	}

}
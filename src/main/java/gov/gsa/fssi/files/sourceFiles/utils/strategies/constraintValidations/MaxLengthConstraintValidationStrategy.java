package main.java.gov.gsa.fssi.files.sourceFiles.utils.strategies.constraintValidations;

import main.java.gov.gsa.fssi.files.schemas.schemaFields.SchemaField;
import main.java.gov.gsa.fssi.files.schemas.schemaFields.fieldConstraints.FieldConstraint;
import main.java.gov.gsa.fssi.files.sourceFiles.records.datas.Data;
import main.java.gov.gsa.fssi.files.sourceFiles.utils.strategies.ConstraintValidationStrategy;

public class MaxLengthConstraintValidationStrategy implements ConstraintValidationStrategy {

	@Override
	public void validate(SchemaField field, FieldConstraint constraint, Data data) {
		if(data != null){
			if(data.getData() != null){
				if(data.getData().length() > Integer.parseInt(constraint.getValue())){
					  data.addValidationResult(false, constraint.getLevel(), constraint.getRuleText());
				}else{
					  data.addValidationResult(true, 0, constraint.getRuleText());	
				}
			}else data.addValidationResult(true, 0, constraint.getRuleText());
		}
	}

	@Override
	public boolean isValid(SchemaField field, FieldConstraint constraint,
			Data data) {
		// TODO Auto-generated method stub
		return false;
	}

}

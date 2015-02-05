package test.java.gov.gsa.fssi.files.sourceFiles.utils.strategies.typeValidations;

import main.java.gov.gsa.fssi.files.File;
import main.java.gov.gsa.fssi.files.schemas.schemaFields.SchemaField;
import main.java.gov.gsa.fssi.files.sourceFiles.records.datas.Data;
import main.java.gov.gsa.fssi.files.sourceFiles.utils.contexts.TypeValidationContext;
import main.java.gov.gsa.fssi.files.sourceFiles.utils.strategies.typeValidations.DateTypeValidationStrategy;
import main.java.gov.gsa.fssi.files.sourceFiles.utils.strategies.typeValidations.IntegerTypeValidationStrategy;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import test.java.gov.gsa.fssi.mockData.MockData;
import test.java.gov.gsa.fssi.mockData.MockSchemaField;

public class IntegerTypeValidationStrategyTest {

	/**
	 * 
	 */
	@Test
	public void testNull() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new IntegerTypeValidationStrategy());
		
		SchemaField field = MockSchemaField.make("INTEGER", SchemaField.TYPE_INTEGER);
		Data data = MockData.make();
		
		context.validate(field, data);
		Assert.assertEquals("failure - IntegerTypeValidationStrategy did not catch error", File.STATUS_PASS, data.getStatusLevel());
		Assert.assertEquals("failure - IntegerTypeValidationStrategy did not make failure", File.STATUS_PASS, data.getValidatorStatus());		
	}

	/**
	 * 
	 */
	@Test
	public void testGoodInteger() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new IntegerTypeValidationStrategy());
		
		SchemaField field = MockSchemaField.make("INTEGER", SchemaField.TYPE_INTEGER);
		Data data = MockData.make("1234567");
		
		context.validate(field, data);
		//data.setStatus(FieldConstraint.LEVEL_ERROR);
		Assert.assertEquals("failure - IntegerTypeValidationStrategy did not catch error", File.STATUS_PASS, data.getStatusLevel());
		Assert.assertEquals("failure - IntegerTypeValidationStrategy did not make failure", File.STATUS_PASS, data.getValidatorStatus());		
	}
	
	/**
	 * 
	 */
	@Test
	public void testNumber() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new IntegerTypeValidationStrategy());
		
		SchemaField field = MockSchemaField.make("INTEGER", SchemaField.TYPE_INTEGER);
		Data data = MockData.make("123.45");
		
		context.validate(field, data);
		//data.setStatus(FieldConstraint.LEVEL_ERROR);
		Assert.assertEquals("failure - IntegerTypeValidationStrategy did not catch error", File.STATUS_ERROR, data.getStatusLevel());
		Assert.assertEquals("failure - IntegerTypeValidationStrategy did not make failure", File.STATUS_FAIL, data.getValidatorStatus());		
	}	
	
	/**
	 * 
	 */
	@Test
	public void testBadInteger() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new IntegerTypeValidationStrategy());
		
		SchemaField field = MockSchemaField.make("INTEGER", SchemaField.TYPE_INTEGER);
		Data data = MockData.make("value");
		
		context.validate(field, data);
		//data.setStatus(FieldConstraint.LEVEL_ERROR);
		Assert.assertEquals("failure - IntegerTypeValidationStrategy did not catch error", File.STATUS_FATAL, data.getStatusLevel());
		Assert.assertEquals("failure - IntegerTypeValidationStrategy did not make failure", File.STATUS_FAIL, data.getValidatorStatus());		
	}
	
	
	
	/**
	 * 
	 */
	@Test
	public void testAlreadyFailed() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new IntegerTypeValidationStrategy());
		
		SchemaField field = MockSchemaField.make("INTEGER", SchemaField.TYPE_INTEGER);
		Data data = MockData.make("12345");
		data.setValidatorStatus(File.STATUS_FAIL);
		data.setStatusLevel(File.STATUS_ERROR);
		
		context.validate(field, data);
		//data.setStatus(FieldConstraint.LEVEL_ERROR);
		Assert.assertEquals("failure - IntegerTypeValidationStrategy did not catch error", File.STATUS_ERROR, data.getStatusLevel());
		Assert.assertEquals("failure - IntegerTypeValidationStrategy did not make failure", File.STATUS_FAIL, data.getValidatorStatus());		
	}
	
	//TODO: Test Negative Numbers
	
}

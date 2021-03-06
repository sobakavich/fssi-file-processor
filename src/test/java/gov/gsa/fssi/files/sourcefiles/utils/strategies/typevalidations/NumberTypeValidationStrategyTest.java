package test.java.gov.gsa.fssi.files.sourcefiles.utils.strategies.typevalidations;

import main.java.gov.gsa.fssi.files.schemas.schemafields.SchemaField;
import main.java.gov.gsa.fssi.files.sourcefiles.records.datas.Data;
import main.java.gov.gsa.fssi.files.sourcefiles.utils.contexts.TypeValidationContext;
import main.java.gov.gsa.fssi.files.sourcefiles.utils.strategies.typevalidations.NumberTypeValidationStrategy;
import main.java.gov.gsa.fssi.helpers.mockdata.MockData;
import main.java.gov.gsa.fssi.helpers.mockdata.MockSchemaField;

import org.junit.Assert;
import org.junit.Test;

public class NumberTypeValidationStrategyTest {

	/**
	 * 
	 */
	@Test
	public void test10DigitFloat() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new NumberTypeValidationStrategy());

		SchemaField field = MockSchemaField.make("NUMBER",
				SchemaField.TYPE_NUMBER);
		Data data = MockData.make("1234.541234677");

		context.validate(field, data);
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not catch error",
				0, data.getMaxErrorLevel());
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not make failure",
				true, data.getStatus());
	}

	/**
	 * 
	 */
	@Test
	public void test2DigitFloat() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new NumberTypeValidationStrategy());

		SchemaField field = MockSchemaField.make("NUMBER",
				SchemaField.TYPE_NUMBER);
		Data data = MockData.make("1234.54");

		context.validate(field, data);
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not catch error",
				0, data.getMaxErrorLevel());
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not make failure",
				true, data.getStatus());
	}

	/**
	 * 
	 */
	@Test
	public void testAlreadyFailed() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new NumberTypeValidationStrategy());

		SchemaField field = MockSchemaField.make("NUMBER",
				SchemaField.TYPE_NUMBER);
		Data data = MockData.make("12345.345");
		data.setStatus(2);
		data.setMaxErrorLevel(2);

		context.validate(field, data);

		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not catch error",
				2, data.getMaxErrorLevel());
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not make failure",
				false, data.getStatus());
	}

	/**
	 * 
	 */
	@Test
	public void testBadNumber() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new NumberTypeValidationStrategy());
		
		SchemaField field = MockSchemaField.make("NUMBER",
				SchemaField.TYPE_NUMBER);
		Data data = MockData.make("value");
		field.setTypeErrorLevel(3);
		context.validate(field, data);

		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not catch error",
				3, data.getMaxErrorLevel());
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not make failure",
				false, data.getStatus());
	}

	/**
	 * 
	 */
	@Test
	public void testInteger() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new NumberTypeValidationStrategy());

		SchemaField field = MockSchemaField.make("NUMBER",
				SchemaField.TYPE_NUMBER);
		Data data = MockData.make("123");

		context.validate(field, data);

		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not catch error",
				0, data.getMaxErrorLevel());
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not make failure",
				true, data.getStatus());
	}

	/**
	 * 
	 */
	@Test
	public void testNull() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new NumberTypeValidationStrategy());

		SchemaField field = MockSchemaField.make("NUMBER",
				SchemaField.TYPE_NUMBER);
		Data data = MockData.make();

		context.validate(field, data);
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not catch error",
				0, data.getMaxErrorLevel());
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not make failure",
				true, data.getStatus());
	}

	
	
	
	/**
	 * 
	 */
	@Test
	public void testBadNumberWithWarningLevel() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new NumberTypeValidationStrategy());

		SchemaField field = MockSchemaField.make("NUMBER",
				SchemaField.TYPE_NUMBER);
		field.setTypeErrorLevel(1);
		Data data = MockData.make("value");
		context.validate(field, data);

		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not catch error",
				1, data.getMaxErrorLevel());
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not make failure",
				true, data.getStatus());
	}	
	
	
	
	
	/**
	 * 
	 */
	@Test
	public void testBadNumberWithErrorLevel() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new NumberTypeValidationStrategy());

		SchemaField field = MockSchemaField.make("NUMBER",
				SchemaField.TYPE_NUMBER);
		field.setTypeErrorLevel(2);
		Data data = MockData.make("testBadNumberWithErrorLevel");
		context.validate(field, data);

		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not catch error",
				2, data.getMaxErrorLevel());
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not make failure",
				false, data.getStatus());
	}		
	
	
	/**
	 * 
	 */
	@Test
	public void testBadNumberWithErrorLevelWarning() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new NumberTypeValidationStrategy());

		SchemaField field = MockSchemaField.make("NUMBER",
				SchemaField.TYPE_NUMBER);
		field.setTypeErrorLevel(1);
		Data data = MockData.make("testBadNumberWithErrorLevel");
		context.validate(field, data);

		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not catch error",
				1, data.getMaxErrorLevel());
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not make failure",
				true, data.getStatus());
	}
	
	
	/**
	 * 
	 */
	@Test
	public void testBadNumberWithFatalLevel() {
		TypeValidationContext context = new TypeValidationContext();
		context.setTypeValidationStrategy(new NumberTypeValidationStrategy());

		SchemaField field = MockSchemaField.make("NUMBER",
				SchemaField.TYPE_NUMBER);
		field.setTypeErrorLevel(3);
		Data data = MockData.make("value");
		context.validate(field, data);

		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not catch error",
				3, data.getMaxErrorLevel());
		Assert.assertEquals(
				"failure - NumberTypeValidationStrategy did not make failure",
				false, data.getStatus());
	}		
	
}

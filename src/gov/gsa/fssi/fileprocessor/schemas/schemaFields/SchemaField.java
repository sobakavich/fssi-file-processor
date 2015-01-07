package gov.gsa.fssi.fileprocessor.schemas.schemaFields;

import gov.gsa.fssi.fileprocessor.schemas.schemaElements.SchemaElement;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaField {
	static Logger logger = LoggerFactory.getLogger(SchemaField.class);
	
	
	private String name = null;
	private String title = null;	
	private String description = null;
	private ArrayList<SchemaElement> constraints = new ArrayList<SchemaElement>();	
	private ArrayList<String> alias = new ArrayList<String>();		
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the constraints
	 */
	public ArrayList<SchemaElement> getConstraints() {
		return constraints;
	}
	/**
	 * @param constraintMap the constraints to set
	 */
	public void setConstraints(ArrayList<SchemaElement> constraintMap) {
		this.constraints = constraintMap;
	}
	/**
	 * @param constraint
	 */
	public void addConstraint(SchemaElement constraint) {
		this.constraints.add(constraint);
	}	
	/**
	 * @return the alias
	 */
	public ArrayList<String> getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(ArrayList<String> alias) {
		this.alias = alias;
	}
	/**
	 * @param alias
	 */
	public void addAlias(String alias) {
		this.alias.add(alias);
	}
	/**
	 * Print Field
	 */
	public void print() {
		logger.debug("     Field '{}' Description: '{}' Alias: {}}",  this.getName(),  this.getDescription(), this.getAlias());
		printConstraints();
	}
	
	private void printConstraints(){
		for (SchemaElement constraint : this.getConstraints()) {
			constraint.print();
		}
	}
}

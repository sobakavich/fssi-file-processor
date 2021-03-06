package main.java.gov.gsa.fssi.files.sourcefiles.records.datas.results;

import main.java.gov.gsa.fssi.files.sourcefiles.records.SourceFileRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationResult {
	private static final Logger logger = LoggerFactory
			.getLogger(SourceFileRecord.class);

	private int errorLevel = 0;

	/**
	 * Pass or Fail
	 */
	private boolean status = true;

	private String rule = null;

	public ValidationResult() {

	}

	public ValidationResult(boolean status, int errorLevel, String rule) {
		this.setErrorLevel(errorLevel);
		this.setStatus(status);
		this.setRule(rule);
	}

	/**
	 * @return current level
	 */
	public int getErrorLevel() {
		return errorLevel;
	}

	/**
	 * @return current rule
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * @return current status
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setErrorLevel(int errorLevel) {
		this.errorLevel = errorLevel;
	}

	/**
	 * @param rule
	 *            the rule to set
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}

package org.jcf;

import org.jivesoftware.smackx.ReportedData.Column;
import org.springframework.util.Assert;

/**
*
* Represents the columns definition of the reported data.
*
* @author Gaston Dombiak
* @author Fakod
*/
public class JCFColumnImpl implements JCFColumn {

	/**
	 * used for delegation
	 */
	private Column column;

	/**
	 * default ctor
	 * @param column
	 */
	JCFColumnImpl(Column column) {
		Assert.notNull(column);
		this.column= column;
	}

	/**
	 * return original Smack Object
	 * @return
	 */
	Column getColumn() {
		return column;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFColumn#getLabel()
	 */
	public String getLabel() {
		return column.getLabel();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFColumn#getType()
	 */
	public String getType() {
		return column.getType();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFColumn#getVariable()
	 */
	public String getVariable() {
		return column.getVariable();
	}

}

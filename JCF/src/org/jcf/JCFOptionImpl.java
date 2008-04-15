package org.jcf;

import org.jivesoftware.smackx.FormField.Option;
import org.springframework.util.Assert;

/**
 * Represents the available option of a given FormField.
 *
 * @author Gaston Dombiak
 * @author FaKod
 */
public class JCFOptionImpl implements JCFOption {

	/**
	 * used for delegation
	 */
	private Option option;

	/**
	 * default ctor
	 * @param next
	 */
	JCFOptionImpl(Option option) {
		Assert.notNull(option);
		this.option = option;
	}

	/**
	 * returns original Smack Object
	 * @return
	 */
	Option getOption() {
		return option;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFOption#getLabel()
	 */
	public String getLabel() {
		return option.getLabel();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFOption#getValue()
	 */
	public String getValue() {
		return option.getValue();
	}

}

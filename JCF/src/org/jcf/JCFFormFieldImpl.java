package org.jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.FormField.Option;
import org.springframework.util.Assert;

/**
 * Represents a field of a form. The field could be used to represent a question to complete,
 * a completed question or a data returned from a search. The exact interpretation of the field
 * depends on the context where the field is used.
 *
 * @author Gaston Dombiak
 * @author FaKod
 */
public class JCFFormFieldImpl implements JCFFormField {
	
	/**
	 * used for delegation
	 */
	FormField formField;

	/**
	 * default ctor
	 * @param formField
	 */
	JCFFormFieldImpl(FormField formField) {
		Assert.notNull(formField);
		this.formField = formField;
	}

	/**
	 * returns original Smack Instance
	 * @return
	 */
	FormField getFormField() {
		return formField;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#addOption(org.jcf.JCFOption)
	 */
	public void addOption(JCFOption option) {
		Assert.notNull(option);
		formField.addOption(((JCFOptionImpl)option).getOption());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#addValue(java.lang.String)
	 */
	public void addValue(String value) {
		Assert.hasLength(value);
		formField.addValue(value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#addValues(java.util.List)
	 */
	public void addValues(List<String> newValues) {
		Assert.notEmpty(newValues);
		formField.addValues(newValues);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#getDescription()
	 */
	public String getDescription() {
		return formField.getDescription();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#getLabel()
	 */
	public String getLabel() {
		return formField.getLabel();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#getOptions()
	 */
	public Collection<JCFOption> getOptions() {
		Iterator<Option> i = formField.getOptions();
		List<JCFOption> l = Collections.synchronizedList(new ArrayList<JCFOption>());
		while(i.hasNext())
			l.add(new JCFOptionImpl(i.next()));
		return l;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#getType()
	 */
	public String getType() {
		return formField.getType();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#getValues()
	 */
	public Collection<String> getValues() {
		Iterator<String> i = formField.getValues();
		List<String> l = Collections.synchronizedList(new ArrayList<String>());
		while(i.hasNext())
			l.add(new String(i.next()));
		return l;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#getVariable()
	 */
	public String getVariable() {
		return formField.getVariable();
	}

	public boolean isRequired() {
		return formField.isRequired();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		Assert.hasLength(description);
		formField.setDescription(description);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {
		Assert.hasLength(label);
		formField.setLabel(label);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#setRequired(boolean)
	 */
	public void setRequired(boolean required) {
		formField.setRequired(required);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFFormField#setType(java.lang.String)
	 */
	public void setType(String type) {
		Assert.hasLength(type);
		formField.setType(type);
	}

}

package org.jcf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.springframework.util.Assert;

/**
 * Represents a Form for gathering data. The form could be of the following types:
 * <ul>
 *  <li>form -> Indicates a form to fill out.</li>
 *  <li>submit -> The form is filled out, and this is the data that is being returned from 
 * the form.</li>
 *  <li>cancel -> The form was cancelled. Tell the asker that piece of information.</li>
 *  <li>result -> Data results being returned from a search, or some other query.</li>
 * </ul>
 * 
 * Depending of the form's type different operations are available. For example, it's only possible
 * to set answers if the form is of type "submit".
 * 
 * @author Gaston Dombiak
 * @author FaKod
 */
public class JCFFormImpl implements JCFForm {

	/**
	 * used for delegation
	 */
	private Form searchForm;
	
	/**
	 * default ctor
	 * @param searchForm
	 */
	JCFFormImpl(Form searchForm) {
		Assert.notNull(searchForm);
		this.searchForm = searchForm;
	}

	/**
	 * return Smack Form Object
	 * @return Smack Form Object
	 */
	Form getForm() {
		return searchForm;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#addField(org.jcf.JCFFormField)
	 */
	public void addField(JCFFormField field) {
		Assert.notNull(field);
		searchForm.addField(((JCFFormFieldImpl)field).getFormField());
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#createAnswerForm()
	 */
	public JCFForm createAnswerForm() {
		return new JCFFormImpl(searchForm.createAnswerForm());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#getDataFormToSend()
	 */
	public JCFDataForm getDataFormToSend() {
		return new JCFDataFormImpl(searchForm.getDataFormToSend());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#getField(java.lang.String)
	 */
	public JCFFormField getField(String variable) {
		Assert.hasLength(variable);
		return new JCFFormFieldImpl(searchForm.getField(variable));
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#getFields()
	 */
	public Iterator<JCFFormField> getFields() {
		Iterator<FormField> i = searchForm.getFields();
		List<JCFFormField> l = new ArrayList<JCFFormField>();
		while(i.hasNext()) {
			l.add(new JCFFormFieldImpl(i.next()));
		}
		return l.iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#getInstructions()
	 */
	public String getInstructions() {
		return searchForm.getInstructions();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#getTitle()
	 */
	public String getTitle() {
		return searchForm.getTitle();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#getType()
	 */
	public String getType() {
		return searchForm.getType();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#setAnswer(java.lang.String, boolean)
	 */
	public void setAnswer(String variable, boolean value) {
		Assert.hasLength(variable);
		searchForm.setAnswer(variable, value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#setAnswer(java.lang.String, double)
	 */
	public void setAnswer(String variable, double value) {
		Assert.hasLength(variable);
		searchForm.setAnswer(variable, value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#setAnswer(java.lang.String, float)
	 */
	public void setAnswer(String variable, float value) {
		Assert.hasLength(variable);
		searchForm.setAnswer(variable, value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#setAnswer(java.lang.String, int)
	 */
	public void setAnswer(String variable, int value) {
		Assert.hasLength(variable);
		searchForm.setAnswer(variable, value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#setAnswer(java.lang.String, java.lang.String)
	 */
	public void setAnswer(String variable, String value) {
		Assert.hasLength(variable);
		searchForm.setAnswer(variable, value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#setAnswer(java.lang.String, java.util.List)
	 */
	public void setAnswer(String variable, List<String> values) {
		Assert.hasLength(variable);
		searchForm.setAnswer(variable, values);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#setAnswer(java.lang.String, long)
	 */
	public void setAnswer(String variable, long value) {
		Assert.hasLength(variable);
		searchForm.setAnswer(variable, value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#setDefaultAnswer(java.lang.String)
	 */
	public void setDefaultAnswer(String variable) {
		Assert.hasLength(variable);
		searchForm.setDefaultAnswer(variable);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#setInstructions(java.lang.String)
	 */
	public void setInstructions(String instructions) {
		Assert.hasLength(instructions);
		searchForm.setInstructions(instructions);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFForm#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		Assert.hasLength(title);
		searchForm.setTitle(title);	
	}

}

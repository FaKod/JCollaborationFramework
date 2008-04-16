package org.jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.packet.DataForm;
import org.jivesoftware.smackx.packet.DataForm.Item;
import org.springframework.util.Assert;

/**
 * Represents a form that could be use for gathering data as well as for reporting data
 * returned from a search.
 *
 * @author Gaston Dombiak
 * @author FaKod
 */
public class JCFDataFormImpl implements JCFDataForm {

	/**
	 * used to delegate
	 */
	DataForm dataForm;
	
	/**
	 * default ctor
	 * @param dataForm
	 */
	JCFDataFormImpl(DataForm dataForm) {
		Assert.notNull(dataForm);
		this.dataForm = dataForm;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#addField(org.jcf.JCFFormField)
	 */
	public void addField(JCFFormField field) {
		Assert.notNull(field);
		dataForm.addField(((JCFFormFieldImpl)field).getFormField());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#addInstruction(java.lang.String)
	 */
	public void addInstruction(String instruction) {
		Assert.hasLength(instruction);
		dataForm.addInstruction(instruction);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#addItem(org.jcf.JCFReportedDataItem)
	 */
	public void addItem(JCFReportedDataItem item) {
		Assert.notNull(item);
		dataForm.addItem(((JCFReportedDataItemImpl)item).getItem());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#getFields()
	 */
	public Collection<JCFFormField> getFields() {
		Iterator<FormField> i = dataForm.getFields();
		List<JCFFormField> l = Collections.synchronizedList(new ArrayList<JCFFormField>());
		while(i.hasNext())
			l.add(new JCFFormFieldImpl(i.next()));
		return l;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#getInstructions()
	 */
	public Collection<String> getInstructions() {
		Iterator<String> i = dataForm.getInstructions();
		List<String> l = Collections.synchronizedList(new ArrayList<String>());
		while(i.hasNext())
			l.add(new String(i.next()));
		return l;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#getItems()
	 */
	public Collection<JCFReportedDataItem> getItems() {
		Iterator<Item> i = dataForm.getItems();
		List<JCFReportedDataItem> l = Collections.synchronizedList(new ArrayList<JCFReportedDataItem>());
		while(i.hasNext())
			l.add(new JCFReportedDataItemImpl(i.next()));
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#getReportedData()
	 */
	public JCFReportedDataFromDataForm getReportedData() {
		return new JCFReportedDataFromDataFormImpl(dataForm.getReportedData());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#getTitle()
	 */
	public String getTitle() {
		return dataForm.getTitle();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#getType()
	 */
	public String getType() {
		return dataForm.getType();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#setInstructions(java.util.List)
	 */
	public void setInstructions(List<String> instructions) {
		Assert.notEmpty(instructions);
		dataForm.setInstructions(instructions);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#setReportedData(org.jcf.JCFReportedData)
	 */
	public void setReportedData(JCFReportedDataFromDataForm reportedData) {
		Assert.notNull(reportedData);
		dataForm.setReportedData(((JCFReportedDataFromDataFormImpl)reportedData).getReportedData());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFDataForm#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		Assert.hasLength(title);
		dataForm.setTitle(title);
	}

}

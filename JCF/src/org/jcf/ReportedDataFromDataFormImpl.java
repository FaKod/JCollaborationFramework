package org.jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.packet.DataForm.ReportedData;
import org.springframework.util.Assert;

public class ReportedDataFromDataFormImpl implements JCFReportedDataFromDataForm {

	/**
	 * used for delegation
	 */
	private ReportedData reportedData;
	
	/**
	 * default ctor
	 * @param reportedData
	 */
	ReportedDataFromDataFormImpl(ReportedData reportedData) {
		Assert.notNull(reportedData);
		this.reportedData = reportedData;
	}

	/**
	 * returns the original Smack Class
	 * @return
	 */
	ReportedData getReportedData() {
		return reportedData;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFReportedData#getFields()
	 */
	public Collection<JCFFormField> getFields() {
		Iterator<FormField> i =  reportedData.getFields();
		List<JCFFormField> l = Collections.synchronizedList(new ArrayList<JCFFormField>());
		while(i.hasNext())
			l.add(new FormFieldImpl(i.next()));
		return l;
	}

}

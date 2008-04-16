package org.jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smackx.ReportedData;
import org.jivesoftware.smackx.ReportedData.Column;
import org.jivesoftware.smackx.ReportedData.Row;
import org.springframework.util.Assert;


/**
 * Represents a set of data results returned as part of a search. The report is structured 
 * in columns and rows.
 * 
 * @author Gaston Dombiak
 * @author FaKod
 */
public class JCFReportedDataFromSearchImpl implements JCFReportedDataFromSearch {

	/**
	 * used for delegation
	 */
	private ReportedData reportedData;
	
	/**
	 * default ctor
	 * @param reportedData
	 */
	JCFReportedDataFromSearchImpl(ReportedData reportedData) {
		Assert.notNull(reportedData);
		this.reportedData = reportedData;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFReportedDataFromSearch#addColumn(org.jcf.JCFColumn)
	 */
	public void addColumn(JCFColumn column) {
		Assert.notNull(column);
		reportedData.addColumn(((JCFColumnImpl)column).getColumn());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFReportedDataFromSearch#addRow(org.jcf.JCFRow)
	 */
	public void addRow(JCFRow row) {
		Assert.notNull(row);
		reportedData.addRow(((JCFRowImpl)row).getRow());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFReportedDataFromSearch#getColumns()
	 */
	public Collection<JCFColumn> getColumns() {
		Iterator<Column> i = reportedData.getColumns();
		List<JCFColumn> l = Collections.synchronizedList(new ArrayList<JCFColumn>());
		while(i.hasNext())
			l.add(new JCFColumnImpl(i.next()));
		return l;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFReportedDataFromSearch#getRows()
	 */
	public Collection<JCFRow> getRows() {
		Iterator<Row> i = reportedData.getRows();
		List<JCFRow> l = Collections.synchronizedList(new ArrayList<JCFRow>());
		while(i.hasNext())
			l.add(new JCFRowImpl(i.next()));
		return l;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFReportedDataFromSearch#getTitle()
	 */
	public String getTitle() {
		return reportedData.getTitle();
	}

}

package org.jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smackx.ReportedData.Row;
import org.springframework.util.Assert;

public class JCFRowImpl implements JCFRow {

	/**
	 * used for delegate
	 */
	private Row row;

	/**
	 * default ctor
	 * @param row
	 */
	JCFRowImpl(Row row) {
		Assert.notNull(row);
		this.row = row;
	}

	/**
	 * returns original Smack object
	 * @return
	 */
	Row getRow() {
		return row;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRow#getValues(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Collection<String> getValues(String variable) {
		Assert.hasLength(variable);
		Iterator<String> i = row.getValues(variable);
		List<String> l = new ArrayList<String>();
		while(i.hasNext())
			l.add(new String(i.next()));
		return l;
	}

}

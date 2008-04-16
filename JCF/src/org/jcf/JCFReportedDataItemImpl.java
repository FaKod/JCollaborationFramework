package org.jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.packet.DataForm.Item;
import org.springframework.util.Assert;

/**
 * 
 * Represents items of reported data.
 *
 * @author Gaston Dombiak
 * @author FaKod
 */
public class JCFReportedDataItemImpl implements JCFReportedDataItem {

	/**
	 * used to delegate
	 */
	private Item item;

	/**
	 * default ctor
	 * @param item
	 */
	JCFReportedDataItemImpl(Item item) {
		Assert.notNull(item);
		this.item = item;
	}

	/**
	 * used to get the Smack Item
	 * @return
	 */
	Item getItem() {
		return item;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFReportedDataItem#getFields()
	 */
	public Collection<JCFFormField> getFields() {
		Iterator<FormField> i = item.getFields();
		List<JCFFormField> l = Collections.synchronizedList(new ArrayList<JCFFormField>());
		while(i.hasNext())
			l.add(new JCFFormFieldImpl(i.next()));
		return l;
	}

}

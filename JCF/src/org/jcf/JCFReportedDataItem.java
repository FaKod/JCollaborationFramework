package org.jcf;

import java.util.Collection;

/**
 * 
 * Represents items of reported data.
 *
 * @author Gaston Dombiak
 * @author FaKod
 */
public interface JCFReportedDataItem {
	
	/**
     * Returns the fields that define the data that goes with the item.
     * 
     * @return the fields that define the data that goes with the item.
     */
    public Collection<JCFFormField> getFields();

}

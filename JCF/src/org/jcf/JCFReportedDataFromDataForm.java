package org.jcf;

import java.util.Collection;

/**
 * 
 * Represents the fields that will be returned from a search. This information is useful when 
 * you try to use the jabber:iq:search namespace to return dynamic form information.
 *
 * @author Gaston Dombiak
 * @author FaKod
 */
public interface JCFReportedDataFromDataForm {
	
	/**
     * Returns the fields returned from a search.
     * 
     * @return the fields returned from a search.
     */
    public Collection<JCFFormField> getFields();
}

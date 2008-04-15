package org.jcf;

import java.util.Collection;


/**
 * Represents a set of data results returned as part of a search. The report is structured 
 * in columns and rows.
 * 
 * @author Gaston Dombiak
 * @author FaKod
 */
public interface JCFReportedDataFromSearch {

	/**
     * Adds a new <code>Column</code>
     * @param column the column to add.
     */
    public void addColumn(JCFColumn column);
    
    /**
     * Adds a new <code>Row</code>.
     * @param row the new row to add.
     */
    public void addRow(JCFRow row);
    
    /**
     * Returns an Collection for the columns returned from a search.
     *
     * @return an Collection for the columns returned from a search.
     */
    public Collection<JCFColumn> getColumns();
    
    /**
     * Returns an Collection for the rows returned from a search.
     *
     * @return an Collection for the rows returned from a search.
     */
    public Collection<JCFRow> getRows();
    
    /**
     * Returns the report's title. It is similar to the title on a web page or an X
     * window.
     *
     * @return title of the report.
     */
    public String getTitle();
}

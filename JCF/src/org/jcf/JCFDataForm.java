package org.jcf;

import java.util.Collection;
import java.util.List;

/**
 * Represents a form that could be use for gathering data as well as for reporting data
 * returned from a search.
 *
 * @author Gaston Dombiak
 * @author FaKod
 */
public interface JCFDataForm {

	/**
     * Adds a new field as part of the form.
     * 
     * @param field the field to add to the form.
     */
    public void addField(JCFFormField field);
    
    /**
     * Adds a new instruction to the list of instructions that explain how to fill out the form 
     * and what the form is about. The dataform could include multiple instructions since each 
     * instruction could not contain newlines characters. 
     * 
     * @param instruction the new instruction that explain how to fill out the form.
     */
    public void addInstruction(String instruction);
    
    /**
     * Adds a new item returned from a search.
     * 
     * @param item the item returned from a search.
     */
    public void addItem(JCFReportedDataItem item);
    
    /**
     * Returns an Iterator for the fields that are part of the form.
     *
     * @return an Iterator for the fields that are part of the form.
     */
    public Collection<JCFFormField> getFields();
    
    /**
     * Returns an Iterator for the list of instructions that explain how to fill out the form and 
     * what the form is about. The dataform could include multiple instructions since each 
     * instruction could not contain newlines characters. Join the instructions together in order 
     * to show them to the user.    
     * 
     * @return an Iterator for the list of instructions that explain how to fill out the form.
     */
    public Collection<String> getInstructions();
    
    /**
     * Returns an Iterator for the items returned from a search.
     *
     * @return an Iterator for the items returned from a search.
     */
    public Collection<JCFReportedDataItem> getItems();
    
    /**
     * Returns the fields that will be returned from a search.
     * 
     * @return fields that will be returned from a search.
     */
    public JCFReportedDataFromDataForm getReportedData();
    
    /**
     * Returns the description of the data. It is similar to the title on a web page or an X 
     * window.  You can put a <title/> on either a form to fill out, or a set of data results.
     * 
     * @return description of the data.
     */
    public String getTitle();
    
    /**
     * Returns the meaning of the data within the context. The data could be part of a form
     * to fill out, a form submission or data results.<p>
     * 
     * Possible form types are:
     * <ul>
     *  <li>form -> This packet contains a form to fill out. Display it to the user (if your 
     * program can).</li>
     *  <li>submit -> The form is filled out, and this is the data that is being returned from 
     * the form.</li>
     *  <li>cancel -> The form was cancelled. Tell the asker that piece of information.</li>
     *  <li>result -> Data results being returned from a search, or some other query.</li>
     * </ul>
     * 
     * @return the form's type.
     */
    public String getType();
    
    /**
     * Sets the list of instructions that explain how to fill out the form and what the form is 
     * about. The dataform could include multiple instructions since each instruction could not 
     * contain newlines characters. 
     * 
     * @param instructions list of instructions that explain how to fill out the form.
     */
    public void setInstructions(List<String> instructions);
    
    /**
     * Sets the fields that will be returned from a search.
     * 
     * @param reportedData the fields that will be returned from a search.
     */
    public void setReportedData(JCFReportedDataFromDataForm reportedData);
    
    /**
     * Sets the description of the data. It is similar to the title on a web page or an X window.
     * You can put a <title/> on either a form to fill out, or a set of data results.
     * 
     * @param title description of the data.
     */
    public void setTitle(String title);
}

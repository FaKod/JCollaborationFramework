package org.jcf;

import java.util.Collection;
import java.util.List;

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
public interface JCFForm {
	
	/**
     * Adds a new field to complete as part of the form.
     * 
     * @param field the field to complete.
     */
    public void addField(JCFFormField field);
    
    /**
     * Returns a new Form to submit the completed values. The new Form will include all the fields
     * of the original form except for the fields of type FIXED. Only the HIDDEN fields will 
     * include the same value of the original form. The other fields of the new form MUST be 
     * completed. If a field remains with no answer when sending the completed form, then it won't 
     * be included as part of the completed form.<p>
     * 
     * The reason why the fields with variables are included in the new form is to provide a model 
     * for binding with any UI. This means that the UIs will use the original form (of type 
     * "form") to learn how to render the form, but the UIs will bind the fields to the form of
     * type submit.
     * 
     * @return a Form to submit the completed values.
     */
    public JCFForm createAnswerForm();
    
    /**
     * Returns a DataForm that serves to send this Form to the server. If the form is of type 
     * submit, it may contain fields with no value. These fields will be removed since they only 
     * exist to assist the user while editing/completing the form in a UI. 
     * 
     * @return the wrapped DataForm.
     */
    public JCFDataForm getDataFormToSend();
    
    /**
     * Returns the field of the form whose variable matches the specified variable.
     * The fields of type FIXED will never be returned since they do not specify a 
     * variable. 
     * 
     * @param variable the variable to look for in the form fields. 
     * @return the field of the form whose variable matches the specified variable.
     */
    public JCFFormField getField(String variable);
    
    /**
     * Returns an Iterator for the fields that are part of the form.
     *
     * @return an Iterator for the fields that are part of the form.
     */
    public Collection<JCFFormField> getFields();
    
    /**
     * Returns the instructions that explain how to fill out the form and what the form is about.
     * 
     * @return instructions that explain how to fill out the form.
     */
    public String getInstructions();
    
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
     *  <li>form -> Indicates a form to fill out.</li>
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
     * Sets a new boolean value to a given form's field. The field whose variable matches the 
     * requested variable will be completed with the specified value. If no field could be found 
     * for the specified variable then an exception will be raised.
     * 
     * @param variable the variable name that was completed.
     * @param value the boolean value that was answered.
     * @throws IllegalStateException if the form is not of type "submit".
     * @throws IllegalArgumentException if the form does not include the specified variable.
     * @throws IllegalArgumentException if the answer type does not correspond with the field type.
     */
    public void setAnswer(String variable, boolean value);
    
    /**
     * Sets a new double value to a given form's field. The field whose variable matches the 
     * requested variable will be completed with the specified value. If no field could be found 
     * for the specified variable then an exception will be raised.
     * 
     * @param variable the variable name that was completed.
     * @param value the double value that was answered.
     * @throws IllegalStateException if the form is not of type "submit".
     * @throws IllegalArgumentException if the form does not include the specified variable.
     * @throws IllegalArgumentException if the answer type does not correspond with the field type.
     */
    public void setAnswer(String variable, double value);
    
    /**
     * Sets a new float value to a given form's field. The field whose variable matches the 
     * requested variable will be completed with the specified value. If no field could be found 
     * for the specified variable then an exception will be raised.
     * 
     * @param variable the variable name that was completed.
     * @param value the float value that was answered.
     * @throws IllegalStateException if the form is not of type "submit".
     * @throws IllegalArgumentException if the form does not include the specified variable.
     * @throws IllegalArgumentException if the answer type does not correspond with the field type.
     */
    public void setAnswer(String variable, float value);
    
    /**
     * Sets a new int value to a given form's field. The field whose variable matches the 
     * requested variable will be completed with the specified value. If no field could be found 
     * for the specified variable then an exception will be raised.
     * 
     * @param variable the variable name that was completed.
     * @param value the int value that was answered.
     * @throws IllegalStateException if the form is not of type "submit".
     * @throws IllegalArgumentException if the form does not include the specified variable.
     * @throws IllegalArgumentException if the answer type does not correspond with the field type.
     */
    public void setAnswer(String variable, int value);
    
    /**
     * Sets a new String value to a given form's field. The field whose variable matches the 
     * requested variable will be completed with the specified value. If no field could be found 
     * for the specified variable then an exception will be raised.<p>
     * 
     * If the value to set to the field is not a basic type (e.g. String, boolean, int, etc.) you
     * can use this message where the String value is the String representation of the object. 
     * 
     * @param variable the variable name that was completed.
     * @param value the String value that was answered.
     * @throws IllegalStateException if the form is not of type "submit".
     * @throws IllegalArgumentException if the form does not include the specified variable.
     * @throws IllegalArgumentException if the answer type does not correspond with the field type.
     */
    public void setAnswer(String variable, String value);
    
    /**
     * Sets a new values to a given form's field. The field whose variable matches the requested 
     * variable will be completed with the specified values. If no field could be found for 
     * the specified variable then an exception will be raised.<p>
     * 
     * The Objects contained in the List could be of any type. The String representation of them
     * (i.e. #toString) will be actually used when sending the answer to the server.
     * 
     * @param variable the variable that was completed.
     * @param values the values that were answered.
     * @throws IllegalStateException if the form is not of type "submit".
     * @throws IllegalArgumentException if the form does not include the specified variable.
     */
    public void setAnswer(String variable, List<String> values);
    
    /**
     * Sets a new long value to a given form's field. The field whose variable matches the 
     * requested variable will be completed with the specified value. If no field could be found 
     * for the specified variable then an exception will be raised.
     * 
     * @param variable the variable name that was completed.
     * @param value the long value that was answered.
     * @throws IllegalStateException if the form is not of type "submit".
     * @throws IllegalArgumentException if the form does not include the specified variable.
     * @throws IllegalArgumentException if the answer type does not correspond with the field type.
     */
    public void setAnswer(String variable, long value);
    
    /**
     * Sets the default value as the value of a given form's field. The field whose variable matches
     * the requested variable will be completed with its default value. If no field could be found
     * for the specified variable then an exception will be raised.
     *
     * @param variable the variable to complete with its default value.
     * @throws IllegalStateException if the form is not of type "submit".
     * @throws IllegalArgumentException if the form does not include the specified variable.
     */
    public void setDefaultAnswer(String variable);
    
    /**
     * Sets instructions that explain how to fill out the form and what the form is about.
     * 
     * @param instructions instructions that explain how to fill out the form.
     */
    public void setInstructions(String instructions);
    
    /**
     * Sets the description of the data. It is similar to the title on a web page or an X window.
     * You can put a <title/> on either a form to fill out, or a set of data results.
     * 
     * @param title description of the data.
     */
    public void setTitle(String title);

}

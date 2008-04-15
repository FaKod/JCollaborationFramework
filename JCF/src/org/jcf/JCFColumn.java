package org.jcf;

/**
*
* Represents the columns definition of the reported data.
*
* @author Gaston Dombiak
* @author Fakod
*/
public interface JCFColumn {

	/**
     * Returns the column's label.
     *
     * @return label of the column.
     */
    public String getLabel();
    
    /**
     * Returns the column's data format. Valid formats are:
     *
     * <ul>
     *  <li>text-single -> single line or word of text
     *  <li>text-private -> instead of showing the user what they typed, you show ***** to
     * protect it
     *  <li>text-multi -> multiple lines of text entry
     *  <li>list-single -> given a list of choices, pick one
     *  <li>list-multi -> given a list of choices, pick one or more
     *  <li>boolean -> 0 or 1, true or false, yes or no. Default value is 0
     *  <li>fixed -> fixed for putting in text to show sections, or just advertise your web
     * site in the middle of the form
     *  <li>hidden -> is not given to the user at all, but returned with the questionnaire
     *  <li>jid-single -> Jabber ID - choosing a JID from your roster, and entering one based
     * on the rules for a JID.
     *  <li>jid-multi -> multiple entries for JIDs
     * </ul>
     *
     * @return format for the returned data.
     */
    public String getType();
    
    /**
     * Returns the variable name that the column is showing.
     *
     * @return the variable name of the column.
     */
    public String getVariable();
}

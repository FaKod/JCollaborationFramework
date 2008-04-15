package org.jcf;

import java.util.Collection;
import java.util.List;

/**
 * Represents a field of a form. The field could be used to represent a question to complete,
 * a completed question or a data returned from a search. The exact interpretation of the field
 * depends on the context where the field is used.
 *
 * @author Gaston Dombiak
 * @author FaKod
 */
public interface JCFFormField {

	/**
     * Adss an available options to the question that the user has in order to answer
     * the question.
     *
     * @param option a new available option for the question.
     */
    public void addOption(JCFOption option);
    
    /**
     * Adds a default value to the question if the question is part of a form to fill out.
     * Otherwise, adds an answered value to the question.
     *
     * @param value a default value or an answered value of the question.
     */
    public void addValue(String value);
    
    /**
     * Adds a default values to the question if the question is part of a form to fill out.
     * Otherwise, adds an answered values to the question.
     *
     * @param newValues default values or an answered values of the question.
     */
    public void addValues(List<String> newValues);
    
    /**
     * Returns a description that provides extra clarification about the question. This information
     * could be presented to the user either in tool-tip, help button, or as a section of text
     * before the question.<p>
     * <p/>
     * If the question is of type FIXED then the description should remain empty.
     *
     * @return description that provides extra clarification about the question.
     */
    public String getDescription();
    
    /**
     * Returns the label of the question which should give enough information to the user to
     * fill out the form.
     *
     * @return label of the question.
     */
    public String getLabel();
    
    /**
     * Returns an Collection for the available options that the user has in order to answer
     * the question.
     *
     * @return Collection for the available options.
     */
    public Collection<JCFOption> getOptions();
    
    /**
     * Returns an indicative of the format for the data to answer. Valid formats are:
     * <p/>
     * <ul>
     * <li>text-single -> single line or word of text
     * <li>text-private -> instead of showing the user what they typed, you show ***** to
     * protect it
     * <li>text-multi -> multiple lines of text entry
     * <li>list-single -> given a list of choices, pick one
     * <li>list-multi -> given a list of choices, pick one or more
     * <li>boolean -> 0 or 1, true or false, yes or no. Default value is 0
     * <li>fixed -> fixed for putting in text to show sections, or just advertise your web
     * site in the middle of the form
     * <li>hidden -> is not given to the user at all, but returned with the questionnaire
     * <li>jid-single -> Jabber ID - choosing a JID from your roster, and entering one based
     * on the rules for a JID.
     * <li>jid-multi -> multiple entries for JIDs
     * </ul>
     *
     * @return format for the data to answer.
     */
    public String getType();
    
    /**
     * Returns an Collection for the default values of the question if the question is part
     * of a form to fill out. Otherwise, returns an Iterator for the answered values of
     * the question.
     *
     * @return an Collection for the default values or answered values of the question.
     */
    public Collection<String> getValues();
    
    /**
     * Returns the variable name that the question is filling out.
     *
     * @return the variable name of the question.
     */
    public String getVariable();
    
    /**
     * Returns true if the question must be answered in order to complete the questionnaire.
     *
     * @return true if the question must be answered in order to complete the questionnaire.
     */
    public boolean isRequired();
    
    /**
     * Sets a description that provides extra clarification about the question. This information
     * could be presented to the user either in tool-tip, help button, or as a section of text
     * before the question.<p>
     * <p/>
     * If the question is of type FIXED then the description should remain empty.
     *
     * @param description provides extra clarification about the question.
     */
    public void setDescription(String description);
    
    /**
     * Sets the label of the question which should give enough information to the user to
     * fill out the form.
     *
     * @param label the label of the question.
     */
    public void setLabel(String label);
    
    /**
     * Sets if the question must be answered in order to complete the questionnaire.
     *
     * @param required if the question must be answered in order to complete the questionnaire.
     */
    public void setRequired(boolean required);
    
    /**
     * Sets an indicative of the format for the data to answer. Valid formats are:
     * <p/>
     * <ul>
     * <li>text-single -> single line or word of text
     * <li>text-private -> instead of showing the user what they typed, you show ***** to
     * protect it
     * <li>text-multi -> multiple lines of text entry
     * <li>list-single -> given a list of choices, pick one
     * <li>list-multi -> given a list of choices, pick one or more
     * <li>boolean -> 0 or 1, true or false, yes or no. Default value is 0
     * <li>fixed -> fixed for putting in text to show sections, or just advertise your web
     * site in the middle of the form
     * <li>hidden -> is not given to the user at all, but returned with the questionnaire
     * <li>jid-single -> Jabber ID - choosing a JID from your roster, and entering one based
     * on the rules for a JID.
     * <li>jid-multi -> multiple entries for JIDs
     * </ul>
     *
     * @param type an indicative of the format for the data to answer.
     */
    public void setType(String type);
}

package org.jcf;

/**
 * Represents the available option of a given FormField.
 *
 * @author Gaston Dombiak
 * @author FaKod
 */
public interface JCFOption {

	/**
     * Returns the label that represents the option.
     *
     * @return the label that represents the option.
     */
    public String getLabel();
    
    /**
     * Returns the value of the option.
     *
     * @return the value of the option.
     */
    public String getValue();
}

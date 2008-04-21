package org.jcf;

import java.util.Collection;

/**
 * Global Message Class holding the received Message
 * @author FaKod
 *
 */
public interface JCFMessage {

	/**
     * Returns the body of the message, or null if the body has not been set. The body
     * is the main message contents.
     *
     * @return the body of the message.
     */
    public String getBody();
    
    /**
     * Returns the body corresponding to the language. If the language is null, the method result
     * will be the same as {@link #getBody()}. Null will be returned if the language does not have
     * a corresponding body.
     *
     * @param language the language of the body to return.
     * @return the body related to the passed in language.
     * @since 3.0.2
     */
    public String getBody(String language);
    
    /**
     * Returns all the languages being used for the bodies, not including the default body.
     *
     * @return the languages being used for the bodies.
     * @since 3.0.2
     */
    public Collection<String> getBodyLanguages();
    
    /**
     * Returns the subject of the message, or null if the subject has not been set.
     * The subject is a short description of message contents.
     *
     * @return the subject of the message.
     */
    public String getSubject();
    
    /**
     * Returns the thread id of the message, which is a unique identifier for a sequence
     * of "chat" messages. If no thread id is set, <tt>null</tt> will be returned.
     *
     * @return the thread id of the message, or <tt>null</tt> if it doesn't exist.
     */
    public String getThread();
    
    /**
     * Removes the body with the given language from the message.
     *
     * @param language the language of the body which is to be removed
     * @return true if a body was removed and false if it was not.
     */
    public boolean removeBody(String language);
    
    /**
     * Sets the body of the message. The body is the main message contents.
     *
     * @param body the body of the message.
     */
    public void setBody(String body);
    
    /**
     * Sets the xml:lang of this Message.
     *
     * @param language the xml:lang of this Message.
     * @since 3.0.2
     */
    public void setLanguage(String language);
    
    /**
     * Sets the subject of the message. The subject is a short description of
     * message contents.
     *
     * @param subject the subject of the message.
     */
    public void setSubject(String subject);
    
    /**
     * Sets the thread id of the message, which is a unique identifier for a sequence
     * of "chat" messages.
     *
     * @param thread the thread id of the message.
     */
    public void setThread(String thread);
    
    /**
     * Sets a property with an Object as the value. The value must be Serializable
     * or an IllegalArgumentException will be thrown.
     *
     * @param name the name of the property.
     * @param value the value of the property.
     */
    public void setProperty(String name, Object value);
    
    /**
     * Returns the packet property with the specified name or <tt>null</tt> if the
     * property doesn't exist. Property values that were orginally primitives will
     * be returned as their object equivalent. For example, an int property will be
     * returned as an Integer, a double as a Double, etc.
     *
     * @param name the name of the property.
     * @return the property, or <tt>null</tt> if the property doesn't exist.
     */
    public Object getProperty(String name);
}

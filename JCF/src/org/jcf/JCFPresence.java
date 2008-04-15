package org.jcf;


/**
 * Represents XMPP presence packets. Every presence packet has a type, which is one of
 * the following values:
 * <ul>
 *      <li>{@link Presence.Type#available available} -- (Default) indicates the user is available to
 *          receive messages.
 *      <li>{@link Presence.Type#unavailable unavailable} -- the user is unavailable to receive messages.
 *      <li>{@link Presence.Type#subscribe subscribe} -- request subscription to recipient's presence.
 *      <li>{@link Presence.Type#subscribed subscribed} -- grant subscription to sender's presence.
 *      <li>{@link Presence.Type#unsubscribe unsubscribe} -- request removal of subscription to
 *          sender's presence.
 *      <li>{@link Presence.Type#unsubscribed unsubscribed} -- grant removal of subscription to
 *          sender's presence.
 *      <li>{@link Presence.Type#error error} -- the presence packet contains an error message.
 * </ul><p>
 *
 * A number of attributes are optional:
 * <ul>
 *      <li>Status -- free-form text describing a user's presence (i.e., gone to lunch).
 *      <li>Priority -- non-negative numerical priority of a sender's resource. The
 *          highest resource priority is the default recipient of packets not addressed
 *          to a particular resource.
 *      <li>Mode -- one of five presence modes: {@link Mode#available available} (the default),
 *          {@link Mode#chat chat}, {@link Mode#away away}, {@link Mode#xa xa} (extended away), and
 *          {@link Mode#dnd dnd} (do not disturb).
 * </ul><p>
 *
 * Presence packets are used for two purposes. First, to notify the server of our
 * the clients current presence status. Second, they are used to subscribe and
 * unsubscribe users from the roster.
 *
 * @see RosterPacket
 * @author FaKod
 * @author Matt Tucker
 */
public interface JCFPresence {
	
	/**
     * Returns the mode of the presence update, or <tt>null</tt> if the mode is not set.
     * A null presence mode value is interpreted to be the same thing as
     * {@link Presence.Mode#available}.
     *
     * @return the mode.
     */
    public JCFMode getMode();
    
    /**
     * Returns the priority of the presence, or Integer.MIN_VALUE if no priority has been set.
     *
     * @return the priority.
     */
    public int getPriority();
    
    /**
     * Returns the status message of the presence update, or <tt>null</tt> if there
     * is not a status. The status is free-form text describing a user's presence
     * (i.e., "gone to lunch").
     *
     * @return the status message.
     */
    public String getStatus();
    
    /**
     * Returns the type of this presence packet.
     *
     * @return the type of the presence packet.
     */
    public JCFType getType();
    
    /**
     * Returns true if the {@link Type presence type} is available (online) and
     * false if the user is unavailable (offline), or if this is a presence packet
     * involved in a subscription operation. This is a convenience method
     * equivalent to <tt>getType() == Presence.Type.available</tt>.
     *
     * @return true if the presence type is available.
     */
    public boolean isAvailable();
    
    /**
     * Returns true if the presence type is {@link Type#available available} and the presence
     * mode is {@link Mode#away away}, {@link Mode#xa extended away}, or
     * {@link Mode#dnd do not disturb}. False will be returned when the type or mode
     * is any other value, including when the presence type is unavailable (offline).
     * This is a convenience method equivalent to
     * <tt>type == Type.available && (mode == Mode.away || mode == Mode.xa || mode == Mode.dnd)</tt>.
     *
     * @return true if the presence type is available and the presence mode is away, xa, or dnd.
     */
    public boolean isAway();
    
    /**
     * Sets the xml:lang of this Presence.
     *
     * @param language the xml:lang of this Presence.
     * @since 3.0.2
     */
    public void setLanguage(String language);
    
    /**
     * Sets the mode of the presence update. A null presence mode value is interpreted
     * to be the same thing as {@link Presence.Mode#available}.
     *
     * @param mode the mode.
     */
    public void setMode(JCFMode mode);
    
    /**
     * Sets the priority of the presence. The valid range is -128 through 128.
     *
     * @param priority the priority of the presence.
     * @throws IllegalArgumentException if the priority is outside the valid range.
     */
    public void setPriority(int priority);
    
    /**
     * Sets the status message of the presence update. The status is free-form text
     * describing a user's presence (i.e., "gone to lunch").
     *
     * @param status the status message.
     */
    public void setStatus(String status);
    
    /**
     * Sets the type of the presence packet.
     *
     * @param type the type of the presence packet.
     */
    public void setType(JCFType type);
    
}

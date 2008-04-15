package org.jcf;

import java.util.Collection;


/**
 * Each user in your roster is represented by a roster entry, which contains the user's
 * JID and a name or nickname you assign.
 *
 * @author Matt Tucker
 * @author FaKod
 */
public interface JCFRosterEntry {
	
	/**
     * Returns an unmodifiable collection of the roster groups that this entry belongs to.
     *
     * @return an iterator for the groups this entry belongs to.
     */
    public Collection<JCFRosterGroup> getGroups();
    
    /**
     * Returns the name associated with this entry.
     *
     * @return the name.
     */
    public String getName();
    
    /**
     * Returns the roster subscription status of the entry. When the status is
     * RosterPacket.ItemStatus.SUBSCRIPTION_PENDING, the contact has to answer the
     * subscription request.
     *
     * @return the status as String.
     */
    public String getStatus();
    
    /**
     * Returns the roster subscription type of the entry. When the type is
     * RosterPacket.ItemType.none or RosterPacket.ItemType.from,
     * refer to {@link RosterEntry getStatus()} to see if a subscription request
     * is pending.
     *
     * @return the type.
     */
    public JCFRosterEntryType getType();
    
    /**
     * Returns the JID of the user associated with this entry.
     *
     * @return the user associated with this entry.
     */
    public String getUser();
    
    /**
     * Sets the name associated with this entry.
     *
     * @param name the name.
     */
    public void setName(String name);
    
    public String toString();

}

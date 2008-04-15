package org.jcf;

import java.util.Collection;


/**
 * A group of roster entries.
 *
 * @see Roster#getGroup(String)
 * @author Matt Tucker
 * @author FaKod
 */
public interface JCFRosterGroup {
	
	/**
     * Adds a roster entry to this group. If the entry was unfiled then it will be removed from 
     * the unfiled list and will be added to this group.
     *
     * @param entry a roster entry.
     * @throws XMPPException if an error occured while trying to add the entry to the group.
     */
    public void addEntry(JCFRosterEntry entry);
    
    /**
     * Returns true if the specified XMPP address is an entry in this group.
     *
     * @param user the XMPP address of the user.
     * @return true if the XMPP address is an entry in this group.
     */
    public boolean contains(String user);
    
    /**
     * Returns true if the specified entry is part of this group.
     *
     * @param entry a roster entry.
     * @return true if the entry is part of this group.
     */
    public boolean contains(JCFRosterEntry entry);
    
    /**
     * Returns an unmodifiable collection of all entries in the group.
     *
     * @return all entries in the group.
     */
    public Collection<JCFRosterEntry> getEntries();
    
    /**
     * Returns the roster entry associated with the given XMPP address or
     * <tt>null</tt> if the user is not an entry in the group.
     *
     * @param user the XMPP address of the user (eg "jsmith@example.com").
     * @return the roster entry or <tt>null</tt> if it does not exist in the group.
     */
    public JCFRosterEntry getEntry(String user);
    
    /**
     * Returns the number of entries in the group.
     *
     * @return the number of entries in the group.
     */
    public int getEntryCount();
    
    /**
     * Returns the name of the group.
     *
     * @return the name of the group.
     */
    public String getName();
    
    /**
     * Removes a roster entry from this group. If the entry does not belong to any other group 
     * then it will be considered as unfiled, therefore it will be added to the list of unfiled 
     * entries.
     *
     * @param entry a roster entry.
     * @throws XMPPException if an error occured while trying to remove the entry from the group. 
     */
    public void removeEntry(JCFRosterEntry entry);

    /**
     * Sets the name of the group. Changing the group's name is like moving all the group entries
     * of the group to a new group specified by the new name. Since this group won't have entries 
     * it will be removed from the roster. This means that all the references to this object will 
     * be invalid and will need to be updated to the new group specified by the new name.
     *
     * @param name the name of the group.
     */
    public void setName(String name);
}

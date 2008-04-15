package org.jcf;

import java.util.Collection;

import org.jivesoftware.smack.Roster;


/**
 * A listener that is fired any time a roster is changed or the presence of
 * a user in the roster is changed.
 * 
 * @author FaKod
 */
public interface JCFRosterListener {

	/**
     * Called when roster entries are added.
     *
     * @param addresses the XMPP addresses of the contacts that have been added to the roster.
     */
    public void entriesAdded(Collection<String> addresses);

    /**
     * Called when a roster entries are updated.
     *
     * @param addresses the XMPP addresses of the contacts whose entries have been updated.
     */
    public void entriesUpdated(Collection<String> addresses);

    /**
     * Called when a roster entries are removed.
     *
     * @param addresses the XMPP addresses of the contacts that have been removed from the roster.
     */
    public void entriesDeleted(Collection<String> addresses);

    /**
     * Called when the presence of a roster entry is changed. Care should be taken
     * when using the presence data delivered as part of this event. Specifically,
     * when a user account is online with multiple resources, the UI should account
     * for that. For example, say a user is online with their desktop computer and
     * mobile phone. If the user logs out of the IM client on their mobile phone, the
     * user should not be shown in the roster (contact list) as offline since they're
     * still available as another resource.<p>
     *
     * To get the current "best presence" for a user after the presence update, query the roster:
     * <pre>
     *    String user = presence.getFrom();
     *    Presence bestPresence = roster.getPresence(user);
     * </pre>
     *
     * That will return the presence value for the user with the highest priority and
     * availability.
     *
     * @param presence the presence that changed.
     * @see Roster#getPresence(String)
     */
    public void presenceChanged(JCFPresence presence);
}

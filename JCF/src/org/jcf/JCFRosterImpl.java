package org.jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.Roster.SubscriptionMode;
import org.jivesoftware.smack.packet.Presence;
import org.springframework.util.Assert;


/**
 * Represents a user's roster, which is the collection of users a person receives
 * presence updates for. Roster items are categorized into groups for easier management.<p>
 * 
 * @author FaKod
 * @author Matt Tucker
 */
public class JCFRosterImpl implements JCFRoster {
	
	Roster roster;
	
	List<JCFRosterListener> rosterListener;
	
	/**
	 * dont use this
	 */
	@SuppressWarnings("unused")
	private JCFRosterImpl(){}

	/**
	 * default ctor uses the given Smack roster
	 * @param roster
	 */
	JCFRosterImpl(Roster roster) {
		Assert.notNull(roster);
		this.roster = roster;	
		rosterListener = new ArrayList<JCFRosterListener>();
		
		/**
		 * use original roster
		 */
		roster.addRosterListener(new RosterListener() {

			public void entriesAdded(Collection<String> addresses) {
				for(JCFRosterListener l : rosterListener)
					l.entriesAdded(addresses);
			}

			public void entriesDeleted(Collection<String> addresses) {
				for(JCFRosterListener l : rosterListener)
					l.entriesDeleted(addresses);
			}

			public void entriesUpdated(Collection<String> addresses) {
				for(JCFRosterListener l : rosterListener)
					l.entriesUpdated(addresses);
			}

			public void presenceChanged(Presence presence) {
				for(JCFRosterListener l : rosterListener)
					l.presenceChanged(new JCFPresenceImpl(presence));
			}
		    
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#addRosterListener(org.jcf.JCFRosterListener)
	 */
	public void addRosterListener(JCFRosterListener rosterListener) {
		this.rosterListener.add(rosterListener);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#contains(java.lang.String)
	 */
	public boolean contains(String user) {
		Assert.hasLength(user);
		return roster.contains(user);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#createEntry(java.lang.String, java.lang.String, java.lang.String[])
	 */
	public void createEntry(String user, String name, String[] groups) {
		Assert.hasLength(user);
		Assert.hasLength(name);
		try {
			roster.createEntry(user, name, groups);
		} catch (XMPPException e) {
			throw new JCFException("error in JCFRoster.createEntry ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#createGroup(java.lang.String)
	 */
	public JCFRosterGroup createGroup(String name) {
		Assert.hasLength(name);
		return new JCFRosterGroupImpl(roster.createGroup(name));
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#getEntries()
	 */
	public Collection<JCFRosterEntry> getEntries() {
		Collection<RosterEntry> c = roster.getEntries();
		List<JCFRosterEntry> cj = new ArrayList<JCFRosterEntry>();
		for(RosterEntry r : c)
			cj.add(new JCFRosterEntryImpl(r));
		return Collections.unmodifiableCollection(cj);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#getEntry(java.lang.String)
	 */
	public JCFRosterEntry getEntry(String user) {
		Assert.notNull(user);
		return new JCFRosterEntryImpl(roster.getEntry(user));
	}

	public int getEntryCount() {
		return roster.getEntryCount();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#getGroup(java.lang.String)
	 */
	public JCFRosterGroup getGroup(String name) {
		Assert.hasLength(name);
		return new JCFRosterGroupImpl(roster.getGroup(name));
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#getGroupCount()
	 */
	public int getGroupCount() {
		return roster.getGroupCount();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#getGroups()
	 */
	public Collection<JCFRosterGroup> getGroups() {
		Collection<RosterGroup> c = roster.getGroups();
		List<JCFRosterGroup> cj = new ArrayList<JCFRosterGroup>();
		for(RosterGroup r : c)
			cj.add(new JCFRosterGroupImpl(r));
		return Collections.unmodifiableList(cj);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#getPresence(java.lang.String)
	 */
	public JCFPresence getPresence(String user) {
		Assert.hasLength(user);
		return new JCFPresenceImpl(roster.getPresence(user));
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#getPresenceResource(java.lang.String)
	 */
	public JCFPresence getPresenceResource(String userWithResource) {
		Assert.hasLength(userWithResource);
		return new JCFPresenceImpl(roster.getPresenceResource(userWithResource));
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#getSubscriptionMode()
	 */
	public JCFSubscriptionMode getSubscriptionMode() {
		return JCFSubscriptionMode.valueOf(roster.getSubscriptionMode().toString());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#getUnfiledEntries()
	 */
	public Collection<JCFRosterEntry> getUnfiledEntries() {
		Collection<RosterEntry> c = roster.getUnfiledEntries();
		List<JCFRosterEntry> cj = new ArrayList<JCFRosterEntry>();
		for(RosterEntry r : c)
			cj.add(new JCFRosterEntryImpl(r));
		return Collections.unmodifiableCollection(cj);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#getUnfiledEntryCount()
	 */
	public int getUnfiledEntryCount() {
		return roster.getUnfiledEntryCount();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#reload()
	 */
	public void reload() {
		roster.reload();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#removeEntry(org.jcf.JCFRosterEntry)
	 */
	public void removeEntry(JCFRosterEntry entry) {
		Assert.notNull(entry);
		try {
			roster.removeEntry(((JCFRosterEntryImpl)entry).getRosterEntry());
		} catch (XMPPException e) {
			throw new JCFException("Error in JCFRoster.removeEntry",e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#removeRosterListener(org.jcf.JCFRosterListener)
	 */
	public void removeRosterListener(JCFRosterListener rosterListener) {
		this.rosterListener.remove(rosterListener);
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRoster#setSubscriptionMode(org.jcf.JCFSubscriptionMode)
	 */
	public void setSubscriptionMode(JCFSubscriptionMode subscriptionMode) {
		Assert.notNull(subscriptionMode);
		roster.setSubscriptionMode(SubscriptionMode.valueOf(subscriptionMode.toString()));
	}

}

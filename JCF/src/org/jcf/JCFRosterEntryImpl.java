package org.jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.packet.RosterPacket;
import org.springframework.util.Assert;

/**
 * Each user in your roster is represented by a roster entry, which contains the user's
 * JID and a name or nickname you assign.
 *
 * @author Matt Tucker
 * @author FaKod
 */
public class JCFRosterEntryImpl implements JCFRosterEntry {

	/**
	 * used for delegation
	 */
	private RosterEntry rosterEntry;

	JCFRosterEntryImpl(RosterEntry rosterEntry) {
		Assert.notNull(rosterEntry);
		this.rosterEntry = rosterEntry;
	}

	/**
	 * returns the Roster Entry
	 * @return rosterEntry object
	 */
	RosterEntry getRosterEntry() {
		return rosterEntry;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterEntry#getGroups()
	 */
	public Collection<JCFRosterGroup> getGroups() {
		Collection<RosterGroup> c = rosterEntry.getGroups();
		List<JCFRosterGroup> cj = Collections.synchronizedList(new ArrayList<JCFRosterGroup>());
		for(RosterGroup r : c)
			cj.add(new JCFRosterGroupImpl(r));
		return Collections.unmodifiableCollection(cj);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterEntry#getName()
	 */
	public String getName() {
		return rosterEntry.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterEntry#getStatus()
	 */
	public String getStatus() {
		return rosterEntry.getStatus().toString();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterEntry#getType()
	 */
	public JCFRosterEntryType getType() {
		RosterPacket.ItemType t = rosterEntry.getType();
		return JCFRosterEntryType.valueOf(t.toString());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterEntry#getUser()
	 */
	public String getUser() {
		return rosterEntry.getUser();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterEntry#setName(java.lang.String)
	 */
	public void setName(String name) {
		Assert.hasLength(name);
		rosterEntry.setName(name);
	}

}

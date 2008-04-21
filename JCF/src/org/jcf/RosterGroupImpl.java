package org.jcf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.XMPPException;
import org.springframework.util.Assert;

/**
 * A group of roster entries.
 *
 * @see Roster#getGroup(String)
 * @author Matt Tucker
 * @author FaKod
 */
public class RosterGroupImpl implements JCFRosterGroup {

	/**
	 * used for delegation
	 */
	RosterGroup rosterGroup;
	
	/**
	 * default ctor
	 * @param createGroup used for delegation
	 */
	RosterGroupImpl(RosterGroup rosterGroup) {
		Assert.notNull(rosterGroup);
		this.rosterGroup = rosterGroup;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterGroup#addEntry(org.jcf.JCFRosterEntry)
	 */
	public void addEntry(JCFRosterEntry entry) {
		Assert.notNull(entry);
		try {
			rosterGroup.addEntry(((RosterEntryImpl)entry).getRosterEntry());
		} catch (XMPPException e) {
			throw new JCFException("Error in JCFRosterGroupImpl.addEntry ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterGroup#contains(java.lang.String)
	 */
	public boolean contains(String user) {
		Assert.hasLength(user);
		return rosterGroup.contains(user);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterGroup#contains(org.jcf.JCFRosterEntry)
	 */
	public boolean contains(JCFRosterEntry entry) {
		Assert.notNull(entry);
		return rosterGroup.contains(((RosterEntryImpl)entry).getRosterEntry());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterGroup#getEntries()
	 */
	public Collection<JCFRosterEntry> getEntries() {
		List<JCFRosterEntry> cj = Collections.synchronizedList(new ArrayList<JCFRosterEntry>());
		Collection<RosterEntry> cr = rosterGroup.getEntries();
		for(RosterEntry r : cr)
			cj.add(new RosterEntryImpl(r));
		return Collections.unmodifiableList(cj);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterGroup#getEntry(java.lang.String)
	 */
	public JCFRosterEntry getEntry(String user) {
		Assert.hasLength(user);
		return new RosterEntryImpl(rosterGroup.getEntry(user));
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterGroup#getEntryCount()
	 */
	public int getEntryCount() {
		return rosterGroup.getEntryCount();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterGroup#getName()
	 */
	public String getName() {
		return rosterGroup.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterGroup#removeEntry(org.jcf.JCFRosterEntry)
	 */
	public void removeEntry(JCFRosterEntry entry) {
		Assert.notNull(entry);
		try {
			rosterGroup.removeEntry(((RosterEntryImpl)entry).getRosterEntry());
		} catch (XMPPException e) {
			throw new JCFException("Error in JCFRosterGroup.removeEntry",e);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFRosterGroup#setName(java.lang.String)
	 */
	public void setName(String name) {
		Assert.hasLength(name);
		rosterGroup.setName(name);
	}

}

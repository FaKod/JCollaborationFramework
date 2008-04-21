package org.jcf;

import org.jivesoftware.smackx.muc.RoomInfo;
import org.springframework.util.Assert;

/**
 * Represents the room information that was discovered using Service Discovery. It's possible to
 * obtain information about a room before joining the room but only for rooms that are public (i.e.
 * rooms that may be discovered).
 *
 * @author FaKod
 */
class RoomInfoImpl implements MUCRoomInfo {
	
	/**
	 * isntance of Smack class
	 */
	RoomInfo instance;
	
	/**
	 * default ctor
	 * @param ri instance of room info
	 */
	RoomInfoImpl(RoomInfo ri) {
		Assert.notNull(ri);
		instance = ri;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.MCURoomInfo#getDescription()
	 */
	public String getDescription() {
		return instance.getDescription();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.MCURoomInfo#getOccupantsCount()
	 */
	public int getOccupantsCount() {
		return instance.getOccupantsCount();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.MCURoomInfo#getRoom()
	 */
	public String getRoom() {
		return instance.getRoom();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.MCURoomInfo#getSubject()
	 */
	public String getSubject() {
		return instance.getSubject();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.MCURoomInfo#isMembersOnly()
	 */
	public boolean isMembersOnly() {
		return instance.isMembersOnly();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.MCURoomInfo#isModerated()
	 */
	public boolean isModerated() {
		return instance.isModerated();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.MCURoomInfo#isNonanonymous()
	 */
	public boolean isNonanonymous() {
		return instance.isNonanonymous();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.MCURoomInfo#isPasswordProtected()
	 */
	public boolean isPasswordProtected() {
		return instance.isPasswordProtected();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.MCURoomInfo#isPersistent()
	 */
	public boolean isPersistent() {
		return instance.isPersistent();
	}

}

package org.jcf;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.Presence.Type;
import org.springframework.util.Assert;

/**
 * delegator for smacks presence class
 * @author fakod
 * @author Matt Tucker
 */
public class JCFPresenceImpl implements JCFPresence {

	/**
	 * used as delegator
	 */
	Presence presence;
	
	/**
	 * default ctor using smack ctor
	 * @param presence
	 */
	JCFPresenceImpl(Presence presence) {
		Assert.notNull(presence);
		this.presence = presence;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#getMode()
	 */
	public JCFMode getMode() {
		Mode m = presence.getMode();
		Assert.notNull(m);
		return JCFMode.valueOf(m.toString());
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#getPriority()
	 */
	public int getPriority() {
		return presence.getPriority();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#getStatus()
	 */
	public String getStatus() {
		return presence.getStatus();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#getType()
	 */
	public JCFType getType() {
		Type t = presence.getType();
		Assert.notNull(t);
		return JCFType.valueOf(t.toString()); 
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#isAvailable()
	 */
	public boolean isAvailable() {
		return presence.isAvailable();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#isAway()
	 */
	public boolean isAway() {
		return presence.isAway();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#setLanguage(java.lang.String)
	 */
	public void setLanguage(String language) {
		Assert.hasLength(language);
		presence.setLanguage(language);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#setMode(org.jcf.JCFMode)
	 */
	public void setMode(JCFMode mode) {
		Assert.notNull(mode);
		Mode m = Mode.valueOf(mode.toString());
		Assert.notNull(m);
		presence.setMode(m);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#setPriority(int)
	 */
	public void setPriority(int priority) {
		presence.setPriority(priority);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#setStatus(java.lang.String)
	 */
	public void setStatus(String status) {
		Assert.hasLength(status);
		presence.setStatus(status);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFPresence#setType(org.jcf.JCFType)
	 */
	public void setType(JCFType type) {
		Assert.notNull(type);
		Type t = Type.valueOf(type.toString());
		Assert.notNull(t);
		presence.setType(t);
	}

}

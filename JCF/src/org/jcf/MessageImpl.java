package org.jcf;

import java.util.Collection;

import org.jivesoftware.smack.packet.Message;
import org.springframework.util.Assert;

/**
 * Global Message Class holding the received Message
 * @author FaKod
 *
 */
public class MessageImpl implements JCFMessage {

	/**
	 * orginal Smack Message class
	 */
	private Message message;
	
	/**
	 * copy ctor
	 * @param message
	 */
	MessageImpl(Message message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessage#getBody()
	 */
	public String getBody() {
		return message.getBody();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessage#getBody(java.lang.String)
	 */
	public String getBody(String language) {
		Assert.hasLength(language);
		return message.getBody(language);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessage#getBodyLanguages()
	 */
	public Collection<String> getBodyLanguages() {
		return message.getBodyLanguages();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessage#getSubject()
	 */
	public String getSubject() {
		return message.getSubject();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessage#getThread()
	 */
	public String getThread() {
		return message.getThread();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessage#removeBody(java.lang.String)
	 */
	public boolean removeBody(String language) {
		Assert.hasLength(language);
		return message.removeBody(language);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessage#setBody(java.lang.String)
	 */
	public void setBody(String body) {
		Assert.hasLength(body);
		message.setBody(body);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessage#setLanguage(java.lang.String)
	 */
	public void setLanguage(String language) {
		Assert.hasLength(language);
		message.setLanguage(language);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessage#setSubject(java.lang.String)
	 */
	public void setSubject(String subject) {
		Assert.hasLength(subject);
		message.setSubject(subject);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessage#setThread(java.lang.String)
	 */
	public void setThread(String thread) {
		Assert.hasLength(thread);
		message.setThread(thread);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getBody();
	}

}

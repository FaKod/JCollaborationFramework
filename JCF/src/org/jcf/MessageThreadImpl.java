package org.jcf;

import org.springframework.util.Assert;

/**
 * Thread for Messages
 * @author FaKod
 *
 */
public class MessageThreadImpl implements JCFMessageThread {

	/**
	 * thread name store
	 */
	private String thread;
	
	/**
	 * default ctor
	 * @param thread name of thread
	 */
	MessageThreadImpl(String thread) {
		Assert.hasLength(thread);
		this.thread = thread;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((thread == null) ? 0 : thread.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MessageThreadImpl other = (MessageThreadImpl) obj;
		if (thread == null) {
			if (other.thread != null)
				return false;
		} else if (!thread.equals(other.thread))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return thread;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessageThread#getThread()
	 */
	public String getThread() {
		return thread;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMessageThread#setThread(java.lang.String)
	 */
	public void setThread(String thread) {
		Assert.hasLength(thread);
		this.thread = thread;
	}

}

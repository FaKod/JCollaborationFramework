package org.jcf;

/**
 * Thread for Messages
 * @author FaKod
 *
 */
public interface JCFMessageThread {

	/**
	 * setting threads name
	 * @param thread name of thread
	 */
	void setThread(String thread);
	
	/**
	 * getting the name of the thread
	 * @return thread name
	 */
	String getThread();
}

package org.jcf;

import org.jcf.graphicMessage.GraphicMessage;

/**
 * Thread listener. Use this to receive Thread messages
 * @author FaKod
 *
 */
public interface JCFThreadMessageListener extends JCFMessageListener {
	
	/**
	 * signs a newly created JCFMessageThread
	 * @param thread thread name
	 */
	void newJCFMessageThread(JCFMessageThread thread);
	
	/**
	 * received message
	 * @param message received message
	 */
	void receivedMessage(JCFMessageThread thread, JCFMessage message);

	/**
	 * received graphic message
	 * @param message message received
	 * @param gm received graphic message
	 */
	void receivedGraphicMessage(JCFMessageThread thread, JCFMessage message, GraphicMessage gm);

}

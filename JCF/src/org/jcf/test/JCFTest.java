package org.jcf.test;

import java.util.ArrayList;

import org.jcf.GraphicObjectEventListener;
import org.jcf.JCFConnection;
import org.jcf.JCFFactory;
import org.jcf.JCFMessageListener;
import org.jcf.JCFMultiUserChat;
import org.jcf.graphicMessage.GraphicMessage;
import org.jcf.graphicMessage.Id;
import org.jcf.graphicMessage.Location;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;


import junit.framework.TestCase;

public class JCFTest extends TestCase {
	
	JCFConnection con;
	JCFMultiUserChat muc;
	
	JCFConnection con2;
	JCFMultiUserChat muc2;
	String jabberServer = "joccis";

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {	
		super.setUp();
		
		XMPPConnection connection = new XMPPConnection(jabberServer);
		connection.connect();
		if (connection.getAccountManager().supportsAccountCreation()) {
            try {
            	connection.getAccountManager().createAccount("test", "test");
            	connection.getAccountManager().createAccount("test2", "test2");
            } catch (XMPPException e) {}
        }
		connection.disconnect();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {	
		super.tearDown();
		con.unConnect();
	}
	
	public void testJCF() throws Exception {
		con = JCFFactory.newJCFConnection(jabberServer, "test", "test");
		con.connect();
		
		muc = JCFFactory.newJCFMultiUserChat(con);
		muc.addListener(new listener("test"));
		muc.createRoom("MyTestRoom");
		
		
		
		con2 = JCFFactory.newJCFConnection(jabberServer, "test2", "test2");
		con2.connect();
		muc2 = JCFFactory.newJCFMultiUserChat(con2);
		muc2.addListener(new listener("test2"));
		muc2.joinRoom("MyTestRoom");
		muc2.getGraphicObjectHandler().addListener(new GOListener("test2"));
		
		// sending Messages
		muc.sendMessageWithoutGeographicMessage("sendMessageWithoutGeographicMessage MUC");
		muc2.sendMessageWithoutGeographicMessage("sendMessageWithoutGeographicMessage MUC2");
		
		muc.getGraphicObjectHandler().addListener(new GOListener("test"));
		muc.getGraphicObjectHandler().createNewGraphicMessage();
		
		ArrayList<Location> list = new ArrayList<Location>();
		list.add(new Location(5,1));
		
		muc.getGraphicObjectHandler().createPointObject(new Location(1,1))
		.createPointObject(new Location(2,1))
		.createLineObject(list)
		.createPolygonObject(list);
		
		muc.sendMessage("sendMessage");
		
		Thread.sleep(2000)
;		
	}
	
	/**
	 * class for listener test
	 * @author FaKod
	 *
	 */
	class GOListener implements GraphicObjectEventListener {
		
		String name;
		
		GOListener(String name) {
			this.name = name;
		}

		public void createEvent(Id id) {
			System.out.println("GOListener: " + name + ": Created Object with Id: " + id);
		}

		public void deleteEvent(Id id) {
			System.out.println("GOListener: " + name + ": Deleted Object with Id: " + id);
			
		}
		
	}
	
	/**
	 * 
	 * @author FaKod
	 *
	 */
	public class listener implements JCFMessageListener {
		
		String name;
		
		listener(String name) {
			this.name = name;
		}

		public void receivedGraphicMessage(GraphicMessage gm) {
			System.out.println("Listener " + name + ": GraphicMessage received: " + gm);
		}

		public void receivedMessage(String message) {
			System.out.println("Listener " + name + ": Message received: " + message);
		}
		
	}

}

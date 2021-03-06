package org.jcf.test;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.TestCase;

import org.jcf.GraphicObjectEventListener;
import org.jcf.JCFConnection;
import org.jcf.JCFFactory;
import org.jcf.JCFForm;
import org.jcf.JCFMessage;
import org.jcf.JCFMessageThread;
import org.jcf.JCFSimpleMessageListener;
import org.jcf.JCFMultiUserChat;
import org.jcf.JCFReportedDataFromSearch;
import org.jcf.JCFRow;
import org.jcf.JCFThreadMessageListener;
import org.jcf.JCFUserSearchManager;
import org.jcf.graphicMessage.GraphicMessage;
import org.jcf.graphicMessage.Id;
import org.jcf.graphicMessage.Location;
import org.jcf.graphicMessage.LocationImpl;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

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
		System.out.println("**** Start testJCF() ****");
		
		System.out.println("\n\n**** Creating Room ****\n\n");
		con = JCFFactory.newJCFConnection(jabberServer, "test", "test");
		con.connect();
		
		muc = JCFFactory.newJCFMultiUserChat(con);
		muc.addListener(new listener("User:test"));
		muc.createRoom("MyTestRoom");
		muc.getGraphicObjectHandler().addListener(new GOListener("User:test"));
		
		Thread.sleep(200);
		
		System.out.println("\n\n**** Joining room ****\n\n");
		con2 = JCFFactory.newJCFConnection(jabberServer, "test2", "test2");
		con2.connect();
		muc2 = JCFFactory.newJCFMultiUserChat(con2);
		muc2.addListener(new listener("User:test2"));
		muc2.joinRoom("MyTestRoom");
		muc2.getGraphicObjectHandler().addListener(new GOListener("User:test2"));
		
		
		// sending Messages
		System.out.println("\n\n**** Test send Message Without Geographic Message ****\n\n");
		muc.sendMessageWithoutGeographicMessage(muc.createJCFMessage("sendMessageWithoutGeographicMessage MUC"));
		Thread.sleep(200);
		
		
		//sending thread message 1
		System.out.println("\n\n**** Test send Message Without Geographic Message and new Thread ****\n\n");
		JCFMessage threadMessage = muc2.createJCFMessage("User:test2 is sending this message with new Thread");
		threadMessage.setThread("This is my test Thread");
		muc2.sendMessageWithoutGeographicMessage(threadMessage);
		Thread.sleep(200);
		
		
		//sending thread message 1
		System.out.println("\n\n**** Test send Message Without Geographic Message and same Thread ****\n\n");
		threadMessage = muc2.createJCFMessage("User:test2 is sending this message with old Thread");
		threadMessage.setThread("This is my test Thread");
		muc2.sendMessageWithoutGeographicMessage(threadMessage);
		Thread.sleep(200);
		
		//creating new message
		muc.getGraphicObjectHandler().createNewGraphicMessage();
		
		ArrayList<Location> list = new ArrayList<Location>();
		list.add(new LocationImpl(5,1));
		
		muc.getGraphicObjectHandler().createPointObject(new LocationImpl(1,1))
		.createPointObject(new LocationImpl(2,1))
		.createLineObject(list)
		.createPolygonObject(list)
		.createTextObject("This is my Text Object", new LocationImpl(1,1));
		
		System.out.println("\n\n**** Test send Message with 5 GObjects ****\n\n");
		muc.sendMessage(muc.createJCFMessage("I created 5 Graphical Objects"));
		Thread.sleep(200);
		System.out.println("\n\n**** END ****\n\n");
	}
	
	public void testUserSearch() {
		System.out.println("**** testUserSearch() ****\n\n");
		
		con = JCFFactory.newJCFConnection(jabberServer, "test", "test");
		con.connect();
		
		JCFUserSearchManager usm = con.getJCFUserSearchManager();
		JCFForm searchForm = usm.getSearchForm("search."+jabberServer);
		
		JCFForm answerForm = searchForm.createAnswerForm();
		answerForm.setAnswer("search", "*");
		answerForm.setAnswer("Username", true);
		answerForm.setAnswer("Name", true);
		answerForm.setAnswer("Email", true);
		
		JCFReportedDataFromSearch rd = usm.getSearchResults(answerForm, "search."+jabberServer);
		Collection<JCFRow> rows = rd.getRows();
		for(JCFRow row : rows) {
			Collection<String> jids = row.getValues("jid");
			System.out.println("User: " + jids.toArray()[0]);
		}
		System.out.println("\n\n**** END ****\n\n");
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
	public class listener implements JCFSimpleMessageListener, JCFThreadMessageListener {
		
		String name;
		
		listener(String name) {
			this.name = name;
		}

		public void receivedGraphicMessage(JCFMessage message, GraphicMessage gm) {
			System.out.println("Listener " + name + ": GraphicMessage received: " + gm);
		}

		public void receivedMessage(JCFMessage message) {
			System.out.println("Listener " + name + ": Message received: " + message);
		}

		public void newJCFMessageThread(JCFMessageThread thread) {
			System.out.println("Listener " + name + ": new Thread received: " + thread);
			
		}

		public void receivedGraphicMessage(JCFMessageThread thread,
				JCFMessage message, GraphicMessage gm) {
			System.out.println("Listener " + name + ": GraphicMessage received: " + gm + " from Thread: " + thread);
		}

		public void receivedMessage(JCFMessageThread thread, JCFMessage message) {
			System.out.println("Listener " + name + ": Message received: " + message + " from Thread: " + thread);
		}
		
	}

}

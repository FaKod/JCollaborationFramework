/*
 * Copyright 2002-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jcf;

import java.util.ArrayList;
import java.util.List;

import org.jcf.graphicMessage.GraphicMessage;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.springframework.util.Assert;




/**
 * class for handling multi user chat
 * @author FaKod
 *
 */
public class JCFMultiUserChatImpl implements JCFMultiUserChat {
	
	/**
	 * keyword for property inside an message
	 */
	 String messagePropertyKeyWord = "GraphicMessage";
	
	/**
	 * keyword to sign a conference
	 */
	final String conferenceKey = "@conference.";
	
	/**
	 * attributes used after room is established
	 */
	private GraphicObjectHandler graphicObjectHandler;
	private String roomNikname;
	private String roomID;
	
	private JCFConnection jCFConnection;
	private MultiUserChat multiUserChat;
	
	/**
	 * store for the listener
	 */
	private List<JCFMessageListener> listener;
	
	/**
	 * dont use this
	 */
	@SuppressWarnings("unused")
	private JCFMultiUserChatImpl() {}

	/**
	 * default ctor. use connect to connect to the jabber server
	 * @param jabberServer chatserver name
	 * @param userName username in chatserver to connect
	 * @param passwd password on chatserver
	 */
	JCFMultiUserChatImpl(JCFConnection jCFConnection) {
		Assert.notNull(jCFConnection);
		
		this.jCFConnection = jCFConnection;
		listener = new ArrayList<JCFMessageListener>();
	}
	
	/* (non-Javadoc)
	 * @see org.jcf.IJCFMultiUserChat#createRoom(java.lang.String)
	 */
	public void createRoom(String room) {
		Assert.hasLength(room);
		
		setRoomID(room);
		multiUserChat = new MultiUserChat(((JCFConnectionImpl)jCFConnection).getXMPPconnection(), roomID);
		roomNikname = jCFConnection.getUserName() + "@" + room;
		
		try {
			multiUserChat.create(roomNikname);
			multiUserChat.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
		} catch (XMPPException e) {
			throw new JCFException("error createRoom: " + roomID, e);
		}
	    graphicObjectHandler = JCFFactory.newGraphicObjectHandler(roomNikname, room);
	    addListener();
	}
	
	/* (non-Javadoc)
	 * @see org.jcf.IJCFMultiUserChat#joinRoom(java.lang.String)
	 */
	public void joinRoom(String room) {
		Assert.hasLength(room);
		
		setRoomID(room);
		multiUserChat = new MultiUserChat(((JCFConnectionImpl)jCFConnection).getXMPPconnection(), roomID);
		roomNikname = jCFConnection.getUserName() + "@" + room;
		try {
			multiUserChat.join(roomNikname);
		} catch (XMPPException e) {
			throw new JCFException("error createRoom: " + roomID, e);
		}
		graphicObjectHandler = JCFFactory.newGraphicObjectHandler(roomNikname, room);
		addListener();
	}

	/**
	 * sets the attribute roomID
	 * @param room singe room name
	 */
	private void setRoomID(String room) {
		Assert.hasLength(room);
		
		roomID = room + conferenceKey + jCFConnection.getJabberServer();
	}
	
	/* (non-Javadoc)
	 * @see org.jcf.IJCFMultiUserChat#getGraphicObjectHandler()
	 */
	public GraphicObjectHandler getGraphicObjectHandler() {
		return graphicObjectHandler;
	}
	
	
	/* (non-Javadoc)
	 * @see org.jcf.IJCFMultiUserChat#addListener(org.jcf.JCFMessageListener)
	 */
	public void addListener(JCFMessageListener l) {
		Assert.notNull(l);
		listener.add(l);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMultiUserChat#sendMessage(java.lang.String)
	 */
	public void sendMessage(String body) {
		Assert.notNull(body);
		
		if(getGraphicObjectHandler().getGraphicMessage()==null)
			throw new JCFException("no GraphicMessage. call create first");
		
		if(roomID==null)
			throw new JCFException("no room created or joined");
		
		Message message = new Message(roomID, Message.Type.groupchat);
		message.setBody(body);
		message.setProperty(new String(messagePropertyKeyWord), getGraphicObjectHandler().getGraphicMessage());
		try {
			multiUserChat.sendMessage(message);
		} catch (XMPPException e) {
			e.printStackTrace();
		}	
		getGraphicObjectHandler().createNewGraphicMessage();
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMultiUserChat#sendMessageWithoutGeographicMessage(java.lang.String)
	 */
	public void sendMessageWithoutGeographicMessage(String body) {
		Assert.notNull(body);
		
		Message message = new Message(roomID, Message.Type.groupchat);
		message.setBody(body);
		try {
			multiUserChat.sendMessage(message);
		} catch (XMPPException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * adding listener to the instance of Multi User Chat. GraphicMessages will be processed by the created instance of GraphicMessage
	 * handler.
	 */
	private void addListener() {
		multiUserChat.addMessageListener( new PacketListener() {
			public void processPacket(Packet packet) {
				Message message = (Message) packet;
				GraphicMessage gm = (GraphicMessage) message.getProperty(new String(messagePropertyKeyWord));
				if(gm!=null)
					for(JCFMessageListener l :listener ) {
						getGraphicObjectHandler().processGraphicMessage(gm);
						l.receivedGraphicMessage(gm);
					}	
				for(JCFMessageListener l :listener ) {
					l.receivedMessage(message.getBody());
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMultiUserChat#getJCFConnection()
	 */
	public JCFConnection getJCFConnection() {
		return jCFConnection;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFMultiUserChat#getMultiUserChat()
	 */
	public MultiUserChat getMultiUserChat() {
		return multiUserChat;
	}
	
}

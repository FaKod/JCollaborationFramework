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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.RoomInfo;
import org.jivesoftware.smackx.search.UserSearchManager;
import org.springframework.util.Assert;

/**
 * main connection class used to connect to a jabber server
 * @author FaKod
 *
 */
class JCFConnectionImpl implements JCFConnection {
	
	/**
	 * parameter use for connection
	 */
	private String jabberServer;
	private String userName;
	private String passwd;
	private XMPPConnection xMPPconnection;
	
	/**
	 * store for the invitation listener
	 */
	List<MUCInvitationListener> invitationListener;
	
	/**
	 * Roster stuff
	 */
	private JCFRoster jCFRoster;
	
	/**
	 * dont use this
	 */
	@SuppressWarnings("unused")
	private JCFConnectionImpl(){}
	
	/**
	 * Connection class for connection to a jabber server
	 * @param jabberServer chatserver name
	 * @param userName username in chatserver to connect
	 * @param passwd password on chatserver
	 */
	JCFConnectionImpl(String jabberServer, String userName, String passwd) {
		Assert.hasLength(jabberServer);
		Assert.hasLength(userName);
		Assert.hasLength(passwd);
		
		this.jabberServer = jabberServer;
		this.userName = userName;
		this.passwd = passwd;
		
		invitationListener = new ArrayList<MUCInvitationListener>();
	}
	
	/* (non-Javadoc)
	 * @see org.jcf.JCFConnection#connect()
	 */
	public void connect() {
		xMPPconnection = new XMPPConnection(jabberServer);
		try {
			xMPPconnection.connect();
			xMPPconnection.login(userName, passwd, "Smak", true );
		} catch (XMPPException e) {
			throw new JCFException("cannot connect to " + jabberServer + " with usename " + userName + " and password " + passwd, e);
		}
		
		MultiUserChat.addInvitationListener(xMPPconnection, new InvitationListener() {
			public void invitationReceived(XMPPConnection conn, String room,
					String inviter, String reason, String password,
					Message message) {
				for(MUCInvitationListener l : invitationListener) {
					l.invitationReceived(room, inviter, reason, password, message.getBody());
				}
				
			}
	      });
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFConnection#unConnect()
	 */
	public void unConnect() {
		if(xMPPconnection==null)
			throw new JCFException("Call connect first");
		
		xMPPconnection.disconnect();
		xMPPconnection = null;
	}

	/* (non-Javadoc)
	 * @see org.jcf.JCFConnection#getJabberServer()
	 */
	public String getJabberServer() {
		return jabberServer;
	}

	/* (non-Javadoc)
	 * @see org.jcf.JCFConnection#getUserName()
	 */
	public String getUserName() {
		return userName;
	}

	/* (non-Javadoc)
	 * @see org.jcf.JCFConnection#getPasswd()
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * returns original smack object
	 * @return
	 */
	public XMPPConnection getXMPPconnection() {
		return xMPPconnection;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFConnection#addMUCInvitationListener(org.jcf.MUCInvitationListener)
	 */
	public void addMUCInvitationListener(MUCInvitationListener l) {
		Assert.notNull(l);
		invitationListener.add(l);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFConnection#removeMUCInvitationListener(org.jcf.MUCInvitationListener)
	 */
	public void removeMUCInvitationListener(MUCInvitationListener l) {
		Assert.notNull(l);
		invitationListener.remove(l);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFConnection#declineMUCInvitation(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void declineMUCInvitation(String room, String inviter, String reason) {
		if(xMPPconnection==null)
			throw new JCFException("call connect first");
		MultiUserChat.decline(xMPPconnection, room, inviter, reason);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFConnection#isMUCServiceEnabled(java.lang.String)
	 */
	public boolean isMUCServiceEnabled(String user) {
		if(xMPPconnection==null)
			throw new JCFException("call connect first");
		return MultiUserChat.isServiceEnabled(xMPPconnection, user);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFConnection#getJoinedRooms(java.lang.String)
	 */
	public Collection<String> getJoinedRooms(String user) {
		if(xMPPconnection==null)
			throw new JCFException("call connect first");
		Iterator<String>  i = MultiUserChat.getJoinedRooms(xMPPconnection, user);
		List<String> l = new ArrayList<String>();
		while(i.hasNext())
			l.add(new String(i.next()));
		return l;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFConnection#getMUCRoomInfo(java.lang.String)
	 */
	public MUCRoomInfo getMUCRoomInfo(String room) {
		if(xMPPconnection==null)
			throw new JCFException("call connect first");
		RoomInfo ri;
		try {
			ri = MultiUserChat.getRoomInfo(xMPPconnection, room);
		} catch (XMPPException e) {
			throw new JCFException("error in getMUCRoomInfo", e);
		}
		return new MUCRoomInfoImpl(ri);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFConnection#getRoster()
	 */
	public JCFRoster getRoster() {
		if(xMPPconnection==null)
			throw new JCFException("call connect first");
		if(jCFRoster==null)
			jCFRoster = new JCFRosterImpl(xMPPconnection.getRoster());
		return jCFRoster;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFConnection#getJCFUserSearchManager()
	 */
	public JCFUserSearchManager getJCFUserSearchManager() {
		if(xMPPconnection==null)
			throw new JCFException("call connect first");
		return new JCFUserSearchManagerImpl(new UserSearchManager(xMPPconnection));
	}
}

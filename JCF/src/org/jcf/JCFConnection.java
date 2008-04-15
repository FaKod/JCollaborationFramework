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

import java.util.Iterator;


/**
 * Connection class to a jabber server
 * @author FaKod
 *
 */
public interface JCFConnection {

	/**
	 * connects to the server using the given parameter
	 */
	void connect();
	
	/**
	 * unconnects from jabber server
	 */
	public void unConnect();

	/**
	 * @return the jabberServer
	 */
	String getJabberServer();

	/**
	 * @return the userName
	 */
	String getUserName();

	/**
	 * @return the passwd
	 */
	String getPasswd();
	
	/**
	 * add the invitation listener
	 * @param l the listener instance
	 */
	void addMUCInvitationListener(MUCInvitationListener l);
	
	/**
	 * removes the invitation listener
	 * @param l the listener instance
	 */
	void removeMUCInvitationListener(MUCInvitationListener l);
	
	/**
	 * decline an invitation
	 * @param room romm nem
	 * @param inviter inviter name
	 * @param reason reason of declination
	 */
	void declineMUCInvitation(String room, String inviter, String reason);
	
	/**
	 * has client for User user MUC capability
	 * @param user user name
	 * @return
	 */
	boolean isMUCServiceEnabled(String user);
	
	/**
	 * Returns the rooms the user is in
	 * @param user user name
	 * @return Iterator instance
	 */
	Iterator<String> getJoinedRooms(String user);
	
	/**
	 * returns a instance of room info
	 * @param room the room name
	 * @return MCURoomInfo
	 */
	MUCRoomInfo getMUCRoomInfo(String room);
	
	/**
	 * Returns the roster for the user logged into the server. If the user has not yet
     * logged into the server (or if the user is logged in anonymously), this method will return
	 * @return the user's roster, or <tt>null</tt> if the user has not logged in yet.
	 */
	JCFRoster getRoster();
	
	/**
	 * gets a JCFUserSearchManager for this connection
	 * @return new instance of JCFUserSearchManager
	 */
	JCFUserSearchManager getJCFUserSearchManager();

}
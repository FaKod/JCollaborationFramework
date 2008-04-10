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



/**
 * interface for Multi User Chat. create vie JCFFactory with the parameter to connect to the jabber server. 
 * For the connection call connect. then create a new room or join an existing one. 
 * An instance of Geographic Message Handler is automatically created and filled by receiving objects.
 * @author FaKod
 *
 */
public interface JCFMultiUserChat {

	/**
	 * creates new room. user name is userName + "@" + room
	 * @param room name of the room
	 */
	void createRoom(String room);

	/**
	 * joins room. user name is userName + "@" + room
	 * @param room name of the room
	 */
	void joinRoom(String room);

	/**
	 * returns the GraphicObjectHandler which was created creating or joining a room
	 * @return instance of GraphicObjectHandler
	 */
	GraphicObjectHandler getGraphicObjectHandler();

	/**
	 * adding the listener to receive messages and GraphicMessages
	 * @param l instance of the listener
	 */
	void addListener(JCFMessageListener l);
	
	/**
	 * sends message to room without using the GeographicMessage stored in the instance of GraphicObjectHandler
	 * @param body body of the message
	 */
	void sendMessageWithoutGeographicMessage(String body);
	
	/**
	 * sends message to room using the GeographicMessage stored in the instance of GraphicObjectHandler and a new instance of
	 * GeographicMessage is created.
	 * @param body body of the message
	 */
	void sendMessage(String body);
	
	/**
	 * returns the created instance after calling connect
	 * @return the JCFConnection
	 */
	public JCFConnection getJCFConnection();
	
	/**
	 * invote user to this room
	 * @param user user name
	 * @param reason reason for this invitation
	 */
	public void invite(String user, String reason);
}
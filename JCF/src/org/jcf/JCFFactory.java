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

import org.springframework.util.Assert;

/**
 * global factory class
 * @author FaKod
 *
 */
public abstract class JCFFactory {
	
	/**
	 * creates new Graphic Object Handler for room room and for nikname nikname
	 * normally this is not used. a graphic object handler is created by JCFMultiUserChat
	 * @param nikName users nikname in room
	 * @param room muti user chat room name
	 * @return the newly created GraphicObjectHandler
	 */
	public static GraphicObjectHandler newGraphicObjectHandler(String nikName, String room) {
		Assert.hasLength(nikName);
		Assert.hasLength(room);
		
		return new GraphicObjectHandlerImpl(nikName, room);
	}
	
	/**
	 * created new instance of JCFMultiUserChat
	 * @param jCFConnection the former created insance of a connection object
	 * @return ins<tance of JCFMultiUserChat
	 */
	public static JCFMultiUserChat newJCFMultiUserChat(JCFConnection jCFConnection) {
		Assert.notNull(jCFConnection);
		return new JCFMultiUserChatImpl(jCFConnection);
	}
	
	/**
	 * creates new instance of a connection class
	 * @param jabberServer chatserver name
	 * @param userName username in chatserver to connect
	 * @param passwd password on chatserver
	 * @return instance of JCFConnection
	 */
	public static JCFConnection newJCFConnection(String jabberServer, String userName, String passwd) {
		Assert.hasLength(jabberServer);
		Assert.hasLength(userName);
		Assert.hasLength(passwd);
		
		return new JCFConnectionImpl(jabberServer, userName, passwd);
	}

}

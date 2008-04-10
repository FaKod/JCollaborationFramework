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

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.springframework.util.Assert;

/**
 * main connection class used to connect to a jabber server
 * @author FaKod
 *
 */
public class JCFConnectionImpl implements JCFConnection {
	
	/**
	 * parameter use for connection
	 */
	private String jabberServer;
	private String userName;
	private String passwd;
	private XMPPConnection xMPPconnection;
	
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
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.jcf.JCFConnection#unConnect()
	 */
	public void unConnect() {
		if(xMPPconnection==null)
			throw new JCFException("Call connect first");
		
		xMPPconnection.disconnect();
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

	/* (non-Javadoc)
	 * @see org.jcf.JCFConnection#getXMPPconnection()
	 */
	public XMPPConnection getXMPPconnection() {
		return xMPPconnection;
	}
}

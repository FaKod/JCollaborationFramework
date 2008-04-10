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
package org.jcf.graphicMessage;

import java.io.Serializable;

import org.springframework.util.Assert;

/**
 * unique Id based an nikname and id
 * @author FaKod
 *
 */
public class Id implements Serializable {
	
	/**
	 * Version ID
	 */
	private static final long serialVersionUID = 1L;

	private String nikName;
	private long id;
	
	/**
	 * Don't use this
	 */
	@SuppressWarnings("unused")
	private Id(){};
	
	/**
	 * c-tor to use
	 * @param nikName
	 */
	public Id(String nikName) {
		Assert.hasLength(nikName);
		this.nikName = nikName;
	}
	
	/**
	 * copy ctor
	 * @param id object to copy from
	 */
	public Id(Id id) {
		Assert.notNull(id);
		this.nikName = id.nikName;
		this.id = id.id;
	}

	/**
	 * get the stored nikname (as part of the unique id)
	 * @return nikname String
	 */
	public String getNikName() {
		return nikName;
	}
	
	/**
	 * get the stored id (as part of the unique id)
	 * @return long id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * increments id to get the next value
	 */
	public Id inc() {
		id = id + 1;
		return this;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Id other = (Id) obj;
		if((other.id == this.id) && (other.nikName.equals(this.nikName)) )
			return true;
		return false;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return nikName + ":" + id;
	}

}

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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

/**
 * Id factory creating Ids
 * @author FaKod
 *
 */
abstract class IdFactory {

	/**
	 * id per room map
	 */
	private static Map<String, Id> ids = Collections.synchronizedMap(new HashMap<String, Id>());
	
	/**
	 * get new id (as a new Object) for an existing or new room
	 * @param room string as a room id
	 * @return new and incremented id
	 */
	static Id getNextId(String room, String nikName) {
		Assert.hasLength(room);
		Assert.hasLength(nikName);
		
		Id id;
		synchronized(ids) {
			id = ids.get(room);
			if(id==null) {
				id = new Id(nikName);
				ids.put(room, id);
			}
		}
		return new Id(id.inc());
	}
}

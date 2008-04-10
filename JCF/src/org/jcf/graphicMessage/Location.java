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

/**
 * used to store locations
 * @author FaKod
 *
 */
public class Location implements Serializable {

	/**
	 * Version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * longitude location
	 */
	private long lon;
	
	/**
	 * latitude location
	 */
	private long lat;
	
	/**
	 * creates Location with the given parameters
	 * @param lon longitude
	 * @param lat latitude
	 */
	public Location(long lon, long lat) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public long getLon() {
		return lon;
	}
	public void setLon(long lon) {
		this.lon = lon;
	}
	public long getLat() {
		return lat;
	}
	public void setLat(long lat) {
		this.lat = lat;
	}
	
	
}

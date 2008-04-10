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
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

/**
 * Represents a graphical Object with a list of points
 * stored as instances of Location
 * @author FaKod
 *
 */
public abstract class GraphicObject implements Serializable {

	/**
	 * unique id of graphical object
	 */
	private Id id;
	
	/**
	 * list of locations (ordered)
	 */
	private List<Location> locations;
	
	/**
	 * ctor to use with unique id
	 * @param id
	 */
	protected GraphicObject(Id id) {
		Assert.notNull(id);
		this.id = id;
	}
	
	/**
	 * dont use this
	 */
	@SuppressWarnings("unused")
	private GraphicObject() {
		
	}

	/**
	 * graphical object id
	 * @return Id
	 */
	public Id getId() {
		return id;
	}

	/**
	 * returns list of object points as instances of Location
	 * @return points as locations
	 */
	public List<Location> getLocations() {
		return locations;
	}

	/**
	 * sets list of points as instances of location
	 * @param locations
	 */
	public void setLocations(List<Location> locations) {
		Assert.notNull(locations);
		this.locations = locations;
	}
	
	/**
	 * adds location. if list of locations is null a empty list is created
	 * @param loc new Location
	 */
	public void addLocation(Location loc) {
		Assert.notNull(loc);
		if(locations==null)
			locations = new ArrayList<Location>();
		locations.add(loc);
	}
	
	/**
	 * list of Locations will be appended to the existing list of locations
	 * @param locations list of Locations
	 */
	public void addAllLocation(List<Location> locations) {
		Assert.notNull(locations);
		if(locations==null)
			setLocations(locations);
		else
			locations.addAll(locations);
	}
}

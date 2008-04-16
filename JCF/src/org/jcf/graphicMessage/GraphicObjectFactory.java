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

import java.util.List;

import org.jcf.JCFException;
import org.springframework.util.Assert;


/**
 * factory for creating graphic objects
 * @author FaKod
 *
 */
public abstract class GraphicObjectFactory {
	
	/**
	 * created object based on type of given go
	 * @param go given GeographicObject
	 * @param nikName nikanme to use
	 * @param room room name to use
	 * @param locs list of locations
	 * @return copy of geographic object with new id
	 */
	public static GraphicObject create(GraphicObject go, String nikName, String room, List<Location> locs) {
		Assert.notNull(go);
		Assert.hasLength(nikName);
		Assert.hasLength(room);
		Assert.notNull(locs);
		
		if(go instanceof Point) {
			return createPoint(nikName,  room, locs.get(0));
		}
		if(go instanceof Line) {
			return createLine(nikName,  room, locs);
		}
		if(go instanceof Polygon) {
			return createPolygon(nikName,  room, locs);
		}
		throw new JCFException("Unsupported GraphicObject");
	}

	/**
	 * creating new PointImpl object with new id and new Location
	 * @param nikName Nikname inside room
	 * @param room name of the room
	 * @param loc Location instance
	 * @return new created PointImpl object
	 */
	public static Point createPoint(String nikName, String room, Location loc) {
		Assert.hasLength(nikName);
		Assert.hasLength(room);
		Assert.notNull(loc);
		
		Id id = IdFactory.getNextId(room, nikName);
		Point p = new PointImpl(id);
		p.addLocation(loc);
		return p;
	}
	
	/**
	 * creating new LineImpl object with new id and a list of locations
	 * @param nikName Nikname inside room
	 * @param room name of the room
	 * @param locs Location instance
	 * @return new created LineImpl object
	 */
	public static Line createLine(String nikName, String room, List<Location> locs) {
		Assert.hasLength(nikName);
		Assert.hasLength(room);
		Assert.notNull(locs);
		
		Id id = IdFactory.getNextId(room, nikName);
		Line l = new LineImpl(id);
		l.addAllLocation(locs);
		return l;
	}
	
	/**
	 * creating new PolygonImpl object with new id and a list of locations
	 * @param nikName Nikname inside room
	 * @param room name of the room
	 * @param locs Location instance
	 * @return new created PolygonImpl object
	 */
	public static Polygon createPolygon(String nikName, String room, List<Location> locs) {
		Assert.hasLength(nikName);
		Assert.hasLength(room);
		Assert.notNull(locs);
		
		Id id = IdFactory.getNextId(room, nikName);
		Polygon p = new PolygonImpl(id);
		p.addAllLocation(locs);
		return p;
	}
}

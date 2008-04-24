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

import java.util.List;
import java.util.Set;

import org.jcf.graphicMessage.GraphicMessage;
import org.jcf.graphicMessage.GraphicObject;
import org.jcf.graphicMessage.GraphicObjectProperty;
import org.jcf.graphicMessage.Id;
import org.jcf.graphicMessage.Location;


/**
 * central interface for handling Graphic Objects
 * @author FaKod
 *
 */
public interface GraphicObjectHandler {

	/**
	 * adding create and delete listener
	 * @param l
	 */
	void addListener(GraphicObjectEventListener l);

	/**
	 * removing the listener
	 * @param l
	 */
	void removeListener(GraphicObjectEventListener l);

	/**
	 * returns Graphical Object of Id id
	 * @param id the Id to search for
	 * @return GraphicalObject
	 */
	GraphicObject getObject(Id id);

	/**
	 * get the list of available Ids
	 * @return list of ids
	 */
	Set<Id> getIds();

	/**
	 * processing GraphicMessage. this is the main method
	 * @param gm instance of the message to process
	 */
	void processGraphicMessage(GraphicMessage gm);

	/**
	 * method starts to create a new instance of a GraphicalMessage. the existing instance is discarded
	 * @return newly created GraphicMessage
	 */
	GraphicMessage createNewGraphicMessage();

	/**
	 * returns the actual instance of the Graphical Message object. it is not discarded here
	 * @return new Instance of GraphicMessage
	 */
	GraphicMessage getGraphicMessage();

	/**
	 * Adding Message for Movement of object with the Id graphicObjectId to the existing Message 
	 * and doing all the necessary operations in the store. 
	 * Notifications are also thrown
	 * @param newLocations new Locations for same object type
	 * @param graphicObjectId id of existing object
	 * @return this instance of GraphicObjectHandler
	 */
	GraphicObjectHandler moveGraphicObject(List<Location> newLocations,
			Id graphicObjectId);

	/**
	 * Adding Message of Deletion of object with the Id graphicObjectId to the existing Message 
	 * and doing all the necessary operations in the store. 
	 * Notifications are also thrown
	 * @param graphicObjectId the Id to delete
	 * @return this instance of GraphicObjectHandler
	 */
	GraphicObjectHandler deleteGraphicObject(Id graphicObjectId);

	/**
	 * Adding Message of creation of a PointImpl to the existing Message
	 * and doing all the necessary operations in the store. 
	 * Notifications are not thrown
	 * @param loc Location of the PointImpl
	 * @return this instance of GraphicObjectHandler
	 */
	GraphicObjectHandler createPointObject(Location loc);
	
	/**
	 * Adding Message of creation of a PointImpl to the existing Message
	 * and doing all the necessary operations in the store. 
	 * Notifications are not thrown
	 * @param loc Location of the PointImpl
	 * @param graphicObjectProperty instance property to attach
	 * @return this instance of GraphicObjectHandler
	 */
	GraphicObjectHandler createPointObject(Location loc, GraphicObjectProperty graphicObjectProperty);

	/**
	 * Adding Message of creation of a LineImpl to the existing Message
	 * and doing all the necessary operations in the store. 
	 * Notifications are not thrown
	 * @param locs Locations of the LineImpl
	 * @return this instance of GraphicObjectHandler
	 */
	GraphicObjectHandler createLineObject(List<Location> locs);
	
	/**
	 * Adding Message of creation of a LineImpl to the existing Message
	 * and doing all the necessary operations in the store. 
	 * Notifications are not thrown
	 * @param locs Locations of the LineImpl
	 * @param graphicObjectProperty instance property to attach
	 * @return this instance of GraphicObjectHandler
	 */
	GraphicObjectHandler createLineObject(List<Location> locs, GraphicObjectProperty graphicObjectProperty);

	/**
	 * Adding Message of creation of a PolygonImpl to the existing Message
	 * and doing all the necessary operations in the store. 
	 * Notifications are not thrown
	 * @param locs Locations of the PolygonImpl
	 * @return this instance of GraphicObjectHandler
	 */
	GraphicObjectHandler createPolygonObject(List<Location> locs);

	/**
	 * Adding Message of creation of a PolygonImpl to the existing Message
	 * and doing all the necessary operations in the store. 
	 * Notifications are not thrown
	 * @param locs Locations of the PolygonImpl
	 * @param graphicObjectProperty instance property to attach
	 * @return this instance of GraphicObjectHandler
	 */
	GraphicObjectHandler createPolygonObject(List<Location> locs, GraphicObjectProperty graphicObjectProperty);

	/**
	 * Adding Message of creation of a TextImpl to the existing Message
	 * and doing all the necessary operations in the store. 
	 * Notifications are not thrown
	 * @param text the text to display
	 * @param loc Locations of the TextImpl
	 * @return this instance of GraphicObjectHandler
	 */
	GraphicObjectHandler createTextObject(String text, Location loc);
	
	/**
	 * Adding Message of creation of a TextImpl to the existing Message
	 * and doing all the necessary operations in the store. 
	 * Notifications are not thrown
	 * @param text the text to display
	 * @param loc Locations of the TextImpl
	 * @param graphicObjectProperty the properies
	 * @return this instance of GraphicObjectHandler
	 */
	GraphicObjectHandler createTextObject(String text, Location loc, GraphicObjectProperty graphicObjectProperty);

}
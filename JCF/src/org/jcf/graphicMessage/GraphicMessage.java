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

/**
 * base Interface Graphic Message
 * @author FaKod
 *
 */
public interface GraphicMessage {

	/**
	 * returns containing list of events
	 * @return list of create or delete events
	 */
	public abstract List<Event> getEvents();

	/**
	 * setting list of create or delete events
	 * @param events
	 */
	public abstract void setEvents(List<Event> events);

	/**
	 * adding one event to the list of event. 
	 * if event is null create empty list
	 * @param evt single event
	 */
	public abstract void addEvent(Event evt);

	/**
	 * adding a list of events to the list of event. 
	 * if event is null create empty list
	 * @param evts list of events
	 */
	public abstract void addAllEvents(List<Event> evts);

	/**
	 * adds new create message
	 * @param p instance of LineImpl
	 * @return the same Graphic Message instance
	 */
	public abstract GraphicMessage addCreateEvent(GraphicObject p);

	/**
	 * adds a delete instance with the given id
	 * @param graphicObject to get the id
	 * @return the same Graphic Message instance
	 */
	public abstract GraphicMessage addDeleteEvent(GraphicObject graphicObject);

	/**
	 * adds a delete instance with the given id
	 * @param id the id
	 * @return the same Graphic Message instance
	 */
	public abstract GraphicMessage addDeleteEvent(Id id);

}
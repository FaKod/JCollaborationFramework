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
 * representation of a Message containing graphic objects
 * @author FaKod
 *
 */
class GraphicMessageImpl implements GraphicMessage, Serializable {
	
	/**
	 * Version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * use factory to create
	 */
	GraphicMessageImpl() {
	}
	
	/*
	 * list of containing events
	 */
	private List<Event> events;

	/* (non-Javadoc)
	 * @see org.jcf.graphicMessage.IGraphicMessage#getEvents()
	 */
	public List<Event> getEvents() {
		return events;
	}

	/* (non-Javadoc)
	 * @see org.jcf.graphicMessage.IGraphicMessage#setEvents(java.util.List)
	 */
	public void setEvents(List<Event> events) {
		Assert.notNull(events);
		this.events = events;
	}
	
	/* (non-Javadoc)
	 * @see org.jcf.graphicMessage.IGraphicMessage#addEvent(org.jcf.graphicMessage.Event)
	 */
	public void addEvent(Event evt) {
		Assert.notNull(evt);
		if(events==null)
			setEvents(new ArrayList<Event>());
		events.add(evt);
		
	}
	
	/* (non-Javadoc)
	 * @see org.jcf.graphicMessage.IGraphicMessage#addAllEvents(java.util.List)
	 */
	public void addAllEvents(List<Event> evts) {
		Assert.notNull(evts);
		if(events==null)
			setEvents(new ArrayList<Event>());
		events.addAll(evts);
	}
	
	/* (non-Javadoc)
	 * @see org.jcf.graphicMessage.IGraphicMessage#addCreateEvent(org.jcf.graphicMessage.GraphicObjectImpl)
	 */
	public GraphicMessage addCreateEvent(GraphicObject go) {
		Assert.notNull(go);
		Event e = new Create(go);
		addEvent(e);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.jcf.graphicMessage.IGraphicMessage#addDeleteEvent(org.jcf.graphicMessage.GraphicObjectImpl)
	 */
	public GraphicMessage addDeleteEvent(GraphicObject graphicObject) {
		Assert.notNull(graphicObject);
		Event e = new Delete(graphicObject.getId());
		addEvent(e);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.jcf.graphicMessage.IGraphicMessage#addDeleteEvent(org.jcf.graphicMessage.Id)
	 */
	public GraphicMessage addDeleteEvent(Id id) {
		Assert.notNull(id);
		Event e = new Delete(id);
		addEvent(e);
		return this;
	}
}

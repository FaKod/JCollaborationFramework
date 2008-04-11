package org.jcf.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jcf.GraphicObjectEventListener;
import org.jcf.GraphicObjectHandler;
import org.jcf.JCFFactory;
import org.jcf.graphicMessage.Event;
import org.jcf.graphicMessage.GraphicMessage;
import org.jcf.graphicMessage.GraphicObject;
import org.jcf.graphicMessage.GraphicObjectFactory;
import org.jcf.graphicMessage.Id;
import org.jcf.graphicMessage.Location;
import org.jcf.graphicMessage.LocationImpl;


public class GraphicObjectHandlerTest extends TestCase {
	
	GraphicObjectHandler goh;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		goh = JCFFactory.newGraphicObjectHandler("nikname", "room");
		goh.addListener(new ListenerTest());
	}
	
	public void testProcessMessage() {
		System.out.println("\ntestProcessMessage()");
		
		/**
		 * create new handler
		 */
		GraphicObjectHandler goh2 = JCFFactory.newGraphicObjectHandler("nikname2", "room2");
		goh2.addListener(new ListenerTest());
		
		GraphicMessage gm = goh.createNewGraphicMessage();
		gm.addCreateEvent(GraphicObjectFactory.createPoint("nikname2", "room2", new LocationImpl(1,1)));
		gm.addCreateEvent(GraphicObjectFactory.createPoint("nikname2", "room2", new LocationImpl(2,1)));
		goh2.processGraphicMessage(gm);	
		assertEquals(2, goh2.getIds().size());
		
		Set<Id> ids = goh2.getIds();
		Id[] ar = ids.toArray(new Id[ids.size()]);
		gm = goh.createNewGraphicMessage();
		gm.addDeleteEvent(ar[0]);
		gm.addDeleteEvent(ar[1]);
		goh2.processGraphicMessage(gm);	
		assertEquals(0, goh2.getIds().size());
	}

	public void testCreateMoveDeleteObject() {
		
		System.out.println("\ntestCreateMoveDeleteObject()");
		
		/**
		 * create objects
		 */
		
		System.out.println("create objects ***********");
		goh.createNewGraphicMessage();
		
		ArrayList<Location> list = new ArrayList<Location>();
		list.add(new LocationImpl(5,1));
		
		goh.createPointObject(new LocationImpl(1,1))
		.createPointObject(new LocationImpl(2,1))
		.createLineObject(list)
		.createPolygonObject(list);
		
		GraphicMessage gm = goh.getGraphicMessage();
		List<Event> events = gm.getEvents();
		Assert.assertEquals(4, events.size());
		
		Set<Id> ids = goh.getIds();
		
		Assert.assertEquals(4, ids.size());
		
		for(Id id: ids) {
			GraphicObject go = goh.getObject(id);
			System.out.println("Object stored: " + go);
		}
		
		/**
		 * Move Objects
		 */
		
		System.out.println("move objects ***********");
		
		goh.createNewGraphicMessage();
		
		Id[] ar = ids.toArray(new Id[ids.size()]);
		
		for(Id id: ar) {
			list = new ArrayList<Location>();
			list.add(new LocationImpl(5,1));
			goh.moveGraphicObject(list, id);
		}
		
		gm = goh.getGraphicMessage();
		events = gm.getEvents();
		Assert.assertEquals(8, events.size());
		
		/**
		 * delete objects
		 */
		
		System.out.println("delete objects ***********");
		
		goh.createNewGraphicMessage();
		
		ar = ids.toArray(new Id[ids.size()]);
		
		for(Id id: ar) {
			goh.deleteGraphicObject(id);
		}
		
		gm = goh.getGraphicMessage();
		events = gm.getEvents();
		Assert.assertEquals(4, events.size());
		
		ids = goh.getIds();
		Assert.assertEquals(0, ids.size());
		
	}
	
	/**
	 * class for listener test
	 * @author christopher schmidt
	 *
	 */
	class ListenerTest implements GraphicObjectEventListener {

		public void createEvent(Id id) {
			System.out.println("Listener: Created Object with Id: " + id);
		}

		public void deleteEvent(Id id) {
			System.out.println("Listener: Deleted Object with Id: " + id);
			
		}
		
	}

}

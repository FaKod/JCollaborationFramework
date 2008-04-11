package org.jcf.graphicMessage;

import java.util.List;

/**
 * main interface for a Graphical Object which supports Locations, Id and GraphicObjectProperties
 * @author FaKod
 *
 */
public interface GraphicObject {

	/**
	 * graphical object id
	 * @return Id
	 */
	public Id getId();

	/**
	 * returns list of object points as instances of Location
	 * @return points as locations
	 */
	public List<Location> getLocations();

	/**
	 * sets list of points as instances of location
	 * @param locations
	 */
	public void setLocations(List<Location> locations);

	/**
	 * adds location. if list of locations is null a empty list is created
	 * @param loc new Location
	 */
	public void addLocation(Location loc);

	/**
	 * list of Locations will be appended to the existing list of locations
	 * @param locations list of Locations
	 */
	public void addAllLocation(List<Location> locations);
	
	/**
	 * sets the custom GraphicObjectProperty
	 * @param graphicObjectProperty
	 */
	public void setGraphicObjectProperty(GraphicObjectProperty graphicObjectProperty);
	
	/**
	 * gets the custom GraphicObjectProperty
	 * @return stored instance of GraphicObjectProperty
	 */
	public GraphicObjectProperty getGraphicObjectProperty();

}
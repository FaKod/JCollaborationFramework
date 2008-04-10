package org.jcf.graphicMessage;

import java.util.List;

public interface GraphicObject {

	/**
	 * graphical object id
	 * @return Id
	 */
	public abstract Id getId();

	/**
	 * returns list of object points as instances of Location
	 * @return points as locations
	 */
	public abstract List<Location> getLocations();

	/**
	 * sets list of points as instances of location
	 * @param locations
	 */
	public abstract void setLocations(List<Location> locations);

	/**
	 * adds location. if list of locations is null a empty list is created
	 * @param loc new Location
	 */
	public abstract void addLocation(Location loc);

	/**
	 * list of Locations will be appended to the existing list of locations
	 * @param locations list of Locations
	 */
	public abstract void addAllLocation(List<Location> locations);

}
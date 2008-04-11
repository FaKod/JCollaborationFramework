package org.jcf;

/**
 * Represents the room information that was discovered using Service Discovery. It's possible to
 * obtain information about a room before joining the room but only for rooms that are public (i.e.
 * rooms that may be discovered).
 *
 * @author FaKod
 */
public interface MUCRoomInfo {

	/**
     * Returns the JID of the room whose information was discovered.
     *
     * @return the JID of the room whose information was discovered.
     */
    public String getRoom();

    /**
     * Returns the discovered description of the room.
     *
     * @return the discovered description of the room.
     */
    public String getDescription();

    /**
     * Returns the discovered subject of the room. The subject may be empty if the room does not
     * have a subject.
     *
     * @return the discovered subject of the room.
     */
    public String getSubject();

    /**
     * Returns the discovered number of occupants that are currently in the room. If this
     * information was not discovered (i.e. the server didn't send it) then a value of -1 will be
     * returned.
     *
     * @return the number of occupants that are currently in the room or -1 if that information was
     * not provided by the server.
     */
    public int getOccupantsCount();

    /**
     * Returns true if the room has restricted the access so that only members may enter the room.
     *
     * @return true if the room has restricted the access so that only members may enter the room.
     */
    public boolean isMembersOnly();

    /**
     * Returns true if the room enabled only participants to speak. Occupants with a role of
     * visitor won't be able to speak in the room.
     *
     * @return true if the room enabled only participants to speak.
     */
    public boolean isModerated();

    /**
     * Returns true if presence packets will include the JID of every occupant.
     *
     * @return true if presence packets will include the JID of every occupant.
     */
    public boolean isNonanonymous();

    /**
     * Returns true if users musy provide a valid password in order to join the room.
     *
     * @return true if users musy provide a valid password in order to join the room.
     */
    public boolean isPasswordProtected();

    /**
     * Returns true if the room will persist after the last occupant have left the room.
     *
     * @return true if the room will persist after the last occupant have left the room.
     */
    public boolean isPersistent();
}

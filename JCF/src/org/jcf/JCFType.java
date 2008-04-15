package org.jcf;

import org.jivesoftware.smack.packet.Presence.Mode;

/**
 * A enum to represent the presecence type. Not that presence type is often confused
 * with presence mode. Generally, if a user is signed into a server, they have a presence
 * type of {@link #available available}, even if the mode is {@link Mode#away away},
 * {@link Mode#dnd dnd}, etc. The presence type is only {@link #unavailable unavailable} when
 * the user is signing out of the server.
 */
public enum JCFType {
	/**
     * The user is available to receive messages (default).
     */
     available,

     /**
      * The user is unavailable to receive messages.
      */
     unavailable,

     /**
      * Request subscription to recipient's presence.
      */
     subscribe,

     /**
      * Grant subscription to sender's presence.
      */
     subscribed,

     /**
      * Request removal of subscription to sender's presence.
      */
     unsubscribe,

     /**
      * Grant removal of subscription to sender's presence.
      */
     unsubscribed,

     /**
      * The presence packet contains an error message.
      */
     error
}

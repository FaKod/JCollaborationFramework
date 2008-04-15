package org.jcf;

/**
 * An enum to represent the presence mode.
 */
public enum JCFMode {
	/**
     * Free to chat.
     */
    chat,

    /**
     * Available (the default).
     */
    available,

    /**
     * Away.
     */
    away,

    /**
     * Away for an extended period of time.
     */
    xa,

    /**
     * Do not disturb.
     */
    dnd
}

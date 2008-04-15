package org.jcf;

/**
 * An enumeration for the subscription mode options.
 */
public enum JCFSubscriptionMode {
	/**
     * Automatically accept all subscription and unsubscription requests. This is
     * the default mode and is suitable for simple client. More complex client will
     * likely wish to handle subscription requests manually.
     */
    accept_all,

    /**
     * Automatically reject all subscription requests.
     */
    reject_all,

    /**
     * Subscription requests are ignored, which means they must be manually
     * processed by registering a listener for presence packets and then looking
     * for any presence requests that have the type Presence.Type.SUBSCRIBE or
     * Presence.Type.UNSUBSCRIBE.
     */
    manual
}

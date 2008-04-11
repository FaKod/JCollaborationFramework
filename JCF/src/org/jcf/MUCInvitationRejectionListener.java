package org.jcf;

/**
 * A listener that is fired anytime an invitee declines or rejects an invitation.
 * 
 * @author FaKod
 */
public interface MUCInvitationRejectionListener {

	/**
     * Called when the invitee declines the invitation.
     * 
     * @param invitee the invitee that declined the invitation. (e.g. hecate@shakespeare.lit).
     * @param reason the reason why the invitee declined the invitation.
     */
    public abstract void invitationDeclined(String invitee, String reason);
    
}

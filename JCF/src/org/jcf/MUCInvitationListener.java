package org.jcf;


/**
 * A listener that is fired anytime an invitation to join a MUC room is received.
 * 
 * @author FaKod
 */
public interface MUCInvitationListener {

	/**
	 * Called when the an invitation to join a MUC room is received.<p>
     * 
     * If the room is password-protected, the invitee will receive a password to use to join
     * the room. If the room is members-only, the the invitee may be added to the member list.
     * 
	 * @param room the room that invitation refers to.
	 * @param inviter the inviter that sent the invitation. (e.g. crone1@shakespeare.lit).
	 * @param reason the reason why the inviter sent the invitation.
	 * @param password the password to use when joining the room.
	 * @param message the message used by the inviter to send the invitation.
	 */
	public void invitationReceived(String room, String inviter, String reason, String password, String message);
}

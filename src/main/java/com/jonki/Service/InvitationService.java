package com.jonki.Service;

import com.jonki.Entity.Invitation;

import java.util.List;

public interface InvitationService {

    public void addInvitation(final Long fromID, final Long toID);
    public void deleteInvitation(final Long invitationID);
    public Long findInvitation(final Long fromID, final Long toID);
    public List<Invitation> findSentInvitations(final Long ID);
    public List<Invitation> findReceivedInvitations(final Long ID);
}

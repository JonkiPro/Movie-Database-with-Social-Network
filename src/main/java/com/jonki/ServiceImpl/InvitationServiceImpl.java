package com.jonki.ServiceImpl;

import com.jonki.DAO.InvitationCRUDRepository;
import com.jonki.Entity.Invitation;
import com.jonki.Service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("invitationService")
public class InvitationServiceImpl implements InvitationService {

    private InvitationCRUDRepository invitationCRUDRepository;

    @Autowired
    public InvitationServiceImpl(InvitationCRUDRepository invitationCRUDRepository) {
        this.invitationCRUDRepository = invitationCRUDRepository;
    }

    @Override
    public void addInvitation(final Long fromID, final Long toID) {
        invitationCRUDRepository.save(new Invitation(fromID, toID));
    }

    @Override
    public void deleteInvitation(final Long invitationID) {
        invitationCRUDRepository.delete(invitationID);
    }

    @Override
    public Long findInvitation(final Long fromID, final Long toID) {
        Invitation invitation = invitationCRUDRepository.findOneByFromIDAndToID(fromID, toID);

        return invitation.getId();
    }

    @Override
    public List<Invitation> findSentInvitations(final Long ID) {
        return invitationCRUDRepository.findByFromID(ID);
    }

    @Override
    public List<Invitation> findReceivedInvitations(final Long ID) {
        return invitationCRUDRepository.findByToID(ID);
    }
}

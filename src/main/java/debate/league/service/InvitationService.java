package debate.league.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import debate.league.repositories.InvitationRepository;
import debate.league.classes.Invitation;
import lombok.NonNull;

import java.util.stream.Collectors;

@Service
public class InvitationService {

    @NonNull
    private final InvitationRepository invitationRepository;

    @Autowired
    InvitationService(InvitationRepository invitationRepository){
        this.invitationRepository = invitationRepository;
    }

    public List<Invitation> getAllInvitations(Long userId ){
        return invitationRepository.findAll().stream().filter(invitation -> invitation.getRecipientUser().getUserId() == userId).collect(Collectors.toList());
    }

    public Optional<Invitation> getInvitationById(Long invitationId){
        return invitationRepository.findById(invitationId);
    }    

    public void saveInvitation(Invitation invitation){
        invitationRepository.saveAndFlush(invitation);
    }

    public void deleteInvitation(Long invitationId){
        invitationRepository.deleteById(invitationId);
    }


}


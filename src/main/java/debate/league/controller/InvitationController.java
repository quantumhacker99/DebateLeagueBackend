package debate.league.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import debate.league.classes.Post;
import debate.league.classes.*;
import debate.league.repositories.PostRepository;
import debate.league.service.InvitationService;
import debate.league.service.PostService;
import debate.league.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@RestController
@CrossOrigin("http://localhost:3000")

public class InvitationController {


    private PostService postService;
    private UserService userService;
    private InvitationService invitationService;

    public InvitationController(UserService userService, PostService postService, InvitationService invitationService){
        this.userService = userService;
        this.postService = postService;
        this.invitationService = invitationService;
        
    }
    
    @RequestMapping(value="/inviteUser/{recipientId}", method=RequestMethod.POST)
    @CrossOrigin
    public @ResponseBody SendConfirmationInviteDTO sendInviteToInbox(@RequestBody SendInviteDTO invite, @PathVariable String recipientId){
        
        SendConfirmationInviteDTO response = new SendConfirmationInviteDTO();
        
        Long receivedFrom = Long.parseLong(invite.getSendingId());
        Long postId = Long.parseLong(invite.getPostId());
        //Long recipient = Long.parseLong(recipientId);
    
        Optional<User> sendingUser = this.userService.getUserById(receivedFrom);
        Optional<User> recipientUser = this.userService.getUserById(Long.parseLong(recipientId));

        if(sendingUser.isEmpty()){
            response.setSuccess(false);
        }
        else{
            Invitation nwInvite = new Invitation();
            nwInvite.setBody(invite.getBody());
            nwInvite.setTopic(invite.getTopic());
            nwInvite.setReceivedFrom(receivedFrom);

            nwInvite.setRecipientId(recipientUser.get().getUserId());
            nwInvite.setRecipientUser(recipientUser.get());

            nwInvite.setPostId(postId);

            invitationService.saveInvitation(nwInvite);

            response.setSuccess(true);

        }
        return response;
    }

    @RequestMapping(value="/inbox/{recipientId}", method=RequestMethod.GET)
    @CrossOrigin
    public @ResponseBody List<LocalInviteDTO> getInvites(@PathVariable String recipientId){
        List<LocalInviteDTO> inviteDTOs = new ArrayList<LocalInviteDTO>();
        
        Long receiverID = Long.parseLong(recipientId);
        List<Invitation> invites = this.invitationService.getAllInvitations(receiverID);

        for(Invitation invite: invites){

            LocalInviteDTO nwInvite = new LocalInviteDTO();
            nwInvite.setNull(false);
            nwInvite.setBody(invite.getBody());
            nwInvite.setInvitationID(invite.getInvitationID());
            nwInvite.setReceivedFrom(invite.getReceivedFrom());
            nwInvite.setRecipientId(invite.getRecipientId());
            nwInvite.setPostId(invite.getPostId());
            nwInvite.setTopic(invite.getTopic());

            inviteDTOs.add(nwInvite);
        }

        return inviteDTOs;
    }

    @RequestMapping(value="/invite/{invitationId}", method=RequestMethod.GET)
    @CrossOrigin
    public @ResponseBody LocalInviteDTO getInvite(@PathVariable String invitationId){
        
        LocalInviteDTO nwInvite = new LocalInviteDTO();
        Long reqInviteId = Long.parseLong(invitationId);
        Optional<Invitation> invite = this.invitationService.getInvitationById(reqInviteId);

        if(invite.isEmpty()){
            nwInvite.setNull(true);
            System.out.println(nwInvite.getNull());
        }

        else {
            nwInvite.setNull(false);
            nwInvite.setBody(invite.get().getBody());
            nwInvite.setInvitationID(reqInviteId);
            nwInvite.setReceivedFrom(invite.get().getReceivedFrom());
            nwInvite.setRecipientId(invite.get().getRecipientId());
            nwInvite.setTopic(invite.get().getTopic());
            nwInvite.setPostId(invite.get().getPostId());

            System.out.println("GET Request made " + nwInvite.getInvitationID() + " " + nwInvite.getBody() + " " +  
                                nwInvite.getReceivedFrom() + " " + nwInvite.getRecipientId() + " " +
                                nwInvite.getTopic() + nwInvite.getNull());
        }


        return nwInvite;
    }

    @RequestMapping(value="/confirmInvite/{invitationId}", method=RequestMethod.POST)
    @CrossOrigin
    public @ResponseBody SendConfirmationInviteDTO confirmInvite(@RequestBody ApprovedInviteDTO response, @PathVariable String invitationId){
        
        SendConfirmationInviteDTO confirm = new SendConfirmationInviteDTO();
        Long inviteId = Long.parseLong(invitationId);
        Invitation invite = this.invitationService.getInvitationById(inviteId).get();
        Long postId = invite.getPostId();

        System.out.println("Approved response " + response);

        try{
            if(response.getApproved().equalsIgnoreCase("true")){
                Post reqPost = this.postService.getPostById(postId).get();
                reqPost.setReplyUserId(invite.getRecipientId());
                this.postService.savePost(reqPost);
                confirm.setSuccess(true);
                System.out.println("Invite status is true, what was sent to us was invitationID " + " " + "and response " + response);
            }
            else{
                confirm.setSuccess(false);
                System.out.println("Invite status is false, what was sent to us was invitationID " + " " + "and response " + response);
            }
        }
        catch(NullPointerException e){
            System.out.println("Response data is null");
        }

        return confirm;
    }

}


@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class SendInviteDTO{
    private String topic;
    private String body;
    private String sendingId;
    private String postId;
}

@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class LocalInviteDTO{
    private boolean isNull;
    public boolean getNull() {
        return isNull;
    }
    private Long invitationID;
    private String topic;
    private String body;
    private Long recipientId;
    private Long receivedFrom;
    private Long postId;
}

@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class SendConfirmationInviteDTO{
    private boolean success;
}

@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
class ApprovedInviteDTO{
    private String approved;
}
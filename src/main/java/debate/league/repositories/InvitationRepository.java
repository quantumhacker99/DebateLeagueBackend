package debate.league.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import debate.league.classes.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long>{
    
}


package debate.league.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import debate.league.classes.LoginDetails;

@Repository
public interface LoginDetailsRepository extends JpaRepository<LoginDetails, String>{
    
}
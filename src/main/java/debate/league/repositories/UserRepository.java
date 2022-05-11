package debate.league.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import debate.league.classes.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}

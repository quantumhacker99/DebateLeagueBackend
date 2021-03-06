package debate.league.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import debate.league.classes.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
}


package fit.se.www_lab_w6.repositories;

import fit.se.www_lab_w6.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByPublishedTrue();
    Optional<Post> findByIdAndPublishedTrue(Long id);

    List<Post>findAllByPublishedTrueAndAuthorId(Long id);
}

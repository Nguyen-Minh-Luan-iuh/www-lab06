package fit.se.www_lab_w6.repositories;

import fit.se.www_lab_w6.models.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
        List<PostComment> findPostCommentsByPostId(long id);
}

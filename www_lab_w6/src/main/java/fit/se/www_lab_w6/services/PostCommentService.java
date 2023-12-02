package fit.se.www_lab_w6.services;

import fit.se.www_lab_w6.models.PostComment;
import fit.se.www_lab_w6.repositories.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;

    public void save(PostComment comment){
        postCommentRepository.save(comment);
    }

    public List<PostComment> findPostCommentsByPostId(long id){
        return postCommentRepository.findPostCommentsByPostId(id);
    }
}

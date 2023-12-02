package fit.se.www_lab_w6.services;

import fit.se.www_lab_w6.models.Post;
import fit.se.www_lab_w6.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void save(Post post){ postRepository.save(post); }

    public List<Post> getAllPublish(){
        return postRepository.findAllByPublishedTrue();
    }

    public Optional<Post> findByIdAndPublishTrue(Long id){
        return postRepository.findByIdAndPublishedTrue(id);
    }

    public List<Post>findByAuthorId(long id){
        return postRepository.findAllByPublishedTrueAndAuthorId(id);
    }
}

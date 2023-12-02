package fit.se.www_lab_w6.controllers;

import fit.se.www_lab_w6.models.Post;
import fit.se.www_lab_w6.models.PostComment;
import fit.se.www_lab_w6.models.User;
import fit.se.www_lab_w6.services.PostCommentService;
import fit.se.www_lab_w6.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostCommentService postCommentService;

    @GetMapping("/create-post")
    public String getCreatePostForm(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null || user.getId() == 0){
            return "redirect:/login";
        }

        Post post = new Post();
        model.addAttribute("user", user);
        model.addAttribute("post", post);

        return "post/post_form";
    }

    @PostMapping("/create-post")
    public String createPost(@ModelAttribute("post") Post post, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user.getId() == 0){
            return "redirect:/login";
        }
        post.setAuthor(user);

        postService.save(post);

        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable("id")long id, Model model, HttpSession session){
        Optional<Post> result = postService.findByIdAndPublishTrue(id);
        if(result.isEmpty()){
            return "redirect:/";
        }
        User user = (User) session.getAttribute("user");
        List<PostComment> commentList =  postCommentService.findPostCommentsByPostId(id);
        Set<PostComment> comments = new HashSet<>(commentList);
        Post post = result.get();
        post.setPostComments(comments);
        PostComment postComment = new PostComment();
        postComment.setPost(post);
        postComment.setUser(user);
        model.addAttribute("comment", postComment);
        model.addAttribute("post", post);
        model.addAttribute("user", user);
        return "post/post_detail";
    }

    @GetMapping("/repost/{id}")
    public String getRepostPage(@PathVariable("id") long id, Model model, HttpSession session){
        Optional<Post> result = postService.findByIdAndPublishTrue(id);

        if(result.isEmpty()){
            return "redirect:/";
        }

        Post post = new Post();

        User user = (User) session.getAttribute("user");
        post.setPost(result.get());
        post.setAuthor(user);
        model.addAttribute("parent", result.get());
        model.addAttribute("user", user);
        model.addAttribute("post", post);

        return "post/repost_form";
    }

    @PostMapping("/repost")
    public String repost(@ModelAttribute("post")Post post, @RequestParam("parent")String parent){
        post.setPost(new Post(Long.parseLong(parent)));
        post.setUpdatedAt(Instant.now());
        post.setCreatedAt(Instant.now());
        post.setPublishedAt(Instant.now());
        postService.save(post);

        return "redirect:/";
    }
}

package fit.se.www_lab_w6.controllers;

import fit.se.www_lab_w6.models.Post;
import fit.se.www_lab_w6.models.User;
import fit.se.www_lab_w6.services.PostService;
import fit.se.www_lab_w6.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping("/login")
    public String showLoginForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user/login_form";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user/register_form";
    }

    @PostMapping("/register/save")
    public String register(@ModelAttribute("user")User user, HttpSession session, Model model){
        String errorMessage = userService.register(user);
        if(errorMessage.isEmpty()){
            session.setAttribute("user", user);
            userService.updateLoginTime(user);
            return "redirect:/";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "user/register_form";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user")User user, HttpSession session, Model model){
        Optional<User> result = userService.login(user);
        if(result.isEmpty()){
            model.addAttribute("errorMessage", "Bad credentials");

            return "user/login_form";
        }

        session.setAttribute("user", result.get());
        userService.updateLoginTime(result.get());
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    public String getUserDetail(@PathVariable("id")long id, Model model, HttpSession session){
        Optional<User> result = userService.findById(id);
        if(result.isEmpty()){
            return "redirect:/";
        }

        User currentUser = (User) session.getAttribute("user");
        List<Post> posts = postService.findByAuthorId(id);
        model.addAttribute("userInfo", result.get());
        model.addAttribute("posts", posts);
        model.addAttribute("user", currentUser);
        return "user/user_info";
    }

    @GetMapping("/user/update/{id}")
    public String getUserUpdateForm(@PathVariable("id")long id, Model model, HttpSession session){
        Optional<User> result = userService.findById(id);
        if(result.isEmpty()){
            return "redirect:/";
        }
        model.addAttribute("user", result.get());
        return "user/update_form";
    }

    @PostMapping("/user/save")
    public String saveUser(@ModelAttribute("user")User user){
        userService.update(user);
        return "redirect:/";
    }
}

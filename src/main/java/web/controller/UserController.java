package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

@Controller
public class UserController {

    private UserServiceImpl userServiseImpl;

    @Autowired
    public UserController(UserServiceImpl userServiseImpl) {
        this.userServiseImpl = userServiseImpl;
    }


    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("userList", userServiseImpl.getListUsers());
        return "users";
    }

    @GetMapping("/new")
    public String getNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        userServiseImpl.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/showUser")
    public String getUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("userList", userServiseImpl.getUser(id));
        return "show";
    }

    @PostMapping("/showUser")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "id", required = false) Integer id) {
        userServiseImpl.updateUser(id, user);
        return "redirect:/";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id", required = false) Integer id) {
        userServiseImpl.deleteUser(id);
        return "redirect:/";
    }

}

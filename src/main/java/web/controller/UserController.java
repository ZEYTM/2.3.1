package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.config.DAO.UserDAO;
import web.model.User;

@Controller
public class UserController {

    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String showUsers(Model model) {
        System.out.println(" show user");
        model.addAttribute("userList", userDAO.getListUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        System.out.println("new user");
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        System.out.println("create user");
        userDAO.saveUser(user);
        return "redirect:/";

    }

    @GetMapping("/showUser")
    public String showUser(@RequestParam(value = "id", required = false) int id, Model model) {
        model.addAttribute("userList", userDAO.showUser(id));
        return "show";
    }

}

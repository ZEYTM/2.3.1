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
        model.addAttribute("userList", userDAO.getListUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        userDAO.saveUser(user);
        return "redirect:/";
    }



    @PostMapping("/showUser")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam (value = "id",required = false) Integer id) {
        userDAO.update(id,user);
        return "redirect:/";

    }
    @GetMapping("/showUser")
    public String showUser(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("userList", userDAO.showUser(id));
        return "show";
    }

}

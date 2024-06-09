package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.config.DAO.UserDAO;
import web.model.User;

@Controller
public class UserController {

    private UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/users")
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
        return "redirect:/users";

    }
//@PostMapping("/new")
//public String createUser(@RequestParam(value = "name", required = false) String name , @RequestParam(value = "age", required = false) int age) {
//    System.out.println("create user");
//    User user = new User();
//    user.setName(name);
//    user.setAge(age);
//    userDAO.saveUser(user);
//    return "redirect:/users";
//}

}

package kata.controller;

import kata.model.User;
import kata.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import kata.model.User;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String allUsers(Model model) {

        model.addAttribute(
                "users",
                userService.getAllUsers());

        model.addAttribute(
                "user",
                new User());

        return "users";
    }

    @PostMapping("/add")
    public String addUser(
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "users",
                    userService.getAllUsers());

            return "users";
        }

        userService.saveUser(user);

        return "redirect:/users";
    }
    @PostMapping("/delete")
    public String deleteUser(
            @RequestParam("id") Long id
    ) {

        userService.deleteUser(id);

        return "redirect:/users";
    }
    @GetMapping("/edit")
    public String editPage(
            @RequestParam("id") Long id,
            Model model
    ) {

        model.addAttribute(
                "user",
                userService.getUserById(id));

        return "edit";
    }
    @PostMapping("/update")
    public String updateUser(
            @Valid User user,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "edit";
        }

        userService.updateUser(user);

        return "redirect:/users";
    }
}
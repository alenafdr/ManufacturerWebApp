package manufacturerwebapp.controller;

import manufacturerwebapp.model.User;
import manufacturerwebapp.service.SecurityService;
import manufacturerwebapp.service.UserService;
import manufacturerwebapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.list());
        for (User user : userService.list()){
            System.out.println("!!!!!!!!!!!!!" + user);
        }
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.list());

        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user")User user){

        if(user.getId() == null){
            this.userService.save(user);
        }else {
            this.userService.update(user);
        }
        return "redirect:/users";
    }

    @RequestMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") UUID id, Model model){
        User user = userService.getById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        model.addAttribute("users", userService.list());
        return "users";
    }

    @RequestMapping("/users/remove/{id}")
    public String removeUser(@PathVariable("id") UUID id){
        userService.remove(id);
        return "redirect:/users";
    }






    /*@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getPassword());

        return "redirect:/welcome";
    }*/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

}
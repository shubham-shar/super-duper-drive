package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.User;
import com.udacity.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

/**
 * @author Shubham Sharma
 * @date 8/4/20
 */
@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String register(@ModelAttribute("SpringWeb") User user, Model model) {

        if (Objects.isNull(user)) {
            model.addAttribute("nullUser",true);
            return "signup.html";
        }

        if(userService.isUserRegistered(user.getUsername())){
            model.addAttribute("isRegistered",true);
            return "signup.html";
        }

        try {
            userService.register(user);
            model.addAttribute("registered",true);
            return "login.html";
        } catch (Exception e) {
            model.addAttribute("error",true);
            return "signup.html";
        }
    }
}

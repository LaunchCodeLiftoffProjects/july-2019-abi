package org.launchcode.liftoff.controllers;

import org.launchcode.liftoff.models.User;
import org.launchcode.liftoff.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Register");
        model.addAttribute("user", userDao.findAll());
        return "user/register";
    }

    @RequestMapping(value="register", method = RequestMethod.POST)
    public String register(Model model, @Valid @ModelAttribute User user, Errors errors, String verify) {
        List<User> sameUser = userDao.findByUsername(user.getUsername());

        if (!errors.hasErrors() && user.getPassword().equals(verify) && sameUser.isEmpty()) {
            model.addAttribute("user", user);
            model.addAttribute("users", userDao.findAll());
            userDao.save(user);
            return "user/index";

        } else {
            model.addAttribute("user", user);
            model.addAttribute("title", "Register");
            if (!user.getPassword().equals(verify)) {
                model.addAttribute("message", "Passwords do not match");
                user.setPassword("");
            }

            if (!sameUser.isEmpty()) {
                model.addAttribute("message", "username already exists");
            }

            return "user/register";
        }
    }

    @RequestMapping(value="login")
    public String loginForm(Model model) {
        model.addAttribute("title", "login");
        model.addAttribute(new User());
        return "user/login";
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String login(Model model, @ModelAttribute User user, HttpServletResponse response) {
        List<User> x = userDao.findByUsername(user.getUsername());
        if (x.isEmpty()) {
            model.addAttribute("message", "Username not found");
            model.addAttribute("title", "login");
            return "user/login";
        }

        User loggedIn = x.get(0);
        if (loggedIn.getPassword().equals(user.getPassword())){
            Cookie a = new Cookie("user", user.getUsername());
            a.setPath("/");
            response.addCookie(a);
            model.addAttribute("user", user);
            model.addAttribute("users", userDao.findAll());
            return "user/index";

        } else {
            model.addAttribute("message", "incorrect password");
            model.addAttribute("title", "login");
            return "user/login";
        }
    }

    @RequestMapping(value="logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie a : cookies) {
                a.setMaxAge(0);
                a.setPath("/");
                response.addCookie(a);
            }
        }

        return "redirect:login";
    }
}


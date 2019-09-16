package org.launchcode.liftoff.controllers;

import org.launchcode.liftoff.model.Player;
import org.launchcode.liftoff.model.data.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    public PlayerDao playerDao;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("players", playerDao.findAll());
        model.addAttribute("title", "Available Players");
        return"player/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddPlayerForm(Model model){
        model.addAttribute("title", "Make Player");
        model.addAttribute(new Player());

        return"players/add";

    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processPlayerForm(@ModelAttribute @Valid Player newPlayer, Errors
                                    errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Make Player");
            return"players/add";
        }

        playerDao.save(newPlayer);
        return "redirect: ";
    }

}

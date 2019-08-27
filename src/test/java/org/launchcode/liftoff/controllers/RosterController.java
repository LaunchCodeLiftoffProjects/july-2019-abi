package org.launchcode.liftoff.controllers;

import org.launchcode.liftoff.forms.AddRosterPlayerForm;
import org.launchcode.liftoff.model.Player;
import org.launchcode.liftoff.model.data.PlayerDao;
import org.launchcode.liftoff.models.Roster;
import org.launchcode.liftoff.models.data.RosterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("roster")
public class RosterController {
    @Autowired
    public RosterDao rosterDao;

    @Autowired
    public PlayerDao playerDao;


    @RequestMapping(value = " ")
    public String index(Model model) {

        model.addAttribute("title", "rosters");
        model.addAttribute("rosters", rosterDao.findAll());
        return "roster/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Roster");
        model.addAttribute(new Roster());
        return "roster/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Roster roster, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Roster");
            return "roster/add";
        }

        rosterDao.save(roster);
        return "redirect:view/" + roster.getId();
    }

    @RequestMapping(value= "view/{id}", method = RequestMethod.GET)
    public String viewRoster(Model model, @PathVariable int id) {

        Roster roster = rosterDao.findOne(id);

        model.addAttribute("title", roster.getName());
        model.addAttribute("roster", roster);
        return "roster/view";
    }

    @RequestMapping(value="add-player/{rosterId}", method = RequestMethod.GET)
    public String addPlayer(Model model, @PathVariable int rosterId) {

        Roster roster = rosterDao.findOne(rosterId);

        AddRosterPlayerForm form = new AddRosterPlayerForm(playerDao.findAll(), roster);

        model.addAttribute("title", "Add Roster Player:" + roster.getName());
        model.addAttribute("form", form);
        return "roster/add-player";
    }

    @RequestMapping(value="add-item/{rosterId}", method = RequestMethod.POST)
    public String addPlayer(Model model, @ModelAttribute @Valid AddRosterPlayerForm playerForm, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("Title", "Add Player");
            return "roster/add-player/";
        }

        Roster roster = rosterDao.findOne(playerForm.getRosterId());
        Player player = playerDao.findOne(playerForm.getPlayerId());

        roster.addPlayer(player);
        rosterDao.save(roster);

        return "redirect:/roster/view/" + roster.getId();
    }

}


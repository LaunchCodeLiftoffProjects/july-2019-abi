package org.launchcode.liftoff.controllers;

import org.launchcode.liftoff.models.data.TeamDao;
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
@RequestMapping("team")
public class TeamController {
    @Autowired
    private TeamDao teamDao;

    @Autowired
    private PlayerDao playerDao;


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "teams");
        model.addAttribute("teams", TeamDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Team");
        model.addAttribute(new Team());
        return "team/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Team team, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Team");
            return "team/add";
        }

        TeamDao.save(team);
        return "redirect:view/" + team.getId();
    }

    @RequestMapping(value= "view/{id}", method = RequestMethod.GET)
    public String viewTeam(Model model, @PathVariable int id) {

        Team team = TeamDao.findOne(id);

        model.addAttribute("title", team.getName());
        model.addAttribute("team", team);
        return "team/view";
    }

    @RequestMapping(value="add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int menuId) {

        Team team = teamDao.findOne(teamId);

        AddTeamPlayerForm form = new AddTeamPlayerForm(PlayerDao.findAll(), team);

        model.addAttribute("title", "Add Team Player:" + team.getName());
        model.addAttribute("form", form);
        return "team/add-player";
    }

    @RequestMapping(value="add-item/{teamId}", method = RequestMethod.POST)
    public String addPlayer(Model model, @ModelAttribute @Valid AddTeamPlalyerForm itemForm, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("Title", "Add Item");
            return "team/add-player/";
        }

        Team team = teamDao.findOne(itemForm.getTeamId());
        Player player = playerDao.findOne(itemForm.getPlayerId());

        team.addPlayer(player);
        playerDao.save(player);

        return "redirect:/team/view/" + team.getId();
    }

}


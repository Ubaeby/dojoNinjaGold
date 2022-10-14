package com.codingdojo.ninja.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NinjaGold {

	Random r = new Random();

	Date newDate = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd YYYY hh:mm aaa");
	String finalDate = sdf.format(new Date());

	@RequestMapping("/")
	public String index(HttpSession session) {
		
		ArrayList<String> activities = new ArrayList<String>();
		
		session.setAttribute("array", activities);
		
		return "redirect:/gold";
	}

	@RequestMapping("/gold")
	public String home(HttpSession session, Model model) {
		if (session.getAttribute("gold") == null) {
			session.setAttribute("gold", 0);
		}
		
		ArrayList<String> activities = (ArrayList<String>) session.getAttribute("array");
		model.addAttribute("loopMe", activities);
		return "index.jsp";
	}
	
	@RequestMapping("/jail")
	public String jail() {
		return "jail.jsp";
	}
	
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
		session.setAttribute("gold", 0);
		session.setAttribute("array", new ArrayList<String>());
		return "redirect:/gold";
	}
	

	@RequestMapping(value = "/normal", method = RequestMethod.POST)
	public String farm(RedirectAttributes redirectAttributes, HttpSession session, Model model,
			@RequestParam(value = "farm", required = false) String farm,
			@RequestParam(value = "cave", required = false) String cave,
			@RequestParam(value = "house", required = false) String house,
			@RequestParam(value = "quest", required = false) String quest,
			@RequestParam(value="spa", required = false) String spa) {

		Integer displayGold = (Integer) session.getAttribute("gold");

		session.setAttribute("gold", displayGold);
		
		
		ArrayList<String> activities = (ArrayList<String>) session.getAttribute("array");
		
//		if (displayGold < 0) {	
//			return "redirect:/jail";
//		}
		

		if (farm != null) {
			int randFarm = r.nextInt((20 - 10) + 1) + 10;
			Integer farmGold = (Integer) session.getAttribute("gold");
			farmGold += randFarm;
			session.setAttribute("gold", farmGold);
			redirectAttributes.addFlashAttribute("normal",
					"You entered a farm and earned " + randFarm + " gold! " + finalDate);
			
			activities.add("You entered a farm and earned " + randFarm + " gold! " + finalDate);
		}

		if (cave != null) {
			int randCave = r.nextInt((10 - 5) + 1) + 5;
			Integer farmCave = (Integer) session.getAttribute("gold");
			farmCave += randCave;
			session.setAttribute("gold", farmCave);
			redirectAttributes.addFlashAttribute("normal",
					"You entered a cave and earned " + randCave + " gold! " + finalDate);
			
			activities.add("You entered a cave and earned " + randCave + " gold! " + finalDate);
		}

		if (house != null) {
			int randHouse = r.nextInt((5 - 2) + 1) + 2;
			Integer farmHouse = (Integer) session.getAttribute("gold");
			farmHouse += randHouse;

			session.setAttribute("gold", farmHouse);
			redirectAttributes.addFlashAttribute("normal",
					"You entered a house and earned " + randHouse + " gold! " + finalDate);
			
			activities.add("You entered a house and earned " + randHouse + " gold! " + finalDate);
		}
		


		if (quest != null) {
			int headsTails = r.nextInt(2);
			int randQuest = r.nextInt(51);
			Integer farmQuest = (Integer) session.getAttribute("gold");
			if (headsTails == 0) {
				farmQuest += randQuest;
				redirectAttributes.addFlashAttribute("normal",
						"You completed a quest and earned " + randQuest + " gold! " + finalDate);
				
				activities.add("You completed a quest and earned " + randQuest + " gold! " + finalDate);
			}

			else if (headsTails == 1) {
				farmQuest -= randQuest;
				redirectAttributes.addFlashAttribute("normal", "You failed a quest and you ended up paying " + randQuest
						+ " gold for all the damage you stirred in the village. Ouch " + finalDate);
				
				activities.add("You failed a quest and you ended up paying " + randQuest
						+ " gold for all the damage you stirred in the village. Ouch " + finalDate);
				if (displayGold < 0) { //This is basically stating that if you are negative, and go negative again then you'll go to jail.
					return "redirect:/jail";
				}
			}

			session.setAttribute("gold", farmQuest);

		}
		
		if (spa != null) {
			int randSpa = r.nextInt((10 - 5) + 1) + 5;
			Integer farmSpa = (Integer) session.getAttribute("gold");

				farmSpa -= randSpa;
				
				//This is the flash activity
				redirectAttributes.addFlashAttribute("normal", "You enjoyed the spa and you ended up paying " + randSpa
						+ " gold." + finalDate);
				
				//This is the arrays activity to store statements
				activities.add("You enjoyed the spa and you ended up paying " + randSpa
						+ " gold." + finalDate);
				
				if (displayGold < 0) { 
					return "redirect:/jail";
				}
				
				session.setAttribute("gold", farmSpa);
			}
		

		session.setAttribute("array", activities);

		return "redirect:/gold";
	}
	

}
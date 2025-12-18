package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.setec.entities.Booked;
import com.setec.repos.BookedRepos;
import com.setec.service.MyTelegramBot;

@Controller
public class MyController {
	//http://localhost:8080/
	@GetMapping({"/","/home"})
	
	public String home(Model mod) {
		Booked booked=new Booked(
				1, "Sry khuon",
				"01283464",
				"nang123@gmail.com",
				"11/17/2025",
				"5:17PM",
				5
				);
		mod.addAttribute("booked" , booked);
		return "index";
	}
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/service")
	public String service() {
		return "service";
	}
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	@GetMapping("/reservation")
	public String reservation(Model mod) {
		Booked booked=new Booked(
				1, "Sry khuon",
				"01283464",
				"nang123@gmail.com",
				"11/17/2025",
				"5:17PM",
				5
				);
		
		mod.addAttribute("booked" , booked);
		return "reservation";
	}
	@GetMapping("/testimonial")
	public String testimonial() {
		return "testimonial";
	}
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	@Autowired
	private BookedRepos bookesRepos;
	
	@Autowired
	private MyTelegramBot bot;
	
	
	
	@PostMapping("/success")
	public String success(@ModelAttribute Booked booked, Model model) {
		bookesRepos.save(booked);
		 String message =
		            "🆔 ID: " + booked.getId() + "\n" +
		            "👤 Name: " + booked.getName() + "\n" +
		            "📞 Phone: " + booked.getPhoneNumber() + "\n" +
		            "📧 Email: " + booked.getEmail() + "\n\n" +
		            "📅 Date: " + booked.getDate() + "\n" +
		            "⏰ Time: " + booked.getTime() + "\n" +
		            "👥 Person: " + booked.getPerson();

		    bot.sendMessage(message);
	    return "success";
	}


}

package com.project.fullstack1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.fullstack1.blueprint.Administrator;
import com.project.fullstack1.blueprint.Zomato;

import com.project.fullstack1.server.ZomatoService;


@Controller
public class ZomatoController {
private ZomatoService restaurantService;
	
	public ZomatoController(ZomatoService restaurantService) {
		this.restaurantService = restaurantService;
	}
	
	@GetMapping("/viewAllRestaurants")
	public String listOfRestaurants(Model model) {
	model.addAttribute("restaurantsList",restaurantService.fetchAllRestaurents());
	return "restaurants"; //html file name
	}
	
	@GetMapping("/createNewRestaurant")
	public String createRestaurant(Model model) {
		Zomato restobject=new Zomato();
		model.addAttribute("restaurantObject",restobject);
		return "createRestaurant";
	}
	
	@PostMapping("/insertRestaurants")
	public String insertRestaurant (@ModelAttribute("restaurantObject") Zomato obj) {
	restaurantService.saveRestaurents(obj); //object insert into db by using save
	return "redirect:/viewAllRestaurants";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRestaurant(@PathVariable int id) {
		restaurantService.deleteRestaurents(id); 
		return "redirect:/viewAllRestaurants";
	}
	@PostMapping("/updateAndSave/{id}")
	public String updateNewValIntoDb(@PathVariable int id,
			@ModelAttribute("updateRestaurant") Zomato newValFrmFE) throws Exception {
		Zomato existingRestaurant = restaurantService.fetchById(id);
		 existingRestaurant.setRestaurantName((newValFrmFE.getRestaurantName()));
		 existingRestaurant.setRating(newValFrmFE.getRating());
		 existingRestaurant.setAverageCost(newValFrmFE.getAverageCost());
	
		 restaurantService.saveRestaurents( existingRestaurant);
	return "redirect:/viewAllRestaurants";
	}
	
	@GetMapping("/update/{id}")
	public String updateRestaurantRecord(@PathVariable int id, Model model) throws Exception {
		model.addAttribute("updateRestaurant", restaurantService.fetchById(id));
		return "updateRestaurant";
	}
	@GetMapping("/contactme")
	public String contact() {
			
		return "contactUs";
	}
	@GetMapping("/logout")
	public String logout() {
			
		return "logOut";
		
	}
	@GetMapping("/home")
	public String Home() {
			
		return "home";
		
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		Administrator admin= new Administrator();
	    model.addAttribute("adminObj",admin);
		return "login";
	}
	@GetMapping("/validatingData")
	public String validateLogin(@ModelAttribute("adminObj")Administrator adminDetails) {
	if(adminDetails.getUserName().equals("Ramadevi@gmail.com")&& adminDetails.getPassword().equals("Rama@1234")) {
		return "redirect:/home";
	}
	else {
		return "redirect:/login";
	}
	}
}

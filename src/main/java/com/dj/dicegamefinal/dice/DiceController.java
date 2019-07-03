package com.dj.dicegamefinal.dice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DiceController {
	//Get mapping for index
	@GetMapping("/")
	public String getIndexPage(Model model, Cup cup) {
		model.addAttribute("amount", cup);
		return "index";
	}
	//post mapping for index
	@PostMapping("/")
	public String rollDice(@ModelAttribute Cup cup, Model model) {
		int total = shakeCup(cup.isLoaded(), cup.getAmount(),cup.getLoadedNum());
		model.addAttribute("total", total);
		model.addAttribute("cup", cup);
		model.addAttribute("loadedNum",cup.getLoadedNum());
		return "index";
	}
	//postmapping for loaded dice
	@PostMapping("/loaded")
	public String loadedDice1(@ModelAttribute Cup cup, Model model) {
		int total = loadedDice(cup.getLoadedNum());
		
		model.addAttribute("loadedNum",total);
		return "index";
	}
	
	//random number between 1 and 6
	public int dice() {
		int rand = (int) (Math.random() * 6) + 1;
		return rand;
	}
	
	//function to make user's number become the output 50% of the time
	public int loadedDice( int loadedNumb) {
		//numByUser is the loaded number from user input. 
		int numByUser = loadedNumb;
		int[] diceArr = { 1, numByUser, 2, numByUser, 3, 4, numByUser, 5, numByUser, 6,};
		int randomNumber  = 0;
		if(numByUser == 0) {numByUser =1;}
			randomNumber = (int) (Math.random() * diceArr.length);
		
		return diceArr[randomNumber];
	}
	
	//This function returns the total sum of dice input by user
	public int shakeCup(boolean isLoaded, int amount, int loadedNum) {
		int total = 0;
		if (isLoaded) {
			for (int i = 0; i < amount; i++) {
				 total += loadedDice(loadedNum);	
			}
			return total;
		} else {
			for (int i = 0; i < amount; i++) {
				total += dice();
			}
			return total;
		}
	}

}

package textAdventureGame;
import java.util.Scanner;
//this is going to be a simple text adventure game attempting to use everything I have learned so far
//it's basic mechanics are inspired by D&D with a dice roll between 0-20 to determine success


public class Main {
	

	
	
	public static void main(String[] args) {
		Player player = new Player();
		Encounters encounter = new Encounters();
		player.characterCreation();
		
		//System.out.println("You are " + player.name + " Your health is " + player.health + " Your base damage is " + player.baseDamage); //testing if all the values have been correctly set
		encounter.decision();
		
	}
	
}

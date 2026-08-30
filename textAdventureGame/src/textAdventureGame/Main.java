package textAdventureGame;
import java.util.Scanner;
//this is going to be a simple text adventure game attempting to use everything I have learned so far
//it's basic mechanics are inspired by D&D with a dice roll between 0-20 to determine success


public class Main {
	

	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Player player = new Player();
		Encounters encounter = new Encounters();
		player.characterCreation();
		
		//System.out.println("You are " + player.name + " Your health is " + player.health + " Your base damage is " + player.baseDamage); //testing if all the values have been correctly set
		
		System.out.println("What would you like to do?");
		System.out.println("Walk");
		
		String choice = input.nextLine().toUpperCase();
		
		if (choice.equals("WALK")) { //to make this a random encounter I will eventually make it so it needs to be if choice.equals("WALK") && randomNumber == 1
			
			encounter.enemyEncounter("Goblin", 50, 3);
			
		}
		
	}
	
}

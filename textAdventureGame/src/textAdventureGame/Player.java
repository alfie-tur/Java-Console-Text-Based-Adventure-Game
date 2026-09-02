package textAdventureGame;
import java.util.Scanner;

public class Player extends Fighting { //inherits from Fighting
	
	//List of attributes all used throughout the program
	String name; //players name
	int health = 100; //players health
	int baseDamage = 3; //players baseDamage, may be renamed to strength and allow the player to assign a number to it at the start using skill points
	boolean hasCompanion = false; //a boolean to determine whether or not the player has a companion or not. Currently not in use
	String[] itemBag = { "Empty Slot", "Empty Slot", "Empty Slot", "Empty Slot", "Empty Slot"}; //an array for the players inventory
	int gold = 0; //the players money
	boolean discoveredTown = false; //boolean for if the player has discovered the town or not, when true they can return to it if not in combat 
	boolean visitedTrader = false; //very similar to discoveredTown, once you visit the trader once it turns true giving you slightly different dialogue. Adds nicely to world building
	int manaPool = 100; //not currently in use, but this will determine how many spell the player can cast, and they will be able to use mana potions to refill it, or rest in the inn
	
	public void characterCreation() { //method which is called in Encounters, allows the player to create their character. This will soon have attribute point distribution for things such as strength
		Scanner input = new Scanner(System.in);
		boolean happyName = false;
		
		while (happyName == false) { //a method which allows the player to infinitely repeat changing their name until it's what they want
			System.out.println("Enter your name:");
			name = input.nextLine();
			System.out.println("Your name is: " + name);
			System.out.println("Are you happy with this name? Y/N");
			String decision = input.nextLine().toUpperCase();
			
			if (decision.equals("Y")) { //here .equals() must be used instead of == as == compares the reference (if both point to the same point in memory) and .equals() compares the values 
				happyName = true;
			}
			else if (decision.equals("N")) {
				System.out.println();
			}
			else {
				System.out.println("That is not a valid input try again");
				System.out.println();
			}
		}
		//input.close(); //this for some reason stops all input from being taken afterwards with a different Scanner object as System.in can seemingly not be reopened
	}
	
	
}

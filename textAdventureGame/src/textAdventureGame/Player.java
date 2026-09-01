package textAdventureGame;
import java.util.Scanner;

public class Player extends Fighting {
	String name;
	int health = 100;
	int baseDamage = 3;
	String currentLocation;
	String[] itemBag = { "Empty Slot", "Empty Slot", "Empty Slot", "Empty Slot", "Empty Slot"};
	int gold = 0;
	boolean discoveredTown = false;
	
	public void characterCreation() {
		Scanner input = new Scanner(System.in);
		boolean happyName = false;
		
		while (happyName == false) {
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

package textAdventureGame;
import java.util.Scanner;
//this is going to be a simple text adventure game attempting to use everything I have learned so far
//it's basic mechanics are inspired by D&D with a dice roll between 0-20 to determine success


public class Main {
	
	static int diceRoll() { //whenever this is called it returns a random number
		int max = 21; //needs to be 21 so that the max can = 20
		int min = 0;
		int range = max - min;
		int rand = (int)(Math.random() * range) + min;
		return rand;
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Player player = new Player();
		player.characterCreation();
		
		//System.out.println("You are " + player.name + " Your health is " + player.health + " Your base damage is " + player.baseDamage); //testing if all the values have been correctly set
		
		Enemy goblinEnemy = new Enemy("Goblin", 50, 3);
		
		System.out.println("What would you like to do?");
		System.out.println("Walk");
		
		String choice = input.nextLine().toUpperCase();
		
		breakLabel:
		if (choice.equals("WALK")) { //to make this a random encounter I will eventually make it so it needs to be if choice.equals("WALK") && randomNumber == 1
			System.out.println("You have encountered a " + goblinEnemy.name);
			
			System.out.println("Your current health: " + player.health);
			System.out.println("The enemies health: " + goblinEnemy.health);
			
			while(player.health > 0 && goblinEnemy.health > 0) {
				System.out.println(goblinEnemy.health);
				System.out.println("Make your move");
				System.out.println("Attack");
				System.out.println("Run");
				
				choice = input.nextLine().toUpperCase();
				switch (choice) {
				case "ATTACK":
					int newGoblinHealth = player.baseAttack(player.health, player.baseDamage, goblinEnemy.health);
					goblinEnemy.health = newGoblinHealth;
					System.out.println("Goblin Health: " + goblinEnemy.health + "\n Your health: " + player.health);
					break;
				case "RUN":
					int fleeChance = diceRoll();
					if (fleeChance == 20) { System.out.println("You successfully ran away"); break breakLabel; }
					break;
				}

				
			}
			
		}
		
	}
}

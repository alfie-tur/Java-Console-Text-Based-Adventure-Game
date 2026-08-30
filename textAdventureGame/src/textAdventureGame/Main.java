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
	static int randNum() { //random number 1-10
		int max = 11;
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
		
		System.out.println("What would you like to do?");
		System.out.println("Walk");
		
		String choice = input.nextLine().toUpperCase();
		
		if (choice.equals("WALK")) { //to make this a random encounter I will eventually make it so it needs to be if choice.equals("WALK") && randomNumber == 1
			
			attack("Goblin", 50, 3);
			
		}
		
	}
	
	public static void attack(String enemy, int health, int baseDamage) {
		Player player = new Player();
		Enemy enemyObject = new Enemy(enemy, health, baseDamage);
		Scanner input = new Scanner(System.in);
		
		System.out.println("You have encountered a " + enemyObject.name);
		
		System.out.println("Your current health: " + player.health);
		System.out.println("The enemies health: " + enemyObject.health);
		
		breakLabel:
		while(player.health > 0 && enemyObject.health > 0) {
			
			System.out.println("Make your move");
			System.out.println("Attack");
			System.out.println("Run");
			
			String choice = input.nextLine().toUpperCase();
			switch (choice) {
			
			case "ATTACK":
				System.out.println("Attack Roll!");
				int attackRoll = diceRoll();
				System.out.println("You rolled: " + attackRoll);
				
				if (attackRoll == 0) { System.out.println("Critical fail!\nYou damage yourself!"); player.health = player.health - randNum();} //roll for 0 
				
				else if (attackRoll == 20) { System.out.println("Critical success!\nYou manage to pull off an extremely impressive hit!"); enemyObject.health = enemyObject.health - (player.baseDamage + 20); }
				//roll for 20 ^
				
				
				else if (attackRoll > 4 && attackRoll <= 7){
					System.out.println("+3 Damage!");
						int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 3); //attackerHealth, attackerDamage, enemyHealth
						enemyObject.health = newEnemyHealth;
						System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
				} //the logic for using a base attack on an enemy
				
				else if (attackRoll > 7 && attackRoll <= 12){
					System.out.println("+5 Damage!");
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 5); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth;
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
				}
				else if (attackRoll > 12 && attackRoll <= 16){
					System.out.println("+7 Damage");
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 7); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth;
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
				}
				else if (attackRoll > 16 && attackRoll < 20){
					System.out.println("+9 Damage!");
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 9); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth;
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
				}
				else {
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth;
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
				}
				
				break;
			
			case "RUN":
				int fleeChance = diceRoll();
				if (fleeChance == 20) { System.out.println("You successfully ran away"); break breakLabel; }
				break;
			
			default:
				System.out.println("Not a valid option, you forfeit your turn. That'll teach you!");
			}
			
			int enemyChoice = diceRoll();
			System.out.println(enemyObject.name + " dice roll: " + enemyChoice);
			
			if(enemyObject.health <= 30 && enemyChoice > 17){ //goblin heals
				System.out.println();
				System.out.println("The Goblin has used a healing potion");
				enemyObject.health += 20;
				System.out.println("Goblin Health: " + enemyObject.health + "\nYour health: " + player.health);
			}
			
			else { //goblin attack
				System.out.println(); //line to spread out console
				System.out.println("The Goblin attacks!");
				int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health);
				player.health = newPlayerHealth;
				System.out.println("Goblin Health: " + enemyObject.health + "\nYour health: " + player.health);
				System.out.println(); //just a line to spread out the console
			}

			
		}
		
	}
	
}

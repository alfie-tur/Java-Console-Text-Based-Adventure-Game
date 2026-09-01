package textAdventureGame;

import java.util.Scanner;

public class Encounters {
	
	static Player player = new Player();
	
	static int diceRoll() { //whenever this is called it returns a random number between 0 and 20
		int range = 21;
		int rand = (int)(Math.random() * range); //casting a double to an int thus rounding. number between 0 and 1 multiplied by 21, will never be 21 as the 1 is exclusive
		return rand;
	}
	static int randNum1To100() { //random number from 1-100
		int range = 100;
		int rand = (int)(Math.random() * range + 1);
		return rand;
	}
	static int randNum1To50() {
		int max = 50;
		int min = 1;
		int range = max - min;
		int rand = (int)(Math.random() * range + min);
		return rand;
	}
	static int randNum1To10(){
		int max = 10;
		int min = 1;
		int range = max - min;
		int rand = (int)(Math.random() * range + min);
		return rand;
	}

	public void decision() {
		Scanner input = new Scanner(System.in);
		
		boolean game = true;
		
		while (game) {
		
			System.out.println("What would you like to do?");
			System.out.println("Walk");
			System.out.println("View Inventory");
			if (player.discoveredTown == true) { System.out.println("Return to the town"); }
			System.out.println("Quit Game");
			
			String choice = input.nextLine().toUpperCase();
			
			if (choice.equals("WALK")) { //to make this a random encounter I will eventually make it so it needs to be if choice.equals("WALK") && randomNumber == 1
				
				int randNum = randNum1To100();
				
				if (randNum >= 90) {
					int oneInTen = randNum1To10();
					switch(oneInTen) {
					case 1:
						enemyEncounter("Goblin", 50, 3);
						break;
					case 2:
						enemyEncounter("Troll", 150, 7);
						break;
					case 3:
						enemyEncounter("Skeleton", 70, 4);
						break;
					case 4:
						enemyEncounter("Bandit", 100, 3);
						break;
					case 5:
						enemyEncounter("Vampire", 130, 5);
						break;
					case 6:
						enemyEncounter("Mage", 150, 5);
						break;
					case 7:
						enemyEncounter("Imp", 35, 8);
						break;
					case 8:
						enemyEncounter("Werewolf", 135, 8);
						break;
					case 9:
						enemyEncounter("Assassin", 80, 6);
						break;
					case 10:
						enemyEncounter("Daedra", 200, 7);
						break;
					default:
						System.out.println("No enemies encountered");
					}
					
				}
				
				else if (randNum < 20 && player.discoveredTown == false) {
					System.out.println("You have discovered a town!");
					System.out.println("You make note of it's location so that you can return later to purchase some supplies\n");
					player.discoveredTown = true;
				}
				
				else {
					System.out.println("You walk for sometime and find nothing");
					System.out.println();
				}
				System.out.println("Your health after that encounter: " + player.health + "HP");
			}
			
			else if (choice.contains("INV")) {
				
				System.out.println("Bag contents: ");
				for (String item : player.itemBag) {
					System.out.println(item);
				}
				System.out.println("Gold: " + player.gold);
				System.out.println();
				
			}
			
			else if (choice.contains("Q")) {
				game = false;
			}
			
		}
		
	}
	
	public void enemyEncounter(String enemyName, int health, int baseDamage) {
		Enemy enemyObject = new Enemy(enemyName, health, baseDamage);
		Scanner input = new Scanner(System.in);
		
		System.out.println("You have encountered a " + enemyObject.name);
		
		System.out.println("Your current health: " + player.health);
		System.out.println(enemyObject.name + " health: " + enemyObject.health);
		
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
				
				if (attackRoll == 0) { 
					int damageSelf = diceRoll();
					System.out.println("Critical fail!\nYou trip when attempting to go in for an attack, causing you to fall into your own blade! ");
					System.out.println("You damage yourself for " + damageSelf + "HP");
					player.health = player.health - damageSelf;
					} //roll for 0 //damage to self between 0-20
				
				else if (attackRoll == 20) { System.out.println("Critical success!\nYou masterfully land a heavy attack, causing critical damage!"); 
				System.out.println("You attack for " + (player.baseDamage + 20) + "HP");
				enemyObject.health = enemyObject.health - (player.baseDamage + 20); 
				}//roll for 20 
				
				else if (attackRoll > 4 && attackRoll <= 7){ /*all these code blocks are for different attack roles, I thought about doing it as attackRole + baseDamage but decided against it as that would allow for very powerful attacks
					Also debated doing something like (attackRoll - 5) + baseAttack but thought that wouldn't work great for numbers like 2 even with Math.abs()
					I may revisit this idea later*/
					System.out.println("You wildly swing your sword and just about pull off an additional hit!");
					System.out.println("+3 Damage!");
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 3); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth; //performs the calculations for the attack
					System.out.println("You attack for " + (player.baseDamage + 3) + "HP");
				} //the logic for using a base attack on an enemy
				
				else if (attackRoll > 7 && attackRoll <= 12){
					System.out.println("You precisely thrust your sword, managing to pierce through your enemy!");
					System.out.println("+5 Damage!");
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 5); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth;
					System.out.println("You attack for " + (player.baseDamage + 5) + "HP");
				}
				else if (attackRoll > 12 && attackRoll <= 16){
					System.out.println("You cleverly position yourself close to the opponent allowing you to land multiple solid strikes!");
					System.out.println("+7 Damage");
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 7); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth;
					System.out.println("You attack for " + (player.baseDamage + 7) + "HP");
				}
				else if (attackRoll > 16 && attackRoll < 20){
					System.out.println("You dodge and weave around the opponent, all while delivering a flurry of strong attacks!");
					System.out.println("+10 Damage!");
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 10); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth;
					System.out.println("You attack for " + (player.baseDamage + 10) + "HP");
				}
				else {
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth;
					System.out.println("You attack for " + player.baseDamage + "HP");
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
			System.out.println();
			System.out.println(enemyObject.name + " dice roll: " + enemyChoice);
			
			if(enemyObject.health <= 30 && enemyChoice > 17){ //enemy heals
				System.out.println(enemyObject.name + " has used a healing potion"); // Letting you know enemies action
				enemyObject.health += 20; // enemy heals
				
				System.out.println(); //line to spread out console
				
				System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health); //writing new health to the console
			}
			
			else { //enemy attack: these code blocks are all the goblin attacks with different attack bonuses based on the dice roll
				//I did think about just doing one code block as the dice roll added onto the damage but I didn't like that idea, although it likely would have been better for the code
				if (enemyChoice > 4 && enemyChoice <= 7) {
					System.out.println("+3 Damage!");
					
					int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health) - 3;
					player.health = newPlayerHealth; //calculation for enemies attack
					
					System.out.println(enemyObject.name + " attacks for " + (enemyObject.baseDamage + 3) + "HP");
					
					System.out.println(); //line to spread out console
					
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
					//writing to the console the amount attacking for
					
					System.out.println();//line to spread out the console
				}
				else if (enemyChoice > 7 && enemyChoice <= 12) {
					System.out.println("+5 Damage!");
					int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health) - 5;
					player.health = newPlayerHealth;
					System.out.println(enemyObject.name + " attacks for " + (enemyObject.baseDamage + 5) + "HP");
					
					System.out.println(); //line to spread out console
					
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
					System.out.println();//just a line to spread out the console
				}
				else if (enemyChoice > 12 && enemyChoice <= 16) {
					System.out.println("+7 Damage!");
					int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health) - 7;
					player.health = newPlayerHealth;
					System.out.println(enemyObject.name + " attacks for " + (enemyObject.baseDamage + 7) + "HP");
					
					System.out.println(); //line to spread out console
					
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
					System.out.println();//just a line to spread out the console
				}
				else if (enemyChoice > 16 && enemyChoice < 20) {
					System.out.println("+10 Damage!");
					int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health) - 10;
					player.health = newPlayerHealth;
					System.out.println(enemyObject.name + " attacks for " + (enemyObject.baseDamage + 10) + "HP");
					
					System.out.println(); //line to spread out console
					
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
					System.out.println();//just a line to spread out the console
				}
				else {
					int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health);
					player.health = newPlayerHealth;
					System.out.println(enemyObject.name + " attacks for " + enemyObject.baseDamage + "HP");
					
					System.out.println(); //line to spread out console
					
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
					System.out.println();//just a line to spread out the console
				}
			}
			if (player.health < 0) {
				System.out.println("You died, tough luck");
				System.exit(0);
			}
			
			if (enemyObject.health <= 0) {
				int gold = randNum1To50();
				System.out.println("You have won!");
				System.out.println("You search the " + enemyObject.name + " and find " + gold + " gold!\n");
				player.gold =+ gold;
			}
		}

	}
	
}

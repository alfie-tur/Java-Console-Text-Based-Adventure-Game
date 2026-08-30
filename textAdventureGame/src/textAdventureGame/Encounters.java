package textAdventureGame;

import java.util.Scanner;

public class Encounters {
	
	static int diceRoll() { //whenever this is called it returns a random number
		int max = 21; //needs to be 21 so that the max can = 20
		int min = 0;
		int range = max - min;
		int rand = (int)(Math.random() * range) + min;
		return rand;
	}

	
	public void enemyEncounter(String enemyName, int health, int baseDamage) {
		Player player = new Player();
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
				
				if (attackRoll == 0) { System.out.println("Critical fail!\nYou damage yourself!"); player.health = player.health - diceRoll();} //roll for 0 //damage to self between 0-20
				
				else if (attackRoll == 20) { System.out.println("Critical success!\nYou manage to pull off an extremely impressive hit!"); enemyObject.health = enemyObject.health - (player.baseDamage + 20); }
				//roll for 20 ^
				
				
				else if (attackRoll > 4 && attackRoll <= 7){
					System.out.println("+3 Damage!");
						int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 3); //attackerHealth, attackerDamage, enemyHealth
						enemyObject.health = newEnemyHealth;
						System.out.println("You attack for " + (player.baseDamage + 3) + "HP");
				} //the logic for using a base attack on an enemy
				
				else if (attackRoll > 7 && attackRoll <= 12){
					System.out.println("+5 Damage!");
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 5); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth;
					System.out.println("You attack for " + (player.baseDamage + 5) + "HP");
				}
				else if (attackRoll > 12 && attackRoll <= 16){
					System.out.println("+7 Damage");
					int newEnemyHealth = player.baseAttack(player.health, player.baseDamage, enemyObject.health - 7); //attackerHealth, attackerDamage, enemyHealth
					enemyObject.health = newEnemyHealth;
					System.out.println("You attack for " + (player.baseDamage + 7) + "HP");
				}
				else if (attackRoll > 16 && attackRoll < 20){
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
			
			if(enemyObject.health <= 30 && enemyChoice > 17){ //goblin heals
				System.out.println(enemyObject.name + " has used a healing potion");
				enemyObject.health += 20;
				System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
			}
			
			else { //goblin attack
				if (enemyChoice > 4 && enemyChoice <= 7) {
					System.out.println("+3 Damage!");
					System.out.println(); //line to spread out console
					System.out.println(enemyObject.name + " attacks!");
					int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health) - 3;
					player.health = newPlayerHealth;
					System.out.println(enemyObject.name + " attacks for " + (enemyObject.baseDamage + 3) + "HP");
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
					System.out.println();//just a line to spread out the console
				}
				else if (enemyChoice > 7 && enemyChoice <= 12) {
					System.out.println("+5 Damage!");
					System.out.println(); //line to spread out console
					System.out.println(enemyObject.name + " attacks!");
					int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health) - 5;
					player.health = newPlayerHealth;
					System.out.println(enemyObject.name + " attacks for " + (enemyObject.baseDamage + 5) + "HP");
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
					System.out.println();//just a line to spread out the console
				}
				else if (enemyChoice > 12 && enemyChoice <= 16) {
					System.out.println("+7 Damage!");
					System.out.println(); //line to spread out console
					System.out.println(enemyObject.name + " attacks!");
					int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health) - 7;
					player.health = newPlayerHealth;
					System.out.println(enemyObject.name + " attacks for " + (enemyObject.baseDamage + 7) + "HP");
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
					System.out.println();//just a line to spread out the console
				}
				else if (enemyChoice > 16 && enemyChoice < 20) {
					System.out.println("+10 Damage!");
					System.out.println(); //line to spread out console
					System.out.println(enemyObject.name + " attacks!");
					int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health) - 10;
					player.health = newPlayerHealth;
					System.out.println(enemyObject.name + " attacks for " + (enemyObject.baseDamage + 10) + "HP");
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
					System.out.println();//just a line to spread out the console
				}
				else {
					System.out.println(); //line to spread out console
					System.out.println(enemyObject.name + " attacks!");
					int newPlayerHealth = enemyObject.baseAttack(enemyObject.health, enemyObject.baseDamage, player.health);
					player.health = newPlayerHealth;
					System.out.println(enemyObject.name + " attacks for " + enemyObject.baseDamage + "HP");
					System.out.println(enemyObject.name + " Health: " + enemyObject.health + "\nYour health: " + player.health);
					System.out.println();//just a line to spread out the console
				}
			}
			
		}
		
	}
	
}

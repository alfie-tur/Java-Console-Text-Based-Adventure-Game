package textAdventureGame;

import java.util.Scanner;

public class Encounters {
	
	static Player player = new Player();
	static Scanner input = new Scanner(System.in);
	
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

		player.characterCreation(); //will eventually be used to assign player stats
		
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
					//potentially genius idea to spice up combat! create a method which returns random combat dialogue such as "you swing your sword fast" and have that after every attack
					//I would do this as well as it potentially helps keep code tidy
					//... OK I admit "genius" is probably a bit of a stretch, but it seems like a good idea
					
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
			else if (choice.contains("TOWN") && player.discoveredTown == true) {
				System.out.println("You journey back to the town");
				town();
			}
			
			else if (choice.contains("Q")) {
				game = false;
			}
			
			else if (choice.equals("DEVLOPERMODE")) {
				player.health = 10000;
				player.gold = 10000;
				player.baseDamage = 200;
				player.discoveredTown = true;
				System.out.println("Dev health: " + player.health + "\nDev Gold: " + player.gold + "\nDev damage: " + player.baseDamage + "\nAnd the town is discovered");
			}
			
		}
		
	}
	
	public void enemyEncounter(String enemyName, int health, int baseDamage) {
		Enemy enemyObject = new Enemy(enemyName, health, baseDamage);

		
		System.out.println("You have encountered a " + enemyObject.name);
		
		System.out.println("Your current health: " + player.health);
		System.out.println(enemyObject.name + " health: " + enemyObject.health);
		
		breakLabel:
		while(player.health > 0 && enemyObject.health > 0) {
			
			System.out.println("Make your move");
			System.out.println("Attack");
			System.out.println("Inventory");
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
				
			case "INVENTORY":
				combatItemBag();
				break;
			
			case "RUN":
				int fleeChance = diceRoll();
				System.out.println("You rolled: " + fleeChance);
				if (fleeChance == 20) { System.out.println("You successfully ran away"); break breakLabel; }
				break;
			
			default:
				System.out.println("Not a valid option, you forfeit your turn. That'll teach you!");
			}
			
			int enemyChoice = diceRoll();
			System.out.println();
			System.out.println(enemyObject.name + " dice roll: " + enemyChoice);
			
			if(enemyObject.health <= 30 && enemyChoice > 17  && !enemyObject.name.toUpperCase().equals("TROLL") && !enemyObject.name.toUpperCase().equals("WEREWOLF") && !enemyObject.name.toUpperCase().equals("SKELETON")){ //enemy heals
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
				player.gold = player.gold + gold;
			}
		}

	}
	
	public void town() {

		boolean inTown = true;
		while(inTown) {
			System.out.println("Where would you like to go?\nThe Inn, The Whispering Winds \nThe local trader");
			System.out.println("Or would you like to leave?");
			String choice = input.nextLine().toUpperCase();
			
			if (choice.contains("INN") || choice.contains("WHISPERING WINDS")) {
				System.out.println("You have entered the inn");
				System.out.println("Inside there is a warm fire, lighting up the room.\nOne of the inn keepers approaches you and asks if you'd like to rent a room for the night for 5 gold");
				String room = input.nextLine().toUpperCase();
				
				if (room.contains("RENT") || room.contains("YES") && player.gold >= 5) {
					player.gold -= 5; //paying the room keeper
					System.out.println("\nAfter paying the fee, the inn keeper shows you to your room. The room is empty besides a hard wooden bed with sheets made of hay. \nYou sleep until the morning refilling your health");
					player.health = 100; //this will need to be changed when your max health value changes
					System.out.println("You leave the inn");
				}
				else if (room.contains("RENT") || room.contains("YES") && player.gold < 5) {
					System.out.println("\"Then get lost, you're wasting both our time\" said the inn keeper");
					System.out.println("You leave the inn");
				}
				else {
					System.out.println("\"Then what are you doing here? Stop wasting my time.\" said the inn keeper");
					System.out.println("You leave the inn");
				}
			}
			
			else if(choice.contains("TRADER") && player.visitedTrader == false) {
				System.out.println("You enter the local trader to be greeted by a dwarf, though only 3 foot tall he had the face of a man who had lived at least 200 years");
				System.out.println("\"A CUSTOMER!\" Screamed the dwarf, seemingly both excited and angry at the fact you walked in");
				System.out.println("\"Sorry 'bout tha. Me names Aedril, local shop keep 'n dwarf the folk round 'ere don't want me to forget that part. What's yer name?\"");
				System.out.println("You reply, \"I'm " + player.name + ".\"");
				System.out.println("\"Nice to meet ya! Take a look around and see if you can't find what you need.\"");
				player.visitedTrader = true;
				
				System.out.println("You take look around at Aedril's stock \n\nYou find: an abundance of health potions costing 15 gold each"); //more will be added later, likely weapons and armour
				
				System.out.println("Find what you were looking for?");
				shopPurchaseMenu();
				
			}
			else if (choice.contains("TRADER") && player.visitedTrader == true) {
				System.out.println("\"A CUST... oh it's you again! Good to see ya " + player.name + ".\" said the familiar voice of Aedril");
				System.out.println("You look around the shop looking at all of Aedril's wares\nYou find: an abundance of health potions costing 15 gold each");
				
				System.out.println(); //line for spacing out the console
				
				System.out.println("\"Now then lets get straight to business. What would you like?\"");
				
				shopPurchaseMenu();
				
			}
			
			else if(choice.contains("LEAVE")) {
				System.out.println("You venture out of the town, continuing on your adventure");
				inTown = false;
			}
			
		}
		
	}
	
	public void shopPurchaseMenu() { //this is the method that contains the code for choosing an item to purchase and it getting added to your inventory

		boolean everythingYouNeed = false;
		
		while (everythingYouNeed == false) {
			
			System.out.println("What would you like to purchase \nOr will you leave:");
			System.out.println("Your current bag contents:");
			for (String item : player.itemBag) {
				System.out.println(item);
			}
			System.out.println("Gold: " + player.gold);
			
			String shopPurchase = input.nextLine().toUpperCase();
			
			if (shopPurchase.contains("HEALTH") && player.gold >= 15) {
				
				for (int i = 0; i < player.itemBag.length; i++) {
					if (player.itemBag[i].equals("Empty Slot")) {
						player.itemBag[i] = "Health Potion";
						player.gold -= 15;
						break;
					}
					else {
						System.out.println("You have no free inventory slot");
					}
					
				}
				
			}
			
			else if(shopPurchase.contains("LEAVE")) {
				System.out.println("\"Be seeing you, " + player.name + "!\" Shouted Aedril, as you walk out of the shop");
				everythingYouNeed = true;
			}
			
		}
	}
	
	public void combatItemBag() {
		boolean doneInBag = false;
		while(!doneInBag) {
			System.out.println("Your Inventory: ");
			for (String item : player.itemBag) {
				System.out.println(item);
			}
			System.out.println("What would you like to use?");
			System.out.println("If you are finished in your inventory type done");
			String choice = input.nextLine().toUpperCase();
			if (choice.contains("HEALTH")) {
				for (int i = 0; i < player.itemBag.length; i++) {
					if (player.itemBag[i].equals("Health Potion")) {
						System.out.println("You consume 1 health potion");
						player.health += 20;
						player.itemBag[i] = "Empty Slot";
						System.out.println("New health: " + player.health);
						break;
					}
					else {
						System.out.println("No health potion in inventory slot " + i);
					}
				}
			}
			else if (choice.contains("DONE")) {
				System.out.println("Closing inventory");
				doneInBag = true;
			}
		}
		
	}
	
	public void introMessages() {
		int introChoice = randNum1To50()/10;
		switch (introChoice) {
		case 1:
			System.out.println("You wake up, laying beside a wide river passing right through the centre of a dense forest. \nBeside you is your trusty 5 slot bag, your empty coin pouch, and of course your sword.");
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		}
	}
	
}

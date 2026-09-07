package textAdventureGame;

public class Fighting {
	
	static int diceRoll() { //whenever this is called it returns a random number between 1 and 20
		int range = 20;
		int rand = (int)(Math.random() * range + 1); //casting a double to an int thus rounding. number between 0 and 1 multiplied by 21, will never be 21 as the 1 is exclusive
		return rand;
	}
	
	public int baseAttack(String yourName, int health, int damage, int enemyHealth) {
		int attackRoll = diceRoll();
		System.out.println(yourName + " Rolled: " + attackRoll);
		
		if (attackRoll == 20) { 
		//System.out.println("Critical success!\nYou masterfully land a heavy attack, causing critical damage!"); 
		System.out.println("Attack for " + (damage + 20) + "HP");
		return enemyHealth - (damage + 20); 
		}//roll for 20 
		
		else if (attackRoll > 4 && attackRoll <= 7){ /*all these code blocks are for different attack roles, I thought about doing it as attackRole + baseDamage but decided against it as that would allow for very powerful attacks
			Also debated doing something like (attackRoll - 5) + baseAttack but thought that wouldn't work great for numbers like 2 even with Math.abs()
			I may revisit this idea later*/
			//System.out.println("You wildly swing your sword and just about pull off an additional hit!");
			System.out.println("+3 Damage!");
			System.out.println("Attack for " + (damage + 3) + "HP");
			return enemyHealth - damage - 3;
		} //the logic for using a base attack on an enemy
		
		else if (attackRoll > 7 && attackRoll <= 12){
			//System.out.println("You precisely thrust your sword, managing to pierce through your enemy!");
			System.out.println("+5 Damage!");
			System.out.println("Attack for " + (damage + 5) + "HP");
			return enemyHealth - damage - 5;
		}
		
		else if (attackRoll > 12 && attackRoll <= 16){
			//System.out.println("You cleverly position yourself close to the opponent allowing you to land multiple solid strikes!");
			System.out.println("+7 Damage");
			System.out.println("Attack for " + (damage + 7) + "HP");
			return enemyHealth - damage - 7;
		}
		
		else if (attackRoll > 16 && attackRoll < 20){
			//System.out.println("You dodge and weave around the opponent, all while delivering a flurry of strong attacks!");
			System.out.println("+10 Damage!");
			System.out.println("Attack for " + (damage + 10) + "HP");
			return enemyHealth - damage - 10;
		}
		
		else { //attack when dice roll adds no bonus
			System.out.println("Attack for " + damage + "HP");
			return enemyHealth - damage;
		}
		
	}
	
}

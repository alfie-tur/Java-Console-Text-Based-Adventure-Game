package textAdventureGame;
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
		
	}
}

package textAdventureGame;

public class Companion {
	String name;
	int health;
	int baseDamage;
	int manaPool;
	
	public Companion(String name, int health, int baseDamage, int manaPool) {
		this.name = name;
		this.health = health;
		this.baseDamage = baseDamage;
		this.manaPool = manaPool;
	} //this allows me to set Companion attributes specific to each object made
}

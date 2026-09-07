package textAdventureGame;

public class Enemy extends Fighting {
	String name;
	int health;
	int baseDamage;
	
	public Enemy(String name, int health, int baseDamage) {
		this.name = name;
		this.health = health;
		this.baseDamage = baseDamage;
	} //this allows me to set Enemy attributes specific to each object made
}

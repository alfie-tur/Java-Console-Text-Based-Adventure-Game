package textAdventureGame;

public class Fighting {
	
	public int baseAttack(int health, int damage, int enemyHealth) {
		enemyHealth = enemyHealth - damage;
		return enemyHealth;
	}
}

package textAdventureGame;

public class Fighting extends Main {
	
	public int baseAttack(int health, int damage, int enemyHealth) {
		enemyHealth = enemyHealth - damage;
		return enemyHealth;
	}
}

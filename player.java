package main;

public class Player {
	private String name;
	private int score;
	private int life;
	
	public Player(String name) {
		super();
		this.name = name;
		this.score = 0;
		this.life = 10;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public int getLife() {
		return life;
	}	
	
	public void deductLife() {
		life--;
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	public boolean hasRunOutOfLives() {
		return life == 0;
	}
}

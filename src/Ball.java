import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	private int x = 400;
	private int y = 25;
	private double dx = 0;
	private double dy = 30;
	private int radius = 20;
	private double gravity = 15;
	//private double energyloss = 1;
	//private double xFriction = .9;
	private double gameDy = -75;
	private double dt = .2;
	private int agility = 3;
	private int maxSpeed = 20;
	private boolean gameOver = false;

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public double getGameDy() {
		return gameDy;
	}

	public void setGameDy(double gameDy) {
		this.gameDy = gameDy;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public Ball() {
		// TODO Auto-generated constructor stub
	}


	public void moveRight() {
		if (dx + agility < maxSpeed) {
			dx += agility;
		}

	}

	public void moveLeft() {
		if (dx - agility > -maxSpeed) {
			dx -= agility;
		}

	}

	public void update(Game game) {
		//verifica coliziunea cu peretii latera
		if (x + dx > game.getWidth() - radius - 1) {
			x = game.getWidth() - radius - 1;
			dx = -dx;
		} else if (x + dx < 0 + radius) {
			x = 0 + radius;
			dx = -dx;
		} else {
			x = x + (int) dx;
		}
		/*
		if (y == game.getHeight() - radius - 1) {
			dx *= xFriction;
			if (Math.abs(dx) < .8) {
				dx = 0;
			}
		}
		*/

		if (y-200 > game.getHeight() - radius - 1) {
			gameOver = true;
			/*y = game.getHeight() - radius - 1;
			dy *= energyloss;
			dy = gameDy;*/
		} else {
			// formula vitezei
			dy += gravity * dt;
			// formula pozitiei
			y += dy * dt + .5 * gravity * dt * dt;
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.cyan);
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		
	
	}
}

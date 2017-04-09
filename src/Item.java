import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Item {
	
	private int x, y, radius, dx;
	private Game game;
	private boolean createNew = false;
	
	
	
	public boolean isCreateNew() {
		return createNew;
	}

	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Item(int i) {
		x = i;
		dx = -2;
		Random r = new Random();
		y =r.nextInt(300) + radius;
		radius = 10;
	}
	
	public void update(Game game, Ball b) {
		x += dx;
		this.game = game;
		checkForCollision(b);
	 	if( x < 0 - radius){
	 		createNew = true;
	 	}
	}

	public void checkForCollision(Ball b){
		int ballY = b.getY();
		int ballX = b.getX();
		int ballR = b.getRadius();
		double ballDy = b.getDy();
	
		double a = x - ballX;
		double bb = y - ballY;
		double collision = radius + ballR;
		double c = Math.sqrt(a*a + bb*bb);
		
		if(c < collision){
			performAction(b);
			createNew = true;
		}
		
			}
		

	
	public void performAction(Ball b) {
		// TODO Auto-generated method stub
		
	}

	public void paint(Graphics g) {
		
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
	}

	
}

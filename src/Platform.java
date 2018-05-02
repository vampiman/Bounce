import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class Platform {

	private int x;
	private int y;
	private int width, height;
	private double dx = 0;
	Image plat;
	URL url;
	float frame = 0;

	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	
	public Platform() {
		// TODO Auto-generated constructor stub
		x = 300;
		y = 300;
		width = 120;
		height = 30;
		dx = -1;
		plat = Pictures.platform;
	}
	
	
	public Image getPlat() {
		return plat;
	}

	public void setPlat(Image plat) {
		this.plat = plat;
	}

	public Platform(int i, int j){
		x = i;
		y = j;
		width = 120;
		height = 40;
		dx = -1;
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

	public void update(Game game, Ball b) {
		x += -Game.level;
	 checkForCollision(b);
	 	if( x < 0 - width){
	 		Random r = new Random();
	 		y = game.getHeight() - 40 - r.nextInt(300);
	 		x = game.getWidth() + r.nextInt(300);
	 	}
	}

	public void checkForCollision(Ball b){
		int ballY = b.getY();
		int ballX = b.getX();
		int radius = b.getRadius();
		double ballDy = b.getDy();
		
		if(ballY + radius > y && ballY + radius < y + height + 1)
			if(ballX + radius > x && ballX - radius < x + width){
				
			ballDy = b.getDy() * -1;
			b.setY(y - radius);
			b.setDy(b.getGameDy());
		}
		
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		g.drawImage(plat, x, y, Pictures.game);
		g.drawImage(plat, x, y, x + width, y + height, 0, 40*(int) frame, 120, 40*(int) frame + 40, Pictures.game);
	}

}

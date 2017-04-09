import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class ScoreUp extends Item {
	
	private Game appletInfo;

	public ScoreUp(int x, Game appletInfo) {
		super(x);
		// TODO Auto-generated constructor stub
		this.appletInfo = appletInfo;
	}

	@Override
	public void performAction(Ball b) {
		// TODO Auto-generated method stub
		Random r = new Random();
		appletInfo.setScore(appletInfo.getScore() + 500 + r.nextInt(2000));
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.CYAN);
		super.paint(g);
	}
}

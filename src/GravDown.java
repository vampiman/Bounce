import java.awt.Color;
import java.awt.Graphics;

public class GravDown extends Item {
	public GravDown(int i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performAction(Ball b) {
		// TODO Auto-generated method stub
		if(b.getGravity() > 3){
		b.setGravity(b.getGravity() - 3);}
		else b.setGravity(3);
		}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		super.paint(g);
	}
}

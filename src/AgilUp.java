import java.awt.Color;
import java.awt.Graphics;

public class AgilUp extends Item{
	public AgilUp(int i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performAction(Ball b) {
		// TODO Auto-generated method stub
		if(b.getAgility() < b.getMaxSpeed())
		b.setAgility(b.getAgility() + 1);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.magenta);
		super.paint(g);
	}
}

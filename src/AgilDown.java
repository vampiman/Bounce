import java.awt.Color;
import java.awt.Graphics;

public class AgilDown extends Item{
	public AgilDown(int i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performAction(Ball b) {
		// TODO Auto-generated method stub
		if(b.getAgility() >= 2)
		b.setAgility(b.getAgility() - 1);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		super.paint(g);
	}
}

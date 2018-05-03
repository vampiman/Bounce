import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.Random;

public class Game extends Applet implements Runnable, KeyListener, MouseMotionListener, MouseListener {
	
	
	Ball b1;
	Platform p[] = new Platform[7];
	Item item[] = new Item[5];
	private int score, levelcheck = 0;
	private boolean Hover=false;
	static int level = 1;
	double forestX = 0;
	double forestDx = .3;
	Image forest;
	Image platform;
	URL url;
	float frame = 0;
	AudioClip backgroundm;
	boolean gameOver = false;
	
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private Image i;
	private Graphics doubleG;

	@Override
	public void init() {

		setSize(800, 600);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		try {
			url = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		forest = getImage(url, "image/Forest.png");
		backgroundm = getAudioClip(url, "music/music1.wav");
		platform = getImage(url, "image/platform 1.png");
		
	}
	
	
	
	@Override
	public void start() {
		
		score = 1;
		b1 = new Ball();
		for (int i = 0; i < p.length; i++) {
			p[i] = new Platform(i * 120,300);
		}

		for (int i = 0; i < item.length; i++) {

			Random r = new Random();

			switch (r.nextInt(5)) {
			case 0:
				item[i] = new GravUp(getWidth() + 1000 * i);
				break;
			case 1:
				item[i] = new GravDown(getWidth() + 1000 * i);
				break;
			case 2:
				item[i] = new AgilUp(getWidth() + 1000 * i);
				break;
			case 3:
				item[i] = new AgilDown(getWidth() + 1000 * i);
				break;
			case 4:
				item[i] = new ScoreUp(getWidth() + 1000 * i, this);
				break;
			}
		}
		backgroundm.play();
		Thread thread = new Thread(this);
		thread.start();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random r = new Random();
		while (true) {
			
			gameOver = b1.isGameOver();
			
			if(levelcheck > 100){
				level++;
				levelcheck = 0;
			}
			levelcheck++;
			
			if (forestX > getWidth() * -1) {
				forestX -= forestDx;
			} else {
				forestX = 0;
			}
			//Oprire scor
			if(!gameOver) {
			score++;
			}
			
			//Oprire muzica
			if(gameOver) {
			backgroundm.stop();
			}
			
			for (int i = 0; i < item.length; i++) {
				if (item[i].isCreateNew()) {
					item[i] = null;
					switch (r.nextInt(5)) {
					case 0:
						item[i] = new GravUp(getWidth() + 5 * r.nextInt(500));
						break;
					case 1:
						item[i] = new GravDown(getWidth() + 5 * r.nextInt(500));
						break;
					case 2:
						item[i] = new AgilUp(getWidth() + 5 * r.nextInt(500));
						break;
					case 3:
						item[i] = new AgilDown(getWidth() + 5 * r.nextInt(500));
						break;
					case 4:
						item[i] = new ScoreUp(getWidth() + 5 * r.nextInt(500), this);
						break;
					}
					item[i].setCreateNew(false);
				}
			}

			b1.update(this);
			for (int i = 0; i < p.length; i++) {
				p[i].update(this, b1);
			}
			for (int i = 0; i < item.length; i++) {
				item[i].update(this, b1);
			}
			repaint();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub

		if (i == null) {
			i = createImage(this.getWidth(), this.getHeight());
			doubleG = i.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getWidth(), this.getHeight());

		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(i, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(11, 77, 147));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(forest, (int) forestX, 0, this);
		g.drawImage(forest, (int) forestX + getWidth(), 0, this);
		
		int tester = (int)( frame + .1);
			if (tester < 3)
				frame += .1;
			else
				frame = 0;
		
		for (int i = 0; i < p.length; i++) {
			
		    g.drawImage(platform, p[i].getX(), p[i].getY(), p[i].getX() + p[i].getWidth(), p[i].getY() + p[i].getHeight(), 0, 40*(int) frame, 120, 40*(int) frame + 40, this);
		}
		
		b1.paint(g);
	
		
		for (int i = 0; i < item.length; i++) {
			item[i].paint(g);
		}

		String s = Integer.toString(score);
		Font font = new Font("Comic Sans", Font.BOLD, 32);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(s, getWidth() - 150 + 2, 50 + 2);
		g.setColor(new Color(197, 226, 255));
		g.drawString(s, getWidth() - 150, 50);
		if(gameOver){
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawString("GAME OVER", 302, 302);
			g.setColor(new Color(197, 226, 255));
			g.drawString("GAME OVER", 300, 300);
			g.setColor(Color.BLACK);
			g.drawString("Try again", 328, 352);
			if(Hover)
			{
				g.setColor(new Color(255, 153, 0));
				g.drawString("Try again", 326, 350);
				
			}else
			{
				g.setColor(Color.YELLOW);
				g.drawString("Try again", 326, 350);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			b1.moveLeft();
			break;

		case KeyEvent.VK_RIGHT:
			b1.moveRight();
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(gameOver) {
		if(e.getX() > 326 && e.getX() < 470) {
			if(e.getY() > 325 && e.getY() < 350)
				Hover=true;
		}
		
		if(e.getX() <=326 || e.getX() >=470)Hover=false;
		if(e.getY() <= 325 || e.getY() >= 350)Hover=false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Hover == true && gameOver == true) {
			b1 = null;
			gameOver=false;
			backgroundm.play();
			level = 0;
			score = 1;
			b1 = new Ball();
			b1.setGameOver(false);
			for (int i = 0; i < p.length; i++) {
				p[i] = new Platform(i * 120,300);
			}

			for (int i = 0; i < item.length; i++) {

				Random r = new Random();

				switch (r.nextInt(5)) {
				case 0:
					item[i] = new GravUp(getWidth() + 1000 * i);
					break;
				case 1:
					item[i] = new GravDown(getWidth() + 1000 * i);
					break;
				case 2:
					item[i] = new AgilUp(getWidth() + 1000 * i);
					break;
				case 3:
					item[i] = new AgilDown(getWidth() + 1000 * i);
					break;
				case 4:
					item[i] = new ScoreUp(getWidth() + 1000 * i, this);
					break;
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

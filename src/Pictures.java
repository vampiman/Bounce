import java.awt.Image;
import java.net.URL;

public class Pictures {

	static Image platform, ball;
	URL url;
	static Game game;
	
	public Pictures(Game game) {
		// TODO Auto-generated constructor stub
		try {
			url = game.getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		platform = game.getImage(url, "image/platform 1.png ");
	}
}

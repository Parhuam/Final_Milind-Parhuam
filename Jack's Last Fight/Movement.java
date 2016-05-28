import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Movement extends KeyAdapter {
	
	private Player player;
	
	public Movement(Player player) {
		this.player = player;
	}
	
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}
}

package dungeongame;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public GameFrame(){
		setSize(800, 600);
		setTitle("Dungeon Game");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		setVisible(true);
	}
	
}

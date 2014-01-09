package dungeongame;

import java.awt.Canvas;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private Canvas gameCanvas;
	
	public GameFrame(){
		setSize(800, 600);
		setTitle("Dungeon Game");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		gameCanvas = new Canvas();
		gameCanvas.setBounds(0, 0, 800, 600);
		
		add(gameCanvas);
		setVisible(true);
	}
	
}

package dungeongame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private Canvas gameCanvas;
	private BufferStrategy gameCanvasBufferStrategy;
	
	public GameFrame(){
		setSize(800, 600);
		setTitle("Dungeon Game");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		gameCanvas = new Canvas();
		gameCanvas.setBounds(0, 0, 800, 600);
		
		GameInput gameInput = new GameInput();
		gameCanvas.addMouseListener(gameInput);
		gameCanvas.addKeyListener(gameInput);
		
		add(gameCanvas);
		setVisible(true);
		
		gameCanvas.createBufferStrategy(2);
		gameCanvasBufferStrategy = gameCanvas.getBufferStrategy();
	}
	
	public void refresh(){
		Graphics g = gameCanvasBufferStrategy.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		
		GameStateManager.drawCurrentGameState(g);
		g.dispose();
		gameCanvasBufferStrategy.show();
	}
}

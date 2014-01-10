package dungeongame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameInput implements MouseListener{
	public static boolean wasLeftClicked, wasRightClicked;
	private static boolean isLeftClicked, isRightClicked;

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)isLeftClicked = true;
		else if(e.getButton() == MouseEvent.BUTTON3) isRightClicked = true;
	}
	
	public static void pushInputs(){
		//Push Mouse Input
		wasLeftClicked = isLeftClicked;
		isLeftClicked = false;
		wasRightClicked = isRightClicked;
		isRightClicked = false;
	}

}

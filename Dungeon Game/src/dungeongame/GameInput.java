package dungeongame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class GameInput implements MouseListener , KeyListener{
	private static boolean wasLeftClicked, wasRightClicked;
	private static boolean isLeftClicked, isRightClicked;
	private static HashMap<String, Boolean> keyIsDown, keyWasDown, keyIsPressed, keyWasPressed;
	private static HashMap<Integer, String> keyBinds;

	static{
		keyIsPressed = new HashMap<String, Boolean>();
		keyIsDown = new HashMap<String, Boolean>();
		
		keyBinds = new HashMap <Integer, String> ();
		keyBinds.put(KeyEvent.VK_W, "Up");
		keyBinds.put(KeyEvent.VK_S, "Down");
		keyBinds.put(KeyEvent.VK_A, "Left");
		keyBinds.put(KeyEvent.VK_D, "Right");
		keyBinds.put(KeyEvent.VK_X, "Menu");
		keyBinds.put(KeyEvent.VK_SPACE, "Action");
	}
	
	public static boolean wasKeyDown(String keyName){
		if(keyWasDown.containsKey(keyName))return keyWasDown.get(keyName);
		else return false;
	}
	
	public static boolean wasKeyPressed(String keyName){
		if(keyWasPressed.containsKey(keyName))return keyWasPressed.get(keyName);
		else return false;
	}
	
	public static boolean wasLeftClicked(){
		return wasLeftClicked;
	}
	
	public static boolean wasRightClicked(){
		return wasRightClicked;
	}
	
	public static void pushInputs(){
		//Push Mouse Input
		wasLeftClicked = isLeftClicked;
		isLeftClicked = false;
		wasRightClicked = isRightClicked;
		isRightClicked = false;
		keyWasDown = keyIsDown;
		keyIsDown = new HashMap<String, Boolean>();
		keyWasPressed = keyIsPressed;
		keyIsPressed = new HashMap<String, Boolean>();
	}
	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)isLeftClicked = true;
		else if(e.getButton() == MouseEvent.BUTTON3) isRightClicked = true;
	}
	
	public void keyPressed(KeyEvent e) {	
		String name = determineKeyName(e);
		if ( name != null )
			keyIsDown.put(name, true);
	}

	public void keyReleased(KeyEvent e) {
		String name = determineKeyName(e);
		if ( name != null )
			keyIsPressed.put(name, true);
	}
	
	private static String determineKeyName(KeyEvent e){
		if ( keyBinds.containsKey ( e.getKeyCode() ) )
			return keyBinds.get(e.getKeyCode());
		return null;
	}
	
	//UNUSED
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void keyTyped(KeyEvent e) {		
	}

}

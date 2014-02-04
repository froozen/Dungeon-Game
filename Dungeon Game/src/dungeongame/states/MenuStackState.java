package dungeongame.states;

import java.awt.Graphics;
import java.util.ArrayList;

import dungeongame.menus.BaseMenu;

public class MenuStackState implements BaseState{
	private ArrayList<BaseMenu> menuStack;

	public MenuStackState(){
		menuStack = new ArrayList<BaseMenu>();
		addMenu(new HUDState());
	}
	
	public void drawMe(Graphics g) {
		if(menuStack.size() > 0){
			for(BaseMenu menu:menuStack)menu.drawMe(g);
		}
	}

	public void computeNextFrame() {
		if(menuStack.size() > 0){
			menuStack.get(menuStack.size() - 1).computeNextFrame();
		}
	}

	public void addMenu(BaseMenu menu){
		menuStack.add(menu);
	}

	public void removeTopMenu(){
		menuStack.remove(menuStack.size() - 1);
	}

}

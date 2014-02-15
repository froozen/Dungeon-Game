package dungeongame.menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dungeongame.GameInput;
import dungeongame.GameStateManager;
import dungeongame.GameVariables;
import dungeongame.RessourceManager;
import dungeongame.GameStateManager.GameStateFocus;
import dungeongame.states.HUDState;

public class InventoryMenu implements BaseMenu{
	private BufferedImage backgroundImage;

	public InventoryMenu(){
		backgroundImage = RessourceManager.getImage("ui.inventory.background");
	}
	
	public void drawMe(Graphics g) {
		g.drawImage(backgroundImage, 100, 100, null);
		
		renderPlayerStats(g);
	}

	public void computeNextFrame() {
		if(GameInput.wasKeyPressed("X")){
			GameStateManager.changeForegroundState(new HUDState());
			GameStateManager.changeGameStateFocus(GameStateFocus.BACKGROUND);
		}
	}

	private void renderPlayerStats(Graphics g){
		BufferedImage statsImage;
		
		g.drawImage(RessourceManager.getFontifiedText("ATK:", "standard"), 560, 225, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.atk, "standard");
		g.drawImage(statsImage, 615 - statsImage.getWidth(), 225, null);
		
		g.drawImage(RessourceManager.getFontifiedText("DEF:", "standard"), 560, 237, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.def, "standard");
		g.drawImage(statsImage, 615 - statsImage.getWidth(), 237, null);
		
		g.drawImage(RessourceManager.getFontifiedText("STR:", "standard"), 560, 249, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.str, "standard");
		g.drawImage(statsImage, 615 - statsImage.getWidth(), 249, null);
		
		g.drawImage(RessourceManager.getFontifiedText("DEX:", "standard"), 622, 225, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.dex, "standard");
		g.drawImage(statsImage, 685 - statsImage.getWidth(), 225, null);
		
		g.drawImage(RessourceManager.getFontifiedText("LUK:", "standard"), 622, 237, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.luk, "standard");
		g.drawImage(statsImage, 685 - statsImage.getWidth(), 237, null);
		
		g.drawImage(RessourceManager.getFontifiedText("MAG:", "standard"), 622, 249, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.mag, "standard");
		g.drawImage(statsImage, 685 - statsImage.getWidth(), 249, null);
	}
}

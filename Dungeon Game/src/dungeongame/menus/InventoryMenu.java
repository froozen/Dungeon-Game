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
		renderPlayerBars(g);
		renderPlayerLeveling(g);
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
		
		g.drawImage(RessourceManager.getFontifiedText("DEX:", "standard"), 560, 237, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.dex, "standard");
		g.drawImage(statsImage, 615 - statsImage.getWidth(), 237, null);
		
		g.drawImage(RessourceManager.getFontifiedText("STR:", "standard"), 560, 249, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.str, "standard");
		g.drawImage(statsImage, 615 - statsImage.getWidth(), 249, null);
		
		g.drawImage(RessourceManager.getFontifiedText("DEF:", "standard"), 622, 225, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.def, "standard");
		g.drawImage(statsImage, 685 - statsImage.getWidth(), 225, null);
		
		g.drawImage(RessourceManager.getFontifiedText("LUK:", "standard"), 622, 237, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.luk, "standard");
		g.drawImage(statsImage, 685 - statsImage.getWidth(), 237, null);
		
		g.drawImage(RessourceManager.getFontifiedText("MAG:", "standard"), 622, 249, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.mag, "standard");
		g.drawImage(statsImage, 685 - statsImage.getWidth(), 249, null);
	}
	
	private void renderPlayerBars(Graphics g){
		BufferedImage hpBar = RessourceManager.getImage("ui.inventory.hpbar");
		int hpRestWidth = (int)(hpBar.getWidth() * ((double)(GameVariables.playerBattleStats.hp / (double)GameVariables.playerBattleStats.maxHp)));
		hpBar = hpBar.getSubimage(hpBar.getWidth() - hpRestWidth, 0, hpRestWidth, hpBar.getHeight());
		g.drawImage(hpBar, 552, 262, null);
		
		BufferedImage mpBar = RessourceManager.getImage("ui.inventory.mpbar");
		int mpRestWidth = (int)(mpBar.getWidth() * ((double)(GameVariables.playerBattleStats.mp / (double)GameVariables.playerBattleStats.maxMp)));
		mpBar = mpBar.getSubimage(mpBar.getWidth() - mpRestWidth, 0, mpRestWidth, mpBar.getHeight());
		g.drawImage(mpBar, 552, 292, null);
		
		BufferedImage expBar = RessourceManager.getImage("ui.inventory.expbar");
		
		int expRestWidth = (int)(expBar.getWidth() * (((double)(GameVariables.playerBattleStats.lastExpToNextLevel - GameVariables.playerBattleStats.expToNextLevel)) / ((double)GameVariables.playerBattleStats.lastExpToNextLevel)));
		if(expRestWidth > 0){
			expBar = expBar.getSubimage(expBar.getWidth() - expRestWidth, 0, expRestWidth, expBar.getHeight());
			g.drawImage(expBar, 552, 322, null);
		}
	}
	
	private void renderPlayerLeveling(Graphics g){
		BufferedImage levelImage;
		
		g.drawImage(RessourceManager.getFontifiedText("Level:", "standard"), 550, 333, null);
		levelImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.level, "standard");
		g.drawImage(levelImage, 610 - levelImage.getWidth(), 333, null);
		
		g.drawImage(RessourceManager.getFontifiedText("Next:", "standard"), 620, 333, null);
		levelImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.expToNextLevel, "standard");
		g.drawImage(levelImage, 694 - levelImage.getWidth(), 333, null);
	}
}

package dungeongame.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dungeongame.GameVariables;
import dungeongame.RessourceManager;
import dungeongame.menus.BaseMenu;

public class HUDState implements BaseState, BaseMenu{

	public void drawMe(Graphics g) {		
		g.drawImage(RessourceManager.getImage("hud.background"), 0, 0, null);
		
		renderPlayerHPBar(g);
		renderPlayerMPBar(g);
		renderPlayerExpBar(g);
		
		g.drawImage(RessourceManager.getImage("hud.overlay"), 0, 0, null);
		
		// TODO Implement leveling
//		BufferedImage level = RessourceManager.getFontifiedText("LV:" + GameVariables.playerBattleStats.level, "outline");
//		g.drawImage(level, 5, 5, null);
	}

	//UNUSED
	public void computeNextFrame() {
	}
	
	private void renderPlayerHPBar(Graphics g){
		BufferedImage hpBar = RessourceManager.getImage("hud.hpbar");
		int hpBarOffset = hpBar.getWidth() - ((int) (hpBar.getWidth() * ((double)GameVariables.playerBattleStats.hp / (double)GameVariables.playerBattleStats.maxHp)));
		
		String hpText = GameVariables.playerBattleStats.hp + "/" + GameVariables.playerBattleStats.maxHp;
		BufferedImage hpTextImage = RessourceManager.getFontifiedText(hpText, "outline");
		int hpTextX = 53 + (hpBar.getWidth() / 2) - (hpTextImage.getWidth() / 2);
		
		g.drawImage(hpBar, 53 - hpBarOffset, 3, null);
		g.drawImage(hpTextImage, hpTextX, 3, null);
	}
	
	private void renderPlayerMPBar(Graphics g){
		BufferedImage mpBar = RessourceManager.getImage("hud.mpbar");
		int mpBarOffset = mpBar.getWidth() - ((int) (mpBar.getWidth() * ((double)GameVariables.playerBattleStats.mp / (double)GameVariables.playerBattleStats.maxMp)));
		
		String mpText = GameVariables.playerBattleStats.mp + "/" + GameVariables.playerBattleStats.maxMp;
		BufferedImage mpTextImage = RessourceManager.getFontifiedText(mpText, "outline");
		int mpTextX = 38 + (mpBar.getWidth() / 2) - (mpTextImage.getWidth() / 2);
		
		g.drawImage(mpBar, 38 - mpBarOffset, 18, null);
		g.drawImage(mpTextImage, mpTextX, 18, null);
	}
	
	private void renderPlayerExpBar(Graphics g){
//		BufferedImage expBar = RessourceManager.getImage("hud.expbar");
//		int expBarOffset = expBar.getWidth() - ((int) (expBar.getWidth() * (((double)(GameVariables.playerBattleStats.lastExpToNextLevel - GameVariables.playerBattleStats.expToNextLevel)) / (double)GameVariables.playerBattleStats.lastExpToNextLevel)));
		
//		g.drawImage(expBar, 2 - expBarOffset, 32, null);
	}
}

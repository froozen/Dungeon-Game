package dungeongame.menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dungeongame.GameInput;
import dungeongame.GameStateManager;
import dungeongame.GameVariables;
import dungeongame.Inventory;
import dungeongame.RessourceManager;
import dungeongame.GameStateManager.GameStateFocus;
import dungeongame.basetypes.items.BaseItem;
import dungeongame.states.HUDState;

public class InventoryMenu implements BaseMenu{
	private final int xRow = 11, yRow = 10;
	
	private BufferedImage backgroundImage;

	public InventoryMenu(){
		backgroundImage = RessourceManager.getImage("ui.inventory.background");
	}
	
	public void drawMe(Graphics g) {
		g.drawImage(backgroundImage, 100, 100, null);
		
		renderPlayerStats(g);
		renderPlayerBars(g);
		renderPlayerLeveling(g);
		renderSelectedItemInformation(g);
		renderItemFields(g);
	}

	public void computeNextFrame() {
		if(GameInput.wasKeyPressed("X")){
			GameStateManager.changeForegroundState(new HUDState());
			GameStateManager.changeGameStateFocus(GameStateFocus.BACKGROUND);
		}
	}

	private void renderPlayerStats(Graphics g){
		BufferedImage statsImage;
		
		g.drawImage(RessourceManager.getFontifiedText("ATK:", "standard"), 550, 122, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.atk, "standard");
		g.drawImage(statsImage, 617 - statsImage.getWidth(), 122, null);
		
		g.drawImage(RessourceManager.getFontifiedText("DEF:", "standard"), 550, 134, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.def, "standard");
		g.drawImage(statsImage, 617 - statsImage.getWidth(), 134, null);
		
		g.drawImage(RessourceManager.getFontifiedText("STR:", "standard"), 550, 146, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.str, "standard");
		g.drawImage(statsImage, 617 - statsImage.getWidth(), 146, null);
		
		g.drawImage(RessourceManager.getFontifiedText("MAG:", "standard"), 550, 158, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.mag, "standard");
		g.drawImage(statsImage, 617 - statsImage.getWidth(), 158, null);
		
		g.drawImage(RessourceManager.getFontifiedText("DEX:", "standard"), 550, 170, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.dex, "standard");
		g.drawImage(statsImage, 617 - statsImage.getWidth(), 170, null);
		
		g.drawImage(RessourceManager.getFontifiedText("LUK:", "standard"), 550, 182, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.luk, "standard");
		g.drawImage(statsImage, 617 - statsImage.getWidth(), 182, null);
	}
	
	private void renderPlayerBars(Graphics g){
		BufferedImage hpBar = RessourceManager.getImage("ui.inventory.hpbar");
		int hpRestWidth = (int)(hpBar.getWidth() * ((double)(GameVariables.playerBattleStats.hp / (double)GameVariables.playerBattleStats.maxHp)));
		hpBar = hpBar.getSubimage(hpBar.getWidth() - hpRestWidth, 0, hpRestWidth, hpBar.getHeight());
		g.drawImage(hpBar, 552, 222, null);
		
		BufferedImage mpBar = RessourceManager.getImage("ui.inventory.mpbar");
		int mpRestWidth = (int)(mpBar.getWidth() * ((double)(GameVariables.playerBattleStats.mp / (double)GameVariables.playerBattleStats.maxMp)));
		mpBar = mpBar.getSubimage(mpBar.getWidth() - mpRestWidth, 0, mpRestWidth, mpBar.getHeight());
		g.drawImage(mpBar, 552, 252, null);
		
		BufferedImage expBar = RessourceManager.getImage("ui.inventory.expbar");
		
		int expRestWidth = (int)(expBar.getWidth() * (((double)(GameVariables.playerBattleStats.lastExpToNextLevel - GameVariables.playerBattleStats.expToNextLevel)) / ((double)GameVariables.playerBattleStats.lastExpToNextLevel)));
		if(expRestWidth > 0){
			expBar = expBar.getSubimage(expBar.getWidth() - expRestWidth, 0, expRestWidth, expBar.getHeight());
			g.drawImage(expBar, 552, 282, null);
		}
	}
	
	private void renderPlayerLeveling(Graphics g){
		BufferedImage levelImage;
		
		g.drawImage(RessourceManager.getFontifiedText("Level:", "standard"), 550, 295, null);
		levelImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.level, "standard");
		g.drawImage(levelImage, 610 - levelImage.getWidth(), 295, null);
		
		g.drawImage(RessourceManager.getFontifiedText("Next:", "standard"), 620, 295, null);
		levelImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.expToNextLevel, "standard");
		g.drawImage(levelImage, 694 - levelImage.getWidth(), 295, null);
	}
	
	private void renderSelectedItemInformation(Graphics g){
		BaseItem selectedItem = Inventory.getInventoryArrayList().get(1);
		
		if(selectedItem != null){
			BufferedImage name = RessourceManager.getFontifiedText(selectedItem.name, "standard");
			g.drawImage(name, 588, 313, null);
			
			g.drawImage(selectedItem.icon, 553, 313, null);
			
			BufferedImage text = RessourceManager.getFontifiedBreakingText(selectedItem.getDescription(), "standard", 140);
			g.drawImage(text, 550, 350, null);
		}
	}
	
	private void renderItemFields(Graphics g){
		int x = 0, y = 0;
		for(BaseItem i:Inventory.getInventoryArrayList()){
			g.drawImage(i.icon, 120 + x * 38, 114 + y * 38, null);
			
			x++;
			if(x == xRow){
				y++;
				x = 0;
			}
			
			if(y == yRow)break;
		}
	}
}

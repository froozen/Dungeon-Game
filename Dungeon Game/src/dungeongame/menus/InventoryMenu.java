package dungeongame.menus;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import dungeongame.GameInput;
import dungeongame.GameStateManager;
import dungeongame.GameVariables;
import dungeongame.Inventory;
import dungeongame.RessourceManager;
import dungeongame.GameStateManager.GameStateFocus;
import dungeongame.basetypes.items.BaseItem;
import dungeongame.states.HUDState;
import dungeongame.stats.HumanStats;

public class InventoryMenu implements BaseMenu{
	private final int xRow = 11, yRow = 10;

	private BufferedImage backgroundImage;
	private Point selectedItemPoint;
	private BaseItem selectedItem;

	public InventoryMenu(){
		backgroundImage = RessourceManager.getImage("ui.inventory.background");
		selectedItem = Inventory.getInventoryArrayList().get(1);
		selectedItemPoint = new Point(0, 0);
	}

	public void drawMe(Graphics g) {
		g.drawImage(backgroundImage, 100, 100, null);

		renderPlayerStats(g);
		renderPlayerBars(g);
		renderPlayerLeveling(g);
		renderSelectedItemInformation(g);
		renderItemFields(g);
		renderEquipmentIcons(g);
	}

	public void computeNextFrame() {
		if(GameInput.wasKeyPressed("Menu")){
			GameStateManager.changeForegroundState(new HUDState());
			GameStateManager.changeGameStateFocus(GameStateFocus.BACKGROUND);
		}
		else if (GameInput.wasKeyPressed("Up") || GameInput.wasKeyPressed("Down") || GameInput.wasKeyPressed("Left") || GameInput.wasKeyPressed("Right")){
			if(GameInput.wasKeyPressed("Up")){
				selectedItemPoint.y --;
				if(selectedItemPoint.y < 0)selectedItemPoint.y = yRow - 1;
			}
			else if(GameInput.wasKeyPressed("Down")){
				selectedItemPoint.y ++;
				if(selectedItemPoint.y == yRow)selectedItemPoint.y = 0;
			}
			else if(GameInput.wasKeyPressed("Right")){
				selectedItemPoint.x ++;
				if(selectedItemPoint.x == xRow)selectedItemPoint.x = 0;
			}
			else if(GameInput.wasKeyPressed("Left")){
				selectedItemPoint.x --;
				if(selectedItemPoint.x < 0)selectedItemPoint.x = xRow - 1;
			}

			selectedItem = Inventory.getItem(selectedItemPoint.y * xRow + selectedItemPoint.x);
		}
		else if(GameInput.wasKeyPressed("Action")){
			if(selectedItem != null){
				selectedItem.use();
				selectedItem = Inventory.getItem(selectedItemPoint.y * xRow + selectedItemPoint.x);
			}
		}
	}

	private void renderPlayerStats(Graphics g){
		BufferedImage statsImage;

		// TODO Update stats display
		g.drawImage(RessourceManager.getFontifiedText("ATK:", "standard"), 550, 122, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.getAtk(), "standard");
		g.drawImage(statsImage, 617 - statsImage.getWidth(), 122, null);

		g.drawImage(RessourceManager.getFontifiedText("DEF:", "standard"), 550, 134, null);
		statsImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.getDef(), "standard");
		g.drawImage(statsImage, 617 - statsImage.getWidth(), 134, null);
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

		BufferedImage hpText = RessourceManager.getFontifiedText(GameVariables.playerBattleStats.hp + " / " + GameVariables.playerBattleStats.maxHp, "outline");
		int hpTextX = 622 - (hpText.getWidth() / 2);
		g.drawImage(hpText, hpTextX, 228, null);
		
		BufferedImage mpText = RessourceManager.getFontifiedText(GameVariables.playerBattleStats.mp + " / " + GameVariables.playerBattleStats.maxMp, "outline");
		int mpTextX = 622 - (mpText.getWidth() / 2);
		g.drawImage(mpText, mpTextX, 258, null);
		
//		BufferedImage expBar = RessourceManager.getImage("ui.inventory.expbar");
//
//		int expRestWidth = (int)(expBar.getWidth() * (((double)(GameVariables.playerBattleStats.lastExpToNextLevel - GameVariables.playerBattleStats.expToNextLevel)) / ((double)GameVariables.playerBattleStats.lastExpToNextLevel)));
//		if(expRestWidth > 0){
//			expBar = expBar.getSubimage(expBar.getWidth() - expRestWidth, 0, expRestWidth, expBar.getHeight());
//			g.drawImage(expBar, 552, 282, null);
//		}
	}

	private void renderPlayerLeveling(Graphics g){
//		BufferedImage levelImage;
//
//		g.drawImage(RessourceManager.getFontifiedText("Level:", "standard"), 550, 295, null);
//		levelImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.level, "standard");
//		g.drawImage(levelImage, 610 - levelImage.getWidth(), 295, null);
//
//		g.drawImage(RessourceManager.getFontifiedText("Next:", "standard"), 620, 295, null);
//		levelImage = RessourceManager.getFontifiedText("" + GameVariables.playerBattleStats.expToNextLevel, "standard");
//		g.drawImage(levelImage, 694 - levelImage.getWidth(), 295, null);
	}

	private void renderSelectedItemInformation(Graphics g){		
		if(selectedItem != null){
			if(!(selectedItem.getDescription() == null || selectedItem.getDescription().equals(""))){
				BufferedImage text = RessourceManager.getFontifiedBreakingText(selectedItem.getDescription(), "standard", 140);
				g.drawImage(text, 550, 350, null);
			}
			if(!(selectedItem.name == null || selectedItem.name.equals(""))){
				BufferedImage name = RessourceManager.getFontifiedText(selectedItem.name, "standard");
				g.drawImage(name, 588, 313, null);
			}
			if(selectedItem.icon != null)g.drawImage(selectedItem.icon, 553, 313, null);
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

		g.drawImage(RessourceManager.getImage("ui.inventory.selector"), 116 + selectedItemPoint.x * 38, 110 + selectedItemPoint.y * 38, null);
	}
	
	private void renderEquipmentIcons(Graphics g){
		HumanStats playerStats = GameVariables.playerBattleStats;
		
		if(playerStats.getWeapon() != null)g.drawImage(playerStats.getWeapon().icon, 623, 147, null);
		if(playerStats.getHelmet() != null)g.drawImage(playerStats.getHelmet().icon, 661, 109, null);
		if(playerStats.getArmor() != null)g.drawImage(playerStats.getArmor().icon, 661, 147, null);
		if(playerStats.getPants() != null)g.drawImage(playerStats.getPants().icon, 661, 185, null);

	}
}

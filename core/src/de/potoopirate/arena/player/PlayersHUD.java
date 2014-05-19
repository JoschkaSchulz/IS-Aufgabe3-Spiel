package de.potoopirate.arena.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.potoopirate.arena.utils.ResourceLoader;

public class PlayersHUD {
	
	private Player mPlayer1;
	private Player mPlayer2;
	
	public PlayersHUD(Player player1, Player player2) {
		mPlayer1 = player1;
		mPlayer2 = player2;
	}
	
	public void draw(SpriteBatch batch) {
		//Player 1
//		batch.draw(ResourceLoader.ICON_KIGHT, 224, 128);
//		batch.draw(ResourceLoader.ICON_MAGE, 96, 32);
		batch.draw(ResourceLoader.ICON_IDLE, 224-46, 64);
//		batch.draw(ResourceLoader.ICON_ARCHER, 352, 32);
		batch.draw(ResourceLoader.CIRCLE, 64, 2);
		switch(mPlayer1.getCursor()) {
			case Player.CURSOR_KNIGHT:
				batch.draw(ResourceLoader.CURSOR, 224-46+122, 18);
				break;
			case Player.CURSOR_MAGE:
				batch.draw(ResourceLoader.CURSOR, 224-46-112, 20);
				break;
			case Player.CURSOR_ARCHER:
				batch.draw(ResourceLoader.CURSOR, 224-46, 15+128);
				break;
			default:
			case Player.CURSOR_IDLE:
				batch.draw(ResourceLoader.CURSOR, 224-46, 64);
				break;
		}
		
		//Player 2
//		batch.draw(ResourceLoader.ICON_KIGHT, 768+224, 128);
//		batch.draw(ResourceLoader.ICON_MAGE, 768+96, 32);
		batch.draw(ResourceLoader.ICON_IDLE, 800+227, 64);
//		batch.draw(ResourceLoader.ICON_ARCHER, 768+352, 32);
		batch.draw(ResourceLoader.CIRCLE, Gdx.graphics.getWidth()-64-ResourceLoader.CIRCLE.getRegionWidth(), 2);
		switch(mPlayer2.getCursor()) {
			case Player.CURSOR_KNIGHT:
				batch.draw(ResourceLoader.CURSOR, 768+224+157, 18);
				break;
			case Player.CURSOR_MAGE:
				batch.draw(ResourceLoader.CURSOR, 768+96+51, 20);
				break;
			case Player.CURSOR_ARCHER:
				batch.draw(ResourceLoader.CURSOR, 800+227, 15+64+64);
				break;
			default:
			case Player.CURSOR_IDLE:
				batch.draw(ResourceLoader.CURSOR, 800+227, 64);
				break;
		}
		
		ResourceLoader.FONT.draw(batch, "Punkte: " + mPlayer1.getPoints(), 10, 196);
		ResourceLoader.FONT.draw(batch, "Punkte: " + mPlayer2.getPoints(), Gdx.graphics.getWidth()-100, 196);
	}
}

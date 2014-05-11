package de.potoopirate.arena.player;

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
		batch.draw(ResourceLoader.ICON_KIGHT, 224, 256);
		batch.draw(ResourceLoader.ICON_MAGE, 96, 128);
		batch.draw(ResourceLoader.ICON_IDLE, 224, 128);
		batch.draw(ResourceLoader.ICON_ARCHER, 352, 128);
		switch(mPlayer1.getCursor()) {
			case Player.CURSOR_KNIGHT:
				batch.draw(ResourceLoader.CURSOR, 224, 256);
				break;
			case Player.CURSOR_MAGE:
				batch.draw(ResourceLoader.CURSOR, 96, 128);
				break;
			case Player.CURSOR_ARCHER:
				batch.draw(ResourceLoader.CURSOR, 352, 128);
				break;
			default:
			case Player.CURSOR_IDLE:
				batch.draw(ResourceLoader.CURSOR, 224, 128);
				break;
		}
		
		//Player 2
		batch.draw(ResourceLoader.ICON_KIGHT, 768+224, 256);
		batch.draw(ResourceLoader.ICON_MAGE, 768+96, 128);
		batch.draw(ResourceLoader.ICON_IDLE, 768+224, 128);
		batch.draw(ResourceLoader.ICON_ARCHER, 768+352, 128);
		switch(mPlayer2.getCursor()) {
			case Player.CURSOR_KNIGHT:
				batch.draw(ResourceLoader.CURSOR, 768+224, 256);
				break;
			case Player.CURSOR_MAGE:
				batch.draw(ResourceLoader.CURSOR, 768+96, 128);
				break;
			case Player.CURSOR_ARCHER:
				batch.draw(ResourceLoader.CURSOR, 768+352, 128);
				break;
			default:
			case Player.CURSOR_IDLE:
				batch.draw(ResourceLoader.CURSOR, 768+224, 128);
				break;
		}
	}
}

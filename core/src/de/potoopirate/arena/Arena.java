package de.potoopirate.arena;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.potoopirate.arena.game.Game;
import de.potoopirate.arena.utils.ResourceLoader;

public class Arena extends com.badlogic.gdx.Game {
	
	private SpriteBatch mBatch;
	private Game mGame;
	
	public SpriteBatch getBatch() {
		return mBatch;
	}
	 
    public void create() {
		ResourceLoader.load();
        mBatch = new SpriteBatch();
        
        mGame = new Game(this);
        
        this.setScreen(mGame);
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        mBatch.dispose();
    }
}

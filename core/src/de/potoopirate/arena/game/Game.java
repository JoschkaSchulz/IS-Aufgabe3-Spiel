package de.potoopirate.arena.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import de.potoopirate.arena.Arena;
import de.potoopirate.arena.game.Clock.IClockListener;
import de.potoopirate.arena.player.Player;
import de.potoopirate.arena.player.PlayersHUD;
import de.potoopirate.arena.utils.ResourceLoader;

public class Game implements Screen,IClockListener{
	
	private Clock mClock;
	private Player mPlayer1;
	private Player mPlayer2;
	private PlayersHUD mPlayersHud;
	private Arena mArena;
	
	public Game(Arena arena) {
		mPlayer1 = new Player(Player.PLAYER_1);
		mPlayer2 = new Player(Player.PLAYER_2);
		mPlayersHud = new PlayersHUD(mPlayer1, mPlayer2);
		
		mClock = new Clock(this, mPlayer1, mPlayer2);
		
		mArena = arena;
	}

	public void act(float delta) {
		mClock.act(delta);
		mPlayer1.act(delta);
		mPlayer2.act(delta);
	}
	
	@Override
	public void clockAction() {
		mPlayer1.resetCursor();
		mPlayer2.resetCursor();
		System.out.println("Clock action!");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //act
        this.act(delta); 
        
        //draw
        mArena.getBatch().begin();
		//Backgrounds
        mArena.getBatch().draw(ResourceLoader.LEFT_PLAYER_BACKGROUND, 0, 0);
        mArena.getBatch().draw(ResourceLoader.RIGHT_PLAYER_BACKGROUND, 640, 0);
        
        //Font test
        ResourceLoader.FONT.draw(mArena.getBatch(), "<fonttest>Das Spiel kann beginnen</fonttest>",
        		500, 500);

        //Players Hud
        mPlayersHud.draw(mArena.getBatch());
        
        //Clock
        mClock.draw(mArena.getBatch());
		mArena.getBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}

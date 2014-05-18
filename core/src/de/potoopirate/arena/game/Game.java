package de.potoopirate.arena.game;

import sun.font.GlyphLayout.LayoutEngineFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.arena.Arena;
import de.potoopirate.arena.game.Clock.IClockListener;
import de.potoopirate.arena.player.Player;
import de.potoopirate.arena.player.PlayersHUD;
import de.potoopirate.arena.utils.ResourceLoader;

public class Game implements Screen,IClockListener{
	
	public static final Vector2 POSITION_LEFT_FIGHT = new Vector2(507, 540);
	public static final Vector2 POSITION_RIGHT_FIGHT = new Vector2(645, 540);
	
	private ShapeRenderer mDebugRenderer;
	
	private Fight mFight;
	
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
		mFight = new Fight(this, mPlayer1, mPlayer2);
		
		mDebugRenderer = new ShapeRenderer();
		
		mArena = arena;
	}

	public void act(float delta) {
		mClock.act(delta);
		mFight.act(delta);
		mPlayer1.act(delta);
		mPlayer2.act(delta);
	}
	
	public void startClock() {
		mClock.resumeClock();
	}
	
	@Override
	public void clockAction() {
		mPlayer1.clockAction();
		mPlayer1.resetCursor();
		mPlayer2.clockAction();
		mPlayer2.resetCursor();
		mFight.clockAction();
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
        mArena.getBatch().draw(ResourceLoader.FIELD[0], 0, 0);
        mArena.getBatch().draw(ResourceLoader.FIELD[1], 640, 0);
        mArena.getBatch().draw(ResourceLoader.RING, 408, 450);
        
        //Draw Player
        mPlayer1.draw(mArena.getBatch());
        mPlayer2.draw(mArena.getBatch());
        mFight.draw(mArena.getBatch());
        
		//HUD Backgrounds
        mArena.getBatch().draw(ResourceLoader.LEFT_PLAYER_BACKGROUND, 0, 0);
        mArena.getBatch().draw(ResourceLoader.RIGHT_PLAYER_BACKGROUND, 773, 0);
        
        //Players Hud
        mPlayersHud.draw(mArena.getBatch());
        
        //Clock
        mClock.draw(mArena.getBatch());
        
		mArena.getBatch().end();
		
		//Debug Renderer
		mDebugRenderer.begin(ShapeType.Line);
		mDebugRenderer.setColor(1f, 0f, 0f, 1f);
		mDebugRenderer.rect(POSITION_LEFT_FIGHT.x, POSITION_LEFT_FIGHT.y, 128, 256);
		mDebugRenderer.rect(POSITION_RIGHT_FIGHT.x, POSITION_RIGHT_FIGHT.y, 128, 256);
		mDebugRenderer.end();
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

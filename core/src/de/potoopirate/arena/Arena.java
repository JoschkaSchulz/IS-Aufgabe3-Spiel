package de.potoopirate.arena;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.potoopirate.arena.unit.Unit;
import de.potoopirate.arena.unit.Unit.IUnitCallback;
import de.potoopirate.arena.utils.ResourceLoader;

public class Arena extends ApplicationAdapter implements IUnitCallback {
	private SpriteBatch mBatch;
	private Stage mStage;
	
	private DummyUnit mDummyUnit;
	private class DummyUnit extends Unit {

		public DummyUnit(IUnitCallback unitCallback) {
			super(unitCallback);
		}
		
	}
	
	@Override
	public void create () {
		ResourceLoader.load();
		mBatch = new SpriteBatch();
		mStage = new Stage();
		
		mDummyUnit = new DummyUnit(this);
		mDummyUnit.moveTo(512,400,5f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mStage.act(Gdx.graphics.getDeltaTime());
		mDummyUnit.act(Gdx.graphics.getDeltaTime());
		
		mBatch.begin();
		mDummyUnit.draw(mBatch);
		mBatch.end();
		mStage.draw();
		
		if(Gdx.input.isTouched()) {
			mDummyUnit.moveTo(Gdx.input.getX()-64, Gdx.graphics.getHeight()-64-Gdx.input.getY(), 0.5f);
		}
	}

	@Override
	public void unitStopped(Unit unit) {
		System.out.println("Unit has ended its movement!");
	}
}

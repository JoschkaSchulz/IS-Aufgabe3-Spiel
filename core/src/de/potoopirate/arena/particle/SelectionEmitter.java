package de.potoopirate.arena.particle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import de.potoopirate.arena.utils.ResourceLoader;

public class SelectionEmitter {
	private ParticleEffectPool bombEffectPool;
	private Array<PooledEffect> effects = new Array();

	public SelectionEmitter() {
		
		ParticleEffect bombEffect = new ParticleEffect();
		bombEffect.load(Gdx.files.internal("particle/ring.p"), ResourceLoader.PARTICLE_SELECTION_EMITTER);
		bombEffectPool = new ParticleEffectPool(bombEffect, 1, 2);
		
		PooledEffect effect = bombEffectPool.obtain();
		effect.setPosition(100, 100);
		effects.add(effect);
	}
	
	public void setPosition(int x, int y) {
		effects.get(0).setPosition(x, y);
	}
	
	public void draw(SpriteBatch batch) {
		for (int i = effects.size - 1; i >= 0; i--) {
		    PooledEffect effect = effects.get(i);
		    effect.draw(batch, Gdx.graphics.getDeltaTime());
		    if (effect.isComplete()) {
		        effect.free();
		        effects.removeIndex(i);
		    }
		}
	}
	
	public void resetEffect() {
		for (int i = effects.size - 1; i >= 0; i--)
		    effects.get(i).free();
		effects.clear();
	}
}

package de.potoopirate.arena.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import de.potoopirate.arena.minmax.MinMaxNode;
import de.potoopirate.arena.minmax.TreeFactory;


public class ComputerKI extends Player {

	private static final int KI_LEVEL = 7;
	
	private class TreeThread extends Thread {

		@Override
		public void run() {
			super.run();
			mTree = TreeFactory.buildTree(KI_LEVEL, mCurrentNode.getLeafes().get(0));
			mCurrentNode = mTree;
		}
		
	}
	
	private Player mPlayer;
	private MinMaxNode mTree;
	private MinMaxNode mCurrentNode;
	private TreeThread mTreeThread;
	
	public ComputerKI(int playerNumber, Player other) {
		super(playerNumber);
		mPlayer = other;
		mTree = TreeFactory.buildTree(4);
		mTreeThread = new TreeThread();
		mCurrentNode = mTree;
	}

	@Override
	public void clockAction() {
		System.out.println("=> " + mCurrentNode.getPointsComputer());
		if(mPlayer.getmCursor() != CURSOR_IDLE) {
			mCurrentNode = mCurrentNode.getNext(mPlayer.getCursor());
			mCurrentNode = mCurrentNode.getBest();
			System.out.println(mCurrentNode.toString());
		}
		setmCursor(mCurrentNode.getChoose());
		
		super.clockAction();

//		mCurrentNode = mCurrentNode.getNode();
		
		if(mCurrentNode.getDeep() <= 2) {
			mTreeThread = new TreeThread();
			mTreeThread.start();
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	
//		System.out.println("DEEP: " + mCurrentNode.getDeep() + "/ Leafes: ");// + mCurrentNode.getLeafes().size());
		
//		if(!ismChoiseBlocked()) {
//			switch(mPlayer.getCursor()) {
//				case CURSOR_KNIGHT:
//					setmCursor(CURSOR_ARCHER);
//					break;
//				case CURSOR_ARCHER:
//					setmCursor(CURSOR_MAGE);
//					break;
//				case CURSOR_MAGE:
//					setmCursor(CURSOR_KNIGHT);
//					break;
//				case CURSOR_IDLE:
//					setmCursor(CURSOR_IDLE);
//					break;
//			}
//		}
	}
}

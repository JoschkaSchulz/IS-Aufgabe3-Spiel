package de.potoopirate.arena.player;

import de.potoopirate.arena.minmax.MinMaxNode;
import de.potoopirate.arena.minmax.TreeFactory;


public class ComputerKI extends Player {

	private static final int KI_LEVEL = 7;
	
	private class TreeThread extends Thread {

		@Override
		public void run() {
			super.run();
			mCurrentNode = TreeFactory.extendTree(KI_LEVEL, mCurrentNode.getLeafes().get(0));
		}	
	}
	
	private Player mPlayer;
	private MinMaxNode mCurrentNode;
	private TreeThread mTreeThread;
	
	public ComputerKI(int playerNumber, Player other) {
		super(playerNumber);
		mPlayer = other;
		mTreeThread = new TreeThread();
		mCurrentNode = TreeFactory.buildTree(4);
	}

	
	@Override
	public void restartGame() {
		super.restartGame();
		
		mCurrentNode = TreeFactory.buildTree(6);
	}



	@Override
	public void clockAction() {
//		System.out.println("auswahl => " + mPlayer.getLastCursor());
//		if(mPlayer.getLastCursor() != CURSOR_IDLE) {
			System.out.println("~~~");
			mCurrentNode = mCurrentNode.getNext(mPlayer.getLastCursor());
			System.out.println("Spieler: " + mCurrentNode.toString());

			System.out.println("auswahlen: " + mCurrentNode.getNext(CURSOR_ARCHER));
			System.out.println("auswahlen: " + mCurrentNode.getNext(CURSOR_KNIGHT));
			System.out.println("auswahlen: " + mCurrentNode.getNext(CURSOR_MAGE));
			System.out.println("auswahlen: " + mCurrentNode.getNext(CURSOR_IDLE));
			mCurrentNode = mCurrentNode.getBest();
			System.out.println("Computer: " + mCurrentNode.toString());
//		}else{
//			System.out.println("nichts: " + mCurrentNode.toString());
//		}
		setCursor(mCurrentNode.getChoose());
		
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

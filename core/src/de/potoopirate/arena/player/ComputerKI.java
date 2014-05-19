package de.potoopirate.arena.player;


public class ComputerKI extends Player {

	private Player mPlayer;
	
	public ComputerKI(int playerNumber, Player other) {
		super(playerNumber);
		mPlayer = other;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(!ismChoiseBlocked()) {
			switch(mPlayer.getCursor()) {
				case CURSOR_KNIGHT:
					setmCursor(CURSOR_ARCHER);
					break;
				case CURSOR_ARCHER:
					setmCursor(CURSOR_MAGE);
					break;
				case CURSOR_MAGE:
					setmCursor(CURSOR_KNIGHT);
					break;
				case CURSOR_IDLE:
					setmCursor(CURSOR_IDLE);
					break;
			}
		}
	}
}

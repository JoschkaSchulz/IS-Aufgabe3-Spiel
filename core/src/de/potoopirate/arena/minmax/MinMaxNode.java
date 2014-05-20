package de.potoopirate.arena.minmax;

import java.util.ArrayList;

import de.potoopirate.arena.player.Player;

public class MinMaxNode {
	private static final int POINTS = 50;
	
	private String mPlayerQueue;
	private String mComputerQueue;
	private int mChoose;
	private boolean isPlayerTurn;
	private ArrayList<MinMaxNode> mNodes;
	private int mPointsPlayer;
	private int mPointsComputer;

	public MinMaxNode(int choose, boolean isPlayerTurn, String playerQueue,
			String computerQueue) {
		mPlayerQueue = playerQueue;
		mComputerQueue = computerQueue;
		mChoose = choose;
		mNodes = new ArrayList<MinMaxNode>();
		mPointsPlayer = 0;
		mPointsComputer = 0;

		this.isPlayerTurn = isPlayerTurn;
		if (this.isPlayerTurn) {
			fight();
			System.out.println("Kampf: " + toString());
		}
		addChooseUnit();
	}
	
	public String getPlayerQueue() {
		return mPlayerQueue;
	}
	
	public String getComputerQueue() {
		return mComputerQueue;
	}
	
	public void addNode(MinMaxNode node) {
		node.mPointsComputer = mPointsComputer;
		node.mPointsPlayer = mPointsPlayer;
		mNodes.add(node);
	}
	
	private void addChooseUnit() {
		if(isPlayerTurn) {
			if(!mPlayerQueue.isEmpty() && mChoose != Player.CURSOR_IDLE) {
				mPlayerQueue += ",";
			}
			
			switch(mChoose) {
				case Player.CURSOR_ARCHER:
					mPlayerQueue += "A3";
					break;
				case Player.CURSOR_KNIGHT:
					mPlayerQueue += "K3";
					break;
				case Player.CURSOR_MAGE:
					mPlayerQueue += "M3";
					break;
			}
		}else{
			if(!mComputerQueue.isEmpty() && mChoose != Player.CURSOR_IDLE) {
				mComputerQueue += ",";
			}
			
			switch(mChoose) {
				case Player.CURSOR_ARCHER:
					mComputerQueue += "A3";
					break;
				case Player.CURSOR_KNIGHT:
					mComputerQueue += "K3";
					break;
				case Player.CURSOR_MAGE:
					mComputerQueue += "M3";
					break;
			}
		}
	}

	private void fight() {
		char pUnit = ' ';
		int pLife = 0;
		String[] playerUnits;

		char cUnit = ' ';
		int cLife = 0;
		String[] computerUnits;

		if (!mPlayerQueue.isEmpty()) {
			playerUnits = mPlayerQueue.split(",");
			pUnit = playerUnits[0].charAt(0);
			pLife = Integer.parseInt(playerUnits[0].substring(1, 2));
		} else {
			playerUnits = new String[0];
		}

		if (!mComputerQueue.isEmpty()) {
			computerUnits = mComputerQueue.split(",");
			cUnit = computerUnits[0].charAt(0);
			cLife = Integer.parseInt(computerUnits[0].substring(1, 2));
		} else {
			computerUnits = new String[0];
		}

		switch (pUnit) {
		case 'M':
			switch (cUnit) {
			case 'A':
				pLife -= 1;
				cLife -= 2;
				break;
			case 'K':
				pLife -= 2;
				cLife -= 1;
				break;
			case ' ':
			default:
				break;
			}
			break;
		case 'A':
			switch (cUnit) {
			case 'M':
				pLife -= 2;
				cLife -= 1;
				break;
			case 'K':
				pLife -= 1;
				cLife -= 2;
				break;
			case ' ':
			default:
				break;
			}
			break;
		case 'K':
			switch (cUnit) {
			case 'M':
				pLife -= 1;
				cLife -= 2;
				break;
			case 'A':
				pLife -= 2;
				cLife -= 1;
				break;
			case ' ':
			default:
				break;
			}
			break;
		case ' ':
		default:
			break;
		}

		mPlayerQueue = "";
		for (int i = 0; i < playerUnits.length; i++) {
			if (i > 0) {
				mPlayerQueue += playerUnits[i];
				if (i + 1 < playerUnits.length) {
					mPlayerQueue += ",";
				}
			}
		}

		if (pLife > 0) {
			mPlayerQueue += (playerUnits.length>1?",":"") + String.valueOf(pUnit) + pLife;
		} else {
			mPointsComputer += 10;
			if(mPointsComputer >= POINTS) {
				mPointsComputer = Integer.MAX_VALUE;
			}
		}

		mComputerQueue = "";
		for (int i = 0; i < computerUnits.length; i++) {
			if (i > 0) {
				mComputerQueue += computerUnits[i];
				if (i + 1 < computerUnits.length) {
					mComputerQueue += ",";
				}
			}
		}

		if (cLife > 0) {
			mComputerQueue += (computerUnits.length>1?",":"") + String.valueOf(cUnit) + cLife;
		} else {
			mPointsPlayer += 10;
			if (mPointsPlayer >= POINTS)
				mPointsComputer = Integer.MIN_VALUE;
		}

	}

	@Override
	public String toString() {
		return mPlayerQueue + "/" + mComputerQueue;
	}
}

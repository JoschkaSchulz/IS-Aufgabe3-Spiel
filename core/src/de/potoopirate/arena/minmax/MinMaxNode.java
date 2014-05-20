package de.potoopirate.arena.minmax;

import java.util.ArrayList;
import java.util.Arrays;

import de.potoopirate.arena.unit.Unit;

public class MinMaxNode {
	private String mPlayerQueue;
	private String mComputerQueue;
	private int mChoose;
	private boolean isPlayerTurn;
	private ArrayList<MinMaxNode> mNodes;
	private int mPointsPlayer;
	private int mPointsComputer;
	
	public MinMaxNode(int choose, boolean isPlayerTurn, String playerQueue, String computerQueue) {
		mPlayerQueue = playerQueue;
		mComputerQueue = computerQueue;
		mChoose = choose;
		mNodes = new ArrayList<MinMaxNode>();
		mPointsPlayer = 0;
		mPointsComputer = 0;
		
		this.isPlayerTurn = isPlayerTurn;
		if(this.isPlayerTurn) {
			fight();
			System.out.println("Kampf: " + toString());
		}
	}
	
	private void fight() {
		char pUnit = ' ';
		int pLife = 0;
		String[] playerUnits;
		
		char cUnit = ' ';
		int cLife = 0;
		String[] computerUnits;
		
		if(!mPlayerQueue.isEmpty()) {
			playerUnits = mPlayerQueue.split(",");
			pUnit = playerUnits[0].charAt(0);	
			pLife = Integer.parseInt(playerUnits[0].substring(1, 2));
		}else{
			playerUnits = new String[0];
		}
		
		if(!mComputerQueue.isEmpty()) {
			computerUnits = mComputerQueue.split(",");
			cUnit = computerUnits[0].charAt(0);	
			cLife = Integer.parseInt(computerUnits[0].substring(1, 2));
		}else{
			computerUnits = new String[0];
		}
		
		switch(pUnit) {
			case 'M':
				switch(cUnit) {
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
				switch(cUnit) {
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
				switch(cUnit) {
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
		for(int i = 0; i < playerUnits.length; i++) {
			if(i > 0) {
				mPlayerQueue += playerUnits[i];
			}
			if(i < playerUnits.length) {
				mPlayerQueue += ",";
			}
		}
		
		if(pLife > 0) {
			mPlayerQueue += pUnit + pLife;
		}else{
			mPointsComputer += 10;
		}
		
		mComputerQueue = "";
		for(int i = 0; i < computerUnits.length; i++) {
			if(i > 0) {
				mComputerQueue += playerUnits[i];
			}
			if(i < computerUnits.length) {
				mComputerQueue += ",";
			}
		}
		
		if(pLife > 0) {
			mComputerQueue += pUnit + pLife;
		}else{
			mPointsPlayer += 10;
			if(mPointsPlayer >= 50) mPointsComputer -= Integer.MAX_VALUE;
		}
		
		
	}
	
	@Override
	public String toString() {
		return mPlayerQueue + "/" + mComputerQueue;
	}
}

package de.potoopirate.arena.minmax;

import java.util.ArrayList;

public class MinMaxNode {
	
	private int mPoints;
	private int mCursor;
	private ArrayList<MinMaxNode> mNodes;
	
	private MinMaxNode(int points, int cursor) {
		mPoints = points;
		mCursor = cursor;
		mNodes = new ArrayList<MinMaxNode>();
		
		System.out.println("Neuer Knoten erstellt mit " + mPoints + "Punkten");
	}
	
	public MinMaxNode getBestChoise() {
		return null;
	}
	
	public void addNode(MinMaxNode node) {
		mNodes.add(node);
	}
	
	public static MinMaxNode createMinMaxNode(int points, int cursor) {
		return new MinMaxNode(points, cursor);
	}
}

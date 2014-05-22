package de.potoopirate.arena.minmax;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;

import de.potoopirate.arena.player.Player;
import de.potoopirate.arena.player.UnitQueue;
import de.potoopirate.arena.unit.Archer;
import de.potoopirate.arena.unit.Knight;
import de.potoopirate.arena.unit.Mage;
import de.potoopirate.arena.unit.Unit;

public class TreeFactory {
	public static MinMaxNode extendTree(int level, MinMaxNode root) {
		MinMaxNode idleNode;
		MinMaxNode archerNode;
		MinMaxNode knightNode;
		MinMaxNode mageNode;
		boolean isPlayerTurn = false;
		
		if(level > 0) {
			for(MinMaxNode node : root.getLeafes()) {
				isPlayerTurn = !node.isPlayerTurn();
				idleNode = new MinMaxNode(Player.CURSOR_IDLE, !isPlayerTurn, node.getPlayerQueue(), node.getComputerQueue());
				node.addNode(idleNode);
				buildTree(level-1, idleNode);
				
				archerNode = new MinMaxNode(Player.CURSOR_ARCHER, !isPlayerTurn, node.getPlayerQueue(), node.getComputerQueue());
				node.addNode(archerNode);
				buildTree(level-1, archerNode);
				
				mageNode = new MinMaxNode(Player.CURSOR_MAGE, !isPlayerTurn, node.getPlayerQueue(), node.getComputerQueue());
				node.addNode(mageNode);
				buildTree(level-1, mageNode);
				
				knightNode = new MinMaxNode(Player.CURSOR_KNIGHT, !isPlayerTurn, node.getPlayerQueue(), node.getComputerQueue());
				node.addNode(knightNode);
				buildTree(level-1, knightNode);
			}
		}
		
		return root;
	}
	
	public static MinMaxNode buildTree(int level, MinMaxNode root) {
		MinMaxNode idleNode;
		MinMaxNode archerNode;
		MinMaxNode knightNode;
		MinMaxNode mageNode;
		
		if(level > 0) {
			for(MinMaxNode node : root.getLeafes()) {
				idleNode = new MinMaxNode(Player.CURSOR_IDLE, !node.isPlayerTurn(), node.getPlayerQueue(), node.getComputerQueue());
				node.addNode(idleNode);
				buildTree(level-1, idleNode);
				
				archerNode = new MinMaxNode(Player.CURSOR_ARCHER, !node.isPlayerTurn(), node.getPlayerQueue(), node.getComputerQueue());
				node.addNode(archerNode);
				buildTree(level-1, archerNode);
				
				mageNode = new MinMaxNode(Player.CURSOR_MAGE, !node.isPlayerTurn(), node.getPlayerQueue(), node.getComputerQueue());
				node.addNode(mageNode);
				buildTree(level-1, mageNode);
				
				knightNode = new MinMaxNode(Player.CURSOR_KNIGHT, !node.isPlayerTurn(), node.getPlayerQueue(), node.getComputerQueue());
				node.addNode(knightNode);
				buildTree(level-1, knightNode);
			}
		}
		
		return root;
	}
	
	public static MinMaxNode buildTree(int level) {
		MinMaxNode root = new MinMaxNode(-1, false, "", "");
		level *= 2;
		
		return buildTree(level+1, root);
	}
}

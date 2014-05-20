package de.potoopirate.arena.minmax;

import java.util.ArrayList;

import de.potoopirate.arena.player.Player;
import de.potoopirate.arena.player.UnitQueue;
import de.potoopirate.arena.unit.Archer;
import de.potoopirate.arena.unit.Knight;
import de.potoopirate.arena.unit.Mage;
import de.potoopirate.arena.unit.Unit;

public class TreeFactory {
	public static MinMaxNode buildTree(int level, MinMaxNode root) {
		
		
		
		return root;
	}
	
	public static MinMaxNode buildTree(int level) {
		MinMaxNode root = new MinMaxNode(-1, false, "", "");
		return buildTree(level, root);
	}
}

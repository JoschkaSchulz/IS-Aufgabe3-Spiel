package de.potoopirate.arena.minmax;

import java.util.ArrayList;

import de.potoopirate.arena.player.Player;
import de.potoopirate.arena.player.UnitQueue;
import de.potoopirate.arena.unit.Archer;
import de.potoopirate.arena.unit.Knight;
import de.potoopirate.arena.unit.Mage;
import de.potoopirate.arena.unit.Unit;

public class NodeFactory {
	public static MinMaxNode generateTree(UnitQueue queue1, int cursor, UnitQueue queue2, int level) {
		MinMaxNode root = MinMaxNode.createMinMaxNode(0, -1);
		
		for(int i = 0; i < level; i++) {
			
		}
		
		return root;
	}
	
	public static MinMaxNode generateNode(UnitQueue queue1, int cursor, UnitQueue queue2) {
		//init points
		int points = 0;
		
		//Create two empty queues
		UnitQueue uQueue1 = new UnitQueue(queue1);
		UnitQueue uQueue2 = new UnitQueue(queue2);
		
		//Adding the cursor unit to one of the queues
		switch(cursor) {
			default:
			case Player.CURSOR_IDLE:
				break;
			case Player.CURSOR_ARCHER:
				uQueue1.add(new Archer(null));
				break;
			case Player.CURSOR_KNIGHT:
				uQueue1.add(new Knight(null));
				break;
			case Player.CURSOR_MAGE:
				uQueue1.add(new Mage(null));
				break;
		}
		
		//calculate the points of the selection
		if(uQueue1.getSize() > uQueue2.getSize()) {
			for(int i = 0; i < uQueue1.getSize(); i++) {
				points += unitValue(uQueue1.getUnitAtPosition(i), uQueue2.getUnitAtPosition(i%uQueue2.getSize()));
			}
		}else if(uQueue1.getSize() < uQueue2.getSize()) {
			for(int i = 0; i < uQueue2.getSize(); i++) {
				points += unitValue(uQueue1.getUnitAtPosition(i%uQueue1.getSize()), uQueue2.getUnitAtPosition(i));
			}
		}else{
			for(int i = 0; i < uQueue1.getSize(); i++) {
				points += unitValue(uQueue1.getUnitAtPosition(i), uQueue2.getUnitAtPosition(i));
			}
		}
		
		return MinMaxNode.createMinMaxNode(points, cursor);
	}
	
	private static int unitValue(Unit attackUnit, Unit defenseUnit) {
		if(attackUnit instanceof Knight) {
			if(defenseUnit instanceof Knight) {
				return 0;
			}else if(defenseUnit instanceof Archer) {
				return -1;
			}else if(defenseUnit instanceof Mage){
				return 1;
			}
		}else if(attackUnit instanceof Archer){
			if(defenseUnit instanceof Knight) {
				return 1;
			}else if(defenseUnit instanceof Archer) {
				return 0;
			}else if(defenseUnit instanceof Mage){
				return -1;
			}
		}else if(attackUnit instanceof Mage){
			if(defenseUnit instanceof Knight) {
				return -1;
			}else if(defenseUnit instanceof Archer) {
				return 1;
			}else if(defenseUnit instanceof Mage){
				return 0;
			}
		}
			
		return 0;
	}
}

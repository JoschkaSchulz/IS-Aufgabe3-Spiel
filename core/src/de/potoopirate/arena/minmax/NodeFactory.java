package de.potoopirate.arena.minmax;

import de.potoopirate.arena.player.UnitQueue;
import de.potoopirate.arena.unit.Archer;
import de.potoopirate.arena.unit.Knight;
import de.potoopirate.arena.unit.Mage;
import de.potoopirate.arena.unit.Unit;

public class NodeFactory {
	public static MinMaxNode generateNode(UnitQueue queue1, int cursor, UnitQueue queue2) {
		int points = 0;
		
			if(queue1.getSize() > queue2.getSize()) {
				for(int i = 0; i < queue1.getSize(); i++) {
					points += unitValue(queue1.getUnitAtPosition(i), queue2.getUnitAtPosition(i%queue2.getSize()));
				}
			}else if(queue1.getSize() < queue2.getSize()) {
				for(int i = 0; i < queue2.getSize(); i++) {
					points += unitValue(queue1.getUnitAtPosition(i%queue1.getSize()), queue2.getUnitAtPosition(i));
				}
			}else{
				for(int i = 0; i < queue1.getSize(); i++) {
					points += unitValue(queue1.getUnitAtPosition(i), queue2.getUnitAtPosition(i));
				}
			}
		
		return MinMaxNode.createMinMaxNode();
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

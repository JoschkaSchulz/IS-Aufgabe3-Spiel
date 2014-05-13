package de.potoopirate.arena.player;

public class NoUnitException extends Exception {
	public NoUnitException(String msg){ 
      super(msg); 
    } 

    public NoUnitException(String msg, Throwable t){ 
      super(msg,t); 
    } 
}

package es.upm.pproject.sokoban.interfaces;

public interface Square {
	
	// Returns true it can move towards that direction
	public abstract boolean move(char way);
	
}
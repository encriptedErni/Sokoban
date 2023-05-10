package es.upm.pproject.sokoban.interfaces;

public abstract class Square {
	
	// Returns true it it can move towards that direction
	abstract boolean move(int way);
	
}
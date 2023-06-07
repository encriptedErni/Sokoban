package es.upm.pproject.sokoban.interfaces;

public interface Square {
	
	// Returns true it can move towards that direction
	public boolean move(char way, int turn);
	public void unmove(char way, int turn);
}
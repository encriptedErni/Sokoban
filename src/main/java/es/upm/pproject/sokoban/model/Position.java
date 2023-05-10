package es.upm.pproject.sokoban.model;

public class Position implements Comparable<Position>{

	private int x,y;
	
	public Position(int x,int y) {
		this.x = x;
		this.y =y; 
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public int compareTo(Position o) {
		if (o.getX() == this.x && o.getY() == this.y) {
			return 0;
		}
		return -1;
	}
}

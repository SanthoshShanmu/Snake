package app;

import javafx.geometry.Bounds;

public class Point {
	private int x;
	private int y;
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public Point(Point p){
		this(p.x, p.y);
	}

	public Point(){
		this(0, 0);
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
	
	public void translate(int a, int b) {
		x += a;
		y += b;
	}
	
	public boolean inBounds(Bounds bounds) {
		return x >= bounds.getMinX() && y >= bounds.getMinY() && x < bounds.getMaxX() && y < bounds.getMaxY();
	}

	@Override
	public boolean equals(Object ting) {
		if (this == ting)
			return true;
		if (ting == null)
			return false;
		if (!(ting instanceof Point))
			return false;
		Point other = (Point) ting;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
		
	public String toString() {
        return x + ", " + y;
    }
}

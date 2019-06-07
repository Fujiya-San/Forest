package forest;

import java.awt.Graphics;

public class Branch extends Object {

	private Node start;

	private Node end;

	public void Branch(Node from, Node to) {
		this.start = from;
		this.end = to;
	}

	public void draw(Graphics aGraphics) {
		Integer startX = this.start.getLocation().x + this.start.getExtent().x;
		Integer startY = this.start.getLocation().y + (this.start.getExtent().y / 2);
		Integer endX = this.start.getLocation().x;
		Integer endY = this.end.getLocation().y + (this.end.getExtent().y / 2);
		aGraphics.drawLine(startX, startY, endX, endY);
	}

	public Node start() {
		return this.start;
	}

	public Node end() {
		return this.end;
	}

	public String toString() {
		return null;
	}

}

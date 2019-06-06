package forest;

import java.awt.Graphics;

public class Branch extends Object {

	private Node start;

	private Node end;

	public void Branch(Node from, Node to) {
		start = from;
		end = to;
	}

	public void draw(Graphics aGraphics) {
		Integer startX = this.start.getLocation().x + this.start.getExtent().x;
		Integer startY = this.start.getLocation().y + (this.start.getExtent().y / 2);
		Integer endX = this.start.getLocation().x;
		Integer endY = this.end.getLocation().y + (this.end.getExtent().y / 2);
		aGraphics.drawLine(startX, startY, endx, endY);
	}

	public Node start() {
		return start;
	}

	public Node end() {
		return end;
	}

	public String toString() {
		return null;
	}

}

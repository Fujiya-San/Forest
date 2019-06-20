package forest;

import java.awt.Graphics;

public class Branch extends Object {

	private Node start;

	private Node end;

	/**
	 * インスタンスを生成する。
	 * @param from 元のノード(親)
	 * @param to 新たなノード(子)
	 */
	public void Branch(Node from, Node to) {
		this.start = from;
		this.end = to;
	}

	/**
	 * 描写メソッド
	 * @param aGraphics 描画引数
	 */
	public void draw(Graphics aGraphics) {
		// Integer startX = this.start.getLocation().x + this.start.getExtent().x;
		// Integer startY = this.start.getLocation().y + (this.start.getExtent().y / 2);
		// Integer endX = this.start.getLocation().x;
		// Integer endY = this.end.getLocation().y + (this.end.getExtent().y / 2);
		double startX = this.start.getBounds().getX() + this.start.getBounds().getWidth();
		double startY = this.start.getBounds().getY() + (this.start.getBounds().getHeight() / 2);
		double endX = this.start.getBounds().getX();
		double endY = this.end.getBounds().getY() + (this.end.getExtent().getY() / 2);
		aGraphics.drawLine((int)startX, (int)startY, (int)endX, (int)endY);
	}

	/**
	 * 元ノードを応答
	 * @return 元ノード
	 */
	public Node start() {
		return this.start;
	}

	/**
	 * 新たなノード
	 * @return 新たなノード
	 */
	public Node end() {
		return this.end;
	}

	public String toString() {
		return null;
	}

}

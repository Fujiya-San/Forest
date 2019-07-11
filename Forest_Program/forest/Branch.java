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
	public Branch(Node from, Node to) {
		this.start = from;
		this.end = to;
	}

	/**
	 * 描写メソッド
	 * @param aGraphics 描画引数
	 */
	public void draw(Graphics aGraphics) {
		Integer startX = this.start.getBounds().x + this.start.getBounds().width;
		Integer startY = this.start.getBounds().y + (this.start.getBounds().height / 2);
		Integer endX = this.end.getBounds().x;
		Integer endY = this.end.getBounds().y + (this.end.getExtent().y / 2);
		aGraphics.drawLine(startX, startY, endX, endY);
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

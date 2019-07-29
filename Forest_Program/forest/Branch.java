package forest;

import java.awt.Graphics;

/**
 * 樹状整列におけるブランチ（枝）を担うクラスになります。
 */
public class Branch extends Object {

	/**
	 * ブランチ（枝）の始点となるノードを記憶するフィールドです。
	 * 良好（2019/7/29）
	 */
	private Node start;

	/**
	 * ブランチ（枝）の終点となるノードを記憶するフィールドです。
	 * 良好（2019/7/29）
	 */
	private Node end;

	/**
	 * インスタンスを生成する。
	 * @param from 元のノード(親)
	 * @param to 新たなノード(子)
	 * 良好（2019/7/29）
	 */
	public Branch(Node from, Node to) {
		this.start = from;
		this.end = to;
		return;
	}

	/**
	 * 描写メソッド
	 * @param aGraphics 描画引数
	 * 良好（2019/7/29）
	 */
	public void draw(Graphics aGraphics) {
		Integer startX = this.start.getBounds().x + this.start.getBounds().width;
		Integer startY = this.start.getBounds().y + (this.start.getBounds().height / 2);
		Integer endX = this.end.getBounds().x;
		Integer endY = this.end.getBounds().y + (this.end.getExtent().y / 2);
		aGraphics.drawLine(startX, startY, endX, endY);
		return;
	}

	/**
	 * 元ノードを応答
	 * @return 元ノード
	 * 良好（2019/7/29）
	 */
	public Node start() {
		return this.start;
	}

	/**
	 * 新たなノード
	 * @return 新たなノード
	 * 良好（2019/7/29）
	 */
	public Node end() {
		return this.end;
	}

	/**
	 * 自分自身を文字列に変換するメソッドです。
	 * @return 自分自身を表す文字列
	 * 良好（2019/7/29）
	 */
	public String toString() {
		StringBuffer aBuffer = new StringBuffer();
		Class<?> aClass = this.getClass();
		aBuffer.append(aClass.getName());
		aBuffer.append(" [親ノード：");
		aBuffer.append(this.start.getName());
		aBuffer.append(", 子ノード：");
		aBuffer.append(this.end.getName());
		aBuffer.append("]");
		return aBuffer.toString();
	}

}

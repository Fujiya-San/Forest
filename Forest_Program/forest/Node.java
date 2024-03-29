package forest;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.FontMetrics;

/**
 * 樹状整列におけるノード（節）を担うクラスになります。
 */
@SuppressWarnings("serial")
public class Node extends Component
{
	/**
	 * ノード名：ラベル文字列を記憶するフィールドです。
	 * 良好 (7月29日)
	 */
	private String name;

	/**
	 * ノードの場所（位置：座標）を記憶するフィールドです。
	 * 良好 (7月29日)
	 */
	private Point location;

	/**
	 * ノードの大きさ（幅と高さ）を記憶するフィールドです。
	 * 良好 (7月29日)
	 */
	private Point extent;

	/**
	 * 樹状整列する際のノードの状態を記憶するフィールドです。
	 * 良好 (7月29日)
	 */
	private Integer status;

	/**
	 * ノードを囲む矩形を記憶するフィールドです。
	 * 良好 (7月29日)
	 */
	private Rectangle bounds;

	/**
	 * このクラスのインスタンスを生成するコンストラクタです。
	 * @param  aString ノード名：ラベル文字列
	 * 良好 (7月29日)
	 */
	public Node(String aString)
	{
		this.setName(aString);
		this.bounds = new Rectangle();
		this.setExtent(new Point(this.stringWidth(aString), this.stringHeight(aString)));
		return;
	}

	/**
	 * ノード（節）を描画するメソッドです。
	 * @param aGraphics グラフィクス（描画コンテクスト）
	 * 良好　(7月２９日)
	 */
	public void draw(Graphics aGraphics)
	{
		aGraphics.drawRect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
		aGraphics.drawString(this.name, this.location.x, this.location.y);
		return;
	}

	/**
	 * ノード（節）の描画領域を応答するメソッドです。
	 * @return ノード（節）の描画領域（Rectangleのインスタンス）
	 * 良好 (7月29日)
	 */
	public Rectangle getBounds()
	{
		return this.bounds;
	}

	/**
	 * ノード（節）の大きさを応答するメソッドです。
	 * @return ノード（節）の大きさ（幅と高さ）
	 * 良好 (7月29日)
	 */
	public Point getExtent()
	{
		return this.extent;
	}

	/**
	 * ノード（節）の位置を応答するメソッドです。
	 * @return ノード（節）の位置（座標）
	 * 良好 (7月29日)
	 */
	public Point getLocation()
	{
		return this.location;
	}

	/**
	 * ノード（節）の名前を応答するメソッドです。
	 * @return ノード名（ラベル文字列）
	 * 良好　(7月29日)
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * ノード（節）の状態を応答するメソッドです。
	 * @return ノードの状態
	 * 良好 (7月29日)
	 */
	public Integer getStatus()
	{
		return this.status;
	}

	/**
	 * ノード（節）の大きさを設定するメソッドです。
	 * @param aPoint ノードの大きさ（幅と高さ）
	 * 良好（2019/7/29）
	 */
	public void setExtent(Point aPoint)
	{
		this.extent = aPoint;
		this.bounds.setSize(aPoint.x+Constants.Margin.x*2, aPoint.y+Constants.Margin.y);
		return;
	}

	/**
	 * ノード（節）の位置を設定するメソッドです。
	 * @param aPoint ノードの位置（座標）
	 * 良好（2019/7/29）
	 */
	public void setLocation(Point aPoint)
	{
		this.location = aPoint;
		this.bounds.setLocation(aPoint.x-Constants.Margin.x, aPoint.y-this.stringHeight(this.name)+Constants.Margin.y);
		return;
	}

	/**
	 * ノード（節）の名前を設定するメソッドです。
	 * @param aString ノードの名前（ラベル）
	 * 良好 (7月29日)
	 */
	public void setName(String aString)
	{
		this.name = aString;
		return;
	}

	/**
	 * ノード（節）の状態を設定するメソッドです。
	 * @param anInteger ノードの状態
	 * 良好 (7月29日)
	 */
	public void setStatus(Integer anInteger)
	{
		this.status = anInteger;
		return;
	}

	/**
	 * 文字列の高さを応答するメソッドです。
	 * @param  string 文字列
	 * @return        文字列の高さ
	 * 良好 (7月29日)
	 */
	protected int stringHeight(String string)
	{
		FontMetrics aFontMetrics = super.getFontMetrics(Constants.DefaultFont);
		return aFontMetrics.getHeight();
	}

	/**
	 * 文字列の幅を応答するメソッドです。
	 * @param  string 文字列
	 * @return        文字列の幅
	 * 良好（2019/7/29）
	 */
	protected int stringWidth(String string)
	{
		FontMetrics aFontMetrics = super.getFontMetrics(Constants.DefaultFont);
		return aFontMetrics.stringWidth(string);
	}

	/**
	 * 自分自身を文字列に変換するメソッドです。
	 * @return 自分自身を表す文字列
	 * 良好 (7月29日)
	 */
	public String toString()
	{
		return this.name;
	}

}

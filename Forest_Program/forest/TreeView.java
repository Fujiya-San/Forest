package forest;

import java.awt.Graphics;
import java.awt.Point;
import mvc.View;

/**
 * 樹状整列におけるMVCのビュー（V）を担うクラスになります。
 */
@SuppressWarnings("serial")
public class TreeView extends View
{

	/**
	 * 情報を握っているTreeModelのインスタンスを束縛する。
	 * 良好 (7月29日)
	 */
	protected TreeModel model;

	/**
	 * 制御を司るTreeControllerのインスタンスを束縛する。
	 * 良好 (7月29日)
	 */
	protected TreeController controller;

	/**
	 * このクラスのインスタンスを生成するコンストラクタです。
	 * @param  aModel モデル（Modelのインスタンス）
	 * 良好 (7月29日)
	 */
	public TreeView(TreeModel aModel)
	{
		super(aModel);
		model = aModel;
		model.addDependent(this);
		controller = new TreeController();
		controller.setModel(model);
		controller.setView(this);
		return;
	}

	/**
	 * このパネル（ビュー）の描画が必要になったときに動作するメソッドです。
	 * @param aGraphics グラフィクス（描画コンテクスト）
	 * 良好 (7月29日)
	 */
	public void paintComponent(Graphics aGraphics)
	{
		aGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		this.model.forest().draw(aGraphics);
		return;
	}

	/**
	 * スクロール量を指定された座標分だけ相対スクロールする。
	 * @param aPoint X軸とY軸のスクロール量を表す座標
	 * 良好 (7月29日)
	 */
	public void scrollBy2(Point aPoint)
	{

		int x = aPoint.x;
		int y = aPoint.y;
		model.forest().moveBounds(new Point(x, y));
		this.model.forest().arrange();
		return;
	}

	/**
	 * 指定された位置（座標）にノードが存在するかを調べるメソッドです。
	 * @param  aPoint 位置（ビュー座標）
	 * @return ノード、もしも見つからなかった場合には、nullを応答します。
	 * 良好 (7月29日)
	 */
	public Node whichOfNodes(Point aPoint)
	{
		return model.forest().whichOfNodes(aPoint);
	}

}

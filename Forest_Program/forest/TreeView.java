package forest;

import java.awt.Graphics;
import java.awt.Point;
import mvc.View;

@SuppressWarnings("serial")
public class TreeView extends View
{
	protected TreeModel model;

	protected TreeController controller;

	// private Point offset;

	public TreeView(TreeModel aModel)
	{
		super(aModel);
		model = aModel;
		model.addDependent(this);
		controller = new TreeController();
		controller.setModel(model);
		controller.setView(this);
		// offset = new Point(0, 0);
		return;

	}

	public void paintComponent(Graphics aGraphics)
	{
		this.update();
		// this.setFont(Constants.DefaultFont);
		this.model.forest().draw(aGraphics);
	}

	public void scrollBy2(Point aPoint)
	{
		
		int x = aPoint.x;
		int y = aPoint.y;
		this.scrollTo2(new Point(x, y));
		this.model.forest().arrange();
		return;
	}


	/**
	 * スクロール量を指定された座標に設定（絶対スクロール）する。
	 * @param aPoint X軸とY軸の絶対スクロール量を表す座標
	 * 良好（2010年7月25日）
	 * 修正（2015年2月9日）
	 */
	public void scrollTo2(Point aPoint)
	{
		// model.forest().moveForest(aPoint);
		model.forest().moveBounds(aPoint);
		return;
	}

	
	public Node whichOfNodes(Point aPoint)
	{
		return model.forest().whichOfNodes(aPoint);
	}

}

package forest;

import java.awt.event.MouseEvent;
import mvc.Controller;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Objects;


public class TreeController extends Controller
{

	protected TreeModel model;

	protected TreeView view;

	private Point changePlace;

	private Point now;

	public TreeController()
	{
		super();
		model = null;
		view = null;
	}

	public void setModel(TreeModel aModel)
	{
		model = aModel;
		return;
	}

	public void mouseDragged(MouseEvent aMouseEvent)
	{
		ArrayList<Node> rootNodes = this.model.roots();
		for(Node aNode : rootNodes)
		{

			if(!(Objects.equals(aNode.getStatus(), Constants.Visited))) return;
			System.out.println(aNode.getStatus());
			
		}
		Cursor aCursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
		Component aComponent = (Component)aMouseEvent.getSource();
		aComponent.setCursor(aCursor);
		now = aMouseEvent.getPoint();
		int x = now.x - changePlace.x;
		int y = now.y - changePlace.y;
		Point point = new Point(x, y);
		view.scrollBy2(point);
		view.update();
		changePlace = now;
		return;
	}

	/**
	 * 指定されたビューをインスタンス変数viewに設定し、
	 * ビューのマウスのリスナおよびモーションリスナそしてホイールリスナをこのコントローラにする。
	 * @param aView このコントローラのビュー
	 * 良好（2010年7月25日）
	 * 修正（2015年2月9日）
	 */
	public void setView(TreeView aView)
	{
		view = aView;
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		view.addMouseWheelListener(this);
		return;
	}

	/**
	 * マウスカーサの形状を十字に変化させ、指定されたマウスイベントからマウスカーサの位置を獲得して、
	 * インスタンス変数nowに設定する共にインスタンス変数changePlaceをインスタンス変数nowに更新する。
	 * @param aMouseEvent マウスイベント
	 * 良好（2010年7月25日）
	 */
	public void mousePressed(MouseEvent aMouseEvent)
	{
		Cursor aCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
		Component aComponent = (Component)aMouseEvent.getSource();
		aComponent.setCursor(aCursor);
		now = aMouseEvent.getPoint();
		changePlace = now;
		return;
	}

	/**
	 * マウスクリック判定を行うメソッド
	 * @param aMouseEvent マウスのクリック情報
	 */
	public void mouseClicked(MouseEvent aMouseEvent)
	{
		Point aPoint = aMouseEvent.getPoint();
		// String aName = view.whichOfNodes(aPoint).getName();
		Node aNode = view.whichOfNodes(aPoint);
		if(aNode != null)
		{
			System.out.printf("%s%n", aNode.getName());
		}
	}

}

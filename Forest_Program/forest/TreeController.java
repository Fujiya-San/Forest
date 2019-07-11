package forest;

import java.awt.event.MouseEvent;
import mvc.Controller;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.Component;


public class TreeController extends Controller
{

	protected TreeModel model;

	protected TreeView view;

	private Point previous;

	private Point current;

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
		Cursor aCursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
		Component aComponent = (Component)aMouseEvent.getSource();
		aComponent.setCursor(aCursor);
		current = aMouseEvent.getPoint();
		int x = current.x - previous.x;
		int y = current.y - previous.y;
		Point point = new Point(x, y);
		view.scrollBy(point);
		view.update();
		previous = current;
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
	 * マウスクリック判定を行うメソッド
	 * @param aMouseEvent マウスのクリック情報
	 */
	public void mouseClicked(MouseEvent aMouseEvent)
	{
		Point aPoint = aMouseEvent.getPoint();
		String nodeName = view.whichOfNodes(aPoint).getName();
		if(nodeName != null)
		{
			System.out.printf("%s%n", nodeName);
		}
	}

}

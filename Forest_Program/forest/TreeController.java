package forest;

import java.awt.event.MouseEvent;
import mvc.Controller;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 樹状整列におけるMVCのコントローラ（C）を担うクラスになります。
 */
public class TreeController extends Controller
{

	/**
	 * 情報を握っているTreeModelのインスタンスを束縛する。
	 * 良好（2019/7/29）
	 */
	protected TreeModel model;

	/**
	 * 表示を司るTreeViewのインスタンスを束縛する。
	 * 良好（2019/7/29）
	 */
	protected TreeView view;

	/**
	 * 以前にマウスのボタンが押下された場所をPointのインスタンスとして束縛する。
	 * 良好（2019/7/29）
	 */
	private Point changePlace;

	/**
	 * 現在にマウスのボタンが押下された場所をPointのインスタンスとして束縛する。
	 * 良好（2019/7/29）
	 */
	private Point now;

	/**
	 * インスタンスを生成して応答する。
	 * すべてのインスタンス変数（model, view, changePlace, now）をnull化する。
	 * 良好(7月29日)
	 */
	public TreeController()
	{
		super();
		model = null;
		view = null;
		changePlace = null;
		now = null;
		return;
	}

	/**
	 * 指定されたモデルをインスタンス変数modelに設定する。
	 * @param aModel このコントローラのモデル
	 * 良好 (7月29日)
	 */
	public void setModel(TreeModel aModel)
	{
		model = aModel;
		return;
	}

	/**
	 * マウスカーサの形状を移動の形に変化させ、指定されたマウスイベントからマウスカーサの位置を獲得して、
	 * インスタンス変数changePlaceに設定すると共に、以前のマウスカーサの位置からの差分を計算する。
	 * そして、その差分だけビューに対してスクロールを依頼し、その後にビューの再描画を依頼する。
	 * 最後にインスタンス変数nowをインスタンス変数changePlaceに更新する。
	 * @param aMouseEvent マウスイベント
	 * 良好（2019/7/29）
	 */
	public void mouseDragged(MouseEvent aMouseEvent)
	{
		ArrayList<Node> rootNodes = this.model.roots();
		for(Node aNode : rootNodes)
		{
			if(!(Objects.equals(aNode.getStatus(), Constants.Visited))) return;
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
	 * 良好（2019/7/29）
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
	 * 良好　(7月29日)
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
	 * 良好 (7月29日)
	 */
	public void mouseClicked(MouseEvent aMouseEvent)
	{
		Point aPoint = aMouseEvent.getPoint();
		Node aNode = view.whichOfNodes(aPoint);
		if(aNode != null)
		{
			System.out.printf("%s%n", aNode.getName());
		}
		return;
	}
}

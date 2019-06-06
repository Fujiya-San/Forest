package forest;

import java.awt.event.MouseEvent;
import mvc.Controller;
import java.awt.Point;

public class TreeController extends Controller
{

	public TreeController()
	{
		super();
	}
	
	/**
	 * マウスクリック判定を行うメソッド
	 * @param aMouseEvent マウスのクリック情報
	 */
	public void mouseClicked(MouseEvent aMouseEvent)
	{
		Point aPoint = aMouseEvent.getPoint();
		aPoint.translate(view.scrollAmount().x, view.scrollAmount().y);
	}

}

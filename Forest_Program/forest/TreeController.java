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

	public void mouseClicked(MouseEvent aMouseEvent)
	{
		Point aPoint = aMouseEvent.getPoint();
		aPoint.translate(view.scrollAmount().x, view.scrollAmount().y);
	}

}

package forest;

import java.awt.Graphics;
import java.awt.Point;
import mvc.View;

@SuppressWarnings("serial")
public class TreeView extends View
{


	public TreeView(TreeModel aModel)
	{
		super(aModel, new TreeController());
	}

	public void paintComponent(Graphics aGraphics)
	{

	}

	public static Node whichOfNodes(Point aPoint)
	{
		new Forest().whichOfNodes(aPoint);
		return null;
	}

}

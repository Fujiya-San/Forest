package forest;

import java.awt.Graphics;
import java.awt.Point;
import mvc.View;

public class TreeView extends View
{


	public TreeView(TreeModel aModel)
	{
		super(aModel, new TreeController());
	}

	public void paintComponent(Graphics aGraphics)
	{

	}

	public Node whichOfNodes(Point aPoint)
	{
		return null;
	}

}

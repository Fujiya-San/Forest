package forest;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.FontMetrics;

@SuppressWarnings("serial")
public class Node extends Component
{
	private String name;
	
	private Point location;

	private Point extent;

	private Integer status;

	private Rectangle bounds;

	public Node(String aString)
	{
		this.setName(aString);
		this.bounds = new Rectangle();
		this.setExtent(new Point(this.stringWidth(aString), this.stringHeight(aString)));	
	}

	public void draw(Graphics aGraphics)
	{
		aGraphics.drawRect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
		aGraphics.drawString(this.name, this.location.x, this.location.y);
	}

	public Rectangle getBounds()
	{
		return this.bounds;
	}

	public Point getExtent()
	{
		return this.extent;
	}

	public Point getLocation()
	{
		return this.location;
	}

	public String getName()
	{
		return this.name;
	}

	public Integer getStatus()
	{
		return this.status;
	}

	public void setExtent(Point aPoint)
	{
		this.extent = aPoint;
		this.bounds.setSize(aPoint.x+Constants.Margin.x, aPoint.y+Constants.Margin.y);
	}

	public void setLocation(Point aPoint)
	{
		this.location = aPoint;
		this.bounds.setLocation(aPoint.x-Constants.Margin.x, aPoint.y-this.stringHeight(this.name));
	}

	public void setName(String aString)
	{
		this.name = aString;
	}

	public void setStatus(Integer anInteger)
	{
		this.status = anInteger;
	}


	protected int stringHeight(String string)
	{
		FontMetrics aFontMetrics = super.getFontMetrics(Constants.DefaultFont);
		return aFontMetrics.getHeight();
	}

	protected int stringWidth(String string)
	{
		FontMetrics aFontMetrics = super.getFontMetrics(Constants.DefaultFont);
		return aFontMetrics.stringWidth(string);
	}

	public String toString()
	{
		return this.name;
	}

}

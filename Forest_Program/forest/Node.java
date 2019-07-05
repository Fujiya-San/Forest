package forest;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Node extends Component
{

	private String name;

	// private Point location;
	private Point location;

	// private Point extent;
	private Point extent;

	// private Integer status;
	private Integer status;

	private Rectangle bounds;

	public Node(String aString)
	{

		this.setName(aString);
		// this.setExtent(new Point(this.stringWidth(aString), this.stringHeight(aString)));
		
		// Point aPoint = new Point(0, 0);
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

	// public void setBounds()
	// {
	// 	// Double x = new Double(this.location.getX()-Constants.Margin.getX());
	// 	// Double y = new Double(this.location.getY()-Constants.Margin.getY());
	// 	// Double width = new Double(this.extent.getX()+Constants.Margin.getX());
	// 	// Double height = new Double(this.extent.getY()+Constants.Margin.getY());
	// 	this.bounds = new Rectangle(this.location.x-Constants.Margin.x, 
	// 								this.location.y-Constants.Margin.y, 
	// 								this.extent.x+Constants.Margin.x, 
	// 								this.extent.y+Constants.Margin.y);
	// 	// this.bounds = new Rectangle(x.intValue(), y.intValue(), width.intValue(), height.intValue());
	// }

	protected int stringHeight(String string)
	{
		return Constants.DefaultFont.getSize();
		// return 0;
	}

	protected int stringWidth(String string)
	{
		return Constants.DefaultFont.getSize()/2*string.length();
		// return 0;
	}

	public String toString()
	{
		return this.name;
	}

}

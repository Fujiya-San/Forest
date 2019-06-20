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

	public void Node(String aString)
	{
		this.setName(aString);
	}

	public void draw(Graphics aGraphics)
	{
		aGraphics.drawRect((int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight());
		aGraphics.drawString(this.name, (int)this.location.getX(), (int)this.location.getY());
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
	}

	public void setLocation(Point aPoint)
	{
		this.location = aPoint;
	}

	public void setName(String aString)
	{
		this.name = aString;
	}

	public void setStatus(Integer anInteger)
	{
		this.status = anInteger;
	}

	public void setBounds()
	{
		Double x = new Double(this.location.getX()-Constants.Margin.getX());
		Double y = new Double(this.location.getY()-Constants.Margin.getY());
		Double width = new Double(this.extent.getX()+Constants.Margin.getX());
		Double height = new Double(this.extent.getY()+Constants.Margin.getY());
		// this.bounds = new Rectangle(this.location.getX()-Constants.Margin.getX(), 
		// 							this.location.getY()-Constants.Margin.getY(), 
		// 							this.extent.getX()+Constants.Margin.getX(), 
		// 							this.extent.getY()+Constants.Margin.getY());
		this.bounds = new Rectangle(x.intValue(), y.intValue(), width.intValue(), height.intValue());
	}

	protected int stringHeight(String string)
	{
		return Constants.DefaultFont.getSize();
	}

	protected int stringWidth(String string)
	{
		return Constants.DefaultFont.getSize()*string.length();
	}

	public String toString()
	{
		return null;
	}

}

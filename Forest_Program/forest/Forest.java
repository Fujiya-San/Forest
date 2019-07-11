package forest;

import java.util.Objects;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mvc.Model;

public class Forest extends Object {

	private ArrayList<Node> nodes = new ArrayList<>();

	private ArrayList<Node> rootNodesList = new ArrayList<>();

	private ArrayList<Branch> branches = new ArrayList<>();

	private Rectangle bounds = new Rectangle();

	// public void Forest() {
	// 	nodes = new ArrayList<Node>();
	// 	branches = new ArrayList<Branch>();
	// 	rootNodesList = new ArrayList<Node>();
	// 	bounds = new Rectangle();
	// }

	public ArrayList<Node> getNodes()
	{
		return this.nodes;
	}

	public void addBranch(Branch aBranch) {
		branches.add(aBranch);
	}

	public void addNode(Node aNode) {
		nodes.add(aNode);
	}

	public void arrange() {
		this.initNodeStatus();
		Consumer<Node> aConsumer = (Node aNode) -> { this.arrange(aNode, new Point(aNode.getLocation().x+this.bounds.x, aNode.getLocation().y+this.bounds.y), null); };
		this.rootNodesList.forEach( aConsumer );

	}

	public void arrange(TreeModel aModel) {
		this.bounds.setSize(0, Constants.DefaultFont.getSize());
		this.rootNodes();
		Consumer<Node> aConsumer = (Node aNode) -> { this.arrange(aNode, new Point(bounds.x, bounds.y+bounds.height), aModel); };
		rootNodesList.forEach( aConsumer );
	}

	protected Point arrange(Node aNode, Point aPoint,  TreeModel aModel) 
	{
		ArrayList<Node> subNodes = this.subNodes(aNode);
		subNodes = this.sortNodes(subNodes);

		aNode.setLocation(aPoint);

		if(aModel != null) this.propagate(aModel);
		
		if(this.bounds.x+this.bounds.width < aPoint.x-aNode.getBounds().width) this.bounds.setSize(aPoint.x+aNode.getBounds().x, this.bounds.height);
		if(this.bounds.y+this.bounds.height < aPoint.y) this.bounds.setSize(this.bounds.width, aPoint.y);

		Integer anIndex = 0;
		for(Node aSubNode : subNodes)
		{
			if(!Objects.equals(aSubNode.getStatus(), Constants.Visited) && anIndex == 0 && aModel != null)
			{
				this.arrange(aSubNode, new Point(aPoint.x+aNode.getBounds().width+Constants.Interval.x, this.bounds.y+this.bounds.height), aModel);
				anIndex++;
			}else if(!Objects.equals(aSubNode.getStatus(), Constants.Visited) && aModel != null)
			{
				this.arrange(aSubNode, new Point(aPoint.x+aNode.getBounds().width+Constants.Interval.x, this.bounds.y+this.bounds.height+aNode.getBounds().height+Constants.Interval.y), aModel);
				anIndex++;
			}else if(aModel == null)
			{
				this.arrange(aSubNode, new Point(aSubNode.getLocation().x+this.bounds.x, aSubNode.getLocation().y+this.bounds.y), aModel);
				anIndex++;
			}
			

		}
		
		if(aModel != null)
		{
			aNode.setLocation(new Point(aPoint.x, aPoint.y+((bounds.height-aPoint.y)/2)));
		}else
		{
			aNode.setLocation(new Point(aPoint.x+this.bounds.x, aPoint.y+this.bounds.y));
		}
		

		if(aModel != null) this.propagate(aModel);

		aNode.setStatus(Constants.Visited);

		return null;
	}

	public Rectangle bounds() 
	{
		return this.bounds;
	}

	public void draw(Graphics aGraphics) 
	{
		nodes.forEach(aNode -> aNode.draw(aGraphics));
		branches.forEach(aBranch -> aBranch.draw(aGraphics));
		aGraphics.drawRect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
	}

	public void flushBounds() 
	{
		bounds = new Rectangle();
	}

	protected void propagate(TreeModel aModel) 
	{
		
		try
		{
			Thread.sleep(Constants.SleepTick);
		}catch(InterruptedException e)
		{
			System.out.println(e);
		}
		aModel.changed();
		
	}

	public void moveBounds(Point aPoint)
	{
		this.bounds.setLocation(aPoint);
	}

	protected void initNodeStatus()
	{
		Consumer<Node> aConsumer = (Node aNode) -> { aNode.setStatus(Constants.UnVisited); };
		this.nodes.forEach(aConsumer);
	}

	public ArrayList<Node> rootNodes() 
	{
		Integer anIndex = 0;
		for(Node aNode : nodes)
		{
			anIndex = 0 ;
			for(Branch aBranch : branches)
			{
				if(Objects.equals(aNode.getName(), aBranch.end().getName())) break;
				else if(anIndex == branches.size()-1) this.rootNodesList.add(aNode);
				anIndex++;
			}
		}
		rootNodesList = this.sortNodes(rootNodesList);
		return null;
	}

	protected ArrayList<Node> sortNodes(ArrayList<Node> nodeCollection) 
	{
		nodeCollection.sort( (node1, node2) -> node1.getName().compareTo(node2.getName()) );
		return nodeCollection;
	}

	public ArrayList<Node> subNodes(Node aNode)
	{
		ArrayList<Node> subNodesList = new ArrayList<>();
		for(Branch aBranch : branches)
		{
			if(Objects.equals(aBranch.start(), aNode))
			{
				subNodesList.add(aBranch.end());
			}
		}
		return subNodesList;
	}

	public ArrayList<Node> superNodes(Node aNode) 
	{
		ArrayList<Node> superNodesList = new ArrayList<>();
		Branch[] branchesToSubNodes = (Branch[])(branches.stream().filter( aBranch -> Objects.equals(aBranch.end(), aNode) ).toArray());
		for(Branch aBranch : branchesToSubNodes)
		{
			superNodesList.add(aBranch.start());
		}
		return superNodesList;
	}

	public String toString() {
		return null;
	}

	public Node whichOfNodes(Point aPoint) 
	{
		Predicate<Node> aPredicate = (Node aNode) -> {return aNode.getBounds().contains(aPoint); };
		return nodes.stream().filter(aPredicate).findFirst().orElse(null);
		
	}

}

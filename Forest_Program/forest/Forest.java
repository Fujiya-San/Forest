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

	private List<Node> nodes = new ArrayList<>();

	private List<Node> rootNodesList = new ArrayList<>();

	private List<Branch> branches = new ArrayList<>();

	private Rectangle bounds;

	public void Forest() {
		nodes = new ArrayList<>();
		branches = new ArrayList<>();
		bounds = new Rectangle();
	}

	public void addBranch(Branch aBranch) {
		branches.add(aBranch);
	}

	public void addNode(Node aNode) {
		nodes.add(aNode);
	}

	public void arrange() {
		Consumer<Node> aConsumer = (Node aNode) -> { this.arrange(aNode, new Point(bounds.x, bounds.y+bounds.height), null); };
		rootNodesList.forEach( aConsumer );
	}

	public void arrange(TreeModel aModel) {
		Consumer<Node> aConsumer = (Node aNode) -> { this.arrange(aNode, new Point(bounds.x, bounds.y+bounds.height), aModel); };
		rootNodesList.forEach( aConsumer );
	}

	protected Point arrange(Node aNode, Point aPoint,  TreeModel aModel) 
	{
		List<Node> subNodes = this.subNodes(aNode);
		subNodes = this.sortNodes(subNodes);

		if(!Objects.equals(aNode.getStatus(), Constants.Visited)) aNode.setLocation(aPoint);

		if(aModel != null) this.propagate(aModel);
		
		if(bounds.x < aPoint.x) bounds.setSize(aPoint.x, bounds.height);
		if(bounds.y < aPoint.y) bounds.setSize(bounds.width, aPoint.y);

		Integer anIndex = 0;
		for(Node aSubNode : subNodes)
		{
			this.arrange(aSubNode, new Point(aPoint.x+aNode.getBounds().width+Constants.Interval.x, aPoint.y+(aNode.getBounds().height*anIndex)+(Constants.Interval.y*anIndex)), aModel);
			anIndex++;
		}
		
		if(!Objects.equals(aNode.getStatus(), Constants.Visited)) aNode.setLocation(new Point(aPoint.x, aPoint.y+((bounds.height-aPoint.y)/2)));

		if(aModel != null) this.propagate(aModel);

		aNode.setStatus(Constants.Visited);

		return null;
	}

	public Rectangle bounds() 
	{
		return bounds;
	}

	public void draw(Graphics aGraphics) 
	{
		nodes.forEach(aNode -> aNode.draw(aGraphics));
		branches.forEach(aBranch -> aBranch.draw(aGraphics));
	}

	public void flushBounds() 
	{
		bounds = new Rectangle();
	}

	protected void propagate(TreeModel aModel) 
	{
		aModel.changed();
		try
		{
			Thread.sleep(Constants.SleepTick);
		}catch(InterruptedException e)
		{
			System.out.println(e);
		}
		
	}

	public ArrayList<Node> rootNodes() 
	{
		// ArrayList<Node> rootNodesList = new ArrayList<>();
		Integer anIndex = 0;
		for(Node aNode : nodes)
		{
			anIndex = 0 ;
			for(Branch aBranch : branches)
			{
				if(Objects.equals(aNode, aBranch.end())) break;
				else if(anIndex == branches.size()-1) rootNodesList.add(aNode);
				anIndex++;
			}
			
		}
		rootNodesList = this.sortNodes(rootNodesList);
		return null;
	}

	protected List<Node> sortNodes(List<Node> nodeCollection) 
	{
		nodeCollection.sort( (node1, node2) -> node1.getName().compareTo(node2.getName()) );
		return nodeCollection;
	}

	public List<Node> subNodes(Node aNode)
	{
		Collection<Branch> aCollection = new Vector<Branch>();
		List<Node> subNodesList = new ArrayList<>();
		Branch[] branchesToSubNodes = (Branch[])(branches.stream().filter( aBranch -> Objects.equals(aBranch.start(), aNode) ).toArray());
		Consumer<Branch> aConsumer = (Branch aBranch) -> { subNodesList.add(aBranch.end()); };
		aCollection.forEach(aConsumer);
		// for(Branch aBranch : branches)
		// {
		// 	if(Objects.equals(aBranch.start(), aNode))
		// 	{
		// 		subNodesList.add(aBranch.end());
		// 	}
		// }
		System.out.println("System OK");
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
		// Predicate<Node> aPredicate = (Node aNode) -> {return (aNode.getLocation().getX() < aPoint.getX() && aNode.getLocation().getX()+aNode.getExtent().getX() > aPoint.getX() && aNode.getLocation().getY() < aPoint.getY() && aNode.getLocation().getY()+aNode.getExtent().getY() > aPoint.getY()); };
		Predicate<Node> aPredicate = (Node aNode) -> {return aNode.getBounds().contains(aPoint); };
		return nodes.stream().filter(aPredicate).findFirst().orElse(null);
		// return nodes.stream().filter(aNode -> aNode.getLocation().getX() < aPoint.getX() && 
		// 									 aNode.getLocation().getX()+aNode.getExtent().getX() > aPoint.getX() &&
		// 									 aNode.getLocation().getY() < aPoint.getY() &&
		// 									 aNode.getLocation().getY()+aNode.getExtent().getY() > aPoint.getY())
		// 					 .findFirst()
		// 					 .orElse(null);
		
	}

}

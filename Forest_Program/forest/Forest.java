package forest;

import java.util.Objects;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import mvc.Model;

public class Forest extends Object {

	private ArrayList<Node> nodes;

	private ArrayList<Branch> branches;

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

	}

	public void arrange(TreeModel aModel) {

	}

	protected Point arrange(Node aNode, Point aPoint,  TreeModel aModel) 
	{
		if(Objects.equals(aNode.getStatus(), Constants.Unvisited))
		{
			aNode.setLocation(aPoint.getX(), aPoint.getY());
		}

		this.propagate(aModel);

		this.subNodes(aNode).forEach(node -> this.arrange(node, new Point(aPoint.getX()+Constants.Interval.getX(), aPoint.getY()+Constants.Interval.getY()), aModel));


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
	}

	public ArrayList<Node> rootNodes() 
	{
		ArrayList<Node> rootNodesList = new ArrayList<>();
		Integer anIndex = 0;
		for(Node aNode : nodes)
		{
			anIndex = 0 ;
			for(Branch aBranch : branches)
			{
				if(Objects.equals(aNode, aBranch.end()))
				{
					break;
				}else if(anIndex == branches.size()-1)
				{
					rootNodesList.add(aNode);
				}
				anIndex++;
			}
			
		}
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
		Branch[] branchesToSubNodes = (Branch[])(branches.stream().filter( aBranch -> Objects.equals(aBranch.start(), aNode) ).toArray());
		for(Branch aBranch : branchesToSubNodes)
		{
			subNodesList.add(aBranch.end());
		}
		// for(Branch aBranch : branches)
		// {
		// 	if(Objects.equals(aBranch.start(), aNode))
		// 	{
		// 		subNodesList.add(aBranch.end());
		// 	}
		// }
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
		return nodes.stream().filter(aNode -> aNode.getLocation().getX() < aPoint.getX() && 
											 aNode.getLocation().getX()+aNode.getExtent().getX() > aPoint.getX() &&
											 aNode.getLocation().getY() < aPoint.getY() &&
											 aNode.getLocation().getY()+aNode.getExtent().getY() > aPoint.getY())
							 .findFirst()
							 .orElse(null);
	}

}

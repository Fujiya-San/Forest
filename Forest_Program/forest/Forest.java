package forest;

import java.util.Objects;
import java.util.Vector;
import java.util.function.Consumer;
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

	}

	public void arrange(TreeModel aModel) {

	}

	protected Point arrange(Node aNode, Point aPoint,  TreeModel aModel) 
	{
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
		return null;
	}

	protected ArrayList<Node> sortNodes(ArrayList<Node> nodeCollection) 
	{
		nodeCollection.sort( (node1, node2) -> node1.getName().compareTo(node2.getName()) );
		return nodeCollection;
	}

	public ArrayList<Node> subNodes(Node aNode)
	{
		Collection<Branch> aCollection = new Vector<Branch>();
		ArrayList<Node> subNodesList = new ArrayList<>();
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
		return nodes.stream().filter(aNode -> aNode.getLocation().getX() < aPoint.getX() && 
											 aNode.getLocation().getX()+aNode.getExtent().getX() > aPoint.getX() &&
											 aNode.getLocation().getY() < aPoint.getY() &&
											 aNode.getLocation().getY()+aNode.getExtent().getY() > aPoint.getY())
							 .findFirst()
							 .orElse(null);
	}

}

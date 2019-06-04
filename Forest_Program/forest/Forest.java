package forest;

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

	}

	public void addBranch(Branch aBranch) {

	}

	public void addNode(Node aNode) {

	}

	public void arrange() {

	}

	public void arrange(TreeModel aModel) {

	}

	protected Point arrange(Node aNode, Point aPoint,  TreeModel aModel) {
		return null;
	}

	public Rectangle bounds() {
		return null;
	}

	public void draw(Graphics aGraphics) {

	}

	public void flushBounds() {

	}

	protected void propagate(TreeModel aModel) {

	}

	public ArrayList<Node> rootNodes() {
		return null;
	}

	protected ArrayList<Node> sortNodes(ArrayList<Node> nodeCollection) {
		return null;
	}

	public ArrayList<Node> subNodes(Node aNode) {
		return null;
	}

	public ArrayList<Node> superNodes(Node aNode) {
		return null;
	}

	public String toString() {
		return null;
	}

	public Node whichOfNodes(Point aPoint) {
		return null;
	}

}

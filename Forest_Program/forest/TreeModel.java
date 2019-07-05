package forest;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.awt.Point;
import mvc.Model;

public class TreeModel extends Model {

	private Forest forest;

	protected ArrayList<TreeView> dependents;

	public TreeModel(File aFile)
	{

		super();
		this.dependents = new ArrayList<TreeView>();
		this.forest = new Forest();
		this.read(aFile);
		// this.forest.getNodes().forEach((Node aNode) -> { System.out.println(aNode.getName());});
		// this.initialize();
		return;
	}

	public void addDependent(TreeView aView)
	{
		dependents.add(aView);
		return;
	}


   /**
	* アニメーションを行うメソッドです。
	*
    */
	public void animate()
	{
		// Iterator<Forest> anIteratorF = dependents.iterator();
	 //  while(anIteratorF.hasnext())
	 //  {
	 //   Forest aForest = anIteratorF.next(); 
	 //   //Forestのメッセージ送信の受け取りかた？　送信のしかた?を知りたい
	   
	 //  }
	 	
	 	this.arrange();
	 	System.out.println("aaaaaaa");
	}

	private void initialize()
	{
		dependents = new ArrayList<TreeView>();
		forest = null;
		return;
	}

	
	/**
	 * 樹状整列を行うメソッドです。
	 */
	public void arrange()
	{
	    this.forest.arrange(this);
	}
    /**
	 * モデルの内部状態が変化していたので、自分の依存物へupdateのメッセージを送信する。
	 */
	public void changed()
	{
		Iterator<TreeView> anIteratorTV = dependents.iterator();
		while (anIteratorTV.hasNext())
		{
			TreeView aView = anIteratorTV.next();
			aView.update();
		}
		return;

		// Iterator<TreeView> anIteratorTV = dependents.iterator();
		// while (anIteratorTV.hasNext())
		// {
		// 	TreeView aView = anIteratorTV.next();
		// 	aView.paintComponent(aView.getGraphics());
		// 	// aView.update();
		// }
		dependents.forEach((TreeView aView) -> { aView.paintComponent(aView.getGraphics());});
		return;

	}
	/**
	 * 樹状整列をそれ自身を応答するメソッドです。
	 * @return
	 */
	public Forest forest()
	{
    	return this.forest;
	}
	/**
	 * 樹状整列をそれ自身を応答するメソッドです。
	 * @return
	 */
	public Forest forest()
	{
    	return this.forest;
	}

	/**
	 * 樹状整列をそれ自身を応答するメソッドです。
	 * @param aFile
	 */
	protected void read(File aFile)
	{

		try
		{
			Integer treesDataFlag = 0;
			Integer nodesDataFlag = 0;
			Integer branchesDataFlag = 0;
			BufferedReader in = new BufferedReader(new FileReader(aFile));
			HashMap<String, Node> nodeMap = new HashMap<>();
			String line;
		
	
			while((line = in.readLine()) != null)
			{
				String[] data = line.split(", ");

				// System.out.println(line);
				// System.out.println(data[0]);
				if(Objects.equals(line, Constants.TagOfTrees))
				{
					treesDataFlag = 1;
					nodesDataFlag = 0;
					branchesDataFlag = 0;
				}else if(Objects.equals(line, Constants.TagOfNodes))
				{
					treesDataFlag = 0;
					nodesDataFlag = 1;
					branchesDataFlag = 0;
				}else if(Objects.equals(line, Constants.TagOfBranches))
				{
					// System.out.println("Objects.equals(line, Constants.TagOfBranches) : " + Objects.equals(line, Constants.TagOfBranches));
					treesDataFlag = 0;
					nodesDataFlag = 0;
					branchesDataFlag = 1;
				}else
				{
					// System.out.println(data[0]);
					// System.out.println("Objects.equals(line, Constants.TagOfBranches) : " + Objects.equals(line, Constants.TagOfBranches));
					// System.out.println("nodesDataFlag : " + nodesDataFlag);
					if(nodesDataFlag == 1)
					{

						Node aNode = new Node(data[1]);
						aNode.setLocation(new Point(0, Integer.valueOf(data[0])*Constants.DefaultFont.getSize()));
						aNode.setStatus(Constants.UnVisited);
						nodeMap.put(data[0], aNode);
						// System.out.print
						// System.out.println(nodeMap.get(data[0]).getName());
						this.forest.addNode(aNode);
						// System.out.println(data[0] + ", " + data[1]);
					}
					// System.out.println("Objects.equals(line, Constants.TagOfBranches) : " + Objects.equals(line, Constants.TagOfBranches));
					// System.out.println("branchesDataFlagaaaa : " + branchesDataFlag);
					if(branchesDataFlag == 1)
					{
						// System.out.println("data[0] : " + data[0] + ", data[1] : " + data[1]);
						// System.out.println(nodeMap.get(data[0]).getName() + ", " + nodeMap.get(data[1]).getName());
						// System.out.println("setBranch : " + nodeMap.get(data[0]) + ", " + nodeMap.get(data[1]));
						this.forest.addBranch(new Branch(nodeMap.get(data[0]), nodeMap.get(data[1])));
					}

					// System.out.println("Objects.equals(line, Constants.TagOfBranches) : " + Objects.equals(line, Constants.TagOfBranches));
				
				}

				// if(data.length > 1)
				// {
				// 	System.out.println(data[1]);
				// }


				
				// System.out.println("branchesDataFlag : " + branchesDataFlag);
			}	
		}catch(Exception e)
		{
			e.printStackTrace();
		}

  		return;
	}
    /**
     * 樹状整列の根源(ルート)になるノードを探し出して応答するメソッドです。
     * @return
     */
	public Node root()
	{
		return null;
	}
	/**
	 * 樹状整列の根源(ルート)になるノードたちを探し出して応答するメソッドです。
	 * @return
	 */
	public ArrayList<Node> roots() {
		return null;
	}

}

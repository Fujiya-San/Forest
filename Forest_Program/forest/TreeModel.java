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

/**
* 樹状整列におけるMVCのモデル（M）を担うクラスになります。
*/

public class TreeModel extends Model {

	/**
	* 樹状整列それ自身を記憶しておくフィールドです。
	* 良好 (7月29日)
	*/
	private Forest forest;

	/**
	* 依存物 TreeViewのインスタンスたちを束縛する。
	* 良好　(7月29日)
	*/
	protected ArrayList<TreeView> dependents;


	/**
	* このクラスのインスタンスを生成するコンストラクタです。
	* @param  aFile 樹状整列データファイル
	* 良好　(7月29日)
	*/
	public TreeModel(File aFile)
	{
		super();
		this.dependents = new ArrayList<TreeView>();
		this.forest = new Forest();
		this.read(aFile);
		return;
	}


	/**
	* 指定されたビューを依存物に設定する
	* @param aView このモデルの依存物となるビュー
	* 良好　(7月29日)
	*/
	public void addDependent(TreeView aView)
	{
		dependents.add(aView);
		return;
	}


	/**
	* アニメーションを行うメソッドです。
	* 良好 (7月29日)
	*/
	public void animate()
	{
		this.arrange();
		// System.out.println(this.forest.toString());
		return;
	}

	/**
	* 初期化する。
	* 良好 (7月29日)
	*/
	private void initialize()
	{
		dependents = new ArrayList<TreeView>();
		forest = null;
		return;
	}

	/**
	* 樹状整列を行うメソッドです。
	* 良好　(7月29日)
	*/
	public void arrange()
	{
		this.forest.arrange(this);
		return;
	}

	/**
	* モデルの内部状態が変化していたので、自分の依存物へupdateのメッセージを送信する。
	* 良好　(7月29日)
	*/
	public void changed()
	{
		dependents.forEach((TreeView aView) -> { aView.paintComponent(aView.getGraphics());});
		return;
	}

	/**
	* 樹状整列をそれ自身を応答するメソッドです。
	* @return 樹状整列それ自身
	* 良好　(7月29日)
	*/
	public Forest forest()
	{
		return this.forest;
	}

	/**
	* 樹状整列をそれ自身を応答するメソッドです。
	* @param aFile
	* 修正　(7月29日)
	* 良好　(7月29日)
	*/
	protected void read(File aFile)
	{
		try
		{
			Boolean treesDataFlag = false;
			Boolean nodesDataFlag = false;
			Boolean branchesDataFlag = false;
			Boolean isNumberFlag = true;
			StringBuffer aBuffer = new StringBuffer();
			BufferedReader in = new BufferedReader(new FileReader(aFile));
			HashMap<String, Node> nodeMap = new HashMap<>();
			String line;

			while((line = in.readLine()) != null)
			{
				String[] data = line.split(", ");

				if(Objects.equals(line, Constants.TagOfTrees))
				{
					treesDataFlag = true;
					nodesDataFlag = false;
					branchesDataFlag = false;
				}else if(Objects.equals(line, Constants.TagOfNodes))
				{
					treesDataFlag = false;
					nodesDataFlag = true;
					branchesDataFlag = false;
				}else if(Objects.equals(line, Constants.TagOfBranches))
				{
					treesDataFlag = false;
					nodesDataFlag = false;
					branchesDataFlag = true;
				}else
				{
					if(treesDataFlag)
					{
						aBuffer.append(data[0]);
						aBuffer.append(System.getProperty("line.separator"));
					}
					if(nodesDataFlag)
					{
						try {
			                Integer.parseInt(data[0]);
			            } catch (NumberFormatException e) {
			            	e.printStackTrace();
			                isNumberFlag = false;
			            }
						if(isNumberFlag)
						{
							Node aNode = new Node(data[1]);
							aNode.setLocation(new Point(0, Integer.valueOf(data[0])*Constants.DefaultFont.getSize()));
							aNode.setStatus(Constants.UnVisited);
							nodeMap.put(data[0], aNode);
							this.forest.addNode(aNode);
						}
						
					}
					if(branchesDataFlag)
					{
						try {
			                Integer.parseInt(data[0]);
			                Integer.parseInt(data[1]);
			            } catch (NumberFormatException e) {
			            	e.printStackTrace();
			                isNumberFlag = false;
			            }
			            if(isNumberFlag)
			            {
			            	if(nodeMap.get(data[0]) != null && nodeMap.get(data[1]) != null) this.forest.addBranch(new Branch(nodeMap.get(data[0]), nodeMap.get(data[1])));
			            } 
					}
				}
				isNumberFlag = true;
			}
			this.forest.setForm(aBuffer.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return;
	}

	/**
	* 樹状整列の根源(ルート)になるノードを探し出して応答するメソッドです。
	* @return
	* 良好　(7月29日)
	*/
	public Node root()
	{
		return this.forest.rootNodes().get(0);
	}

	/**
	* 樹状整列の根源(ルート)になるノードたちを探し出して応答するメソッドです。
	* @return
	* 良好　(7月29日)
	*/
	public ArrayList<Node> roots() {
		return this.forest.rootNodes();
	}

}

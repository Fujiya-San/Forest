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

/**
 * 樹状整列におけるフォレスト（木・林・森・亜格子状の森）を担うクラスになります。
 */
public class Forest extends Object {

	/**
	 * ノード（節）群（たち）を記憶するフィールドです。
	 */
	private ArrayList<Node> nodes = new ArrayList<>();

	/**
	 * ルートノードを記憶するフィールドです。
	 */
	private ArrayList<Node> rootNodesList = new ArrayList<>();

	/**
	 * ブランチ（枝）群（たち）を記憶するフィールドです。
	 */
	private ArrayList<Branch> branches = new ArrayList<>();

	/**
	 * 樹状整列したフォレスト（森）の領域（矩形）を記憶するフィールドです。
	 */
	private Rectangle bounds = new Rectangle();

	/**
	 * [getNodes description]
	 * @return [description]
	 */
	// public ArrayList<Node> getNodes()
	// {
	// 	return this.nodes;
	// }

	/**
	 * ブランチ（枝）を追加するメソッドです。
	 * @param aBranch ノード（節）
	 */
	public void addBranch(Branch aBranch) {
		branches.add(aBranch);
	}

	/**
	 * ノード（節）を追加するメソッドです。
	 * @param aNode ノード（節）
	 */
	public void addNode(Node aNode) {
		nodes.add(aNode);
	}

	/**
	 * 樹状整列するトップ（一番上位）のメソッドです。
	 */
	public void arrange() {
		this.initNodeStatus();
		Consumer<Node> aConsumer = (Node aNode) -> { this.arrange(aNode, new Point(aNode.getLocation().x+this.bounds.x, aNode.getLocation().y+this.bounds.y), null); };
		this.rootNodesList.forEach( aConsumer );

	}

	/**
	 * 樹状整列するセカンドレベル（二番階層）のメソッドです。
	 * @param aModel モデル
	 */
	public void arrange(TreeModel aModel) {
		this.bounds.setSize(0, 0);
		this.rootNodes();
		Consumer<Node> aConsumer = (Node aNode) -> { this.arrange(aNode, new Point(this.bounds.x, this.bounds.y+this.bounds.height+aNode.getBounds().height+Constants.Interval.y), aModel); };
		rootNodesList.forEach( aConsumer );
	}

	/**
	 * 樹状整列する再帰レベル（N番階層）のメソッドです。
	 * @param  aNode  ノード（このノードから再帰的にたどって下位のものたちも整列する）
	 * @param  aPoint ノードの位置（座標）
	 * @param  aModel モデル（nullのときはアニメーションを行わない）
	 * @return        樹状整列に必要だった大きさ（幅と高さ）
	 */
	protected Point arrange(Node aNode, Point aPoint,  TreeModel aModel) 
	{
		// 現在のノードのサブノードを求めて、ソートする
		ArrayList<Node> subNodes = this.subNodes(aNode);
		subNodes = this.sortNodes(subNodes);

		// ノードの位置を更新する
		aNode.setLocation(aPoint);

		// モデルが変化したことを伝える
		if(aModel != null) this.propagate(aModel);
		
		// 描画領域の範囲を更新する
		if(this.bounds.x+this.bounds.width < aNode.getBounds().x+aNode.getBounds().width) this.bounds.setSize(aNode.getBounds().width+aNode.getBounds().x, this.bounds.height);
		if(this.bounds.y+this.bounds.height < aNode.getBounds().y+aNode.getBounds().height) this.bounds.setSize(this.bounds.width, aNode.getBounds().y+aNode.getBounds().height);

		// サブノードとその座標を引数として渡して再帰呼び出しする。
		Integer anIndex = 0;
		for(Node aSubNode : subNodes)
		{
			if(!Objects.equals(aSubNode.getStatus(), Constants.Visited) && anIndex == 0 && aModel != null)
			{
				this.arrange(aSubNode, new Point(aNode.getBounds().x+aNode.getBounds().width+Constants.Interval.x+Constants.Margin.x, this.bounds.y+this.bounds.height-Constants.Margin.y-Constants.Margin.y), aModel);	
			}else if(!Objects.equals(aSubNode.getStatus(), Constants.Visited) && aModel != null)
			{
				this.arrange(aSubNode, new Point(aNode.getBounds().x+aNode.getBounds().width+Constants.Interval.x+Constants.Margin.x, this.bounds.y+this.bounds.height+aNode.getBounds().height+Constants.Interval.y), aModel);
			}else if(aModel == null && !Objects.equals(aSubNode.getStatus(), Constants.Visited))
			{
				this.arrange(aSubNode, new Point(aSubNode.getLocation().x+this.bounds.x, aSubNode.getLocation().y+this.bounds.y), aModel);		
			}
			anIndex++;

		}
		
		// サブノード群の高さの半分の高さにノードの位置をセットする
		if(aModel != null)
		{
			aNode.setLocation(new Point(aPoint.x, aNode.getBounds().y+((this.bounds.y+this.bounds.height-aNode.getBounds().y)/2)+(aNode.getBounds().height/2)-Constants.Margin.y));
		}else
		{
			aNode.setLocation(new Point(aPoint.x+this.bounds.x, aPoint.y+this.bounds.y));
		}
		
		// モデルが変更されたことを伝える
		if(aModel != null) this.propagate(aModel);

		// ノードのステータスを訪問済みにセットする
		aNode.setStatus(Constants.Visited);

		return new Point(this.bounds.x, this.bounds.y);
	}
	
	/**
	 * フォレスト（木・林・森・亜格子状の森）の領域（矩形）を応答するメソッドです。
	 * @return フォレスト領域（矩形）
	 */
	public Rectangle bounds() 
	{
		return this.bounds;
	}

	/**
	 * フォレスト（木・林・森・亜格子状の森）を描画するメソッドです。
	 * @param aGraphics グラフィクス（描画コンテクスト）
	 */
	public void draw(Graphics aGraphics) 
	{
		nodes.forEach(aNode -> aNode.draw(aGraphics));
		branches.forEach(aBranch -> aBranch.draw(aGraphics));
		aGraphics.drawRect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
	}

	/**
	 * フォレスト（木・林・森・亜格子状の森）の領域（矩形）を水に流す（チャラにする）メソッドです。
	 */
	public void flushBounds() 
	{
		bounds = new Rectangle();
	}

	/**
	 * チックタックの間、スリープし、モデルが変化した、と騒ぐ（広める：放送する）メソッドです。
	 * @param aModel モデル
	 */
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

	/**
	 * スクロールされた際、矩形を移動させるメソッドです。
	 * @param aPoint X軸とY軸の絶対スクロール量を表す座標
	 */
	public void moveBounds(Point aPoint)
	{
		this.bounds.setLocation(aPoint);
	}

	/**
	 * ノードのステータスを初期化するメソッドです。
	 */
	protected void initNodeStatus()
	{
		Consumer<Node> aConsumer = (Node aNode) -> { aNode.setStatus(Constants.UnVisited); };
		this.nodes.forEach(aConsumer);
	}

	/**
	 * フォレストの根元（ルート）となるノード群を応答するメソッドです。
	 * @return ルートノード群
	 */
	public ArrayList<Node> rootNodes() 
	{
		if(this.rootNodesList.size() == 0)
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
			return this.rootNodesList;
		}
		else
		{
			return this.rootNodesList;
		}
		
	}

	/**
	 * 引数で指定されたノード群をノード名でソート（並び替えを）するメソッドです。
	 * @param  nodeCollection ノード群
	 * @return                ソートされたノード群
	 */
	protected ArrayList<Node> sortNodes(ArrayList<Node> nodeCollection) 
	{
		nodeCollection.sort( (node1, node2) -> node1.getName().compareTo(node2.getName()) );
		return nodeCollection;
	}

	/**
	 * 引数で指定されたノードのサブノード群を応答するメソッドです。
	 * @param  aNode ノード
	 * @return       サブノード群
	 */
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

	/**
	 * 引数で指定されたノードのサブノード群を応答するメソッドです。
	 * @param  aNode ノード
	 * @return       サブノード群
	 */
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

	/**
	 * 自分自身を文字列に変換するメソッドです。
	 * @return 自分自身を表す文字列
	 */
	public String toString() {
		return null;
	}

	/**
	 * 指定された位置（座標）にノードが存在するかを調べるメソッドです。
	 * @param  aPoint 位置（モデル座標）
	 * @return        ノード、もしも見つからなかった場合には、nullを応答します。
	 */
	public Node whichOfNodes(Point aPoint) 
	{
		Predicate<Node> aPredicate = (Node aNode) -> {return aNode.getBounds().contains(aPoint); };
		return nodes.stream().filter(aPredicate).findFirst().orElse(null);
		
	}

}

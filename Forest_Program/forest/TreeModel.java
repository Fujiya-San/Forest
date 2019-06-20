package forest;

import java.io.File;
import java.util.ArrayList;
import mvc.Model;

public class TreeModel extends Model {

	private Forest forest;

	public TreeModel(File aFile)
	{
		super(); 
		return;
	}
   /**
	* アニメーションを行うメソッドです。
	*
    */
	public void animate()
	{
		Iterator<Forest> anIteratorF = dependents.iterator();
	  while(anIteratorF.hasnext())
	  {
	   Forest aForest = anIteratorF.next(); 
	   //Forestのメッセージ送信の受け取りかた？　送信のしかた?を知りたい
	   
	  }
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

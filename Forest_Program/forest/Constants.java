package forest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

public class Constants extends Object {

	public static String TagOfTrees = "trees:";

	public static String TagOfNodes = "nodes:";

	public static String TagOfBranches = "branches:";

	public static Color ForegroundColor = Color.black;

	public static Color BackgroundColor = Color.white;

	public static Font DefaultFont = new Font("Serif", Font.PLAIN, 12);

	public static Point Margin = new Point(2,1);

	public static Point Interval = new Point(25,2);

	public static Integer UnKnown = -1;

	public static Integer UnVisited = 0;

	public static Integer Visited = 1;

	public static Integer SleepTick = 100;

}

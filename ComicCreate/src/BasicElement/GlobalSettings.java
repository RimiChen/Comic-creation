package BasicElement;

import Data.ActionPool;

public class GlobalSettings {
	/*
	 * constants
	 */
	public static final int LEFT = 0;
	public static final int MIDDLE = 1;
	public static final int RIGHT = 2;
	public static final int LOW = 0;
	public static final int HIGH = 1;
	
	public static final int FACE_RIGHT = 1;
	public static final int FACE_LEFT = -1;
	public static final int NO_DIRECTION = 0;
	
	/*
	 * environment settings
	 */
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	
	public static final int PANEL_WIDTH = 100;
	public static final int PANEL_HEIGHT = 100;
	
	public static final int EXPAND_FRACTION = 5;
	
	public static final int MAX_CHARACTER = 4;
	
	//Global variables
	public static ActionPool AP = new ActionPool();
}

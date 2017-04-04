package BasicElement;

public class PositionInPanel {
	int horizontal;
	int vertical;
	int direction;
	
	public PositionInPanel(int horizontal, int vertical, int direction){
		this.horizontal = horizontal;
		this.vertical = vertical;
		this.direction = direction;
	}
	public Vector2D transform2Real(){
		Vector2D realPos = new Vector2D(0, 0);
		//transform position in panel to real position on the screen
		
		return realPos;
	}
}

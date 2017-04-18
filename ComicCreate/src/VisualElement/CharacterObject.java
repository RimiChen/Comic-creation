package VisualElement;


import BasicElement.GlobalSettings;
import BasicElement.PositionInPanel;
import BasicElement.RGBA;
import BasicElement.Vector2D;
import Data.ActionPool;
import Data.CharaState;
import Data.State;
import processing.core.PApplet;

public class CharacterObject implements VisualElement{
	//display
	PApplet p;
	ActionPool pool;
	RGBA color;
	PositionInPanel pos;
	/*
	 * data structure
	 */
	// the current state of this character, with position direction
	CharaState currentState;
	
	public CharacterObject(PApplet p, ActionPool pool, CharaState currentState, RGBA color, PositionInPanel pos){
		this.p = p;
		this.pool = pool;
		//need initialize, but now empty
		this.currentState = currentState;
		this.color = color;
		this.pos = pos;
	}
	/*
	 * match currentState with Action's state, where the Actions are in the ActionPool
	 * return: action category, which action was matched. 
	 */
	public String matchActionState(CharaState currentState, ActionPool pool){
		String actionCategory = "";
		
		//match action state with current state
		actionCategory = pool.findMatchedAction(currentState);
		return actionCategory;
	}
	@Override
	public void display() {
		
		p.fill(color.r, color.g, color.b, color.a);
		Vector2D realpos = pos.transform2Real();
		p.pushMatrix();
		p.translate(realpos.x, realpos.y);
		p.ellipse(0, 0, GlobalSettings.CHARA_SIZE/2, GlobalSettings.CHARA_SIZE/2);
		p.popMatrix();
		
		
		/*
		// TODO Auto-generated method stub
		//transform position in panel to real position, suppose it is center point
		Vector2D currenPos = pos.transform2Real();
		Vector2D origin = new Vector2D(0, 0);
		int halfPanelWidth = GlobalSettings.PANEL_WIDTH / 2;
		int halfPanelHeight = GlobalSettings.PANEL_HEIGHT / 2;
		
		//triangle
		p.pushMatrix();
		p.translate(currenPos.x, currenPos.y);
		//p.rotate(currentOrientation);
		//by default set stroke black
		p.stroke(0);
		p.fill(color.r, color.g, color.b, color.a);
			p.pushMatrix();		
			p.triangle(
					origin.x-halfPanelWidth, origin.y-halfPanelHeight,
					origin.x+halfPanelWidth, origin.y,
					origin.x-halfPanelWidth, origin.y+halfPanelHeight
			);
			p.popMatrix();
		p.popMatrix();				
		*/
		
	}//end of display

}

import java.util.ArrayList;
import java.util.List;
import BasicElement.GlobalSettings;
import BasicElement.PositionInPanel;
import BasicElement.RGBA;
import BasicElement.Vector2D;
import Data.ActionPool;
import Data.CharaState;
import Data.State;
import Structure.StructureMap;
import Structure.StructureNode;
import VisualElement.CharacterObject;
import processing.core.PApplet;

/*
 * This project was created for CSC 591 Visual Narrative project
 * Author: Yi-Chun Chen (RimiChen), Wan-Yi Yeh
 * 
 * revision history:
 * v1: to create narrative grammar structure tree according to Cohn's VNG
 * v2: Add GUI with Character class
 */

/*
 * This is the starting point of this program.
 */
public class MainProgram extends PApplet{
	
	//Execute processing, don't put anything else in here
	public static void main(String[] args) {		
		PApplet.main("MainProgram");
	}

	//set size, don't put anything else in here=============================================================
	public void settings(){
		//window size
		size(GlobalSettings.SCREEN_WIDTH, GlobalSettings.SCREEN_HEIGHT);
		
	}

	//Declare global settings
	public static GlobalSettings G;
	public static StructureMap S;
	public static List<StructureNode> currentStructure;
	public static ActionPool A;
		
	//main setup run on first time===================================================================
	public void setup(){
		
		//create the first narrative structure
		//initialize current structure

		//initial global settings
		initialize();	
		refresh();
	}
	
	public void keyPressed(){
		if (key == 'n' || key == 'N'){
			refresh();
		}
	}
	
	//main draw loop===========================================================================
	public void draw(){
		/*
		ActionPool pool = new ActionPool();
		CharaState initState = new CharaState();
		RGBA color = new RGBA(255, 0, 0, 255);
		PositionInPanel pos = new PositionInPanel(GlobalSettings.LEFT, GlobalSettings.LOW, GlobalSettings.FACE_RIGHT);
		
		CharacterObject c1 = new CharacterObject(this, pool, initState, color, pos);
		c1.display();
		*/
		
		
	}
	
	//Initialize settings===========================================================================
	public void initialize(){
		
		//global setting
		G = new GlobalSettings(this);
		
		//Visual narrative structure
		S = new StructureMap();
		S.addStructure("P|R");
		S.addStructure("I|P|R");
		S.addStructure("I|L|P|R");
		S.addStructure("E|I|P|R");
		S.addStructure("E|I|L|P");
		S.addStructure("E|I|L|P|R");
		S.addStructure("I|P|I|P|R");
		S.showAllStructure();
		
		//inital actionpool
		A = new ActionPool();
		//set current structure to null
		currentStructure = new ArrayList<StructureNode>();
	}
	
	public void refresh(){

		//clean structure and canvas
		currentStructure.clear();
		background(204);
		
		//initial and draw
		currentStructure = createStructure();
		if(currentStructure.size()>0){
			int next = 0;
			pushMatrix();
			currentStructure.get(0).initializePanel();
			currentStructure.get(0).drawPanel();
			translate(GlobalSettings.PANEL_WIDTH+10, 0);
			for (int i = 1; i < currentStructure.size(); i++){
				//current line full, draw next
				if((i / GlobalSettings.PANEL_ONE_LINE) > next){
					popMatrix();
					pushMatrix();
					next++;
					translate(0, (GlobalSettings.PANEL_HEIGHT+15)*next);
				}
				//currentStructure.get(i).initializePanel();
				currentStructure.get(i).followUpdate(currentStructure.get(i-1));
				currentStructure.get(i).drawPanel();
				translate(GlobalSettings.PANEL_WIDTH+10, 0);
			}
			popMatrix();
		}
	}
	
	
	//create structure=====================================================================
	public List<StructureNode> createStructure(){
		
		//create a root node for structure
		StructureNode root = new StructureNode(this);
		
		//pick a basic phase from structure map
		int random = (int )(Math.random() * S.phaseMap.size() + 0);
		//choose a structure for root
		root.getStructureByIndex(random, S);
		root.showAllChild();
		
		System.out.println("==========================");

		//create structure
		root.expandStructure(0, 6, root.childList.size(), S);
		
		//return the leaf node as a list
		List<StructureNode> structureList = new ArrayList<StructureNode>();
		structureList = root.getLeafStructure(0, structureList);

		return structureList;
		
	}

}

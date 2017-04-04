import java.util.ArrayList;
import java.util.List;

import BasicElement.GlobalSettings;
import BasicElement.PositionInPanel;
import BasicElement.RGBA;
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

	//Declare global settings
	public static GlobalSettings G;
	public static StructureMap S;
	public static List<StructureNode> currentStructure;
	
	public static void main(String[] args) {

		//initial global settings
		initialize();			
		
		//Execute processing
		PApplet.main("MainProgram");
	}
	//set size	
	public void settings(){
		//window size
		size(GlobalSettings.SCREEN_WIDTH, GlobalSettings.SCREEN_HEIGHT);
		
		//create the first narrative structure
		//initialize current structure
		currentStructure.clear();
		currentStructure = createStructure();
		
		if(currentStructure.size()>0){
			//assign character
			currentStructure.get(0).initializePanel();
		}
	}
	//main draw loop
	public void draw(){
		ActionPool pool = new ActionPool();
		CharaState initState = new CharaState();
		RGBA color = new RGBA(255, 0, 0, 255);
		PositionInPanel pos = new PositionInPanel(GlobalSettings.LEFT, GlobalSettings.LOW, GlobalSettings.FACE_RIGHT);
		
		CharacterObject c1 = new CharacterObject(this, pool, initState, color);
		c1.display(pos);
	}
	//Initialize settings
	public static void initialize(){
		//global setting
		G = new GlobalSettings();
		
		//Visual narrative structure
		S = new StructureMap();
		S.addStructure("P|R");
		S.addStructure("I|P|R");
		S.addStructure("I|P|L|R");
		S.addStructure("I|E|P|R");
		S.showAllStructure();
		
		//set current structure to null
		currentStructure = new ArrayList<StructureNode>();
	}
	//create structure
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

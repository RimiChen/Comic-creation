package Structure;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import BasicElement.GlobalSettings;
import BasicElement.PositionInPanel;
import BasicElement.RGBA;
import BasicElement.Vector2D;
import Data.ActionPool;
import Data.CharaState;
import VisualElement.CharacterObject;
import processing.core.PApplet;

/*
 * Each node is belongs to a narrative category.
 * A structure tree is composed by structure nodes, and the leaf node are panels.
 * so, this node should also contains the information about content in the panels
 */
public class StructureNode {
	// I: Initial, E: Establisher, L: Prolongation, P: Peak, R: Release
	public String category;
	public List<StructureNode> childList;
	public boolean isWentThrough = false;
	public int currentLevel = 0;
	public PApplet p;
	
	//character name, should be string
	public Vector characterSet;
	
	
	public StructureNode(PApplet p){
		this.p = p;
		childList = new ArrayList<StructureNode>();
	}
	
	/*
	 * check if this is a leaf node
	 */
	public boolean checkLeaf(){
		boolean isLeaf = false;
		if(childList.size() == 0){
			isLeaf = true;
		}
		return isLeaf;
	}
	/*
	 * get structure list
	 */
	public void getStructureByIndex(int index, StructureMap map){
		Vector targetV = map.phaseMap.get(index);
		for(int i =0; i < targetV.size(); i++){
			StructureNode newNode = new StructureNode(p);
			newNode.category = (String) targetV.get(i);
			childList.add(newNode);
		}
	}
	/*
	 * print all node in childList
	 */
	public void showAllChild(){
		
		System.out.print("( level "+currentLevel+" , " +category+",");
		for(int i =0; i < childList.size(); i++){
			System.out.print(childList.get(i).category+" ");
		}
		System.out.println(")");

	}
	/*
	 * try to expand structures
	 */
	public int expandStructure(int level, int maxPanelNumber, int currentPanelNumber, StructureMap map){
		//ignore all seen nodes
		if(isWentThrough != true){
			//System.out.println("#: "+level +", "+category);
		}
		
		isWentThrough = true;
		currentLevel = level;
		int count = 0;
		if(childList.size() == 0){
			//expandable
			boolean willExpand = shouldExpand(GlobalSettings.EXPAND_FRACTION);
			if(willExpand == true){
				//choose a structure, and compute panel numbers
				if(currentPanelNumber < maxPanelNumber){
					//assign a structure, and add current Panel number
					int random = (int )(Math.random() * map.phaseMap.size() + 0);
					getStructureByIndex(random, map);
					showAllChild();
					int expandRefer = (int )(Math.random() * 2 + 0);
					// add child nodes, minus parent node
					//recursively expand nodes
					currentPanelNumber = currentPanelNumber+map.phaseMap.get(random).size()-1;
					currentPanelNumber = expandStructure(level, maxPanelNumber, currentPanelNumber, map);
				}
			}
			else{
				// should return
			}
		}
		else{
			//check whether the child need to be expand
			//see whether the child node was seen
			while(count < childList.size()){
				if(childList.get(count).isWentThrough != true){
					//recursively expand nodes
					currentPanelNumber = childList.get(count).expandStructure(level+1, maxPanelNumber, currentPanelNumber, map);
				}
				count++;
			}
		}
		return currentPanelNumber;
	}	
	public boolean shouldExpand(int fraction){
		boolean should = false;
		int random = (int )(Math.random() * fraction + 0);
		if(random % fraction == 0){
			should = true;
		}
		else{
			should = false;
		}
		return should;
	}
	/*
	 * print whole structure
	 */
	public List<StructureNode> getLeafStructure(int currentChildCount, List<StructureNode> structureList){
		int count = 0;
		if(childList.size() == 0){
			//if no child -> leaf node
			System.out.println("$: "+currentLevel+", "+category);
			structureList.add(this);
		}
		else{
			//check count didn't exceed child index
			while(currentChildCount<childList.size()){
				childList.get(currentChildCount).getLeafStructure(0, structureList);
				currentChildCount = currentChildCount+1;
			}
		}
		return structureList;
	}
	public void initializePanel(){
		int numberOfCharacter = (int )(Math.random() * GlobalSettings.MAX_CHARACTER + 1);
		for(int i =0; i <numberOfCharacter; i++){
			RGBA tempColor = getRandomColor();
			CharaState initialState = new CharaState();
			Vector2D tempPosi = new Vector2D(0, 1);
			PositionInPanel pos = new PositionInPanel(GlobalSettings.LEFT, GlobalSettings.LOW, GlobalSettings.FACE_RIGHT);
			
			//PApplet p, ActionPool pool, CharaState currentState, RGBA color
			CharacterObject tempCharacter = new CharacterObject(p, GlobalSettings.AP, initialState, tempColor, pos);
			p.fill(255);
			p.rect(0,0,GlobalSettings.PANEL_WIDTH, GlobalSettings.PANEL_HEIGHT);
			p.line(GlobalSettings.BLOCK_WIDTH, 0, GlobalSettings.BLOCK_WIDTH, GlobalSettings.PANEL_HEIGHT);
			p.line(GlobalSettings.BLOCK_WIDTH*2, 0, GlobalSettings.BLOCK_WIDTH*2, GlobalSettings.PANEL_HEIGHT);
			p.line(0, GlobalSettings.BLOCK_HEIGHT, GlobalSettings.PANEL_WIDTH, GlobalSettings.BLOCK_HEIGHT);
		}
	}
	public RGBA getRandomColor(){
		// generate a random color with random RGB
		RGBA newColor = new RGBA(0, 0, 0, 255);
		return newColor;
	}
	
	
}

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
	
	//character name, should be string
	public Vector characterSet;
	
	
	public StructureNode(){
		childList = new ArrayList<StructureNode>();
	}
	
	/*
	 * chekc if this is a leaf node
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
			StructureNode newNode = new StructureNode();
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
	public int expandStructure( int maxPanelNumber, int currentPanelNumber, StructureMap map){
		isWentThrough = true;
		if(childList.size() == 0){
			//expandable
			//boolean willExpand = shouldExpand();
			boolean willExpand = true;
			if(willExpand == true){
				//choose a structure, and compute panel numbers
				if(currentPanelNumber < maxPanelNumber){
					//assign a structure, and add current Panel number
					int random = (int )(Math.random() * map.phaseMap.size() + 0);
					getStructureByIndex(random, map);
					showAllChild();
					int expandRefer = (int )(Math.random() * 2 + 0);
					// add child nodes, minus parent node
					currentPanelNumber = currentPanelNumber+map.phaseMap.get(random).size()-1;
					currentPanelNumber = expandStructure( maxPanelNumber, currentPanelNumber, map);
				}
			}
			else{
				// should return
			}
		}
		else{
			//check whether the child need to be expand
			//see whether the child node was seen
			int count = 1;
			//ignore all seen nodes
			while(childList.get(count).isWentThrough == true){
				count++;
			}
			if(count < childList.size()){
				//still have unseen nodes
				childList.get(count).isWentThrough = true;
				currentPanelNumber = childList.get(count).expandStructure( maxPanelNumber, currentPanelNumber, map);
				
			}
			else{
				
			}
		}
		return currentPanelNumber;
	}
	public int expandStructure2(int level, int maxPanelNumber, int currentPanelNumber, StructureMap map){
		isWentThrough = true;
		currentLevel = level;
		int count = 0;
		//ignore all seen nodes
		
		if(childList.size() == 0){
			//expandable
			boolean willExpand = shouldExpand();
			if(willExpand == true){
				//choose a structure, and compute panel numbers
				if(currentPanelNumber < maxPanelNumber){
					//assign a structure, and add current Panel number
					int random = (int )(Math.random() * map.phaseMap.size() + 0);
					getStructureByIndex(random, map);
					showAllChild();
					int expandRefer = (int )(Math.random() * 2 + 0);
					// add child nodes, minus parent node
					currentPanelNumber = currentPanelNumber+map.phaseMap.get(random).size()-1;
					currentPanelNumber = expandStructure2(level, maxPanelNumber, currentPanelNumber, map);
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
					currentPanelNumber = childList.get(count).expandStructure2(level+1, maxPanelNumber, currentPanelNumber, map);
				}
				count++;
			}
		}
		return currentPanelNumber;
	}	
	public boolean shouldExpand(){
		boolean should = false;
		int random = (int )(Math.random() * 2 + 0);
		if(random % 2 == 0){
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
	public void printWholeStructure(){
		
	}
	
}

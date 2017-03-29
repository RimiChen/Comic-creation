/*
 * This project was created for CSC 591 Visual Narrative project
 * Author: Yi-Chun Chen (RimiChen), Wan-Yi Yeh
 * 
 * revision history:
 * v1: to create narrative grammar structure tree according to Cohn's VNG
 * v2: 
 */


/*
 * This is the starting point of this program.
 */
public class MainProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StructureMap S = new StructureMap();
		S.addStructure("P|R");
		S.addStructure("I|P|R");
		S.addStructure("I|P|L|R");
		S.addStructure("I|E|P|R");

		S.showAllStructure();
		
		//create root structure
		StructureNode root = new StructureNode();
		
		int random = (int )(Math.random() * S.phaseMap.size() + 0);
		
		System.out.println("==========================");
		//first choose a structure for root
		root.getStructureByIndex(random, S);
		root.showAllChild();
		//create structure
		
		root.expandStructure2(0, 6, root.childList.size(), S);
		
		//print structure
		
		
	}

}

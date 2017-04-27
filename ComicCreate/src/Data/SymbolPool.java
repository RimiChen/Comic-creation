package Data;

import java.util.Map;
import java.util.TreeMap;

import processing.core.PApplet;
import processing.core.PImage;

/*
 * This class contains images for symbols
 */
public class SymbolPool {
	PApplet P;
	// save image in <symbol name, img>
	Map<String, PImage> imagePool;
	PImage tempImage;
	
	public SymbolPool(PApplet P){
		this.P = P;
		imagePool = new TreeMap<String, PImage>();
/*
		actionCategory.put("stand"	, "EIR");
		actionCategory.put("walk"	, "IL");
		actionCategory.put("run"	, "IL");
		actionCategory.put("jumpUp"	, "IL");
		actionCategory.put("fall"	, "PL");
		actionCategory.put("roll"	, "PL");
		actionCategory.put("collis"	, "P");
		actionCategory.put("dizzy"	, "R");
*/		
		
		//load images
		
		//Note: use same picture to check whether the function work
		tempImage = P.loadImage("../symbol/Fall.png");
		imagePool.put("fall",tempImage);
		//tempImage = P.loadImage("../symbol/Fall.png");
		imagePool.put("stand",tempImage);		
		//tempImage = P.loadImage("../symbol/Fall.png");
		imagePool.put("walk",tempImage);	
		//tempImage = P.loadImage("../symbol/Fall.png");
		imagePool.put("run",tempImage);	
		//tempImage = P.loadImage("../symbol/Fall.png");
		imagePool.put("roll",tempImage);	
		//tempImage = P.loadImage("../symbol/Fall.png");
		imagePool.put("collis",tempImage);	
		//tempImage = P.loadImage("../symbol/Fall.png");
		imagePool.put("dizzy",tempImage);	
		
	}
	
	
}

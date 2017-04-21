package Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

public class ActionPool {
	Vector actionSet;
	
	static HashMap<String, String[]> actionNet = new HashMap<String, String[]>();
	{
		// put("name", new String[] 		  {"stand", "walk", "run",  "jumpUp", "fall", "trip", "dizzy"}
		actionNet.put("stand"		, new String[]{"stand", "walk", "run",	"jumpUp", "-",	 "roll", "-" 			});
		actionNet.put("walk"			, new String[]{"stand", 	"walk", "run",	"jumpUp", "-", 	 "roll", "-" 			});
		actionNet.put("run"			, new String[]{"stand", 	"walk", "run",	"jumpUp", "-",	 "roll", "-" 			});
		actionNet.put("jumpUp"	, new String[]{"-", 			"-", 		 "-",		"-", 	  		   "fall", "-", 	  	"-" 			});
		actionNet.put("fall"			, new String[]{"stand",	 "-", 		 "-",		"-", 	  		   "-", 	 "roll", 	"dizzy" 	});
		actionNet.put("roll"			, new String[]{"stand", 	"walk", "-",		"-", 	   		   "-",  	 "roll", 	"dizzy" 	});
		actionNet.put("collis"		, new String[]{"-", 			"-", 		 "-",		"jumpUp", "fall", "-", 	  	"dizzy" 	});
		actionNet.put("dizzy"		, new String[]{"stand", 	"walk", "-", 		"jumpUp", "-", 	 "roll", 	"dizzy"	});
	}
	
	static HashMap<String, String> actionCategory = new HashMap<String, String>();
	{
		actionCategory.put("stand"	, "EIR");
		actionCategory.put("walk"	, "IL");
		actionCategory.put("run"	, "IL");
		actionCategory.put("jumpUp"	, "IL");
		actionCategory.put("fall"	, "PL");
		actionCategory.put("roll"	, "PL");
		actionCategory.put("collis"	, "P");
		actionCategory.put("dizzy"	, "R");
	}
	
	static void ActionPool(){	
	}
	
	public void findMatchedAction(CharaState currentState){
		//by default if empty
		//String actionCategory = "";
		//for each( action in actionSet ){find matched state with currentState, actionCategory = matched}
		//return actionCategory;
		
	}
	
	public Set<String> getAllAction(){
		return actionCategory.keySet();
	}
	
	public String nextAction(String previousAction, String category){
	    //find candidate action
	    Set<String> candidate = getAvailableActions(previousAction);
	    //Picking a random element from a set
	    String randomPick = pickFromSet(candidate);
	    //check is picked fit it's category  
	    while(actionCategory.get(randomPick).contains(category)==false && candidate.size()>=2){
	    	candidate.remove(randomPick);
	    	randomPick = pickFromSet(candidate);
	    }
	    //prevent actionNet error
	    if(candidate.size() == 1 && actionCategory.get(randomPick).contains(category)==false){
	    	System.out.println("net fail,  \"" + previousAction + "\" candidate can't find match category to " + category);
	    	randomPick = choseRandomAction(category);
	    }
	    return randomPick;      
	  }
	
	private Set<String> getAvailableActions(String action){
		Set<String> available = new HashSet<String>();
		String[] tmp = actionNet.get(action);
		for(int i = 0 ; i < tmp.length; i++){
			if (tmp[i] != "-"){
				available.add(tmp[i]);
			}
		}
		if(available.size()==0){
			System.out.println("getAvailableAction error");
		}
		return available;
	} 
	
	public static String pickFromSet(Set<String> myHashSet){
		int size = myHashSet.size();
		//System.out.println("size: " + size);
		int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
		int i = 0;
		for(String obj : myHashSet)
		{
		    if (i == item)
		        return obj;
		    i++;
		}
		System.out.println("pickFromSet error");
		return "";
	}
	
	public static String choseRandomAction(String category){
		String tmp = "";
		Set<String> contains = new HashSet<String>();
		for (String action: actionCategory.keySet()){
			  if(actionCategory.get(action).contains(category)){
				  contains.add(action);
			  }
		}
		tmp = pickFromSet(contains);
		if (tmp=="")
			System.out.println("choseRandomAction = null");
		return tmp;
	}
	
}

import java.util.ArrayList;

public abstract class Action {
	
	//Member functions and variables:

	//variables
	public String name; //The name is used to find it in the findAction function. 
	public int priority; //The priority is used to sort the ActionLog in 
	
	//Constructor
	public Action()
	{
		//Nothing in the base class, to be inherited
	}	
	
	public void execute(Player subject,  Player object) //This will be overridden in the children
	{
		
	}

	//static class variables and methods
	
	public static ArrayList<Action> ActionList; //A list of all actions so each role can check them.
	
	public static void Start() //Sets up the list of actions
	{
		ActionList=new ArrayList<Action>();
		ActionList.add(new Kill());
		ActionList.add(new Lookout());
	}

	public static void End() //Tears down the list of actions
	{
		ActionList=null;
	}
	
	public static Action findAction(String name) //used to find actions based on the name
	{
		for(Action actions : ActionList)
			if(actions.name==name)
				return actions;
			else
				System.out.println("actions name: "+actions.name+" name: "+name);
		System.out.println("Current actions in action class: "+Action.ActionList);
		
		System.out.println("ERROR: THE FUNCTION findAction HAS RETURNED NULL");
		return null;
	}
	



}


public class Role {

	private Action action;
	
	public Role(Action action)
	{
		this.action=action;
	}
	
	public void doActionRole(Player user, String GivenAction, Player target)
	{
		if(action.name==GivenAction)
		{
			action.execute(user, target);
		}
		else
		{
			System.out.println("Failure: no action has this name. Wrong role selected.");
			System.out.println("Command given: "  + GivenAction);
			System.out.println("Command expected: "+ action.name);
		}
	}
	
	
	
}

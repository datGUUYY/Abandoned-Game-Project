
public class Kill extends Action {

	public Kill() {
		super();
		this.name="Kill";
		this.priority=2; //Second most important.
	}
	
	@Override
	public void execute(Player subject, Player object)
	{
		object.die();
	}

	

}

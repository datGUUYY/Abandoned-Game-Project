import java.util.ArrayList;

public class Player {

	/* This class represents one player in the game.
	 * 
	 * 
	 */
	
	private boolean dead;
	private String name;
	
	private static ArrayList<Player> playerList;
	
	public static void setup()
	{
		playerList=new ArrayList<Player>();
	}
	
	public static void teardown()
	{
		playerList=null;
	}
	
	public static Player searchPlayer(String name)
	{
		for(Player player : playerList)
			if(player.name==name)
				return player;
		System.out.println("WARNING: searchPlayer FUNCTION RETURNED NULL");
		return null;
		
	}
	
	public Role role;
	public Player(Role role, String name)
	{
		this.role=role;
		dead=false;
		this.name=name;
		playerList.add(this);
		//This is getting called, but doActionPlayer is not.
		
	}
	
	public void doActionPlayer(String action, Player target) //STRAIGHT UP NOT BEING CALLED.
	{
		//THIS IS NOT BEING CALLED
		if(!dead)
			role.doActionRole(this, action, target);
		else
			System.out.println("This character is dead");
	}
	
	public void die()
	{
		this.dead=true;
	}
	
	public boolean isDead()
	{
		System.out.println("Break 1.1");
		System.out.println(this.dead);
		System.out.println("Break 1.2");
		return this.dead;
		
	}

	
	public String getName() {
		return name;
	}
	
}

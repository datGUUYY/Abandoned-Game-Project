import java.util.ArrayList;
import java.util.Collections;
import org.json.simple.JSONObject;

public class ActionLog implements Comparable<ActionLog> {

	private String subject;
	private String object;
	private String verb;
	private Action act;
	private Player subjectPlayer;
	private Player objectPlayer;
	
	public static ArrayList<ActionLog> ActionLogList; //abbreviated ALL below
	


	public ActionLog(String subject, String object, String verb)
	{	this.subject=subject;
		this.object=object;
		this.verb=verb;
		
		this.subjectPlayer=Player.searchPlayer(this.subject);
		System.out.println("searchplayer returns " + this.subjectPlayer.getName());
		this.objectPlayer=Player.searchPlayer(this.object);
		System.out.println("and the object: " + this.objectPlayer.getName());
		
		
		System.out.println("this.act is "+Action.findAction(verb));
		this.act=Action.findAction(verb);
		
		ActionLogList.add(this);
	}
	public ActionLog(JSONObject job)
	{
		String subject=(String)job.get("subject");
		String object=(String)job.get("object");
		String verb=(String)job.get("verb");
		
		this.subject=subject;
		this.object=object;
		this.verb=verb;
		
		this.subjectPlayer=Player.searchPlayer(this.subject);
		System.out.println("searchplayer returns " + this.subjectPlayer.getName());
		this.objectPlayer=Player.searchPlayer(this.object);
		System.out.println("and the object: " + this.objectPlayer.getName());
		
		
		System.out.println("this.act is "+Action.findAction(verb));
		this.act=Action.findAction(verb);
		
		ActionLogList.add(this);
	}
	
	@Override
	public int compareTo(ActionLog other) {
		return this.act.priority-other.act.priority;
	}
	public void doAction() 
	{
		act.execute(subjectPlayer, objectPlayer);
	}
	public String getSubject()
	{
		return subject;
	}
	
	
	public static void addActionLog(ActionLog addition)
	{
		ActionLogList.add(addition);
	}
	
	public static ActionLog findActionLog(String subject)
	{
		for (ActionLog log : ActionLogList)
			if(log.subject == subject)
				return log;
		
		System.out.println("ERROR: FUNCTION findActionLog RETURNED NULL");
		return null;
	}


	public static void execute() //This sorts and executes the actions in the ALL.
	{	Collections.sort(ActionLogList);
		for(ActionLog log : ActionLogList) //should execute in order
			log.act.execute(log.subjectPlayer, log.objectPlayer);
	}

	public static void startUp() //SETS UP THE ACTION LOG LIST
	{
		ActionLogList=new ArrayList<ActionLog>();
	}
	public static void TearDown()
	{
		ActionLogList.clear();
		ActionLogList=null;
	}
	
}

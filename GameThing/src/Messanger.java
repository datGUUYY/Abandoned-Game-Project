import java.net.*;
import java.io.*;
import org.json.simple.*;


/*
 * JSON SERVER OBJECT as given:
 * Header: list of players and player variables, such as name and tag.
 * Body: list of ActionLogs as JSON object
 * Night: returns the current night. Used to check if the server has been updated.
 * Gamestate: TO BE UPDATED LATER
 */

public class Messanger {
private static Socket socket;
private static DataInputStream in;
private static DataOutputStream out;

private static String IP="localhost"; //treat as constant
private static int PORT=6666;


public static void onStartUp() throws UnknownHostException, IOException
{
	socket=new Socket(IP, PORT);
}

public static JSONObject getFromServer()
{
	System.out.println("USING TEMPORARY getFromServer FUNCTION, EDIT LATER");
	return(genSampleOutput());
}

public static JSONObject getFromClient()
{
	System.out.println("USING TEMPORARY getFromClient FUNCTION, EDIT LATER");
	return(genSampleClient());
}

private static JSONObject genSampleClient()
{
	JSONObject returnObject = new JSONObject();
	returnObject.put("subject", "Player1");
	returnObject.put("object", "Player2");
	returnObject.put("verb", "Kill");
	returnObject.put("Night", 1);
	return returnObject;
}

private static JSONObject genSampleOutput()
{
	JSONObject returnObject = new JSONObject();
	JSONArray headerList = new JSONArray();
	JSONArray bodyList = new JSONArray();
	int night=1;
	//header
	JSONObject temp = new JSONObject();
	temp.put("Name", "Player1");
	temp.put("Tag", "OwO");
	headerList.add(temp);
	temp= new JSONObject();
	temp.put("Name", "Player2");
	temp.put("Tag", "UwU");
	headerList.add(temp);
	temp=new JSONObject();
	//body
	temp.put("subject", "Player1");
	temp.put("object", "Player2");
	temp.put("verb", "Kill");
	bodyList.add(temp);
	temp=new JSONObject();
	temp.put("subject", "Player2");
	temp.put("object", "Player1");
	temp.put("verb", "Lookout");
	bodyList.add(temp);
	//object
	returnObject.put("Header", headerList);
	returnObject.put("Body", bodyList);
	returnObject.put("Night", night);
	
	return returnObject;
}

public static JSONObject genStartClientObject() {
	JSONObject returnObject=new JSONObject();
	int night=0; //The sample exists before the first night.
	//In the future updates, this will be the user's data object.
	returnObject.put("Night", night);
	System.out.println("WARNING: THE CLIENT'S USER DATA HAS NOT YET BEEN IMPLEMENTED. GENERATING DEFAULT");
	return returnObject;
	
}

public static JSONObject genStartServerObject()
{
	JSONObject returnObject=new JSONObject();
	returnObject.put("Night", 0);
	return returnObject;
}
}
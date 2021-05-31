import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SETest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/* The following test is used for the sample case, in which the server is stubbed.
	 * It should fail on non-stubbed executions, succeed on stubbed execution.
	 */

	@Test
	public void test() throws UnknownHostException, IOException 
	{ 
		//Set up the players:
		
		
		
		Assert.assertNull(ServersideExecution.getClient());
		Assert.assertNull(ServersideExecution.getServer());
		SetupClass.start();
		
		Player Player1 = new Player(RolesList.yandere, "Player1");
		Player Player2 = new Player(RolesList.yandere, "Player2");
		// The sample versions of the classes are created. it should have only "night" as a variable, at 0.
		
		Assert.assertEquals(ServersideExecution.getClient().get("Night"), 0);
		Assert.assertEquals(ServersideExecution.getServer().get("Night"), 0);
		Assert.assertNull(ServersideExecution.getServer().get("Body"));
		
		
		ServersideExecution.onUpdate(); //NOT changing anything.
		/* After this, calling the update function should switch the client's status out with the stubbed data.
		 * It will then "send" this update to the "server". Meaning it will do nothing. Because this is the stubbed
		 * version. The switch will flip, and it will go back to waiting for the server.
		 */
		
		//log debugging
		System.out.println(ServersideExecution.getServer().toJSONString());
		System.out.println(ServersideExecution.getClient().toJSONString());
		
		Assert.assertEquals(ServersideExecution.getClient().get("Night"), 1);
		Assert.assertEquals(ServersideExecution.getClient().get("subject"), "Player1");
		Assert.assertEquals(ServersideExecution.getClient().get("object"), "Player2");
		Assert.assertEquals(ServersideExecution.getClient().get("verb"), "Kill");
		//The server hasn't changed
		Assert.assertEquals(ServersideExecution.getServer().get("Night"), 0);
		Assert.assertNull(ServersideExecution.getServer().get("Body"));
		
		ServersideExecution.onUpdate();
		/* The second update will cause it to call the code from the server. The stubbed function generates a 
		 * predefined JSON object. We will test to make sure all of the data is as written. The client does not change.
		 * The server is updated.
		 */
		Assert.assertEquals(ServersideExecution.getClient().get("Night"), 1);
		Assert.assertEquals(ServersideExecution.getClient().get("subject"), "Player1");
		Assert.assertEquals(ServersideExecution.getClient().get("object"), "Player2");
		Assert.assertEquals(ServersideExecution.getClient().get("verb"), "Kill");
	
		Assert.assertEquals(ServersideExecution.getServer().get("Night"), 1);
		JSONArray temp = (JSONArray)ServersideExecution.getServer().get("Body");
		JSONObject tempObj=(JSONObject) temp.get(0);
		Assert.assertEquals(tempObj.get("subject"), "Player1");
		Assert.assertEquals(tempObj.get("object"), "Player2");
		Assert.assertEquals(tempObj.get("verb"), "Kill");
		tempObj=(JSONObject) temp.get(1);
		Assert.assertEquals(tempObj.get("subject"), "Player2");
		Assert.assertEquals(tempObj.get("object"), "Player1");
		Assert.assertEquals(tempObj.get("verb"), "Lookout");
		SetupClass.end();
	}
	
	/* The next test case should connect to localhost, and interacts with stubbed server code.
	 * The non-stubbed serverside execution should use stubbed players to test its validity.
	 */

}

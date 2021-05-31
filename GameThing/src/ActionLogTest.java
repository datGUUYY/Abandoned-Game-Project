import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActionLogTest {
	Player player1;
	Player player2;
	
	

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
		
	}

	@Test
	public void testActionLog() throws UnknownHostException, IOException {
		SetupClass.start(); //This no longer includes the actionlog classes' setup or termination
		player1 = new Player(RolesList.yandere, "player1");
		player2 = new Player(RolesList.yandere, "player2");
		
		ActionLog.startUp();
		new ActionLog("player1", "player2", "Kill");
		new ActionLog("player2", "player1", "Lookout");

		ActionLog.execute();
		Assert.assertTrue(player2.isDead());
		Assert.assertEquals(ActionLog.ActionLogList.get(0).getSubject(),"player2"); //These should be private. This test should fail.
		//This will be tested to pass once, afterwards it should fail
				
		SetupClass.end();
		ActionLog.TearDown();
		player1=player2=null;
		
	}

	@Test
	public void testDoAction() throws UnknownHostException, IOException {
		SetupClass.start();
		player1=new Player(RolesList.yandere, "player1");
		player2=new Player(RolesList.yandere, "player2");
		
		ActionLog.startUp();
		new ActionLog("player1", "player2", "Kill").doAction();
		Assert.assertTrue(player2.isDead());
		
		ActionLog.TearDown();
		player1=player2=null;
		SetupClass.end();
	}

}

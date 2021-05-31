import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;



public class playerTest {
	static Player player1;
	static Player player2;
	
	static Player sample;
	static Action sampleKill;
	
	@Before
	public void setUp() throws Exception {
		SetupClass.start();
		
		player1= new Player(RolesList.yandere, "Player1");
		player2= new Player(RolesList.yandere, "Player1");
		
		sample= new Player(RolesList.yandere, "Sample");
		sampleKill= new Kill();
	}

	@After
	public void tearDown() throws Exception {
		sample=player1=player2=null;
		sampleKill=null;
		
		SetupClass.end();
	}

	@Test
	public void test(){
		sampleKill.execute(sample, sample); //subject doesn't play a role in this function.
		Assert.assertTrue(sample.isDead());
		
		player1.doActionPlayer("Kill", player2);
		Assert.assertTrue(player2.isDead());
	}

}

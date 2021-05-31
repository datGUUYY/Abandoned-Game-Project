import java.io.IOException;
import java.net.UnknownHostException;

/*THIS CLASS WILL SIMPLY HAVE A START FUNCTION THAT INITIATES EVERYTHING. DO NOT FORGET IT
 * 
 * 
 * 
 */
public class SetupClass {

	public static void start() throws UnknownHostException, IOException
	{
		Action.Start();
		Player.setup();
		Messanger.onStartUp();
		ServersideExecution.onStartup();
	}
	
	public static void end()
	{
		Action.End();
		Player.teardown();
	}
	
}

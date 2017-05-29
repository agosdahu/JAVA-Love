package tenisz;

public abstract class Network {

	public abstract void disconnect();

	public abstract void connect(String string);

	public abstract void sendDataToClient(Ball ball, Player player, Score score);
	public abstract Data getDataFromClient();
	public abstract void sendDataToServer(Player player1, boolean getplayer1Up, boolean getplayer1Down);
	public abstract Data getDataFromServer();

	
}

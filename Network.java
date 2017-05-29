package tenisz;

abstract class Network {

	protected Control ctrl;

	Network(Control c) {
		ctrl = c;
	}
	
	abstract void connect(String ip);

	abstract void disconnect();
	
	abstract void sendToClient(DataFromServer temp);

	abstract void sendToServer(DataFromClient temp);

	abstract DataFromClient getReceivedDataFromClient();

	abstract DataFromServer getReceivedDataFromServer();
}

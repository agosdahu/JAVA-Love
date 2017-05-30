package tenisz;

import tenisz.Control;

public abstract class Network {
	
	protected Control ctrl;
	
	Network(Control c) {
		ctrl = c;
	}
		
	abstract void connect(String host);
	
	abstract void disconnect();
		
	abstract DataFromClient getReceivedDataFromClient();

	abstract DataFromServer getReceivedDataFromServer();
	
	abstract void updateGame();
}
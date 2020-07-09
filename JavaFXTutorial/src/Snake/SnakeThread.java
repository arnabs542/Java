package Snake;

public class SnakeThread extends Thread {

	private SnakeWindow parentWindow;
	private byte directionBefore = SnakeObject.LEFT;
	private byte pendingDirection = SnakeObject.LEFT;
	
	public SnakeThread(SnakeWindow parentWindow) {
		this.parentWindow = parentWindow;
	}
	
	public void setDirection(byte direction) {
		if ( direction != pendingDirection * -1 && direction != directionBefore * -1)
			this.pendingDirection = direction;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(parentWindow.getSnakeSpeed() );
			} catch (InterruptedException e) {
				break;
			}
			parentWindow.getSnakeObject().move(pendingDirection);
			directionBefore = pendingDirection;
		}
	}
	
}

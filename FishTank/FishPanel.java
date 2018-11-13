/**
 * FishPanel.java The JPanel in which the fish tank and fish are displayed.
 */
public class FishPanel extends javax.swing.JPanel implements Mover {
	private final int TANK_X = 75; // attributes

	private final int TANK_Y = 75;

	private final int TANK_WIDTH = 400;

	private final int TANK_HEIGHT = 300;

	private final int INTERVAL = 100;

	private SmartRectangle _tank; // components

	private Fish _yellowfish;
	private Fish _pinkFish;
	private MoveTimer _timer;

	public FishPanel() {
		super();
		_tank = new SmartRectangle(java.awt.Color.blue);
		_yellowfish = new Fish(java.awt.Color.yellow, _tank);
		_pinkFish = new Fish(java.awt.Color.pink, _tank);
		_timer = new MoveTimer(INTERVAL, this);
		this.setBackground(java.awt.Color.white);
		_tank.setBorderColor(java.awt.Color.black);
		_tank.setLocation(TANK_X, TANK_Y);
		_tank.setSize(TANK_WIDTH, TANK_HEIGHT);
		_yellowfish.setLocation(TANK_X, TANK_Y + TANK_HEIGHT / 2);
		_pinkFish.setLocation(TANK_X + TANK_WIDTH, TANK_Y + TANK_HEIGHT / 2);
		_timer.start();
	}

	public void move() {
		_yellowfish.move();
		_pinkFish.move();
		if (_yellowfish.getHeadX() > _pinkFish.getHeadX()) {
			_yellowfish.turnUp();
			_pinkFish.turnDown();
		}
		this.repaint();
	}

	public void paintComponent(java.awt.Graphics aBrush) {
		super.paintComponent(aBrush);
		java.awt.Graphics2D betterBrush = (java.awt.Graphics2D) aBrush;
		_tank.fill(betterBrush);
		_tank.draw(betterBrush);
		_yellowfish.fill(betterBrush);
		_pinkFish.fill(betterBrush);
	}
}

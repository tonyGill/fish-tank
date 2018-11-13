/**
 * Fish.java Models a fish in a fish tank.
 */
public class Fish implements Mover {
	private final int BODY_WIDTH = 34; // attributes

	private final int TAIL_WIDTH = 12;

	private final int FISH_WIDTH = 44;

	private final int FISH_HEIGHT = 20;

	public final static int MOVE_LEN = 5;

	private int _changeX, _changeY;

	private double _rotation;

	private SmartEllipse _body; // components

	private SmartArc _tail;

	private SmartRectangle _tank; // peer object

	public Fish(java.awt.Color aColor, SmartRectangle aRectangle) {
		// super();
		_changeX = MOVE_LEN;
		_changeY = 0; // fish swims horizontally
		_rotation = 0;
		_body = new SmartEllipse(aColor);
		_tail = new SmartArc(90, 180, java.awt.geom.Arc2D.PIE, aColor);
		_tank = aRectangle;
		_body.setSize(BODY_WIDTH, FISH_HEIGHT);
		_tail.setSize(TAIL_WIDTH, FISH_HEIGHT);
	}

	public void move() {
		int nextX = (int) this.getX() + _changeX;
		int nextY = (int) this.getY() + _changeY;
		if (nextX <= this.getMinBoundX()) {
			_changeX *= -1;
			nextX = this.getMinBoundX();
		} else if (nextX >= this.getMaxBoundX()) {
			_changeX *= -1;
			nextX = this.getMaxBoundX();
		}
		if (nextY <= this.getMinBoundY()) {
			_changeY *= -1;
			nextY = this.getMinBoundY();
		} else if (nextY >= this.getMaxBoundY()) {
			_changeY *= -1;
			nextY = this.getMaxBoundY();
		}

		if (_changeX < 0)
			this.setRotation(Math.PI);
		else
			this.setRotation(0);
		this.setLocation(nextX, nextY);
		if (_changeY < 0) { // For turning up
			this.setRotation(-Math.PI / 2);
		} else if (_changeY > 0) { // For turning down
			this.setRotation(Math.PI / 2);
		}
	}

	public int getHeadX() {
		int edge;
		if (_changeX < 0) // for the situation of right fish
			edge = (int) _body.getX();
		else // for the situation of left fish
			edge = (int) (_body.getX() + _body.getWidth());
		return edge;
	}

	public void turnDown() {
		_changeX = 0;
		_changeY = 5;
	}

	public void turnUp() {
		_changeX = 0;
		_changeY = -5;
	}

	public int getMinBoundX() {
		return (int) _tank.getX();
	}

	public int getMaxBoundX() {
		return (int) (_tank.getX() + _tank.getWidth() - FISH_WIDTH);
	}

	public int getMinBoundY() {
		return (int) _tank.getY();
	}

	public int getMaxBoundY() {
		return (int) (_tank.getY() + _tank.getHeight() - FISH_HEIGHT);
	}

	public void setLocation(int x, int y) {
		_body.setLocation(x + 5, y);
		_tail.setLocation(x, y);
	}

	public int getX() {
		return (int) _tail.getX();
	}

	public int getY() {
		return (int) _tail.getY();
	}

	public void setRotation(double aNumberDegrees) {
		_rotation = aNumberDegrees;
	}

	public double getCenterX() {
		return this.getX() + FISH_WIDTH / 2;
	}

	public double getCenterY() {
		return this.getY() + FISH_HEIGHT / 2;
	}

	public void fill(java.awt.Graphics2D aBrush) {
		aBrush.rotate(_rotation, this.getCenterX(), this.getCenterY());
		_body.fill(aBrush);
		_tail.fill(aBrush);
		aBrush.rotate(-_rotation, this.getCenterX(), this.getCenterY());
	}

	public void draw(java.awt.Graphics2D aBrush) {
		aBrush.rotate(_rotation, this.getCenterX(), this.getCenterY());
		_body.draw(aBrush);
		_tail.draw(aBrush);
		aBrush.rotate(-_rotation, this.getCenterX(), this.getCenterY());
	}

	public void setChangX(int i) {
		// TODO Auto-generated method stub
		_changeX = i;

	}

	public void setChangY(int i) {
		// TODO Auto-generated method stub
		_changeY = i;
	}

}

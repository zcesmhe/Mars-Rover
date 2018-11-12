package MarsRover;

public class Position {

	int x;
	int y;

	public Position(int x, int y) {

		//Initialising x and y coordinates
		this.x = x;
		this.y = y;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	//Move the position 1 unit to the right
	public void moveRight() {
		x += 1;
	}

	//Move the position 1 unit to the left
	public void moveLeft() {
		x -= 1;
	}

	//Move the position 1 unit up
	public void moveUp() {
		y += 1;
	}

	//Move the position 1 unit down
	public void moveDown() {
		y -= 1;
	}

	//Create a new Position object and contains the same x and y coordinates
	public Position createCopy() {
		return new Position(x, y);
	}

	//Check if two Positions have the same x and y coordinates
	@Override
	public boolean equals(Object object) {
		if (object instanceof Position) {
			return (this.x == ((Position) object).getX())
					&& (this.y == ((Position) object).getY());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int result = 19;
		result = 65537 * result + x;
		result = 65537 * result + y;
		return result;
	}

}

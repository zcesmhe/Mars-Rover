package MarsRover;
/*The rover class takes in a starting Position and directions as well as a 
 * sequence of characters which represent its movement 
 */
public class Rover {

	Position position;
	int direction; //Current angle the rover is facing (East = 0, N = 90 etc)
	String moveSequence;

	public Rover(Position position, int direction, String moveSequence) {

		this.position = position;
		this.direction = direction;
		this.moveSequence = moveSequence;

	}

	//Rotates the rover 90 degrees left
	public void turnLeft() {
		direction = (direction + 90) % 360;
	}

	//Rotates the rover 90 degrees right
	public void turnRight() {
		direction = (direction + 270) % 360;
	}

	/*Returns the Position of the rover if it were to move in its current 
	 * direction
	 */
	public Position getNextPosition() {

		Position positionCopy = position.createCopy();

		switch (direction) {

		case 0:
			positionCopy.moveRight();
			return positionCopy;

		case 90:
			positionCopy.moveUp();
			return positionCopy;

		case 180:
			positionCopy.moveLeft();
			return positionCopy;

		default:
			positionCopy.moveDown();
			return positionCopy;

		}
	}

	//Moves the rover 1 unit in its current direction
	public void move() {
		this.position = getNextPosition();
	}

	//Returns the move sequence of the rover
	public String getMoveSequence() {
		return moveSequence;
	}

	//Returns the current angle the rover is facing
	public int getDirection() {
		return direction;
	}

	//Converts the angle into a cardinal direction
	public String getCardinalDirection() {

		switch (direction) {

		case 0:
			return "E";

		case 90:
			return "N";

		case 180:
			return "W";

		default:
			return "S";

		}
	}

	//Returns the current position of the rover
	public Position getPosition() {
		return position;
	}

	@Override
	public String toString() {
		String s = "";
		s += position.getX() + " " + position.getY() + " "
				+ getCardinalDirection();

		return s;
	}

}

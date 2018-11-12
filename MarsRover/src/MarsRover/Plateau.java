package MarsRover;
import java.util.ArrayList;

public class Plateau {

	int height;
	int width;
	//List of positions occupied by a rover
	ArrayList<Position> occupiedPositions;

	public Plateau(int width, int height) {

		this.height = height;
		this.width = width;
		this.occupiedPositions = new ArrayList<Position>();

	}

	/*Moves a rover through its move sequence and returns TRUE if it completes
	 * the entire sequence or FALSE if the rover gets stuck (e.g reaches the 
	 * plateau boundary or hits another rover)
	 */
	public boolean moveRoverSuccessful(Rover rover) {
		//Adds the rovers current position to the occupied positions list
		occupiedPositions.add(rover.getPosition());
		
		for (char move : rover.getMoveSequence().toCharArray()) {
			
			switch (move) {

			case 'L':
				rover.turnLeft();
				break;
			case 'R':
				rover.turnRight();
				break;

			default:
				if(isMoveValid(rover)) {
					/*Removes the current position from the occupied list,
					 * moves the rover to its new position and adds new position
					 * to the occupied list
					 */
					occupiedPositions.remove(occupiedPositions.size()-1);
					rover.move();
					occupiedPositions.add(rover.getPosition());
					
				}
				else {
					return false;
				}
			}
		}
		return true;
	}

	/*Checks if the rovers next move is valid i.e it wont go out of bounds 
	 * or hit another rover
	 */
	public boolean isMoveValid(Rover rover) {
		Position nextPosition = rover.getNextPosition();
		return (!positionOutOfBounds(nextPosition)
				&& !occupiedPositions.contains(nextPosition));
	}

	//Checks if the position is outside of the plateaus boundaries
	public boolean positionOutOfBounds(Position position) {
		return (position.getX() < 0 || position.getX() > width
				|| position.getY() < 0 || position.getY() > height);
	}

	//Returns a list of positions currently occupied by rovers
	public ArrayList<Position> getOccupiedPositions() {
		return occupiedPositions;
	}
	
	//Checks the occupied list to see if the position is currently occupied
	public boolean isPositionOccupied(Position position) {
		return occupiedPositions.contains(position);
	}
}

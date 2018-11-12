package MarsRover;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//Scanner to read user input from the console
		Scanner scan = new Scanner(System.in);
		int height;
		int width;
		int numberOfRovers;
		//Map of results to be printed at the end
		LinkedHashMap<Rover, Boolean> results 
		= new LinkedHashMap<Rover, Boolean>();

		//Loops asking for plateaus coordinates that checks for a valid input
		while (true) {
			System.out.println(
					"Please enter the upper-right coordinates of the plateau in the form x y:");
			String coords = scan.nextLine();
			if (validPlateauCoords(coords)) {
				width = getPlateauCoords(coords)[0];
				height = getPlateauCoords(coords)[1];
				break;
			}
			System.out.println("Invalid input!");
		}

		//Initialising the plaeau
		Plateau plateau = new Plateau(width, height);

		/*Loop asking for the number of rovers to be used and the checking for a 
		 * valid user input
		 */
		while (true) {
			System.out.println("Please enter the number of rovers to be used:");
			String input = scan.nextLine();
			if (validNumberOfRovers(input)) {
				numberOfRovers = Integer.parseInt(input);
				break;
			}
			System.out.println("Invalid input!");
		}

		/*Looping through the number of rovers given by the user and asking
		 * for starting coordinates and move sequence
		 */
		for (int i = 1; i < numberOfRovers + 1; i++) {

			// Rover initialisation variables
			Position position;
			int direction;
			String moveSequence;

			//Checking for valid rover position and direction
			while (true) {
				System.out.println("Please enter rover " + i
						+ "'s starting position and cardinal" +  
						  " direction in the form x y d:");
				String input = scan.nextLine();
				if (validRoverInput(input)) {
					String[] roverInputs = input.split(" ");
					int x = Integer.parseInt(roverInputs[0]);
					int y = Integer.parseInt(roverInputs[1]);
					position = new Position(x, y);
					//Checks if starting coordinates are within the plateau
					if (plateau.positionOutOfBounds(position)) {
						System.out.println(
							"Coordinates are not located within the plateau!");
						//Checks if the position is currently occupied
					} else if (plateau.isPositionOccupied(position)) {
						System.out.println("Position is already occupied!");
					} else {
						direction = cardinalDirectionToInt(roverInputs[2]);
						break;
					}

				} else {
					System.out.println("Invalid input!");
				}
			}
			//Checks for a valid move sequence
			while (true) {
				System.out.println("Please enter the rovers move sequence: ");
				String input = scan.nextLine();
				if (sequenceIsValid(input)) {
					moveSequence = input;
					break;
				}
				System.out.println("Invalid input!");
			}
			//Creates a new rover from the users inputs
			Rover rover = new Rover(position, direction, moveSequence);

			/*The rover executes its move sequence on the plateau and the 
			 * results are stored in the results map
			 */
			if (plateau.moveRoverSuccessful(rover)) {
				results.put(rover, true);
			} else {
				results.put(rover, false);
			}
		}
		int index = 1;
		for (Rover rover : results.keySet()) {
			if (results.get(rover)) {
				//Successful rover output message
				System.out.println("Rover " + index + "'s position is: "
						+ rover.toString());
			} else {
				//Unsuccessful rover output message
				System.out.println("Rover " + index + " is stuck at position: "
						+ rover.toString());
			}
			index++;
		}

	}

	//Checks if the move sequence only contains 'L', 'R' or 'M'
	public static boolean sequenceIsValid(String moveSequence) {
		if (moveSequence.length() == 0) {
			return true;
		} else {
			for (char move : moveSequence.toCharArray()) {
				if (move == 'L' || move == 'R' || move == 'M') {
					return true;
				}
			}
		}
		return false;
	}

	//Check if the input is a valid integer
	public static boolean validNumberOfRovers(String numRovers) {
		try {
			Integer.parseInt(numRovers);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	//Checks if plateau coordinatesare valid integers
	public static boolean validPlateauCoords(String coords) {

		String[] coordsArray = coords.split(" ");

		if (coordsArray.length != 2) {
			return false;
		}
		try {
			Integer.parseInt(coordsArray[0]);
			Integer.parseInt(coordsArray[1]);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	//Return and array of the plateaus x and y coords
	public static int[] getPlateauCoords(String coords) {
		String[] coordsArray = coords.split(" ");
		int[] intCoords = new int[2];
		intCoords[0] = Integer.parseInt(coordsArray[0]);
		intCoords[1] = Integer.parseInt(coordsArray[0]);

		return intCoords;
	}

	/*Checks if the rover input is valid i.e x and y coords are integers and
	 * direction is 'N', 'E', 'S', or 'W'
	 */
	public static boolean validRoverInput(String roverInput) {
		String[] roverInputArray = roverInput.split(" ");
		if (roverInputArray.length != 3) {
			return false;
		}
		if (isValidDirection(roverInputArray[2])) {
			try {
				Integer.parseInt(roverInputArray[0]);
				Integer.parseInt(roverInputArray[1]);
				return true;

			} catch (NumberFormatException e) {
				return false;
			}
		}
		return false;

	}

	//Checks the direction is a valid cardinal direction
	public static boolean isValidDirection(String direction) {
		if (direction.equals("N") || direction.equals("E")
				|| direction.equals("S") || direction.equals("W")) {
			return true;
		}
		return false;
	}

	//Converts a cardinal direction into an angle (E = 0, N = 90 etc)
	public static int cardinalDirectionToInt(String direction) {

		switch (direction) {

		case "E":
			return 0;

		case "N":
			return 90;

		case "W":
			return 180;

		default:
			return 270;
		}
	}

}

package MarsRoverTest;

import org.junit.Test;

import MarsRover.*;
import org.junit.Assert;

public class PlateauTest {

	@Test
	public void testSuccessfulMoves() {
		// Create test rover 1
		Position position1 = new Position(1, 2);
		int direction1 = 90;
		String moveSequence1 = "LMLMLMLMM";
		Rover rover1 = new Rover(position1, direction1, moveSequence1);

		// Create test rover 2
		Position position2 = new Position(3, 3);
		int direction2 = 0;
		String moveSequence2 = "MMRMMRMRRM";
		Rover rover2 = new Rover(position2, direction2, moveSequence2);

		// Expected results for test rover 1
		String expectedOutput1 = "1 3 N";

		// Expected results for test rover 2
		String expectedOutput2 = "5 1 E";

		// Create plateau to test the rovers
		Plateau plateau = new Plateau(5, 5);
		plateau.moveRoverSuccessful(rover1);
		plateau.moveRoverSuccessful(rover2);

		checkCorrectOutput(rover1.toString(), expectedOutput1);
		checkCorrectOutput(rover2.toString(), expectedOutput2);

	}

	// Check if the rovers do not crash into each other
	@Test
	public void testRoverStuck() {
		// Create test rover 1
		Position position1 = new Position(0, 0);
		int direction1 = 90;
		String moveSequence1 = "MRMLMRM";
		Rover rover1 = new Rover(position1, direction1, moveSequence1);

		// Create test rover 2
		Position position2 = new Position(0, 0);
		int direction2 = 90;
		String moveSequence2 = "RMMLMM";
		Rover rover2 = new Rover(position2, direction2, moveSequence2);

		// Expected results for test rover 1
		String expectedOutput1 = "2 2 E";

		// Expected results for test rover 2
		String expectedOutput2 = "2 1 N";

		// Create plateau to test the rovers
		Plateau plateau = new Plateau(5, 5);
		plateau.moveRoverSuccessful(rover1);
		plateau.moveRoverSuccessful(rover2);

		checkCorrectOutput(rover1.toString(), expectedOutput1);
		checkCorrectOutput(rover2.toString(), expectedOutput2);
	}

	// Check if the rovers do not fall off the plateau
	@Test
	public void testRoverOutOfBounds() {
		// Create test rover 1
		Position position1 = new Position(0, 0);
		int direction1 = 90;
		String moveSequence1 = "MMMMMMMM";
		Rover rover1 = new Rover(position1, direction1, moveSequence1);

		// Create test rover 2
		Position position2 = new Position(0, 0);
		int direction2 = 0;
		String moveSequence2 = "MMMMMMMM";
		Rover rover2 = new Rover(position2, direction2, moveSequence2);

		// Expected results for test rover 1
		String expectedOutput1 = "0 5 N";

		// Expected results for test rover 2
		String expectedOutput2 = "5 0 E";

		// Create plateau to test the rovers
		Plateau plateau = new Plateau(5, 5);
		plateau.moveRoverSuccessful(rover1);
		plateau.moveRoverSuccessful(rover2);

		checkCorrectOutput(rover1.toString(), expectedOutput1);
		checkCorrectOutput(rover2.toString(), expectedOutput2);
	}

	// Check if the rovers do not fall off the plateau
	@Test
	public void test3SuccessfulRovers() {
		// Create test rover 1
		Position position1 = new Position(0, 0);
		int direction1 = 90;
		String moveSequence1 = "MMRMMLMLL";
		Rover rover1 = new Rover(position1, direction1, moveSequence1);

		// Create test rover 2
		Position position2 = new Position(2, 1);
		int direction2 = 0;
		String moveSequence2 = "MMLMMRM";
		Rover rover2 = new Rover(position2, direction2, moveSequence2);

		// Create test rover 3
		Position position3 = new Position(5, 4);
		int direction3 = 180;
		String moveSequence3 = "MLMRMRML";
		Rover rover3 = new Rover(position3, direction3, moveSequence3);

		// Expected results for test rover 1
		String expectedOutput1 = "2 3 S";

		// Expected results for test rover 2
		String expectedOutput2 = "5 3 E";

		// Expected results for test rover 3
		String expectedOutput3 = "3 4 W";

		// Create plateau to test the rovers
		Plateau plateau = new Plateau(5, 5);
		plateau.moveRoverSuccessful(rover1);
		plateau.moveRoverSuccessful(rover2);
		plateau.moveRoverSuccessful(rover3);

		checkCorrectOutput(rover1.toString(), expectedOutput1);
		checkCorrectOutput(rover2.toString(), expectedOutput2);
		checkCorrectOutput(rover3.toString(), expectedOutput3);
	}

	// Checks the string output against the expected output
	private void checkCorrectOutput(String actualResult,
			String expectedResult) {

		Assert.assertEquals(actualResult, expectedResult);
	}

}

## Software Quality and Testing Report ##

*Matthijs Halvemaan	- 4353803*

#### Exercise 1 ####
The overall test coverage as of this exercise is equal to 74.6%.
In this, two classes that have a very poor test coverage are the class “*Board.java*” and the class “*PacKeyListener.java*” with a test coverage of 33.9% and 33.3% respectively.
The coverage of "*Board.java*" is lower, because these end-to-end test do not use, or cover, most methods that are present in "*Board.java*".
As for “*PacKeyListener.java*”, this class is simply very hard to test using automated tests.

#### Exercise 2 ####
The test have been added into a new class, which has been named “ExtendedLauncherSmokeTest.java”.
In this class, the test are based on the stories as stated in the JavaDoc, and as follows:  

- The test “startTest” is based on Story 1, Scenario S1.1.
- The test “consumePelletTest” is based on Story 2, Scenario S2.1.
- The test “emptySquareTest” is based on Story 2, Scenario S2.2.
- The test “playerDiesTest” is based on Story 2, Scenario S2.3.
- The test “suspendTest” is based on Story 4, Scenario S4.1.
- The test “resumeTest” is based on Story 4, Scenario S4.2. 

#### Exercise 3 ####
Personally, I found Story 2, Scenario S2.4 nearly impossible to test, especially when the board gets bigger and enemies get involved. 
It would be doable when making a smaller board without enemies, just for the test. 
However it would require a very precise knowledge of the board and the movement of the ghost, as well as very precise timing to win the game during an automated test on a bigger board. 
Which would also lead to a very long test, given all the moves the player needs to make to win the game.

#### Exercise 4 ####
Unfortunately, I was unable to test the Scenarios given in Story 3. 
This is because I was unable to find an easy method to get access the ghosts and influence their behavior. 
Also most scenarios are very dependent on being able to react to the ghost, in order to walk into them, which would, much like Scenario S2.4, would require a very precise knowledge of the game as well as very precise timing to test. Lastly, some scenarios describe something the player describes visually, which makes it harder to write automated tests for these scenarios.
  
#### Exercise 5 ####
![Table 1](http://i.imgur.com/UuPy6y2.jpg)  

<h6></h6>

#### Exercise 6 ####
Included underneath is the most important code from this exercise.

    @RunWith(Parameterized.class) 
    public class WithinBordersTest {
    	private int x, y;
    	private boolean expectedOut;
	...
		public WithinBordersTest(int xIn, int yIn, boolean out) {
			this.x = xIn;
			this.y = yIn;
			this.expectedOut = out;	
		}
	...
		@Test
		public void withinBordersTest() {
		...
			boolean out = board.withinBorders(x, y);
			assertEquals(expectedOut, out);
		}
	...
	...
		@Parameters
		public static Collection<Object[]> data() {
			Object[][]	values = {
					{ 0, 2, true },
					{ -1, 3, false },
					{ 5, 1, false },
					{ 4, 4, true },
					{ 2, 0, true },
					{ 3, -1, false },
					{ 1, 5, false },
					{ 4, 4, true }
			};
		
			return Arrays.asList(values);
		}

	}

#### Exercise 7 ####
There were only two check-style warnings that I was unable to eliminate.
Both of these were warnings stating that a method was too big.
In the first case I simply refused to eliminate the warning because it would cause the method to become very unclear.
In the second case, even if I would accept the method to become unclear, I was unable to eliminate the warning because I could not figure out a way to shorten the method.  

After writing the new tests for this assignment, the test coverage has increased from 74.6% to 78.5% which is a slight improvement, but it is not a big enough lift to get the coverage above 80% which has been the excepted coverage (for non-GUI classes).  

As for my server integration and commit behavior, my opinion is that when I started Part 1, my commits happened a little to few and far between. And, with a few exceptions, I feel that behavior has improved when nearing the end of this Part.
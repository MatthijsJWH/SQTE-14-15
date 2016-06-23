## Software Quality and Testing Report ##

*Matthijs Halvemaan	- 4353803*

### Exercise 16 ###
![State Machine Model](http://i.imgur.com/bkXRHw3.jpg)

### Exercise 17 ###
![State Transition Tree](http://i.imgur.com/hJsy8b5.jpg)

![Test cases derived from the tree.](http://i.imgur.com/xJw3KEU.jpg)

In the class GameTest the above tests are implented in the following way:
- Test 1 is implemented as startTest() (Lines 58 - 65).
- Test 2 is implemented as pauseTest() (Lines 73 - 81).
- Test 3 is implemented as resumeTest() (Lines 90 - 99).
- Test 4 is implemented as singleMoveTest() (Lines 110 - 119).
- Test 5 is implemented as both levelWonTest() and levelLostTest() (Lines 125 - 160).

### Exercise 18 ###
![State Transition Table](http://i.imgur.com/Gur9yHA.jpg)

Apart from the "don't care" situations all tests derived from the above table were already derived from the State Transition Tree.
The "don't care" situations in the table are all covered in the class Part2GameTest except for 1, which was already implemented in GameTest.

<h6></h6>

### Exercise 20 ###
The updates provided for the scenarios from doc/scenarios.md are the following:

Updated Scenario S2.5:
	Scenario S2.5: Player completes the level, extends S2.2
	When	I have eaten the last pellet;
	Then	I complete the level.

Added Scenario S2.6:
	Scenario S2.6: Player wins, extends S2.5
	When	I have completed the last level;
	Then	I win the game.

### Exercise 21 ###
![Multi Level State Machine Model](http://i.imgur.com/AyDjery.jpg)

### Exercise 29 ###
Looking back at my results, I considered the lab work a very educative and nicely organized project. There were a couple of little things I found annoying though, to say the least. First of all I was not very fond of the fact that my partner left me just after the deadline of getting a new group. Secondly, I found it annoying that, even though I was promised to possibly get new partner, I never received any word later on. This however does have a very positive upside though, at least in my mind. I found that I learned a lot doing this project by myself, especially when judging by the results of the first 2 assignments. And, except for the last assignment, I never got into any trouble with making the deadlines. The last thing I found a little annoying was that I was constantly reminded that I was not using branches and pull requests even though I was working alone. But all in all, I believe that I am now able to test projects effectively, now the importance of testing, and learned a little bit more about using git as a tool.
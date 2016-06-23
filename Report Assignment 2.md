## Software Quality and Testing Report ##

*Matthijs Halvemaan	- 4353803*

#### Exercise 9 ####
The following tests are the good weather tests, and are all located in the MapParserTest class:
- parseMapBoardTest (Lines 46 - 51).
- parseMapLevelTest (Lines 58 - 62).
- parseWallTest (Lines 68 - 72).
- parseGroundTest (Lines 78 - 82).
- parsePelletTest (Lines 88 - 93).
- parseGhostBoardTest (Lines 99 - 104).
- parseGhostLevelTest (Lines 110 - 115).
- stringListTest (Lines 170 - 181).
- inputStreamTest (Lines 203 - 211).

#### Exercise 10 ####
The following tests are the bad weather tests, and are all located in the MapParserTest class:
- unknownCharTest (Lines 121 - 124).
- nullListTest (Lines 130 - 133).
- emptyListTest (Lines 138 - 142).
- emptyStringTest (Lines 148 - 152).
- differentSizesTest (Lines 158 - 163).
- invalidInputStreamTest (Lines 192 - 196).

<h6></h6>

#### Exercise 11 ####
|        | Player                            | Ghost                             | Pellet                                           |
|--------|-----------------------------------|-----------------------------------|--------------------------------------------------|
| Player | No Interaction should take place. | Player dies / Game Over.          | Player Score Increases + Pellet Leaves the grid. |
| Ghost  | Player dies / Game Over.          | No Interaction should take place. | No Interaction should take place.                |
| Pellet | Pellet can never be the mover.    | Pellet can never be the mover.    | Pellet can never be the mover.                   |

#### Exercise 12 ####
All tests in the classes CollisionsTest, PlayerCollisionsTest and DefaultPlayerInteractionMapTest are derived from the table shown in the exercise above.
  
#### Exercise 13 ####
As of now, I couldn't get a parallel test suite to work properly, leading me to make the design choice to create abstract test methods in the CollisionMapTest to ensure that both PlayerCollisionsTest and DefaultPlayerInteractionMapTest contain all test cases that are needed for complete branch coverage based on the table in exercise 12.

<h6></h6>

#### Exercise 14 ####
The new test coverage stands at 88.2% line-coverage, within this 88.2% both PlayerCollisions and DefaultPlayerInteractionMap are tested 100%, Only CollisionInteractionMap is not tested completely at 94.7% and therefore still has room for improvement. 
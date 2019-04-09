## Use Cases

1. Place a block that the player is not able to go through, but must go over or below instead.
2. Choose a new image to use for an enemy in the authoring environment.
3. Make user's character take damage and be pushed backwards when hit by an enemy.
4. Change the background to signify the start of a new level or time period.
5. Change the number of lives that a character begins a level with to better balance its difficulty.
6. Create new stages for different layers, and write Groovy logic for each stage.
7. Create objects that were drawn on the canvas, and modify their instances' properties.
8. Browse created objects in the objects library, and be able to drag and drop them on the canvas.
9. Write independent logic for each object's instance.
10. Instantly run the game from within the authoring environment.
11. Be able to update items on the canvas, and hot-reload the game while it's running.
12. Be able to debug running games, and see their debugging statements or output displayed in the bottom terminal in the authoring environment.
13. Be able to package and export games that were authored to load directly from the game center.
14. Create their own interactions between objects using Groovy or combinations of preset actions. 
15. Define what happens as a result of a collision between two types of objects (design various collision response subclasses).
16. Designate a key for the player to add to its key listeners and a response for the engine to commit on that key click
17. Create a subclass of a predefined class in the author e.g. goomba in MARIO (subclass the enemy class)
18. Set images associated with health levels of hero
19. Allow user in author to set gravity constant which affects physics in engine
20. Reflectively create map of key inputs mapped to hero direction vector changes and hero specialty actions (such as shooting a missile)
21. Reflectively create map of object tuples with associated collision Responses
22. Create a doubly nested loop that uses the object tuple key to get the collision Response subclass, then perform .respond
23. Design direction vector for GameObjects, and update this in the main Controller method according to either the key input or counter
24. Create and regulate list of actively displayable objects in Controller, updating it with every newly created element (such as a powerup or missile)
25. Save the status of a current game to a JSON file
26. Load a saved game state and continue editing or playing
27. Pick a game to play in the player
28. Change any fonts/colors in the player or authoring environment (e.g. have pre-set light/dark modes and custom themes)
29. Track and display high scores for each game
30. Create and regulate a list of "favorite" games
31. Change the image of the user's character based on if it is in the air (jumping), in water, moving, etc. 
32. Have various permeabilities with different types of blocks (water, air) that will incorporate changes to the physics (velocity, acceleration)
33. Extend the option to the author to have the game be auto-scroll with a scroll speed or character-defined scroll 
34. Display the result of the powerup once it collides with the character (fireball ability, extra life, bigger)
35. Ability to choose templates of the game (top-down vs. left-right) and have it reflected within the game engine.
36. Display the destruction of an enemy (such as squashing a goomba)
37. Detect collisions using the x and y positions of the box of each image 
38. Author sets the jump velocity (the initial velocity mario jumps with), which affects how high and far he can jump
39. Create a new type of Block with specified width, height, number of hits, and special effects that result from it being potentially being destroyed
40. Set the order of operations with which the objects are updated through the Controller
41. Create a power-up that, when picked up by the player hero, allows them to throw a projectile (which is its own object of type GameObject). Ex: the fireball power-up in Mario.
42. With a top-down game, allow the player to control the hero's movement in the four cardinal directions using binded keys of the game author's choosing.
43. During playing, the game should display a message or splash screen upon completion of a level. The level goal is determined by the author of the game in the authoring environment.
44. Properly detect when the hero collides with an enemy in a way that does not kill the enemy (i.e. runs into it from the side). In this case, the hero should lose hp (amount specified by the game author) and die if they run out of health.
45. If the player hero runs out of health points, the game should display a Game Over splash screen. If the game implements a lives system, the game should allow the player to try again from the most recent checkpoint or level start.
46. The user wants to rate a game 4/5 stars -> the rating shows up in real time
47. The user wants to favorite a game -> the game's thumbnail moves in real time to the "Favorites" section of the gamesRegion
48. The user wants to play a game -> they click on the thumbnail of the game -> the image, description, and gameOptions box appear in the descriptionRegion -> they click "Play" and a new window appears that is running the engine
49. The user wants to build a game level -> they click on the thumbnail of the game -> they click "Build" in the gameOptions box and a new window appears that is running the game authoring environment
50. The user wants to play 2 games at once -> they click the thumbnail of the first game, and then click "Play" in the gameOptions box -> they go back to the game choosing screen and click the thumbnail of the second tame, and then click "Play" in that gameOptions box.

use cases for player:

1. User wants to rate the game Scrolly Bird 4/5 stars.
* user runs program--list of games appears in gamesRegion on right side of UI
* user clicks on the Scrolly Bird thumbnail in the gamesRegion -> the Scrolly Bird info
appears in the descriptionRegion
* user clicks on the "Rate" button in the gameOptions bar in the descriptionRegion
* the game's rating updates in real time underneath the Rate button.

2. User wants to play the game Luigio Scroller.
* user runs program--list of games appears in gamesRegion on right side of UI
* user clicks on the Luigio Scroller thumbnail in the gamesRegion -> the Scrolly Bird info
appears in the descriptionRegion
* user clicks "Play" button in the gameOptions bar
* the Player opens a new window that is running the engine and begins the Luigio Scroller
game

3. User wants to favorite Scrolly Bird.
* user runs program--list of games appears in gamesRegion on right side of UI
* user clicks on the Scrolly Bird thumbnail in the gamesRegion -> the Scrolly Bird info
appears in the descriptionRegion
* user clicks "Favorite" button in the gameOptions bar
* the game's thumbnail appears in the Favorites section of the gamesRegion.

Example Code:
* Main.start() -> PlayerStage.makeStage() -> gamesRegion.buildGroup() -> places all the
Thumbnail objects in the ScrollPane of the gamesRegion.
* gamesRegion.buildGroup() -> makeHeader(true), makeThumbnails(true), makeHeader(false), 
makeThumbnails(false). this sets up the "Favorites" header and inserts the thumbnails
for all the favorited games, then it does the same for the "Games" header and all of
the games.
* user clicks on Scrolly Bird thumbnail -> descriptionRegion.updateRegion("Scrolly Bird"). 
this places the image, the description, and the gameOptions box in the descriptionRegion.
* user clicks on the "Favorite" button -> gamesRegion.updateRegion. the global variable
myFavorites has just changed, so the gamesRegion will clear its contents and rebuild its
headers and thumbnails just like in Step 2, except with a newly updated myFavorites list.
the button click will have added "Scrolly Bird" to the ArrayList myFavorites.

4. User wants to run both Scrolly Bird and Luigio Scroller at once.
* user runs program--list of games appears in gamesRegion on right side of UI
* user clicks on the Scrolly Bird thumbnail in the gamesRegion -> the Scrolly Bird info
appears in the descriptionRegion
* user clicks "Play" button in the gameOptions bar
* the Player opens a new window that is running Scrolly Bird
* user moves back to the game choosing region of the Player and clicks on the Luigio
Scroller thumbnail in the gamesRegion -> the Luigio Scroller info appears in the 
descriptionRegion
* user clicks "Play" button in the gameOptions bar
* since the play button simply opens up a new stage, the old stage will continue playing
in the background, so the user will be able to see both games being run at the
same time

5. User wants to build a level in Scrolly Doodle and then play it.
* user runs program--list of games appears in gamesRegion on right side of UI
* user clicks on the Scrolly Bird thumbnail in the gamesRegion -> the Scrolly Bird info
appears in the descriptionRegion
* user clicks "Build" button in the gameOptions bar
* the Player opens a new window that is running the game authoring environment with
Scrolly Doodle
* user builds the level however they want and clicks save
* user exits out of the authoring environment
* user clicks "Play" button in the gameOptions bar
* the Player opens a new window that is running Scrolly Doodle. the user can now play
their level by loading it in.

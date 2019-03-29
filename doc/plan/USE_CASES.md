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
VOOGASalad : Design Plan
===

### Members
Januario Carreiro (jjc70)

Anshu Dwibhashi (ad353)

Carter Gay (cag65)

Hunter Gregory (hlg16)

Ian Hanus (ih52)

Daniel Kingsbury (dpk14)

David Liu (dwl23)

Pavel Pivarshev (pap27)

Duc Tran (dt135)

Jonathan Yu (jy178)

### Introduction
1. Problem Solved by Program
* Create a flexible game authoring environment, engine, and player that will allow users to create and play as many variations of sidescrolling games as possible with minimal programming.

2. Primary Design Goals
* Create a flexible architecture that allows for the seamless integration of additional features
* Minimize and simplify any programming users must undertake
* Provide significant built-in functionality while still  allowing for the flexibility to create dynamic and exciting games of the side-scrolling genre

3. Primary Architecture of the Design 
* Separate the authoring environment from the player and engine using the data manager
* Have the data manager save and load configurations that correspond to game settings and initial states, editable in the authoring environment and playable in the player.
* Use the launcher as a main to allow the user to choose between playing and creating a game. 
* Have the player pass the engine a state, and the engine pass back the next state

4. Game Genre: Scrolling-Platformer
* Pros:
    * Since the concept of scolling-platformer is relatively simple, this can allow for a variety of addional features and concepts to be added like networking or scripting custom behaviors.
    * Variety of games and examples to potentailly implement.
    * We can use our experiece from the first game project with its collision interation.
    * Can also integrate other genres into it like RPGs or Puzzles.
* Cons: 
    * Might not be able to create varied enough games to show off our design.
    * Not as exciting or unique as other genres.

### Overview

The general design of our program is to create an authorship environment that will have access to a data writer and reader, allowing the user to load in and write .json files that characterize games. These files can also be loaded by the player class, which creates its own instance of the engine. This will allow for the running of mutliple players at once, with each having its own engine. The game engine provides a general framework that when integrated with the specific game data file will allow for the running/playing of that game. The engine will also have a data writer and data saver to load in and parse information about games as well as save the current state of games that are being played.

Following the above plan, we will create 5 separate modules: the player, the engine, the authoring environment, the data manager, and also a launcher that will allow the user to navigate between the player and authoring environment as well as see requisite information about different games. Below is an individual description of each of the modules. 

1. Authoring Environment
* Purpose: Allow the user to create entirely customizable new games and levels without having to write any code. 
* Collaboration (API focus): Uses the data manager's API to load and write game authoring environment. Its own API will make it available to be run through the launcher, using launchAuthor() or a similar method name.
2. Player
* Purpose: Allow players to load packaged games and play them.
* Collaboration: Must agree on a common class structure with the authoring environment, so that when the saved game is loaded via GSON, it's capable of being executed from within player.
3. Engine
* Purpose: Housing the models of the game and the objects associated with it as well as the controller that mediates the view and model. Will not contain any JavaFX implementation. It will supply the player with the "next game state" based on the models, given the "current game state" from the Player. 
* Collaboration (API focus): Connects the Controller (which is a part of the Engine) with the Player so that it can receive the current state and return the next state. Interacts with the Data Manager when saving or loading a .json file in changing the objects to a viable .json format agreed upon.
4. Data Manager
* Purpose: The data manager creates a level of separation between the authoring environment and the player/engine. It also allows the "publishing" of games, saving setups created in the authoring environment to be tested and played in the player. This will be accomplished using the Gson library.
* Collaboration: API involving saveGame() that allows either the engine or the authoring environment to save the current state of a game as a .json file to be loaded in elsewhere. On the other side, there must be a loadGame() that takes in the .json file and provides all of the objects necessary to represent and play the game. This will be called by the authoring environment and the player/engine combination.
5. Launcher
* Purpose: Allow the user to choose whether they will author or play a game. 
* Collaboration: Would not have any external API, but would be able to access the authoring environment and player.

Illustration of Module Relation: 
![](https://i.imgur.com/gTr2S18.png)


### User Interface
#### Authoring Environment
The authoring environment looks like this:
![](https://i.imgur.com/mV4AP4S.png)

The user will interact with the tools to be able to create objects and place them on the canvas. They'll be able to modify the objects' instances' properties in the properties pane on the right. They'll be able to browse all the objects that they've defined in the object library, so that they can drag and drop them onto the canvas. They'll be able to write code (Groovy) to add logic to their objects as well as scenes. Lastly, they can run, debug, save and export their games using the buttons in the pane in the bottom right corner.

Any erroneous values added in the properties pane would be displayed as tooltips. Any scripting errors that are encountered in runtime will be displayed in the output tab of the terminal on the bottom.


#### Player
The game selection screen for the player will look similar to this:

![](https://i.imgur.com/3ygoaiO.jpg)

And when you select a game, the screen will look like this:

![](https://i.imgur.com/FJlPY7f.jpg)

The user can click on any of the games on the scrolling list of visuals on the right-hand side to get a description of the game as well as a list of high scores, a button to rate the game, and a button to play the game. Furthermore, the user will have the ability to "favorite" a game by clicking on the heart. This will make it so that the game always appears at the top of the list on the right. The list of games/descriptions will not be hard-coded but instead will be loaded into the player, making it easy to create new games and having them show up in the player. 

There are many cases where an error is reported to the user. If for some reason the game does not load, the an error will show up on screen. If basic functions such as "favorite" or high scores list is not being updated, the user will also get an error. Also, if the game stops working for any number of reasons, the user will get an error from the player.


### Design Details 

1. Authoring Environment
    
    The authoring environment, which will be launched through its API by the launcher, will have two sub-modules: the visualization and the backend. The visualization will allow users to place visual blocks, and the backend will create the objects with the requisite information for each (position, hit points, etc.) so that it can be saved to a .json file through the data manager API. 

2. Player

    The player will function as the view for the engine as well as have several social functions. The user is able to select a game from a visual list of games that is loaded from resources and will be able to launch the game. Additionally, the player will keep track of games' high scores through successive runs, locally at first, and through the integrated social hub in the complete implementation. The player will save the user's current checkpoint to a file in the resources folder(as to make it harder for the user to access it) and reload from the checkpoint. On a similar note, the user will be able to reload from the last checkpoint without qutting the player. If the game is a sidescroller without checkpoints, the user will be able to restart without qutting the player. The user will also be able to switch games without having to quit the player. This will stop the current instance of the game engine and load a different game into the game engine if the user wants to play a different game.

    The player will mostly be interacting with the engine and data manager, and will be controlled from the Launcher.

    There are several extensions that will improve the player, including networked games and social hub. For the social hub especially, the player will be importing data from a server---no longer will changes need to be saved locally. A user can make an account on one computer and log on in another computer and continue where he/she left off. This will not require communicating with other modules.
    
3. Engine

    The engine functions as both the Model and Controller in the MVC pattern that is responsible for running any given game. On its own, the engine provides a general framework for a scrolling-platformer game (our chosen genre), including an inheritance hiearchy for game objects (GameObject superclass) and additional classes for physics/collisions. Examples of objects defined within the engine are enemies of various types, the player-hero, blocks, power-ups, and ballistic projectiles.
    
    To start a new game, the Player module will read the authored game data file (using the Data Manager) and instantiate all game objects (defined in the Engine) with the details/functionality specific to that game. Additionally, a new instance of the Controller will be created to connect the View (Player) to the Model (rest of the Engine).
    
    To run the game, the Player will call upon the Engine Controller to update each game objects' state upon each iteration of the game loop. These updates will taken into account "automatic" movement, user input, and collisions. In continuing the game until its end, the Player will utilize various classes and functionality in the Engine, such as levels and game over utilities. As a separate interaction with the Player, the Engine will handle key inputs (KeyCode) specific to what the player wants through a method within the EngineController.
    
    Additionally, the Engine will be integrated with the Authoring Environment to allow for proper display of the game/level as its being created and for the ability to test authored games "on-the-fly" (during creation of the game).

4. Data Manager
The data manager will be fairly straightforward: using Gson, the game information will be saved using the Data Manager's saveGame() defined below in the API. Then, when it is time for that game to once again be loaded into either the player or the authoring environment, the appropriate space will call loadGame() to get the objects and their information back in the correct positions and states.
5. Launcher
The launcher allows the user to choose whether they will be creating a game or playing an existing game. This will act as the main class, and will be able to create new authoring environments and players. The creation of this module will allow us to keep the player and the authoring environment separate, while still allowing them both to be easily accessible by the user. 

### Example games

1. Mario
Mario is one of the defining games of the sidescrolling genre. It will showcase the worldbuilding functionallity that we will include in our authorship environment, creating structure and adding enemies as well as goals. This will also demonstrate our ability to program interactions and collisions by things such as colliding with an enemy taking away a life and picking up a certain powerup giving the character a power.
2. Flappy Bird
Flappy Bird adds a new element to the sidescrolling genre: constant movement. This will show our authoring flexibility by adding new parameters to the game, enabling not only Flappy Bird to work but also most other games in the "endless runner" genre.
3. Geometry Wars
This game will demonstrate how we can use the scrolling concept to create games that are not just platformers in our authoring enviroment. Instead of jumping on and running across platforms, authors will have to design obstacles that the player and enemies will have to navigate around. 

### Design Considerations 

1. Authoring Environment
The authoring environment has to be in agreement with the data manager and player regarding how information will be stored in classes. In other words, the class structures have to be the exact same, so that the author and player can read and write from binary files without having to worry about the internal structure of the data. We were initially considering developing our own scripting language to define logic, but ended up deciding to use Groovy so that we don't waste a lot of time coming up with a parser. We also have to decide whether we'll allow developers to design assets or just be able to import and export assets from other graphic design programs such as photoshop.

2. Player
The player is a pretty simple feature overall maybe compared to the engine or authoring environment. UI-wise, it needs a way for the user to choose games to play/build from an arrangement that is functional (the game choosing state), and then a way to run either the game engine or the authoring environment. The first thing that happens in terms of the entire project is running Main.java, which sets up the PlayerStage, which is the game choosing state. The Player doesn't really have its own Controller-type class, unless you count the PlayerStage. The PlayerStage initializes any/all new windows (Authoring Environment/Engine) and holds all the information about how to do that. There is one hierarchy design in effect at the moment, and that's for Regions--the graphical compartments of the game choosing state. Theres is a gamesRegion, which stores the games' Thumbnails, the titleRegion, which simply displays the title of either the program or the chosen game, and the descriptionRegion, which displays the image, description, and gameOptions box of the chosen game. Each of these classes has a graphical setup and a way to return its organizational component (a Group object). 

3. Engine 

We decided to give the Player access to a Controller package in the Engine, which contains methods that establish the order of calls to other Engine methods (such as moving the hero, updating object positions, regulating collisions, performing special actions, etc.). For instance, we have a map of GameObject tuples keyed to Responses, which are unique modifications to these GameObjects prompted by the collision of the particular pair. The Player loop takes a key input, then using this key input calls a method within the Controller which uses a map to perform the corresponding method on the player's hero. The Player then calls a method in the Controller that updates the states of all the GameObjects (which are contained in a List). This List is mapped 1:1 to a List of ImageViews in the frontEnd, which removes all JavaFx dependency from the Engine. Apart from knowledge of the different varieties of GameObjects, these are the only two connections between the Player and Engine, leaving the Engine effectively very independent. 

We designed the superClass GameObjects, which contains a location, direction vector, speed, and counters to break their motion into stages, etc. in addition to methods which can modify these elements in response to other variations in the scene (such as Collisions). GameObjects is very concise, which maximizes its subclass extensibility (it will extend to create subclasses such as Enemy, Hero, Block, and Ballistic, which are all movable and can experience collisions). We also planned on a Weapons class, which can be owned by a GameObject, and is used to create Ballistics.  


4. Data Manager
First, we decided on a file format to manage our data. While members of the group were already familiar with XML from past projects, we ultimately decided to use JSON in combination with Gson due to the ease of serialization and deserialization of Java objects. This will require becoming familiar with the Gson library; however, the decision will result in a much simple parser. Naturally, the schema of our JSON files will directly rely on the design of the various classes used to construct objects present in a game. We assumed that the user will manually choose to save a game rather than saving changes whenever a change in the game state occurs. This would not be adequate if we were implementing networked games where changes in the game state must be communicated immediately.

5. Launcher
There is not much consideration for this class. We will use JavaFX to present two buttons and once we select one, the launcher will close and the corresponding program selected will open.


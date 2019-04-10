PROJECT PLAN
===

### High-level plan for how team will spend its time to complete the program:
The team will begin by focusing on creating the hierarchies responsible for building simple scrolling platform games first. We will focus on developing the data structure as quickly as possible to allow each individual module to do complete testing: the game authoring environment will be able to publish and load games, and the player will be able to load the relevant information to visualize the current frame and pass the state of which are EngineController, GameObject, and Collision. We will then focus on creating our first example game, to see what problems we run into along the way while trying to use the broad genre-based game engine for a specific use. After this, we will progress on towards challenge features and making the user interface look better and cleaner.

**Game: Mario**
Features/Goals
1. Scrolling
    * Auto-scrolling
    * Character-defined scrolling
2. Physics
    * Moving around
    * Jumping, falling (acceleration, velocity)
    * Colliding with enemies and blocks (resulting physics)
    * Permeability - being in water, air, or land
3. Some Powerups
    * Increase Size
    * Restore Life
    * Give a weapon/attack
    * Give the ability to shoot a "ballistic" (ex: fireball)
4. Interactions
    * Getting Powerups
    * Colliding with enemies and blocks (resulting game events, ex: losing hp or spawning a power-up)
    * Completing a goal
5. Changing Levels/Scenes
    * Reaching the end of one level and progessing to another.
    * Going down a pipe or through a portal(This isn't a new level, but a part of the current level that can't be reached or seen otherwise)
6. Displaying Game Info
    * Showing high scores from recently played games in real time
    * Showing description and image relating to game
    * Rating and Favoriting the game

## Members
* Januario Carreiro (jjc70)
    * Responsibilities:
        * Primary: Player
        * Secondary: Data, Integrating Player and Data
    * Features:
        * First sprint: Displaying HUD and saving preferences. 
        * Second sprint: Implement features of a social center
* Anshu Dwibhashi (ad353)
    * Responsibilities:
        * Primary: Authoring Environment
        * Secondary: Networking, real-time updating, databases. (Advanced features)
    * Features:
        * First sprint: Be able to author games with scripted logic.
        * Second sprint: Be able to save scores and other metadata on a remote machine and and play multiplayer games.
* Carter Gay (cag65)
    * Responsibilities:
        * Primary: Data Management
        * Secondary: Challenge Feature
    * Features:
        * First sprint: Allow the user to save games and then load games at later times.
        * Second sprint: Allow for multiplayer games to be played using networking.
* Hunter Gregory (hlg16)
    * Responsibilities:
        * Primary: Engine - Controller and Collisions 
        * Secondary: AI and More Interactions
    * Features:
        * First sprint: Designing Engine Controller, using Engine models to update Scene, Collision Mapping
        * Second sprint: Creating Action classes to regulate AI behavior according to local conditions
* Ian Hanus (ih52)
    * Responsibilities:
        * Primary: Authoring environment, saving and loading
        * Secondary: Authoring environment, roiGUI
    * Features:
        * First sprint: Saving the configurations of blocks and enemies to be loaded into the player
        * Second sprint: Allowing users to load files back in to be edited again
* Daniel Kingsbury (dpk14)
    * Responsibilities:
        * Primary: Engine 
        * Secondary: AI
    * Features:
        * First sprint: Designing Engine Controller, using Engine models to update Scene, Collision Mapping
        * Second sprint: creating Action classes to regulate AI behavior according to local conditions
* David Liu (dwl23)
    * Responsibilities:
        * Primary: Game Engine
        * Secondary: Live Game Editing
    * Features:
        * First sprint: Designing the GameObjects Hierarchy, implementing GameObjects (focusing on PowerupItem, Ballistics, and the related Powerup Interface), basic Physics - movement, jumping
        * Second sprint: Incorporating the Engine within the Authoring Environment to allow for simultaneous editing and playing of a game
* Pavel Pivarshev (pap27)
    * Responsibilities:
        * Primary: Player front end
        * Secondary: Player back end
    * Features:
        * First sprint: Building the game choosing state and being able to run the game engine and authoring environment
        * Second sprint: Adding to the graphical Components of the Engine and Authoring environment
* Duc Tran (dt135)
    * Responsibilities:
        * Primary: Authoring Environment Backend
        * Secondary: Debugging and Refacotoring
    * Features:
        * First sprint: Placing multiple objects and keeping track of them. Using the data saver to save game files.
        * Second sprint: Extra challenges features like networking or live game editing.
* Jonathan Yu (jy178)
    * Responsibilities:
        * Primary: Game Engine (backend)
        * Secondary: Linking with Player (i.e. helping with game loop)
    * Features:
        * First sprint: Creating the GameObject hiearchy (focusing on the Character sub-hiearchy and Block classes), working on the Engine Controller, helping with Collisions and Physics as needed
        * Second sprint: Extending the Engine to run games beyond just scrolling platformers (i.e. top-down shooters and driving games), increasing the complexity of possible games (more in-game features, physics, etc.)
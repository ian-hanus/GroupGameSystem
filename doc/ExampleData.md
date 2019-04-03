## Example data for a game
The follow JSON file illustrates how we're tentatively going to save data for a particular game:

```
{
  "assets": [
    {
      "src": "\/path\/to\/graphic1.png",
      "type": "image"
    },
    {
      "src": "\/path\/to\/graphic2.png",
      "type": "image"
    },
    {
      "src": "\/path\/to\/audio.wav",
      "type": "audio"
    }
  ],
  "objects": [
    {
      "graphic": "\/path\/to\/graphic1.png",
      "id": "mainButton"
    },
    {
      "graphic": "\/path\/to\/graphic2.png",
      "id": "otherButton"
    }
  ],
  "misc": {
    
  },
  "scoreboard": [
    {
      "score": "125",
      "name": "person 1"
    },
    {
      "score": "45",
      "name": "person 2"
    },
    {
      "score": "12",
      "name": "person 3"
    }
  ],
  "levels": [
    {
      "sceneID": "scene1",
      "index": 1,
      "objects": [
        {
          "graphic": "\/path\/to\/graphic1.png",
          "id": "mainButton"
        },
        {
          "graphic": "\/path\/to\/graphic2.png",
          "id": "otherButton"
        }
      ],
      "logic": "println \"things to execute in this level's loop\""
    },
    {
      "sceneID": "scene2",
      "index": 2,
      "objects": [
        
      ],
      "logic": "println \"things to execute in this level's loop\""
    }
  ],
  "splash": {
    "objects": [
      {
        "graphic": "\/path\/to\/graphic1.png",
        "id": "mainButton"
      },
      {
        "graphic": "\/path\/to\/graphic2.png",
        "id": "otherButton"
      }
    ],
    "logic": "println \"groovy script 1\""
  },
  "logic": "println \"Things that are done in the main loop go here\"",
  "logo": "\/path\/to\/logo.png"
}
```
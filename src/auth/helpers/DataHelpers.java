package auth.helpers;

import gamedata.Game;
import gamedata.Resource;
import gamedata.Scene;

public class DataHelpers {
    public static Scene createNewScene(int number) {
        var scene = new Scene();
        scene.sceneID = "scene_"+number;
        return scene;
    }

    public static boolean sceneIDExists(Game game, String sceneID) {
        for (var s : game.scenes) {
            if (s.sceneID.equals(sceneID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean colorExists(Game game, String id) {
        for (var s : game.resources) {
            if (s.resourceID.equals(id) && s.resourceType == Resource.ResourceType.COLOR_RESOURCE) {
                return true;
            }
        }
        return false;
    }

    public static boolean imgExists(Game game, String id) {
        for (var s : game.resources) {
            if (s.resourceID.equals(id) && s.resourceType == Resource.ResourceType.IMAGE_RESOURCE) {
                return true;
            }
        }
        return false;
    }
}

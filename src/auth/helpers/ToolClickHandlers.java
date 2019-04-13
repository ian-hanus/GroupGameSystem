package auth.helpers;

/**
 * Author: Anshu Dwibhashi
 * All methods in this class will be of the name as defined in tools.json in the data folder. Since all methods in this class will be invoked
 * by reflection, it is critical to follow this convention.
 */
public class ToolClickHandlers {
    public static void handleNewScene () {
        System.out.println("In handle new scene");
    }

    public static void handleNewObject () {
        System.out.println("In handle new object");
    }
}

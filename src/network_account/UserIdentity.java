package network_account;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

public class UserIdentity {
    private String myUsername;
    private Map<String, Integer> myHighScores;
//    private Image myAvatar;
    private String myName;

    public UserIdentity(String username, String name, Map<String, Integer> highScores, Image avatar){
        myUsername = username;
        myHighScores = highScores;
        myName = name;
//        myAvatar = avatar;
    }

    public String getUsername(){
        return myUsername;
    }

    public Integer getHighScores(String gameName){
        return myHighScores.get(gameName);
    }

    public String getName(){return myName;}

//    public Image getAvatar(){
//        return myAvatar;
//    }
}

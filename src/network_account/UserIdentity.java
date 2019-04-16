package network_account;

import java.util.Map;

public class UserIdentity {
    private String myUsername;
    private Map<String, Integer> myHighScores;

    public UserIdentity(String username, Map<String, Integer> highScores){
        myUsername = username;
        myHighScores = highScores;
    }

    public String getUsername(){
        return myUsername;
    }

    public Integer getHighScore(String gameName){
        return myHighScores.get(gameName);
    }
}

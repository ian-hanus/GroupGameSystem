package network_account;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class IdentityController {
    private List<Label> myScores;

    @FXML
    public Label usernameText, score1, score2, score3;
    public ImageView avatarImageView;

    public IdentityController(){
        myScores = new ArrayList<Label>();
        myScores.add(score1);
        myScores.add(score2);
        myScores.add(score3);
    }

    public void updateSocial(){

    }

    private void updateIdentity(UserIdentity userIdentity, String gameName){
        usernameText.setText(userIdentity.getUsername());
        int scoreCounter = 0;
        for(Label score:myScores) {
            score.setText(Integer.toString(userIdentity.getHighScores(gameName)));
            scoreCounter++;
        }
    }
}

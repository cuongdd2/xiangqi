package home;

import game.Board;
import game.PlayerModel;
import game.Val;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class GameModeController {

    @FXML private Text welcome;

    private PlayerModel playerModel;

    @FXML
    protected void handlePvP(ActionEvent event) {
        newGame(event, true);
    }


    @FXML
    protected void handlePvC(ActionEvent event) {
        newGame(event, false);
    }

    private void newGame(ActionEvent event, boolean pvp) {
        Group root = new Group();
        Stage stage = new Stage();
        if (!pvp) playerModel.black = false; // if player with computer, you are red side, play first
        try {
            root.getChildren().add(new Board(playerModel));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, Val.W, Val.H, Color.GRAY);
        stage.setTitle("Xiangqi");
        stage.setScene(scene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }


    @FXML
    protected void handleLogout(ActionEvent event) {

    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
}

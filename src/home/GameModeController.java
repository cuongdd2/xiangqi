package home;

import game.Board;
import ai.GameState;
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

import javax.inject.Inject;
import java.io.IOException;

public class GameModeController {

    @FXML private Text welcome;

    @Inject PlayerModel playerModel;
    @Inject
    GameState gameState;

    @FXML
    protected void handlePvP(ActionEvent event) {
        gameState.isOnline = true;
        newGame(event);
    }

    @FXML
    protected void handlePvC(ActionEvent event) {
        gameState = new GameState();
        gameState.isOnline = false;
        newGame(event);
    }

    private void newGame(ActionEvent event) {
        Group root = new Group();
        Stage stage = new Stage();
        try {
            root.getChildren().add(new Board());
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
}

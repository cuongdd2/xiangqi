package home;

import game.Board;
import game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameModeController {

    @FXML private Text welcome;

    @FXML
    protected void handlePvP(ActionEvent event) {

    }


    @FXML
    protected void handlePvC(ActionEvent event) {
        try {
            Group root = new Group();
            Stage stage = new Stage();
            root.getChildren().add(new Board());
            Scene scene = new Scene(root, Game.W, Game.H, Color.GRAY);
            stage.setTitle("Xiangqi");
            stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void handleLogout(ActionEvent event) {

    }

}

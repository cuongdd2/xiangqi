package player;

import game.Val;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UserInfoView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //get palyer Info
        Player player = new Player(Val.GAME_TIME,new PlayerProfile(1,"test1",0.23,500));

        ////////
        primaryStage.setTitle("Select");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(1, 1, 1, 1));

//        Label idLabel = new Label("id:"+player.playProfile.getId());
//        grid.add(idLabel, 1, 0);

        Label nameLabel = new Label("name:"+player.playProfile.getName());
        nameLabel.setTextFill(Color.web("000"));
        grid.add(nameLabel, 0, 3);

        Label winRateLabel = new Label("win:"+player.playProfile.getWinRate() + "%");
        winRateLabel.setTextFill(Color.web("#0076FF"));
        grid.add(winRateLabel, 0, 4);

        Label eloResultLabel = new Label("ELO:"+player.playProfile.getEloResult());
        eloResultLabel.setTextFill(Color.web("#FF0000"));
        grid.add(eloResultLabel, 0, 5);

        Image avatar = new Image("avatar.png");
        ImageView iv2 = new ImageView();
        iv2.setImage(avatar);
        iv2.setFitWidth(50);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);
        grid.add(iv2, 0, 0);

        Scene scene = new Scene(grid, 60, 120);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}

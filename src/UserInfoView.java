import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UserInfoView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Player player = new Player(Util.GAME_TIME,new PlayProfile(1,"test1",0.23,500));

        ////////
        primaryStage.setTitle("Select");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Label idLabel = new Label("id:"+player.playProfile.getId());
        grid.add(idLabel, 1, 0);

        Label nameLabel = new Label("name:"+player.playProfile.getName());
        grid.add(nameLabel, 1, 1);

        Label winRateLabel = new Label("winRate:"+player.playProfile.getWinRate() + "%");
        grid.add(winRateLabel, 0, 2);

        Label eloResultLabel = new Label("ELO:"+player.playProfile.getEloResult());
        grid.add(eloResultLabel, 0, 3);

        Image avatar = new Image("avatar.png");
        ImageView iv2 = new ImageView();
        iv2.setImage(avatar);
        iv2.setFitWidth(50);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);
        grid.add(iv2, 0, 0);



        Scene scene = new Scene(grid, 150, 150);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}

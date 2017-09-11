import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
    public static int H = 960;
    public static int W = 640;
    private Group root = new Group();

    @Override
    public void start(Stage stage) throws Exception {
        root.getChildren().add(new Board());
        Scene scene = new Scene(root, Game.W, Game.H, Color.GRAY);
        stage.setTitle("Xiangqi");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

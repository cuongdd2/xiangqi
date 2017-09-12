import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class SelectFight extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Select");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(1);
        grid.setVgap(3);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Button btn1 = new Button("   PVP   ");
        btn1.setFont(new Font(20));
        btn1.setStyle("-fx-background-color: #484");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn1.getChildren().add(btn1);
        grid.add(hbBtn1, 0, 1);

        Button btn2 = new Button("   PVC   ");
        btn2.setStyle("-fx-background-color: #55F");
        btn2.setFont(new Font(20));
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn2.getChildren().add(btn2);
        grid.add(hbBtn2, 0, 2);

        Button btn3 = new Button("   EXIT   ");
        btn3.setFont(new Font(20));
        btn3.setStyle("-fx-background-color: #F33");
        HBox hbBtn3 = new HBox(10);
        hbBtn3.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn3.getChildren().add(btn3);
        grid.add(hbBtn3, 0, 3);

//        final Text actiontarget = new Text();
//        grid.add(actiontarget, 1, 6);
//        //setting id for CSS styling
//        actiontarget.setId("actiontarget");

        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("Select PVP");
            }
        });

        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("Select PVC");
            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("Exit");
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

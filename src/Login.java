
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("XiangQi");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome Play XiangQi");
        //setting id for CSS styling
        scenetitle.setId("welcome-text");
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

//        Label pw = new Label("Password:");
//        grid.add(pw, 0, 2);
//
//        PasswordField pwBox = new PasswordField();
//        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        //setting id for CSS styling
        actiontarget.setId("actiontarget");

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println(userTextField.getText());
                String userName = userTextField.getText();

                //test
                try {
                    if (this.login(userName).equals(null)){
                        //login success
                        System.out.println("login success");
                    }else {
                        //create new user
                        System.out.println("Create new User, username is: " + this.login(userName));
                    }

                    //jump to the chess board

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                actiontarget.setText("Sign in button pressed");
            }

            private String login(String userName) throws SQLException {

                Connection conn = null;
                String url = "jdbc:sqlite:C:/sqlite/main.db";
                // create a connection to the database
                conn = DriverManager.getConnection(url);

                Statement stmt = conn.createStatement();
                ResultSet rs;

                String sql = "select * from user where name = " + userName;
                rs = stmt.executeQuery(sql);

                if (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Double winRate = rs.getDouble("winRate");
                    int eloResult = rs.getInt("eloResult");
                    PlayProfile playProfile = new PlayProfile(id, name, winRate, eloResult);
                    Player player = new Player(Util.GAME_TIME,playProfile);
                    return null;  //old player
                }else {
                    String insertSql = "insert into user(name) values('default')";
                    int id= stmt.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);

                    String defaultName = "default" + id;
                    String updateSql = "update  user name='" + defaultName + "' where id=" + id;

                    PlayProfile playProfile = new PlayProfile(id, defaultName, 0, 100);
                    Player player = new Player(Util.GAME_TIME,playProfile);
                    return defaultName; //new player
                }
            }
        });


        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

//        scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
        primaryStage.show();
    }


}

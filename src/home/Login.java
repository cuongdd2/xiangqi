package home;

import game.Val;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import player.PlayerProfile;
import player.Player;

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

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("home.Login");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
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
                String password = pwBox.getText();

                if (!userName.equals("") && !password.equals("")){
                    //test
                    try {
                        if (this.login(userName,password) == 1){
                            //home success
                            System.out.println("home success");
                        }else {
                            //create new user
//                        System.out.println("Create new User, username is: " + this.home(userName,password));
                            System.out.println("Username or password wrong ");

                        }

                        //jump to the chess board

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }


                actiontarget.setText("home.Login button pressed");
            }

            private int login(String userName, String password) throws Exception {

                Connection conn = null;
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB

                String url = "jdbc:mysql://138.197.92.4:3306/xiangqi";
                // create a connection to the database
                conn = DriverManager.getConnection(url,"root","");

                Statement stmt = conn.createStatement();
                ResultSet rs;

                String sql = "select * from user where name = '" + userName + "' and password = '" + password+"'";
                rs = stmt.executeQuery(sql);

                if (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Double winRate = rs.getDouble("winRate");
                    int eloResult = rs.getInt("eloResult");
                    PlayerProfile playProfile = new PlayerProfile(id, name, winRate, eloResult);
                    Player player = new Player(Val.GAME_TIME,playProfile);
                    return 1;  //old player
                }else {
                    return 0;
                }
//                else {
//                    String insertSql = "insert into user(name) values('default')";
//                    int id= stmt.executeUpdate(insertSql, Statement.RETURN_GENERATED_KEYS);
//
//                    String defaultName = "default" + id;
//                    String updateSql = "update  user name='" + defaultName + "' where id=" + id;
//
//                    player.PlayerProfile playProfile = new player.PlayerProfile(id, defaultName, 0, 100);
//                    player.Player player = new player.Player(Util.GAME_TIME,playProfile);
//                    return defaultName; //new player
//                }
            }
        });


        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

//        scene.getStylesheets().add(getClass().getResource("home.Login.css").toExternalForm());
        primaryStage.show();
    }


}

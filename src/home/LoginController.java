/*
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package home;

import game.PlayerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import player.PlayerProfile;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class LoginController {
    @FXML private Text actiontarget;
    @FXML private TextField usernameTF;
    @FXML private TextField passwordTF;
    private Node source;
    private Xiangqi game;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        source = (Node)(event.getSource());
        String userName = usernameTF.getText();
        String password = passwordTF.getText();

        if (Objects.equals(userName, "")) actiontarget.setText("Username must not empty!");
        else if(Objects.equals(password, "")) actiontarget.setText("Password must not empty!");
        else if (checkLogin(userName,password)) openGameModeScreen();
        else actiontarget.setText("Username or password wrong ");
    }

    private boolean checkLogin(String userName, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://138.197.92.4:3306/xiangqi";
        String sql = "select * from user where name = '" + userName + "' and password = '" + password + "'";

        try (
                Connection conn = DriverManager.getConnection(url, "root", "");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double winRate = rs.getDouble("winRate");
                int eloResult = rs.getInt("eloResult");
                PlayerProfile playProfile = new PlayerProfile(id, name, winRate, eloResult);
                game.setPlayerModel(new PlayerModel(playProfile));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void openGameModeScreen() {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("game_mode.fxml"));
            root = loader.load();
            source.getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Game mode");
            stage.setScene(new Scene(root, 300, 275));
            stage.show();
            GameModeController controller = loader.getController();
            controller.setPlayerModel(game.getPlayerModel());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Xiangqi getGame() {
        return game;
    }

    public void setGame(Xiangqi game) {
        this.game = game;
    }
}

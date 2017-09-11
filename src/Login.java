import java.sql.*;

public class Login {

    public void login(String userName) throws SQLException {


        String url = "jdbc:sql";
        Connection conn = DriverManager.getConnection(url,"","");
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
        }else {
            String createUser = "insert into user(name) values('default')";
            int id= stmt.executeUpdate(createUser, Statement.RETURN_GENERATED_KEYS);
            PlayProfile playProfile = new PlayProfile(id, "me,", 0, 100);
            Player player = new Player(Util.GAME_TIME,playProfile);
        }
    }
}

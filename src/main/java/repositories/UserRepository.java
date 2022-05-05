package repositories;

import models.User;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public UserRepository() {

    }
    public void save(User object){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DbConnector.getConnection();

            String sql = "INSERT INTO user (name) VALUES(?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, object.getName());


            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("A row has been inserted.");
            }
        } catch (SQLException e) {
            System.out.println("oh no, error!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public User findByName(String name){
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DbConnector.getConnection();

            String sql = "SELECT * FROM user WHERE name = ? limit 1";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("oh no, error!");
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;

    }

}

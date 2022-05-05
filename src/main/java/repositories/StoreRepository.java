package repositories;

import models.Store;
import models.User;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreRepository {
    public StoreRepository() {
    }

    public void SaveStore(Store model){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DbConnector.getConnection();

            String sql = "INSERT INTO store (storeName, createdBy) VALUES(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, model.getStoreName());
            statement.setInt(2, model.getCreatedBy());


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
    public Store findByName(String name){
       Store store = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DbConnector.getConnection();

            String sql = "SELECT * FROM store WHERE storeName = ? limit 1";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                store = new Store();
                store.setStoreName(rs.getString("storeName"));
                store.setStoreID(rs.getInt("storeID"));
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
        return store;

    }
}


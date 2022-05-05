package repositories;

import models.Product;
import models.Store;
import utils.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepository {
    public ProductRepository() {
    }

    public void SaveProduct(Product model) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DbConnector.getConnection();

            String sql = "INSERT INTO product (name, createdBy) VALUES(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, model.getName());
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
    public Product findByName(String name){
        Product product = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DbConnector.getConnection();

            String sql = "SELECT * FROM product WHERE name = ? limit 1";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setName(rs.getString("name"));
                product.setId(rs.getInt("id"));
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
        return product;

    }
}
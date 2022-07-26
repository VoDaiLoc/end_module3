package com.codegym.dao;

import com.codegym.connection.MySQLConnUtils;
import com.codegym.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductDAO implements IProductDAO{

    private MySQLConnUtils connectionMySQL = new MySQLConnUtils();

    private int noOfRecords;

    public int getNoOfRecords(){
        return noOfRecords;
    }

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (name, price, quantity, color, des) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_PRODUCT_SQL = "update product set name = ?,price= ?, quantity =?, color= ?, des=? where id = ?;";
    private static final String SELECT_ALL_PRODUCT = "select * from product";
    private static final String SEARCH_BY_NAME_TYPE = "SELECT * FROM user  WHERE  name LIKE ? OR price LIKE ? OR quantity LIKE ? ; ";
    private static final String DELETE_PRODUCT_SQL = "delete from product where id = ?;";
    @Override
    public Boolean insertProduct(Product product) throws SQLException {
        boolean rowInsert = false;
        System.out.println(INSERT_PRODUCT_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = connectionMySQL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPrice());
            preparedStatement.setString(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDes());
            rowInsert =  preparedStatement.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowInsert;
    }

    @Override
    public Product selectProduct(int id) {
        return null;
    }

    public List<Product> getNumberPage(int offset, int noOfRecords, String name) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMySQL.getConnection();
        System.out.println("numberpage");

        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM product where name LIKE ? OR price LIKE ? OR quantity LIKE ? limit " + offset + "," + noOfRecords;
        List<Product> list = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, '%' + name + '%');
        ps.setString(2, '%' + name + '%');
        ps.setString(3, '%' + name + '%');
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getString("price"));
            product.setQuantity(rs.getString("quantity"));
            product.setColor(rs.getString("color"));
            product.setDes(rs.getString("des"));
            list.add(product);
        }
        rs = ps.executeQuery("SELECT FOUND_ROWS()");
        if (rs.next()){
            this.noOfRecords = rs.getInt(1);
        }
        connection.close();
        return list;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        // Step 1: Establishing a Connection
        try {
            Connection connection = connectionMySQL.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String price = rs.getString("price");
                String quantity = rs.getString("quantity");
                String color = rs.getString("color");
                String des = rs.getString("des");
                products.add(new Product(id, name, price, quantity, color,des));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = connectionMySQL.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<Product> searchProduct(String query) {
        List<Product> listProduct = selectAllProduct();

        List<Product> searchList = new ArrayList<>();

        for (Product product : listProduct) {
            if(product.getName().toLowerCase().contains(query.toLowerCase())) {
                searchList.add(product);
            }
        }

        return searchList;
    }



    @Override
    public Boolean updateProduct(Product product) throws ClassNotFoundException, SQLException {
        boolean rowUpdated;
        try (Connection connection = connectionMySQL.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getPrice());
            statement.setString(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getDes());
            statement.setInt(6, product.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }



    @Override
    public boolean existByProductId(int id) {
        return false;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}

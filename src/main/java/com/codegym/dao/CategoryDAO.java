package com.codegym.dao;

import com.codegym.connection.MySQLConnUtils;
import com.codegym.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO{
    private MySQLConnUtils connectionMySQL = new MySQLConnUtils();

    private static final String SELECT_ALL_CATEGORY= "SELECT * FROM category;";

    private static final String EXIST_CATEGORY_ID = "SELECT COUNT(*) AS count FROM category AS c WHERE c.id = ?;";
    @Override
    public List<Category> selectAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        try{
            Connection connection = connectionMySQL.getConnection();
            PreparedStatement statement = connection.prepareCall(SELECT_ALL_CATEGORY);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String acction = rs.getString("action");
                categoryList.add(new Category(id,acction));
            }
        }catch (SQLException e){
            connectionMySQL.printSQLException(e);
        }
        return categoryList;
    }

    @Override
    public boolean existByProductId(int id) {
        boolean exist = false;
        try {
            Connection connection = connectionMySQL.getConnection();
            PreparedStatement statement = connection.prepareCall(EXIST_CATEGORY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }
}

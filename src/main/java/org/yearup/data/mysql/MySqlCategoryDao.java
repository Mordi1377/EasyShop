package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories() {
        // get all categories
        String sql = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                categories.add(mapRow(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;

    }

    @Override
    public Category getById(int categoryId) {
        // get category by id
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        Category category = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                category = mapRow(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching category with ID: " + categoryId, e);
        }

        return category;
    }

    @Override
    public Category create(Category category) {
        // create a new category
        // get all categories
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setCategoryId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating new category", e);
        }

        return category;

    }

    @Override
    public void update(int categoryId, Category category) {
        // update category
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3, categoryId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating category with ID: " + categoryId, e);
        }
    }

    @Override
    public void delete(int categoryId) {
        // delete category
        String sql = "DELETE FROM categories WHERE category_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting category with ID: " + categoryId, e);
        }
    }

        private Category mapRow (ResultSet row) throws SQLException {
            int categoryId = row.getInt("category_id");
            String name = row.getString("name");
            String description = row.getString("description");

            Category category = new Category() {{
                setCategoryId(categoryId);
                setName(name);
                setDescription(description);
            }};

            return category;
        }
    }

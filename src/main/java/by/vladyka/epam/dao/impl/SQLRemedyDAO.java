package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.RemedyDAO;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.SQLConnectionHelper;
import by.vladyka.epam.entity.Remedy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.vladyka.epam.dao.util.SQLQuery.QUERY_FIND_REMEDY;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:58
 **/
public class SQLRemedyDAO extends SQLConnectionHelper implements RemedyDAO {

    public List<Remedy> find(String name, int start, int offset) throws DAOException {
        ArrayList<Remedy> remedies = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, props);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_REMEDY)) {
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Remedy remedy = createRemedy(resultSet);
                remedies.add(remedy);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
        return remedies;
    }

    private Remedy createRemedy(ResultSet resultSet) throws SQLException {
        Remedy remedy = new Remedy();
        remedy.setIdRemedy(resultSet.getInt("idRemedy"));
        remedy.setName(resultSet.getString("name"));
        remedy.setPacking(resultSet.getString("packing"));
        remedy.setMaker(resultSet.getString("maker"));
        remedy.setRemainder(resultSet.getInt("remainder"));
        remedy.setPrice(resultSet.getDouble("price"));
        return remedy;
    }

    public boolean update(Map<String, String> parameters) {
        return true;
    }

    public boolean add(Map<String, String> parameters) {
        return true;
    }

    public boolean delete(Map<String, String> parameters) {
        return true;
    }
}

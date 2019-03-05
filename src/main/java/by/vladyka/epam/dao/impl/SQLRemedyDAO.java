package by.vladyka.epam.dao.impl;

import by.vladyka.epam.dao.RemedyDAO;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.dao.util.SQLConnectionHelper;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.RemedySearchingResult;

import java.sql.*;
import java.util.Map;

import static by.vladyka.epam.dao.util.SQLQuery.QUERY_COUNT_SIMULAR_REMEDIES;
import static by.vladyka.epam.dao.util.SQLQuery.QUERY_FIND_REMEDY;

/**
 * Created by Vladyka Stas
 * on 26.02.2019 at 1:58
 **/
public class SQLRemedyDAO extends SQLConnectionHelper implements RemedyDAO {

    public RemedySearchingResult findRemedy(String name, int start, int offset) throws DAOException {
        RemedySearchingResult result = new RemedySearchingResult();
        try (Connection connection = DriverManager.getConnection(URL, props);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_REMEDY)) {
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, offset);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Remedy remedy = createRemedy(resultSet);
                result.getRemedies().add(remedy);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
        result.setFoundRemediesNumber(getFoundRemediesNumber(name));

        return result;
    }

    private Remedy createRemedy(ResultSet resultSet) throws SQLException {
        Remedy remedy = new Remedy();
        remedy.setIdRemedy(resultSet.getInt("idRemedy"));
        remedy.setName(resultSet.getString("name"));
        remedy.setPacking(resultSet.getString("packing"));
        remedy.setMaker(resultSet.getString("maker"));
        remedy.setQuantity(resultSet.getInt("quantity"));
        remedy.setPrice(resultSet.getDouble("price"));
        remedy.setReceipt(resultSet.getString("receipt").charAt(0));
        return remedy;
    }

    private static int getFoundRemediesNumber (String remedyName) throws DAOException {
        int foundRemediesNumber =0;
        try(Connection connection = DriverManager.getConnection(URL, props);
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_COUNT_SIMULAR_REMEDIES)){
            preparedStatement.setString(1, "%"+remedyName+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                foundRemediesNumber = resultSet.getInt(1);
            }
        }catch (SQLException ex){
            throw new DAOException();
        }
        return foundRemediesNumber;
    }

    public boolean updateRemedy(Map<String, String> parameters) {
        return true;
    }

    public boolean addRemedy(Map<String, String> parameters) {
        return true;
    }

    public boolean deleteRemedy(Map<String, String> parameters) {
        return true;
    }
}

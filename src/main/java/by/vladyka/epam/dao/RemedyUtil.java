package by.vladyka.epam.dao;

import by.vladyka.epam.entity.Remedy;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.vladyka.epam.dao.util.DBColumn.*;

/**
 * Created by Vladyka Stas
 * on 12.03.2019 at 12:12
 **/
public interface RemedyUtil {
    default Remedy createRemedy(ResultSet resultSet) throws SQLException {
        Remedy remedy = new Remedy();
        remedy.setId(resultSet.getInt(ID));
        remedy.setReceiptRequired(resultSet.getBoolean(RECEIPT_REQUIRED));
        remedy.setDescription(resultSet.getString(DESCRIPTION));
        remedy.setPrice(resultSet.getDouble(PRICE));
        remedy.setName(resultSet.getString(NAME));
        return remedy;
    }
}

package by.vladyka.epam.dao.util;

import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.vladyka.epam.dao.util.DBColumn.*;

/**
 * Created by Vladyka Stas
 * on 12.03.2019 at 12:12
 **/
public final class SQLDaoAssistant {
    public static Remedy createRemedy(ResultSet rs) throws SQLException {
        Remedy remedy = new Remedy();
        remedy.setId(rs.getInt(ID));
        remedy.setReceiptRequired(rs.getBoolean(RECEIPT_REQUIRED));
        remedy.setDescription(rs.getString(DESCRIPTION));
        remedy.setPrice(rs.getDouble(PRICE));
        remedy.setName(rs.getString(NAME));
        return remedy;
    }

//    public static Receipt createReceipt(ResultSet rs) throws SQLException{
//
//    }
//
//    public static RemedyOrder createRemedyOrder (ResultSet rs) throws SQLException{
//
//    }
//
//    public static ClientOrder createClientOrder (ResultSet rs) throws SQLException{
//
//    }

//    private User createUser(ResultSet resultSet) throws SQLException {
//        User user = new User();
//        user.setId(resultSet.getInt(ID));
//        user.setEmail(resultSet.getString(EMAIL));
//        user.setFirstName(resultSet.getString(FIRST_NAME));
//        user.setLastName(resultSet.getString(LAST_NAME));
//        user.setRole(User.UserRole.valueOf(resultSet.getString(ROLE)));
//        user.setPhone(resultSet.getString(PHONE));
//        return user;
//    }




    public static int getFoundEntitiesNumber(String query, ConnectionPool pool) throws DAOException {
        int foundEntitiesNumber = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                foundEntitiesNumber = rs.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return foundEntitiesNumber;
    }

    public static void setStartPositionAndOffset(PreparedStatement ps, int start, int offset) throws SQLException {
        ps.setInt(1, start);
        ps.setInt(2, offset);
    }


}

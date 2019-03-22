package by.vladyka.epam.dao.util;

import by.vladyka.epam.dao.exception.ConnectionPoolException;
import by.vladyka.epam.dao.exception.DAOException;
import by.vladyka.epam.entity.ClientOrder;
import by.vladyka.epam.entity.Receipt;
import by.vladyka.epam.entity.Remedy;
import by.vladyka.epam.entity.RemedyOrder;

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

    public static int getFoundEntitiesNumber(String query, ConnectionPool pool) throws DAOException {
        int foundReceiptsNumber = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                foundReceiptsNumber = rs.getInt(1);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
        return foundReceiptsNumber;
    }

    public static void setStartPositionAndOffset(PreparedStatement ps, int start, int offset) throws SQLException {
        ps.setInt(1, start);
        ps.setInt(2, offset);
    }


}

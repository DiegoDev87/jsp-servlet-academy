package dev.webacademy.bancodados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilsDB {

    public static Date converteData(java.util.Date date) {
        if (date != null) {
            return new Date(date.getTime());
        }
        return null;
    }

    public static void fecharConexao(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null;
            }
        } catch (SQLException ex) {}
    }

    public static void fechar(PreparedStatement ps) {
        try {
            if (ps != null && !ps.isClosed()) {
                ps.close();
                ps = null;
            }
        } catch (SQLException ex) {
        }
    }

    public static void fechar(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
                rs = null;
            }
        } catch (SQLException ex) {
        }
    }

    public static void fechar(PreparedStatement ps, ResultSet rs) {
        fechar(ps);
        fechar(rs);
    }
}

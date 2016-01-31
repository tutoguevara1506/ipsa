package paquetes;

import java.sql.*;

public class Accesos extends Conexion {

    private ResultSet resultado;
    public static String idtra;

    public Accesos() {
    }

    public ResultSet querySQLvariable(String mQuery) {
        try {
            getStmt();
            resultado = stmt.executeQuery(mQuery);
            return resultado;
        } catch (Exception e) {
            System.err.println("Sql Consulta ResultSet Variable FI Exception:" + e.getMessage() + " Query: " + mQuery);
            return null;
        } finally {

        }
    }

    public String strQuerySQLvariable(String mQuery) {
        try {
            getStmt();
            String miSTR = "";
            resultado = stmt.executeQuery(mQuery);
            while (resultado.next()) {
                miSTR = resultado.getString(1);
            }
            return miSTR;
        } catch (Exception e) {
            System.err.println("Sql Consulta String Variable FI Exception:" + e.getMessage() + " Query: " + mQuery);
            return null;
        } finally {

        }
    }

    public Double doubleQuerySQLvariable(String mQuery) {
        try {
            getStmt();
            Double miDBL = 0.0;
            resultado = stmt.executeQuery(mQuery);
            while (resultado.next()) {
                miDBL = resultado.getDouble(1);
            }
            return miDBL;
        } catch (Exception e) {
            System.err.println("Sql Consulta Double Variable FI Exception:" + e.getMessage() + " Query: " + mQuery);
            return null;
        } finally {

        }
    }
    
    public Date dateQuerySQLvariable(String mQuery) {
        try {
            getStmt();
            Date miDate = null;
            resultado = stmt.executeQuery(mQuery);
            while (resultado.next()) {
                miDate = resultado.getDate(1);
            }
            return miDate;
        } catch (Exception e) {
            System.err.println("Sql Consulta Date Variable FI Exception:" + e.getMessage() + " Query: " + mQuery);
            return null;
        } finally {

        }
    }

    public void dmlSQLvariable(String mQuery) {
        try {
            getStmt();
            stmt.executeUpdate(mQuery);
        } catch (Exception e) {
            System.err.println("Sql Operación Variable FI Exception:" + e.getMessage() + " Query: " + mQuery);

        } finally {

        }
    }

}

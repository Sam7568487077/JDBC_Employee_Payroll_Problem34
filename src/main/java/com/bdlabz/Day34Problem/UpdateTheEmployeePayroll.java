package com.bdlabz.Day34Problem;

import java.sql.*;

public class UpdateTheEmployeePayroll {

    public static void main(String[] args) {
        PreparedStatement stmt = null;
        String query="SELECT * FROM Employee;";
        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver class loaded and register");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employeepayrollservice3","root","Sam@1998");
            System.out.println("connection Establish with DB...");

            executeUpdateQuery(conn,"Update Employee set salary=7000000 where name='Sowmya'");
            System.out.println("Update the employeepayroll");

            stmt = conn.prepareStatement(query);
            System.out.println("platform created");

            ResultSet rs = stmt.executeQuery(query);

            displayResultSet(rs);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(stmt!= null){
                try{
                    stmt.close();
                    System.out.println("statement costly resources closed");
                }catch(SQLException se){
                    System.out.println(se.getMessage());
                }
            } if(con!= null){
                try{
                    con.close();
                    System.out.println("connection costly resources closed");
                }catch(Exception se){
                    System.out.println(se.getMessage());
                }
            }
        }
    }

    public static void displayResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " + columnValue);
            }
            System.out.println("");
        }
    }

    public static void executeUpdateQuery(Connection conn, String query) throws SQLException {
        Statement stmt = conn.createStatement();
        int result = stmt.executeUpdate(query);
        System.out.println(result + " rows affected by the update query");
    }

}

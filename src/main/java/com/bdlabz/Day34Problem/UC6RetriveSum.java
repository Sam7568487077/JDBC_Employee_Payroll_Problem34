package com.bdlabz.Day34Problem;

import java.sql.*;

public class UC6RetriveSum {
    public static void main(String[] args) {
        PreparedStatement stmt = null;
        String query="SELECT SUM(basic_pay) FROM employee_payroll  GROUP BY gender ";
        String query2="SELECT COUNT(basic_pay) FROM employee_payroll  GROUP BY gender ";
        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver class loaded and register");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employeepayrollservice3","root","Sam@1998");
            System.out.println("connection Establish with DB...");

            stmt = conn.prepareStatement(query);
            Statement stmt2 = conn.prepareStatement(query2);
            System.out.println("platform created");

            ResultSet rs = stmt.executeQuery(query);
            ResultSet rs2 = stmt.executeQuery(query2);

            displayResultSet(rs);
            displayResultSet(rs2);


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

    public static void executeUpdateQuery(Connection con , String query) throws SQLException {
        Statement stmt = con.prepareStatement(query);
        int result = stmt.executeUpdate(query);
        System.out.println(result + " rows affected by the update query");
    }

}

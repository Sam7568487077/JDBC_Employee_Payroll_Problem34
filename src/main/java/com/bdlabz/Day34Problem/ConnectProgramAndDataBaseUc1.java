package com.bdlabz.Day34Problem;

import java.sql.*;

public class ConnectProgramAndDataBaseUc1 {

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement stmt = null;
        String query="select sum(basic_pay) from employee_payroll;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver class loaded and register");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employeepayrollservice3","root","Sam@1998");
            System.out.println("connection Establish with DB...");
            stmt = con.prepareStatement(query);
            System.out.println("platform created");
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(rs);
        }catch (ClassNotFoundException | SQLException ce){
            ce.printStackTrace();

        }finally {
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author viniciusspatto
 */
public class DBConn {
    
    public Connection getConnection() throws Exception{
        try{
            Class.forName("org.postgresql.Driver"); // carrega o drive e registra no JDBC
            String url = "jdbc:postgresql://localhost/Funcionario"; //caminho para o BD
            String usr = "postgres";
            String pswrd = "aula321";
            return DriverManager.getConnection(url, usr, pswrd);
        } catch (Exception e){
            throw e;
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import dao.DBConn;
import dao.FuncionarioDAO;
import dao.impl.FuncionarioDAOImpl;
import dto.Funcionario;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author viniciusspatto
 */
public class AccessManager {

    public ArrayList<Funcionario> getFuncionarios() throws Exception{
        DBConn dbConn = new DBConn();
        Connection conn = dbConn.getConnection();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();
        return funcionarioDAO.getFuncionarios(conn);
    }
    
    public Funcionario getFuncionario(String cpf) throws Exception{
        DBConn dbConn = new DBConn();
        Connection conn = dbConn.getConnection();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();
        return funcionarioDAO.getFuncionario(conn, cpf) ;
    }
    
    public boolean addFuncionario(Funcionario func) throws Exception{
        DBConn dbConn = new DBConn();
        Connection conn = dbConn.getConnection();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();
        return funcionarioDAO.addFuncionario(conn, func);
    }
    
    public boolean setFuncionario(Funcionario func) throws Exception{
        DBConn dbConn = new DBConn();
        Connection conn = dbConn.getConnection();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();
        return funcionarioDAO.setFuncionario(conn, func);
    }
    
    public boolean delFuncionario(String cpf) throws Exception{
        DBConn dbConn = new DBConn();
        Connection conn = dbConn.getConnection();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();
        return funcionarioDAO.delFuncionario(conn, cpf);
    }
    
    
        
}

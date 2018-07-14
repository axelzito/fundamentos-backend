/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Funcionario;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author viniciusspatto
 */
public interface FuncionarioDAO {

    /**
     * Metodo chamado para executar a query para recuperar todos os funcionarios
     * no banco de dados (tabela funcionarios - bd supermercado)
     * @Param Connection conn
     * @return ArrayList<Funcionario> funcionarios
     * throws SQLException
    */
    public ArrayList<Funcionario> getFuncionarios(Connection conn) throws SQLException;

    /**
     * Metodo chamado para executar a query para recuperar um funcionario
     * no banco de dados (tabela funcionarios - bd supermercado)
     * @Param Connection conn
     * @Param String cpf
     * @return Funcionario funcionario
 throws SQLException
    */
    public Funcionario getFuncionario(Connection conn, String cpf) throws SQLException;

    /**
     * Metodo chamado para executar a query para inserir um funcionario
     * no banco de dados (tabela funcionarios - bd supermercado)
     * @Param Connection conn
     * @Param Funcionario funcionario
     * @return boolean
     * throws SQLException
    */
    public boolean addFuncionario(Connection conn, Funcionario funcionario) throws SQLException;
    
    /**
     * Metodo chamado para executar a query para atalizar um funcionario
     * no banco de dados (tabela funcionarios - bd supermercado)
     * @Param Connection conn
     * @Param Funcionario funcionario
     * @return boolean
     * throws SQLException
    */
    public boolean setFuncionario(Connection conn, Funcionario funcionario) throws SQLException;

    /**
     * Metodo chamado para executar a query para excluir um funcionario
     * no banco de dados (tabela funcionarios - bd supermercado)
     * @Param Connection conn
     * @Param Funcionario funcionario
     * @return boolean
     * throws SQLException
    */
    public boolean delFuncionario(Connection conn, String cpf) throws SQLException;

}

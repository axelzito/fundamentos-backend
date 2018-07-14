/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dto.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.FuncionarioDAO;

/**
 *
 * @author viniciusspatto
 */
public class FuncionarioDAOImpl implements FuncionarioDAO {

    /**
     * Metodo chamado para executar a query para recuperar todos os funcionarios no
     * banco de dados (tabela funcionarios - bd supermercado)
     *
     * @Param Connection conn
     * @return ArrayList<Funcionario> funcionarios throws SQLException
     */
    public ArrayList<Funcionario> getFuncionarios(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
        try {
            String  sql = "SELECT * FROM funcionario";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setNascimento(rs.getDate("nascimento"));
                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return funcionarios;
    }

    /**
     * Metodo chamado para executar a query para recuperar um funcionario no banco
     * de dados (tabela funcionarios - bd supermercado)
     *
     * @Param Connection conn
     * @Param String cpf
     * @return Funcionario funcionario throws SQLException
     */
    public Funcionario getFuncionario(Connection conn, String cpf) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Funcionario funcionario = new Funcionario();
        if (cpf.equals("")) {
            throw new SQLException("Nenhum cpf foi informado");
        }
        try {
            String  sql = "SELECT * FROM funcionario WHERE cpf=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            while(rs.next()){
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setNascimento(rs.getDate("nascimento"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return funcionario;
    }

    /**
     * Metodo chamado para executar a query para inserir um funcionario no banco de
     * dados (tabela funcionarios - bd supermercado)
     *
     * @Param Connection conn
     * @Param Funcionario funcionario
     * @return boolean throws SQLException
     */
    public boolean addFuncionario(Connection conn, Funcionario funcionario) throws SQLException {
        PreparedStatement ps = null;
        if (funcionario.getCpf().trim().equals("")) {
            throw new SQLException("Nenhum funcionario/cpf foi informado");
        }
        try {
            String  sql = "INSERT INTO funcionario (nome, cpf, email, telefone, nascimento) " +
                    "VALUES(?, ?, ?, ?, ?) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getEmail());
            ps.setString(4, funcionario.getTelefone());
            ps.setDate(5, funcionario.getNascimento());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return true;
    }

    /**
     * Metodo chamado para executar a query para atalizar um funcionario no banco de
     * dados (tabela funcionarios - bd supermercado)
     *
     * @Param Connection conn
     * @Param Funcionario funcionario
     * @return boolean throws SQLException
     */
    public boolean setFuncionario(Connection conn, Funcionario funcionario) throws SQLException {
        PreparedStatement ps = null;
        if (funcionario.getCpf().trim().equals("")) {
            throw new SQLException("Nenhum funcionario/cpf foi informado");
        }
        try {
            String  sql = "UPDATE funcionario SET nome=?, email=?, "+
                    "telefone=?, nascimento=? "+
                    "WHERE cpf=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getEmail());
            ps.setString(3, funcionario.getTelefone());
            ps.setDate(4, funcionario.getNascimento());
            ps.setString(5, funcionario.getCpf());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return true;
    }

    /**
     * Metodo chamado para executar a query para excluir um funcionario no banco de
     * dados (tabela funcionarios - bd supermercado)
     *
     * @Param Connection conn
     * @Param Funcionario funcionario
     * @return boolean throws SQLException
     */
    public boolean delFuncionario(Connection conn, String cpf) throws SQLException {
        PreparedStatement ps = null;
        if (cpf.equals("")) {
            throw new SQLException("Nenhum cpf foi informado");
        }
        try {
            String  sql = "DELETE FROM funcionario WHERE cpf=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.executeUpdate();
            //ps.executeQuery();
           
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
            }
        }
        return true;
    }
}

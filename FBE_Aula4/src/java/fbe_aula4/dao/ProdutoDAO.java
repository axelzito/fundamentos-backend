/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbe_aula4.dao;

import fbe_aula4.model.Produto;
import java.util.List;

/**
 *
 * @author viniciusspatto
 */
public interface ProdutoDAO {

    /**
     * Metodo chamado para executar a query para recuperar todos os produtos
     * no banco de dados (tabela produtos - bd supermercado)
     * @return List<Produto> produtos
    */
    public List<Produto> getProdutos();

    /**
     * Metodo chamado para executar a query para recuperar um produto
     * no banco de dados (tabela produtos - bd supermercado)
     * @Param String codigo
     * @return Produto produto
    */
    public Produto getProduto(String codigo);

    /**
     * Metodo chamado para executar a query para inserir um produto
     * no banco de dados (tabela produtos - bd supermercado)
     * @Param Produto produto
     * @return boolean
    */
    public boolean addProduto(Produto produto);
    
    /**
     * Metodo chamado para executar a query para atalizar um produto
     * no banco de dados (tabela produtos - bd supermercado)
     * @Param Produto produto
     * @return boolean
    */
    public boolean setProduto(Produto produto);

    /**
     * Metodo chamado para executar a query para excluir um produto
     * no banco de dados (tabela produtos - bd supermercado)
     * @Param String codigo
     * @return boolean
    */
    public boolean delProduto(String codigo);

}
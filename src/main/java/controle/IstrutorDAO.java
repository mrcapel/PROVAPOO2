/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import conexao.ConectaFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Instrutor;
import visao.frmLogin;
import visao.frmMenu;

public class IstrutorDAO {
    private Connection con;
    
    public IstrutorDAO(){
        this.con = new ConectaFactory().getConection();
    }
    
    public void cadastrarInstrutor(Instrutor obj){
        try{
            String sql = "insert into instrutor(idinstrutor, RG, nome, nascimento, titulacao)"
                    + "values (?, ?, ?, ?, ?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getIdInstrutor());
                stmt.setInt(2, obj.getRg());
                stmt.setString(3, obj.getNome());
                stmt.setString(4, obj.getNascimento());
                stmt.setInt(5, obj.getTitulacao());
               
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro" +erro);
        }
        
    }
    
    public void excluirInstrutor(Instrutor obj){
        try{
            String sql = "delete from instrutor where idinstrutor=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getIdInstrutor());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar ação " +erro);
        }
    }
    
    public List<Instrutor> listarInstrutor(){
        try {
            List<Instrutor> lista = new ArrayList<>();
            
            String sql = "select * from instrutor";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Instrutor obj = new Instrutor();
                
                obj.setIdInstrutor(rs.getInt("idinstrutor"));
                obj.setRg(rs.getInt("RG"));
                obj.setNome(rs.getString("nome"));
                obj.setNascimento(rs.getString("nascimento"));
                obj.setTitulacao(rs.getInt("titulacao"));
                
                lista.add(obj);
            }
            
            return lista;
        }
        
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados!" +erro);
            return null;
        }
    }
    
    public void alterarInstrutor(Instrutor obj){
        
        try{
            String sql = "update instrutor set rg=?, nome=?, nascimento=?, titulacao=? where idinstrutor=?";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(5, obj.getIdInstrutor());
                stmt.setInt(1, obj.getRg());
                stmt.setString(2, obj.getNome());
                stmt.setString(3, obj.getNascimento());
                stmt.setInt(4, obj.getTitulacao());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Editar  " +erro);
        }
    }
    
    public List<Instrutor> listarInstrutorPorNome(String nome){
        try{
            List<Instrutor> lista= new ArrayList<>();
            
            String sql = "select * from instrutor where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Instrutor obj = new Instrutor();
                obj.setIdInstrutor(rs.getInt("idinstrutor"));
                obj.setRg(rs.getInt("RG"));
                obj.setNome(rs.getString("nome"));
                obj.setNascimento(rs.getString("nascimento"));
                obj.setTitulacao(rs.getInt("titulacao"));
               
                lista.add(obj);
            }
            return lista;
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar!  " +erro);
            return null;
        }
    }
    
    public Instrutor consultarInstrutorPorNome(String nome){
        try{
            
            String sql = "select * from instrutor where nome=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            Instrutor obj = new Instrutor();
            
            if(rs.next()){
             obj.setIdInstrutor(rs.getInt("idinstrutor"));
                obj.setRg(rs.getInt("RG"));
                obj.setNome(rs.getString("nome"));
                obj.setNascimento(rs.getString("nascimento"));
                obj.setTitulacao(rs.getInt("titulacao"));
            }
            
            return obj;
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Atividade Não Encontrado!  " +erro);
            return null;
        }
    }
    
    public List<Instrutor> buscaInstrutorPorNome(String nome){
        try{
            List<Instrutor> lista= new ArrayList<>();
            
            String sql = "select * from instrutor where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Instrutor obj = new Instrutor();
                obj.setRg(rs.getInt("RG"));
                obj.setNome(rs.getString("nome"));
                obj.setNascimento(rs.getString("nascimento"));
                obj.setTitulacao(rs.getInt("titulacao"));
                
                lista.add(obj);
            }
            return lista;
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar!  " +erro);
            return null;
        }
    }
    
    public void efetuarLogin(String nome){
        try{
            
            String sql = "select * from instrutor where nome=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Seja Bem Vindo Ao Sistema!");
                frmMenu tela = new frmMenu();
                tela.usuarioLogado = rs.getString("nome");
                tela.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(null, "Dados Incorretos");
                new frmLogin().setVisible(true);
            }
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro  " +erro);
        }
    }
    
}

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
import modelo.Atividade;

public class AtividadesDAO {
    
    private Connection con;
    
    public AtividadesDAO(){
        this.con = new ConectaFactory().getConection();
    }
    
    public void cadastrarAtividade(Atividade obj){
        try{
            String sql = "insert into atividade(nome, idatividade)"
                    + "values (?, ?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                stmt.setInt(2, obj.getIdatividade());
               
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro" +erro);
        }
        
    }
    
    public void excluirAtividade(Atividade obj){
        try{
            String sql = "delete from atividade where idatividade=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getIdatividade());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar ação " +erro);
        }
    }
    
    public List<Atividade> listarAtividade(){
        try {
            List<Atividade> lista = new ArrayList<>();
            
            String sql = "select * from atividade";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Atividade obj = new Atividade();
                
                obj.setIdatividade(rs.getInt("idatividade"));
                obj.setNome(rs.getString("nome"));
                
                lista.add(obj);
            }
            
            return lista;
        }
        
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados!" +erro);
            return null;
        }
    }
    
    public void alterarAtividade(Atividade obj){
        
        try{
            String sql = "update atividade set nome=? where idatividade=?";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, obj.getNome());
                
                stmt.setInt(2, obj.getIdatividade());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Editar  " +erro);
        }
    }
    
    public List<Atividade> listarAtividadePorNome(String nome){
        try{
            List<Atividade> lista= new ArrayList<>();
            
            String sql = "select * from atividade where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Atividade obj = new Atividade();
                obj.setIdatividade(rs.getInt("idatividade"));
                obj.setNome(rs.getString("nome"));
               
                lista.add(obj);
            }
            return lista;
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar!  " +erro);
            return null;
        }
    }
    
    public Atividade consultarAtividadePorNome(String nome){
        try{
            
            String sql = "select * from atividade where nome=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            Atividade obj = new Atividade();
            
            if(rs.next()){
             obj.setIdatividade(rs.getInt("idatividade"));
             obj.setNome(rs.getString("nome"));
            }
            
            return obj;
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Atividade Não Encontrado!  " +erro);
            return null;
        }
    }
    
    public List<Atividade> buscaAtividadePorNome(String nome){
        try{
            List<Atividade> lista= new ArrayList<>();
            
            String sql = "select * from atividade where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Atividade obj = new Atividade();
                obj.setIdatividade(rs.getInt("idatividade"));
                obj.setNome(rs.getString("nome"));
                
                lista.add(obj);
            }
            return lista;
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar!  " +erro);
            return null;
        }
    }
    
}

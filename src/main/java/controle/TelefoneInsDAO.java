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
import modelo.TelefoneInstrutor;

public class TelefoneInsDAO {
    
    
    private Connection con;
    
    public TelefoneInsDAO(){
        this.con = new ConectaFactory().getConection();
    }
    
    public void cadastrarTelefone(TelefoneInstrutor obj){
        try{
            String sql = "insert into telefone_instrutor(idtelefone, numero, tipo, instrutor_idinstrutor1)"
                    + "values (?, ?, ?, ?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getIdTelefone());
                stmt.setInt(2, obj.getNumero());
                stmt.setString(3, obj.getTipo());
                stmt.setInt(4, obj.getInstrutor().getIdInstrutor());
               
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro" +erro);
        }
        
    }
    
    public void excluirTelefone(TelefoneInstrutor obj){
        try{
            String sql = "delete from telefone_instrutor where idtelefone=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getIdTelefone());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar ação " +erro);
        }
    }
    
    public List<TelefoneInstrutor> listarTelefone(){
        try {
            List<TelefoneInstrutor> lista = new ArrayList<>();
            
            String sql = "select t.idtelefone, t.numero, t.tipo, i.idinstrutor from telefone_instrutor as t "
                    + "inner join instrutor as i";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                TelefoneInstrutor obj = new TelefoneInstrutor();
                Instrutor i = new Instrutor();
                
                obj.setIdTelefone(rs.getInt("idtelefone"));
                obj.setNumero(rs.getInt("numero"));
                obj.setTipo(rs.getString("tipo"));
                
                i.setIdInstrutor(rs.getInt(("i.idinstrutor")));
                
                obj.setInstrutor(i);
                
                lista.add(obj);
            }
            
            return lista;
        }
        
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados!" +erro);
            return null;
        }
    }
    
    public void alterarTelefone(TelefoneInstrutor obj){
        
        try{
            String sql = "update telefone_instrutor set idtelefone=?, numero=?, tipo=?, "
                    + "instrutor_idinstrutor1=? where idtelefone=?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getIdTelefone());
                stmt.setInt(2, obj.getNumero());
                stmt.setString(3, obj.getTipo());
                stmt.setInt(4, obj.getInstrutor().getIdInstrutor());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Editar  " +erro);
        }
    }
    
    public List<TelefoneInstrutor> listarTelefonePorNome(int idinstrutor){
        try {
            List<TelefoneInstrutor> lista = new ArrayList<>();
            
            String sql = "select t.idtelefone, t.numero, t.tipo, i.idinstrutor from telefone_instrutor as t "
                    + "inner join instrutor as i";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                TelefoneInstrutor obj = new TelefoneInstrutor();
                Instrutor i = new Instrutor();
                
                obj.setIdTelefone(rs.getInt("idtelefone"));
                obj.setNumero(rs.getInt("numero"));
                obj.setTipo(rs.getString("tipo"));
                
                i.setIdInstrutor(rs.getInt(("i.idinstrutor")));
                
                obj.setInstrutor(i);
                
                lista.add(obj);
            }
            
            return lista;
        }
        
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados!" +erro);
            return null;
        }
    }
    
    public List <TelefoneInstrutor> consultarAtividadePorNome(int idinstrutor){
        try {
            List<TelefoneInstrutor> lista = new ArrayList<>();
            
            String sql = "select t.idtelefone, t.numero, t.tipo, i.idinstrutor from telefone_instrutor as t "
                    + "inner join instrutor as i";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                TelefoneInstrutor obj = new TelefoneInstrutor();
                Instrutor i = new Instrutor();
                
                obj.setIdTelefone(rs.getInt("idtelefone"));
                obj.setNumero(rs.getInt("numero"));
                obj.setTipo(rs.getString("tipo"));
                
                i.setIdInstrutor(rs.getInt(("i.idinstrutor")));
                
                obj.setInstrutor(i);
                
                lista.add(obj);
            }
            
            return lista;
        }
        
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados!" +erro);
            return null;
        }
    }
    
    public List<TelefoneInstrutor> buscaTelefonePorNome(String nome){
        try {
            List<TelefoneInstrutor> lista = new ArrayList<>();
            
            String sql = "select t.idtelefone, t.numero, t.tipo, i.idinstrutor from telefone_instrutor as t "
                    + "inner join instrutor as i";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                TelefoneInstrutor obj = new TelefoneInstrutor();
                Instrutor i = new Instrutor();
                
                obj.setIdTelefone(rs.getInt("idtelefone"));
                obj.setNumero(rs.getInt("numero"));
                obj.setTipo(rs.getString("tipo"));
                
                i.setIdInstrutor(rs.getInt(("i.idinstrutor")));
                
                obj.setInstrutor(i);
                
                lista.add(obj);
            }
            
            return lista;
        }
        
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados!" +erro);
            return null;
        }
    }
}

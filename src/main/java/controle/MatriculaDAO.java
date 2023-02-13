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
import modelo.Matricula;

public class MatriculaDAO {
    
    
    private Connection con;
    
    public MatriculaDAO(){
        this.con = new ConectaFactory().getConection();
    }
    
    public void cadastrarMatricula(Matricula obj){
        try{
            String sql = "insert into matricula(aluno_codMatricula, turma_idturma )"
                    + "values (?, ? )";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getCod_Aluno_Matricula());
                stmt.setInt(2, obj.getId_Turma());
                
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro" +erro);
        }
        
    }
    
    public void excluirMatricula(Matricula obj){
        try{
            String sql = "delete from matricula where aluno_codMatricula=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCod_Aluno_Matricula());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar ação " +erro);
        }
    }
    
    public List<Matricula> listarMatricula(){
        try {
            List<Matricula> lista = new ArrayList<>();
            
            String sql = "select * from matricula";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Matricula obj = new Matricula();
                
                obj.setCod_Aluno_Matricula(rs.getInt("aluno_codMatricula"));
                obj.setId_Turma(rs.getInt("turma_idturma"));
                
                lista.add(obj);
            }
            
            return lista;
        }
        
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados!" +erro);
            return null;
        }
    }
    
    public void alterarMatricula(Matricula obj){
        
        try{
            String sql = "update matricula set turma_idturma=? where aluno_codMatricula=?";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
               stmt.setInt(1, obj.getCod_Aluno_Matricula());
                stmt.setInt(2, obj.getId_Turma());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Editar  " +erro);
        }
    }
    
    public List<Matricula> listarMatriculaPorNome(String nome){
        try{
            List<Matricula> lista= new ArrayList<>();
            
            String sql = "select * from matricula where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Matricula obj = new Matricula();
                obj.setCod_Aluno_Matricula(rs.getInt("aluno_codMatricula"));
                obj.setId_Turma(rs.getInt("turma_idturma"));
               
                lista.add(obj);
            }
            return lista;
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar!  " +erro);
            return null;
        }
    }
    
    public Matricula consultarMatriculaPorNome(String nome){
        try{
            
            String sql = "select * from instrutor where nome=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            Matricula obj = new Matricula();
            
            if(rs.next()){
            
                obj.setCod_Aluno_Matricula(rs.getInt("aluno_codMatricula"));
                obj.setId_Turma(rs.getInt("turma_idturma"));
            }
            
            return obj;
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Atividade Não Encontrado!  " +erro);
            return null;
        }
    }
    
    public List<Matricula> buscaMatriculaPorNome(String nome){
          try{
            List<Matricula> lista= new ArrayList<>();
            
            String sql = "select * from matricula where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Matricula obj = new Matricula();
                obj.setCod_Aluno_Matricula(rs.getInt("aluno_codMatricula"));
                obj.setId_Turma(rs.getInt("turma_idturma"));
               
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

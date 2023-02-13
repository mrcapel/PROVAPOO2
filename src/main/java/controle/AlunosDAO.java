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
import modelo.Alunos;
import modelo.Instrutor;


public class AlunosDAO {
    
    private Connection con;
    
    public AlunosDAO(){
        this.con = new ConectaFactory().getConection();
    }
    
    public void cadastrarAlunos(Alunos obj){
        try{
            String sql = "insert into aluno(codMatricula, turma_idturma, dataMatricula, nome, endereco "
                    + "telefone, dataNascimento, altura, peso)"
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getCodMatricula());
                stmt.setInt(2, obj.getIdTurma());
                stmt.setString(3, obj.getDataMatricula());
                stmt.setString(4, obj.getNome());
                stmt.setString(5, obj.getEndereco());
                stmt.setInt(6, obj.getTelefone());
                stmt.setString(7, obj.getDataNascimento());
                stmt.setDouble(8, obj.getAltura());
                stmt.setInt(9, obj.getPeso());
                
               
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro" +erro);
        }
        
    }
    
    public void excluirAlunos(Alunos obj){
        try{
            String sql = "delete from instrutor where CodMatricula=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCodMatricula());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar ação " +erro);
        }
    }
    
    public List<Alunos> listarAlunos(){
        try {
            List<Alunos> lista = new ArrayList<>();
            
            String sql = "select * from aluno";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Alunos obj = new Alunos();
                
                obj.setCodMatricula(rs.getInt("CodMatricula"));
                obj.setIdTurma(rs.getInt("turma_idturma"));
                obj.setDataMatricula(rs.getString("dataMatricula"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setTelefone(rs.getInt("telefone"));
                obj.setDataNascimento(rs.getString("dataNascimento"));
                obj.setAltura(rs.getDouble("altura"));
                obj.setPeso(rs.getInt("peso"));
                
                
                lista.add(obj);
            }
            
            return lista;
        }
        
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados!" +erro);
            return null;
        }
    }
    
    public void alterarAlunos(Alunos obj){
        
        try{
            String sql = "update Alunos set IdTurma=?, dataMatricula=?, nome=?, endereco=?, telefone, "
                    + "dataNascimento, altura, peso "
                    + " where CodMatricula=?";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(9, obj.getCodMatricula());
                stmt.setInt(2, obj.getIdTurma());
                stmt.setString(2, obj.getDataMatricula());
                stmt.setString(3, obj.getNome());
                stmt.setString(4, obj.getEndereco());
                stmt.setInt(5, obj.getTelefone());
                stmt.setString(6, obj.getDataNascimento());
                stmt.setDouble(7, obj.getAltura());
                stmt.setInt(8, obj.getPeso());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Editar  " +erro);
        }
    }
    
    public List<Alunos> listarAlunosPorNome(String nome){
        try{
            List<Alunos> lista= new ArrayList<>();
            
            String sql = "select * from Alunos where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Alunos obj = new Alunos();
                obj.setCodMatricula(rs.getInt("CodMatricula"));
                obj.setIdTurma(rs.getInt("turma_idturma"));
                obj.setDataMatricula(rs.getString("dataMatricula"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setTelefone(rs.getInt("telefone"));
                obj.setDataNascimento(rs.getString("dataNascimento"));
                obj.setAltura(rs.getDouble("altura"));
                obj.setPeso(rs.getInt("peso"));
               
                lista.add(obj);
            }
            return lista;
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Falha ao pesquisar!  " +erro);
            return null;
        }
    }
    
    public Alunos consultarAlunosPorNome(String nome){
        try{
            
            String sql = "select * from Alunos where nome=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            
            Alunos obj = new Alunos();
            
            if(rs.next()){
                obj.setCodMatricula(rs.getInt("CodMatricula"));
                obj.setIdTurma(rs.getInt("turma_idturma"));
                obj.setDataMatricula(rs.getString("dataMatricula"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setTelefone(rs.getInt("telefone"));
                obj.setDataNascimento(rs.getString("dataNascimento"));
                obj.setAltura(rs.getDouble("altura"));
                obj.setPeso(rs.getInt("peso"));
            }
            
            return obj;
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Atividade Não Encontrado!  " +erro);
            return null;
        }
    }
    
    public List<Alunos> buscaAlunosPorNome(String nome){
        try{
            List<Alunos> lista= new ArrayList<>();
            
            String sql = "select * from Alunos where nome like?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Alunos obj = new Alunos();
                obj.setCodMatricula(rs.getInt("CodMatricula"));
                obj.setIdTurma(rs.getInt("turma_idturma"));
                obj.setDataMatricula(rs.getString("dataMatricula"));
                obj.setNome(rs.getString("nome"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setTelefone(rs.getInt("telefone"));
                obj.setDataNascimento(rs.getString("dataNascimento"));
                obj.setAltura(rs.getDouble("altura"));
                obj.setPeso(rs.getInt("peso"));
               
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

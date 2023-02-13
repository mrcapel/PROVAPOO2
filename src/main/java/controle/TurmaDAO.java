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
import modelo.Instrutor;
import modelo.Turma;


public class TurmaDAO {
    
    private Connection con;
    
    public TurmaDAO(){
        this.con = new ConectaFactory().getConection();
    }
    
    public void cadastrarTurma(Turma obj){
        try{
            String sql = "insert into turma(idturma, horario, duracao, dataInicio, dataFim )"
                    + "values (?, ?, ?, ?, ?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getIdTurma());
                stmt.setString(2, obj.getHorario());
                stmt.setInt(3, obj.getDuracao());
                stmt.setString(4, obj.getDataInicio());
                stmt.setString(5, obj.getDataFim());
               
                stmt.execute();
            }
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar o cadastro" +erro);
        }
        
    }
    
    public void excluirTurma(Turma obj){
        try{
            String sql = "delete from turma where instrutor_idinstrutor=?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(4, obj.getInstrutor().getIdInstrutor());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao efetuar ação " +erro);
        }
    }
    
    public List<Turma> listarTurma(){
        try {
            List<Turma> lista = new ArrayList<>();
            
            String sql = "select * from turma ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Turma obj = new Turma();
                Instrutor i = new Instrutor();
                Atividade a = new Atividade();
                
                obj.setIdTurma(rs.getInt("idturma"));
                obj.setHorario(rs.getString("horario"));
                obj.setDuracao(rs.getInt("duracao"));
                obj.setDataInicio(rs.getString("dataInicio"));
                obj.setDataFim(rs.getString("dataFim"));
                
                //a.setIdatividade(rs.getInt(("atividade.idatividade")));
                //i.setIdInstrutor(rs.getInt(("instrutor.idinstrutor")));
                
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
    
    public void alterarTurma(Turma obj){
        
        try{
            String sql = "update turma set idTurma=?, horario=?, duracao=?, "
                    + "dataInicio=?, dataFim=? where idTurma=?)";
            
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, obj.getIdTurma());
                stmt.setString(2, obj.getHorario());
                stmt.setInt(3, obj.getDuracao());
                stmt.setString(4, obj.getDataInicio());
                stmt.setString(5, obj.getDataFim());
                
                stmt.execute();
            }
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Editar  " +erro);
        }
    }
    
    public List<Turma> listarTurmaPorNome(int idTurma){
        try {
            List<Turma> lista = new ArrayList<>();
            
            String sql = "select * from turma ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Turma obj = new Turma();
                
                obj.setIdTurma(rs.getInt("idturma"));
                obj.setHorario(rs.getString("horario"));
                obj.setDuracao(rs.getInt("duracao"));
                obj.setDataInicio(rs.getString("dataInicio"));
                obj.setDataFim(rs.getString("dataFim"));

                
                lista.add(obj);
            }
            
            return lista;
        }
        
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados!" +erro);
            return null;
        }
    }
    
    public List <Turma> consultarTurmaPorNome(int idTurma){
        try {
            List<Turma> lista = new ArrayList<>();
            
            String sql = "select * from turma ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Turma obj = new Turma();
                
                obj.setIdTurma(rs.getInt("idturma"));
                obj.setHorario(rs.getString("horario"));
                obj.setDuracao(rs.getInt("duracao"));
                obj.setDataInicio(rs.getString("dataInicio"));
                obj.setDataFim(rs.getString("dataFim"));
                
                
                lista.add(obj);
            }
            
            return lista;
        }
        
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar os dados!" +erro);
            return null;
        }
    }
    
    public List<Turma> buscaTurmaPorNome(String nome){
        try {
            List<Turma> lista = new ArrayList<>();
            
            String sql = "select * from turma ";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Turma obj = new Turma();
                
                obj.setIdTurma(rs.getInt("idturma"));
                obj.setHorario(rs.getString("horario"));
                obj.setDuracao(rs.getInt("duracao"));
                obj.setDataInicio(rs.getString("dataInicio"));
                obj.setDataFim(rs.getString("dataFim"));
                
                
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

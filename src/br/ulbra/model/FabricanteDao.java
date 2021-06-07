package br.ulbra.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Erik
 */
public class FabricanteDao{
    Connection con;
    
    public FabricanteDao() throws SQLException{
        con = ConnectionFactory.getConnection();
    }
    
    
    public void create(Fabricante f){
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("INSERT INTO tbfabricante (marca,telefone,site,email) VALUE (?,?,?,?)");
            
            stmt.setString(1, f.getMarca());
            stmt.setString(2, f.getTelefone());
            stmt.setString(3, f.getSite());
            stmt.setString(4, f.getEmail());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Fabricante " + f.getMarca() + " de ID " + f.getId() + " salvo com sucesso");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public void update(Fabricante f){
       PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("UPDATE tbfabricante SET marca = ?, telefone = ?, site = ?, email = ? WHERE id = ?");
            
            stmt.setString(1, f.getMarca());
            stmt.setString(2, f.getTelefone());
            stmt.setString(3, f.getSite());
            stmt.setString(4, f.getEmail());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Fabricante " + f.getMarca() + " de ID " + f.getId() + " modificado com sucesso");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
    }
    
    
    public void delete(Fabricante f){
        PreparedStatement stmt = null;
        try{
            stmt = con.prepareStatement("DELETE FROM tbfabricante WHERE id = ?");
            
            stmt.setInt(1, f.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Fabricante " + f.getMarca() + " de ID " + f.getId() + " excluido com sucesso");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public ArrayList<Fabricante> read(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Fabricante> fabs = new ArrayList<>();
        try{
            stmt = con.prepareStatement("SELECT * FROM tbfabricante ORDER BY id ASC");
            rs = stmt.executeQuery();
            while(rs.next()){
                Fabricante fabricante = new Fabricante();
                
                fabricante.setId(rs.getInt("id"));
                fabricante.setMarca(rs.getString("marca"));
                fabricante.setTelefone(rs.getString("telefone"));
                fabricante.setSite(rs.getString("site"));
                fabricante.setEmail(rs.getString("email"));
                
                fabs.add(fabricante);
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Fabricante>) fabs;
    
    }
    
    
    public ArrayList<Fabricante> readPesq(String marca){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Fabricante> fabs = new ArrayList<>();
        try{
            stmt = con.prepareStatement("SELECT * FROM tbfabricante WHERE marca LIKE ?");
            
            stmt.setString(1, "%" + marca + "%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Fabricante fabricante = new Fabricante();
                
                fabricante.setId(rs.getInt("id"));
                fabricante.setMarca(rs.getString("marca"));
                fabricante.setTelefone(rs.getString("telefone"));
                fabricante.setSite(rs.getString("site"));
                fabricante.setEmail(rs.getString("email"));
                
                fabs.add(fabricante);
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return (ArrayList<Fabricante>) fabs;
    
    }
}

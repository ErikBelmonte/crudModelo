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
public class ModeloDao {
    Connection con;
    
    public ModeloDao() throws SQLException
    {
        con = ConnectionFactory.getConnection();
    }
    
    
    public void create(Modelo m)
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("INSERT INTO tbmodelo (modelo,marca,ano) VALUE (?,?,?)");
            
            stmt.setString(1, m.getModelo());
            stmt.setString(2, m.getMarca());
            stmt.setInt(3, m.getAno());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Modelo " + m.getModelo() + " de ID " + m.getId() + " salvo com sucesso");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public void update(Modelo m)
    {
       PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("UPDATE tbmodelo SET modelo = ?, marca = ?, ano = ? WHERE id = ?");
            
            stmt.setString(1, m.getModelo());
            stmt.setString(2, m.getMarca());
            stmt.setInt(3, m.getAno());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Modelo " + m.getModelo() + " de ID " + m.getId() + " modificado com sucesso");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        } 
    }
   
    
    public void delete(Modelo m)
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("DELETE FROM tbmodelo WHERE id = ?");
            
            stmt.setInt(1, m.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Modelo " + m.getModelo() + " de ID " + m.getId() + " excluido com sucesso");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
    public ArrayList<Modelo> read()
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Modelo> mods = new ArrayList<>();
        try
        {
            stmt = con.prepareStatement("SELECT * FROM tbmodelo ORDER BY id ASC");
            rs = stmt.executeQuery();
            while(rs.next())
            {
                Modelo modelos = new Modelo();
                
                modelos.setId(rs.getInt("id"));
                modelos.setModelo(rs.getString("modelo"));
                modelos.setMarca(rs.getString("marca"));
                modelos.setAno(rs.getInt("ano"));          
                
                mods.add(modelos);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Modelo>) mods;
    
    }
    
    
    public ArrayList<Modelo> readPesq(String modelo)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Modelo> mods = new ArrayList<>();
        try
        {
            stmt = con.prepareStatement("SELECT * FROM tbmodelo WHERE marca LIKE ?");
            
            stmt.setString(1, "%" + modelo + "%");
            
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Modelo modelos = new Modelo();
                
                modelos.setId(rs.getInt("id"));
                modelos.setModelo(rs.getString("modelo"));
                modelos.setMarca(rs.getString("marca"));
                modelos.setAno(rs.getInt("ano"));          
                
                mods.add(modelos);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return (ArrayList<Modelo>) mods;
    
    }
}

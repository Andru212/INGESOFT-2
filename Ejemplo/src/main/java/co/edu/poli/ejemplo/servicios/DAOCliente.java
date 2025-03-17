package co.edu.poli.ejemplo.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.ejemplo.modelo.Cliente;

public class DAOCliente implements CRUD<Cliente, String, String> {

    private final Connection connection;

    public DAOCliente() throws SQLException {

        this.connection = Singleton.getConnection();
    }

    @Override
    public String create(Cliente c) {
        // Primero, verificamos si ya existe un cliente con el mismo ID
        String checkSql = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setString(1, c.getId());
            try (ResultSet rs = checkStmt.executeQuery()) {
                // Si ya existe un cliente con ese ID
                if (rs.next()) {
                    return "Error: Ya existe un cliente con el ID " + c.getId();
                }
            }
        } catch (Exception e) {
            return "Error de base de datos: " + e.getMessage();
        }

        // Si no existe el cliente, procedemos a insertarlo
        String sql = "INSERT INTO clientes (id, nombre) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getId());
            stmt.setString(2, c.getNombre());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Cliente creado exitosamente";
            } else {
                return "Error al crear cliente";
            }
        } catch (Exception e) {
            return "Error de base de datos: " + e.getMessage();
        }
    }

    @Override
    public List<Cliente> readAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getString("id"));
                c.setNombre(rs.getString("nombre"));
                clientes.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public Cliente read(String id) throws Exception {
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setId(rs.getString("id"));
            cliente.setNombre(rs.getString("nombre"));
        } else {
            return null;
        }

        return cliente;
    }

   
    public Cliente findByName(String nombre) {
        String sql = "SELECT id, nombre FROM clientes WHERE nombre = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(rs.getString("id"), rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente por nombre", e);
        }
        
        return null; // Retorna null si no encuentra el cliente
    }
    
    

    @Override
    public String update(String id, Cliente c) throws Exception {
        String sql = "UPDATE clientes SET nombre = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, c.getNombre());
        stmt.setString(2, id);

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            return "Cliente actualizado exitosamente";
        } else {
            return "Cliente no encontrado en la base de datos";
        }

    }

    @Override
    public String delete(String id) throws Exception {
        String sql = "DELETE FROM clientes WHERE id = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, id);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            return "Cliente eliminado exitosamente";
        } else {
            return "Cliente no encontrado en la base de datos";
        }

    }

}

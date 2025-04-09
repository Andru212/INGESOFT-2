package co.edu.poli.ejemplo.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.ejemplo.modelo.Pedido;
import co.edu.poli.ejemplo.modelo.Cliente;
import co.edu.poli.ejemplo.modelo.Producto;
import co.edu.poli.ejemplo.modelo.ProductoAlimentos;
import co.edu.poli.ejemplo.modelo.ProductoElectronico;

public class DAOPedido {

    private Connection connection;

    public DAOPedido() throws SQLException {
        this.connection = Singleton.getConnection();
    }

    public String create(Pedido p) {
        // Insertar en la tabla principal "pedido"
        String sqlPedido = "INSERT INTO pedido (numero, fecha, cliente_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlPedido)) {
            stmt.setString(1, p.getNumero());
            stmt.setDate(2, java.sql.Date.valueOf(p.getFecha()));
            stmt.setString(3, p.getCliente().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            return "Error al insertar en pedido: " + e.getMessage();
        }

        // Insertar productos asociados al pedido
        for (Producto producto : p.getProducto()) {
            String sqlProducto = "INSERT INTO pedido_producto (pedido_numero, producto_id) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sqlProducto)) {
                stmt.setString(1, p.getNumero());
                stmt.setString(2, producto.getId());
                stmt.executeUpdate();
            } catch (Exception e) {
                return "Error al insertar en pedido_producto: " + e.getMessage();
            }
        }
        return "Pedido creado exitosamente";
    }

    public List<Pedido> readAll() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.numero, p.fecha, p.cliente_id FROM pedido p";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String numero = rs.getString("numero");
                String fecha = rs.getDate("fecha").toString();
                Cliente cliente = getClienteById(rs.getString("cliente_id"));
                List<Producto> productos = getProductosByPedidoNumero(numero);
                pedidos.add(new Pedido(cliente, fecha, numero, productos));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al leer pedidos: " + e.getMessage());
        }
        return pedidos;
    }

    public Pedido read(String numero) {
        String sql = "SELECT p.numero, p.fecha, p.cliente_id FROM pedido p WHERE p.numero = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, numero);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String fecha = rs.getDate("fecha").toString();
                    String clienteId = rs.getString("cliente_id");
    
                    // Manejo si el cliente_id es NULL
                    Cliente cliente = null;
                    if (clienteId != null) {
                        cliente = getClienteById(clienteId);
                    }
    
                    // Obtener productos asociados al pedido
                    List<Producto> productos = getProductosByPedidoNumero(numero);
    
                    return new Pedido(cliente, fecha, numero, productos);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al leer pedido: " + e.getMessage());
        }
        
        return null;
    }
    

    public String update(String numero, Pedido p) {
        // Primero, actualizar la tabla "pedido"
        String sqlPedido = "UPDATE pedido SET fecha = ?, cliente_id = ? WHERE numero = ?";
        try (PreparedStatement stmtPedido = connection.prepareStatement(sqlPedido)) {
            stmtPedido.setDate(1, java.sql.Date.valueOf(p.getFecha()));
            stmtPedido.setString(2, p.getCliente().getId());
            stmtPedido.setString(3, numero);
            stmtPedido.executeUpdate();
        } catch (Exception e) {
            return "Error al actualizar pedido: " + e.getMessage();
        }

        // Luego, actualizar los productos asociados al pedido
        String deleteProductosSql = "DELETE FROM pedido_producto WHERE pedido_numero = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteProductosSql)) {
            deleteStmt.setString(1, numero);
            deleteStmt.executeUpdate();
        } catch (Exception e) {
            return "Error al eliminar productos asociados: " + e.getMessage();
        }

        for (Producto producto : p.getProducto()) {
            String sqlProducto = "INSERT INTO pedido_producto (pedido_numero, producto_id) VALUES (?, ?)";
            try (PreparedStatement stmtProducto = connection.prepareStatement(sqlProducto)) {
                stmtProducto.setString(1, numero);
                stmtProducto.setString(2, producto.getId());
                stmtProducto.executeUpdate();
            } catch (Exception e) {
                return "Error al insertar productos asociados: " + e.getMessage();
            }
        }

        return "Pedido actualizado exitosamente";
    }

    public String delete(String numero) {
        // Eliminar productos asociados al pedido
        String deleteProductosSql = "DELETE FROM pedido_producto WHERE pedido_numero = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteProductosSql)) {
            deleteStmt.setString(1, numero);
            deleteStmt.executeUpdate();
        } catch (Exception e) {
            return "Error al eliminar productos asociados: " + e.getMessage();
        }

        // Eliminar el pedido
        String sqlPedido = "DELETE FROM pedido WHERE numero = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlPedido)) {
            stmt.setString(1, numero);
            stmt.executeUpdate();
            return "Pedido eliminado exitosamente";
        } catch (Exception e) {
            return "Error al eliminar pedido: " + e.getMessage();
        }
    }

    private Cliente getClienteById(String id) {
        Cliente cliente = null;
        String sql = "SELECT id, nombre FROM clientes WHERE id = ?";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(rs.getString("id"), rs.getString("nombre"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener cliente por ID", e);
        }
    
        return cliente; // Si no encuentra el cliente, retorna null
    }
    

    private List<Producto> getProductosByPedidoNumero(String numero) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.id, p.descripcion, p.precio, a.aporte_calorico, e.voltaje_entrada " +
                     "FROM productos p " +
                     "LEFT JOIN productos_alimentos a ON p.id = a.id " +
                     "LEFT JOIN productos_electronicos e ON p.id = e.id " +
                     "JOIN pedido_producto pp ON p.id = pp.producto_id " +
                     "WHERE pp.pedido_numero = ?";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String descripcion = rs.getString("descripcion");
                    double precio = rs.getDouble("precio");
                    
                    Float aporteCalorico = rs.getObject("aporte_calorico", Float.class);
                    Float voltajeEntrada = rs.getObject("voltaje_entrada", Float.class);
    
                    Producto producto = null;
    
                    if (aporteCalorico != null) {  
                        producto = new ProductoAlimentos(id, descripcion, precio, aporteCalorico);
                    } else if (voltajeEntrada != null) {  
                        producto = new ProductoElectronico(id, descripcion, precio, voltajeEntrada);
                    }
    
                    if (producto != null) {
                        productos.add(producto);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener productos del pedido", e);
        }
    
        return productos;
    }
    

    
}
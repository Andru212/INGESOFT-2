package co.edu.poli.ejemplo.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.ejemplo.modelo.Producto;
import co.edu.poli.ejemplo.modelo.ProductoAlimentos;
import co.edu.poli.ejemplo.modelo.ProductoElectronico;

public class DAOProducto implements DAOConsultasEspecializadas {

    private Connection connection;

    public DAOProducto() throws SQLException {
        this.connection = Singleton.getConnection();
    }

    @Override
    public String create(Producto p) {
        // Insertar en la tabla principal "productos"
        String sqlProducto = "INSERT INTO productos (id, descripcion, precio) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlProducto)) {
            stmt.setString(1, p.getId());
            stmt.setString(2, p.getDescripcion());
            stmt.setDouble(3, p.getPrecio());
            stmt.executeUpdate();
        } catch (Exception e) {
            return "Error al insertar en productos: " + e.getMessage();
        }

        // Insertar en la tabla específica según el tipo de producto
        if (p instanceof ProductoAlimentos) {
            String sqlAlimentos = "INSERT INTO productos_alimentos (id, aporte_calorico) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sqlAlimentos)) {
                stmt.setString(1, p.getId());
                stmt.setFloat(2, ((ProductoAlimentos) p).getAporteCalorico());
                stmt.executeUpdate();
            } catch (Exception e) {
                return "Error al insertar en productos_alimentos: " + e.getMessage();
            }
        } else if (p instanceof ProductoElectronico) {
            String sqlElectronico = "INSERT INTO productos_electronicos (id, voltaje_entrada) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sqlElectronico)) {
                stmt.setString(1, p.getId());
                stmt.setFloat(2, ((ProductoElectronico) p).getVoltajeEntrada());
                stmt.executeUpdate();
            } catch (Exception e) {
                return "Error al insertar en productos_electronicos: " + e.getMessage();
            }
        }
        return "Producto creado exitosamente";
    }

    @Override
    public List<Producto> readAll() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.id, p.descripcion, p.precio, a.aporte_calorico, e.voltaje_entrada FROM productos p "
                + "LEFT JOIN productos_alimentos a ON p.id = a.id "
                + "LEFT JOIN productos_electronicos e ON p.id = e.id";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String id = rs.getString("id");
                String descripcion = rs.getString("descripcion");
                double precio = rs.getDouble("precio");
                Float aporteCalorico = rs.getFloat("aporte_calorico");
                Float voltajeEntrada = rs.getFloat("voltaje_entrada");

                if (aporteCalorico != 0) {
                    productos.add(new ProductoAlimentos(id, descripcion, precio, aporteCalorico));
                } else if (voltajeEntrada != 0) {
                    productos.add(new ProductoElectronico(id, descripcion, precio, voltajeEntrada));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer productos: " + e.getMessage());
        }
        return productos;
    }

    @Override
    public Producto read(String id) {
        String sql = "SELECT p.id, p.descripcion, p.precio, a.aporte_calorico, e.voltaje_entrada FROM productos p "
                + "LEFT JOIN productos_alimentos a ON p.id = a.id "
                + "LEFT JOIN productos_electronicos e ON p.id = e.id WHERE p.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String descripcion = rs.getString("descripcion");
                    double precio = rs.getDouble("precio");
                    Float aporteCalorico = rs.getFloat("aporte_calorico");
                    Float voltajeEntrada = rs.getFloat("voltaje_entrada");
                    if (aporteCalorico != 0) {
                        return new ProductoAlimentos(id, descripcion, precio, aporteCalorico);
                    } else if (voltajeEntrada != 0) {
                        return new ProductoElectronico(id, descripcion, precio, voltajeEntrada);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer producto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String update(String id, Producto p) {
        // Primero, actualizar la tabla "productos"
        String sqlProducto = "UPDATE productos SET descripcion = ?, precio = ? WHERE id = ?";
        try (PreparedStatement stmtProducto = connection.prepareStatement(sqlProducto)) {
            stmtProducto.setString(1, p.getDescripcion());
            stmtProducto.setDouble(2, p.getPrecio());
            stmtProducto.setString(3, id);
            stmtProducto.executeUpdate();
        } catch (Exception e) {
            return "Error al actualizar productos: " + e.getMessage();
        }

        // Luego, actualizar la tabla hija según el tipo de producto
        if (p instanceof ProductoAlimentos) {
            String sqlAlimentos = "UPDATE productos_alimentos SET aporte_calorico = ? WHERE id = ?";
            try (PreparedStatement stmtAlimentos = connection.prepareStatement(sqlAlimentos)) {
                stmtAlimentos.setFloat(1, ((ProductoAlimentos) p).getAporteCalorico());
                stmtAlimentos.setString(2, id);
                stmtAlimentos.executeUpdate();
            } catch (Exception e) {
                return "Error al actualizar productos_alimentos: " + e.getMessage();
            }
        } else if (p instanceof ProductoElectronico) {
            String sqlElectronicos = "UPDATE productos_electronicos SET voltaje_entrada = ? WHERE id = ?";
            try (PreparedStatement stmtElectronicos = connection.prepareStatement(sqlElectronicos)) {
                stmtElectronicos.setFloat(1, ((ProductoElectronico) p).getVoltajeEntrada());
                stmtElectronicos.setString(2, id);
                stmtElectronicos.executeUpdate();
            } catch (Exception e) {
                return "Error al actualizar productos_electronicos: " + e.getMessage();
            }
        }

        return "Producto actualizado exitosamente";
    }

    @Override
    public String delete(String id) {
        String sqlProductos = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sqlProductos)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            return "Producto eliminado exitosamente";
        } catch (Exception e) {
            return "Error al eliminar producto: " + e.getMessage();
        }
    }

    public List<Producto> leerPorRangoDePrecios(double precioMin, double precioMax) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.id, p.descripcion, p.precio, a.aporte_calorico, e.voltaje_entrada FROM productos p "
                + "LEFT JOIN productos_alimentos a ON p.id = a.id "
                + "LEFT JOIN productos_electronicos e ON p.id = e.id "
                + "WHERE p.precio BETWEEN ? AND ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, precioMin);
            stmt.setDouble(2, precioMax);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String descripcion = rs.getString("descripcion");
                    double precio = rs.getDouble("precio");
                    Float aporteCalorico = rs.getFloat("aporte_calorico");
                    Float voltajeEntrada = rs.getFloat("voltaje_entrada");

                    if (aporteCalorico != 0) {
                        productos.add(new ProductoAlimentos(id, descripcion, precio, aporteCalorico));
                    } else if (voltajeEntrada != 0) {
                        productos.add(new ProductoElectronico(id, descripcion, precio, voltajeEntrada));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer productos en el rango de precios: " + e.getMessage());
        }
        return productos;
    }

}

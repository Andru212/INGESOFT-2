package co.edu.poli.ejemplo.controlador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.util.Duration;
import co.edu.poli.ejemplo.modelo.Cliente;
import co.edu.poli.ejemplo.modelo.FactoryAlimento;
import co.edu.poli.ejemplo.modelo.FactoryElectronico;
import co.edu.poli.ejemplo.modelo.Producto;
import co.edu.poli.ejemplo.modelo.ProductoAlimentos;
import co.edu.poli.ejemplo.modelo.ProductoElectronico;
import co.edu.poli.ejemplo.modelo.ProductoFactory;
import co.edu.poli.ejemplo.modelo.PrototipoProducto;
import co.edu.poli.ejemplo.servicios.DAOCliente;
import co.edu.poli.ejemplo.servicios.DAOProducto;
import co.edu.poli.ejemplo.servicios.Singleton;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class MainController {

    @FXML
    private Button CrearBD;

    @FXML
    private TextArea Mostrar_productos;
    @FXML
    private TextField Precio_maximo;
    @FXML
    private TextField Precio_minimo;
    @FXML
    private Button btt_crearCliente;
    @FXML
    private Button btt_crearProducto;
    @FXML
    private Button btt_eliminarCliente;
    @FXML
    private Button btt_eliminarProducto;
    @FXML
    private Button btt_encontrar_cliente_id;
    @FXML
    private Button btt_encontrar_id;
    @FXML
    private Button btt_encontrar_precio;
    @FXML
    private Button btt_modificarCliente;
    @FXML
    private Button btt_modificarProducto;
    @FXML
    private Button btt_salir;
    @FXML
    private Button btt_verCliente;
    @FXML
    private Button btt_verProducto;
    @FXML
    private MenuButton categoriaProducto;
    @FXML
    private Button clonar;
    @FXML
    private TextArea label_mostrar_cliente;
    @FXML
    private TextArea mostrar_clon;
    @FXML
    private Label msg_alerta;
    @FXML
    private Label msg_alerta_cliente;
    @FXML
    private Label msg_alerta_producto;
    @FXML
    private Button probarConexion;
    @FXML
    private TextField txt_idCliente;
    @FXML
    private TextField txt_idProducto;
    @FXML
    private TextField txt_nombreCliente;
    @FXML
    private TextField txt_nombreProducto;
    @FXML
    private TextField txt_precioProducto;
    @FXML
    private TextField voltaje_calorias;
    @FXML
    private Button clonarporip;
    @FXML
    void ProbarConexion(ActionEvent event) {
            try {
        Connection conn = Singleton.getConnection();
        if (conn != null && !conn.isClosed()) {
            msg_alerta.setText("✅ Conexión exitosa con la base de datos.");
        } else {
            msg_alerta.setText("⚠ No se pudo establecer la conexión.");
        }
    } catch (SQLException e) {
        msg_alerta.setText("❌ Error de conexión: " + e.getMessage());
    }    
    }

 @FXML
void Crear_bd(ActionEvent event) {
    String rutaSQL = "C:/Users/LENOVO-02500100/Desktop/Andres/UNI/INGESOFT-2/Scrip sql/BD_script.sql";
    crearBaseDeDatos(rutaSQL);
}

private void crearBaseDeDatos(String rutaArchivo) {
    try {
        ResourceBundle rd = ResourceBundle.getBundle("config");
        String dbServer = rd.getString("db.server"); // Conexión sin BD específica
        String dbUSERNAME = rd.getString("db.username");
        String dbPASSWORD = rd.getString("db.password");

        Connection conn = DriverManager.getConnection(dbServer, dbUSERNAME, dbPASSWORD);
        Statement stmt = conn.createStatement();

        // Verificar si la base de datos ya existe
        ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE 'tienda';");
        if (rs.next()) {
            msg_alerta.setText("⚠ La base de datos ya existe.");
        } else {
            // Si no existe, crearla y ejecutar el script
            stmt.execute("CREATE DATABASE tienda;");
            stmt.execute("USE tienda;");
            
            ejecutarScriptSQL(stmt, rutaArchivo);
            msg_alerta.setText("✅ Base de datos creada correctamente.");
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (Exception e) {
        msg_alerta.setText("❌ Error al crear la base de datos: " + e.getMessage());
    }
}

private void ejecutarScriptSQL(Statement stmt, String rutaArchivo) {
    try {
        File sqlFile = new File(rutaArchivo);
        if (!sqlFile.exists()) {
            msg_alerta.setText("⚠ No se encontró el archivo SQL.");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(sqlFile));
        StringBuilder sqlQuery = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            sqlQuery.append(line).append("\n");
            if (line.trim().endsWith(";")) {
                stmt.execute(sqlQuery.toString());
                sqlQuery.setLength(0);
            }
        }

        br.close();
    } catch (Exception e) {
        msg_alerta.setText("❌ Error al ejecutar script SQL: " + e.getMessage());
    }
}



    private DAOCliente daoCliente;
    @FXML
public void initialize() {
    try {
        daoCliente = new DAOCliente(); // Asegurar conexión con la BD
        cargar_categorias();
    } catch (Exception e) {
        label_mostrar_cliente.setText("⚠️ Error al conectar con la base de datos: " + e.getMessage());
    }
}
@FXML
void ver_clientes() throws SQLException {
label_mostrar_cliente.clear(); // Limpiar el área de texto antes de mostrar los datos
msg_alerta_cliente.setText(""); // Limpiar mensajes previos
DAOCliente daoCliente = new DAOCliente(); // Asegúrate de que tu DAOCliente tiene la conexión a la BD
List<Cliente> clientes = daoCliente.readAll();
if (clientes.isEmpty()) {
    msg_alerta_cliente.setText("No hay clientes registrados.");
    return;
}
StringBuilder sb = new StringBuilder();
for (Cliente c : clientes) {
    sb.append(c.toString()).append("\n"); // Usar el toString() de Cliente
}
label_mostrar_cliente.setText(sb.toString());
}
    @FXML
    void actualizar_cliente() {
 // Obtener datos de los campos de texto
 String idCliente = txt_idCliente.getText().trim();
 String nombreCliente = txt_nombreCliente.getText().trim();
 // Validar que no estén vacíos
 if (idCliente.isEmpty() || nombreCliente.isEmpty()) {
     msg_alerta_cliente.setText("⚠️ Error: ID y Nombre no pueden estar vacíos.");
     return;
 }
 // Crear objeto Cliente con el nuevo nombre
 Cliente clienteActualizado = new Cliente();
 clienteActualizado.setNombre(nombreCliente);
 try {
     // Llamar al método update del DAO
     String resultado = daoCliente.update(idCliente, clienteActualizado);
     // Mostrar resultado en el TextArea
     label_mostrar_cliente.setText(resultado);
 } catch (Exception e) {
     msg_alerta_cliente.setText("⚠️ Error al actualizar cliente: " + e.getMessage());
 }
    }
    @FXML
    void crear_Cliente() throws SQLException {
    // Obtener los datos ingresados desde los TextField
    String idCliente = txt_idCliente.getText().trim();
    String nombreCliente = txt_nombreCliente.getText().trim();
    // Validar que los datos no estén vacíos
    if (idCliente.isEmpty() || nombreCliente.isEmpty()) {
        msg_alerta_cliente.setText("⚠️ Error: Ingrese todos los datos.");
        return;
    }
    // Crear objeto Cliente
    Cliente nuevoCliente = new Cliente(idCliente, nombreCliente);
    // Llamar al DAO para insertar en la base de datos
    DAOCliente clienteDAO = new DAOCliente(); // Asegúrate de inicializar correctamente
    String resultado = clienteDAO.create(nuevoCliente);
    // Mostrar el resultado en la interfaz
    if (resultado.startsWith("Error")) {
        msg_alerta_cliente.setText("❌ " + resultado);
    } else {
        msg_alerta_cliente.setText("✅ Cliente registrado correctamente.");
        label_mostrar_cliente.setText("Cliente creado:\nID: " + idCliente + "\nNombre: " + nombreCliente);
        PauseTransition pausa = new PauseTransition(Duration.seconds(2));
        pausa.setOnFinished(e -> {
            try {
                ver_clientes();
            } catch (SQLException e1) {
                msg_alerta_cliente.setText(e1.getMessage());
            }
        }); // Verificar que el método existe
        pausa.play();
        // Limpiar los campos después de la inserción
        txt_idCliente.clear();
        txt_nombreCliente.clear();
    }
        
    }
    @FXML
    void eliminar_cliente() {
// Obtener el ID del cliente
String idCliente = txt_idCliente.getText().trim();
// Validar que el ID no esté vacío
if (idCliente.isEmpty()) {
    msg_alerta_cliente.setText("⚠️ Error: Debe ingresar un ID para eliminar.");
    return;
}
try {
    // Intentar eliminar el cliente
    String resultado = daoCliente.delete(idCliente);
    // Mostrar mensaje en msg_alerta_cliente
    label_mostrar_cliente.setText(resultado);
    
    PauseTransition pausa = new PauseTransition(Duration.seconds(2));
    pausa.setOnFinished(e -> {
        try {
            ver_clientes();
        } catch (SQLException e1) {
            msg_alerta_cliente.setText(e1.getMessage());
        }
    }); // Verificar que el método existe
    pausa.play();
    // Limpiar campos después de la eliminación
    txt_idCliente.clear();
    txt_nombreCliente.clear();
} catch (Exception e) {
    msg_alerta_cliente.setText("⚠️ Error al eliminar cliente: " + e.getMessage());
}
    }
    @FXML
    void encontrar_cliente_id(ActionEvent event) {
    // Obtener el ID ingresado por el usuario
    String idCliente = txt_idCliente.getText().trim();
    // Validar que el ID no esté vacío
    if (idCliente.isEmpty()) {
        msg_alerta_cliente.setText("⚠️ Error: Debe ingresar un ID para consultar.");
        return;
    }
    try {
        // Buscar cliente en la base de datos
        Cliente cliente = daoCliente.read(idCliente);
        if (cliente != null) {
            // Mostrar la información del cliente en label_mostrar_cliente
            label_mostrar_cliente.setText(cliente.toString());
            msg_alerta_cliente.setText(""); // Limpiar mensajes de error
        } else {
            label_mostrar_cliente.setText("Cliente no encontrado.");
            PauseTransition pausa = new PauseTransition(Duration.seconds(2));
            pausa.setOnFinished(e -> {
                try {
                    ver_clientes();
                    msg_alerta_cliente.setText("");
                } catch (SQLException e1) {
                    msg_alerta_cliente.setText(e1.getMessage());
                }
            }); // Verificar que el método existe
            pausa.play();
            msg_alerta_cliente.setText("⚠️ No se encontró un cliente con ese ID.");
        }
    } catch (Exception e) {
        msg_alerta_cliente.setText("⚠️ Error al consultar cliente: " + e.getMessage());
    }
    }
@FXML
void ver_producto_all(ActionEvent event) {
    try {
        DAOProducto daoProducto = new DAOProducto();
        List<Producto> listaProductos = daoProducto.readAll();

        if (listaProductos.isEmpty()) {
            msg_alerta_producto.setText("⚠️ No hay productos registrados.");
            return;
        }

        // Ordenar por ID numérico
        listaProductos.sort(Comparator.comparing(p -> Integer.parseInt(p.getId())));

        StringBuilder resultado = new StringBuilder();
        for (Producto producto : listaProductos) {
            if (producto instanceof ProductoAlimentos) {
                ProductoAlimentos alimento = (ProductoAlimentos) producto;
                resultado.append(alimento.toString())
                        .append(" | Calorías: ")
                        .append(alimento.getAporteCalorico()).append(" kcal\n");
            } else if (producto instanceof ProductoElectronico) {
                ProductoElectronico electronico = (ProductoElectronico) producto;
                resultado.append(electronico.toString())
                        .append(" | Voltaje: ")
                        .append(electronico.getVoltajeEntrada()).append("V\n");
            } else {
                resultado.append(producto.toString()).append("\n"); // En caso de ser otro tipo
            }
        }

        // Mostrar productos en el TextArea
        Mostrar_productos.setText(resultado.toString());
        msg_alerta_producto.setText(""); // Limpiar mensajes de error
    } catch (NumberFormatException e) {
        msg_alerta_producto.setText("⚠️ Error: ID no válido en la lista.");
    } catch (Exception e) {
        msg_alerta_producto.setText("⚠️ Error al obtener productos: " + e.getMessage());
    }
}

    @FXML
    void crear_producto(ActionEvent event) {
 // Obtener datos de los campos de texto
    String idProducto = txt_idProducto.getText().trim();
    String nombreProducto = txt_nombreProducto.getText().trim();
    String precioTexto = txt_precioProducto.getText().trim();
    String categoria = categoriaProducto.getText().trim();
    String extraInfo = voltaje_calorias.getText().trim();
    // Validar que los campos no estén vacíos
    if (idProducto.isEmpty() || nombreProducto.isEmpty() || precioTexto.isEmpty() || categoria.isEmpty()) {
        msg_alerta_producto.setText("⚠️ Error: Todos los campos son obligatorios.");
        return;
    }
    try {
        double precio = Double.parseDouble(precioTexto);
        ProductoFactory factory = null;
        Producto nuevoProducto = null;
        // Usar la factoría según la categoría seleccionada
        if (categoria.equalsIgnoreCase("Alimento")) {
            if (extraInfo.isEmpty()) {
                msg_alerta_producto.setText("⚠️ Error: Debe ingresar el aporte calórico.");
                return;
            }
            factory = new FactoryAlimento();
            nuevoProducto = factory.crearProducto();
            ((ProductoAlimentos) nuevoProducto).setId(idProducto);
            ((ProductoAlimentos) nuevoProducto).setDescripcion(nombreProducto);
            ((ProductoAlimentos) nuevoProducto).setPrecio(precio);
            ((ProductoAlimentos) nuevoProducto).setAporteCalorico(Float.parseFloat(extraInfo));
            
        } else if (categoria.equalsIgnoreCase("Electrónico")) {
            if (extraInfo.isEmpty()) {
                msg_alerta_producto.setText("⚠️ Error: Debe ingresar el voltaje de entrada.");
                return;
            }
            factory = new FactoryElectronico();
            nuevoProducto = factory.crearProducto();
            ((ProductoElectronico) nuevoProducto).setId(idProducto);
            ((ProductoElectronico) nuevoProducto).setDescripcion(nombreProducto);
            ((ProductoElectronico) nuevoProducto).setPrecio(precio);
            ((ProductoElectronico) nuevoProducto).setVoltajeEntrada(Float.parseFloat(extraInfo));
            
        } else {
            msg_alerta_producto.setText("⚠️ Error: Categoría no válida.");
            return;
        }
        // Guardar en la base de datos
        DAOProducto daoProducto = new DAOProducto();
        String resultado = daoProducto.create(nuevoProducto);
        // Mostrar resultado en la interfaz
        msg_alerta_producto.setText(resultado);
        // Limpiar campos tras agregar producto
        txt_idProducto.clear();
        txt_nombreProducto.clear();
        txt_precioProducto.clear();
        voltaje_calorias.clear();
    } catch (NumberFormatException e) {
        msg_alerta_producto.setText("⚠️ Error: El precio y valores extra deben ser números.");
    } catch (Exception e) {
        msg_alerta_producto.setText("⚠️ Error al agregar producto: " + e.getMessage());
    }
    }
    @FXML
    void eliminar_producto(ActionEvent event) {
    // Obtener el ID del producto desde el campo de texto
    String idProducto = txt_idProducto.getText().trim();
    // Validar que el ID no esté vacío
    if (idProducto.isEmpty()) {
        msg_alerta_producto.setText("⚠️ Error: Debe ingresar un ID para eliminar.");
        return;
    }
    try {
        DAOProducto daoProducto = new DAOProducto();
        String resultado = daoProducto.delete(idProducto);
        // Si la eliminación fue exitosa, limpiar los campos
        if (resultado.equals("Producto eliminado exitosamente")) {
            txt_idProducto.clear();
            txt_nombreProducto.clear();
            txt_precioProducto.clear();
            voltaje_calorias.clear();
            Mostrar_productos.clear();
        }    
        // Mostrar mensaje de resultado
        msg_alerta_producto.setText(resultado);
    } catch (Exception e) {
        msg_alerta_producto.setText("⚠️ Error al eliminar producto: " + e.getMessage());
    }
    }
    @FXML
    void modificar_producto(ActionEvent event) {
    // Obtener el ID del producto desde el campo de texto
    String idProducto = txt_idProducto.getText().trim();
    String descripcion = txt_nombreProducto.getText().trim();
    String precioTexto = txt_precioProducto.getText().trim();
    String extraTexto = voltaje_calorias.getText().trim();
    String categoria = categoriaProducto.getText().trim(); // "Alimento" o "Electrónico"
    // Validar que los campos obligatorios no estén vacíos
    if (idProducto.isEmpty() || descripcion.isEmpty() || precioTexto.isEmpty() || extraTexto.isEmpty()) {
        msg_alerta_producto.setText("⚠️ Error: Todos los campos deben estar completos.");
        return;
    }
    try {
        double precio = Double.parseDouble(precioTexto);
        float extra = Float.parseFloat(extraTexto);
        DAOProducto daoProducto = new DAOProducto();
        Producto producto;
        // Determinar el tipo de producto según la categoría seleccionada
        if ("Alimento".equals(categoria)) {
            producto = new ProductoAlimentos(idProducto, descripcion, precio, extra);
        } else if ("Electrónico".equals(categoria)) {
            producto = new ProductoElectronico(idProducto, descripcion, precio, extra);
        } else {
            msg_alerta_producto.setText("⚠️ Error: Categoría no válida.");
            return;
        }
        // Llamar al método de actualización en el DAO
        String resultado = daoProducto.update(idProducto, producto);
        // Mostrar mensaje de confirmación o error
        msg_alerta_producto.setText(resultado);
    } catch (NumberFormatException e) {
        msg_alerta_producto.setText("⚠️ Error: Precio y valor extra deben ser números válidos.");
    } catch (Exception e) {
        msg_alerta_producto.setText("⚠️ Error al actualizar producto: " + e.getMessage());
    }
    }
    
  
    @FXML
    void encontrar_por_id(ActionEvent event) {
        try {
            String id = txt_idProducto.getText().trim();
    
            // Validar que el ID no esté vacío
            if (id.isEmpty()) {
                msg_alerta_producto.setText("⚠️ Ingrese un ID de producto.");
                return;
            }
    
            DAOProducto daoProducto = new DAOProducto();
            Producto producto = daoProducto.read(id);
    
            // Validar si el producto fue encontrado
            if (producto == null) {
                msg_alerta_producto.setText("⚠️ Producto no encontrado.");
                Mostrar_productos.setText(""); // Limpiar el TextArea
                return;
            }
    
            // Construir la información a mostrar
            StringBuilder resultado = new StringBuilder();
            resultado.append("ID: ").append(producto.getId()).append("\n")
                     .append("Descripción: ").append(producto.getDescripcion()).append("\n")
                     .append("Precio: ").append(producto.getPrecio()).append("$\n");
    
            // Determinar tipo de producto y agregar dato extra
            if (producto instanceof ProductoAlimentos) {
                resultado.append("Categoría: Alimento\n");
                resultado.append("Aporte calórico: ").append(((ProductoAlimentos) producto).getAporteCalorico()).append(" kcal\n");
            } else if (producto instanceof ProductoElectronico) {
                resultado.append("Categoría: Electrónico\n");
                resultado.append("Voltaje de entrada: ").append(((ProductoElectronico) producto).getVoltajeEntrada()).append(" V\n");
            } else {
                resultado.append("Categoría: Desconocida\n");
            }
    
            // Mostrar el resultado en el TextArea
            Mostrar_productos.setText(resultado.toString());
            msg_alerta_producto.setText(""); // Limpiar mensajes de error
    
        } catch (Exception e) {
            msg_alerta_producto.setText("⚠️ Error al buscar producto: " + e.getMessage());
        }
    }
    private String generarNuevoID() throws SQLException {
    DAOProducto daoProducto = new DAOProducto();
    List<Producto> productos = daoProducto.readAll();
    // Crear una lista con todos los IDs numéricos
    List<Integer> idsNumericos = new ArrayList<>();
    for (Producto p : productos) {
        try {
            idsNumericos.add(Integer.parseInt(p.getId()));
        } catch (NumberFormatException ignored) {
            // Ignorar IDs no numéricos
        }
    }
    // Ordenar los IDs numéricos
    Collections.sort(idsNumericos);
    // Buscar el primer hueco en la secuencia
    int nuevoID = 1;  // Empezamos desde 1
    for (int id : idsNumericos) {
        if (id == nuevoID) {
            nuevoID++;  // Si el número ya está en la lista, pasamos al siguiente
        } else {
            break;  // Si hay un hueco, lo usamos
        }
    }
    return String.valueOf(nuevoID);  // Retornar el primer ID disponible
    }
    
    @FXML
    void clonar_producto(ActionEvent event) {
        try {
        DAOProducto daoProducto = new DAOProducto();
        List<Producto> productos = daoProducto.readAll();
        if (productos.isEmpty()) {
            msg_alerta_producto.setText("⚠️ No hay productos para clonar.");
            return;
        }
        // Seleccionar un producto aleatorio
        Random rand = new Random();
        Producto productoOriginal = productos.get(rand.nextInt(productos.size()));
        // Clonar el producto si es una instancia de PrototipoProducto
        if (!(productoOriginal instanceof PrototipoProducto)) {
            msg_alerta_producto.setText("⚠️ Error: El producto no es clonable.");
            return;
        }
        PrototipoProducto clon = ((PrototipoProducto) productoOriginal).clonar();
        // Asignar un nuevo ID disponible
        String nuevoId = generarNuevoID();
        if (clon instanceof ProductoAlimentos) {
            clon = new ProductoAlimentos(nuevoId, productoOriginal.getDescripcion(), productoOriginal.getPrecio(),
                    ((ProductoAlimentos) productoOriginal).getAporteCalorico());
        } else if (clon instanceof ProductoElectronico) {
            clon = new ProductoElectronico(nuevoId, productoOriginal.getDescripcion(), productoOriginal.getPrecio(),
                    ((ProductoElectronico) productoOriginal).getVoltajeEntrada());
        }
        // Guardar el clon en la base de datos
        String resultado = daoProducto.create((Producto) clon);
        // Mostrar el producto original en `Mostrar_productos`
        Mostrar_productos.setText("Original: " + productoOriginal.toString());
        // Mostrar el clon en `mostrar_clon`
        mostrar_clon.setText("Clonado: " + clon.toString());
        // Mensaje de éxito
        msg_alerta_producto.setText(resultado);
    } catch (Exception e) {
        msg_alerta_producto.setText("⚠️ Error al clonar: " + e.getMessage());
    }
    }

    @FXML
    void clonar_producto_ip(ActionEvent event) {
            try {
                String idOriginal = txt_idProducto.getText().trim(); // ID ingresado
        
                if (idOriginal.isEmpty()) {
                    msg_alerta_producto.setText("⚠️ Error: Debe ingresar un ID.");
                    return;
                }
        
                DAOProducto daoProducto = new DAOProducto();
                Producto productoOriginal = daoProducto.read(idOriginal); // Buscar el producto
        
                if (productoOriginal == null) {
                    msg_alerta_producto.setText("⚠️ Error: No se encontró un producto con ese ID.");
                    return;
                }
        
                if (!(productoOriginal instanceof PrototipoProducto)) {
                    msg_alerta_producto.setText("⚠️ Error: El producto no es clonable.");
                    return;
                }
        
                // Clonar el producto
                PrototipoProducto clon = ((PrototipoProducto) productoOriginal).clonar();
        
                // Asignar un nuevo ID disponible
                String nuevoId = generarNuevoID();
                if (clon instanceof ProductoAlimentos) {
                    clon = new ProductoAlimentos(nuevoId, productoOriginal.getDescripcion(), productoOriginal.getPrecio(),
                            ((ProductoAlimentos) productoOriginal).getAporteCalorico());
                } else if (clon instanceof ProductoElectronico) {
                    clon = new ProductoElectronico(nuevoId, productoOriginal.getDescripcion(), productoOriginal.getPrecio(),
                            ((ProductoElectronico) productoOriginal).getVoltajeEntrada());
                }
        
                // Guardar el clon en la base de datos
                String resultado = daoProducto.create((Producto) clon);
        
                // Mostrar el producto original en `Mostrar_productos`
                Mostrar_productos.setText("Original: " + productoOriginal.toString());
        
                // Mostrar el clon en `mostrar_clon`
                mostrar_clon.setText("Clonado: " + clon.toString());
        
                // Mensaje de éxito
                msg_alerta_producto.setText(resultado);
        
            } catch (Exception e) {
                msg_alerta_producto.setText("⚠️ Error al clonar: " + e.getMessage());
            }
    }

    @FXML
    void encontrar_por_precio(ActionEvent event) {
        String minPrecioTexto = Precio_minimo.getText().trim();
        String maxPrecioTexto = Precio_maximo.getText().trim();
    
        // Validar que los campos no estén vacíos
        if (minPrecioTexto.isEmpty() || maxPrecioTexto.isEmpty()) {
            msg_alerta_producto.setText("⚠️ Error: Debes ingresar ambos valores de precio.");
            return;
        }
    
        try {
            double precioMin = Double.parseDouble(minPrecioTexto);
            double precioMax = Double.parseDouble(maxPrecioTexto);
    
            // Validar que el rango sea correcto
            if (precioMin > precioMax) {
                msg_alerta_producto.setText("⚠️ Error: El precio mínimo no puede ser mayor que el máximo.");
                return;
            }
    
            DAOProducto daoProducto = new DAOProducto();
            List<Producto> listaProductos = daoProducto.leerPorRangoDePrecios(precioMin, precioMax);
    
            if (listaProductos.isEmpty()) {
                msg_alerta_producto.setText("⚠️ No hay productos en ese rango de precios.");
                Mostrar_productos.clear();
                return;
            }
    
            // Construir la lista de productos
            StringBuilder resultado = new StringBuilder();
            for (Producto producto : listaProductos) {
                resultado.append(producto.toString()).append("\n");
            }
    
            // Mostrar resultados en la interfaz
            Mostrar_productos.setText(resultado.toString());
            msg_alerta_producto.setText(""); // Limpiar mensajes de error
    
        } catch (NumberFormatException e) {
            msg_alerta_producto.setText("⚠️ Error: Ingrese valores numéricos válidos.");
        } catch (Exception e) {
            msg_alerta_producto.setText("⚠️ Error al filtrar productos: " + e.getMessage());
        }
    }
    @FXML
void cargar_categorias() {
    // Limpiar opciones previas
    categoriaProducto.getItems().clear();
    // Crear elementos de menú
    MenuItem alimento = new MenuItem("Alimento");
    MenuItem electronico = new MenuItem("Electrónico");
    // Asignar evento de selección
    alimento.setOnAction(this::seleccionar_categoria_producto);
    electronico.setOnAction(this::seleccionar_categoria_producto);
    // Agregar opciones al MenuButton
    categoriaProducto.getItems().addAll(alimento, electronico);
}
    @FXML
    void seleccionar_categoria_producto(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        categoriaProducto.setText(item.getText()); // Establecer la categoría seleccionada en el botón
    
    }
    @FXML
    void salir(ActionEvent event) {
        Platform.exit(); // Cierra la aplicación JavaFX
        System.exit(0);  // Asegura que todos los procesos se detengan
    }
}

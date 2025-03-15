package co.edu.poli.ejemplo.controlador;

import java.sql.SQLException;

import co.edu.poli.ejemplo.modelo.Cliente;
import co.edu.poli.ejemplo.modelo.FactoryAlimento;
import co.edu.poli.ejemplo.modelo.FactoryElectronico;
import co.edu.poli.ejemplo.modelo.Producto;
import co.edu.poli.ejemplo.modelo.ProductoAlimentos;
import co.edu.poli.ejemplo.modelo.ProductoElectronico;
import co.edu.poli.ejemplo.modelo.ProductoFactory;
import co.edu.poli.ejemplo.servicios.DAOCliente;
import co.edu.poli.ejemplo.servicios.DAOProducto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    private final Alert alerta;
    DAOProducto daoProducto;
    private DAOCliente daoCliente;

    @FXML
    private Button botonConsultarPedido, botonConsultaClientes, botonProductoConsulta,
            botonOkClientes, botonOkPedidos, botonOkProductos, botonClienteConsulta,
            botonVerClientes, botonVerPedidos;

    @FXML
    private MenuItem itemActualizarCliente, itemActualizarPedido, itemActualizarProducto,
            itemClonarProducto, itemCrearCliente, itemCrearPedido, itemCrearProducto,
            itemConsultarCliente, itemConsultarProducto,
            itemVerProductos, itemVerClientes,
            itemEliminarCliente, itemEliminarPedido, itemEliminarProducto;

    @FXML
    private SplitMenuButton seleccionClientesAdmin, seleccionClientesConsulta,
            seleccionProductosConsulta, seleccionProductosAdmin, seleccionTipoProducto;
    @FXML
    private TextField textClienteID, textClienteNombre, textClientePedido,
            textClienteConsulta, textFechaPedido, textNumeroPedido, textProductoConsulta,
            textProductoDescripcion, textProductoPrecio, textProductoID, textProductoExtra;

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Cliente, String> colID, colNombre;
    @FXML
    private TableColumn<Producto, String> colIDP, colDes, colTipo, colExtra;
    @FXML
    private TableColumn<Producto, Float> colPrecio;

    public MainController() {
        this.alerta = new Alert(AlertType.NONE);
    }

    @FXML
    void accionCrearCliente(ActionEvent event) {
        textClienteID.clear();
        textClienteNombre.clear();
        botonConsultaClientes.setVisible(false);
        textClienteID.setPromptText("Ingrese el ID del cliente a crear");
        textClienteNombre.setPromptText("Ingrese el nombre del cliente a crear");
        textClienteID.setEditable(true);
        textClienteNombre.setEditable(true);
        seleccionClientesAdmin.setText("Crear");
    }

    @FXML
    void accionCrearProducto(ActionEvent event) {
        textProductoID.clear();
        textProductoDescripcion.clear();
        textProductoPrecio.clear();
        textProductoExtra.clear();
        seleccionProductosAdmin.setText("Crear");
        textProductoID.setPromptText("Ingrese el ID del producto a crear");
        textProductoDescripcion.setPromptText("Ingrese la descripción del producto a crear");
        textProductoPrecio.setPromptText("Ingrese el precio del producto a crear");
        textProductoExtra.setPromptText("Seleccione el tipo de producto a crear");
        seleccionTipoProducto.setVisible(true);
        textProductoID.setEditable(true);
        textProductoDescripcion.setEditable(true);
        textProductoPrecio.setEditable(true);
        textProductoExtra.setEditable(true);
    }

    @FXML
    void accionActualizarProducto(ActionEvent event) {
        textProductoID.clear();
        textProductoDescripcion.clear();
        textProductoPrecio.clear();
        textProductoExtra.clear();
        seleccionProductosAdmin.setText("Actualizar");
        textProductoID.setPromptText("Ingrese el ID del producto a Actualizar");
        textProductoDescripcion.setPromptText("Ingrese la descripción del producto a Actualizar");
        textProductoPrecio.setPromptText("Ingrese el precio del producto a Actualizar");
        seleccionTipoProducto.setVisible(false);
        textProductoID.setEditable(true);
        textProductoDescripcion.setEditable(true);
        textProductoPrecio.setEditable(true);
        textProductoExtra.setEditable(true);
    }

    @FXML
    void accionEliminarProducto(ActionEvent event) {
        textProductoID.clear();
        textProductoDescripcion.clear();
        textProductoPrecio.clear();
        textProductoExtra.clear();
        seleccionProductosAdmin.setText("Eliminar");
        textProductoID.setPromptText("Ingrese el ID del producto a eliminar");
        seleccionTipoProducto.setVisible(false);
        textProductoID.setEditable(true);
        textProductoDescripcion.setEditable(false);
        textProductoPrecio.setEditable(false);
        textProductoExtra.setEditable(false);
    }

    @FXML
    void accionClonarProducto(ActionEvent event) {
        textProductoID.clear();
        textProductoDescripcion.clear();
        textProductoPrecio.clear();
        textProductoExtra.clear();
        seleccionProductosAdmin.setText("Clonar");
        textProductoID.setPromptText("Ingrese el ID del producto a Clonar");
        seleccionTipoProducto.setVisible(false);
        textProductoID.setEditable(true);
        textProductoDescripcion.setEditable(false);
        textProductoPrecio.setEditable(false);
        textProductoExtra.setEditable(false);
    }

    @FXML
    void accionActualizarCliente(ActionEvent event) {
        textClienteID.clear();
        textClienteNombre.clear();
        botonConsultaClientes.setVisible(true);
        textClienteID.setPromptText("Ingrese el ID del cliente a actualizar");
        textClienteID.setEditable(true);
        textClienteNombre.setEditable(true);
        seleccionClientesAdmin.setText("Actualizar");
    }

    @FXML
    void accionEliminarCliente(ActionEvent event) {
        textClienteID.clear();
        textClienteNombre.clear();
        botonConsultaClientes.setVisible(true);
        textClienteID.setEditable(true);
        textClienteNombre.setEditable(false);
        textClienteID.setPromptText("Ingrese el ID del cliente a eliminar");
        textClienteNombre.setPromptText("Nombre del cliente");
        seleccionClientesAdmin.setText("Eliminar");
    }

    @FXML
    void accionConsultaCliente(ActionEvent event) {
        tablaClientes.getItems().clear();
        textClienteConsulta.clear();
        seleccionClientesConsulta.setText("Consulta por ID");
        textClienteConsulta.setVisible(true);
        botonClienteConsulta.setVisible(true);

    }

    @FXML
    void accionConsultaProducto(ActionEvent event) {
        tablaProductos.getItems().clear();
        textProductoConsulta.clear();
        seleccionProductosConsulta.setText("Consulta por ID");
        textProductoConsulta.setVisible(true);
        botonProductoConsulta.setVisible(true);

    }

    @FXML
    void accionVerClientes(ActionEvent event) {
        seleccionClientesConsulta.setText("Ver");
        textClienteConsulta.setVisible(false);
        botonClienteConsulta.setVisible(false);
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        try {
            daoCliente = new DAOCliente();
            ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(daoCliente.readAll());
            tablaClientes.setItems(listaClientes);
        } catch (SQLException e) {
            mostrarError(e.getMessage());
        }

    }

    @FXML
    void accionVerProductos(ActionEvent event) {
        seleccionProductosConsulta.setText("Ver");
        textProductoConsulta.setVisible(false);
        botonProductoConsulta.setVisible(false);

        // Configurar columnas básicas
        colIDP.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        try {
            daoProducto = new DAOProducto();
            ObservableList<Producto> listaProductos = FXCollections.observableArrayList(daoProducto.readAll());

            // Configurar columna de tipo
            colTipo.setCellValueFactory(cellData
                    -> new SimpleStringProperty(cellData.getValue() instanceof ProductoAlimentos ? "Alimento" : "Electrónico")
            );

            // Configurar columna extra
            colExtra.setCellValueFactory(cellData -> {
                Producto producto = cellData.getValue();
                String extraInfo = "";
                if (producto instanceof ProductoAlimentos) {
                    extraInfo = ((ProductoAlimentos) producto).getAporteCalorico() + " kcal";
                } else if (producto instanceof ProductoElectronico) {
                    extraInfo = ((ProductoElectronico) producto).getVoltajeEntrada() + "V";
                }
                return new SimpleStringProperty(extraInfo);
            });

            // Asignar la lista a la tabla
            tablaProductos.setItems(listaProductos);
        } catch (SQLException e) {
            mostrarError("Error al obtener productos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String mensaje) {
        alerta.alertTypeProperty().set(AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarError(String mensaje) {
        alerta.alertTypeProperty().set(AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    void accionCompletarClientes(ActionEvent event) {

        String opcionSeleccionada = seleccionClientesAdmin.getText(); // Obtener el texto actual del SplitMenuButton
        String mensaje;
        Cliente nuevoCliente;
        try {
            daoCliente = new DAOCliente();
            switch (opcionSeleccionada) {
                case "Crear":
                    nuevoCliente = new Cliente();
                    nuevoCliente.setId(textClienteID.getText());
                    nuevoCliente.setNombre(textClienteNombre.getText());
                    if (nuevoCliente.getId().isEmpty() || nuevoCliente.getNombre().isEmpty()) {
                        mostrarAlerta("Por favor, complete todos los campos");
                        return;
                    }
                    mensaje = daoCliente.create(nuevoCliente);
                    if (mensaje.equals("Cliente creado exitosamente")) {
                        mostrarAlerta(mensaje + " " + nuevoCliente.toString());
                    } else {
                        mostrarError(mensaje);
                    }

                    break;

                case "Eliminar":
                    mensaje = daoCliente.delete(textClienteID.getText());
                    mostrarAlerta(mensaje);

                    break;

                case "Actualizar":
                    nuevoCliente = new Cliente();
                    nuevoCliente.setId(textClienteID.getText());
                    nuevoCliente.setNombre(textClienteNombre.getText());
                    mensaje = daoCliente.update(textClienteID.getText(), nuevoCliente);
                    mostrarAlerta(mensaje);
                    break;

                default:
                    mostrarAlerta("No has seleccionado ninguna opción");
                    break;
            }
        } catch (Exception e) {
            mostrarError(e.getMessage());

        }

    }

    @FXML
    void accionTipoProductoAlimento(ActionEvent event) {
        textProductoID.clear();
        textProductoDescripcion.clear();
        textProductoPrecio.clear();
        textProductoExtra.clear();
        textProductoExtra.setPromptText("Ingrese el aporte calórico");
        seleccionTipoProducto.setText("Alimento");
    }

    @FXML
    void accionTipoProductoElectronico(ActionEvent event) {
        textProductoID.clear();
        textProductoDescripcion.clear();
        textProductoPrecio.clear();
        textProductoExtra.clear();
        textProductoExtra.setPromptText("Ingrese el voltaje de entrada");
        seleccionTipoProducto.setText("Electrónico");
    }

    @FXML
    void accionCompletarProductos(ActionEvent event) {
        try {
            String opcionSeleccionada = seleccionProductosAdmin.getText(); // Obtener la opción del SplitMenuButton
            String tipoProductoSeleccionado = seleccionTipoProducto.getText(); // Obtener el tipo de producto seleccionado
            String mensaje;
            daoProducto = new DAOProducto();
            Producto producto;
            ProductoFactory factory;
            switch (opcionSeleccionada) {
                case "Crear":
                    if (tipoProductoSeleccionado.equals("Alimento")) {
                        factory = new FactoryAlimento();
                        producto = factory.crearProducto();
                        ((ProductoAlimentos) producto).setId(textProductoID.getText());
                        ((ProductoAlimentos) producto).setDescripcion(textProductoDescripcion.getText());
                        ((ProductoAlimentos) producto).setPrecio(Float.parseFloat(textProductoPrecio.getText()));
                        ((ProductoAlimentos) producto).setAporteCalorico(Float.parseFloat(textProductoExtra.getText()));
                        mensaje = daoProducto.create(producto);
                        mostrarAlerta(mensaje);
                        break;
                    } else {
                        factory = new FactoryElectronico();
                        producto = factory.crearProducto();
                        ((ProductoElectronico) producto).setId(textProductoID.getText());
                        ((ProductoElectronico) producto).setDescripcion(textProductoDescripcion.getText());
                        ((ProductoElectronico) producto).setPrecio(Float.parseFloat(textProductoPrecio.getText()));
                        ((ProductoElectronico) producto).setVoltajeEntrada(Float.parseFloat(textProductoExtra.getText()));
                    }
                    mensaje = daoProducto.create(producto);
                    mostrarAlerta(mensaje);
                    break;

                case "Eliminar":
                    mensaje = daoProducto.delete(textProductoID.getText());
                    mostrarAlerta(mensaje);
                    break;

                case "Actualizar":
                    try {
                        // Obtener el producto existente desde la BD
                        Producto productoExistente = daoProducto.read(textProductoID.getText());

                        if (productoExistente == null) {
                            mostrarAlerta("No se encontró un producto con ese ID para actualizar.");
                            return;
                        }

                        // Identificar el tipo de producto
                        if (productoExistente instanceof ProductoAlimentos) {
                            ProductoAlimentos productoAlimento = (ProductoAlimentos) productoExistente;
                            productoAlimento.setDescripcion(textProductoDescripcion.getText());
                            productoAlimento.setPrecio(Double.parseDouble(textProductoPrecio.getText()));
                            productoAlimento.setAporteCalorico(Float.parseFloat(textProductoExtra.getText()));
                        } else if (productoExistente instanceof ProductoElectronico) {
                            ProductoElectronico productoElectronico = (ProductoElectronico) productoExistente;
                            productoElectronico.setDescripcion(textProductoDescripcion.getText());
                            productoElectronico.setPrecio(Double.parseDouble(textProductoPrecio.getText()));
                            productoElectronico.setVoltajeEntrada(Float.parseFloat(textProductoExtra.getText()));
                        }

                        // Intentar actualizar en la BD
                        mensaje = daoProducto.update(textProductoID.getText(), productoExistente);
                        mostrarAlerta(mensaje);
                    } catch (Exception e) {
                        mostrarError(e.getMessage());
                    }
                    break;

                case "Clonar":
                    Producto productoExistente = daoProducto.read(textProductoID.getText());
                    if (productoExistente == null) {
                        mostrarAlerta("No se encontró un producto con ese ID para clonar.");
                        return;
                    }
                    Producto productoClonado;
                    // Verificar el tipo y clonar adecuadamente
                    if (productoExistente instanceof ProductoAlimentos) {
                        productoClonado = (Producto) ((ProductoAlimentos) productoExistente).clonar();
                    } else if (productoExistente instanceof ProductoElectronico) {
                        productoClonado = (Producto) ((ProductoElectronico) productoExistente).clonar();
                    } else {
                        mostrarError("Error: Tipo de producto desconocido.");
                        return;
                    }
                    String idBase = productoExistente.getId();
                    int nuevoIdNumero = 1; // Empezar desde 1

                    // Extraer la parte numérica si el ID termina en un número
                    if (idBase.matches(".*\\d+$")) {
                        nuevoIdNumero = Integer.parseInt(idBase.replaceAll("\\D+", "")) + 1; // Extrae números y suma 1
                        idBase = idBase.replaceAll("\\d+$", ""); // Elimina los números finales
                    }

                    // Generar un nuevo ID hasta encontrar uno disponible
                    String nuevoId;
                    do {
                        nuevoId = idBase + nuevoIdNumero;
                        nuevoIdNumero++;
                    } while (daoProducto.read(nuevoId) != null); // Verifica si el ID ya existe en la BD

                    productoClonado.setId(nuevoId);

                    // Intentar insertar en la base de datos
                    daoProducto.create(productoClonado);
                    mostrarAlerta("Producto clonado exitosamente con ID: " + productoClonado.getId());
                    break;

                default:
                    mostrarAlerta("No has seleccionado ninguna opción");
                    break;
            }
        } catch (SQLException e) {
            mostrarError(e.getMessage());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error en formato numérico. Verifica los datos ingresados.");
        }
    }

    @FXML
    void accionConsultarClientes(ActionEvent event) {
        Cliente nuevoCliente;

        try {
            daoCliente = new DAOCliente();
            nuevoCliente = daoCliente.read(textClienteID.getText());
            if (nuevoCliente == null) {
                mostrarAlerta("Cliente no encontrado");
                return;
            }
            textClienteNombre.setText(nuevoCliente.getNombre());
        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    void accionBotonConsultaCliente(ActionEvent event) {

        try {
            Cliente nuevoCliente;
            daoCliente = new DAOCliente();
            colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            nuevoCliente = daoCliente.read(textClienteConsulta.getText());
            if (nuevoCliente == null) {
                mostrarAlerta("Cliente no encontrado");
                return;
            }
            // Crear la lista con el cliente encontrado
            ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(nuevoCliente);

            // Asignar la lista a la tabla
            tablaClientes.setItems(listaClientes);

        } catch (Exception e) {
            mostrarError(e.getMessage());
        }

    }

    @FXML
    void accionBotonConsultaProducto(ActionEvent event) {
        try {
            daoProducto = new DAOProducto();

            // Configurar columnas básicas
            colIDP.setCellValueFactory(new PropertyValueFactory<>("id"));
            colDes.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

            // Buscar producto
            Producto nuevoProducto = daoProducto.read(textProductoConsulta.getText());

            if (nuevoProducto == null) {
                mostrarAlerta("Producto no encontrado");
                colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue() instanceof ProductoAlimentos ? "Alimento" : "Electrónico"));
            }

            // Solución usando Callback para garantizar el tipo correcto
            colTipo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> cellData) {
                    return new SimpleStringProperty(cellData.getValue() instanceof ProductoAlimentos ? "Alimento" : "Electrónico");
                }
            });

            colExtra.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> cellData) {
                    Producto producto = cellData.getValue();
                    String extraInfo = "";
                    if (producto instanceof ProductoAlimentos) {
                        extraInfo = ((ProductoAlimentos) producto).getAporteCalorico() + " kcal";
                    } else if (producto instanceof ProductoElectronico) {
                        extraInfo = ((ProductoElectronico) producto).getVoltajeEntrada() + "V";
                    }
                    return new SimpleStringProperty(extraInfo);
                }
            });

            // Crear la lista con el producto encontrado
            ObservableList<Producto> listaProductos = FXCollections.observableArrayList(nuevoProducto);

            // Asignar la lista a la tabla
            tablaProductos.setItems(listaProductos);

        } catch (Exception e) {
            mostrarError("Error al consultar producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

package co.edu.poli.ejemplo.servicios;

import java.util.List;

import co.edu.poli.ejemplo.modelo.Producto;

public interface DAOConsultasEspecializadas extends CRUD<Producto, String, String>{

    public List<Producto> leerPorRangoDePrecios(double precioMin, double precioMax);

}

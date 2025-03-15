package poli.modelo;

// Tratamiento médico especializado
public class Cirugia implements Tratamiento {
    
    private String tipoCirugia;

    public Cirugia(String tipoCirugia) {
        this.tipoCirugia = tipoCirugia;
    }

    @Override
    public void aplicarTratamiento(Mascota mascota) {
        System.out.println("Realizando cirugía " + tipoCirugia + " a " + mascota.getNombre());
    }
}
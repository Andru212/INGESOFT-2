package poli.modelo;

// Tratamiento básico
public class Vacuna implements Tratamiento {
    
    private String tipoVacuna;

    public Vacuna(String tipoVacuna) {
        this.tipoVacuna = tipoVacuna;
    }

    @Override
    public void aplicarTratamiento(Mascota mascota) {
        System.out.println("Aplicando vacuna " + tipoVacuna + " a " + mascota.getNombre());
    }
}



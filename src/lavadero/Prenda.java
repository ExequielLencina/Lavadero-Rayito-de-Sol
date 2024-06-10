package lavadero;

public class Prenda {
    private int idPrenda;
    private String tipoPrenda;
    private String descripcion;

    public Prenda(int idPrenda, String tipoPrenda, String descripcion) {
        this.idPrenda = idPrenda;
        this.tipoPrenda = tipoPrenda;
        this.descripcion = descripcion;
    }

    public int getIdPrenda() {
        return idPrenda;
    }

    public void setIdPrenda(int idPrenda) {
        this.idPrenda = idPrenda;
    }

    public String getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Prenda{" +
                "idPrenda=" + idPrenda +
                ", tipoPrenda='" + tipoPrenda + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

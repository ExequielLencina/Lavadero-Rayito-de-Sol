package lavadero;


public class Pedido {
    private int idPedido;
    private String fechaIngreso;
    private String estado;
    private String tipoEntrega;
    private String tipoServicio;
    private String tipoPrenda;
    private String detalle;
    private Cliente cliente;

    // Constructor que acepta todos los parámetros necesarios
    public Pedido(int idPedido, String fechaIngreso, String estado, String tipoEntrega, String tipoServicio, String tipoPrenda, String detalle, Cliente cliente) {
        this.idPedido = idPedido;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.tipoEntrega = tipoEntrega;
        this.tipoServicio = tipoServicio;
        this.tipoPrenda = tipoPrenda;
        this.detalle = detalle;
        this.cliente = cliente;
    }

    // Getters y setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", fechaIngreso=" + fechaIngreso +
                ", estado='" + estado + '\'' +
                ", tipoEntrega='" + tipoEntrega + '\'' +
                ", tipoServicio='" + tipoServicio + '\'' +
                ", tipoPrenda='" + tipoPrenda + '\'' +
                ", detalle='" + detalle + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}

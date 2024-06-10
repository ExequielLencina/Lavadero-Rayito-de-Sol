package lavadero;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private int idPedido;
    private Date fechaIngreso;
    private String estado;
    private String tipoEntrega;
    private Cliente cliente;
    private List<Servicio> servicios;

    public Pedido(int idPedido, Date fechaIngreso, String estado, String tipoEntrega, Cliente cliente) {
        this.idPedido = idPedido;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.tipoEntrega = tipoEntrega;
        this.cliente = cliente;
        this.servicios = new ArrayList<>();
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void agregarServicio(Servicio servicio) {
        this.servicios.add(servicio);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", fechaIngreso=" + fechaIngreso +
                ", estado='" + estado + '\'' +
                ", tipoEntrega='" + tipoEntrega + '\'' +
                ", cliente=" + cliente +
                ", servicios=" + servicios +
                '}';
    }
}

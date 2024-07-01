package lavadero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import conexionBD.ConexionBD;

public class App {

    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Crear Pedido");
            System.out.println("3. Listar Pedidos");
            System.out.println("4. Listar Clientes");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarCliente(scanner);
                    break;
                case 2:
                    crearPedido(scanner);
                    break;
                case 3:
                    listarPedidos();
                    break;
                case 4:
                    listarClientes();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
        scanner.close();
    }

    private static void agregarCliente(Scanner scanner) {
        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la dirección del cliente:");
        String direccion = scanner.nextLine();
        System.out.println("Ingrese el teléfono del cliente:");
        String telefono = scanner.nextLine();

        // Insertar cliente en la base de datos
        try (Connection connection = ConexionBD.getConnection()) {
            String sql = "INSERT INTO Cliente (nombre, direccion, telefono) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, direccion);
            statement.setString(3, telefono);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cliente agregado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }

        // Actualizar la lista local de clientes
        actualizarClientesDesdeBD();

        // Mostrar confirmación en formato de tabla
        listarClientes();
    }

    private static void crearPedido(Scanner scanner) {
        System.out.println("Ingrese el ID del cliente:");
        int idCliente = scanner.nextInt();
        scanner.nextLine();

        // Verificar si el cliente existe en la lista local
        Cliente cliente = buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("Ingrese la fecha de ingreso (Ej. 30/06/2024):");
        String fechaIngresoStr = scanner.nextLine();
        System.out.println("Ingrese el estado del pedido:");
        String estado = scanner.nextLine();
        System.out.println("Ingrese el tipo de entrega:");
        String tipoEntrega = scanner.nextLine();
        System.out.println("Ingrese el tipo de servicio:");
        String tipoServicio = scanner.nextLine();
        System.out.println("Ingrese la prenda a lavar:");
        String prenda = scanner.nextLine();
        System.out.println("Ingrese el detalle de la prenda:");
        String detalle = scanner.nextLine();

        // Insertar pedido en la base de datos
        try (Connection connection = ConexionBD.getConnection()) {
            String sql = "INSERT INTO Pedido (fechaIngreso, estado, tipoEntrega, tipoServicio, tipoPrenda, detalle, idCliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fechaIngresoStr);
            statement.setString(2, estado);
            statement.setString(3, tipoEntrega);
            statement.setString(4, tipoServicio);
            statement.setString(5, prenda);
            statement.setString(6, detalle);
            statement.setInt(7, idCliente);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Pedido creado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear pedido: " + e.getMessage());
        }

        // Actualizar la lista local de pedidos
        actualizarPedidosDesdeBD();

        // Mostrar el pedido creado en formato de tabla
        listarPedidos();
    }

    private static void listarPedidos() {
        // Obtener la lista de pedidos desde la base de datos
        List<Pedido> pedidos = obtenerPedidosDesdeBD();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        System.out.println("Listado de Pedidos:");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
        System.out.println("| ID Pedido  | Fecha de Ingreso     | Estado               | Tipo de Entrega      | Tipo de Servicio     | Tipo de Prenda       | Detalle              | Cliente              | Dirección            | Teléfono             |");
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
        for (Pedido pedido : pedidos) {
            System.out.printf("| %-10d | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |\n",
                    pedido.getIdPedido(),
                    pedido.getFechaIngreso(),
                    pedido.getEstado(),
                    pedido.getTipoEntrega(),
                    pedido.getTipoServicio(),
                    pedido.getTipoPrenda(),
                    pedido.getDetalle(),
                    pedido.getCliente().getNombre(),
                    pedido.getCliente().getDireccion(),
                    pedido.getCliente().getTelefono());
        }
        System.out.println("+------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
    }

    private static void listarClientes() {
        // Obtener la lista de clientes desde la base de datos
        List<Cliente> clientes = obtenerClientesDesdeBD();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("Listado de Clientes:");
        System.out.println("+------+----------------------+------------------------------+---------------+");
        System.out.println("| ID   | Nombre               | Dirección                    | Teléfono      |");
        System.out.println("+------+----------------------+------------------------------+---------------+");
        for (Cliente cliente : clientes) {
            System.out.printf("| %-4d | %-20s | %-28s | %-13s |\n",
                    cliente.getIdCliente(),
                    cliente.getNombre(),
                    cliente.getDireccion(),
                    cliente.getTelefono());
        }
        System.out.println("+------+----------------------+------------------------------+---------------+");
    }

    // Métodos para interactuar con la base de datos

    private static void actualizarClientesDesdeBD() {
        clientes = obtenerClientesDesdeBD();
    }

    private static void actualizarPedidosDesdeBD() {
        pedidos = obtenerPedidosDesdeBD();
    }

    private static List<Cliente> obtenerClientesDesdeBD() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idCliente = resultSet.getInt("idCliente");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                Cliente cliente = new Cliente(idCliente, nombre, direccion, telefono);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener clientes desde la base de datos: " + e.getMessage());
        }
        return clientes;
    }

    private static List<Pedido> obtenerPedidosDesdeBD() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT p.idPedido, p.fechaIngreso, p.estado, p.tipoEntrega, p.tipoServicio, p.tipoPrenda, p.detalle, c.idCliente, c.nombre, c.direccion, c.telefono " +
                     "FROM Pedido p INNER JOIN Cliente c ON p.idCliente = c.idCliente";
        try (Connection connection = ConexionBD.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idPedido = resultSet.getInt("idPedido");
                String fechaIngreso = resultSet.getString("fechaIngreso");
                String estado = resultSet.getString("estado");
                String tipoEntrega = resultSet.getString("tipoEntrega");
                String tipoServicio = resultSet.getString("tipoServicio");
                String tipoPrenda = resultSet.getString("tipoPrenda");
                String detalle = resultSet.getString("detalle");
                int idCliente = resultSet.getInt("idCliente");
                String nombreCliente = resultSet.getString("nombre");
                String direccionCliente = resultSet.getString("direccion");
                String telefonoCliente = resultSet.getString("telefono");
                Cliente cliente = new Cliente(idCliente, nombreCliente, direccionCliente, telefonoCliente);
                Pedido pedido = new Pedido(idPedido, fechaIngreso, estado, tipoEntrega, tipoServicio, tipoPrenda, detalle, cliente);
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pedidos desde la base de datos: " + e.getMessage());
        }
        return pedidos;
    }

    private static Cliente buscarClientePorId(int idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }
}

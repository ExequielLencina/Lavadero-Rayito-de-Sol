package lavadero;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {

    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static List<Servicio> servicios = new ArrayList<>();
    private static List<Prenda> prendas = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Crear Pedido");
            System.out.println("3. Listar Pedidos");
            System.out.println("4. Salir");
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
                    buscarPedidos();
                    break;
                case 4:
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
        Cliente cliente = new Cliente(clientes.size() + 1, nombre, direccion, telefono);
        clientes.add(cliente);
        System.out.println("Cliente agregado: " + cliente);
    }

    private static void crearPedido(Scanner scanner) {
        System.out.println("Ingrese el ID del cliente:");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getIdCliente() == idCliente) {
                cliente = c;
                break;
            }
        }
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }
        System.out.println("Ingrese la fecha de ingreso (YYYY-MM-DD):");
        String fechaIngresoStr = scanner.nextLine();
        Date fechaIngreso = new Date(fechaIngresoStr);
        System.out.println("Ingrese el estado del pedido:");
        String estado = scanner.nextLine();
        System.out.println("Ingrese el tipo de entrega:");
        String tipoEntrega = scanner.nextLine();
        Pedido pedido = new Pedido(pedidos.size() + 1, fechaIngreso, estado, tipoEntrega, cliente);
        pedidos.add(pedido);
        System.out.println("Pedido creado: " + pedido);
    }

    private static void buscarPedidos() {
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }
}

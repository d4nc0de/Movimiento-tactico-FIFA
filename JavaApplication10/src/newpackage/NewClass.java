package newpackage;

import java.util.Scanner;

public class NewClass {

    static long totalVentas = 0;
    static int numVentas = 0;
    static long totalDevoluciones = 0;
    static int numDevoluciones = 0;
    static long totalCompras = 0;
    static int numCompras = 0;
    static long totalCaja = 1000000; // Dinero inicial en caja
    static long[][] clientes = new long[2][101]; // [0] -> ID, [1] -> Compras acumuladas
    static int numClientes = 0; // Número actual de clientes registrados

    // Método para calcular el valor de venta con un margen del 20%
    static void valor_venta(long B[][], int n) {
        for (int i = 0; i < n; i++) {
            B[3][i] = B[2][i] * 6 / 5; // 20% más sobre el precio de compra
        }
    }

    // Método para validar el código de los productos
    static boolean validar_code(int x, int y, long B[][]) {
        if (!mayor_que(x)) {
            return true;
        }
        for (int i = 0; i < y; i++) {
            if (x == B[0][i]) {
                return true;
            }
        }
        return false;
    }

    static boolean mayor_que(int x) {
        return x > 0;
    }

    // Método para registrar un cliente
    static void registrarCliente(long idCliente) {
        clientes[0][numClientes] = idCliente;
        clientes[1][numClientes] = 0; // Inicialmente no tiene compras
        numClientes++;
    }

    // Método para buscar cliente por su ID
    static int buscarCliente(long idCliente) {
        for (int i = 0; i < numClientes; i++) {
            if (clientes[0][i] == idCliente) {
                return i;
            }
        }
        return -1; // Cliente no encontrado
    }

    // Método para aplicar descuento según el tipo de cliente
    static long aplicarDescuento(long valorVenta, int indexCliente) {
        long comprasAcumuladas = clientes[1][indexCliente];
        double descuento = 0;

        if (comprasAcumuladas > 500000) {
            descuento = 0.30; // Cliente Platino
        } else if (comprasAcumuladas > 100000) {
            descuento = 0.20; // Cliente VIP
        } else {
            descuento = 0.10; // Cliente Regular
        }

        long descuentoAplicado = (long) (valorVenta * descuento);
        System.out.println("Descuento aplicado: " + descuentoAplicado);
        return valorVenta - descuentoAplicado;
    }

    // Método para realizar una venta
    static void realizarVenta(long B[][], int[] n) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el código del producto que desea comprar:");
        int codigo = sc.nextInt();
        int indexProducto = -1;

        // Buscar el producto por su código
        for (int i = 0; i < n[0]; i++) {
            if (B[0][i] == codigo) {
                indexProducto = i;
                break; // Salir del bucle una vez que se encuentra el producto
            }
        }

        if (indexProducto == -1) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Ingrese la cantidad que desea comprar:");
        int cantidadCompra = sc.nextInt();

        // Verificar que haya suficiente en bodega
        if (cantidadCompra > B[1][indexProducto]) {
            System.out.println("No hay suficientes unidades en bodega.");
            return;
        }

        // Calcular el valor de la venta
        long valorVenta = B[3][indexProducto] * cantidadCompra;

        // Actualizar la cantidad en bodega
        B[1][indexProducto] -= cantidadCompra;

        // Solicitar ID del cliente y buscarlo
        System.out.println("Ingrese la identificación del cliente:");
        long idCliente = sc.nextLong();
        int indexCliente = buscarCliente(idCliente);

        if (indexCliente == -1) {
            // Registrar nuevo cliente
            System.out.println("Cliente no registrado. Se procederá a registrar.");
            registrarCliente(idCliente);
            indexCliente = buscarCliente(idCliente);
        }

        // Aplicar descuento basado en el nivel del cliente
        long valorConDescuento = aplicarDescuento(valorVenta, indexCliente);

        // Actualizar el total de ventas
        totalVentas += valorConDescuento;
        numVentas++;
        totalCaja += valorConDescuento;

        // Actualizar las compras acumuladas del cliente
        clientes[1][indexCliente] += valorConDescuento;

        // Generar factura
        System.out.println("Factura:");
        System.out.println("Cliente: " + idCliente);
        System.out.println("Producto: " + codigo);
        System.out.println("Cantidad: " + cantidadCompra);
        System.out.println("Precio por unidad: " + B[3][indexProducto]);
        System.out.println("Total antes del descuento: " + valorVenta);
        System.out.println("Total a pagar con descuento: " + valorConDescuento);
        System.out.println("Gracias por su compra!");
    }

// Método para realizar una devolución
static void realizarDevolucion(long B[][], int[] n) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Ingrese el código del producto que desea devolver:");
    int codigo = sc.nextInt();
    int indexProducto = -1;

    // Buscar el producto por su código
    for (int i = 0; i < n[0]; i++) {
        if (B[0][i] == codigo) {
            indexProducto = i;
            break;
        }
    }

    if (indexProducto == -1) {
        System.out.println("Producto no encontrado. No se puede realizar la devolución.");
        return;
    }

    System.out.println("Ingrese la cantidad que desea devolver:");
    int cantidadDevolucion = sc.nextInt();

    // Verificar que la cantidad a devolver no exceda la cantidad en bodega
    if (cantidadDevolucion > B[1][indexProducto]) {
        System.out.println("No puede devolver más de lo que hay en bodega.");
        return;
    }

    // Calcular el valor de la devolución usando el precio de compra
    long valorDevolucion = B[2][indexProducto] * cantidadDevolucion;

    // Actualizar el total de devoluciones
    totalDevoluciones += valorDevolucion;
    numDevoluciones++;
    totalCaja += valorDevolucion; // Aumentar el total en caja, ya que se devuelve el dinero

    // Actualizar la cantidad en bodega
    B[1][indexProducto] += cantidadDevolucion; // Aumentar la cantidad en bodega

    System.out.println("Devolución exitosa. Se ha devuelto: " + valorDevolucion);
}

    // Método para comprar productos a los productores
    static void comprarProductos(long B[][], int[] n) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el código del producto que desea comprar del productor:");
        int codigo = sc.nextInt();
        int indexProducto = -1;

        // Buscar el producto por su código
        for (int i = 0; i < n[0]; i++) {
            if (B[0][i] == codigo) {
                indexProducto = i;
                System.out.println("Producto encontrado en la posición: " + i); // Depuración
                break;
            }
        }

        System.out.println("Ingrese la cantidad que desea comprar:");
        int cantidadCompra = sc.nextInt();

        System.out.println("Ingrese el valor por unidad del producto:");
        long precioCompra = sc.nextLong();

        // Calcular el valor total de la compra
        long valorCompra = precioCompra * cantidadCompra;

        // Verificar si hay suficiente dinero en caja
        if (totalCaja < valorCompra) {
            System.out.println("No hay suficiente dinero en caja para realizar la compra.");
            return;
        }

        // Si el producto ya existe en bodega
        if (indexProducto != -1) {
            B[1][indexProducto] += cantidadCompra; // Actualizar la cantidad en bodega
            System.out.println("Cantidad actualizada en bodega para el producto con código: " + codigo);
        } else {
            if (n[0] < B[0].length) { // Verificar si hay espacio para un nuevo producto
                B[0][n[0]] = codigo; // Código del producto
                B[1][n[0]] = cantidadCompra; // Cantidad comprada
                B[2][n[0]] = precioCompra; // Precio de compra
                valor_venta(B, n[0]); // Calcular el precio de venta con el margen
                n[0]++; // Incrementar el número de productos
                System.out.println("Nuevo producto añadido con código: " + codigo);
            } else {
                System.out.println("No hay espacio para añadir nuevos productos.");
                return;
            }
        }

        totalCompras += valorCompra; // Actualizar el total de compras
        totalCaja -= valorCompra; // Disminuir el total en caja por la compra
        System.out.println("Compra realizada exitosamente. Total gastado: " + valorCompra);
    }

    // Método para mostrar resumen del día
    static void mostrarResumen() {
        System.out.println("--- Resumen del día ---");
        System.out.println("Número de ventas realizadas: " + numVentas);
        System.out.println("Número de devoluciones realizadas: " + numDevoluciones);
        System.out.println("Número de compras a productores: " + numCompras);
        System.out.println("Total en ventas: " + totalVentas);
        System.out.println("Total en devoluciones: " + totalDevoluciones);
        System.out.println("Total en compras: " + totalCompras);
        System.out.println("Dinero en caja: " + totalCaja);
    }

    public static void main(String[] args) {
        long[][] B = new long[4][101]; // [0] -> Código, [1] -> Cantidad, [2] -> Precio de compra, [3] -> Precio de venta
        int[] n = {0}; // Número de productos

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("--- Menú Principal ---");
            System.out.println("1. Realizar una venta");
            System.out.println("2. Realizar una devolución");
            System.out.println("3. Comprar productos a productores");
            System.out.println("4. Mostrar resumen del día");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    realizarVenta(B, n);
                    break;
                case 2:
                    realizarDevolucion(B, n);
                    break;
                case 3:
                    comprarProductos(B, n);
                    break;
                case 4:
                    mostrarResumen();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }
}

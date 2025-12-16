package com.finalproyectjava.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Empleado;
import com.finalproyectjava.model.Pago;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;

public class ReporteService {
    //Atributos
    private final PrestamoService prestamoService;
    private final PagoService pagoService;
    private final ClienteService clienteService;
    private final EmpleadoService empleadoService;

    public ReporteService( PrestamoService ps, PagoService pags, ClienteService cs, EmpleadoService es) {
        this.prestamoService = ps;
        this.pagoService = pags;
        this.clienteService = cs;
        this.empleadoService = es;
    }
    //Préstamos activos (estado = pendiente)
    public List<Prestamo> prestamosActivos() {
    return prestamoService.listaPrestamo()
        .stream()
        .filter(p -> p.getEstado() == EstadoPrestamo.PENDIENTE)
        .toList();
    }
    // Préstamos pagados
    public List<Prestamo> prestamosPagados() {
    return prestamoService.listaPrestamo()
        .stream()
        .filter(p -> p.getEstado() == EstadoPrestamo.PAGADO)
        .toList();
    }
    // Préstamos vencidos (fecha + cuotas)
    public List<Prestamo> prestamosVencidos() {
        LocalDate hoy = LocalDate.now();

        return prestamoService.listaPrestamo()
            .stream()
            .filter(p -> {
                LocalDate fin = p.getFechaInicio().plusMonths(p.getCuotas());
                return hoy.isAfter(fin) && p.getEstado() == EstadoPrestamo.PENDIENTE;
            })
            .toList();
    }
    // Clientes morosos (saldo > 0)
    public List<Cliente> clientesMorosos() {
        return clienteService.listaClientes()
            .stream()
            .filter(c -> {
                double totalPrestamos = prestamoService.listaPrestamo().stream()
                    .filter(p -> p.getClienteId() == c.getId())
                    .mapToDouble(p -> p.getValorCuota() * p.getCuotas())
                    .sum();

                double totalPagado = pagoService.listaPagos().stream()
                    .filter(p -> prestamoService.buscarPrestamoId(p.getPrestamoId())
                            .getClienteId() == c.getId())
                    .mapToDouble(Pago::getMonto)
                    .sum();

                return totalPagado < totalPrestamos;
            })
            .toList();
    }
    // Empleados con mas prestamos otorgados
    public Map<Empleado, Long> empleadosConMasPrestamos() {
        return prestamoService.listaPrestamo()
            .stream()
            .collect(Collectors.groupingBy(
                p -> empleadoService.buscarEmpleadoId(p.getEmpleadoId()),
                Collectors.counting()
            ));
    }
        
    // Total recaudado por pagos
    public double totalRecaudado() {
        return pagoService.listaPagos()
            .stream()
            .mapToDouble(Pago::getMonto)
            .sum();
    }

}
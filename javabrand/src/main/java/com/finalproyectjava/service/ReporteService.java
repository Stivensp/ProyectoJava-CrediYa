package com.finalproyectjava.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.finalproyectjava.exceptions.EmpleadoNoEncontradoException;
import com.finalproyectjava.exceptions.PrestamoNoEncontradoException;
import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Empleado;
import com.finalproyectjava.model.Pago;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;

public class ReporteService {

    private final PrestamoService prestamoService;
    private final PagoService pagoService;
    private final ClienteService clienteService;
    private final EmpleadoService empleadoService;

    public ReporteService(PrestamoService ps, PagoService pags,
                           ClienteService cs, EmpleadoService es) {
        this.prestamoService = ps;
        this.pagoService = pags;
        this.clienteService = cs;
        this.empleadoService = es;
    }

    public List<Prestamo> prestamosActivos() {
        return prestamoService.listaPrestamo()
                .stream()
                .filter(p -> p.getEstado() == EstadoPrestamo.PENDIENTE)
                .toList();
    }

    public List<Prestamo> prestamosPagados() {
        return prestamoService.listaPrestamo()
                .stream()
                .filter(p -> p.getEstado() == EstadoPrestamo.PAGADO)
                .toList();
    }

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

    public List<Cliente> clientesMorosos() {

        List<Prestamo> prestamos = prestamoService.listaPrestamo();
        List<Pago> pagos = pagoService.listaPagos();

        return clienteService.listaClientes()
                .stream()
                .filter(c -> {

                    double totalPrestamos = prestamos.stream()
                            .filter(p -> p.getClienteId() == c.getId())
                            .mapToDouble(p -> p.getValorCuota() * p.getCuotas())
                            .sum();

                    double totalPagado = pagos.stream()
                            .filter(p -> {
                                try {
                                    return prestamoService
                                            .buscarPrestamoId(p.getPrestamoId())
                                            .getClienteId() == c.getId();
                                } catch (PrestamoNoEncontradoException e) {
                                    return false;
                                }
                            })
                            .mapToDouble(Pago::getMonto)
                            .sum();

                    return totalPagado < totalPrestamos;
                })
                .toList();
    }

    public Map<Empleado, Long> empleadosConMasPrestamos() {

        List<Prestamo> prestamos = prestamoService.listaPrestamo();

        return prestamos.stream()
                .map(p -> {
                    try {
                        return empleadoService.buscarEmpleadoId(p.getEmpleadoId());
                    } catch (EmpleadoNoEncontradoException e) {
                        return null;
                    }
                })
                .filter(e -> e != null)
                .collect(Collectors.groupingBy(
                        e -> e,
                        Collectors.counting()
                ));
    }

    public double totalRecaudado() {
        return pagoService.listaPagos()
                .stream()
                .mapToDouble(Pago::getMonto)
                .sum();
    }
}

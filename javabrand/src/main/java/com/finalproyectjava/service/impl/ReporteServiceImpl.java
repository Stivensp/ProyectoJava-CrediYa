package com.finalproyectjava.service.impl;

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
import com.finalproyectjava.service.serviceInterfaces.IClienteService;
import com.finalproyectjava.service.serviceInterfaces.IEmpleadoService;
import com.finalproyectjava.service.serviceInterfaces.IPagoService;
import com.finalproyectjava.service.serviceInterfaces.IPrestamoService;
import com.finalproyectjava.service.serviceInterfaces.IReporteService;

public class ReporteServiceImpl implements IReporteService {

    private final IPrestamoService prestamoService;
    private final IPagoService pagoService;
    private final IClienteService clienteService;
    private final IEmpleadoService empleadoService;

    public ReporteServiceImpl(IPrestamoService ps, IPagoService pagos,
                              IClienteService cs, IEmpleadoService es) {
        this.prestamoService = ps;
        this.pagoService = pagos;
        this.clienteService = cs;
        this.empleadoService = es;
    }

    @Override
    public List<Prestamo> prestamosActivos() {
        return prestamoService.listaPrestamo()
                .stream()
                .filter(p -> p.getEstado() == EstadoPrestamo.PENDIENTE)
                .toList();
    }

    @Override
    public List<Prestamo> prestamosPagados() {
        return prestamoService.listaPrestamo()
                .stream()
                .filter(p -> p.getEstado() == EstadoPrestamo.PAGADO)
                .toList();
    }

    @Override
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

    @Override
    public List<Cliente> clientesMorosos() {
        List<Prestamo> prestamos = prestamoService.listaPrestamo();
        List<Pago> pagos = pagoService.listarPagos();

        return clienteService.listarClientes()
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

    @Override
    public Map<Empleado, Long> empleadosConMasPrestamos() {
        return prestamoService.listaPrestamo()
                .stream()
                .map(p -> {
                    try {
                        return empleadoService.buscarEmpleadoId(p.getEmpleadoId());
                    } catch (EmpleadoNoEncontradoException e) {
                        return null;
                    }
                })
                .filter(e -> e != null)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    @Override
    public double totalRecaudado() {
        return pagoService.listarPagos()
                .stream()
                .mapToDouble(Pago::getMonto)
                .sum();
    }
}

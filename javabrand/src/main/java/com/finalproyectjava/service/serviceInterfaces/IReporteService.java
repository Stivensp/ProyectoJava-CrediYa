package com.finalproyectjava.service.serviceInterfaces;

import java.util.List;
import java.util.Map;

import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Empleado;
import com.finalproyectjava.model.Prestamo;

public interface IReporteService {

    List<Prestamo> prestamosActivos();

    List<Prestamo> prestamosPagados();

    List<Prestamo> prestamosVencidos();

    List<Cliente> clientesMorosos();

    Map<Empleado, Long> empleadosConMasPrestamos();

    double totalRecaudado();
}

package com.finalproyectjava.service.serviceInterfaces;

import java.util.List;

import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Prestamo;

public interface IGeneradorReportes{

    List<Prestamo> prestamosActivos();

    List<Prestamo> prestamosPagados();

    List<Prestamo> prestamosVencidos();

    List<Cliente> clientesMorosos();

    List<Prestamo> activos();
}

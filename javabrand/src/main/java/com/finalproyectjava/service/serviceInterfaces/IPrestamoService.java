package com.finalproyectjava.service.serviceInterfaces;

import java.util.List;

import com.finalproyectjava.model.Pago;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;

public interface IPrestamoService {

    Prestamo agregarPrestamo(int clienteId, int empleadoId,
                             double monto, double interes, int cuotas);

    List<Prestamo> listaPrestamo();

    Prestamo buscarPrestamoId(int id);

    boolean cambiarEstadoPrestamo(int id, EstadoPrestamo estado);

    void recalcularCuotas(Prestamo prestamo);

    double calcularTotalPrestamo(Prestamo prestamo);

    double calcularTotalPagado(List<Pago> pagos);

    double calcularDeudaRestante(Prestamo prestamo, List<Pago> pagos);

    List<String> generarDetalleCuotas(Prestamo prestamo, List<Pago> pagos);

    void verificarYFinalizarPrestamo(Prestamo prestamo, List<Pago> pagos);

    List<Pago> obtenerPagosPrestamo(int prestamoId);

    void actualizarEstadoPrestamo(int prestamoId);
}

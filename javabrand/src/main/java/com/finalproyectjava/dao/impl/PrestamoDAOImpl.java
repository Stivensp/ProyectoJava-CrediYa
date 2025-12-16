package com.finalproyectjava.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.PrestamoDAO;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;
import com.finalproyectjava.util.ConexionBD;

public class PrestamoDAOImpl implements PrestamoDAO {

    @Override
    public Prestamo agregarPrestamoDAO(Prestamo p) {
        String sql = "INSERT INTO prestamos (cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, estado, valor_cuota) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, p.getClienteId());
            ps.setInt(2, p.getEmpleadoId());
            ps.setDouble(3, p.getMonto());
            ps.setDouble(4, p.getInteres());
            ps.setInt(5, p.getCuotas());
            ps.setDate(6, java.sql.Date.valueOf(p.getFechaInicio()));
            ps.setString(7, p.getEstado().name());
            ps.setDouble(8, p.getValorCuota());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1)); 
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al agregar préstamo: " + ex.getMessage(), ex);
        }
        return p;
    }

    @Override
    public List<Prestamo> listaPrestamosDAO() {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Prestamo p = new Prestamo(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getInt("empleado_id"),
                        rs.getDouble("monto"),
                        rs.getDouble("interes"),
                        rs.getInt("cuotas"),
                        rs.getDate("fecha_inicio").toLocalDate(),
                        EstadoPrestamo.valueOf(rs.getString("estado")),
                        rs.getDouble("valor_cuota")
                );
                lista.add(p);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar préstamos: " + ex.getMessage(), ex);
        }

        return lista;
    }

    @Override
    public Prestamo buscarPrestamoIdDAO(int id) {
        String sql = "SELECT * FROM prestamos WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Prestamo(
                            rs.getInt("id"),
                            rs.getInt("cliente_id"),
                            rs.getInt("empleado_id"),
                            rs.getDouble("monto"),
                            rs.getDouble("interes"),
                            rs.getInt("cuotas"),
                            rs.getDate("fecha_inicio").toLocalDate(),
                            EstadoPrestamo.valueOf(rs.getString("estado")),
                            rs.getDouble("valor_cuota")
                    );
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar préstamo por ID: " + ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public boolean cambiarEstadoPrestamoDAO(int id, EstadoPrestamo estado) {
        String sql = "UPDATE prestamos SET estado = ? WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado.name());
            ps.setInt(2, id);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al cambiar estado del préstamo: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void recalcularCuotasDAO(Prestamo prestamo) {
        if (prestamo.getCuotas() <= 0) {
            throw new IllegalArgumentException("Las cuotas deben ser mayores a 0");
        }
        double total = prestamo.getMonto() + (prestamo.getMonto() * prestamo.getInteres() / 100);
        prestamo.setValorCuota(total / prestamo.getCuotas());

        String sql = "UPDATE prestamos SET valor_cuota = ? WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, prestamo.getValorCuota());
            ps.setInt(2, prestamo.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al recalcular cuota: " + ex.getMessage(), ex);
        }
    }
}

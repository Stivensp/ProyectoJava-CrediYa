package com.finalproyectjava.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.PagoDAO;
import com.finalproyectjava.model.Pago;
import com.finalproyectjava.util.db.ConexionBD;

public class PagoDAOImpl implements PagoDAO {

    @Override
    public Pago registrarPagoDAO(Pago p) {
        String sql = "INSERT INTO pagos (prestamo_id, fecha_pago, monto) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, p.getPrestamoId());
            ps.setDate(2, java.sql.Date.valueOf(p.getfechaPago()));
            ps.setDouble(3, p.getMonto());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al registrar pago", ex);
        }

        return p;
    }

    @Override
    public List<Pago> listaPagoDAO() {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagos";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pago p = new Pago(
                        rs.getInt("id"),
                        rs.getInt("prestamo_id"),
                        rs.getDate("fecha_pago").toLocalDate(),
                        rs.getDouble("monto")
                );
                lista.add(p);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar pagos", ex);
        }

        return lista;
    }

    @Override
    public List<Pago> pagoPorPrestamoDAO(int prestamoId) {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagos WHERE prestamo_id = ?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, prestamoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pago p = new Pago(
                            rs.getInt("id"),
                            rs.getInt("prestamo_id"),
                            rs.getDate("fecha_pago").toLocalDate(),
                            rs.getDouble("monto")
                    );
                    lista.add(p);
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al obtener pagos por préstamo", ex);
        }

        return lista;
    }

        @Override
    public double totalPagadoPorPrestamoDAO(int prestamoId) {

        String sql = "SELECT COALESCE(SUM(monto), 0) FROM pagos WHERE prestamo_id = ?";
        
        try (Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, prestamoId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error calculando total pagado", e);
        }

        return 0;
    }

}

package com.finalproyectjava.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.EmpleadoDAO;
import com.finalproyectjava.model.Empleado;
import com.finalproyectjava.util.ConexionBD;

public class EmpleadoDAOImpl implements EmpleadoDAO {

    @Override
    public Empleado registrarEmpleadoDAO(Empleado e) {
        String sql = "INSERT INTO empleados (nombre, documento, rol, correo, salario) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDocumento());
            ps.setString(3, e.getRol());
            ps.setString(4, e.getCorreo());
            ps.setDouble(5, e.getSalario());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    e.setId(rs.getInt(1)); 
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al registrar empleado: " + ex.getMessage(), ex);
        }
        return e;
    }

    @Override
    public List<Empleado> listaEmpleadosDAO() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado e = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("documento"),
                        rs.getString("rol"),
                        rs.getString("correo"),
                        rs.getDouble("salario")
                );
                lista.add(e);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar empleados: " + ex.getMessage(), ex);
        }

        return lista;
    }

    @Override
    public Empleado buscarEmpleadoIdDAO(int id) {
        String sql = "SELECT * FROM empleados WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("documento"),
                            rs.getString("rol"),
                            rs.getString("correo"),
                            rs.getDouble("salario")
                    );
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar empleado por ID: " + ex.getMessage(), ex);
        }

        return null;
    }

    @Override
    public Empleado actualizarEmpleadoDAO(Empleado e) {
        String sql = "UPDATE empleados SET nombre=?, documento=?, rol=?, correo=?, salario=? WHERE id=?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDocumento());
            ps.setString(3, e.getRol());
            ps.setString(4, e.getCorreo());
            ps.setDouble(5, e.getSalario());
            ps.setInt(6, e.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar empleado: " + ex.getMessage(), ex);
        }
        return e;
    }

    @Override
    public boolean eliminarEmpleadoDAO(int id) {
        String sql = "DELETE FROM empleados WHERE id=?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar empleado: " + ex.getMessage(), ex);
        }
    }
}

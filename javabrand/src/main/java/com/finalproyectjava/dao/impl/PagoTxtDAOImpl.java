package com.finalproyectjava.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.finalproyectjava.dao.interfaces.PagoDAO;
import com.finalproyectjava.model.Pago;

public class PagoTxtDAOImpl implements PagoDAO {

    private static final String RUTA = "javabrand/data/pagos.txt";

    @Override
    public Pago registrarPagoDAO(Pago p) {
        p.setId(generarId());

        File archivo = new File(RUTA);
        File directorio = archivo.getParentFile();

        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(
                p.getId() + ";" +
                p.getPrestamoId() + ";" +
                p.getfechaPago() + ";" +
                p.getMonto()
            );
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar pago en TXT", e);
        }

        return p;
    }

    @Override
    public List<Pago> listaPagoDAO() {
        List<Pago> lista = new ArrayList<>();
        File archivo = new File(RUTA);

        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                Pago p = new Pago(
                    Integer.parseInt(datos[0]),
                    Integer.parseInt(datos[1]),
                    LocalDate.parse(datos[2]),
                    Double.parseDouble(datos[3])
                );

                lista.add(p);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer pagos desde TXT", e);
        }

        return lista;
    }

    @Override
    public List<Pago> pagoPorPrestamoDAO(int prestamoId) {
        List<Pago> lista = new ArrayList<>();

        for (Pago p : listaPagoDAO()) {
            if (p.getPrestamoId() == prestamoId) {
                lista.add(p);
            }
        }

        return lista;
    }

    private int generarId() {
        int max = 0;
        for (Pago p : listaPagoDAO()) {
            if (p.getId() > max) {
                max = p.getId();
            }
        }
        return max + 1;
    }
}

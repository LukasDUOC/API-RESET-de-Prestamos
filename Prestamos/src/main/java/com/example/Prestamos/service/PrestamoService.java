package com.example.Prestamos.service;

import com.example.Prestamos.model.Prestamo;
import com.example.Prestamos.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public Prestamo guardPrestamo(Prestamo prestamo) {
        if (prestamo.getCodigoLibro() == null || prestamo.getCodigoLibro().trim().isEmpty()) {
            throw new IllegalArgumentException("Error: no puedes agregar un codgio vacio");

        }
        if (prestamo.getDiaPrestamo() > 30) {
            throw new IllegalArgumentException("Error: El plazo de préstamo excede el límite de 30 días");
        }
        if (prestamo.getRutUsuario() == null || prestamo.getRutUsuario().trim().length() < 8)
            throw new IllegalArgumentException("Error: El RUT ingresado es inválido.");
        prestamo.setFechaInicio(LocalDate.now());

        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> obtenerTodos() {

        return prestamoRepository.findAll();
    }

    public Optional<Prestamo> obtenerPorId(Long id) {
        return prestamoRepository.findById(id);
    }

    public Prestamo actualizarPrestamo(Long id, Prestamo detallesPrestamo) {
        // Primero verificamos si el libro existe en la base de datos
        Optional<Prestamo> prestamoExistente = prestamoRepository.findById(id);

        if (prestamoExistente.isPresent()) {
            // Obtenemos el libro real de la base de datos
            Prestamo prestamoAActualizar = prestamoExistente.get();
            // Actualizamos sus datos con los nuevos datos recibidos
            prestamoAActualizar.setCodigoLibro(detallesPrestamo.getCodigoLibro());
            prestamoAActualizar.setRutUsuario(detallesPrestamo.getRutUsuario());
            prestamoAActualizar.setDiaPrestamo(detallesPrestamo.getDiaPrestamo());
            prestamoAActualizar.setFechaInicio(detallesPrestamo.getFechaInicio());
            // Guardamos los cambios
            return prestamoRepository.save(prestamoAActualizar);
        } else {
            // Si no existe, retornamos nulo o podríamos lanzar un error
            return null;
        }
    }

    public boolean eliminarPrestamo(Long id) {
        if (prestamoRepository.existsById(id)) {
            prestamoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

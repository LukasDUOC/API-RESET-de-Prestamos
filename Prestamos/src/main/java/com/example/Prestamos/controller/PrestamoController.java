package com.example.Prestamos.controller;

import com.example.Prestamos.model.Prestamo;
import com.example.Prestamos.service.PrestamoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<?> crearPrestamo(@RequestBody Prestamo prestamo) {

    try {
        Prestamo guardado = prestamoService.guardPrestamo(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }
    }

    @GetMapping
    public List<Prestamo> listarTodo() {
        return prestamoService.obtenerTodos();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerLibroPorId(@PathVariable Long id) {
        Optional<Prestamo> prestamo = prestamoService.obtenerPorId(id);
        if (prestamo.isPresent()) {
            return ResponseEntity.ok(prestamo.get()); // Retorna HTTP 200 con el libro
        } else {
            return ResponseEntity.notFound().build(); // Retorna HTTP 404 si no existe
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Long id, @RequestBody Prestamo detallesPrestamo) {
        Prestamo prestamoActualizado = prestamoService.actualizarPrestamo(id, detallesPrestamo);
        if (prestamoActualizado != null) {
            return ResponseEntity.ok(prestamoActualizado); // Retorna HTTP 200 con los nuevos datos
        } else {
            return ResponseEntity.notFound().build(); // Retorna HTTP 404
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {
        boolean eliminado = prestamoService.eliminarPrestamo(id);
        if (eliminado) {
            return ResponseEntity.noContent().build(); // Retorna HTTP 204 (Éxito sin contenido)
        } else {
            return ResponseEntity.notFound().build(); // Retorna HTTP 404
        }
    }

}

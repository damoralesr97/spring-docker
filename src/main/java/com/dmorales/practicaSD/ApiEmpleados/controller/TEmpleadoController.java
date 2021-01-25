package com.dmorales.practicaSD.ApiEmpleados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmorales.practicaSD.ApiEmpleados.ResourceNotFoundException;
import com.dmorales.practicaSD.ApiEmpleados.entities.TEmpleado;
import com.dmorales.practicaSD.ApiEmpleados.repository.TEmpleadoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TEmpleadoController {

	@Autowired
	private TEmpleadoRepository tEmpleadoRepository;

	@PostMapping("/empleados")
	public TEmpleado crearEmpleado(@RequestBody TEmpleado empleado) {
		return tEmpleadoRepository.save(empleado);
	}

	@GetMapping("/empleados")
	public ResponseEntity<List<TEmpleado>> getEmpleados() {

		return ResponseEntity.ok(tEmpleadoRepository.findAll());
	}

	@GetMapping("/empleados/{id}")
	public ResponseEntity<TEmpleado> getEmpleadoById(@PathVariable(value = "id") Integer empleadoId)
			throws ResourceNotFoundException {
		TEmpleado empleado = tEmpleadoRepository.findById(empleadoId)
				.orElseThrow(() -> new ResourceNotFoundException("El id: " + empleadoId + " no existe."));
		return ResponseEntity.ok().body(empleado);
	}

	@PutMapping("/empleados/{id}")
	public ResponseEntity<TEmpleado> actualizarEmpleado(@PathVariable(value = "id") Integer empleadoId,
			@RequestBody TEmpleado empleadoDetalle) throws ResourceNotFoundException {
		TEmpleado empleado = tEmpleadoRepository.findById(empleadoId)
				.orElseThrow(() -> new ResourceNotFoundException("El id: " + empleadoId + " no existe."));

		empleado.setNombre(empleadoDetalle.getNombre());
		empleado.setCorreo(empleadoDetalle.getCorreo());
		empleado.setTelefono(empleadoDetalle.getTelefono());
		empleado.setDepartamento(empleadoDetalle.getDepartamento());

		final TEmpleado empleadoAct = tEmpleadoRepository.save(empleado);
		return ResponseEntity.ok(empleadoAct);
	}

	@DeleteMapping("/empleados/{id}")
	public Map<String, Boolean> eliminarEmpleado(@PathVariable(value = "id") Integer empleadoId)
			throws ResourceNotFoundException {
		TEmpleado empleado = tEmpleadoRepository.findById(empleadoId)
				.orElseThrow(() -> new ResourceNotFoundException("El id: " + empleadoId + " no existe."));

		tEmpleadoRepository.delete(empleado);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("emilinado", Boolean.TRUE);
		return respuesta;
	}

}

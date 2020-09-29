package com.web.cliente.Restservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.mongoLib.EntidadesMongo.Alcaldia;
import com.test.mongoLib.EntidadesMongo.UbicacionUnidades;
import com.test.mongoLib.EntidadesMongo.Unidad;
import com.test.mongoLib.crudBeans.MongoCrud;

@RestController
public class ServiciosRest implements Servicios {

	@Autowired
	private MongoCrud mongoCrud;

	public ServiciosRest() {
	}

	public MongoCrud getMongoCrud() {
		return mongoCrud;
	}

	@Override
	@GetMapping("/listarUnidadesActivas")
	public List<Unidad> listarUnidades() {
		return mongoCrud.unidadObtenerTodo();
	}

	@Override
	@GetMapping("/listarAlcaldiasActivas")
	public List<Alcaldia> listarAlcaldias() {
		return mongoCrud.alcaldiaObtenerTodo();
	}

	@Override
	@GetMapping("/seguimientoUnidad")
	public ResponseEntity<?> rastrearUnidad(@RequestParam Integer id) {
		List<UbicacionUnidades> respuesta = mongoCrud.seguimientoUnidad(id);

		if(respuesta.isEmpty()){
			return ResponseEntity.status(404).body("Unidad no encontrada");
		}

		return ResponseEntity.ok(respuesta);
	}

	@Override
	@GetMapping("/seguimientoPorAlcaldia")
	public ResponseEntity<?> filtarUnidadAlcaldia(@RequestParam Integer id) {
		
		Optional<Alcaldia> alcaldia = mongoCrud.obtenerAlcaldia(id);
		
		if(!alcaldia.isPresent()) {
			return ResponseEntity.status(404).body("Alcaldia no registrada");
		}
		
		Double[][] coordenadas = alcaldia.get().getForma().getCoordenadas()[0];
		Optional<List<UbicacionUnidades>> unidades = mongoCrud.obtenerUnidadesAlcaldia(coordenadas);
		
		if(!unidades.isPresent()) {
			return ResponseEntity.status(404).body("No se encontraron unidades en "+alcaldia.get().getNombre());
		}
		
		return ResponseEntity.ok(unidades.get());
	}
	
	@GetMapping("/")
	public String hola(){
		return "Rutas Disponibles <br>"
				+ "/listarUnidadesActivas  <br>"
				+ "/listarAlcaldiasActivas <br>"
				+ "/seguimientoUnidad, parametro id (identificador de la unidad) <br>"
				+ "/seguimientoPorAlcaldia, parametro id (identificador de Alcaldia a consultar) ";
	}

}

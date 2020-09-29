package com.web.cliente.Restservice;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.test.mongoLib.EntidadesMongo.Alcaldia;
import com.test.mongoLib.EntidadesMongo.Unidad;

public interface Servicios {
	public List<Unidad> listarUnidades();
	public List<Alcaldia> listarAlcaldias();
	public ResponseEntity<?> rastrearUnidad(Integer id);
	public ResponseEntity<?> filtarUnidadAlcaldia(Integer id);
}

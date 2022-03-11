package es.marioperez.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.marioperez.modelo.Mesa;
import es.marioperez.modelo.MesaRepositorio;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MesaController 
{
	private final MesaRepositorio mesaRepositorio;	
	
	@GetMapping("/mesas")
	public List<Mesa> obtenerTodos()
	{
		return mesaRepositorio.findAll();
	}


}

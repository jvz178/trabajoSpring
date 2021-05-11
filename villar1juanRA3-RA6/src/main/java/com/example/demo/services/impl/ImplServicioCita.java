package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Citas;
import com.example.demo.entity.Mascotas;
import com.example.demo.entity.Usuarios;
import com.example.demo.repository.RepositorioCita;
import com.example.demo.repository.RepositorioUsuario;
import com.example.demo.services.ServicioCita;
import com.example.demo.services.ServicioUsuario;

@Service("servicioCita")
public class ImplServicioCita implements ServicioCita {

	@Autowired
	@Qualifier("repositorioCita")
	private RepositorioCita repositorioCita;

	@Autowired
	@Qualifier("servicioUsuario")
	private ServicioUsuario servicioUsuario;
	
	public List<Citas> listarCita() {
		return repositorioCita.findAll();
	}

	public Citas añadirCita(Citas cita) {
		return repositorioCita.save(cita);
	}

	public int quitarCita(int id) {
		repositorioCita.deleteById(id);
		return 0;
	}

	public Citas actualizarCita(Citas cita) {
		return repositorioCita.save(cita);
	}
	
	public Citas listarCitaPorMascota(Mascotas mascota) {
		
		return repositorioCita.findByIdMascota(mascota);
	}

	@Override
	public Citas obtenerCitaPorId(int id) throws Exception{
		return repositorioCita.findById(id);
	}

	@Override
	public List<Citas> obtenerCitaPendientesPorMascota(List<Mascotas> mascotas) {
		
		List<Citas> citas = listarCita();
		Citas[] citasPendientes = new Citas[citas.size()];
		int contador = 0;
		
		for(Citas cita : citas) {
			
			for(Mascotas mascota : mascotas) {
				
				if(cita.getIdMascota()==mascota) {
					
					if(cita.getRealizada()==false) {
						
						citasPendientes[contador]=cita;
						contador++;
					}
				}
			}
		}
		
		List<Citas> citasFinal = Arrays.asList(citasPendientes);
		
		/*for(Citas ct : citasFinal ) {
			
			if(ct==null) {
				
				citasFinal.remove(ct);
			}
		}*/
		
		for(Citas x : citasFinal) {
			
			System.out.println(x.getMotivo());
		}
			
		return citasFinal;
	}
	
	
	
	
	
	
	
	
	/*public List<Usuarios> veterinariosLibres(Date fechaSolicitada) {
		System.out.println("HOLAAAAAAAAAAA");
		List<Citas> listaCitas = listarCita();
		List<Usuarios> usuarios = servicioUsuario.listarUsuario();
		Usuarios[] veterinariosFecha = new Usuarios[usuarios.size()];
		Usuarios[] disponibles = new Usuarios[usuarios.size()];
		int contador=0, contador2=0;
		
		for(Usuarios u : usuarios) {
			
			if(u.getRole().equals("ROLE_VET")) {
				
				veterinariosFecha[contador]=u;
				contador++;
			}
		}
		
		for(Citas cita : listaCitas) {
			
			if(cita.getFecha()==fechaSolicitada) {
				
				veterinariosFecha[contador]=cita.getIdVeterinario();
				contador++;
			}
		}
		
		for(Usuarios usuario : usuarios) {
			System.out.println("HOLAAAAAAAAAAAAAAAAAAAA3");
			System.out.println("ROL: "+usuario.getRole());
			if(usuario.getRole().equals("ROLE_VET")) {
				System.out.println("ENTRA EN ROL");
				int repetido=-1;
				
				if(veterinariosFecha == null) {
					
					break;
				}
				
				for(Usuarios veterinarioFecha : veterinariosFecha) {
					System.out.println("ENTRA EN VETERINARIOSFECHA");
					if(usuario==veterinarioFecha) {
						
						repetido++;
					}
				}
				
				if(repetido<3) {
					System.out.println("ENTRA EN REPETIDO <3");
					disponibles[contador2]=usuario;
					contador2++;
				}
			}
		}
		
		List<Usuarios> listaFinal = new ArrayList<Usuarios>();
		for(Usuarios us : disponibles) {
			
			listaFinal.add(us);
		}
		
		return listaFinal;
	}*/
}

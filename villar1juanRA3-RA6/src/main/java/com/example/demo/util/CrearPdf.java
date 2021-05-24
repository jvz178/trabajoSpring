package com.example.demo.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.Citas;
import com.example.demo.entity.Usuarios;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("historialCitas")
public class CrearPdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Citas> listadoCitas = (List<Citas>) model.get("citas");
		
		PdfPTable tablaCliente = new PdfPTable(4);
		tablaCliente.addCell("Nombre");
		tablaCliente.addCell("Apellidos");
		tablaCliente.addCell("Telefono");
		tablaCliente.addCell("Username");
		tablaCliente.setHeaderRows(1);
		
		PdfPTable tablaVeterinarios = new PdfPTable(4);
		tablaVeterinarios.addCell("Nombre");
		tablaVeterinarios.addCell("Apellidos");
		tablaVeterinarios.addCell("Telefono");
		tablaVeterinarios.addCell("Username");
		tablaVeterinarios.setHeaderRows(1);
		
		PdfPTable tablaMascota = new PdfPTable(5);
		tablaMascota.addCell("Nombre");
		tablaMascota.addCell("Tipo");
		tablaMascota.addCell("Raza");
		tablaMascota.addCell("Fecha de nacimiento");
		tablaMascota.addCell("Foto");
		tablaMascota.setHeaderRows(1);
		
		PdfPTable tablaCitas = new PdfPTable(4);
		tablaCitas.addCell("Fecha");
		tablaCitas.addCell("Motivo");
		tablaCitas.addCell("Informe");
		tablaCitas.addCell("Realizada");
		tablaCitas.setHeaderRows(1);
		
		document.open();
		listadoCitas.forEach(cita -> {
			tablaCliente.deleteBodyRows();
			tablaCliente.addCell(cita.getIdMascota().getIdCliente().getNombre());
			tablaCliente.addCell(cita.getIdMascota().getIdCliente().getApellidos());
			tablaCliente.addCell(cita.getIdMascota().getIdCliente().getTelefono());
			tablaCliente.addCell(cita.getIdMascota().getIdCliente().getUsername());
			document.add(tablaCliente);
			
			tablaVeterinarios.deleteBodyRows();
			tablaVeterinarios.addCell(cita.getIdVeterinario().getNombre());
			tablaVeterinarios.addCell(cita.getIdVeterinario().getApellidos());
			tablaVeterinarios.addCell(cita.getIdVeterinario().getTelefono());
			tablaVeterinarios.addCell(cita.getIdVeterinario().getUsername());
			document.add(tablaVeterinarios);
			
			tablaMascota.deleteBodyRows();
			tablaMascota.addCell(cita.getIdMascota().getNombre());
			tablaMascota.addCell(cita.getIdMascota().getTipo());
			tablaMascota.addCell(cita.getIdMascota().getRaza());
			tablaMascota.addCell(cita.getIdMascota().getFechaNacimiento().toString());
			tablaMascota.addCell(cita.getIdMascota().getFoto());
			document.add(tablaMascota);
			
			tablaCitas.deleteBodyRows();
			tablaCitas.addCell(cita.getFecha().toString());
			tablaCitas.addCell(cita.getMotivo());
			tablaCitas.addCell(cita.getInforme());
			if(cita.getRealizada()==true) {
				
				tablaCitas.addCell("Si");
			}else {
				
				tablaCitas.addCell("No");
			}
			document.add(tablaCitas);
			document.newPage();
		});
		
		/*document.add(tablaCitas);
		document.newPage();
		document.add(new Paragraph("PAGINA"));*/
		document.close();
	}

}

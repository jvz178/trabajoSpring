package com.example.demo.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.Citas;
import com.example.demo.entity.Usuarios;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("historialCitas")
public class CrearPdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Citas> listadoCitas = (List<Citas>) model.get("citas");
		
		Font fuenteTitulo = FontFactory.getFont("ariel",20,Color.WHITE);
		Font fuenteSubtitulos = FontFactory.getFont("ariel",14,Color.WHITE);
		
		PdfPTable titulo = new PdfPTable(1);
		PdfPCell celda = new PdfPCell(new Phrase("Informe citas", fuenteTitulo));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setBackgroundColor(new Color(228,9,32));
		celda.setPadding(20);
		celda.setBorder(0);
		titulo.addCell(celda);
		titulo.setSpacingAfter(30);
		
		PdfPTable subtitulo1 = new PdfPTable(1);
		PdfPCell celdaSubtitulo1 = new PdfPCell(new Phrase("Datos cliente y mascota", fuenteSubtitulos));
		celdaSubtitulo1.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaSubtitulo1.setVerticalAlignment(Element.ALIGN_CENTER);
		celdaSubtitulo1.setBackgroundColor(new Color(228,9,32));
		celdaSubtitulo1.setPadding(12);
		celdaSubtitulo1.setBorder(0);
		subtitulo1.addCell(celdaSubtitulo1);
		subtitulo1.setSpacingAfter(24);
		
		PdfPTable subtitulo2 = new PdfPTable(1);
		PdfPCell celdaSubtitulo2 = new PdfPCell(new Phrase("Citas", fuenteSubtitulos));
		celdaSubtitulo2.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaSubtitulo2.setVerticalAlignment(Element.ALIGN_CENTER);
		celdaSubtitulo2.setBackgroundColor(new Color(228,9,32));
		celdaSubtitulo2.setPadding(12);
		celdaSubtitulo2.setBorder(0);
		subtitulo2.addCell(celdaSubtitulo2);
		subtitulo2.setSpacingAfter(24);
		
		PdfPTable clienteTitulo = new PdfPTable(1);
		clienteTitulo.addCell("Cliente");
		PdfPTable tablaCliente = new PdfPTable(4);
		tablaCliente.addCell("Nombre");
		tablaCliente.addCell("Apellidos");
		tablaCliente.addCell("Telefono");
		tablaCliente.addCell("Username");
		tablaCliente.setHeaderRows(1);
		tablaCliente.setSpacingAfter(20);
		
		PdfPTable veterinarioTitulo = new PdfPTable(1);
		veterinarioTitulo.addCell("Veterinario");
		PdfPTable tablaVeterinarios = new PdfPTable(4);
		tablaVeterinarios.addCell("Nombre");
		tablaVeterinarios.addCell("Apellidos");
		tablaVeterinarios.addCell("Telefono");
		tablaVeterinarios.addCell("Username");
		tablaVeterinarios.setHeaderRows(1);
		tablaVeterinarios.setSpacingAfter(20);
		
		PdfPTable mascotaTitulo = new PdfPTable(1);
		mascotaTitulo.addCell("Mascota");
		PdfPTable tablaMascota = new PdfPTable(5);
		tablaMascota.addCell("Nombre");
		tablaMascota.addCell("Tipo");
		tablaMascota.addCell("Raza");
		tablaMascota.addCell("Fecha de nacimiento");
		tablaMascota.addCell("Foto");
		tablaMascota.setHeaderRows(1);
		tablaMascota.setSpacingAfter(20);
		
		PdfPTable citaTitulo = new PdfPTable(1);
		citaTitulo.addCell("Cita");
		PdfPTable tablaCitas = new PdfPTable(4);
		tablaCitas.addCell("Fecha");
		tablaCitas.addCell("Motivo");
		tablaCitas.addCell("Informe");
		tablaCitas.addCell("Realizada");
		tablaCitas.setHeaderRows(1);
		tablaCitas.setSpacingAfter(20);
		
		document.open();
		document.add(titulo);
		document.add(subtitulo1);
		tablaCliente.addCell(listadoCitas.get(0).getIdMascota().getIdCliente().getNombre());
		tablaCliente.addCell(listadoCitas.get(0).getIdMascota().getIdCliente().getApellidos());
		tablaCliente.addCell(listadoCitas.get(0).getIdMascota().getIdCliente().getTelefono());
		tablaCliente.addCell(listadoCitas.get(0).getIdMascota().getIdCliente().getUsername());
		document.add(clienteTitulo);
		document.add(tablaCliente);
		tablaMascota.addCell(listadoCitas.get(0).getIdMascota().getNombre());
		tablaMascota.addCell(listadoCitas.get(0).getIdMascota().getTipo());
		tablaMascota.addCell(listadoCitas.get(0).getIdMascota().getRaza());
		tablaMascota.addCell(listadoCitas.get(0).getIdMascota().getFechaNacimiento().toString());
		tablaMascota.addCell(listadoCitas.get(0).getIdMascota().getFoto());
		document.add(mascotaTitulo);
		document.add(tablaMascota);
		document.newPage();
		
		document.add(subtitulo2);
		listadoCitas.forEach(cita -> {
			tablaVeterinarios.deleteBodyRows();
			tablaVeterinarios.addCell(cita.getIdVeterinario().getNombre());
			tablaVeterinarios.addCell(cita.getIdVeterinario().getApellidos());
			tablaVeterinarios.addCell(cita.getIdVeterinario().getTelefono());
			tablaVeterinarios.addCell(cita.getIdVeterinario().getUsername());
			document.add(veterinarioTitulo);
			document.add(tablaVeterinarios);
						
			tablaCitas.deleteBodyRows();
			tablaCitas.addCell(cita.getFecha().toString());
			tablaCitas.addCell(cita.getMotivo());
			tablaCitas.addCell(cita.getInforme());
			if(cita.getRealizada()==true) {
				
				tablaCitas.addCell("Si");
			}else {
				
				tablaCitas.addCell("No");
			}
			document.add(citaTitulo);
			document.add(tablaCitas);
			document.newPage();
		});
		
		/*document.add(tablaCitas);
		document.newPage();
		document.add(new Paragraph("PAGINA"));*/
		document.close();
	}

}

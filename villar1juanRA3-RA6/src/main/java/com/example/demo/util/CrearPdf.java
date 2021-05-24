package com.example.demo.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.demo.entity.Citas;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("historialCitas")
public class CrearPdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Citas> listadoCitas = (List<Citas>) model.get("citas");
		
		PdfPTable tablaCitas = new PdfPTable(4);
		
		tablaCitas.addCell("Fecha");
		tablaCitas.addCell("Motivo");
		tablaCitas.addCell("Informe");
		tablaCitas.addCell("Realizada");
		
		listadoCitas.forEach(cita -> {
			tablaCitas.addCell(cita.getFecha().toString());
			tablaCitas.addCell(cita.getMotivo());
			tablaCitas.addCell(cita.getInforme());
			if(cita.getRealizada()==true) {
				
				tablaCitas.addCell("Si");
			}else {
				
				tablaCitas.addCell("No");
			}
		});
		
		document.add(tablaCitas);
	}

}

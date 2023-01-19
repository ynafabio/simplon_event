package event.simplon.actions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import event.simplon.bean.Evenement;
import event.simplon.fwk.ContextUtils;






public class PdfAction {

	static Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	static Font boldFontSouligne = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD | Font.UNDERLINE);
	static Font boldFontBig = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	static Font Normal = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	static Font boldNormal = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	static Font boldNormalItalicBleu = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLDITALIC, BaseColor.BLUE);
	static Font boldNormalItalic = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLDITALIC);
	static Font normalItalic = new Font(FontFamily.TIMES_ROMAN, 10, Font.ITALIC);
	static Font smallNormal = new Font(FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
	static Font smallBoldNormal = new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD);
	static Font verySmallNormal = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
	static Font boldlVerySmallNormal = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD);
	static Font italicNormal = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL | Font.ITALIC);
	static Font boldNormalSouligne = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD | Font.UNDERLINE);
	static Font verySmallNormalItalic = new Font(FontFamily.TIMES_ROMAN, 8, Font.NORMAL | Font.ITALIC);
	static Paragraph paragraph = new Paragraph(50);
	static String data = "";
	

	static DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.FRENCH);
	static DecimalFormat df = new DecimalFormat("###,###", symbols);
	static DecimalFormat dfdeci = new DecimalFormat("###,###.##", symbols);
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static SimpleDateFormat sdfMin = new SimpleDateFormat("dd/MM/yy");
	static SimpleDateFormat sdfLettre = new SimpleDateFormat("dd MMMM yyyy"); 
	static SimpleDateFormat sdfDm1 = new SimpleDateFormat("dd/MM");
	static SimpleDateFormat sdfDm2 = new SimpleDateFormat("dd/MM");
	
	static String cheminLogo =ContextUtils.getDefaultPathDirectory()+"img/";
	
	class TableFooter extends PdfPageEventHelper {
	     String footer;
	     PdfTemplate total;

	     public void setFooter() {
	    	 String footer = "";
	         this.footer = footer;
	     }

	     @Override
	     public void onOpenDocument(PdfWriter writer, Document document) {
	         total = writer.getDirectContent().createTemplate(30, 16);
	     }

	     @Override
	     public void onEndPage(PdfWriter writer, Document document) {
	         PdfPTable table = new PdfPTable(3);
	         table.setWidthPercentage(100);
	         try {
	             if(document.getPageSize().getRotation()==0){
	                 if(writer.getPageNumber()!=1){
	                	 table.setWidths(new int[]{94, 3, 1});
	                     table.setTotalWidth(527);
	                     table.setLockedWidth(true);
	                     PdfPCell cell; 
	                     table.getDefaultCell().setFixedHeight(30);
	                     table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	                     cell = new PdfPCell(new Phrase(footer,verySmallNormal));
	                     cell.setBorder(Rectangle.NO_BORDER);
	                     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                     table.addCell(cell);
	                     cell = new PdfPCell(new Phrase(String.format("%d /", writer.getPageNumber()),verySmallNormal));
	                     cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                     cell.setBorder(Rectangle.NO_BORDER);
	                     table.addCell(cell);
	                     cell = new PdfPCell(Image.getInstance(total));
	                     cell.setBorder(Rectangle.NO_BORDER);
	                     table.addCell(cell);
	                     //table.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
	                     table.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());
	                 }
	             }else{
	                     table.setWidths(new int[]{96, 3, 1});
	                     table.setTotalWidth(790);
	                     table.setLockedWidth(true);
	                     PdfPCell cell; 
	                     table.getDefaultCell().setFixedHeight(30);
	                     table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	                     cell = new PdfPCell(new Phrase(footer,verySmallNormal));
	                     cell.setBorder(Rectangle.NO_BORDER);
	                     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                     table.addCell(cell);
	                     cell = new PdfPCell(new Phrase(String.format("%d /", writer.getPageNumber()),verySmallNormal));
	                     cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                     cell.setBorder(Rectangle.NO_BORDER);
	                     table.addCell(cell);
	                     cell = new PdfPCell(Image.getInstance(total));
	                     cell.setBorder(Rectangle.NO_BORDER);
	                     table.addCell(cell);
	            	 
	                     table.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(), writer.getDirectContent());	                     
	             }
	         }
	         catch(DocumentException de) {
	             throw new ExceptionConverter(de);
	         }
	     }


	     @Override
	     public void onCloseDocument(PdfWriter writer, Document document) {
	         ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
	                 new Phrase(String.valueOf(writer.getPageNumber() - 1),verySmallNormal),
	                 2, 2, 0);
	     }
	}
	 
	public void createListParticipantEvent(List<Evenement> list, 
			String date1, String date2, String dest) throws DocumentException, MalformedURLException, IOException, ParseException{
	    
	    	 Document document = new Document(PageSize.A4.rotate(), 25, 25, 25, 36);
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
	         TableFooter event = new TableFooter();
	         writer.setPageEvent(event);
	         document.open();
	         
	         int cpt=0;
	         int idev=0;
	         
	         enteteDocument(writer, document, date1, date2, "Liste des participants", "", null);
	         
	         document.add(new Paragraph("\n"));
	         
	         document.add(enteteTableau(writer, document));
	        
	        
	         for(int i=0; i<list.size(); i++) {
	        	 Evenement bean = list.get(i);
	        	 if(bean.getIdEvenement()!=idev) {
	        		 
	        		 document.add(ruptureTableau(writer, document, "Date : " + sdf.format(bean.getDateEvenement()) 
	        		 + "      " +  bean.getLibEvenement(), true));
	        		 cpt = 0;
	        	 }
	        	 document.add(ligneCorpsTableau(writer, document, bean, ++cpt));
	        	 
	        	 idev = bean.getIdEvenement();
	         }
	         
	         document.close();
	         
	       //Ajouter un pied de page
	        // event.setFooter();
	 		
	 }
	
	
	public static void enteteDocument(PdfWriter writer, Document document, String date1, String date2, 
			String titre, String soustitre, String logoEntite) throws DocumentException, MalformedURLException, IOException, ParseException{
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.setWidths(new int[] { 3, 4, 3 });
		 PdfPCell cell ;
		 	
			if(logoEntite!=null && cheminLogo!=null) {
			Image logo = Image.getInstance(cheminLogo + logoEntite);
			logo.scaleAbsolute(110f, 50f);
				cell = new PdfPCell(logo);
			}else
				cell = new PdfPCell(new Phrase(""));
		      cell.setRowspan(3);
		      cell.setBorder(Rectangle.NO_BORDER);
		      table.addCell(cell);
		      cell = new PdfPCell(new Phrase(titre, boldFont));
		      cell.setHorizontalAlignment(Element.ALIGN_CENTER);	
		      cell.setBorder(Rectangle.NO_BORDER);
		      table.addCell(cell);
		      cell = new PdfPCell(new Phrase(sdf.format(new Date()), smallBoldNormal));
		      cell.setRowspan(3);
		      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		      cell.setBorder(Rectangle.NO_BORDER);
		      table.addCell(cell);
		      
		      cell = new PdfPCell(new Phrase(soustitre, boldNormal));
		      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		      cell.setBorder(Rectangle.NO_BORDER);
		      table.addCell(cell);
		      if(date1 !=null && date2!=null)
		    	  cell = new PdfPCell(new Phrase("Période du " + date1 + " - " + date2, boldNormal));
		      else if(date1 != null)
		    	  cell = new PdfPCell(new Phrase("Date : " + date1 , boldNormal));
		      else
		    	  cell = new PdfPCell(new Phrase("Date : " + sdf.format(new Date()) , boldNormal));
		      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		      cell.setBorder(Rectangle.NO_BORDER);
		      table.addCell(cell);
			
			document.add(table);
	}
	
	public static PdfPTable enteteTableau(PdfWriter writer, Document document) throws DocumentException{
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 1, 2.5f, 4, 2, 3});
		
		PdfPCell cell;
		
		cell = new PdfPCell(new Paragraph("N°", smallBoldNormal));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(6f);
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("Nom".toUpperCase(), smallBoldNormal));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(6f);
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("Prénoms".toUpperCase(), smallBoldNormal));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(6f);
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("Téléphone".toUpperCase(), smallBoldNormal));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(6f);
		table.addCell(cell);
		cell = new PdfPCell(new Paragraph("Email".toUpperCase(), smallBoldNormal));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(6f);
		table.addCell(cell);
		
				
		return table;
	}
	
		
	public static PdfPTable ligneCorpsTableau(PdfWriter writer, Document document, Evenement bean, int compteur) throws DocumentException{
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 1, 2.5f, 4, 2, 3});
		PdfPCell cell;
		
		
				cell = new PdfPCell(new Phrase(String.valueOf(compteur), smallNormal));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(6f);
				table.addCell(cell);
				
				data = bean.getNomParticipant()!=null?bean.getNomParticipant().toUpperCase():"";
				cell = new PdfPCell(new Phrase(data, smallNormal));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPadding(6f);
				table.addCell(cell);
				data = bean.getPrenomParticipant()!=null?bean.getPrenomParticipant().toUpperCase():"";
				cell = new PdfPCell(new Phrase(data, smallNormal));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPadding(6f);
				table.addCell(cell);
				data = bean.getTelParticipant()!=null?bean.getTelParticipant():"";
				cell = new PdfPCell(new Phrase(data, smallNormal));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(6f);
				table.addCell(cell);
				data = bean.getEmailParticipant()!=null?bean.getEmailParticipant():"";
				cell = new PdfPCell(new Phrase(data, smallNormal));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPadding(6f);
				table.addCell(cell);

		return table;
	}
	
	public static PdfPTable ruptureTableau(PdfWriter writer, Document document, String titre, boolean avecBordure) throws DocumentException{
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		
		PdfPCell cell;
		
		cell = new PdfPCell(new Paragraph(titre, boldNormal));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBackgroundColor(new BaseColor(230, 230, 230));
		if(avecBordure == false)
			cell.setBorder(Rectangle.NO_BORDER);
		cell.setPadding(6f);
		table.addCell(cell);
		
		return table;
	}
	
	
}

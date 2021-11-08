package ferreteria;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GenerarReportes {

    public  void CrearFactura(){
       try{

           File file = new File("C:\\Users\\Home\\Desktop\\REPORTES\\repo1.pdf");
           PdfWriter pdfWriter = new PdfWriter(file);

           PdfDocument pdfDocument = new PdfDocument(pdfWriter);

           Document document = new Document(pdfDocument);

           Paragraph paragraph = new Paragraph("reporte 1");

           document.add(paragraph);
           document.close();

           pdfWriter.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

    }

    public  static void crearpdf(){

    }
    /*
    @FXML public void GenerarReporteStock(String St){

    }
    @FXML public void GenerarRepVentas(){

    }
    @FXML public void GenerarRepCompras(){

    }


     */
}

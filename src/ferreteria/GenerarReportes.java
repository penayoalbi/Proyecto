package ferreteria;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GenerarReportes {

    public  void CrearFactura(){
       try{
            //file es el fichero donde se crea el archivo
           File file = new File("C:\\Users\\Home\\Desktop\\REPORTES\\repo1.pdf");

           //PdfWriteres un objeto que puede escribir un archivo PDF
           PdfWriter pdfWriter = new PdfWriter(file);

           //El PdfDocument administra el contenido que se agrega
           PdfDocument pdfDocument = new PdfDocument(pdfWriter);

           //toma PdfDocument como parámetro
           Document document = new Document(pdfDocument);

           document.add(new Paragraph("parrafo 1: \n\n "));

           List lista = new ArrayList();

           Paragraph titulo = new Paragraph("Detalle de ventas \n\n");

           document.add(titulo);
           document.close();

           pdfWriter.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }

    }

    public  static void crearReporteTable() {

        try {
            File destino = new File("C:\\Users\\Home\\Desktop\\REPORTES\\repo1.pdf");

            PdfWriter pdfWriter = new PdfWriter(destino);

            PdfDocument pdf = new PdfDocument(pdfWriter);


            Document document = new Document(pdf);


            document.add(new Paragraph("es una prueba: "));
            Paragraph titulo = new Paragraph("Detalle de ventas \n\n");

            //agregar tabla
            document.add(titulo);
            document.close();

            pdfWriter.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

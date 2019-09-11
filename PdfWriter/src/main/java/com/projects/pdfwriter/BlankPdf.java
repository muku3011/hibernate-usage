package com.projects.pdfwriter;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class BlankPdf {

	public static void main(String[] args) throws IOException {
		// Create a new empty document
		PDDocument document = new PDDocument();
		
		// Create a new blank page and add it to the document
		PDPage blankPage = new PDPage();
		document.addPage(blankPage);
		
		// Save the newly created document
        document.save("BlankPage.pdf_writer");

        // finally make sure that the document is properly
		// closed.
		document.close();
	}
	
}

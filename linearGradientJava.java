package com.itextpdf.samples.sandbox.graphics;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.gradients.AbstractLinearGradientBuilder;
import com.itextpdf.kernel.colors.gradients.GradientColorStop;
import com.itextpdf.kernel.colors.gradients.LinearGradientBuilder;
import com.itextpdf.kernel.colors.gradients.StrategyBasedLinearGradientBuilder;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.BackgroundImage;
import com.itextpdf.layout.property.Property;

import java.io.File;

public class LinearGradientsInLayout {
    public static final String DEST = "./results/linearGradientParagraph.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new LinearGradientsInLayout().manipulatePdf();
    }

    protected void manipulatePdf() throws Exception {
        Document doc = new Document(new PdfDocument(new PdfWriter(DEST)));
        addLinearGradientAsElementBackground(doc);
        doc.close();
    }

    private void addLinearGradientAsElementBackground(Document doc) {
        doc.add(new Paragraph("The \"addLinearGradientAsElementBackground\" starts here."));

        AbstractLinearGradientBuilder gradientBuilder = new StrategyBasedLinearGradientBuilder()
                .addColorStop(new GradientColorStop(ColorConstants.RED.getColorValue()))
                .addColorStop(new GradientColorStop(ColorConstants.GREEN.getColorValue()))
                .addColorStop(new GradientColorStop(ColorConstants.BLUE.getColorValue()));
        BackgroundImage backgroundImage = new BackgroundImage(gradientBuilder);

        if (backgroundImage.isBackgroundSpecified()) {
            String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
                    "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
                    "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                    "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
                    "officia deserunt mollit anim id est laborum. ";

            Div div = new Div().add(new Paragraph(text + text + text));
            div.setProperty(Property.BACKGROUND_IMAGE, backgroundImage);
            doc.add(div);

        }
    }
}

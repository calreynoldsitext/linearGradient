using System;
using System.IO;
using iText.Kernel.Colors;
using iText.Kernel.Colors.Gradients;
using iText.Kernel.Geom;
using iText.Kernel.Pdf;
using iText.Layout;
using iText.Layout.Borders;
using iText.Layout.Element;
using iText.Layout.Properties;

namespace iText.Samples.Sandbox.Graphics
{
    public class LinearGradientsInLayoutParagraph
    {
        public static readonly string DEST = "results/linearGradientParagraph.pdf";

        public static void Main(String[] args)
        {
            FileInfo file = new FileInfo(DEST);
            file.Directory.Create();

            new LinearGradientsInLayout().ManipulatePdf();
        }

        protected void ManipulatePdf()
        {
            Document doc = new Document(new PdfDocument(new PdfWriter(DEST)));
            AddLinearGradientAsElementBackground(doc);
            doc.Close();
        }

        private void AddLinearGradientAsElementBackground(Document doc)
        {
            doc.Add(new Paragraph("The \"addLinearGradientAsElementBackground\" starts here."));

            AbstractLinearGradientBuilder gradientBuilder = new StrategyBasedLinearGradientBuilder()
                    .AddColorStop(new GradientColorStop(ColorConstants.RED.GetColorValue()))
                    .AddColorStop(new GradientColorStop(ColorConstants.GREEN.GetColorValue()))
                    .AddColorStop(new GradientColorStop(ColorConstants.BLUE.GetColorValue()));
            BackgroundImage backgroundImage = new BackgroundImage(gradientBuilder);

            if (backgroundImage.IsBackgroundSpecified())
            {
                String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                              "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                              "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
                              "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit " +
                              "in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                              "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
                              "officia deserunt mollit anim id est laborum. ";

                Div div = new Div().Add(new Paragraph(text + text + text));
                div.SetProperty(Property.BACKGROUND_IMAGE, backgroundImage);
                doc.Add(div);

            }
        }
    }
}

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerCreator {

    public void create(InputStream inputStream, String fileName) throws Exception {

        // leitura da imagem
        // InputStream inputStream = new FileInputStream(new File("input/movie.jpg"));
        // InputStream inputStream = new
        // URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        int newHeight = height + (height / 5);

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // configurar a fonte
        // var font = new Font("Impact", Font.BOLD, 64);
        // graphics.setColor(Color.YELLOW);
        // graphics.setFont(font);

        // Colando uma imagem
        BufferedImage joinha = ImageIO.read(new File("input/approves.png"));
        graphics.drawImage(joinha, -80, newHeight - 300, null);

        // Criando o outline

        printText(width, newHeight, graphics);

        // escrever uma frase na nova imagem
        // FontMetrics metrics = graphics.getFontMetrics();
        // graphics.drawString("TOPZERA", (width - metrics.stringWidth("TOPZERA")) / 2,
        // newHeight - 100);
        // int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;

        // escrever a nova imagem em um arquivo
        ImageIO.write(newImage, "png", new File("output/" + fileName));

    }

    private void printText(int width, int newHeight, Graphics2D graphics) {

        String text = "TOPZERA";
        var font = new Font("Impact", Font.BOLD, newHeight / 18); // 1200px height - 66 px

        Color outlineColor = Color.black;
        Color fillColor = Color.white;
        BasicStroke outlineStroke = new BasicStroke(newHeight / 80); //// 1200px height - 15 px

        Graphics2D g2 = (Graphics2D) graphics;

        // remember original settings
        Color originalColor = g2.getColor();
        Stroke originalStroke = g2.getStroke();
        RenderingHints originalHints = g2.getRenderingHints();

        // create a glyph vector from your text
        GlyphVector glyphVector = font.createGlyphVector(g2.getFontRenderContext(), text);
        // get the shape object
        Shape textShape = glyphVector.getOutline();

        // activate anti aliasing for text rendering (if you want it to look nice)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2.setColor(outlineColor);
        g2.setStroke(outlineStroke);
        g2.translate((width - textShape.getBounds2D().getWidth()) / 2, newHeight - newHeight / 15);
        g2.draw(textShape); // draw outline

        g2.setColor(fillColor);
        g2.fill(textShape); // fill the shape

        // reset to original settings after painting
        g2.setColor(originalColor);
        g2.setStroke(originalStroke);
        g2.setRenderingHints(originalHints);
    }
}

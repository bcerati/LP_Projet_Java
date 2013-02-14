package general_views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private Image image;

	public Panel(String imageLink, int width, int height) {
		try {
			image = ImageIO.read(this.getClass().getClassLoader().getResource("images/" + imageLink));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(width,height));
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		//write(g2d, "Boris CERATI;175000", 30);
	}
	
	/*public void write(Graphics2D g2d, String str, int top) {
		System.out.println();
		URL fontUrl = null;
		try {
			fontUrl = new URL(this.getClass().getResource("../fonts/TheKingsoftheHouse-Regular.ttf") + "");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	        Font font = null;
			try {
				font = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream());
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        font = font.deriveFont(Font.PLAIN, 20);
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(font);

	        String strings[] = str.split(";");
	        int stringHeight = (int)g2d.getFontMetrics().getStringBounds(str, g2d).getHeight();
	        int string1Width = (int)g2d.getFontMetrics().getStringBounds(strings[1], g2d).getWidth();

		//Font font = new Font("Arial", Font.BOLD, 15);
		g2d.setFont(font);
		g2d.setColor(Color.RED);
		g2d.drawString(strings[0], 100, stringHeight + 14);
		g2d.setColor(Color.BLUE);
		g2d.drawString(strings[1], 100, 2*stringHeight + 28);
	}*/
}
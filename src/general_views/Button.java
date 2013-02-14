package general_views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class Button extends JButton{

	private Image image;

	public Button(String imageLink, int width, int height) {
		try {
			image = ImageIO.read(this.getClass().getClassLoader().getResource("images/" + imageLink));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setPreferredSize(new Dimension(width,height));
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		g2d.setColor(Color.black);
	}
}
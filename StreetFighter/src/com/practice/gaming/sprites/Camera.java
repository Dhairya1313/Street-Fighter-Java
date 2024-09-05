package com.practice.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.practice.gaming.canvas.Board;

public class Camera extends Sprite{

	public Camera() {
		x = 0;
		y = 180;
		w = 1400;
		h = 900;
		try {
			image = ImageIO.read(Board.class.getResource("bg.jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public BufferedImage defaultImage() {
		// TODO Auto-generated method stub
		BufferedImage subImage = image.getSubimage(x, y, w, h);
		return subImage;
	}

}

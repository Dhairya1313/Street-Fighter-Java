package com.practice.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RyuPlayer extends Sprite {
	private BufferedImage walkImages[] = new BufferedImage[6];
	private BufferedImage standingImages[] = new BufferedImage[6];
	private BufferedImage punchImages[] = new BufferedImage[6];
	private BufferedImage kickImages[] = new BufferedImage[6];
	public RyuPlayer() throws IOException {
		x = 200;
		y = FLOOR - h;
		speed = 0;
		currentMove = STANDING;
		image = ImageIO.read(RyuPlayer.class.getResource(RYU_IMAGE));
		loadWalkImages();
		loadStandingImages();
		loadKickImages();
		loadPunchImages();
	}
	
	public void jump() {
		if(!isJump)
		force = DEFAULT_FORCE;
		y += force;
		isJump = true;
	}
	public void fall() {
		force = force + GRAVITY;
		if(y>=FLOOR - h) {
			isJump = false;
			return;
		}
		y += force;
		 
	}
	
	private void loadWalkImages() {
		walkImages[0] = image.getSubimage(49, 9, 44, 86);
		walkImages[1] = image.getSubimage(99, 9, 44, 86);
		walkImages[2] = image.getSubimage(149, 9, 44, 86);
		walkImages[3] = image.getSubimage(199, 9, 44, 86);
		walkImages[4] = image.getSubimage(249, 9, 44, 86);
		walkImages[5] = image.getSubimage(299, 9, 44, 86);
	}
	private void loadStandingImages() {
		standingImages[0] = image.getSubimage(158, 869, 47, 90);
		standingImages[1] = image.getSubimage(210, 869, 32, 85);
		standingImages[2] = image.getSubimage(257, 869, 37, 87);
		standingImages[3] = image.getSubimage(297, 872, 51, 85);
		standingImages[4] = image.getSubimage(345, 868, 40, 88);
		standingImages[5] = image.getSubimage(392, 870, 48, 84);
	}
	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(3, 130, 44, 78);
		punchImages[1] = image.getSubimage(48, 123, 62, 87);
		punchImages[2] = image.getSubimage(108, 129, 51, 83);
		punchImages[3] = image.getSubimage(167, 129, 48, 87);
		punchImages[4] = image.getSubimage(220, 127, 50, 85);
		punchImages[5] = image.getSubimage(277, 126, 68, 89);
	}
	private void loadKickImages() {
		kickImages[0] = image.getSubimage(0, 254, 48, 91);
		kickImages[1] = image.getSubimage(56, 253, 72, 91);
		kickImages[2] = image.getSubimage(128, 251, 47, 93);
		
	}
	
	private BufferedImage walkImage() {
		if(imageIndex>5) {
			imageIndex=0;
			currentMove = STANDING;
		}
		BufferedImage img = walkImages[imageIndex];
		imageIndex++;
		return img;
	}
	
	private BufferedImage standingImage() {
		if(imageIndex>5) {
			imageIndex=0;
		}
		BufferedImage img = standingImages[imageIndex];
		imageIndex++;
		return img;
	}
	private BufferedImage punchImage() {
		if(imageIndex>3)
			isAttacking = false;
		if(imageIndex>5) {
			imageIndex=0;
			currentMove = STANDING;
			
		}
		BufferedImage img = punchImages[imageIndex];
		imageIndex++;
		return img;
	}
	private BufferedImage kickImage() {
		if(imageIndex>3) {
			imageIndex=0;
			currentMove = STANDING;
			isAttacking = false;
		}
		BufferedImage img = kickImages[imageIndex];
		imageIndex++;
		return img;
	}
	
	@Override
	public BufferedImage defaultImage() {
		if(currentMove == WALK) {
			return walkImage();
		}
		else if(currentMove == PUNCH) {
			return punchImage();
		}
		else if(currentMove == KICK) {
			return kickImage();
		}
		return standingImage();
		
	}	
}
package com.practice.gaming.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class KenPlayer extends Sprite{
	private BufferedImage walkImages[] = new BufferedImage[6];
	private BufferedImage standingImages[] = new BufferedImage[6];
	private BufferedImage punchImages[] = new BufferedImage[6];
	private BufferedImage kickImages[] = new BufferedImage[6];
	public KenPlayer() throws IOException {
	x = GWIDTH - 400;
	y = FLOOR - h;
	speed = 0;
	currentMove = STANDING;
	image = ImageIO.read(KenPlayer.class.getResource(KEN_IMAGE));
	loadWalkImages();
	loadStandingImages();
	loadPunchImages();
	loadKickImages();
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
	walkImages[0] = image.getSubimage(0, 132, 105, 110);
	walkImages[1] = image.getSubimage(100, 132, 105, 110);
	walkImages[2] = image.getSubimage(200, 132, 94, 110);
	walkImages[3] = image.getSubimage(292, 132, 84, 110);
	walkImages[4] = image.getSubimage(384, 130, 80, 115);
	walkImages[5] = image.getSubimage(473, 132, 71, 118);
}
private void loadStandingImages() {
	standingImages[0] = image.getSubimage(0, 0, 98, 110);
	standingImages[1] = image.getSubimage(100, 0, 98, 110);
	standingImages[2] = image.getSubimage(200, 0, 98, 110);
	standingImages[3] = image.getSubimage(300, 0, 98, 110);
	standingImages[4] = image.getSubimage(400, 0, 98, 110);
	standingImages[5] = image.getSubimage(500, 0, 98, 110);
}
private void loadPunchImages() {
	punchImages[0] = image.getSubimage(3, 273, 118, 115);
	punchImages[1] = image.getSubimage(122, 276, 116, 114);
	punchImages[2] = image.getSubimage(243, 276, 119, 115);
	punchImages[3] = image.getSubimage(365, 277, 157, 117);
	punchImages[4] = image.getSubimage(520, 275, 144, 117);
	punchImages[5] = image.getSubimage(668, 273, 147, 116);
}
private void loadKickImages() {
	kickImages[0] = image.getSubimage(8, 520, 108, 110);
	kickImages[1] = image.getSubimage(117, 514, 95, 121);
	kickImages[2] = image.getSubimage(226, 532, 95, 104);
	kickImages[3] = image.getSubimage(324, 534, 100, 102);
	kickImages[4] = image.getSubimage(436, 544, 100, 94);
	kickImages[5] = image.getSubimage(548, 547, 96, 96);
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
	if(imageIndex>5) {
		imageIndex=0;
		currentMove = STANDING;
	}
	BufferedImage img = punchImages[imageIndex];
	imageIndex++;
	return img;
}
private BufferedImage kickImage() {
	if(imageIndex>5) {
		imageIndex=0;
		currentMove = STANDING;
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
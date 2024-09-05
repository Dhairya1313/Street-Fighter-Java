package com.practice.gaming.canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.practice.gaming.sprites.Camera;
import com.practice.gaming.sprites.KenPlayer;
import com.practice.gaming.sprites.Power;
import com.practice.gaming.sprites.RyuPlayer;
import com.practice.gaming.utility.GameConstants;
import com.practice.gaming.utility.PlayerConstants;

public class Board extends JPanel implements GameConstants, PlayerConstants{
	BufferedImage imageBg;
	private RyuPlayer ryuPlayer;
	private KenPlayer kenPlayer;
	private Timer timer;
	private Power ryuPower;
	private Power kenPower;
	private boolean isGameOver;
	private Camera camera;

	private void loadPower() {
		ryuPower = new Power(80,"Daksh".toUpperCase());
		kenPower = new Power(GWIDTH/2+80,"Ajay".toUpperCase());
	}
	
	private void paintPower(Graphics pen) {
		ryuPower.printBox(pen);
		kenPower.printBox(pen);
		
	}

	public Board() throws IOException{
		camera = new Camera();
		//loadBackgroundImage();
		ryuPlayer = new RyuPlayer();
		kenPlayer = new KenPlayer();
		setFocusable(true);
		bindEvents();
		gameLoop();
		loadPower();
	}
	
	public void collision() {
		if(isCollide()) {
			if(ryuPlayer.isAttacking()) {
				kenPlayer.setCurrentMove(DAMAGE);
				kenPower.setHealth();
			}
			ryuPlayer.setCollide(true);
			ryuPlayer.setSpeed(0);
			//kenPlayer.setCollide(true);
			//kenPlayer.setSpeed(0);
			
			if(kenPlayer.isAttacking()) {
				ryuPlayer.setCurrentMove(DAMAGE);
				ryuPower.setHealth();
			}
			if(kenPower.getHealth()<=0||ryuPower.getHealth()<=0) {
				isGameOver = true;
			}
		}
		
		else {
			//kenPlayer.setSpeed(SPEED);
			ryuPlayer.setSpeed(SPEED);
		}
	}
	private void printMessage(Graphics pen) {
		pen.setColor(Color.red);
		pen.setFont(new Font("times",Font.BOLD, 80));
		pen.drawString("GAME OVER", GWIDTH/2 - 230, GHEIGHT/2);
	}
	
	private boolean isCollide() {
		int xDistance = Math.abs(ryuPlayer.getX() - kenPlayer.getX());
		int yDistance = Math.abs(ryuPlayer.getY() - kenPlayer.getY());
		return xDistance <= ryuPlayer.getW()-40 && yDistance<=ryuPlayer.getH(); 
	}
	
	private void gameLoop() {
		timer = new Timer(80, (ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
				ryuPlayer.fall();
				kenPlayer.fall();
				collision();
			}
			
		});
		timer.start();
	}
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				ryuPlayer.setSpeed(0);		
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					ryuPlayer.setSpeed(0);
					ryuPlayer.setSpeed(-SPEED);
					ryuPlayer.setCurrentMove(WALK);
					if(ryuPlayer.getX()>0)
					ryuPlayer.move();
					ryuPlayer.setCollide(false);
					//repaint();
				}
				else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					if(ryuPlayer.isCollide()) {
						ryuPlayer.setSpeed(0);
					}
					else {
						ryuPlayer.setCollide(false);
						ryuPlayer.setSpeed(SPEED);
					}
					ryuPlayer.setCurrentMove(WALK);
					if(ryuPlayer.getX()<=GWIDTH-ryuPlayer.getW())
					ryuPlayer.move();
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_K){
					ryuPlayer.setAttacking(true);
					ryuPlayer.setCurrentMove(KICK);
				}
				else if(e.getKeyCode() == KeyEvent.VK_P){
					ryuPlayer.setAttacking(true);
					ryuPlayer.setCurrentMove(PUNCH);
				}
				else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					ryuPlayer.jump();
					ryuPlayer.setCurrentMove(JUMP);
				}
				else if(e.getKeyCode() == KeyEvent.VK_J) {
					kenPlayer.setSpeed(-SPEED);
					kenPlayer.setCurrentMove(WALK);
					if(kenPlayer.getX()>0)
						kenPlayer.move();
					//repaint();
				}
				else if(e.getKeyCode()==KeyEvent.VK_L) {
					kenPlayer.setSpeed(SPEED);
					kenPlayer.setCurrentMove(WALK);
					if(kenPlayer.getX()<=GWIDTH-kenPlayer.getW())
					kenPlayer.move();
					//repaint();
				}
				else if(e.getKeyCode() == KeyEvent.VK_E){
					kenPlayer.setCurrentMove(KICK);
				}
				else if(e.getKeyCode() == KeyEvent.VK_R){
					kenPlayer.setCurrentMove(PUNCH);
				}
				else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					kenPlayer.jump();
					kenPlayer.setCurrentMove(JUMP);
				}
			}

		});
	}
	
	@Override
	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		printBackgroundImage(pen);
		ryuPlayer.printPlayer(pen);
		kenPlayer.printPlayer(pen);
		paintPower(pen);
		if(isGameOver) {
			printMessage(pen);
			timer.stop();
		}
		
	}
	private void printBackgroundImage(Graphics pen) {
		pen.drawImage(camera.defaultImage(), 0,0, 1400,900,null);
	}
	
	
	private void loadBackgroundImage() {
		try {
			imageBg = ImageIO.read(Board.class.getResource("bg.jpg"));
		}
		catch(Exception ex) {
			System.out.println("Background Image Couldn't Load...");
			System.exit(0);
		}
	}
}

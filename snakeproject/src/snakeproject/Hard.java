package snakeproject;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Hard extends JPanel implements KeyListener, ActionListener {
	
	private int[] snakexlength= new int[750];
	private int[] snakeylength= new int[750];
	int enemyxpos[]= new int[34];
	int enemyypos[]= new int[23];
	
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	private boolean bonusvisible=false;
	private boolean timevisible=false;
	private boolean enemyvisible=false;
	private boolean over=false;
	
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon leftmouth;
	private ImageIcon foodimage;
	private ImageIcon snakeimage;
	private ImageIcon enemyimage;
	private ImageIcon titleImage;
	
	int time=100;
	private Timer timer;
	
	private Random random= new Random();
	
	private int xpos=random.nextInt(34);
	private int ypos=random.nextInt(23);
	private int bonxpos=random.nextInt(34);
	private int bonypos= random.nextInt(23);
	private int score=0;
	private int xblock=0;
	private int yblock=0;
	private int lengthofsnake=3;
	private int moves=0;
	private int delay=100;
	
	SnakeHome homepage;
	public Hard(SnakeHome obj1) {
		homepage=obj1;
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer =new Timer(delay, this);
		timer.start();	
		for(int i=0;i<34;i++) {
			enemyxpos[i]=25+25*i;
		}
		for(int i=0;i<23;i++) {
			enemyypos[i]=75+25*i;
		}
	}
	
	public void paint(Graphics g) {
		
		if(moves==0) {
			snakexlength[2]=50;
			snakexlength[1]=75;
			snakexlength[0]=100;
			
			snakeylength[2]=100;
			snakeylength[1]=100;
			snakeylength[0]=100;
		}
		// draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 852, 55);
		
		// draw the title image
		titleImage= new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for gameplay
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 852, 577);
		
		//draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		// draw blocks
		g.setColor(Color.cyan);
		g.fillRect(175, 75, 25, 500);
		g.fillRect(450, 75, 25, 575);
		g.fillRect(725, 150, 25, 500);
		
		//draw scores
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN,14));
		g.drawString("Scores: "+score, 780, 30);
		
		//draw length
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN,14));
		g.drawString("Length: "+lengthofsnake, 780, 50);
		
		rightmouth= new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int a=0; a< lengthofsnake;a++) {
			
			if(a==0 && right) {
				rightmouth= new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && left) {
				leftmouth= new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && down) {
				downmouth= new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && up) {
				upmouth= new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}	
			if(a!=0) {
				snakeimage= new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
		}
		enemyimage= new ImageIcon("enemy.png");
		//draw bonus and enemy
				if(lengthofsnake%9==0) {
					timevisible=true;
					bonusvisible=true;
				}
				if(lengthofsnake%12==0) {
					timevisible=true;
					enemyvisible=true;
				}
				if(lengthofsnake%9==0 && lengthofsnake%12==0) {
					bonusvisible=false;
					timevisible=true;
					enemyvisible=true;
				}
				if(timevisible==true){
					g.setColor(Color.white);
					g.setFont(new Font("arial", Font.PLAIN,16));
				    g.drawString("Time Left: ",50,36);
				    g.setColor(Color.GREEN);
				    g.drawRect(130, 20, 100, 20);
				    g.fill3DRect(130, 20, time-=1, 20,false);
				     if(time==0){
				    	 bonusvisible=false;
				    	 enemyvisible=false;
				    	 timevisible=false;
				    	 time=100;
				    }
				}
				if(bonusvisible==true) {
					g.setColor(Color.yellow);
					g.fillOval(enemyxpos[bonxpos], enemyypos[bonypos], 25, 25);		
				}
				if(enemyvisible==true) {
					enemyimage.paintIcon(this, g, enemyxpos[bonxpos], enemyypos[bonypos]);
				}
		foodimage= new ImageIcon("food.png");
		
		if(enemyxpos[xpos]==snakexlength[0] && enemyypos[ypos]==snakeylength[0]) {
			score++;
			lengthofsnake++;
			xpos= random.nextInt(34);
			ypos= random.nextInt(23);
		}
		if(enemyxpos[bonxpos]==snakexlength[0] && enemyypos[bonypos]==snakeylength[0] && bonusvisible==true) {
			score+=time*2;
			lengthofsnake++;
			bonxpos= random.nextInt(34);
			bonypos= random.nextInt(23);
			bonusvisible=false;
			timevisible=false;
			time=100;
		}
		if(enemyxpos[bonxpos]==snakexlength[0] && enemyypos[bonypos]==snakeylength[0] && enemyvisible==true) {
			over=true;
			timevisible=false;
			enemyvisible=false;
			time=100;
		}
		
	for(int j=0;j<20;j++) {
	 if((snakexlength[0]==175 && snakeylength[0]==75+25*j)||(snakexlength[0]==450)||(snakexlength[0]==725 && snakeylength[0]==150+25*j)){
	   	over= true;
	 }
	 if((enemyxpos[xpos]==175 && enemyypos[ypos]==75+25*j)||(enemyxpos[xpos]==450)||(enemyxpos[xpos]==725 && enemyypos[ypos]==150+25*j)){
	   	xpos=random.nextInt(34);
		ypos=random.nextInt(23);
	 }
	 if((enemyxpos[bonxpos]==175 && enemyypos[bonypos]==75+25*j)||(enemyxpos[bonxpos]==450)||(enemyxpos[bonxpos]==725 && enemyypos[bonypos]==150+25*j)){
		 bonxpos=random.nextInt(34);
		 bonypos=random.nextInt(23);
		 }
	}
	
	for(int b=1;b<lengthofsnake;b++) {
		if(snakexlength[b]==snakexlength[0] && snakeylength[b]==snakeylength[0]) {
			
			over=true;
			}
		if(snakexlength[b]==enemyxpos[xpos] && snakeylength[b]==enemyypos[ypos]) {
			
			xpos=random.nextInt(34);
			ypos=random.nextInt(23);
			}
		if(snakexlength[b]==enemyxpos[bonxpos] && snakeylength[b]==enemyypos[bonypos]) {
			
			bonxpos=random.nextInt(34);
			bonypos=random.nextInt(23);
			}
		}
		
		foodimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		if(over==true) {
			right=false;
			left=false;
			up=false;
			down=false;
			over=false;
			time=100;
			bonusvisible=false;
			enemyvisible=false;
			timevisible=false;
			g.setColor(Color.yellow);
			g.fillRect(240, 220, 500, 230);
			g.setColor(Color.red);
			g.setFont(new Font("arial", Font.BOLD, 70));
			g.drawString("Game Over", 300, 300);
			
			g.setColor(Color.magenta);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("Your Score is "+score, 380, 344);
			
			g.setColor(Color.blue);
			g.setFont(new Font("arial", Font.BOLD, 25));
			g.drawString("Press ENTER to RESTART", 335, 380);
			
			g.setFont(new Font("arial", Font.BOLD, 25));
			g.drawString("Press SPACE to go to HOMEPAGE", 290, 415);
		}
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();	
		if(right) {
			for(int r=lengthofsnake-1;r>=0;r--) {
				snakeylength[r+1]=snakeylength[r];
			}
			for(int r=lengthofsnake;r>=0;r--) {
				if(r==0) {
					snakexlength[r]=snakexlength[r]+25;
				}
				else {
					snakexlength[r]=snakexlength[r-1];
				}
				if(snakexlength[r]>850) {
					snakexlength[r]=25;
				}
			}		
			repaint();
		}
		if(left) {
			for(int r=lengthofsnake-1;r>=0;r--) {
				snakeylength[r+1]=snakeylength[r];
			}
			for(int r=lengthofsnake;r>=0;r--) {
				if(r==0) {
					snakexlength[r]=snakexlength[r]-25;
				}
				else {
					snakexlength[r]=snakexlength[r-1];
				}
				if(snakexlength[r]<25) {
					snakexlength[r]=850;
				}
			}
			repaint();
		}
		if(up) {
			for(int r=lengthofsnake-1;r>=0;r--) {
				snakexlength[r+1]=snakexlength[r];
			}
			for(int r=lengthofsnake;r>=0;r--) {
				if(r==0) {
					snakeylength[r]=snakeylength[r]-25;
				}
				else {
					snakeylength[r]=snakeylength[r-1];
				}
				if(snakeylength[r]<75) {
					snakeylength[r]=625;
				}
			}
			repaint();
		}
		if(down) {
			for(int r=lengthofsnake-1;r>=0;r--) {
				snakexlength[r+1]=snakexlength[r];
			}
			for(int r=lengthofsnake;r>=0;r--) {
				if(r==0) {
					snakeylength[r]=snakeylength[r]+25;
				}
				else {
					snakeylength[r]=snakeylength[r-1];
				}
				if(snakeylength[r]>625) {
					snakeylength[r]=75;
				}
			}
			repaint();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			moves=0;
			score=0;
			lengthofsnake=3;
			timevisible=false;
			enemyvisible=false;
			bonusvisible=false;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			//repaint();
			homepage.a.dispose();
			homepage.obj.dispose();
			homepage.obj.setVisible(true);
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			moves++;
			right=true;
			if(!left) {
				right=true;
			}
			else {
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			moves++;
			left=true;
			if(!right) {
				left=true;
			}
			else {
				left=false;
				right=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			moves++;
			up=true;
			if(!down) {
				up=true;
			}
			else {
				up=false;
				down=true;
			}
			left=false;
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			moves++;
			down=true;
			if(!up) {
				down=true;
			}
			else {
				up=true;
				down=false;
			}
			left=false;
			right=false;
		}	
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}
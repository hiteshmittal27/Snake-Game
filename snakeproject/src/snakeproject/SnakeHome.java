package snakeproject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Painter extends JComponent{
	ImageIcon homeimage;
	public void paint(Graphics g) {
		
		//draw instructions
		g.setColor(Color.orange);
		g.fillRect(580, 160, 300, 50);
		g.setColor(Color.pink);
		g.fillRect(580, 210, 300, 270);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD,18));
		g.drawString("INSTRUCTIONS", 655, 190);
		g.setColor(Color.blue);
		g.setFont(new Font("arial", Font.PLAIN,15));
		g.drawString("1. Use Arrows Keys to move the Snake", 590, 250);
		g.setFont(new Font("arial", Font.PLAIN,15));
		g.drawString("2. Eat the Food to increase your Score", 590, 290);
		g.setFont(new Font("arial", Font.PLAIN,15));
		g.drawString("3. Take the Bonus to maximize your Score", 590, 330);
		g.setFont(new Font("arial", Font.PLAIN,15));
		g.drawString("4. Don't touch the Snake with its Body", 590, 370);
		g.setFont(new Font("arial", Font.PLAIN,15));
		g.drawString("5. Don't made the Snake to hit the Blocks", 590, 410);
		g.setFont(new Font("arial", Font.PLAIN,15));
		g.drawString("6. Be carefull with the Snake's Enemy", 590, 450);
		
		//draw image
		homeimage= new ImageIcon("homesnake.png");
		homeimage.paintIcon(this, g, 13, 365);
		
		//draw made by
		g.setColor(Color.blue);
		g.fillRect(3, 565, 883, 90);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN,15));
		g.drawString("Made By: Hitesh Mittal", 350, 590);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN,15));
		g.drawString("Class- B.TECH CSE 3A", 348, 615);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN,15));
		g.drawString("Roll No.- 2K19CSUN01022", 338, 640);
		
		//draw difficulty
		g.setColor(Color.yellow);
		g.setFont(new Font("COOPER BLACK", Font.BOLD,32));
		g.drawString("SELECT DIFFICULTY LEVEL", 50, 200);	
	}
}

public class SnakeHome extends JFrame implements KeyListener, ActionListener {
	static JFrame obj;
	JFrame a = new JFrame();
	static JButton easybtn = new JButton("EASY ");
	static JButton mediumbtn = new JButton("MEDIUM");
	static JButton hardbtn = new JButton("HARD");
	static JLabel label1 = new JLabel("Snake Game");
	public SnakeHome() {
		
		getContentPane().setBackground(Color.BLACK);
		easybtn.setBackground(Color.green);
		easybtn.setFont(new Font("elephant", Font.PLAIN, 18));
		easybtn.setBounds(50,250, 135, 90);
		easybtn.addActionListener(this);
		mediumbtn.setBackground(Color.orange);
		mediumbtn.setFont(new Font("elephant", Font.PLAIN, 18));
		mediumbtn.setBounds(225, 250, 135, 90);
		mediumbtn.addActionListener(this);
		hardbtn.setBackground(Color.red);
		hardbtn.setFont(new Font("elephant", Font.PLAIN, 18));
		hardbtn.setBounds(400, 250, 135, 90);
		hardbtn.addActionListener(this);
		label1.setForeground(Color.cyan);
		label1.setFont(new Font("algerian",Font.BOLD, 110));
		label1.setBounds(84,25, 2000, 100);
	}
	
	public static void main(String[] args) {
		obj = new SnakeHome();
		obj.setBackground(Color.BLACK);
		obj.setBounds(10,10,905,700);
		obj.setTitle("Snake Game");		
		obj.setResizable(false);
		obj.getContentPane().add(new Painter());
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		obj.getContentPane().setLayout(null);
		obj.getContentPane().add(label1);
		obj.getContentPane().add(easybtn);
		obj.getContentPane().add(mediumbtn);
		obj.getContentPane().add(hardbtn);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.obj.dispose();
		a = new JFrame();
		if(e.getSource()==easybtn) {

			Easy esy = new Easy(this);
			this.a.setBounds(10, 10, 905, 700);
			this.a.setTitle("Easy Level");		
			this.a.setResizable(false);
			this.a.setVisible(true);
			this.a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.a.getContentPane().add(esy);
		}
		else if(e.getSource()==mediumbtn) {

			Medium mdm = new Medium(this);
			this.a.setBounds(10, 10, 905, 700);
			this.a.setTitle("Medium Level");		
			this.a.setResizable(false);
			this.a.setVisible(true);
			this.a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.a.getContentPane().add(mdm);
		}
		else if(e.getSource()==hardbtn) {
			
			Hard hrd = new Hard(this);
			this.a.setBounds(10, 10, 905, 700);
			this.a.setTitle("Hard Level");		
			this.a.setResizable(false);
			this.a.setVisible(true);
			this.a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.a.getContentPane().add(hrd);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
}
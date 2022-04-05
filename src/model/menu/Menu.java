package model.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Game;
import model.Sound;

public class Menu extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JButton startBtn = new JButton("START");
	JButton exitBtn = new JButton("EXIT");

	Sound sound = new Sound();
	public Menu() {
		playMusic(0);
		this.setTitle("CHESS GAME"); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 400, 1148, 688);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setResizable(false);

		//===== Start Button =====
		startBtn.setBounds(470, 200, 200, 80);
		startBtn.setFont(new Font("san serif", Font.BOLD, 36));
		startBtn.setBackground(new Color(217, 217, 217));
		startBtn.setForeground(new Color(194, 0, 0));;
		startBtn.setBorder(null);
		startBtn.setFocusPainted(false);
		//===== Exit Button =====
		exitBtn.setBounds(470, 330, 200, 80);
		exitBtn.setFont(new Font("san serif", Font.BOLD, 36));
		exitBtn.setBackground(new Color(217, 217, 217));
		exitBtn.setForeground(new Color(194, 0, 0));
		exitBtn.setBorder(null);
		exitBtn.setFocusPainted(false);

		try {
			JLabel bg = new JLabel(new ImageIcon(ImageIO.read(new File("img\\bg1.jpg"))));
			this.add(bg);
			bg.add(startBtn, BorderLayout.CENTER);
			bg.add(exitBtn, BorderLayout.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}

		startBtn.addActionListener(this);
		startBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt)  {
				startBtn.setBackground(new Color(194, 0, 0));
				startBtn.setForeground(Color.white);
			}
			public void mouseExited(MouseEvent evt) {
				startBtn.setBackground(new Color(217, 217, 217));
				startBtn.setForeground(new Color(194, 0, 0));
			}
			public void mousePressed(MouseEvent evt) {
				startBtn.setBackground(new Color(217, 217, 217));
				startBtn.setForeground(new Color(194, 0, 0));
			}
		});
		exitBtn.addActionListener(this);
		exitBtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) 
			{
				exitBtn.setBackground(new Color(194, 0, 0));
				exitBtn.setForeground(Color.white);
			}
			public void mouseExited(MouseEvent evt) 
			{
				exitBtn.setBackground(new Color(217, 217, 217));
				exitBtn.setForeground(new Color(194, 0, 0));
			}
			public void mousePressed(MouseEvent evt) {
				exitBtn.setBackground(new Color(217, 217, 217));
				exitBtn.setForeground(new Color(194, 0, 0));
			}
		});
		this.setVisible(true);

	}
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	public void stopMusic() {
		sound.stop();
	}
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startBtn) {
			Game game = new Game();
			this.setVisible(false);
			stopMusic();
		}
		if(e.getSource() == exitBtn)
			System.exit(0);
	}

}

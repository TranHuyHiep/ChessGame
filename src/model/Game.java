package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import model.spot.Spot;
import model.spot.piece.Bishop;
import model.spot.piece.King;
import model.spot.piece.Knight;
import model.spot.piece.Pawn;
import model.spot.piece.Queen;
import model.spot.piece.Rook;

public class Game implements ActionListener {
	private static List<Spot> bspots = new ArrayList<Spot>();
	private static List<Spot> wspots = new ArrayList<Spot>();
	private Spot selectedSpot = null;
	private static boolean isContinue = true;
	private static boolean isEnd = false;
	private static boolean isTurn = true;
	JFrame frame = new JFrame();
	// ===Time_setting===
	JLabel timeLabel1 = new JLabel();
	JLabel timeLabel2 = new JLabel();
	int fseconds = 0;
	int fminutes = 15;
	int sseconds = 0;
	int sminutes = 15;
	String seconds_string1 = String.format("%02d", fseconds);
	String minutes_string1 = String.format("%02d", fminutes);
	String seconds_string2 = String.format("%02d", sseconds);
	String minutes_string2 = String.format("%02d", sminutes);
	JButton Restart = new JButton("Restart");
	JButton Pause = new JButton("Pause");
	JButton Exit = new JButton("EXIT");
	JTextField white_name = new JTextField();
	JTextField black_name = new JTextField();
	public static JTextArea step = new JTextArea(5, 10);
	public static JScrollPane jsp;
	Sound sound = new Sound();

	public Game() {
		playMusic(1);

		Rook brook = new Rook(0, 0, false, false, "rook");
		bspots.add(brook);
		Knight bkinght = new Knight(1, 0, false, false, "knight");
		bspots.add(bkinght);
		Bishop bbishop = new Bishop(2, 0, false, false, "bishop");
		bspots.add(bbishop);
		Queen bqueen = new Queen(3, 0, false, false, "queen");
		bspots.add(bqueen);
		King bking = new King(4, 0, false, false, "king");
		bspots.add(bking);
		Bishop bbishop2 = new Bishop(5, 0, false, false, "bishop");
		bspots.add(bbishop2);
		Knight bknight2 = new Knight(6, 0, false, false, "knight");
		bspots.add(bknight2);
		Rook brook2 = new Rook(7, 0, false, false, "rook");
		bspots.add(brook2);
		Pawn bpawn1 = new Pawn(1, 1, false, false, "pawn");
		bspots.add(bpawn1);
		Pawn bpawn2 = new Pawn(2, 1, false, false, "pawn");
		bspots.add(bpawn2);
		Pawn bpawn3 = new Pawn(3, 1, false, false, "pawn");
		bspots.add(bpawn3);
		Pawn bpawn4 = new Pawn(4, 1, false, false, "pawn");
		bspots.add(bpawn4);
		Pawn bpawn5 = new Pawn(5, 1, false, false, "pawn");
		bspots.add(bpawn5);
		Pawn bpawn6 = new Pawn(6, 1, false, false, "pawn");
		bspots.add(bpawn6);
		Pawn bpawn7 = new Pawn(7, 1, false, false, "pawn");
		bspots.add(bpawn7);
		Pawn bpawn8 = new Pawn(0, 1, false, false, "pawn");
		bspots.add(bpawn8);

		Rook wrook = new Rook(0, 7, true, false, "rook");
		wspots.add(wrook);
		Knight wknight = new Knight(1, 7, true, false, "knight");
		wspots.add(wknight);
		Bishop wbishop = new Bishop(2, 7, true, false, "bishop");
		wspots.add(wbishop);
		Queen wqueen = new Queen(3, 7, true, false, "queen");
		wspots.add(wqueen);
		King wking = new King(4, 7, true, false, "king");
		wspots.add(wking);
		Bishop wbishop2 = new Bishop(5, 7, true, false, "bishop");
		wspots.add(wbishop2);
		Knight wknight2 = new Knight(6, 7, true, false, "knight");
		wspots.add(wknight2);
		Rook wrook2 = new Rook(7, 7, true, false, "rook");
		wspots.add(wrook2);
		Pawn wpawn1 = new Pawn(1, 6, true, false, "pawn");
		wspots.add(wpawn1);
		Pawn wpawn2 = new Pawn(2, 6, true, false, "pawn");
		wspots.add(wpawn2);
		Pawn wpawn3 = new Pawn(3, 6, true, false, "pawn");
		wspots.add(wpawn3);
		Pawn wpawn4 = new Pawn(4, 6, true, false, "pawn");
		wspots.add(wpawn4);
		Pawn wpawn5 = new Pawn(5, 6, true, false, "pawn");
		wspots.add(wpawn5);
		Pawn wpawn6 = new Pawn(6, 6, true, false, "pawn");
		wspots.add(wpawn6);
		Pawn wpawn7 = new Pawn(7, 6, true, false, "pawn");
		wspots.add(wpawn7);
		Pawn wpawn8 = new Pawn(0, 6, true, false, "pawn");
		wspots.add(wpawn8);

		BufferedImage all = null;
		try {
			all = ImageIO.read(new File("img\\chess.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image imgs[] = new Image[12];
		int ind = 0;
		for (int y = 0; y < 400; y += 200) {
			for (int x = 0; x < 1200; x += 200) {
				imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(81, 81, BufferedImage.SCALE_SMOOTH);
				ind++;
			}
		}
		// board_game_graphic
		JPanel jp1 = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			public void paint(Graphics g) {
				boolean white = true;
				for (int y = 0; y < 8; y++) {
					for (int x = 0; x < 8; x++) {
						if (white)
							g.setColor(new Color(235, 235, 208));
						else
							g.setColor(new Color(119, 148, 85));
						g.fillRect(x * 81, y * 81, 81, 81);
						white = !white;
					}
					white = !white;
				}
				for (Spot p : wspots) {
					if (p.getPiece().isDead() == true)
						continue;
					int id = 0;
					if (p.getPiece().getName().equalsIgnoreCase("king")) {
						id = 0;
					}
					if (p.getPiece().getName().equalsIgnoreCase("queen")) {
						id = 1;
					}
					if (p.getPiece().getName().equalsIgnoreCase("bishop")) {
						id = 2;
					}
					if (p.getPiece().getName().equalsIgnoreCase("knight")) {
						id = 3;
					}
					if (p.getPiece().getName().equalsIgnoreCase("rook")) {
						id = 4;
					}
					if (p.getPiece().getName().equalsIgnoreCase("pawn")) {
						id = 5;
					}
					if (!p.getPiece().isColor()) {
						id += 6;
					}
					g.drawImage(imgs[id], p.getPiece().getPx(), p.getPiece().getPy(), this);
				}
				for (Spot p : bspots) {
					if (p.getPiece().isDead() == true)
						continue;
					int id = 0;
					if (p.getPiece().getName().equalsIgnoreCase("king")) {
						id = 0;
					}
					if (p.getPiece().getName().equalsIgnoreCase("queen")) {
						id = 1;
					}
					if (p.getPiece().getName().equalsIgnoreCase("bishop")) {
						id = 2;
					}
					if (p.getPiece().getName().equalsIgnoreCase("knight")) {
						id = 3;
					}
					if (p.getPiece().getName().equalsIgnoreCase("rook")) {
						id = 4;
					}
					if (p.getPiece().getName().equalsIgnoreCase("pawn")) {
						id = 5;
					}
					if (!p.getPiece().isColor()) {
						id += 6;
					}
					g.drawImage(imgs[id], p.getPiece().getPx(), p.getPiece().getPy(), this);
				}
			}
		};

		// ===mouse_listener===
		frame.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) { 
				if (selectedSpot != null && selectedSpot.getPiece().isColor() != isTurn) {
					selectedSpot = null;
					return;
				}
				if (selectedSpot != null) {

					selectedSpot.getPiece().setPx((e.getX() - 40));
					selectedSpot.getPiece().setPy((e.getY() - 70));
					frame.repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}
		});
		frame.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			// Mouse_press
			@Override
			public void mousePressed(MouseEvent e) { // click
				if (getSpot(e.getX() - 8, e.getY() - 31) != null && Bkinglive() && Wkinglive() && isContinue) {
					selectedSpot = getSpot(e.getX() - 8, e.getY() - 31);
				}
			}

			// Mouse_release
			@Override
			public void mouseReleased(MouseEvent e) { 
				if (selectedSpot != null) {
					if (selectedSpot.move((e.getX() - 8) / 81, (e.getY() - 31) / 81) == true) {
						if (isTurn == true) { // luot quan trang
							stop2();
							start1();
							isTurn = false;
						} else { // luot quan den
							stop1();
							start2();
							isTurn = true;
						}
					}
					selectedSpot.move((e.getX() - 8) / 81, (e.getY() - 31) / 81);
					Iterator<Spot> itr1 = wspots.iterator();
					while (itr1.hasNext()) {
						Spot temp = itr1.next();
						if (temp.getPiece().isDead() == true) {
							itr1.remove();
						}
					}
					Iterator<Spot> itr2 = bspots.iterator();
					while (itr2.hasNext()) {
						Spot temp = itr2.next();
						if (temp.getPiece().isDead() == true) {
							itr2.remove();
						}
					}
					playMusic(2);
					frame.repaint();
				}
				if ("00:00".equals(timeLabel1.getText())) {
					isContinue = false;
					playMusic(4);
					isEnd = true;
					JOptionPane.showMessageDialog(null, "White Won");
					stop1();
					stop2();
				} else if ("00:00".equals(timeLabel2.getText())) {
					isContinue = false;
					playMusic(4);
					isEnd = true;
					JOptionPane.showMessageDialog(null, "Black Won");
					stop1();
					stop2();
				}
				boolean ktBoolean1 = false, ktBoolean2 = false;
				Iterator<Spot> itr1 = wspots.iterator();
				while (itr1.hasNext()) {
					Spot temp = itr1.next();
					if (temp.getPiece().getName() == "king") {
						ktBoolean1 = true;
					}
				}
				if (ktBoolean1 == false) {
					isContinue = false;
					timer1.stop();
					timer2.stop();
					playMusic(4);
					isEnd = true;
					JOptionPane.showMessageDialog(null, "Black Won");
				}
				Iterator<Spot> itr2 = bspots.iterator();
				while (itr2.hasNext()) {
					Spot temp = itr2.next();
					if (temp.getPiece().getName() == "king") {
						ktBoolean2 = true;
					}
				}
				if (ktBoolean2 == false) {
					isContinue = false;
					timer1.stop();
					timer2.stop();
					playMusic(4);
					isEnd = true;
					JOptionPane.showMessageDialog(null, "White Won");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		// ==========set_label===========
		jp1.setSize(648, 648);
		// ==========Time_label_1==========
		timeLabel1.setText(minutes_string1 + ":" + seconds_string1);
		timeLabel1.setBounds(648, 0, 490, 120);
		timeLabel1.setFont(new Font("Verdana", Font.PLAIN, 70));
		timeLabel1.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel1.setOpaque(true);
		timeLabel1.setHorizontalAlignment(JTextField.CENTER);
		timeLabel1.setBackground(new Color(133, 165, 61));
		timeLabel1.setForeground(new Color(255, 244, 192));
		timeLabel1.setBorder(BorderFactory.createLineBorder(Color.black));
		// ==========Time_label_2==========

		timeLabel2.setText(minutes_string2 + ":" + seconds_string2);
		timeLabel2.setBounds(648, 529, 490, 120);
		timeLabel2.setFont(new Font("Verdana", Font.PLAIN, 70));
		timeLabel2.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel2.setOpaque(true);
		timeLabel2.setHorizontalAlignment(JTextField.CENTER);
		timeLabel2.setBackground(new Color(133, 165, 61));
		timeLabel2.setForeground(new Color(255, 244, 192));
		timeLabel2.setBorder(BorderFactory.createLineBorder(Color.black));

		// ===name label===
		white_name.setText("Player 1");
		white_name.setBounds(648, 487, 490, 42);
		white_name.setBackground(Color.WHITE);
		white_name.setBorder(BorderFactory.createBevelBorder(1));
		white_name.setEditable(false);
		white_name.setFont(new Font("Arial", Font.BOLD, 20));
		white_name.setHorizontalAlignment(JTextField.CENTER);
		white_name.setBackground(new Color(204, 195, 153));
		white_name.setBorder(BorderFactory.createLineBorder(Color.black));

		black_name.setText("Player 2");
		black_name.setBounds(648, 120, 490, 42);
		black_name.setBackground(Color.WHITE);
		black_name.setBorder(BorderFactory.createBevelBorder(1));
		black_name.setEditable(false);
		black_name.setFont(new Font("Arial", Font.BOLD, 20));
		black_name.setHorizontalAlignment(JTextField.CENTER);
		black_name.setBackground(new Color(204, 195, 153));
		black_name.setBorder(BorderFactory.createLineBorder(Color.black));

		step.setBounds(648, 162, 248, 325);
		step.setFont(new Font("SansSerif", Font.ITALIC, 20));
		step.setEditable(false);
		step.setBorder(BorderFactory.createLineBorder(Color.black));
		step.setWrapStyleWord(true);
		jsp = new JScrollPane(step, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBounds(648, 162, 248, 325);

		// ===chess_button_setting===
		Pause.setBounds(950, 190, 150, 70);
		Pause.setBackground(new Color(144, 197, 127));
		Pause.setForeground(Color.WHITE);
		Pause.setFont(new Font("Arial", Font.BOLD, 20));
		Exit.setBounds(950, 390, 150, 70);
		Exit.setBackground(new Color(144, 197, 127));
		Exit.setForeground(Color.WHITE);
		Exit.setFont(new Font("Arial", Font.BOLD, 20));
		Restart.setBounds(950, 290, 150, 70);
		Restart.setBackground(new Color(144, 197, 127));
		Restart.setForeground(Color.WHITE);
		Restart.setFont(new Font("Arial", Font.BOLD, 20));

		// ===Frame_add_setting===
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(648 + 500, 648 + 38);
		frame.setTitle("Chess");
		frame.getContentPane().setBackground(new Color(255, 232, 164));
		frame.add(timeLabel1);
		frame.add(timeLabel2);
		frame.add(white_name);
		frame.add(black_name);
		frame.add(jp1);
		frame.add(Pause);
		frame.add(Exit);
		frame.add(Restart);
		frame.add(jsp);

		Restart.addActionListener(this);
		Pause.addActionListener(this);
		Exit.addActionListener(this);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		// ======time_run======
		start2();
	}

	// ==========Time_setting==========
	Timer timer1 = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (fseconds > 0) {
				fseconds = fseconds - 1;
			} else if (fseconds == 0 && fminutes > 0) {
				fminutes = fminutes - 1;
				fseconds = 59;
			}
			seconds_string1 = String.format("%02d", fseconds);
			minutes_string1 = String.format("%02d", fminutes);
			timeLabel1.setText(minutes_string1 + ":" + seconds_string1);

		}

	});
	Timer timer2 = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (sseconds > 0) {
				sseconds = sseconds - 1;
			} else if (sseconds == 0 && sminutes > 0) {
				sminutes = sminutes - 1;
				sseconds = 59;
			}
			seconds_string2 = String.format("%02d", sseconds);
			minutes_string2 = String.format("%02d", sminutes);
			timeLabel2.setText(minutes_string2 + ":" + seconds_string2);
		}

	});

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Pause && isEnd == false) {
			if (isContinue == true) {
				isContinue = false;
				Pause.setText("Countinue");
				if (isTurn == true) {
					timer2.stop();
					JOptionPane.showMessageDialog(null, "Stop! White turn");
				} else {
					timer1.stop();
					JOptionPane.showMessageDialog(null, "Stop! Black turn");
				}
			} else if (isContinue == false) {
				isContinue = true;
				Pause.setText("Pause");
				if (isTurn == true) {
					timer2.start();
				} else {
					timer1.start();
				}
			}
		}

		if (e.getSource() == Restart) {
			bspots = new ArrayList<Spot>();
			wspots = new ArrayList<Spot>();
			step.setText("");
			frame.setVisible(false);
			Game game = new Game();
			Game.isTurn = true;
			Game.isContinue = true;
			Game.isEnd = false;
		}
		if (e.getSource() == Exit) {
			System.exit(0);
		}

	}

	// ==========button==========
	void start1() {
		timer1.start();
	}

	void start2() {
		timer2.start();
	}

	void stop1() {
		timer1.stop();
	}

	void stop2() {
		timer2.stop();
	}

	public static Spot getSpot(int x, int y) {
		int xp = x / 81;
		int yp = y / 81;
		for (Spot p : bspots) {
			if (xp == p.getPiece().getX() && yp == p.getPiece().getY()) {
				return p;
			}
		}
		for (Spot p : wspots) {
			if (xp == p.getPiece().getX() && yp == p.getPiece().getY()) {
				return p;
			}
		}
		return null;
	}

	public boolean Bkinglive() {
		for (Spot p : bspots) {
			if (p.getPiece().getName() == "king") {
				return true;
			}
		}
		return false;
	}

	public boolean Wkinglive() {
		for (Spot p : wspots) {
			if (p.getPiece().getName() == "king") {
				return true;
			}
		}
		return false;
	}

	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
	}

	public void stopMusic() {
		sound.stop();
	}

	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
	public static List<Spot> getWspots() {
		return wspots;
	}

	public static void setWspots(List<Spot> wspots) {
		Game.wspots = wspots;
	}

	public static List<Spot> getBspots() {
		return bspots;
	}

	public static void setBspots(List<Spot> bspots) {
		Game.bspots = bspots;
	}

	public static boolean isContinue() {
		return isContinue;
	}

	public static void setTurn(boolean isTurn) {
		Game.isTurn = isTurn;
	}

	public static void setContinue(boolean isContinue) {
		Game.isContinue = isContinue;
	}
}

package game.main.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.ricm3.game.GameUI;
import game.main.controller.Controller;
import game.main.model.AutomatonProvider;
import game.main.model.Model;
import game.main.model.World;
import game.main.model.entities.*;


public class Launcher {

	Model model;
	JFrame frame;
	
	public Launcher(Model m) {
		this.model=m;
		
		frame= new JFrame();	
		frame.setTitle("Welcome to SpaceCrusader");
		frame.setSize(1024, 768);
		frame.setLocation(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		JLabel titre = new JLabel("Space Crusader");
		titre.setFont(new Font(titre.getFont().getName(), titre.getFont().getStyle(), 40));
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(titre, BorderLayout.NORTH);
		
		JButton newGame = new JButton("Nouvelle Partie");
		newGame.setFont(new Font(newGame.getFont().getName(), newGame.getFont().getStyle(), 30));
		newGame.setPreferredSize(new Dimension(200,70));
		newGame.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  newGame();
			  } 
			} );
		frame.add(newGame, BorderLayout.CENTER);
		JButton option = new JButton("Option");
		option.setFont(new Font(option.getFont().getName(), option.getFont().getStyle(), 30));
		option.setPreferredSize(new Dimension(200,70));
		option.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  options();
			  } 
			} );
		frame.add(option, BorderLayout.EAST);
		JPanel pane2 = new JPanel();
		
		JButton demo1 = new JButton("Demo dungeon");
		demo1.setFont(new Font(demo1.getFont().getName(), demo1.getFont().getStyle(), 20));
		demo1.setPreferredSize(new Dimension(200,70));
		demo1.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  demoSeeDungeon();
			  } 
			});
		pane2.add(demo1);
		
		frame.add(pane2, BorderLayout.SOUTH);
	}
	
	private void newGame() {
		frame.setVisible(false);
		model.initGame();
		View view = new View(model);
	    Controller c = new Controller(model,view);
	    
	    Dimension d = new Dimension(1024, 768);
	    new GameUI(model, view, c, d);
	    view.setHUD();
	}
	
	private void demoSeeDungeon() {
		frame.setVisible(false);
		model.initDemoDisplayDungeon();
		View view = new View(model);
	    Controller c = new Controller(model,view);
	    
	    Dimension d = new Dimension(1024, 768);
	    new GameUI(model, view, c, d);
	    view.setHUD();
	}
	
	private void options() {
		JFrame frame2= new JFrame();	
		frame2.setTitle("Options");
		frame2.setSize(800, 600);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setResizable(false);
		
		String[] listAutomaton = AutomatonProvider.getInstance().getAllAutomatonNames();
		
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(15,2));
		JLabel labelPlayer = new JLabel("Automate du player : ");
		pane.add(labelPlayer);
		JComboBox<String> jcbPlayer = new JComboBox<>(listAutomaton);
		jcbPlayer.setSelectedItem(Player.nameAtomaton);
		pane.add(jcbPlayer);
		JLabel labelRabbit = new JLabel("Automate des lapins : ");
		pane.add(labelRabbit);
		JComboBox<String> jcbRabbit = new JComboBox<>(listAutomaton);
		jcbRabbit.setSelectedItem(Rabbit.nameAtomaton);
		pane.add(jcbRabbit);
		JLabel labelAdultRabbit = new JLabel("Automate des lapins adultes : ");
		pane.add(labelAdultRabbit);
		JComboBox<String> jcbAdultRabbit = new JComboBox<>(listAutomaton);
		jcbAdultRabbit.setSelectedItem(AdultRabbit.nameAtomaton);
		pane.add(jcbAdultRabbit);
		JLabel labelFlower = new JLabel("Automate des fleurs : ");
		pane.add(labelFlower);
		JComboBox<String> jcbFlower = new JComboBox<>(listAutomaton);
		jcbFlower.setSelectedItem(Flower.nameAtomaton);
		pane.add(jcbFlower);
		JLabel labelRock = new JLabel("Automate des rochers : ");
		pane.add(labelRock);
		JComboBox<String> jcbRock = new JComboBox<>(listAutomaton);
		jcbRock.setSelectedItem(Rock.nameAtomaton);
		pane.add(jcbRock);
		JLabel labelTree = new JLabel("Automate des arbres : ");
		pane.add(labelTree);
		JComboBox<String> jcbTree = new JComboBox<>(listAutomaton);
		jcbTree.setSelectedItem(Tree.nameAtomaton);
		pane.add(jcbTree);
		JLabel labelAnvil = new JLabel("Automate des enclumes : ");
		pane.add(labelAnvil);
		JComboBox<String> jcbAnvil = new JComboBox<>(listAutomaton);
		jcbAnvil.setSelectedItem(Anvil.nameAtomaton);
		pane.add(jcbAnvil);
		JLabel labelDroppedBomb = new JLabel("Automate des bombes : ");
		pane.add(labelDroppedBomb);
		JComboBox<String> jcbDroppedBomb = new JComboBox<>(listAutomaton);
		jcbDroppedBomb.setSelectedItem(DroppedBomb.nameAtomaton);
		pane.add(jcbDroppedBomb);
		JLabel labelItem = new JLabel("Automate des items : ");
		pane.add(labelItem);
		JComboBox<String> jcbItem = new JComboBox<>(listAutomaton);
		jcbItem.setSelectedItem(DroppedItem.nameAtomaton);
		pane.add(jcbItem);
		JLabel labelGate = new JLabel("Automate des portails : ");
		pane.add(labelGate);
		JComboBox<String> jcbGate = new JComboBox<>(listAutomaton);
		jcbGate.setSelectedItem(Gate.nameAtomaton);
		pane.add(jcbGate);
		JLabel labelWall = new JLabel("Automate des murs : ");
		pane.add(labelWall);
		JComboBox<String> jcbWall = new JComboBox<>(listAutomaton);
		jcbWall.setSelectedItem(Wall.nameAtomaton);
		pane.add(jcbWall);
		JLabel labelOldMan = new JLabel("Automate de VieuxMan : ");
		pane.add(labelOldMan);
		JComboBox<String> jcbOldMan = new JComboBox<>(listAutomaton);
		jcbOldMan.setSelectedItem(OldMan.nameAtomaton);
		pane.add(jcbOldMan);
		JLabel labelFurniture = new JLabel("Automate des meubles : ");
		pane.add(labelFurniture);
		JComboBox<String> jcbFurniture = new JComboBox<>(listAutomaton);
		jcbFurniture.setSelectedItem(Furniture.nameAtomaton);
		pane.add(jcbFurniture);
		JLabel labelGraal = new JLabel("Automate du Graal : ");
		pane.add(labelGraal);
		JComboBox<String> jcbGraal = new JComboBox<>(listAutomaton);
		jcbGraal.setSelectedItem(Graal.nameAtomaton);
		pane.add(jcbGraal);
		
		frame2.add(pane,BorderLayout.CENTER);
		
		JButton submit = new JButton("Valider");
		submit.setFont(new Font(submit.getFont().getName(), submit.getFont().getStyle(), 30));
		submit.setPreferredSize(new Dimension(200,70));
		submit.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  Player.nameAtomaton = listAutomaton[jcbPlayer.getSelectedIndex()];
				  Rabbit.nameAtomaton = listAutomaton[jcbRabbit.getSelectedIndex()];
				  AdultRabbit.nameAtomaton = listAutomaton[jcbAdultRabbit.getSelectedIndex()];
				  Flower.nameAtomaton = listAutomaton[jcbFlower.getSelectedIndex()];
				  Rock.nameAtomaton = listAutomaton[jcbRock.getSelectedIndex()];
				  Tree.nameAtomaton = listAutomaton[jcbTree.getSelectedIndex()];
				  Anvil.nameAtomaton = listAutomaton[jcbAnvil.getSelectedIndex()];
				  Gate.nameAtomaton = listAutomaton[jcbGate.getSelectedIndex()];
				  DroppedBomb.nameAtomaton = listAutomaton[jcbDroppedBomb.getSelectedIndex()];
				  Wall.nameAtomaton = listAutomaton[jcbWall.getSelectedIndex()];
				  DroppedItem.nameAtomaton = listAutomaton[jcbItem.getSelectedIndex()];
				  Furniture.nameAtomaton = listAutomaton[jcbFurniture.getSelectedIndex()];
				  Graal.nameAtomaton = listAutomaton[jcbGraal.getSelectedIndex()];
				  OldMan.nameAtomaton = listAutomaton[jcbOldMan.getSelectedIndex()];
				  frame2.setVisible(false);
			  } 
			});
		frame2.add(submit, BorderLayout.SOUTH);
	}
	
}

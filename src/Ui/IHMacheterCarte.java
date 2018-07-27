/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Data.*;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.LayoutManager;
import oracle.jrockit.jfr.JFR;
/**
 *
 * @author ribotv
 */
public class IHMacheterCarte extends JPanel {
    
        private JPanel jPanelFond = new JPanel();
        private JList jListJoueur;
        private JComboBox joueur;
        private JTextField champPrix;
        private IhmBoiteMessage msg;
        private JFrame fenetre;
        
        
    public IHMacheterCarte(Patrimoine prop) {

        this.setLayout(new BorderLayout());
        
        if (prop instanceof ProprieteAConstruire){
            
            JPanel jPanelCarte = new JPanel();                     
                            
        JPanel jPanelTitre = new JPanel();                          //Titre de la carte (Propriete + Nom)
        JPanel jPanelInfoPrix = new JPanel();
        JPanel jPanelInfoPrixGrid = new JPanel();                   //Gridlayout 6x4
        JPanel jPanelInfoCarte = new JPanel();                      //Regle pour le joueur
        JPanel jPanelInfoPrixMaison = new JPanel();                 //Prix maisons / Hotel
        JPanel jPanelmaison = new JPanel();
        
        
        JPanel jPanelSud = new JPanel(); 
        jPanelSud.setBackground(Color.WHITE);
        jPanelSud.setLayout(new GridLayout(2,1));
        
        jPanelCarte.add(jPanelSud,BorderLayout.SOUTH);
        
        //Changement des backgrounds pour afficher la carte en blanc
        jPanelInfoPrixMaison.setBackground(Color.WHITE);
        jPanelInfoPrixGrid.setBackground(Color.WHITE);
        jPanelInfoCarte.setBackground(Color.WHITE);
        
        switch (((ProprieteAConstruire)prop).getGroupe().getCouleur()) {
            case bleuCiel:

                jPanelTitre.setBackground(new Color(187, 228, 250));
                break;
            case bleuFonce:

                jPanelTitre.setBackground(new Color(5, 120, 205));
                break;
            case jaune:

                jPanelTitre.setBackground(new Color(236, 218, 0));
                break;
            case mauve:

                jPanelTitre.setBackground(new Color(202, 104, 62));
                break;
            case orange:

                jPanelTitre.setBackground(new Color(246, 143, 4));
                break;
            case rouge:

                jPanelTitre.setBackground(new Color(255, 21, 39));
                break;
            case vert:

                jPanelTitre.setBackground(new Color(31, 164, 73));
                break;
            case violet:

                jPanelTitre.setBackground(new Color(211, 48, 137));
                break;
            default:
                jPanelTitre.setBackground(Color.green);
                break;
        }
        jPanelmaison.setBackground(Color.WHITE);
        
        //Layout des panels
        jPanelTitre.setLayout(new BoxLayout(jPanelTitre, BoxLayout.Y_AXIS));
        jPanelInfoPrixGrid.setLayout(new GridLayout(6,4));
        jPanelCarte.setLayout(new BorderLayout());
        jPanelInfoPrix.setLayout(new BorderLayout());
        jPanelInfoPrixMaison.setLayout(new BorderLayout());
        
        
        
        //Bordures
        jPanelCarte.setBorder(BorderFactory.createRaisedBevelBorder());
        jPanelInfoPrixGrid.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        
        //Size
        jPanelTitre.setPreferredSize(new Dimension(400,100));
        jPanelInfoPrixGrid.setPreferredSize(new Dimension(350,200));
        jPanelInfoPrixMaison.setPreferredSize(new Dimension(400,150));
        jPanelmaison.setPreferredSize(new Dimension(400,100));
        
        
        //Creation des JLabels
        
        JLabel jLabelPropriete = new JLabel("PROPRIETE");
        jLabelPropriete.setFont(new Font("Arial", Font.PLAIN, 20));
        jLabelPropriete.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel jLabelNomProp = new JLabel(prop.getNomCarreau());
        jLabelNomProp.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel jLabelEspace = new JLabel(" ");
        jLabelPropriete.setFont(new Font("Arial", Font.PLAIN, 20));
        
        
        jLabelNomProp.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel jLabelPMaisons = new JLabel("Prix des maisons / Hôtel : "+((ProprieteAConstruire)prop).getPrixMaison()+" €");  //A FAIRE

        jLabelPMaisons.setFont(new Font("Arial", Font.PLAIN, 18));

        
        JLabel jLabelVendrePro = new JLabel("Propriété de ... ");  //A FAIRE
        jLabelVendrePro.setFont(new Font("Arial", Font.PLAIN, 18));
        jLabelVendrePro.setHorizontalAlignment(JLabel.CENTER);
 
        
        String str = (((ProprieteAConstruire)prop).getNbMaison()==5) ? "Hotel: " : "Maison(s): ";
        int i;
        if (((ProprieteAConstruire)prop).getNbMaison()==5) i=1;
        else i =((ProprieteAConstruire)prop).getNbMaison();
        
        
        JLabel jLabelMaison = new JLabel(str+i);  //A FAIRE
        jLabelMaison.setFont(new Font("Arial", Font.PLAIN, 18));
        jLabelMaison.setHorizontalAlignment(JLabel.CENTER);
        
 
        
        
        
        
        
        
        
        //Ajout de tous les widgets + panels
        
        
        jPanelTitre.add(jLabelPropriete);
        jPanelTitre.add(jLabelEspace);
        jPanelTitre.add(jLabelNomProp);
        
        jPanelInfoPrix.add(jPanelInfoPrixGrid,BorderLayout.NORTH);
        
        jPanelCarte.add(jPanelTitre,BorderLayout.NORTH);
        jPanelCarte.add(jPanelInfoPrix,BorderLayout.CENTER);
        
        jPanelFond.add(jPanelCarte);
        
        
        jPanelInfoPrixGrid.add(new JLabel("LOYER"));
        jPanelInfoPrixGrid.add(new JLabel("Terrrain nu"));
        jPanelInfoPrixGrid.add(new JLabel("    €"));
        jPanelInfoPrixGrid.add(new JLabel(""+((ProprieteAConstruire)prop).getLoyerProp().get(0)));              
        
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("Avec"));
        jPanelInfoPrixGrid.add(new JLabel("1 Maison")); 
        jPanelInfoPrixGrid.add(new JLabel(""+((ProprieteAConstruire)prop).getLoyerProp().get(1)));              
        
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("2 Maisons")); 
        jPanelInfoPrixGrid.add(new JLabel(""+((ProprieteAConstruire)prop).getLoyerProp().get(2)));            
   
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("3 Maisons")); 
        jPanelInfoPrixGrid.add(new JLabel(""+((ProprieteAConstruire)prop).getLoyerProp().get(3)));            
        
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("4 Maisons")); 
        jPanelInfoPrixGrid.add(new JLabel(""+((ProprieteAConstruire)prop).getLoyerProp().get(4)));            
        
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("1 HOTEL")); 
        jPanelInfoPrixGrid.add(new JLabel(""+((ProprieteAConstruire)prop).getLoyerProp().get(5)));              
        
        jPanelInfoPrix.add(jPanelInfoCarte,BorderLayout.CENTER);
       
        jPanelInfoCarte.add(new JLabel("Si un joueur possède TOUS les terrains "));
        jPanelInfoCarte.add(new JLabel("d'un groupe de Couleur quelconque, le loyer"));
        jPanelInfoCarte.add(new JLabel("des terrains nus de ce groupe est doublé."));
        jPanelInfoCarte.setPreferredSize(new Dimension(400,60));
        jPanelInfoPrix.add(jPanelInfoPrixMaison,BorderLayout.SOUTH);

        jPanelInfoPrixMaison.add(jPanelmaison,BorderLayout.SOUTH);
        jPanelmaison.add(jLabelPMaisons);
        
        
        jPanelFond.setPreferredSize(new Dimension(420,520));
        
        this.add(jPanelFond,BorderLayout.NORTH);
        this.add(new JLabel("Voulez-vous acheter cette propriété ?",null,JLabel.CENTER),BorderLayout.SOUTH);
            
        }
        else if (prop instanceof Gare){
            
            
            
            JPanel jPanelTitre = new JPanel();
        JPanel jPanelInfoPrix = new JPanel();
       
        JPanel jPanelCarte = new JPanel(); 
        jPanelCarte.setLayout(new BorderLayout());
        
        JPanel jPanelSud = new JPanel(); 
        
        jPanelSud.setBackground(Color.WHITE);
        jPanelSud.setLayout(new GridLayout(2,1));
        
        jPanelCarte.add(jPanelSud,BorderLayout.SOUTH);
        
        jPanelCarte.setBorder(BorderFactory.createRaisedBevelBorder());
        
        jPanelCarte.add(jPanelTitre,BorderLayout.NORTH);
        jPanelCarte.add(jPanelInfoPrix,BorderLayout.CENTER);
        
        
        
        
        jPanelFond.add(jPanelCarte);
        
        
        
        jPanelTitre.setBackground(Color.WHITE);
        
        
        JLabel jLabelPropriete = new JLabel("SNCF");
        jLabelPropriete.setFont(new Font("Arial", Font.PLAIN, 25));
        jLabelPropriete.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel jLabelNomProp = new JLabel(((Gare)prop).getNomCarreau());                       //A Faire
        jLabelNomProp.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel jLabelEspace = new JLabel(" ");
        jLabelPropriete.setFont(new Font("Arial", Font.PLAIN, 20));
        
        
        jLabelNomProp.setAlignmentX(CENTER_ALIGNMENT);
        jPanelTitre.setLayout(new BoxLayout(jPanelTitre, BoxLayout.Y_AXIS));
        jPanelTitre.add(jLabelPropriete);
        jPanelTitre.add(jLabelEspace);
        jPanelTitre.add(jLabelNomProp);
        
        
        JPanel jPanelInfoPrixGrid = new JPanel();  
        jPanelInfoPrixGrid.setLayout(new GridLayout(4,3));
        jPanelInfoPrix.add(jPanelInfoPrixGrid,BorderLayout.NORTH);
        
        
        
        jPanelInfoPrixGrid.add(new JLabel("     LOYER"));
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("         "+"50 €")); 
                    
        
        jPanelInfoPrixGrid.add(new JLabel("     LOYER"));
        jPanelInfoPrixGrid.add(new JLabel("Avec 2 gares"));
        jPanelInfoPrixGrid.add(new JLabel("         "+"100 €"));            
        
        jPanelInfoPrixGrid.add(new JLabel("     LOYER"));
        jPanelInfoPrixGrid.add(new JLabel("Avec 3 gares"));
        jPanelInfoPrixGrid.add(new JLabel("         "+"150 €"));
        
        jPanelInfoPrixGrid.add(new JLabel("     LOYER"));
        jPanelInfoPrixGrid.add(new JLabel("Avec 4 gares"));
        jPanelInfoPrixGrid.add(new JLabel("         "+"200 €"));
        
        
        jPanelTitre.setPreferredSize(new Dimension(400,100));
        jPanelInfoPrixGrid.setPreferredSize(new Dimension(370,150));
        
        jPanelInfoPrixGrid.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        jPanelInfoPrix.setBackground(Color.WHITE);
        jPanelInfoPrixGrid.setBackground(Color.WHITE);
        
        
        jPanelFond.add(jPanelCarte);
        jPanelCarte.setPreferredSize(new Dimension(400,500));
        
        jPanelFond.setPreferredSize(new Dimension(420,520));
        
        this.add(jPanelFond,BorderLayout.NORTH);
        this.add(new JLabel("Voulez-vous acheter cette propriété ?",null,JLabel.CENTER),BorderLayout.SOUTH);
  
        }
        else if (prop instanceof Compagnie){
            
            
            JPanel jPanelTitre = new JPanel();
        JPanel jPanelInfoPrix = new JPanel();
       
        JPanel jPanelCarte = new JPanel(); 
        jPanelCarte.setLayout(new BorderLayout());
        
        JPanel jPanelSud = new JPanel(); 
        jPanelSud.setLayout(new GridLayout(2,1));
        jPanelSud.setBackground(Color.WHITE);
        jPanelCarte.add(jPanelSud,BorderLayout.SOUTH);
        jPanelSud.setPreferredSize(new Dimension(100,60));
        
        
        
        
        
        jPanelCarte.setBorder(BorderFactory.createRaisedBevelBorder());
        
        jPanelCarte.add(jPanelTitre,BorderLayout.NORTH);
        jPanelCarte.add(jPanelInfoPrix,BorderLayout.CENTER);
        
        
        
        
        jPanelFond.add(jPanelCarte);
        
        
        
        jPanelTitre.setBackground(Color.WHITE);
        
        
        JLabel jLabelPropriete = new JLabel("COMPAGNIE");
        jLabelPropriete.setFont(new Font("Arial", Font.PLAIN, 25));
        jLabelPropriete.setAlignmentX(CENTER_ALIGNMENT);
        
        JLabel jLabelNomProp = new JLabel(((Compagnie)prop).getNomCarreau());                       
        jLabelNomProp.setFont(new Font("Arial", Font.BOLD, 18));
        
        JLabel jLabelEspace = new JLabel(" ");
        jLabelPropriete.setFont(new Font("Arial", Font.PLAIN, 20));
        
        
        jLabelNomProp.setAlignmentX(CENTER_ALIGNMENT);
        jPanelTitre.setLayout(new BoxLayout(jPanelTitre, BoxLayout.Y_AXIS));
        jPanelTitre.add(jLabelPropriete);
        jPanelTitre.add(jLabelEspace);
        jPanelTitre.add(jLabelNomProp);
        
        
        JPanel jPanelInfoPrixGrid = new JPanel();  
        jPanelInfoPrix.add(jPanelInfoPrixGrid,BorderLayout.NORTH);
        //jPanelInfoPrixGrid.setPreferredSize(new Dimension(250,200));
        
        
        
        jPanelInfoPrixGrid.add(new JLabel("Si l'on possède UNE carte de"));
        jPanelInfoPrixGrid.add(new JLabel("compagnie de Service Public, le"));
        jPanelInfoPrixGrid.add(new JLabel("loyer est 4 fois le montant"));
        jPanelInfoPrixGrid.add(new JLabel("indiqué par les dés."));
        jPanelInfoPrixGrid.add(new JLabel("Si l'on possède les DEUX cartes"));
        jPanelInfoPrixGrid.add(new JLabel("de compagnie de Service Public,"));
        jPanelInfoPrixGrid.add(new JLabel("le loyer est 10 fois le montant"));
        jPanelInfoPrixGrid.add(new JLabel("indiqué par les dés."));
        
        
        
        
        jPanelInfoPrixGrid.setBackground(Color.WHITE);
        jPanelTitre.setPreferredSize(new Dimension(400,100));
        
        jPanelInfoPrixGrid.setPreferredSize(new Dimension(250,200));
        jPanelInfoPrixGrid.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        jPanelInfoPrix.setBackground(Color.WHITE);
        
        jPanelCarte.setPreferredSize(new Dimension(400,500));
        
        jPanelFond.setPreferredSize(new Dimension(420,520));
        
        this.add(jPanelFond,BorderLayout.NORTH);
        this.add(new JLabel("Voulez-vous acheter cette propriété ?",null,JLabel.CENTER),BorderLayout.SOUTH);
            
            
        }
    }
}

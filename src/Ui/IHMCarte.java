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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 *
 * @author ribotv
 */
public class IHMCarte extends JFrame {

    private JPanel jPanelFond;
    private JComboBox joueur;
    private JTextField champPrix;
    private IhmBoiteMessage msg;
    private IHM ihm;
    private ProprieteAConstruire proprio;
    private ArrayList<Joueur> listeJoueur;
    private JLabel jLabelMaison;
    private JButton jButAjouter, jButVendreMai, jButVendrePro;

    public ProprieteAConstruire getProprio() {
        return proprio;
    }

    public void setProprio(ProprieteAConstruire proprio) {
        this.proprio = proprio;
    }

    public IHMCarte(IHM ihm) {

        this.ihm = ihm;
        setTitle("Informations cartes");
        setSize(800, 600);
        setResizable(false);
        setLocation(50, 50);
        jPanelFond = new JPanel();
        this.add(jPanelFond);

        jPanelFond.setLayout(new GridLayout(1, 2));  //Info Carte | Boutons
        this.setVisible(true);
    }

    public void afficheCartePropriete(ProprieteAConstruire prop) {

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                ihm.getMenuPlateau().setEnabled(true);
                
                e.getWindow().dispose();
            }
        });
        
        
        
        this.proprio = prop;

        //Creation des panels
        JPanel jPanelCarte = new JPanel();
        JPanel jPanelBoutons = new JPanel();
        JPanel jPanelTitre = new JPanel();                          //Titre de la carte (Propriete + Nom)
        JPanel jPanelInfoPrix = new JPanel();
        JPanel jPanelInfoPrixGrid = new JPanel();                   //Gridlayout 6x4
        JPanel jPanelInfoCarte = new JPanel();                      //Regle pour le joueur
        JPanel jPanelInfoPrixMaison = new JPanel();                 //Prix maisons / Hotel
        JPanel jPanelmaison = new JPanel();
        JPanel jPanelNord = new JPanel();
        JPanel jPanelCentre = new JPanel();
        JPanel jPanelSud = new JPanel();
        JPanel jPanelBo = new JPanel();
        JPanel jPanel2BoutMais = new JPanel();

        //Changement des backgrounds pour afficher la carte en blanc
        jPanelInfoPrixMaison.setBackground(Color.WHITE);
        jPanelInfoPrixGrid.setBackground(Color.WHITE);
        jPanelInfoCarte.setBackground(Color.WHITE);

        switch (prop.getGroupe().getCouleur()) {
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
        jPanelInfoPrixGrid.setLayout(new GridLayout(6, 4));
        jPanelCarte.setLayout(new BorderLayout());
        jPanelInfoPrix.setLayout(new BorderLayout());
        jPanelInfoPrixMaison.setLayout(new BorderLayout());
        jPanelBoutons.setLayout(new BorderLayout());
        jPanelSud.setLayout(new BorderLayout());
        jPanelNord.setLayout(new GridLayout(2, 1));
        jPanelBo.setLayout(new GridLayout(3, 1));
        jPanel2BoutMais.setLayout(new GridLayout(1, 2));

        //Bordures
        jPanelCarte.setBorder(BorderFactory.createRaisedBevelBorder());
        jPanelInfoPrixGrid.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        //Size
        jPanelTitre.setPreferredSize(new Dimension(400, 100));
        jPanelInfoPrixGrid.setPreferredSize(new Dimension(350, 200));
        jPanelInfoPrixMaison.setPreferredSize(new Dimension(400, 150));
        jPanelmaison.setPreferredSize(new Dimension(400, 100));
        jPanelBo.setPreferredSize(new Dimension(350, 90));

        //Creation des JLabels
        JLabel jLabelPropriete = new JLabel("PROPRIETE");
        jLabelPropriete.setFont(new Font("Arial", Font.PLAIN, 20));
        jLabelPropriete.setAlignmentX(CENTER_ALIGNMENT);

        JLabel jLabelNomProp = new JLabel(prop.getNomCarreau());
        jLabelNomProp.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel jLabelEspace = new JLabel(" ");
        jLabelPropriete.setFont(new Font("Arial", Font.PLAIN, 20));

        jLabelNomProp.setAlignmentX(CENTER_ALIGNMENT);

        JLabel jLabelPMaisons = new JLabel("Prix des maisons / Hôtel : " + prop.getPrixMaison() + " €");  //A FAIRE

        jLabelPMaisons.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel jLabelVendrePro = new JLabel("Propriété de " + prop.getProprietaire().getNomJoueur());  //A FAIRE
        jLabelVendrePro.setFont(new Font("Arial", Font.PLAIN, 18));
        jLabelVendrePro.setHorizontalAlignment(JLabel.CENTER);

        String str = (prop.getNbMaison() == 5) ? "Hotel: " : "Maison(s): ";
        int i;
        if (prop.getNbMaison() == 5) {
            i = 1;
        } else {
            i = prop.getNbMaison();
        }

        jLabelMaison = new JLabel(str + i);  //A FAIRE
        jLabelMaison.setFont(new Font("Arial", Font.PLAIN, 18));
        jLabelMaison.setHorizontalAlignment(JLabel.CENTER);

        jButAjouter = new JButton("Acheter une maison/hotel");
        jButVendrePro = new JButton("Vendre propriété");
        jButVendreMai = new JButton("Vendre une maison/hotel");
        JButton jButExitAffiche = new JButton("Fermer X");

        //Ajout de tous les widgets + panels
        jPanelTitre.add(jLabelPropriete);
        jPanelTitre.add(jLabelEspace);
        jPanelTitre.add(jLabelNomProp);

        jPanelInfoPrix.add(jPanelInfoPrixGrid, BorderLayout.NORTH);

        jPanelCarte.add(jPanelTitre, BorderLayout.NORTH);
        jPanelCarte.add(jPanelInfoPrix, BorderLayout.CENTER);

        jPanelFond.add(jPanelCarte);
        jPanelFond.add(jPanelBoutons);

        jPanelInfoPrixGrid.add(new JLabel("LOYER"));
        jPanelInfoPrixGrid.add(new JLabel("Terrrain nu"));
        jPanelInfoPrixGrid.add(new JLabel("    €"));
        jPanelInfoPrixGrid.add(new JLabel("" + prop.getLoyerProp().get(0)));

        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("Avec"));
        jPanelInfoPrixGrid.add(new JLabel("1 Maison"));
        jPanelInfoPrixGrid.add(new JLabel("" + prop.getLoyerProp().get(1)));

        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("2 Maisons"));
        jPanelInfoPrixGrid.add(new JLabel("" + prop.getLoyerProp().get(2)));

        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("3 Maisons"));
        jPanelInfoPrixGrid.add(new JLabel("" + prop.getLoyerProp().get(3)));

        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("4 Maisons"));
        jPanelInfoPrixGrid.add(new JLabel("" + prop.getLoyerProp().get(4)));

        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("-"));
        jPanelInfoPrixGrid.add(new JLabel("1 HOTEL"));
        jPanelInfoPrixGrid.add(new JLabel("" + prop.getLoyerProp().get(5)));

        jPanelInfoPrix.add(jPanelInfoCarte, BorderLayout.CENTER);

        jPanelInfoCarte.add(new JLabel("Si un joueur possède TOUS les terrains "));
        jPanelInfoCarte.add(new JLabel("d'un groupe de Couleur quelconque, le loyer"));
        jPanelInfoCarte.add(new JLabel("des terrains nus de ce groupe est doublé."));

        jPanelInfoPrix.add(jPanelInfoPrixMaison, BorderLayout.SOUTH);

        jPanelInfoPrixMaison.add(jPanelmaison, BorderLayout.SOUTH);
        jPanelmaison.add(jLabelPMaisons);

        jPanelBoutons.add(jPanelNord, BorderLayout.NORTH);
        jPanelBoutons.add(jPanelCentre, BorderLayout.CENTER);
        jPanelBoutons.add(jPanelSud, BorderLayout.SOUTH);

        jPanelNord.add(jLabelVendrePro);

        JPanel jPanelVente = new JPanel();
        jPanelVente.setLayout(new GridLayout(5, 1));
        listeJoueur = new ArrayList();
        listeJoueur.addAll(ihm.getControleur().getMonopoly().getJoueurs());
        listeJoueur.remove(ihm.getControleur().getJoueurCourant());
        ArrayList<String> nomJoueurs = new ArrayList();
        for (Joueur j : listeJoueur) {
            nomJoueurs.add(j.getNomJoueur());
        }

        jPanelVente.add(new JLabel("Nom de l'acheteur:"));
        joueur = new JComboBox(nomJoueurs.toArray());
        jPanelVente.add(joueur);

        jPanelVente.add(new JLabel("Prix de vente:"));

        //joueur.setPreferredSize(new Dimension(200,100));
        this.champPrix = new JTextField(5);
        jPanelVente.add(this.champPrix);

        jPanelNord.add(jPanelVente);

        jPanelVente.setBorder(BorderFactory.createTitledBorder("Vente propriété"));

        jPanelVente.add(jButVendrePro);

        jPanelCentre.add(jPanelBo);

        jPanelBo.add(new JLabel());

        jPanel2BoutMais.add(jButAjouter);
        jPanel2BoutMais.add(jButVendreMai);

        jPanelBo.add(jLabelMaison);
        jPanelBo.add(jPanel2BoutMais);

        jPanelSud.add(jButExitAffiche, BorderLayout.SOUTH);

        ///------------------
        //activation desactivation bouton ACHETER maison
        if (ihm.getControleur().getJoueurCourant().getProprieteDispoAchatConstruction().contains(getProprio())) {
            jButAjouter.setEnabled(true);

        } else {
            jButAjouter.setEnabled(false);

        }

        //activation desactivation bouton vendre maison
        if (ihm.getControleur().getJoueurCourant().getProprieteDispoVenteConstruction().contains(getProprio())) {
            jButVendreMai.setEnabled(true);
        } else {
            jButVendreMai.setEnabled(false);
        }

        //ActionListener
        jButVendrePro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                try {
                    Integer.parseInt(champPrix.getText()); //C'est un integer
                    if (Integer.valueOf(champPrix.getText()) >= 0) {
                        if (Integer.valueOf(champPrix.getText()) <= getListeJoueur().get(joueur.getSelectedIndex()).getCash()) {
                            if (msg.afficherBoiteDialogue("Voulez-vous vendre cette propriété pour " + champPrix.getText() + " € à " + getListeJoueur().get(joueur.getSelectedIndex()).getNomJoueur() + " ?", 1)) {
                                ihm.getControleur().demandeVentePropriete(getListeJoueur().get(joueur.getSelectedIndex()), Integer.valueOf(champPrix.getText()), getProprio().getNumero());
                                ihm.actualiserIHM();
                                ihm.getMenuPlateau().setEnabled(true);
                                setVisible(false);
                                dispose();
                            }
                        } else {
                            msg.afficherBoiteDialogue("Le joueur selectionné possède uniquement " + getListeJoueur().get(joueur.getSelectedIndex()).getCash() + " €", 2);
                        }
                    } else {
                        msg.afficherBoiteDialogue("Merci d'entrer un prix superieur à zero !", 2);
                    }

                } catch (NumberFormatException exe) {
                    msg.afficherBoiteDialogue("Merci d'entrer un prix correct", 2);
                }
                ihm.getControleur().getJoueurCourant().actualisePropireteDispoVenteConstruction();
                ihm.getControleur().getJoueurCourant().actualisePropireteDispoVente();
                ihm.getControleur().getJoueurCourant().actualisePropireteDispoAchatConstruction();
                
                if (ihm.getControleur().getJoueurCourant().getProprieteDispoVente().contains(getProprio())) {
                    jButVendrePro.setEnabled(true);
                } else {
                    jButVendrePro.setEnabled(false);
                }
                ihm.actualiserIHM();
            }
        });

        jButAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
                ihm.getControleur().demandeAchatConstruction(getProprio().getNumero());

                String str = (getProprio().getNbMaison() == 5) ? "Hotel: " : "Maison(s): ";
                int i;
                if (getProprio().getNbMaison() == 5) {
                    i = 1;
                   
                } else {
                    i = getProprio().getNbMaison();
                }

                jLabelMaison.setText(str + i);
                ihm.getControleur().getJoueurCourant().actualisePropireteDispoVenteConstruction();
                ihm.getControleur().getJoueurCourant().actualisePropireteDispoVente();
                ihm.getControleur().getJoueurCourant().actualisePropireteDispoAchatConstruction();
                
                if (ihm.getControleur().getJoueurCourant().getProprieteDispoVenteConstruction().contains(getProprio())) {
                    jButVendreMai.setEnabled(true);
                } else {
                    jButVendreMai.setEnabled(false);
                }
                
                if (ihm.getControleur().getJoueurCourant().getProprieteDispoAchatConstruction().contains(getProprio())) {
                    jButAjouter.setEnabled(true);
                } else {
                    jButAjouter.setEnabled(false);
                }

                if (ihm.getControleur().getJoueurCourant().getProprieteDispoVente().contains(getProprio())) {
                    jButVendrePro.setEnabled(true);
                } else {
                    jButVendrePro.setEnabled(false);
                }
                ihm.actualiserIHM();
            }
        });

        jButVendreMai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               ihm.getControleur().demandeVenteConstruction(getProprio().getNumero());

                String str = (getProprio().getNbMaison() == 5) ? "Hotel: " : "Maison(s): ";
                int i;
                if (getProprio().getNbMaison() == 5) {
                    i = 1;
                } else {
                    i = getProprio().getNbMaison();
                }

                jLabelMaison.setText(str + i);


                ihm.getControleur().getJoueurCourant().actualisePropireteDispoVenteConstruction();
                ihm.getControleur().getJoueurCourant().actualisePropireteDispoVente();
                ihm.getControleur().getJoueurCourant().actualisePropireteDispoAchatConstruction();

                if (ihm.getControleur().getJoueurCourant().getProprieteDispoVenteConstruction().contains(getProprio())) {
                    jButVendreMai.setEnabled(true);
                } else {
                    jButVendreMai.setEnabled(false);
                }
                
                if (ihm.getControleur().getJoueurCourant().getProprieteDispoAchatConstruction().contains(getProprio())) {
                    jButAjouter.setEnabled(true);
                } else {
                    jButAjouter.setEnabled(false);
                }
                
                ihm.actualiserIHM();
            }
        });

        jButExitAffiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ihm.getMenuPlateau().setEnabled(true);
                setVisible(false);
                dispose();
            }
        });

    }

    public ArrayList<Joueur> getListeJoueur() {
        return listeJoueur;
    }



}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import Data.*;
import Data.ProprieteAConstruire;
import Jeu.Controleur;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Baptiste
 */
public class IHM implements Observateur {

    private PlateauImg plateau = new PlateauImg();
    private JList jListProprietes, jListGares, jListCompagnie;
    private IHMdes IHMdesUn = new IHMdes();
    private IHMdes IHMdesDeux = new IHMdes();
    private JTextArea textArea = new JTextArea(10, 5);
    private JButton jButAffiPlusProprietes;
    private JButton jButLancerDes;
    private JButton jButFinirTour;
    private JButton jButRotatePlateau;
    private JButton jButAbandonner;
    private JButton jButAffiPlusGares;
    private JButton jButAffiPlusCompagnies,jButActualiser;
    private JLabel jLabelTour2, jLabelPossede, jLabelInfoPartie;
    private ArrayList<String> dataProprio = new ArrayList<>();
    private ArrayList<String> dataGare = new ArrayList<>();
    private ArrayList<String> dataCompagnie = new ArrayList<>();
    private ArrayList<String> dataNewJoueur = new ArrayList<>();
    private JList jListNewJoueur;
    private JButton jButCommencerJeu;
    private JFrame menuInitJFrame, menuPlateau,fenetreOdreJoueur;
    private BufferedImage image;

    private Controleur controleur;
    private int nbTour = 0;
    private JTextField jTfNouvJoueur;
    private JLabel jLabelPositionCourant, jLabelCartePrison;
    private int rotaton = 0;
    private JTree tree;
    private DefaultMutableTreeNode treeNodeJ,treeNodeP,treeNodeNp,root;
    private JPanel jPanelLancerDes;
  
    
    public IHM(final Controleur controleur) {
        setControleur(controleur);
        controleur.setObservateur(this);
        controleur.initJoueurs();

    }

    public void fenetrePlateau() {
        setMenuPlateau(new JFrame());
        getMenuPlateau().setTitle("Monopoly");
        getMenuPlateau().setSize(1355, 874);
        getMenuPlateau().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getMenuPlateau().setResizable(true);///f
        getMenuPlateau().setLocation(30, 30);

        //Mise en page PLATEAU / HUD
        getMenuPlateau().setLayout(new BorderLayout());

        JPanel panelPlateau = new JPanel();     //Plateau img            
        JPanel panelHUD = new JPanel();         //Boutons 

        panelHUD.setPreferredSize(new Dimension(500, 874));  //874=Taille en hauteur de l'image plateau

        //Ajout au Panel this.
        getMenuPlateau().add(panelHUD, BorderLayout.EAST);
        getMenuPlateau().add(plateau);                      //Classe PlateauImg
        plateau.ImagePanel(getThis(), rotaton);                   //dessine l'image

        /**
         * ***********************************************************
         *
         * Contrôles HUD
         *
         ************************************************************
         */
        panelHUD.setBorder(BorderFactory.createTitledBorder("Informations"));   //Bordure autour du panel

        jButLancerDes = new JButton("Lancer les des");                      //Instanciation boutons
        jButFinirTour = new JButton("Finir son tour");
        jButRotatePlateau = new JButton("Rotation 90°");
        jButAbandonner = new JButton("Abandonner");
        jButAffiPlusProprietes = new JButton("Afficher plus");
        jButAffiPlusGares = new JButton("Afficher plus");
        jButAffiPlusCompagnies = new JButton("Afficher plus");

        //desactivation des boutons!
        jButLancerDes.setEnabled(false);
        jButFinirTour.setEnabled(false);
        jButAffiPlusCompagnies.setEnabled(false);
        jButAffiPlusGares.setEnabled(false);
        jButAffiPlusProprietes.setEnabled(false);

        //A FAIRE
        //DATA PROPRIETES Creation d'une liste de demo pour les propriété JLIST
        jListProprietes = new JList();

        //DATA GARES JLIST
        jListGares = new JList();

        //DATA COMPAGNIES JLIST
        jListCompagnie = new JList();

        //PanelCartes
        JPanel panelCartes = new JPanel();                              //Tableau affiche les propriétés, gares, Compagnies (TAB)
        JTabbedPane jtp = new JTabbedPane();
        panelCartes.add(jtp);

        JPanel jp1 = new JPanel();                                      //Panel propietes
        JPanel jp2 = new JPanel();                                      //Panel gares
        JPanel jp3 = new JPanel();                                      //Panel Compagnies

        JPanel jpanelEtatJoueur = new JPanel(); 
        jpanelEtatJoueur.setLayout(new BorderLayout());
        
        // JList avec barre de defilement contient PROPRIETES du joueur
        JScrollPane scrollPaneProprietes = new JScrollPane();

        scrollPaneProprietes.setViewportView(jListProprietes);
        scrollPaneProprietes.setPreferredSize(new Dimension(465, 180));

        jp1.add(scrollPaneProprietes);                          //Ajout de la JLIST dans le jpanel n°1
        jp1.add(jButAffiPlusProprietes);                        //Ajout du bouton option

        // JList GARES du joueur
        jListGares.setPreferredSize(new Dimension(465, 180));

        jp2.add(jListGares);                                    //Ajout de la JLIST dans le jpanel n°2
        jp2.add(jButAffiPlusGares);                             //Ajout du bouton option   

        // JList GARES du joueur
        jListCompagnie.setPreferredSize(new Dimension(465, 180));

        jp3.add(jListCompagnie);                                //Ajout de la JLIST dans le jpanel n°3
        jp3.add(jButAffiPlusCompagnies);                        //Ajout du bouton option  

        jtp.addTab("Propriétés      ", jp1);
        jtp.addTab("Gares           ", jp2);
        jtp.addTab("Companies       ", jp3);
        jtp.addTab("Etat Joueurs       ", jpanelEtatJoueur);
        
        
        jtp.setPreferredSize(new Dimension(470, 250));                  //Taille du JTabbedPane
        
        
        //----------- jpanelEtatJoueur avec des tabs
        
        
             //TabbledPane a jouter dans jpanelEtatJoueur
 
        root = new DefaultMutableTreeNode("Liste des joueurs");
        tree = new JTree(root);
        jpanelEtatJoueur.add(new JScrollPane(tree),BorderLayout.CENTER);
        jpanelEtatJoueur.setPreferredSize(new Dimension(470, 250));
        
        
 
        //actionPerformed boutons                                                      
        jButLancerDes.addActionListener(new ActionListener() {          //Bouton lancer les des
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.lancerDesEtJouer();

            }
        });

        jButFinirTour.addActionListener(new ActionListener() {          //Bouton Finir le tour passer au joueur suivant
            @Override
            public void actionPerformed(ActionEvent e) {
                //textArea.setText("");                                         //A METTRE TRETERTETE------------------------------
                IHMdesUn.afficheDeNum(0);                                               //A FAIRE affiche le score des dés 1 5
                IHMdesDeux.afficheDeNum(0);
                controleur.finTour();

            }
        });

        jButRotatePlateau.addActionListener(new ActionListener() {      //Bouton rotation du plateau
            @Override
            public void actionPerformed(ActionEvent e) {
                plateau.ImagePanel(getThis(), ++rotaton);
            }
        });

        jButAbandonner.addActionListener(new ActionListener() {         //Bouton joueur abandonne la partie
            @Override
            public void actionPerformed(ActionEvent e) {
                if (IhmBoiteMessage.afficherBoiteDialogue("Voulez-vous vraiment abandonner ?", 1)) {
                    IHMdesUn.afficheDeNum(0);                                               
                    IHMdesDeux.afficheDeNum(0);
                    controleur.supprimerJoueurCourant();
                    controleur.finTour();
                }
            }
        });

        jButAffiPlusProprietes.addActionListener(new ActionListener() { //Bouton Afficher plus Proprietes
            @Override
            public void actionPerformed(ActionEvent e) {
                controleur.getJoueurCourant().actualisePropireteDispoVenteConstruction();
                controleur.getJoueurCourant().actualisePropireteDispoVente();
                controleur.getJoueurCourant().actualisePropireteDispoAchatConstruction();
                getMenuPlateau().setEnabled(false);
                IHMCarte frameCartes = new IHMCarte(getThis());
                frameCartes.afficheCartePropriete(controleur.getJoueurCourant().getPropriete().get(jListProprietes.getSelectedIndex()));

            }
        });

        jButAffiPlusGares.addActionListener(new ActionListener() {      //Bouton Afficher plus Gares
            @Override
            public void actionPerformed(ActionEvent e) {
                getMenuPlateau().setEnabled(false);
                IHMCarteCarte frameCartes = new IHMCarteCarte(getThis());                                                            //MODIFICATION
                frameCartes.afficheCarteGare(controleur.getJoueurCourant().getGares().get(jListGares.getSelectedIndex()));
            }
        });

        jButAffiPlusCompagnies.addActionListener(new ActionListener() { //Bouton Afficher plus Compagnie
            @Override
            public void actionPerformed(ActionEvent e) {
                getMenuPlateau().setEnabled(false);
                IHMCarteCarte frameCartes = new IHMCarteCarte(getThis());                                                            //MODIFICATION

                frameCartes.afficheCarteCompagnie(controleur.getJoueurCourant().getCompagnies().get(jListCompagnie.getSelectedIndex()));
            }
        });

        jListProprietes.addListSelectionListener(new ListSelectionListener() {  //Permet de savoir si un element est selectionner dans la liste (active/desactive bouton)
            public void valueChanged(ListSelectionEvent evt) {
                int idx[] = jListProprietes.getSelectedIndices();

                if (idx.length == 1) {
                    jButAffiPlusProprietes.setEnabled(true);
                } else {
                    jButAffiPlusProprietes.setEnabled(false);
                }

            }
        });

        jListCompagnie.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                int idx[] = jListCompagnie.getSelectedIndices();

                if (idx.length == 1) {
                    jButAffiPlusCompagnies.setEnabled(true);
                } else {
                    jButAffiPlusCompagnies.setEnabled(false);
                }

            }
        });

        jListGares.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                int idx[] = jListGares.getSelectedIndices();

                if (idx.length == 1) {
                    jButAffiPlusGares.setEnabled(true);
                } else {
                    jButAffiPlusGares.setEnabled(false);
                }

            }
        });

        //ORDRE d'affichage des WIDGETS
        JPanel alignColon = new JPanel();                                   //Panel mise en page vertical
        panelHUD.add(alignColon);

        alignColon.setLayout(new BoxLayout(alignColon, BoxLayout.Y_AXIS));  //vertical box

        //------- 
        JPanel jPanelInfoPartie = new JPanel();                                   //Panel : Tour n° + Durée partie
        alignColon.add(jPanelInfoPartie);

        jLabelInfoPartie = new JLabel("Monopoly par TeamTarpin");
        jLabelInfoPartie.setFont(new Font("Serif", Font.PLAIN, 24));
        jLabelInfoPartie.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        jPanelInfoPartie.add(jLabelInfoPartie);
        ///-----------

        //-------
        JPanel jPanelInfosJoueur = new JPanel();
        alignColon.add(jPanelInfosJoueur);

        jPanelInfosJoueur.setLayout(new GridLayout(2, 2));

        jLabelTour2 = new JLabel();         //A FAIRE jean
        jLabelTour2.setFont(new Font("Courier New", Font.BOLD, 18));

        jLabelPossede = new JLabel();         // A FAIRE  1500€
        jLabelPossede.setFont(new Font("Courier New", Font.BOLD, 16));

        jLabelPositionCourant = new JLabel();
        jLabelPositionCourant.setFont(new Font("Courier New", Font.PLAIN, 13));

        jLabelCartePrison = new JLabel();
        jLabelCartePrison.setFont(new Font("Courier New", Font.PLAIN, 13));

        jPanelInfosJoueur.add(jLabelTour2);
        jPanelInfosJoueur.add(jLabelPositionCourant);
        jPanelInfosJoueur.add(jLabelCartePrison);
        jPanelInfosJoueur.add(jLabelPossede);
        jPanelInfosJoueur.setPreferredSize(new Dimension(490, 80));
        ///---------

        //---------
        alignColon.add(panelCartes);                                            //Le tableau avec les TABS
        ///----------

        //---------
        jPanelLancerDes = new JPanel();
        alignColon.add(jPanelLancerDes);
        jPanelLancerDes.setLayout(new BorderLayout());

        JPanel jPanelDe1 = new JPanel();
        JPanel jpanelLancerD = new JPanel();
        JPanel jPanelDe2 = new JPanel();

        jPanelDe1.setPreferredSize(new Dimension(165, 100));
        jPanelDe2.setPreferredSize(new Dimension(160, 100));

        jPanelDe1.add(IHMdesUn);
        jPanelDe2.add(IHMdesDeux);
        jpanelLancerD.add(jButLancerDes);

        jPanelLancerDes.add(jPanelDe1, BorderLayout.WEST);
        jPanelLancerDes.add(jpanelLancerD, BorderLayout.SOUTH); //CENTER NORD SOUTH
        jPanelLancerDes.add(jPanelDe2, BorderLayout.EAST);

        IHMdesUn.afficheDeNum(0);                                               //A FAIRE affiche le score des dés 1 5
        IHMdesDeux.afficheDeNum(0);

        jPanelLancerDes.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        ///--------

        //----------
        JPanel jPanelFinirTour = new JPanel();

        jPanelFinirTour.setLayout(new BorderLayout());
        jPanelFinirTour.setPreferredSize(new Dimension(0, 80));                 //espace entre les des et le bouton finir son tour

        jPanelFinirTour.add(jButFinirTour, BorderLayout.SOUTH);
        jPanelFinirTour.setPreferredSize(new Dimension(494, 45));
        ///------------

        //-------------
        JScrollPane scrollPane2 = new JScrollPane(textArea);                    //TEXTAREA
        scrollPane2.setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, new Color(234, 234, 234)));
        alignColon.add(scrollPane2);

        alignColon.add(jPanelFinirTour);
        textArea.setEditable(false);

        ///---------------
        //------------------
        JPanel jPanelSud = new JPanel();
        alignColon.add(jPanelSud);

        jPanelSud.setLayout(new BorderLayout());
        jPanelSud.setPreferredSize(new Dimension(0, 50));

        JPanel jPanelSudSud = new JPanel();
        jPanelSud.add(jPanelSudSud, BorderLayout.SOUTH);

        jPanelSudSud.setLayout(new BorderLayout());

        jPanelSudSud.add(jButRotatePlateau, BorderLayout.WEST);
        jPanelSudSud.add(jButAbandonner, BorderLayout.EAST);
        ///---------

        getMenuPlateau().setVisible(true);
    }

    public void actualiserIHM() {
       
        root.removeAllChildren();
       for(Joueur jo : controleur.getMonopoly().getJoueurs()){
           
            treeNodeJ = new DefaultMutableTreeNode(jo.getNomJoueur() + "       Possède: "+ jo.getCash() + "€ se situe sur "+jo.getPositionCourante().getNomCarreau());
            root.add(treeNodeJ);      
            
            for(CouleurPropriete coul : CouleurPropriete.values()){
                treeNodeP = new DefaultMutableTreeNode(coul.toString());
                treeNodeJ.add(treeNodeP);
                for (ProprieteAConstruire p : jo.getPropriete()){
                    if (p.getGroupe().getCouleur().equals(coul)){
                        
                        //Permet d'afficher le nombre de maisons/hotel sur la propriete
                        String str = (p.getNbMaison() == 5) ? " hotel" : " maison(s)";
                        int i;
                        if (p.getNbMaison() == 5) {
                            i = 1;
                        } else {
                            i = p.getNbMaison();
                        }
                        
                        treeNodeNp = new DefaultMutableTreeNode(p.getNomCarreau()+"     | Avec "+i+str);
                        treeNodeP.add(treeNodeNp);
                    }
                    
                }
  
               
            }
            //Affiche les gares
            treeNodeP = new DefaultMutableTreeNode("Gares");
                treeNodeJ.add(treeNodeP);
                for(Gare g : jo.getGares()){
                        treeNodeNp = new DefaultMutableTreeNode(g.getNomCarreau());
                        treeNodeP.add(treeNodeNp);
                }
            //Affiche les compagnies 
            treeNodeP = new DefaultMutableTreeNode("Compagnies");
            treeNodeJ.add(treeNodeP);
            for(Compagnie comp : jo.getCompagnies()){
                    treeNodeNp = new DefaultMutableTreeNode(comp.getNomCarreau());
                    treeNodeP.add(treeNodeNp);
            }
            
            
            
        }  
        tree.updateUI();
        plateau.ImagePanel(getThis(), rotaton);

        System.out.println("IHM"+" "+ controleur.getJoueurCourant().getNomJoueur()+ " "+controleur.getJoueurCourant().isPeutJouer()  );
        if (controleur.getJoueurCourant().isPeutJouer()) {
            jButLancerDes.setEnabled(true);
            jButFinirTour.setEnabled(false);
        } else {
            jButLancerDes.setEnabled(false);
            jButFinirTour.setEnabled(true);
        }
        dataProprio.clear();

        for (ProprieteAConstruire prop : controleur.getJoueurCourant().getPropriete()) {
            dataProprio.add(prop.getNomCarreau());
        }
        jListProprietes.setListData(dataProprio.toArray());

        dataGare.clear();
        for (Gare gare : controleur.getJoueurCourant().getGares()) {
            dataGare.add(gare.getNomCarreau());
        }
        jListGares.setListData(dataGare.toArray());

        dataCompagnie.clear();
        for (Compagnie compagnie : controleur.getJoueurCourant().getCompagnies()) {
            dataCompagnie.add(compagnie.getNomCarreau());
        }
        jListCompagnie.setListData(dataCompagnie.toArray());

        jLabelTour2.setText("A " + controleur.getJoueurCourant().getNomJoueur() + " de jouer");
        jLabelPossede.setText("Possède: " + controleur.getJoueurCourant().getCash() + "€");

        jLabelPositionCourant.setText("Position: " + controleur.getJoueurCourant().getPositionCourante().getNomCarreau());
        jLabelCartePrison.setText("Nombres de cartes prison: " + controleur.getJoueurCourant().getCarteSortiePrison().size());
        jPanelLancerDes.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, controleur.getJoueurCourant().getColor()));

    }

    @Override
    public void notifier(Message message) {
        switch (message.type) {
            case lancerDes:
                plateau.ImagePanel(getThis(), rotaton);
                IHMdesUn.afficheDeNum(message.n1);                                               //A FAIRE affiche le score des dés 1 5
                IHMdesDeux.afficheDeNum(message.n2);
                break;
            case actuTour:
                actualiserIHM();
                break;
            case carreauCourant:
                afficherConsole(message.joueurCourrant.getNomJoueur() + " tombe sur la case " + message.carreauCourrant.getNomCarreau());
                break;
            case achatPatrimoine:
                afficherConsole(message.joueurCourrant.getNomJoueur() + " a bien acheté " + message.carreauCourrant.getNomCarreau() + " (n°" + message.carreauCourrant.getNumero() + ") pour " + ((Patrimoine) message.carreauCourrant).getPrix() + "€.");
                break;
            case payerLoyer:
                afficherConsole(message.joueurCourrant.getNomJoueur() + " a payé " + message.joueurProprio.getNomJoueur() + " " + message.n1 + " €");
                break;
            case dejaProprio:
                afficherConsole(message.joueurCourrant.getNomJoueur() + " est chez lui.");
                break;
            case allerPrison:
                afficherConsole("Direction case Prison !");
                break;        
            case carte:
                IhmBoiteMessage.afficherBoiteDialogue("Action de la carte piochée: " + message.carte.getIndication(),3);
                break;
            case parDepart:
                afficherConsole(message.joueurCourrant.getNomJoueur() + " gagne " + message.n1 + "€ grâce à un passage sur la case Départ.");
                break;
            case debit:
                afficherConsole(message.joueurCourrant.getNomJoueur() + " a perdu " + message.n1 + "€");
                break;
            case encaissement:
                afficherConsole(message.joueurCourrant.getNomJoueur() + " a gagné " + message.n1 + "€");
                break;
            case carteDurable:
                afficherConsole("La carte a bien été rajouté dans votre main.");
                break;
            case finPartie:
                if (IhmBoiteMessage.afficherBoiteDialogue("Fin de partie ! "+controleur.getMonopoly().getJoueurs().get(0).getNomJoueur() + " a gagné!", 3)) {
                    getMenuPlateau().setVisible(false);
                    getMenuPlateau().dispose();
                    textArea.setText("");
                    menuInitJoueurs();

                }

                break;
            case tourPrison:
                afficherConsole(message.joueurCourrant.getNomJoueur() + " doit encore rester " + message.joueurCourrant.getTourRestantPrison() + " tour(s) avant de sortir de prison.");
                break;
            case initJoueurs:
                menuInitJoueurs();
                break;
            case manquePionHotel:
                IhmBoiteMessage.afficherBoiteDialogue("Plus de pion Hotel disponible", 2);
                break;
            case manquePionMaison:
                IhmBoiteMessage.afficherBoiteDialogue("Plus de pion Maison disponible", 2);
                break;
            case manqueArgent:
                IhmBoiteMessage.afficherBoiteDialogue("Pas assez d'argent !", 2);
                break;
            case allerPrisonDouble:
                IhmBoiteMessage.afficherBoiteDialogue("3 Doubles d'affilé, direction la case prison !", 2);
                break;
            case payementPrison:
                IhmBoiteMessage.afficherBoiteDialogue("En payant 50€ "+message.joueurCourrant.getNomJoueur()+ " est libéré de prison.", 3);
                break;
            case faillite:
                if (IhmBoiteMessage.afficherBoiteDialogue("Faillite: "+message.joueurCourrant.getNomJoueur() +" est supprimé(e) du jeu ! Tous ses biens sont remis à la banque", 2)){
                    IHMdesUn.afficheDeNum(0);                                               
                    IHMdesDeux.afficheDeNum(0);
                    controleur.supprimerJoueurCourant();
                    controleur.finTour();
                }
                
                break;
            case ordreJoueurs:
                afficheOrdreJoueurs();
                break;
            case parcGratuit:
                afficherConsole(message.joueurCourrant.getNomJoueur() +" peut se reposer durant ce tour!");
                break;

            default:
                afficherConsole(
                        "ERREUR AFFICHAGE, CONTACTEZ LE PROGRAMMEUR COMPETENT.");

                break;

        }
    }

    public void afficheOrdreJoueurs() {
         fenetreOdreJoueur = new JFrame();
        ArrayList<String> chaine = new ArrayList<>();
        JList liste = new JList();
        fenetreOdreJoueur.setTitle("Ordre des joueurs");
        fenetreOdreJoueur.setSize(200,285);
        fenetreOdreJoueur.setLocationRelativeTo(null);
        fenetreOdreJoueur.setResizable(true);

        JPanel panel = new JPanel(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 3;
        //panelGrid.add(new JLabel("Nouvel Ordre des joueurs :"), c);
        int place = 1;
        int y = 1;
        c.gridwidth = 1;
        for (Joueur j : controleur.getMonopoly().getJoueurs()) {
            chaine.add(place + ". " + j.getNomJoueur() + " avec un " + j.getScoreDe1() );
            place++;
        }
        liste.setListData(chaine.toArray());

        panel.add(liste, BorderLayout.CENTER);
        JButton bouton = new JButton("OK");
        fenetreOdreJoueur.getRootPane().setDefaultButton(bouton);
        bouton.addActionListener(new ActionListener() {      //Bouton Afficher plus Gares
            @Override
            public void actionPerformed(ActionEvent e) {
                fenetreOdreJoueur.dispose();
                        
            }
        }
        );

        panel.add(bouton, BorderLayout.SOUTH);

        panel.setBorder(BorderFactory.createTitledBorder("Nouvel ordre des joueurs :"));
        fenetreOdreJoueur.add(panel);

        fenetreOdreJoueur.setVisible(
                true);
    }

    @Override
    public boolean demande(MessageDemande messageDemande
    ) {

        switch (messageDemande.type) {  //A CHANGER
            case achat:

                IHMacheterCarte carte = new IHMacheterCarte((Patrimoine) messageDemande.carreauCourrant);

                return IhmBoiteMessage.afficherBoiteDialogueCarte(carte);
            case cartePrison:
                return IhmBoiteMessage.afficherBoiteDialogue("Voulez-vous utiliser votre carte Sortie de prison ?", 1);
        }

        return false;

    }

    public Controleur getControleur() {
        return controleur;
    }

    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public void afficherConsole(String str) {
        textArea.append(str + "\n");
    }

    public void menuInitJoueurs() {
        menuInitJFrame = new JFrame();
        menuInitJFrame.setTitle("Monopoly");
        menuInitJFrame.setSize(400, 600);
        menuInitJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuInitJFrame.setResizable(false);
        menuInitJFrame.setLocation(30, 30);

        JPanel jPanelFond = new JPanel();
        menuInitJFrame.add(jPanelFond);

        //JPanel jPanelTitre = new JPanel();
        // jPanelTitre.setLayout(new GridLayout(2,1));
        jPanelFond.setLayout(new BorderLayout());

        JLabel titre = new JLabel("Monopoly");
        titre.setFont(new Font("Rockwell", Font.PLAIN, 80));
        titre.setHorizontalAlignment(JLabel.CENTER);
        jPanelFond.add(titre, BorderLayout.NORTH);

        JPanel jPanelCentreChampFond = new JPanel(); //fond avec les champs
        jPanelFond.add(jPanelCentreChampFond, BorderLayout.CENTER);

        jPanelCentreChampFond.setLayout(new BorderLayout());
        ImageIcon image = new ImageIcon("images/icon.png");
        JLabel label = new JLabel("", image, JLabel.CENTER);

        jPanelCentreChampFond.add(label, BorderLayout.NORTH);
        JPanel jPanelChamp = new JPanel();
        jPanelChamp.setLayout(new BorderLayout());

        jPanelCentreChampFond.add(jPanelChamp, BorderLayout.CENTER);
        JPanel jpanelchampNord = new JPanel();
        jPanelChamp.add(jpanelchampNord, BorderLayout.NORTH);

        jpanelchampNord.setLayout(new BorderLayout());
        jpanelchampNord.add(new JLabel("  Entrer un nom de joueur:", null, JLabel.LEFT), BorderLayout.NORTH);

        jTfNouvJoueur = new JTextField(10);

        jpanelchampNord.add(jTfNouvJoueur, BorderLayout.CENTER);

        jListNewJoueur = new JList();

        jpanelchampNord.add(jListNewJoueur, BorderLayout.SOUTH);

        JPanel jPanelSudFond = new JPanel(); //fond avec les champs
        jPanelFond.add(jPanelSudFond, BorderLayout.SOUTH);
        jPanelSudFond.setLayout(new GridLayout(3, 1));

        JButton jButAjouter = new JButton("Ajouter le joueur");
        jButCommencerJeu = new JButton("Commencer le jeu");
        jButCommencerJeu.setEnabled(false);
        JButton jButExit = new JButton("Quitter");

        jPanelSudFond.add(jButAjouter);
        jPanelSudFond.add(jButCommencerJeu);
        jPanelSudFond.add(jButExit);

        menuInitJFrame.setVisible(true);
        menuInitJFrame.getRootPane().setDefaultButton(jButAjouter);

        jButAjouter.addActionListener(new ActionListener() {      //Bouton Afficher plus Gares
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTfNouvJoueur.getText().equals("")) {
                    IhmBoiteMessage.afficherBoiteDialogue("Merci d'entrer un nom!", 2);
                } else if (jTfNouvJoueur.getText().contains(" ")) {
                    IhmBoiteMessage.afficherBoiteDialogue("Merci de ne pas mettre d'espace dans votre nom!", 2);
                } else if (jTfNouvJoueur.getText().length() <= 10) {
                    if (dataNewJoueur.size() < 6) {
                        if (controleur.getMonopoly().creerJoueur(jTfNouvJoueur.getText())) {  // création du joueur
                            dataNewJoueur.add(jTfNouvJoueur.getText());
                            jListNewJoueur.setListData(dataNewJoueur.toArray());
                        } else {
                            IhmBoiteMessage.afficherBoiteDialogue("Nom impossible à ajouter !", 2);
                        }
                    } else {
                        IhmBoiteMessage.afficherBoiteDialogue("Impossible d'ajouter plus de six joueurs ", 2);
                    }

                    jTfNouvJoueur.setText(null);

                    if (dataNewJoueur.size() > 1) {
                        jButCommencerJeu.setEnabled(true);
                    }
                } else {
                    IhmBoiteMessage.afficherBoiteDialogue("Merci d'entrer un nom inférieur à 10 caractères ", 2);
                }
            }

        });

        jButCommencerJeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataNewJoueur.clear();
                jListNewJoueur.removeAll();
                for(Joueur j : getControleur().getMonopoly().getJoueurs()){
                    j.setColor(getControleur().getMonopoly().getCouleurPion().get(getControleur().getMonopoly().getJoueurs().indexOf(j)));
                }
                fenetrePlateau();
                menuInitJFrame.setVisible(false);
                menuInitJFrame.dispose();
                controleur.ordreJoueurs();
                controleur.startGame();

            }
        });

        jButExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuInitJFrame.setVisible(false);
                menuInitJFrame.dispose();
            }
        });

    }

    public IHM getThis() {
        return this;
    }

    /**
     * @return the menuPlateau
     */
    public JFrame getMenuPlateau() {
        return menuPlateau;
    }

    /**
     * @param menuPlateau the menuPlateau to set
     */
    public void setMenuPlateau(JFrame menuPlateau) {
        this.menuPlateau = menuPlateau;
    }

}

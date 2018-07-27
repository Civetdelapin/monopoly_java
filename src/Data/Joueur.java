package Data;

import Data.Carte.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

public class Joueur {

    private String nomJoueur;
    private int scoreDe1;
    private int scoreDe2;
    private int cash = 10;
    private boolean Enjeu;
    private Carreau positionCourante;
    private boolean EnPrison = false;
    private LinkedList<SortiePrison> CarteSortiePrison = new LinkedList<>();
    private int tourRestantPrison = 3;
    private int strikeDouble = 0;
    private boolean peutJouer = true;
    private Color color;

    
    
    //Listes de tous les carreaux du plateau que possède le joueur 
    private ArrayList<Gare> gares = new ArrayList<>();
    private ArrayList<ProprieteAConstruire> propriete = new ArrayList<>();
    private ArrayList<Compagnie> compagnies = new ArrayList<>();
    private ArrayList<ProprieteAConstruire> proprieteDispoAchatConstruction = new ArrayList<>();
    private ArrayList<ProprieteAConstruire> proprieteDispoVenteConstruction = new ArrayList<>();
    private ArrayList<ProprieteAConstruire> proprieteDispoVente = new ArrayList<>();

    public ArrayList<ProprieteAConstruire> getProprieteDispoAchatConstruction() {
        return proprieteDispoAchatConstruction;
    }

    public void setProprieteDispoAchatConstruction(ArrayList<ProprieteAConstruire> proprieteDispoAchatConstruction) {
        this.proprieteDispoAchatConstruction = proprieteDispoAchatConstruction;
    }

    public ArrayList<ProprieteAConstruire> getProprieteDispoVenteConstruction() {
        return proprieteDispoVenteConstruction;
    }

    public void setProprieteDispoVenteConstruction(ArrayList<ProprieteAConstruire> proprieteDispoVenteConstruction) {
        this.proprieteDispoVenteConstruction = proprieteDispoVenteConstruction;
    }
    
    
    public Joueur(String nomJoueur, Carreau positionCourante) {
        this.nomJoueur = nomJoueur;
        this.positionCourante = positionCourante;
        scoreDe1 = 0;
        scoreDe2 = 0;
        
        Enjeu = true;
    }
public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public boolean isEnjeu() {
        return Enjeu;
    }

    public void setEnjeu(boolean Enjeu) {
        this.Enjeu = Enjeu;
    }

    public boolean isPeutJouer() {
        return peutJouer;
    }

    public void setPeutJouer(boolean peutJouer) {
        this.peutJouer = peutJouer; 
    } 
public void actualisePropireteDispoAchatConstruction() {
        proprieteDispoAchatConstruction.clear();                                //Vide l'ancienne collection 
        for (ProprieteAConstruire p : propriete){                               //Effectu les verificatirns pour chaque propriété
            if (proprioAllProp(p) && p.getNbMaison()<5){                        //Si le joueur possede toutes les proppriétés du groupe et si le nb de maison n'est pas deja a son max (5 l'hotel)
                boolean possible = true;
                for(ProprieteAConstruire pg : p.getGroupe().getPropriete()){    //Si une des propriété du groupe possede plus de maison que la propriété en question
                    if (p.getNbMaison() > pg.getNbMaison()){
                        possible = false;
                    }
                        
                }
                if (possible){                                                  //Insertion de la propriete en question dans le vecteur selon possible
                    proprieteDispoAchatConstruction.add(p);
                }
            }
        }
}
    
    public void actualisePropireteDispoVenteConstruction() {
        proprieteDispoVenteConstruction.clear();                                //Vide l'ancienne collection
        for (ProprieteAConstruire p : propriete){                               //Effectu les verificatirns pour chaque propriété
            if (p.getNbMaison()>0){                                            //Si le nb de maison n'est pas deja a son minimum (0)
                boolean possible = true;
                for(ProprieteAConstruire pg : p.getGroupe().getPropriete()){    //Si une des propriété du groupe possede moins de maison que la propriété en question
                    if (p.getNbMaison() < pg.getNbMaison())
                        possible = false;
                }
                if (possible){                                                  //Insertion de la propriete en question dans le vecteur selon possible
                    System.out.println(p.getNomCarreau() + " " + p.getGroupe().getCouleur().toString());
                    proprieteDispoVenteConstruction.add(p);
                }
            } else {
                System.out.println(p.getNomCarreau() + " possede " + p.getNbMaison());
            }
        }
        System.out.println("----------------------------------");
    }
    
    public void actualisePropireteDispoVente() {
        proprieteDispoVente.clear();                                            //Vide l'ancienne collection
        for (ProprieteAConstruire p : propriete){                               //Effectu les verifications pour chaque propriété
                boolean possible = true;                                        
                for(ProprieteAConstruire pg : p.getGroupe().getPropriete()){    
                    if (pg.getNbMaison() != 0)                                  //Si une des propriété du groupe possede des maisons
                        possible = false;
                }
                if (possible){                                                  //Insertion de la propriete en question dans le vecteur selon possible
                    proprieteDispoVente.add(p);
                }
        }
    }

    
    public int getTourRestantPrison() {
        return tourRestantPrison;
    }

    public void setTourRestantPrison(int tourRestantPrison) {
        this.tourRestantPrison = tourRestantPrison;
    }

    public void setScoreDe1(int scoreDes) {
        this.scoreDe1 = scoreDes;
    }

    public int getScoreDe1() {
        return scoreDe1;
    }

    public void setScoreDe2(int scoreDes) {
        this.scoreDe2 = scoreDes;
    }

    public int getScoreDe2() {
        return scoreDe2;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public LinkedList<SortiePrison> getCarteSortiePrison() {
        return CarteSortiePrison;
    }

    public void setCarteSortiePrison(LinkedList<SortiePrison> nbCarteSortiePrison) {
        this.CarteSortiePrison = nbCarteSortiePrison;
    }

    public ArrayList<Gare> getGares() {
        return gares;
    }

    public void setGares(ArrayList<Gare> gares) {
        this.gares = gares;
    }

    public Carreau getPositionCourante() {
        return positionCourante;
    }

    public void setPositionCourante(Carreau positionCourante) {
        this.positionCourante = positionCourante;
    }

    public ArrayList<ProprieteAConstruire> getPropriete() {
        return propriete;
    }

    public void setPropriete(ArrayList<ProprieteAConstruire> propriete) {
        this.propriete = propriete;
    }

    public ArrayList<Compagnie> getCompagnies() {
        return compagnies;
    }

    public void setCompagnies(ArrayList<Compagnie> compagnie) {
        this.compagnies = compagnie;
    }

    public void setStrikeDouble(int strikeDouble) {
        this.strikeDouble = strikeDouble;
    }

    public int getStrikeDouble() {
        return strikeDouble;
    }

    public void payerLoyer(int i, Joueur jProprio) {

        if (getCash() >= i) {
            setCash(getCash() - i); //soustrait la somme à l'argent du joueur
            jProprio.recevoirArgent(i); //paye le propriétaire
        } else {
            jProprio.recevoirArgent(getCash());
            setCash(0);
        }

    }

    public void recevoirArgent(int i) {
        setCash(getCash() + i);
    }

    public int getNumCarreauCourant() {
        return getPositionCourante().getNumero();
    }

    public void payement(int i) {
        setCash(getCash() - i);
    }

    public void addGare(Gare g) {
        getGares().add(g);
    }

    public int getNbGare() {
        return getGares().size();
    }

    public int getNbCompagnie() {
        return getCompagnies().size();
    }

    public void addCompagnie(Compagnie c) {
        getCompagnies().add(c);
    }

    public void addPropriete(ProprieteAConstruire p) {
        getPropriete().add(p);
    }

    public boolean faillite() {
        return getCash()<=0;
    }

    public boolean proprioAllProp(ProprieteAConstruire p) {
        int i = 0;
        for (ProprieteAConstruire pr : getPropriete()) {
            if (pr.getGroupe().getCouleur().equals(p.getGroupe().getCouleur()) && pr.getProprietaire().equals(this)) {
                i++;
            }
        }
        return i == p.getGroupe().getPropriete().size();
    }

    public boolean isEnPrison() {
        return EnPrison;
    }

    public void setEnPrison(boolean EnPrison) {
        this.EnPrison = EnPrison;
    }

    public ArrayList<ProprieteAConstruire> getProprieteDispoVente() {
        return proprieteDispoVente;
    }
}

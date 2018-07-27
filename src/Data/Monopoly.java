package Data;

import Data.Carte.Carte;
import Data.Carte.DeplacementAbsolu;
import Data.Carte.DeplacementRelatif;
import Data.Carte.ModiArgent;
import Data.Carte.PayementJoueur;
import Data.Carte.PayementMaisonHotel;
import Data.Carte.SortiePrison;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Monopoly {

    private HashMap<Integer, Carreau> carreaux;
    private ArrayList<Joueur> joueurs;
    private LinkedList<Carte> cartesChances;
    private LinkedList<Carte> cartesCommun;
    private int nbPionMaison = 32;
    private int nbPionHotel = 12;
    private ArrayList<Color> couleurPion = new ArrayList<>();

    

    public Monopoly() {
        couleurPion.add(Color.RED);
        couleurPion.add(Color.YELLOW);
        couleurPion.add(Color.BLUE);
        couleurPion.add(Color.GREEN);
        couleurPion.add(Color.PINK);
        couleurPion.add(Color.ORANGE);
        this.carreaux = new HashMap<Integer, Carreau>();
        this.joueurs = new ArrayList<Joueur>();
        this.cartesChances = new LinkedList<Carte>();
        this.cartesCommun = new LinkedList<Carte>();
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
public ArrayList<Color> getCouleurPion() {
        return couleurPion;
    }
    public HashMap<Integer, Carreau> getCarreaux() {
        return carreaux;
    }

    public boolean creerJoueur(String str) {
        for (Joueur j : getJoueurs()) {
            if (j.getNomJoueur().equals(str)) {
                return false;
            }
        }
        getJoueurs().add(new Joueur(str, getCarreaux().get(1)));
        return true;
    }

    public boolean passageDepart(Joueur j, int anciennePos) {
        if (anciennePos > j.getNumCarreauCourant() && j.getPositionCourante().getNumero() != 1) {
            getCarreaux().get(1).action(j);
            return true;
        }
        return false;
    }

    public LinkedList<Carte> getCartesChances() {
        return cartesChances;
    }

    public void setCartesChances(LinkedList<Carte> cartesChances) {
        this.cartesChances = cartesChances;
    }

    public LinkedList<Carte> getCartesCommun() {
        return cartesCommun;
    }

    public void setCartesCommun(LinkedList<Carte> cartesCommun) {
        this.cartesCommun = cartesCommun;
    }

    public Carreau getCarreau(int i) {
        return getCarreaux().get(i);
    }

    public void setCarreaux(HashMap<Integer, Carreau> carreaux) {
        this.carreaux = carreaux;
    }

    public int getNbPionMaison() {
        return nbPionMaison;
    }

    public void setNbPionMaison(int nbPionMaison) {
        this.nbPionMaison = nbPionMaison;
    }

    public int getNbPionHotel() {
        return nbPionHotel;
    }

    public void setNbPionHotel(int nbPionHotel) {
        this.nbPionHotel = nbPionHotel;
    }

    public void buildGamePlateau(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");
            ArrayList<Groupe> groupes = new ArrayList<>();
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];
                if (caseType.compareTo("P") == 0) {
                    groupes.add(new Groupe(CouleurPropriete.valueOf(data.get(i)[3])));
                }
            }
            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];
                if (caseType.compareTo("P") == 0) {
                    Groupe temp = null;
                    for (Groupe groupe : groupes) {
                        if (groupe.getCouleur() == CouleurPropriete.valueOf(data.get(i)[3])) {
                            temp = groupe;
                        }

                    }

                    ProprieteAConstruire temp2 = new ProprieteAConstruire(Integer.valueOf(data.get(i)[1]), data.get(i)[2], temp, Integer.valueOf(data.get(i)[4]), Integer.valueOf(data.get(i)[5]), Integer.valueOf(data.get(i)[6]), Integer.valueOf(data.get(i)[7]), Integer.valueOf(data.get(i)[8]), Integer.valueOf(data.get(i)[9]), Integer.valueOf(data.get(i)[10]), Integer.valueOf(data.get(i)[11]));
                    temp.getPropriete().add(temp2);
                    getCarreaux().put(Integer.valueOf(data.get(i)[1]), temp2);

                } else if (caseType.compareTo("G") == 0) {

                    getCarreaux().put(Integer.valueOf(data.get(i)[1]), new Gare(Integer.valueOf(data.get(i)[1]), data.get(i)[2], Integer.valueOf(data.get(i)[3])));
                } else if (caseType.compareTo("C") == 0) {

                    getCarreaux().put(Integer.valueOf(data.get(i)[1]), new Compagnie(Integer.valueOf(data.get(i)[1]), data.get(i)[2], Integer.valueOf(data.get(i)[3])));
                } else if (caseType.compareTo("AU") == 0) {
                    getCarreaux().put(Integer.valueOf(data.get(i)[1]), new AutreCarreau(Integer.valueOf(data.get(i)[1]), data.get(i)[2]));
                } else if (caseType.compareTo("CF") == 0) {

                    getCarreaux().put(Integer.valueOf(data.get(i)[1]), new CaseFinanciere(Integer.valueOf(data.get(i)[1]), data.get(i)[2], Integer.valueOf(data.get(i)[3])));
                } else if (caseType.compareTo("AP") == 0) {

                    getCarreaux().put(Integer.valueOf(data.get(i)[1]), new AllerEnPrison(Integer.valueOf(data.get(i)[1]), data.get(i)[2]));
                } else if (caseType.compareTo("CH") == 0) {
                    getCarreaux().put(Integer.valueOf(data.get(i)[1]), new CaseACarte(Integer.valueOf(data.get(i)[1]), data.get(i)[2], true));
                } else if (caseType.compareTo("CC") == 0) {

                    getCarreaux().put(Integer.valueOf(data.get(i)[1]), new CaseACarte(Integer.valueOf(data.get(i)[1]), data.get(i)[2], false));
                } else if (caseType.compareTo("PC") == 0) {
                    getCarreaux().put(Integer.valueOf(data.get(i)[1]), new ParcGratuit(Integer.valueOf(data.get(i)[1]), data.get(i)[2]));
                } else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }

    }

    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        while ((line = reader.readLine()) != null) {
            data.add(line.split(token));
        }
        reader.close();

        return data;
    }

    public void supprimerJoueur(Joueur j) {
        for (Compagnie c : j.getCompagnies()) {
            c.setProprietaire(null);
        }

        for (ProprieteAConstruire p : j.getPropriete()) {
            p.setProprietaire(null);
            if (p.getNbMaison() == 5) {                                //rajoute le pion hotel au monopoly
                setNbPionHotel(getNbPionHotel() + 1);
            } else {
                setNbPionMaison(getNbPionMaison() + p.getNbMaison()); //rajopute les pions maisons au monopoly (si ya 0 pas grave tmtc)
            }
            p.setNbMaison(0); //enleve toutes les maisons de la prop
        }

        for (Gare g : j.getGares()) {
            g.setProprietaire(null);
        }
        j.getGares().clear();
        j.getCompagnies().clear();
        j.getPropriete().clear();
        
    }

    public int nbJoueurEnJeu() {
        int i = 0;
        for (Joueur j : getJoueurs()) {
            if (j.isEnjeu()) {
                i++;
            }
        }
        return i;

    }

    public void buildCartes(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");

            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];
                if (caseType.compareTo("CH") == 0) {
                    caseType = data.get(i)[1];
                    if (caseType.compareTo("LIB") == 0) {
                        getCartesChances().add(new SortiePrison(data.get(i)[2], this, true));
                    } else if (caseType.compareTo("DPR") == 0) {
                        getCartesChances().add(new DeplacementRelatif(data.get(i)[2], this, Integer.valueOf(data.get(i)[3])));
                    } else if (caseType.compareTo("PPMH") == 0) {
                        getCartesChances().add(new PayementMaisonHotel(data.get(i)[2], this, Integer.valueOf(data.get(i)[3]), Integer.valueOf(data.get(i)[4])));
                    } else if (caseType.compareTo("AR") == 0) {
                        getCartesChances().add(new ModiArgent(data.get(i)[2], this, Integer.valueOf(data.get(i)[3])));
                    } else if (caseType.compareTo("DPA") == 0) {
                        getCartesChances().add(new DeplacementAbsolu(data.get(i)[2], this, Integer.valueOf(data.get(i)[3]), Integer.valueOf(data.get(i)[4])));
                    }

                } else if (caseType.compareTo("CC") == 0) {
                    caseType = data.get(i)[1];
                    if (caseType.compareTo("LIB") == 0) {
                        getCartesCommun().add(new SortiePrison(data.get(i)[2], this, false));
                    } else if (caseType.compareTo("DPR") == 0) {
                        getCartesCommun().add(new DeplacementRelatif(data.get(i)[2], this, Integer.valueOf(data.get(i)[3])));
                    } else if (caseType.compareTo("PPMH") == 0) {
                        getCartesCommun().add(new PayementMaisonHotel(data.get(i)[2], this, Integer.valueOf(data.get(i)[3]), Integer.valueOf(data.get(i)[4])));
                    } else if (caseType.compareTo("AR") == 0) {
                        getCartesCommun().add(new ModiArgent(data.get(i)[2], this, Integer.valueOf(data.get(i)[3])));
                    } else if (caseType.compareTo("DPA") == 0) {
                        getCartesCommun().add(new DeplacementAbsolu(data.get(i)[2], this, Integer.valueOf(data.get(i)[3]), Integer.valueOf(data.get(i)[4])));
                    } else if (caseType.compareTo("ARJ") == 0) {
                        getCartesCommun().add(new PayementJoueur(data.get(i)[2], this, Integer.valueOf(data.get(i)[3])));
                    }
                } else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
        Collections.shuffle(getCartesChances());
        Collections.shuffle(getCartesCommun());
    }
}

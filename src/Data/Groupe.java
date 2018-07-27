package Data;
import java.util.ArrayList;

public class Groupe {

    private CouleurPropriete couleur;
    public ArrayList<ProprieteAConstruire> propriete = new ArrayList<>();
    
    public Groupe(CouleurPropriete couleur) {
        this.couleur = couleur;
    }
	
    public CouleurPropriete getCouleur() {
        return couleur;
    }

    public void setCouleur(CouleurPropriete couleur) {
        this.couleur = couleur;
    }

    public ArrayList<ProprieteAConstruire> getPropriete() {
        return propriete;
    }

    public void setPropriete(ArrayList<ProprieteAConstruire> propriete) {
        this.propriete = propriete;
    }
}
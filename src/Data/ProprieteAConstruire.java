package Data;

import java.util.ArrayList;

public class ProprieteAConstruire extends Patrimoine {
    private Groupe groupe;
    private int nbMaison =0;

    public int getNbMaison() {
        return nbMaison;
    }

    public void setNbMaison(int nbMaison) {
        this.nbMaison = nbMaison;
    }
    private int prixMaison;

    public int getPrixMaison() {
        return prixMaison;
    }
    private ArrayList<Integer> loyer = new ArrayList<>();

    public ArrayList<Integer> getLoyerProp() {
        return loyer;
    }

    public void setLoyerProp(ArrayList<Integer> loyer) {
        this.loyer = loyer;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public ProprieteAConstruire(int numero, String nomCarreau, Groupe groupe, int prix, int loyer0, int maison1, int maison2, int maison3, int maison4, int hotel, int prixm ) {
        super(numero, nomCarreau, prix,loyer0);
        this.groupe = groupe;
        
        loyer.add(0, loyer0);
        loyer.add(1,maison1);
        loyer.add(2, maison2);
        loyer.add(3,maison3);
        loyer.add(4, maison4);
        loyer.add(5, hotel);
        prixMaison = prixm;
    }  

    @Override
    public void achat(Joueur j) {
        j.addPropriete(this);
        setProprietaire(j);
        j.payement(getPrix());
    }
    
    public int calculLoyer(int des){
        if (getProprietaire().proprioAllProp(this) && nbMaison==0){
            return loyer.get(nbMaison)*2;
        }
        else {
            return loyer.get(nbMaison);
        }
        
    }
}
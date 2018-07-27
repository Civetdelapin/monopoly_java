package Data;

public class Compagnie extends Patrimoine {

    public Compagnie(int numero, String nomCarreau, int prix) {
        super(numero, nomCarreau, prix, 0);
    }

    public int calculLoyer(int des) {
        if (getProprietaire().getNbCompagnie() == 2) {
            return des * 10;
        } else {                    //getProprietaire().getNbCompagnie()==1
            return des * 4;
        }
    }

    @Override
    public void achat(Joueur j) {
        j.addCompagnie(this);
        setProprietaire(j);
        j.payement(getPrix());      //Débite le joueur du prix de la compagnie
    }

    //Définit une action
    

}

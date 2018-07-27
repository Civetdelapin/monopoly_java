package Data;

public class Gare extends Patrimoine {

    public Gare(int numero, String nomCarreau,int prix) {
        super(numero, nomCarreau,prix,50);
       
    }
	

    public int calculLoyer(int des) {
		return (getProprietaire().getNbGare()*getLoyer()); 
	}

    @Override
    public void achat(Joueur j) {
        j.addGare(this);
        setProprietaire(j);
        j.payement(getPrix());      //Débite le joueur du prix de la compagnie
    }
}
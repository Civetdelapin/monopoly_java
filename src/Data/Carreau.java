package Data;

public abstract class Carreau {
	private int numero;
	private String nomCarreau;

	

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNomCarreau() {
        return nomCarreau;
    }

    public Carreau(int numero, String nomCarreau) {
        this.numero = numero;
        this.nomCarreau = nomCarreau;
    }

    public void setNomCarreau(String nomCarreau) {
        this.nomCarreau = nomCarreau;
    }
    
    public abstract  Action action(Joueur aJ) ;
     
}
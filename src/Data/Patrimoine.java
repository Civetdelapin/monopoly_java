package Data;

public abstract class Patrimoine extends Carreau {

    private int prix;
    private int loyer;
    private Joueur proprietaire;

    public Patrimoine(int numero, String nomCarreau, int prix, int loyer) {
        super(numero, nomCarreau);
        this.prix = prix;
        this.loyer = loyer;
        setProprietaire(null);
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public abstract int calculLoyer(int des) ;
    

    public int getLoyer() {
        return loyer;
    }

    public void setLoyer(int loyer) {
        this.loyer = loyer;
    }

    public boolean verifAchatPossible(Joueur j) {
        return j.getCash() >= getPrix();
    }

    public int getPrix() {
        return this.prix;
    }

    public abstract void achat(Joueur j);

    public Action action(Joueur aJ) {
        Action a = new Action();
        if (!verifProprietaire() && verifAchatPossible(aJ)) {       //Si il n'y a pas de propriétaire et que le joueur a assez d'argent
            a.setType(Action.TypeAction.peutAcheter);
        } else if (verifProprietaire()) {       //Si il y a un propriétaire
            int i = calculLoyer(aJ.getScoreDe1() + aJ.getScoreDe2());
            a.setNb(i);
            if (!aJ.equals(getProprietaire())){
                 aJ.payerLoyer(i, getProprietaire());
                 a.setType(Action.TypeAction.aPayer);
            }
            else {
                a.setType(Action.TypeAction.saProprio);
            }
        }
        return a;
    }

    public boolean verifProprietaire() {
        return getProprietaire() != null;

    }

}

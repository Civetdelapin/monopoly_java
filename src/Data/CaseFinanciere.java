/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author reyba
 */
public class CaseFinanciere extends Carreau{

    private int valeur;
    
    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    
    public CaseFinanciere(int numero, String nomCarreau, int valeur) {
        super(numero, nomCarreau);
        setValeur(valeur);
    }

    @Override
    public Action action(Joueur aJ) {
      Action a = new Action();
      aJ.recevoirArgent(getValeur());
      a.setType(Action.TypeAction.modiRichesse);
      return a;
    }
    
}

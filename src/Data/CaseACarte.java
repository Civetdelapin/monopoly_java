/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Baptiste
 */
public class CaseACarte extends Carreau {

    private boolean estChance;

    public boolean isEstChance() {
        return estChance;
    }

    public void setEstChance(boolean estChance) {
        this.estChance = estChance;
    }
    public CaseACarte(int numero, String nomCarreau, boolean type) {
        super(numero, nomCarreau);
        setEstChance(type);
    }

    @Override
    public Action action(Joueur aJ) {
        Action a = new Action();
      a.setType(Action.TypeAction.tirerCarte);
       return a;
    }
    
    
}

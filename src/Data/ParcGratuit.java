/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author vincent
 */
public class ParcGratuit extends Carreau {

    public ParcGratuit(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }

    @Override
    public Action action(Joueur aJ) {
        Action a = new Action();
        a.setType(Action.TypeAction.parcGratuit);
        return a;
    }
    
}

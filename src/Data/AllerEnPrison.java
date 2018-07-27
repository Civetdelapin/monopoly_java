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
public class AllerEnPrison extends Carreau {

    public AllerEnPrison(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }

    
    public Action action(Joueur j) {
        j.setEnPrison(true);
        Action a= new Action();
        a.setType(Action.TypeAction.allerPrison);
        j.setPeutJouer(false);
        return a;
        
    }
    
}

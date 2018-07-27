/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Carte;

import Data.Joueur;
import Data.Monopoly;

/**
 *
 * @author reyba
 */
public class SortiePrison extends Carte{
    private boolean chance;

    public boolean isChance() {
        return chance;
    }

    public void setChance(boolean chance) {
        this.chance = chance;
    }
    public SortiePrison(String indication, Monopoly mono, boolean type) {
        super(indication, mono);
        this.chance=type;
    }

    @Override
    public ActionCarte action(Joueur j) {
        ActionCarte a = new ActionCarte();
        a.setType(ActionCarte.TypeActionCarte.carteDurable); //type de carte qu'on peut garder dans sa main
       j.getCarteSortiePrison().add(this);
       return a;
    }
    
}

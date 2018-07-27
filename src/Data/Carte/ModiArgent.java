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
public class ModiArgent extends Carte{
    private int montant;

    public ModiArgent(String indication, Monopoly mono,int montant) {
        super(indication, mono);
        setMontant(montant);
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    public ActionCarte action(Joueur j){
        ActionCarte a = new ActionCarte();
        if (getMontant()<0){ //correspond à un débit
            a.setType(ActionCarte.TypeActionCarte.debit);
            
        }
        else {
            a.setType(ActionCarte.TypeActionCarte.encaissement);
        }
       a.setNb(getMontant());
       j.recevoirArgent(getMontant());
       return a;
    }
    
}

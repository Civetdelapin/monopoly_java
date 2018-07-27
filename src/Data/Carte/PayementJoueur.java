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
public class PayementJoueur extends ModiArgent{
    
    public PayementJoueur(String indication, Monopoly mono, int montant) {
        super(indication, mono, montant);
    }
    
    public ActionCarte action(Joueur j){
        ActionCarte a = new ActionCarte();
        a.setType(ActionCarte.TypeActionCarte.debit);
        a.setNb((getMono().getJoueurs().size()-1)*getMontant());
        j.recevoirArgent((getMono().getJoueurs().size()-1)*getMontant());
        for (Joueur joueur : getMono().getJoueurs()){
            if (!joueur.equals(j)){
                joueur.payement(getMontant());
            }
        }
        return a;
    }
    
}

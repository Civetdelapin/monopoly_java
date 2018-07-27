/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Carte;

import Data.*;

/**
 *
 * @author reyba
 */
public class PayementMaisonHotel extends Carte {

    private int montantMaison;
    private int montantHotel;
    public int getMontantMaison() {
        return montantMaison;
    }

    public void setMontantMaison(int montantMaison) {
        this.montantMaison = montantMaison;
    }

    public int getMontantHotel() {
        return montantHotel;
    }

    public void setMontantHotel(int montantHotel) {
        this.montantHotel = montantHotel;
    }
    
    
    public PayementMaisonHotel(String indication, Monopoly mono, int montantMaison, int montantHotel) {
        super(indication, mono);
        setMontantMaison(montantMaison);
        setMontantHotel(montantHotel);
    }
    
    
    
    public ActionCarte action(Joueur j){
        ActionCarte a = new ActionCarte();
        a.setType(ActionCarte.TypeActionCarte.debit);
        int nbMaison = 0;
        int nbHotel = 0;
       for (ProprieteAConstruire p : j.getPropriete()){
           if (p.getNbMaison()==5){
               nbHotel++;
           }
           if (p.getNbMaison()>1 && p.getNbMaison()<5){
               nbMaison++;
           }
           
       }
       j.payement((nbHotel*montantHotel)+(nbMaison*montantMaison));
       a.setNb((nbHotel*montantHotel)+(nbMaison*montantMaison));
       return a;
    }
}

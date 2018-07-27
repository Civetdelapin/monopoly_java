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
public class DeplacementRelatif extends Carte {

    private int NbCarreau;

    public int getNbCarreau() {
        return NbCarreau;
    }

    public void setNbCarreau(int NbCarreau) {
        this.NbCarreau = NbCarreau;
    }
    public DeplacementRelatif(String indication, Monopoly mono, int NbCarreau) {
        super(indication, mono);
        setNbCarreau(NbCarreau);
    }
    
    public ActionCarte action(Joueur j ){
        ActionCarte a = new ActionCarte();
        a.setType(ActionCarte.TypeActionCarte.deplacement);
        j.setPositionCourante(getMono().getCarreau(j.getNumCarreauCourant()+getNbCarreau()));  
        return a;
    }
    
    
    
}

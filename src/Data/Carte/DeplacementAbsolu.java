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
public class DeplacementAbsolu extends Carte {

    private boolean parDepart;
    private int numCarreau;

    public int getNumCarreau() {
        return numCarreau;
    }

    public void setNumCarreau(int numCarreau) {
        this.numCarreau = numCarreau;
    }

    public boolean isParDepart() {
        return parDepart;
    }

    public void setParDepart(boolean parDepart) {
        this.parDepart = parDepart;
    }

    public DeplacementAbsolu(String indication, Monopoly mono, int mode, int nbCarreau) {
        super(indication, mono);
        if (mode == 0) {
            setParDepart(false);
        } else {
            setParDepart(true);
        }
        setNumCarreau(nbCarreau);
    }

    @Override
    public ActionCarte action(Joueur aj) {
        ActionCarte a = new ActionCarte();
        a.setType(ActionCarte.TypeActionCarte.deplacement);
        if (parDepart) {
            int tempPos = aj.getPositionCourante().getNumero();
            aj.setPositionCourante(getMono().getCarreaux().get(getNumCarreau()));
            a.setDepart(getMono().passageDepart(aj, tempPos));
        } else {
            if (getNumCarreau() == 11) {
                aj.setEnPrison(true);
            }
            aj.setPositionCourante(getMono().getCarreaux().get(getNumCarreau()));
        }
        return a;
    }
}

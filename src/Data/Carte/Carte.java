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
public abstract class Carte {
    private String indication;
    private Monopoly mono;
    
  
  
    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public Monopoly getMono() {
        return mono;
    }

    public void setMono(Monopoly mono) {
        this.mono = mono;
    }

    public Carte(String indication, Monopoly mono) {
        this.indication = indication;
        this.mono = mono;
    }
    
    public abstract  ActionCarte action(Joueur aj);
        
    
    
}

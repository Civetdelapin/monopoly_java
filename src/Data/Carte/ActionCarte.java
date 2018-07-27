/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Carte;

/**
 *
 * @author reyba
 */
public class ActionCarte {
    
    private TypeActionCarte type; 
    private boolean depart; 
    private int nb;

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
    public TypeActionCarte getType() {
        return type;
    }

    public void setType(TypeActionCarte type) {
        this.type = type;
    }
    
    
    public static enum TypeActionCarte {
	deplacement,debit,encaissement,carteDurable;
        
    }
     public boolean isDepart() {
        return depart;
    }

    public void setDepart(boolean depart) {
        this.depart = depart;
    }
    
    public ActionCarte(){
        //initialisation
        depart=false;
        setNb(0);
    }
    
    
}

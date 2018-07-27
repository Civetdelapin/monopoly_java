package Data;

public class AutreCarreau extends Carreau {

    public AutreCarreau(int numero, String nomCarreau) {
        super(numero, nomCarreau);
    }
    
    public Action action(Joueur j){
        return new Action();
    }
    
}
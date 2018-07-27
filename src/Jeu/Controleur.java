package Jeu;

import Data.*;
import Data.Carte.*;
import Ui.*;
import java.util.ArrayList;

public class Controleur {

    // private IHM ihm;
    private Monopoly monopoly;
    private Observateur obs;
    private Joueur joueurCourant;

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    public void setObservateur(Observateur observateur) {
        this.obs = observateur;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public static void main(String[] args) {
        Controleur control = new Controleur();
        control.getMonopoly().getCarreaux().get(1);
        /*control.getMonopoly().getJoueurs().add(new Joueur("Sylvestre", control.getMonopoly().getCarreaux().get(1)));
        control.getMonopoly().getJoueurs().add(new Joueur("Baptiste", control.getMonopoly().getCarreaux().get(1)));
        control.getMonopoly().getJoueurs().add(new Joueur("Vincent", control.getMonopoly().getCarreaux().get(1)));
         */

        IHM ihm = new IHM(control);

    }

    public Controleur() {

        monopoly = new Monopoly();
        getMonopoly().buildGamePlateau("data.txt");
        getMonopoly().buildCartes("card.txt");

    }

    public boolean jouerUnCoup(Joueur aJ) {
        //Lance et enregistre le score des dés

        Message message = new Message();
        message.joueurCourrant = aJ;
        int d1 = lancerD6();
        int d2 = lancerD6();
        aJ.setScoreDe1(d1);
        aJ.setScoreDe2(d2);
        message.n1 = d1;
        message.n2 = d2;
        message.type = Message.Types.lancerDes;
        obs.notifier(message);
        gererPrison(aJ);
        if (!aJ.isEnPrison() && aJ.isPeutJouer()) {                                                 //Si le joueur n'est pas en prison
            Carreau c = Avancer(aJ, d1, d2); //Faire avancer le joueur
            return effectuerActionCarreau(aJ, c);

        }
        return false;
    }

    public boolean effectuerActionCarreau(Joueur aJ, Carreau c) {
        Action a = c.action(aJ);//Lancer l'action de la case apres déplacement
        MessageDemande messageDemande = new MessageDemande();
        messageDemande.joueurCourrant = aJ;
        messageDemande.carreauCourrant = c;
        Message message = new Message();
        message.joueurCourrant = aJ;
        message.carreauCourrant = c;

        message.type = Message.Types.carreauCourant;
        obs.notifier(message);

        //Cas des diffétentes actions a effectuer
        if (a.getType().equals(Action.TypeAction.peutAcheter)) {
            if (c instanceof Patrimoine) {        //Securité
                messageDemande.type = MessageDemande.Types.achat;
                if (obs.demande(messageDemande)) {                 //Propose au joueur d'acheter
                    ((Patrimoine) c).achat(aJ);
                    message.type = Message.Types.achatPatrimoine;
                    obs.notifier(message);
                }
            }
        } else if (a.getType() == Action.TypeAction.aPayer) {
            message.n1 = a.getNb();
            message.type = Message.Types.payerLoyer;
            message.joueurProprio = ((Patrimoine) c).getProprietaire();
            obs.notifier(message);
        } else if (a.getType() == Action.TypeAction.saProprio) {
            message.joueurCourrant = aJ;
            message.type = Message.Types.dejaProprio;
            obs.notifier(message);
        } else if (a.getType() == Action.TypeAction.allerPrison) {
            aJ.setPositionCourante(getMonopoly().getCarreau(11));
            message.type = Message.Types.allerPrison;
            obs.notifier(message);
        } else if (a.getType() == Action.TypeAction.modiRichesse) {
            message.joueurProprio = aJ;
            message.n1 = ((CaseFinanciere) c).getValeur();
            if (message.n1 < 0) {
                message.n1 = -message.n1;
                message.type = Message.Types.debit;
            } else {
                message.type = Message.Types.encaissement;
            }
            obs.notifier(message);

        } else if (a.getType() == Action.TypeAction.parcGratuit) {
            message.joueurCourrant = joueurCourant;
            message.type = Message.Types.parcGratuit;
            obs.notifier(message);
        } else if (a.getType() == Action.TypeAction.tirerCarte) {
            Carte carte;
            if (((CaseACarte) c).isEstChance()) { //Differenciation entre carte chance et communauté pour gerer les paquets
                carte = getMonopoly().getCartesChances().pollFirst();
            } else {
                carte = getMonopoly().getCartesCommun().pollFirst();
            }
            ActionCarte ac = carte.action(aJ);

            message.type = Message.Types.carte;
            message.carte = carte;
            obs.notifier(message);
            switch (ac.getType()) { //type de la carte piochée
                case deplacement: {
                    if (ac.isDepart()) { //si passage par la case depart aprés deplacement grâce à l'action de la carte
                        message.joueurCourrant = joueurCourant;
                        message.type = Message.Types.parDepart;
                        message.n1 = ((CaseFinanciere) getMonopoly().getCarreaux().get(1)).getValeur();

                        obs.notifier(message);
                    }
                    effectuerActionCarreau(aJ, aJ.getPositionCourante());
                    break;
                }
                case debit: {
                    message.type = Message.Types.debit;
                    message.n1 = -ac.getNb();
                    obs.notifier(message);
                    break;
                }
                case encaissement: {
                    message.type = Message.Types.encaissement;
                    message.n1 = ac.getNb();
                    obs.notifier(message);
                    break;
                }
                case carteDurable: {
                    message.type = Message.Types.carteDurable;
                    obs.notifier(message);
                    break;
                }

                default:
                    break;
            }
            if (ac.getType() != ActionCarte.TypeActionCarte.carteDurable) { //Les cartes durables ne sont pas remises tout de suite dans le paquet
                if (((CaseACarte) c).isEstChance()) {
                    getMonopoly().getCartesChances().addLast(carte);
                } else {
                    getMonopoly().getCartesCommun().addLast(carte);
                }
            }

        }
        if (joueurCourant.faillite()) {                                           //Gere la fallite d'un joueur a la fin de chaque action d'un carreau
            message = new Message();
            message.joueurCourrant = joueurCourant;
            message.type = Message.Types.faillite;
            obs.notifier(message);
            return true;
        }
        return false;

    }

    private Carreau Avancer(Joueur aJ, int d1, int d2) {
        Carreau c = monopoly.getCarreau(getNewNumCarreau(aJ.getPositionCourante().getNumero(), d1, d2));//c est le prochain careau du joueur d'apres son score de dés
        int posPrecedente = aJ.getPositionCourante().getNumero();                                       //Sauvegarde la l'ancienne postition du joueur
        aJ.setPositionCourante(c);
        if (getMonopoly().passageDepart(aJ, posPrecedente)) {                                           //Si le joueur est passé par la case départ
            Message message = new Message();
            message.joueurCourrant = joueurCourant;
            message.type = Message.Types.parDepart;
            message.n1 = ((CaseFinanciere) getMonopoly().getCarreaux().get(1)).getValeur();
            obs.notifier(message);

        }
        return c;                                                                                       // return c pour effectuer l'action sur c
    }

    public int lancerD6() {
        return 1 + (int) (Math.random() * ((6 - 1) + 1));
    }

    public int getNewNumCarreau(int nb, int d1, int d2) {
        return ((d1 + d2 + nb - 1) % 40) + 1;
    }

    public void startGame() {
        setJoueurCourant(getMonopoly().getJoueurs().get(0));
        Message message = new Message();
        message.joueurCourrant = joueurCourant;
        message.type = Message.Types.actuTour;
        obs.notifier(message);
    }

    public void finTour() {
        Boolean test = false;
        int j = 0;
        if (!joueurCourant.isEnjeu()) {
            test = true;
            j = getMonopoly().getJoueurs().indexOf(joueurCourant);
            System.out.println(j);
        }

        int i = getMonopoly().nbJoueurEnJeu();
        if (i == 1) {
            if (test) {
                getMonopoly().getJoueurs().remove(j);
            }
            Message message = new Message();
            message.type = Message.Types.finPartie;
            obs.notifier(message);
            getMonopoly().getJoueurs().clear();
        } else {
            setJoueurCourant(getMonopoly().getJoueurs().get((getMonopoly().getJoueurs().indexOf(joueurCourant) + 1) % getMonopoly().getJoueurs().size()));

            getJoueurCourant().setPeutJouer(true);
            if (test) {
                getMonopoly().getJoueurs().remove(j);
            }
            System.out.println("" + getJoueurCourant().getNomJoueur() + " " + getJoueurCourant().isPeutJouer());
            Message message = new Message();
            message.type = Message.Types.actuTour;
            obs.notifier(message);

        }

    }

    public void supprimerJoueurCourant() {
        joueurCourant.setEnjeu(false);
        getMonopoly().supprimerJoueur(joueurCourant);

    }

    public void ordreJoueurs() {
        ArrayList<Joueur> joueurs = new ArrayList<>();
        joueurs.addAll(getMonopoly().getJoueurs());
        for (Joueur j : joueurs) {
            j.setScoreDe1(lancerD6());
        }
        //tribulle pour ordonner les joueurs
        int i = 0;
        boolean b = true;
        while (b) {
            b = false;
            for (int j = (joueurs.size() - 1); j >= 1; j--) {
                if (joueurs.get(j - 1).getScoreDe1() < joueurs.get(j).getScoreDe1()) {
                    Joueur temp = joueurs.get(j);
                    joueurs.set(j, joueurs.get(j - 1));
                    joueurs.set(j - 1, temp);
                    b = true;
                }
            }
            i++;
        }
        getMonopoly().getJoueurs().clear();
        getMonopoly().getJoueurs().addAll(joueurs);
        Message message = new Message();
        message.type = Message.Types.ordreJoueurs;
        obs.notifier(message);
    }

    public void initJoueurs() {
        Message message = new Message();
        message.type = Message.Types.initJoueurs;
        obs.notifier(message);
    }

    public void demandeAchatConstruction(int numC) {                                                    //Affiche seulement les desponibles a la vente de construction: homogene et different de 5
        ProprieteAConstruire c = (ProprieteAConstruire) getMonopoly().getCarreaux().get(numC);          //Recupere la carreau selectioné
        if (joueurCourant.getCash() >= c.getLoyerProp().get(c.getNbMaison())) {                         //Si le joueur a assez d'argent
            if (c.getNbMaison() == 4) {                                                                 //Si le joueur achete un hotel
                if (getMonopoly().getNbPionHotel() > 0) {                                               //Si il reste des hotel en banque
                    getMonopoly().setNbPionMaison(getMonopoly().getNbPionMaison() + 4);                 //Remise des 4 maisons en banque
                    getMonopoly().setNbPionHotel(getMonopoly().getNbPionHotel() - 1);                   //Retire l'hotel de la banque
                    c.setNbMaison(c.getNbMaison() + 1);                                                 //Ajoute un au nb de maisons qui est de 4, il passe donc a 5 (un hotel)
                    joueurCourant.setCash(joueurCourant.getCash() - c.getPrixMaison());                                           //Paye le prix
                } else {                                                                                //Si il n'y a plus d'hotel en banque
                    Message message = new Message();
                    message.type = Message.Types.manquePionHotel;
                    obs.notifier(message);
                }
            } else if (getMonopoly().getNbPionMaison() > 0) {                                           //Si la vente concerne une maison et si la banque en possede encore des maison
                getMonopoly().setNbPionMaison(getMonopoly().getNbPionMaison() - 1);                     //Retire la maison de la banque
                c.setNbMaison(c.getNbMaison() + 1);                                                     //Ajoute un au nombre de maisons
                joueurCourant.setCash(joueurCourant.getCash() - c.getPrixMaison());                                               //Paye le prix
            } else {                                                                                    //Si il n'y a plus de maison en banque
                Message message = new Message();
                message.type = Message.Types.manquePionMaison;
                obs.notifier(message);

            }
        } else {                                                                                        //Si le joueur n'a pas assez d'argent
            Message message = new Message();
            message.type = Message.Types.manqueArgent;
            obs.notifier(message);
        }
    }

    public void demandeVenteConstruction(int numC) {
        //Affiche seulement les desponibles a la vente de construction: homogene et different de 0
        int some = 0;
        ProprieteAConstruire p = (ProprieteAConstruire) getMonopoly().getCarreaux().get(numC);          //Recupere la carreau selectioné
        if (p.getNbMaison() == 5) {                                                                     //Si la vente concerne un Hotel
            for (ProprieteAConstruire pg : p.getGroupe().getPropriete()) {                              //Parcour toutes les propriétés du groupe pour vendre toutes le constructions
                if (pg.getNbMaison() == 5) {                                                            //Si la propriete du groupe passede un hotel
                    getMonopoly().setNbPionHotel(getMonopoly().getNbPionHotel() + 1);                   //Rendre a la banque un hotel
                } else {
                    getMonopoly().setNbPionHotel(getMonopoly().getNbPionMaison() + pg.getNbMaison());   //Sinon lui rendre le nombre de maison de la propriete du groupe
                }
                pg.setNbMaison(0);                                                                      //Remise a 0 du nombre de maison de la propriete (rappel: 5 maison = un hotel)
                some = some + (pg.getNbMaison() * pg.getPrixMaison()) / 2;                              //Compte de la valeur de ses ventes
            }
        } else {                                                                                        //Si la vente concerne une maison
            getMonopoly().setNbPionMaison(getMonopoly().getNbPionMaison() + 1);                         //Rendre a la banque une maison
            p.setNbMaison(p.getNbMaison() - 1);                                                         //Soustraire un au nombre de maison de la propriete
            some = p.getPrixMaison() / 2;                                                               //Valeur de sa vente
        }
        joueurCourant.recevoirArgent(some);                                                                         //Remise de l'argent obtenue

    }

    public void demandeVentePropriete(Joueur jv, int prix, int numC) {
        ProprieteAConstruire p = (ProprieteAConstruire) getMonopoly().getCarreaux().get(numC);
        joueurCourant.getPropriete().remove(p);
        p.setProprietaire(jv);
        joueurCourant.recevoirArgent(prix);
        jv.getPropriete().add(p);
        jv.setCash(jv.getCash() - prix);
        joueurCourant.actualisePropireteDispoVente();
    }

    public void hypothequePropriete(Joueur j) { //revoir avec IHM
        //int i = ihm.selectionPropriétéHypotheque(j);

    }

    public void gererPrison(Joueur j) {
        if (j.isEnPrison()) {                                                   //Si le joueur est en prison
            if (j.getScoreDe1() == j.getScoreDe2()) {                           //Si il fait un double il sort de prison
                j.setEnPrison(false);
            } else if (j.getCarteSortiePrison().size() > 0) {                   //Si il possede une carte sortie de prison
                MessageDemande messageDemande = new MessageDemande();
                messageDemande.type = MessageDemande.Types.cartePrison;
                if (obs.demande(messageDemande)) {                                 //Si il souhaite l'utiliser
                    j.setEnPrison(false);
                    //Remise de la carte dans le paquet 
                    if (j.getCarteSortiePrison().getFirst().isChance()) {
                        getMonopoly().getCartesChances().addLast(j.getCarteSortiePrison().getFirst());
                    } else {
                        getMonopoly().getCartesCommun().addLast(j.getCarteSortiePrison().getFirst());
                    }
                    j.getCarteSortiePrison().pollFirst();
                }
            } else if (j.getTourRestantPrison() == 0) {                         //Si il a deja passer 3 tours en prison
                j.setEnPrison(false);
                j.payement(50);
                Message message = new Message();
                message.joueurCourrant = joueurCourant;
                message.type = Message.Types.payementPrison;
                obs.notifier(message);
            }

            if (j.isEnPrison()) {                                               //Si il est en prison apres totes les verification
                Message message = new Message();
                message.joueurCourrant = j;
                message.type = Message.Types.tourPrison;
                obs.notifier(message);
                j.setTourRestantPrison(j.getTourRestantPrison() - 1);
            } else {                                                            //Sinon on remet son nombre de tour restant en prison a 3 pour son prochain sejour
                j.setTourRestantPrison(3);
            }
        } else if (j.getStrikeDouble() == 3) {                                  //Si le joueur fais 3 doubles à la suite
            j.setPositionCourante(getMonopoly().getCarreaux().get(11));
            j.setEnPrison(true);
            j.setStrikeDouble(0);
            j.setPeutJouer(false);
            Message message = new Message();
            message.type = Message.Types.allerPrisonDouble;
            obs.notifier(message);
        }

    }

    public void lancerDesEtJouer() {
        if (!jouerUnCoup(joueurCourant)) { //test si le joueurCourrant est declaré en faillite ou non à la fin du Coup
                                           //si il est declaré, l'IHM s'occupe déja de changer de Tour et de Joueur, du coup l'attribut JoueurCourrant n'est plus le bon.
            if (joueurCourant.getScoreDe1() == joueurCourant.getScoreDe2()) {               //Si le joueur effectue un double
                joueurCourant.setStrikeDouble(joueurCourant.getStrikeDouble() + 1);
            } else {
                joueurCourant.setStrikeDouble(0);
                joueurCourant.setPeutJouer(false);
            }

            Message message = new Message();
            message.joueurCourrant = joueurCourant;
            message.type = Message.Types.actuTour;
            obs.notifier(message);
        }

    }

}

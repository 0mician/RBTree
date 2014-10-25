/**
 * RougeNoir - Implementation ADT d'un arbre binaire de recherche Rouge Noir
 * @author Cedric Lood (033639)
 * @version 1.0
 */

import java.util.*;
import java.io.*;

public class RougeNoir
{
    private static NoeudRN sentinelle= new NoeudRN(null);	//sentinelle est un noeud noir NULL
    private int nbreEntree= 0;
    private NoeudRN racine;

    public void insert(int matriculeEtudiant, int pointInitial)
    {
		Etudiant e= new Etudiant(matriculeEtudiant, pointInitial);
		NoeudRN z= new NoeudRN(e);
		insererNoeud(z);
		nbreEntree++;
    }

    public void remove(int matricule)
    {
		NoeudRN toRemove= rechercheNoeud(matricule);
		if(toRemove == sentinelle)
			System.out.println("Etudiant inexistant dans le système.");
		else{
			delete(toRemove);
			nbreEntree--;
		}
    }

    public void addPoints(int matriculeEtudiant, int points)
    {
		NoeudRN found= rechercheNoeud(matriculeEtudiant);
		if(found == sentinelle)
			System.out.println("Etudiant inexistant dans le système.");
		else
			found.getEtudiant().ajoutPoints(points);
    }

    public int getSize()
    {
		return nbreEntree;
    }

	public void printBreadthFirst()
    {
		NoeudRN z= racine;
		Queue<NoeudRN> noeuds= new LinkedList<NoeudRN>();
		if(z != sentinelle){
			noeuds.add(z);
			while(!noeuds.isEmpty()){
				z= noeuds.remove();
				System.out.println(z.toString());
				if(z.getGauche() != sentinelle)
					noeuds.add(z.getGauche());
				if(z.getDroit() != sentinelle)
					noeuds.add(z.getDroit());
			}
		}
    }

    public void printInOrder()
    {
		NoeudRN z= racine;
		Stack<NoeudRN> noeuds= new Stack<NoeudRN>();
		while(!noeuds.isEmpty() || z != sentinelle) {
			if(z != sentinelle) {
				noeuds.push(z);
				z= z.getGauche();
			} else {
				z= noeuds.pop();
				System.out.println(z.toString());
				z= z.getDroit();
			}
		}
    }
	

    public RougeNoir(String fichier)
    {
		racine= sentinelle;
		try{
			Scanner input= new Scanner (new File(fichier));
			int numberOfEntries= Integer.parseInt(input.next());
			while (input.hasNext() && numberOfEntries >= 0) {
				int	a= Integer.parseInt(input.next()), b= Integer.parseInt(input.next());
				insert(a, b);
				numberOfEntries--;
			}
			input.close();
		}
		catch (IOException e) {
			System.out.println("Erreur: " + e);
		}
    }

    public RougeNoir()
    {
		racine= sentinelle;
    }

    public void addFromFile(String fichier)	
    {
		try{
			Scanner input= new Scanner (new File(fichier));
			int numberOfEntries= Integer.parseInt(input.next());
			System.out.println(numberOfEntries);
			NoeudRN found= null;
			while (input.hasNext() && numberOfEntries >= 0) {
				int	a= Integer.parseInt(input.next()), b= Integer.parseInt(input.next());
				found= rechercheNoeud(a);
				if(found == sentinelle){
					System.out.println("Etudiant " + a + " non présent dans le système, a été ajouté.");
					insert(a, b);
				}
				else
					addPoints(a, b);
				numberOfEntries--;
			}
			input.close();
		}
		catch (IOException e){
			System.out.println("Erreur : " + e);
		}
    }

	private void insererNoeud(NoeudRN z)
	{
		NoeudRN y= null, x= racine;
		while(x != sentinelle){
			y= x;
			if(z.compareTo(x) == 1)
				x= x.getGauche();
			else
				x= x.getDroit();
		}
		z.setPere(y);
		if(y == null){
			racine= z;
			racine.setPere(sentinelle);
		}
		else if (z.compareTo(y) == 1)
			y.setGauche(z);
		else
			y.setDroit(z);
		z.setGauche(sentinelle);
		z.setDroit(sentinelle);
		z.setCouleur(Couleur.Rouge);
		insertFixUp(z);
	}

  	private void insertFixUp(NoeudRN z)
    {
		while (z != racine && z.getPere().getCouleur() == Couleur.Rouge){
			if(z.getPere() == z.getGrandPere().getGauche()){
				NoeudRN y= z.getGrandPere().getDroit();
				if(y.getCouleur() == Couleur.Rouge){
					z.getPere().setCouleur(Couleur.Noir);
					y.setCouleur(Couleur.Noir);
					z.getGrandPere().setCouleur(Couleur.Rouge);
					z= z.getGrandPere();
				}
				else{
					if(z == z.getPere().getDroit()){
						z= z.getPere(); 
						rotationGauche(z);
					}
					z.getPere().setCouleur(Couleur.Noir);
					z.getGrandPere().setCouleur(Couleur.Rouge);
					rotationDroite(z.getGrandPere());
				}
			}
			else {
				NoeudRN y= z.getGrandPere().getGauche();
				if(y.getCouleur() == Couleur.Rouge){
					z.getPere().setCouleur(Couleur.Noir);
					y.setCouleur(Couleur.Noir);
					z.getGrandPere().setCouleur(Couleur.Rouge);
					z= z.getGrandPere();
				}
				else{
					if(z == z.getPere().getGauche()){
						z= z.getPere();
						rotationDroite(z);
					}
					z.getPere().setCouleur(Couleur.Noir);
					z.getGrandPere().setCouleur(Couleur.Rouge);
					rotationGauche(z.getGrandPere());
				}
			}
		}
		racine.setCouleur(Couleur.Noir);
    }

	private void rotationGauche(NoeudRN x)
    {
		NoeudRN y= x.getDroit();
		x.setDroit(y.getGauche());
		if(y.getGauche() != sentinelle)
			y.getGauche().setPere(x);
		y.setPere(x.getPere());
		if(x.getPere() == sentinelle)
			racine= y;
		else if (x == x.getPere().getGauche())
			x.getPere().setGauche(y);
		else 
			x.getPere().setDroit(y);
		y.setGauche(x);
		x.setPere(y);
    }

    private void rotationDroite(NoeudRN x)
    {
		NoeudRN y= x.getGauche();
		x.setGauche(y.getDroit());
		if(y.getDroit() != sentinelle)
			y.getDroit().setPere(x);
		y.setPere(x.getPere());
		if(x.getPere() == sentinelle)
			racine= y;
		else if (x == x.getPere().getDroit())
			x.getPere().setDroit(y);
		else
			x.getPere().setGauche(y);
		y.setDroit(x);
		x.setPere(y);
    }

	private NoeudRN rechercheNoeud(int matricule)
	{
		NoeudRN parcours= racine;
		while ((parcours != sentinelle) && parcours.key() != matricule){
			if(matricule < parcours.key())
				parcours= parcours.getGauche();
			else
				parcours= parcours.getDroit();
		}
		return parcours;
	}
	
	private void transplant(NoeudRN u, NoeudRN v)
	{
		if(u.getPere() == sentinelle)
			racine= v;
		else if(u == u.getPere().getGauche())
			u.getPere().setGauche(v);
		else
			u.getPere().setDroit(v);
		v.setPere(u.getPere());
	}

	private void delete(NoeudRN z)
	{
		NoeudRN y= z, x;
		Couleur noeudY= y.getCouleur();
		if(z.getGauche() == sentinelle){
			x= z.getDroit();
			transplant(z, z.getDroit());
		}
		else if(z.getDroit() == sentinelle){
			x= z.getGauche();
			transplant(z, z.getGauche());
		}
		else {
			y= minimum(z.getDroit());
			noeudY= y.getCouleur();
			x= y.getDroit();
			if(y.getPere() == z)
				x.setPere(y);
			else {
				transplant(y, y.getDroit());
				y.setDroit(z.getDroit());
				y.getDroit().setPere(y);
			}
			transplant(z,y);
			y.setGauche(z.getGauche());
			y.getGauche().setPere(y);
			y.setCouleur(z.getCouleur());
		}
		if(noeudY == Couleur.Noir)
			deleteFixUp(x);
	}

	private void deleteFixUp(NoeudRN x)
	{
		NoeudRN w= null;
		while((x != racine) && (x.getCouleur() != Couleur.Noir)){
			if(x == x.getPere().getGauche()){
				w= x.getPere().getDroit();
				if(w.getCouleur() == Couleur.Rouge){
					w.setCouleur(Couleur.Noir);
					x.getPere().setCouleur(Couleur.Rouge);
					rotationGauche(x.getPere());
					w= x.getPere().getDroit();
				}
				if((w.getGauche().getCouleur() == Couleur.Noir)
					&& (w.getDroit().getCouleur() == Couleur.Noir)){
						w.setCouleur(Couleur.Rouge);
						x= x.getPere();
				}
				else {
					if(w.getDroit().getCouleur()== Couleur.Noir){
						w.getGauche().setCouleur(Couleur.Noir);
						w.setCouleur(Couleur.Rouge);
						rotationDroite(w);
						w= x.getPere().getDroit();
					}
					w.setCouleur(x.getPere().getCouleur());
					x.getPere().setCouleur(Couleur.Noir);
					w.getDroit().setCouleur(Couleur.Noir);
					rotationGauche(x.getPere());
					x= racine;
				}
			}
			else {
				w= x.getPere().getGauche();
				if(w.getCouleur() == Couleur.Rouge){
					w.setCouleur(Couleur.Noir);
					x.getPere().setCouleur(Couleur.Rouge);
					rotationDroite(x.getPere());
					w= x.getPere().getGauche();
				}
				if((w.getDroit().getCouleur() == Couleur.Noir)
					&& (w.getGauche().getCouleur() == Couleur.Noir)){
						w.setCouleur(Couleur.Rouge);
						x= x.getPere();
				}
				else {
					if(w.getGauche().getCouleur()== Couleur.Noir){
						w.getDroit().setCouleur(Couleur.Noir);
						w.setCouleur(Couleur.Rouge);
						rotationGauche(w);
						w= x.getPere().getGauche();
					}
					w.setCouleur(x.getPere().getCouleur());
					x.getPere().setCouleur(Couleur.Noir);
					w.getGauche().setCouleur(Couleur.Noir);
					rotationDroite(x.getPere());
					x= racine;
				}
			}
		}
		x.setCouleur(Couleur.Noir);
	}

    private NoeudRN minimum(NoeudRN x)
    {
		while(x.getGauche() != sentinelle)
			x= x.getGauche();
		return x;
    }

    private NoeudRN maximum(NoeudRN x)
    {
		while(x.getDroit() != sentinelle)
			x= x.getDroit();
		return x;
    }
    
    private int hauteur(NoeudRN n){
        if(n==sentinelle) 
			return 0;
		else {
			int hGauche = hauteur(n.getGauche());
			int hDroite = hauteur(n.getDroit());
			return Math.max(hGauche,hDroite)+1;
		}
	}
    
    public int hauteurArbre(){
        return hauteur(racine);
    }
}

/**
 * NoeudRN - represente un noeud colore d'un arbre binaire Rouge Noir
 * @author Cedric Lood (033639)
 * @version 1.0
 */

public class NoeudRN implements Comparable
{
	private Etudiant etudiant;
	private NoeudRN filsGauche, filsDroit, pere;
	private Couleur couleur;

	public NoeudRN(Etudiant e)
	{
		etudiant= e;
		filsGauche= null;
		filsDroit= null;
		pere= null;
		couleur= Couleur.Noir;
	}
	
	public String toString()
	{
		return etudiant.toString();
	}
	
	public int compareTo(Object o) throws ClassCastException
	{
		if (!(o instanceof NoeudRN))
	    	throw new ClassCastException("A NoeudRN object expected.");
		if(this.etudiant.compareTo(((NoeudRN)o).etudiant) == 1)
			return 1;
		else
			return 0;
	}
	
	public int key()
	{
		return etudiant.getMatricule();
	}
	
	public NoeudRN getGauche() 		{ return this.filsGauche; 	}
	public NoeudRN getDroit () 		{ return this.filsDroit;  	}
	public NoeudRN getPere  () 		{ return this.pere;       	}
	public NoeudRN getGrandPere()	{ return this.pere.pere;	}
	public Couleur getCouleur()		{ return this.couleur;		}
	public Etudiant getEtudiant()	{ return this.etudiant;		}

	public void setGauche	(NoeudRN z)	{ this.filsGauche= z;	  	}
	public void setDroit	(NoeudRN z)	{ this.filsDroit= z;   		}
	public void setPere		(NoeudRN z) { this.pere= z;		   		}
	public void setCouleur	(Couleur c)	{ this.couleur= c;			}
}	

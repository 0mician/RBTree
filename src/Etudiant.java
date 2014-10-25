/**
 * Etudiant - represente par un matricule (unique) et une cote
 * @author Cedric Lood (033639)
 * @version 1.0
 */

public class Etudiant implements Comparable
{
    private int matricule;
    private int points;

    public Etudiant(int matricule, int points)
    {
		this.matricule= matricule;
		this.points= points;
    }
	
	public void ajoutPoints(int points)
	{
		this.points+= points;
	}

	public int compareTo(Object o) throws ClassCastException 
	{
	    if (!(o instanceof Etudiant))
	      throw new ClassCastException("A Etudiant object expected.");
		if(this.matricule <= ((Etudiant)(o)).matricule)
			return 1;
		else
			return 0;
	}
	
	public String toString()
	{
		return "Etudiant (" + matricule + "): " + points;
	}
	
	public int getMatricule()
	{
		return matricule;
	}
}

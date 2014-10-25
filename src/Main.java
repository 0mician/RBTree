/**
 * Main - Implementation ADT d'un arbre binaire de recherche Rouge Noir
 * @author Cedric Lood (033639)
 * @version 1.0
 */

public class Main
{
    public static void main(String[] args)
    {
		if (args.length == 0) {
			System.out.println("Veuillez rentrer le nom du fichier d'upload initial en parametre!");
		}
		else {
			String filename= args[0];
			ProgrammeGestion prog= new ProgrammeGestion(filename);
			prog.greetings();
			while(prog.menuGestion() != 0) ;
		}
    }
}

/**
 * @author Cedric Lood (033639)
 * @version 1.0
 */

import java.util.*;
import java.io.*;

public class ProgrammeGestion 
{
	private RougeNoir arbreRN;

	public ProgrammeGestion(String filename)
	{
		arbreRN= new RougeNoir(filename);		
	}

	public void greetings()
	{
		System.out.println("");
		System.out.println("Bienvenue dans le programme de gestion des etudiants!");
		System.out.println("");
	}

	public int menuGestion()
	{
		int choix= -1;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Veuillez entrer le numero de la fonction que vous souhaitez lancer:");
			System.out.println("");
			System.out.println("0: Quitter");
			System.out.println("1: Inserer un etudiant");
			System.out.println("2: Ajouter les points d'un etudiant");
			System.out.println("3: Ajouter les points de plusieurs etudiants via un fichier");
			System.out.println("4: Supprimer un etudiant");
			System.out.println("5: Afficher la liste triee des etudiants et leurs points");
			System.out.println("6: Afficher la structure de donnee(Arbre Binaire) par niveau");
			System.out.println("7: Afficher la hauteur de l'arbre (Administrateur)");
			System.out.println("");
			System.out.print  ("Votre choix: ");
			choix= Integer.parseInt(scanner.nextLine());
		}
		while (choix < 0 || choix > 7);

		switch (choix) {
			case 0: 							break;
			case 1: insertionEtudiant();		break;
			case 2: modificationEtudiant();		break;
			case 3: remplacementViaFichier();	break;
			case 4: suppressionEtudiant();		break;
			case 5: affichageOrdonne();			break;
			case 6: affichageParNiveau();		break;
			case 7: affichageHauteurArbre();	break;
		}
		return choix;
	}
	
	private void insertionEtudiant()
	{
		int matricule, points;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer le matricule de l'etudiant: ");
		matricule= Integer.parseInt(scanner.nextLine());
		System.out.println("Veuillez entrer les points de l'etudiant: ");
		points= Integer.parseInt(scanner.nextLine());
		arbreRN.insert(matricule, points);
		System.out.println("");
	}

	private void modificationEtudiant()
	{
		int matricule, points;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer le matricule de l'etudiant: ");
		matricule= Integer.parseInt(scanner.nextLine());
		System.out.println("Veuillez entrer les points de l'etudiant: ");
		points= Integer.parseInt(scanner.nextLine());
		arbreRN.addPoints(matricule, points);
		System.out.println("");
	}

	private void remplacementViaFichier()
	{
		String filename;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer le nom du fichier: ");
		filename= scanner.nextLine();
		arbreRN.addFromFile(filename);
		System.out.println("");
	}

	private void suppressionEtudiant()
	{
		int matricule;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Veuillez entrer le matricule de l'etudiant: ");
		matricule= Integer.parseInt(scanner.nextLine());
		arbreRN.remove(matricule);
		System.out.println("");
	}

	private void affichageOrdonne()
	{
		System.out.println("Affichage ordonne: ");
		arbreRN.printInOrder();
		System.out.println("");
	}

	private void affichageParNiveau()
	{
		System.out.println("Affichage par niveau: ");
		arbreRN.printBreadthFirst();
		System.out.println("");
	}
	
	private void affichageHauteurArbre()
	{
		System.out.println("Voici la hauteur: " + arbreRN.hauteurArbre() );
	}
}

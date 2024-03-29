package models.solver.reader;

/**
 * Lecteur de solutions provenant du solveur Glpk
 */
public interface SolutionReaderGlpk extends SolutionReader{

	
	/**
	 * Lecture d'un fichier correspondant a la solution venant du Solveur Glpk.
	 * @param pathFile Chemin du fichier a lire.
	 * @throws ReaderException Probleme de lecture du fichier.
	 * @throws NotFoundSolutionException Absence de solution.
	 */
	public void read(String pathFile) throws ReaderException, NotFoundSolutionException;
}

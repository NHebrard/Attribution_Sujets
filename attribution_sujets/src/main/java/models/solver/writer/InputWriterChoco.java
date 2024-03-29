package models.solver.writer;

/**
 * Ecrivain du fichier d'entree pour Choco
 */
public interface InputWriterChoco extends InputWriter{

	/**
	 * Methode d'ecriture d'un fichier d'entree de Choco. Ce fichier est documentee
	 * @param pathFile chemin du fichier a ecrire.
	 * @throws WriterException Erreur d'ecriture
	 */
	public void write(String pathFile) throws WriterException;
}

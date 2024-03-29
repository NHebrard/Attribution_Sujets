package models.parser.user;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.bean.Person;
import models.exception.fileformatexception.FileException;
import models.exception.fileformatexception.FileFormatException;
import models.exception.fileformatexception.NotFoundFileException;
import models.factory.UserFactory;
import models.parser.AbstractParser;
import models.parser.DataCleaner;


/**
 * classe permettant de parser un fichier csv representant la liste des utilisateurs
 */
public class ParserCsvUserList extends AbstractParser {

	private List<Person> UserList;
	
	public ParserCsvUserList() {
		this.UserList= new ArrayList<Person>();
	}
	
	/**
	 * Methode demandant a la classe de pasrer une liste de user
	 * @param sourceFile
	 * @throws IOException
	 * @throws FileFormatException 
	 */
	public void ParseUserList(File sourceFile) throws FileException{
		List<String> datas;
		try {
			datas = AbstractParser.readfile(sourceFile);
			
			int size = datas.size(); 						// compte le nombre total de ligne dans le fichier
			int index; 										// pour se deplacer dans le tableau de donn�es source nombre de champ maximum d'une r�ponse
			String[] line = new String[datas.get(0).split(PERSONSPLIT).length]; // donne la taille max du tableau
			checkFormat(PERSON, datas.get(0).split(PERSONSPLIT));
			// on va parcourir chaque lignes et creer un objet Person contenant les informations ad�quates
			for (index = 1; index < size; index++) {
				String data = datas.get(index);
				line = data.split(PERSONSPLIT);
				Person person = UserFactory.createUser(line);// on creer un objet User
				UserList.add(person); 
			}
		} catch (IOException e) {
			throw new NotFoundFileException();
		}

	}
	/**
	 * renvoi la liste des user sans doublon
	 * @return
	 */
	public List<Person> getUserList() {
		return DataCleaner.cleanPerson(UserList);
	}
	
	/**
	 * donne le nombre d'utilisateurs 
	 */
	public int getNbUser(){
		return UserList.size();
	}
	
}

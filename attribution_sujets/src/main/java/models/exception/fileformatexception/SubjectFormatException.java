package models.exception.fileformatexception;


public class SubjectFormatException extends FileFormatException {

	
	public String getMessage() {
		String message ="Format de fichier incompatible avec une liste de sujet.\n"
				+ "Format en vigeur:";
		for(String col : models.parser.AbstractParser.SUBJECTFORMAT)
			message+="\n-"+col;
		return message;
		
		
	};
}

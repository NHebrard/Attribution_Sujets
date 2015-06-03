package models.solver;

import java.io.IOException;
import java.net.URL;

import models.bean.Model;
import models.solver.adaptor.AdaptorGlpk;
import models.solver.adaptor.AdaptorGlpkImpl;
import models.solver.reader.NotFoundSolutionException;
import models.solver.reader.ReaderException;
import models.solver.reader.SolutionReaderGlpk;
import models.solver.writer.InputWriterGlpk;
import models.solver.writer.WriterException;


public class Glpk implements Solver{
	
	@Override
	public Model solve(String inputFilename, String outputFilename, Model data)
			throws WriterException, ReaderException, NotFoundSolutionException {
		
		AdaptorGlpk ag = new AdaptorGlpkImpl(data);
		
		InputWriterGlpk.write(inputFilename, ag);
		
		URL modelFile = this.getClass().getClassLoader().getResource("glpk/intersemestre-modele.mod");
	
		String[] cmd = {"cmd", "/c", "glpsol", "-m", modelFile.getPath().substring(1), "-d", inputFilename, "-w", outputFilename };
		
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		SolutionReaderGlpk.read(outputFilename, data);
		
		return data;
	}

}

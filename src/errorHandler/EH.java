package errorHandler;

import tipo.ErrorType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class EH {

	private List<ErrorType> errors = new ArrayList<ErrorType>();

	private static EH instance = new EH();

	private EH() {
	}

	public static EH getEH() {
		if (instance == null)
			instance = new EH();
		return instance;
	}

	public boolean hasErrors() {
		return !getErrors().isEmpty();
	}

	public void addErrors(ErrorType err) {
		errors.add(err);
	}

	public void showErrors(PrintStream pS) {
		for(ErrorType e:errors) {
			pS.println(e.toString());
		}
	}

	public List<ErrorType> getErrors() {
		return errors;
	}

}

import codegeneration.ExecuteCodeGeneratorVisitor;
import errorHandler.EH;
import visitor.IdentificationVisitor;
import visitor.OffSetVisitor;
import visitor.TypeCheckingVisitor;
import visitor.Visitor;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Pass the name of the input file.");
            return;
        }

        FileReader fr = null;
        try {
            fr = new FileReader(args[0]);
        } catch (IOException io) {
            System.err.println("The file " + args[0] + " could not be opened.");
            return;
        }

        Scanner lexico = new Scanner(fr);
        Parser parser = new Parser(lexico);
        parser.run();

        Visitor i = new IdentificationVisitor();
        Visitor v = new TypeCheckingVisitor();
        Visitor o = new OffSetVisitor();
        Visitor e = new ExecuteCodeGeneratorVisitor(args[0], args[1]);

        parser.getAST().accept(i, null);

        if (EH.getEH().hasErrors()) {
            // * Show errors
            EH.getEH().showErrors(System.err);
        } else {
            parser.getAST().accept(v, null);

            if (EH.getEH().hasErrors()) {
                // * Show errors
                EH.getEH().showErrors(System.err);
            } else {
                parser.getAST().accept(o, null);

                if (EH.getEH().hasErrors()) {
                    // * Show errors
                    EH.getEH().showErrors(System.err);
                } else {
                    parser.getAST().accept(e, null);

                }
            }
        }
    }
}

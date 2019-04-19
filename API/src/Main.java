import org.w3c.dom.Document;
import java.lang.Object;

public class Main {

    public static void main(String[] args) {
        Problem pr = new Problem();
        //pr.testIfFunctioning();
        //System.out.println(pr.getEnuntProblema(1));
        Document doc    = pr.getTeste(1);

        //Apelez metoda serialize care afiseaza pe ercan continutul documentului insa trebuie  sa adaugati jar urile pentru parserul xml
        //File->ProjectStructure->Libraries-> semnul "+" din stanga si adaugati jar urile puse in folderul proiect
       // System.out.println(pr.serialize(doc));
    }

}
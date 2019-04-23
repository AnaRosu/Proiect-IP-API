import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class AddProblem {
    public Connection myConn;

    public AddProblem() {
        this.databaseConnect();
    }

    public static void main(String[] args) throws SQLException {
        AddProblem addedProblem = new AddProblem();
        File xml = new File("/home/iulian/problem.xml");
        addedProblem.addProblem(xml);
    }

    //concectarea la baza de date
    private void databaseConnect() {
        try {
            //am scris dedesupt ce inseamna fiecare
            myConn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/proiectip12345","amrus12345","databaseacces");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    int getAvailableIdForInsertion(PreparedStatement stmt) {
        //depinde daca o sa reciclam id-urile nefolosite sau vom folose autoincrement
        //momentan se gaseste cel mai mai mare id disponibil
        int availableId;
        try {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("maxId") == null)
                    return 1;
                else
                    availableId = Integer.parseInt(rs.getString("maxId"));
                return availableId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }


    public void addProblem(File xmlFile) {
        SAXBuilder builder = new SAXBuilder();
        try {
            //Get available id for problem
            PreparedStatement stmt = myConn.prepareStatement("select max(id) + 1 as maxId  from enunturi_probleme");
            int problemAvailableId = getAvailableIdForInsertion(stmt);

            //Get available id for test
            stmt = myConn.prepareStatement("select max(id) + 1 as maxId  from teste_probleme");
            int testAvailableId = getAvailableIdForInsertion(stmt);

            //Read from xml (using JDom) representing the problem
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("date");
            Element node = (Element) ((List) list).get(0);

            stmt = myConn.prepareStatement("insert into enunturi_probleme values(" +
                    Integer.toString(problemAvailableId) + ",'" +
                    node.getChildText("titlu") + "','" +
                    node.getChildText("enunt") + "','" +
                    node.getChildText("rezolvare") + "'," +
                    node.getChildText("categorie") + ")");
            stmt.executeUpdate();

            list = rootNode.getChildren("teste");
            node = (Element) ((List) list).get(0);
            stmt = myConn.prepareStatement("insert into teste_probleme values(" +
                    Integer.toString(testAvailableId) + "," +
                    Integer.toString(problemAvailableId) + ",'" +
                    node.getChildText("test1_in") + "','" +
                    node.getChildText("test1_out") + "','" +
                    node.getChildText("test2_in") + "','" +
                    node.getChildText("test2_out") + "','" +
                    node.getChildText("test3_in") + "','" +
                    node.getChildText("test3_out") + "','" +
                    node.getChildText("test4_in") + "','" +
                    node.getChildText("test4_out") + "')");
            stmt.executeUpdate();

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

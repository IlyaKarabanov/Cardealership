import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Sale extends DocumentOperations {
    private String clientSurname;
    private String clientName;
    private String clientPatronymic;
    private String clientBirthDate;
    private long passport;

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPatronymic() {
        return clientPatronymic;
    }

    public void setClientPatronymic(String clientPatronymic) {
        this.clientPatronymic = clientPatronymic;
    }

    public String getClientBirthDate() {
        return clientBirthDate;
    }

    public void setClientBirthDate(String clientBirthDate) {
        this.clientBirthDate = clientBirthDate;
    }

    public long getPassport() {
        return passport;
    }

    public void setPassport(long passport) {
        this.passport = passport;
    }

    public void saveXML(String savePath) {
        File file = new File(savePath);
        try{
            PrintWriter cleanWriter = new PrintWriter(new FileWriter(file));
            cleanWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

            PrintWriter printWriter = new PrintWriter(new FileWriter(file,true));
            printWriter.write("<document type = \"sale\">\n");
            printWriter.write("\t<client>\n");
            printWriter.write("\t\t<clientSurname>" + clientSurname + "</clientSurname>\n");
            printWriter.write("\t\t<clientName>" + clientName + "</clientName>\n");
            printWriter.write("\t\t<clientPatronymic>" + clientPatronymic + "</clientPatronymic>\n");
            printWriter.write("\t\t<clientBirthDate>" + clientBirthDate + "</clientBirthDate>\n");
            printWriter.write("\t\t<passport>" + passport + "</passport>\n");
            printWriter.write("\t</client>\n");
            printWriter.write("\t<automobile>\n");
            printWriter.write("\t\t<mark>" + markName + "</mark>\n");
            printWriter.write("\t\t<model>" + modelName + "</model>\n");
            printWriter.write("\t\t<category>" + categoryLetter + "</category>\n");
            printWriter.write("\t\t<dateOfManufacture>" + dateOfManufacture + "</dateOfManufacture>\n");
            printWriter.write("\t\t<vinEngine>" + vinEngine + "</vinEngine>\n");
            printWriter.write("\t\t<vinChassis>" + vinChassis + "</vinChassis>\n");
            printWriter.write("\t\t<vinBody>" + vinBody + "</vinBody\n");
            printWriter.write("\t\t<color>" + colorName + "</color>\n");
            printWriter.write("\t\t<power>" + powerValue + "</power>\n");
            printWriter.write("\t\t<engine>" + engineCapicity + "</engine>\n");
            printWriter.write("\t\t<weight>" + weightValue + "</weight>\n");
            printWriter.write("\t\t<maximumWeight>" + maxWeightValue + "</maximumWeight>\n");
            printWriter.write("\t</automobile>\n");
            printWriter.write("</document>\n");

            cleanWriter.close();
            printWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void automobilesDataBase() {
        super.automobilesDataBase();

        String user = "root";
        String password = "1111";
        String url = "jdbc:mysql://localhost:3306/cardealership?useSSL=false";

        try(Connection conn = DriverManager.getConnection(url,user,password);
            Statement statement = conn.createStatement()) {
            statement.executeUpdate("update automobiles set status = 'N' where vin_engine ='" + vinEngine +
                    "' and vin_chassis = '" + vinChassis + "' and vin_body = '" + vinBody + "' and status = 'Y'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void download(String xmlPath) {
        super.download(xmlPath);
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(xmlPath);

            Element element = document.getDocumentElement();

            clientSurname = element.getElementsByTagName("clientSurname").item(0).getTextContent();
            clientName = element.getElementsByTagName("clientName").item(0).getTextContent();
            clientPatronymic = element.getElementsByTagName("clientPatronymic").item(0).getTextContent();
            clientBirthDate = element.getElementsByTagName("clientBirthDate").item(0).getTextContent();
            passport = Long.parseLong(element.getElementsByTagName("passport").item(0).getTextContent());

            markName = element.getElementsByTagName("mark").item(0).getTextContent();
            modelName = element.getElementsByTagName("model").item(0).getTextContent();
            categoryLetter = element.getElementsByTagName("category").item(0).getTextContent().charAt(0);
            dateOfManufacture = element.getElementsByTagName("dateOfManufacture").item(0).getTextContent();
            vinEngine = element.getElementsByTagName("vinEngine").item(0).getTextContent();
            vinChassis = element.getElementsByTagName("vinChassis").item(0).getTextContent();
            vinBody = element.getElementsByTagName("vinBody").item(0).getTextContent();
            colorName = element.getElementsByTagName("color").item(0).getTextContent();
            powerValue = Integer.parseInt(element.getElementsByTagName("power").item(0).getTextContent());
            engineCapicity = Float.parseFloat(element.getElementsByTagName("engine").item(0).
                    getTextContent());
            weightValue = Integer.parseInt(element.getElementsByTagName("weight").item(0).getTextContent());
            maxWeightValue = Integer.parseInt(element.getElementsByTagName("maximumWeight").item(0).
                    getTextContent());
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}

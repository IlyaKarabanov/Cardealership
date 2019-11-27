import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;

public class Supply extends DocumentOperations{
    private String bodyName;
    private String modificationName;
    private int lengthValue;
    private int widthValue;
    private int heightValue;
    private int wheelbaseValue;
    private int cylindersQuantity;
    private float accelerationTime;
    private float fuelConsumptionCityValue;
    private float fuelConsumptionRouteValue;
    private String transmissionName;
    private char absExist;
    private char cruiseControlExist;
    private String interiorName;
    private char climateControlExist;
    private char airbagsFrontExist;
    private char airbagsSideExist;
    private char statusLetter;

    public String getBodyName() {
        return bodyName;
    }

    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }

    public String getModificationName() {
        return modificationName;
    }

    public void setModificationName(String modificationName) {
        this.modificationName = modificationName;
    }

    public int getLengthValue() {
        return lengthValue;
    }

    public void setLengthValue(int lengthValue) {
        this.lengthValue = lengthValue;
    }

    public int getWidthValue() {
        return widthValue;
    }

    public void setWidthValue(int widthValue) {
        this.widthValue = widthValue;
    }

    public int getHeightValue() {
        return heightValue;
    }

    public void setHeightValue(int heightValue) {
        this.heightValue = heightValue;
    }

    public int getWheelbaseValue() {
        return wheelbaseValue;
    }

    public void setWheelbaseValue(int wheelbaseValue) {
        this.wheelbaseValue = wheelbaseValue;
    }

    public int getCylindersQuantity() {
        return cylindersQuantity;
    }

    public void setCylindersQuantity(int cylindersQuantity) {
        this.cylindersQuantity = cylindersQuantity;
    }

    public float getAccelerationTime() {
        return accelerationTime;
    }

    public void setAccelerationTime(float accelerationTime) {
        this.accelerationTime = accelerationTime;
    }

    public float getFuelConsumptionCityValue() {
        return fuelConsumptionCityValue;
    }

    public void setFuelConsumptionCityValue(float fuelConsumptionCityValue) {
        this.fuelConsumptionCityValue = fuelConsumptionCityValue;
    }

    public float getFuelConsumptionRouteValue() {
        return fuelConsumptionRouteValue;
    }

    public void setFuelConsumptionRouteValue(float getFuelConsumptionRouteValue) {
        this.fuelConsumptionRouteValue = getFuelConsumptionRouteValue;
    }

    public String getTransmissionName() {
        return transmissionName;
    }

    public void setTransmissionName(String transmissionName) {
        this.transmissionName = transmissionName;
    }

    public char getAbsExist() {
        return absExist;
    }

    public void setAbsExist(char absExist) {
        this.absExist = absExist;
    }

    public char getCruiseControlExist() {
        return cruiseControlExist;
    }

    public void setCruiseControlExist(char cruiseControlExist) {
        this.cruiseControlExist = cruiseControlExist;
    }

    public String getInteriorName() {
        return interiorName;
    }

    public void setInteriorName(String interiorName) {
        this.interiorName = interiorName;
    }

    public char getClimateControlExist() {
        return climateControlExist;
    }

    public void setClimateControlExist(char climateControlExist) {
        this.climateControlExist = climateControlExist;
    }

    public char getAirbagsFrontExist() {
        return airbagsFrontExist;
    }

    public void setAirbagsFrontExist(char airbagsFrontExist) {
        this.airbagsFrontExist = airbagsFrontExist;
    }

    public char getAirbagsSideExist() {
        return airbagsSideExist;
    }

    public void setAirbagsSideExist(char airbagsSideExist) {
        this.airbagsSideExist = airbagsSideExist;
    }

    public char getStatusLetter() {
        return statusLetter;
    }

    public void setStatusLetter(char statusLetter) {
        this.statusLetter = statusLetter;
    }

    @Override
    public void automobilesDataBase() {
        super.automobilesDataBase();
        String user = "root";
        String password = "1111";
        String url = "jdbc:mysql://localhost:3306/cardealership?useSSL=false";

        try(Connection conn = DriverManager.getConnection(url,user,password);
            Statement statement = conn.createStatement()) {
            statement.executeUpdate("insert into automobiles set mark = '" + markName + "', model = '" + modelName +
                    "', category = '" + categoryLetter + "', body = '" + bodyName + "', length = '" + lengthValue +
                    "', width ='" + widthValue + "', height ='" + heightValue + "', wheelbase ='" + wheelbaseValue +
                    "', weight ='" + weightValue + "', maximum_weight = '" + maxWeightValue + "', modification = '" +
                    modificationName + "', engine = '" + engineCapicity + "', cylinders = '" + cylindersQuantity +
                    "', power = '" + powerValue + "', acceleration = '" + accelerationTime +
                    "', fuel_consumption_city = '" + fuelConsumptionCityValue + "', fuel_consumption_route = '" +
                    fuelConsumptionRouteValue + "', transmission = '" + transmissionName + "', ABS = '" + absExist +
                    "', cruise_control = '" + cruiseControlExist + "', interior = '" + interiorName +
                    "', climate_control = '" + climateControlExist + "', airbags_front = '" + airbagsFrontExist +
                    "', airbags_side = '" + airbagsSideExist + "', color = '" + colorName +
                    "', date_of_manufacture = '" +  dateOfManufacture + "', vin_engine = '" + vinEngine +
                    "', vin_chassis = '" + vinChassis + "', vin_body = '" + vinBody +"', status = 'Y'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void download(String xmlPath) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(xmlPath);

            Element element = document.getDocumentElement();

            markName = element.getElementsByTagName("mark").item(0).getTextContent();
            modelName = element.getElementsByTagName("model").item(0).getTextContent();
            categoryLetter = element.getElementsByTagName("category").item(0).getTextContent().charAt(0);

            bodyName = element.getElementsByTagName("body").item(0).getTextContent();

            lengthValue = Integer.parseInt(element.getElementsByTagName("length").item(0).getTextContent());
            widthValue = Integer.parseInt(element.getElementsByTagName("width").item(0).getTextContent());
            heightValue = Integer.parseInt(element.getElementsByTagName("height").item(0).getTextContent());
            wheelbaseValue = Integer.parseInt(element.getElementsByTagName("wheelbase").item(0).
                    getTextContent());

            weightValue = Integer.parseInt(element.getElementsByTagName("weight").item(0).getTextContent());
            maxWeightValue = Integer.parseInt(element.getElementsByTagName("maximumWeight").item(0).
                    getTextContent());

            modificationName = element.getElementsByTagName("modification").item(0).getTextContent();

            engineCapicity = Float.parseFloat(element.getElementsByTagName("engine").item(0).
                    getTextContent());

            cylindersQuantity = Integer.parseInt(element.getElementsByTagName("cylinders").item(0).
                    getTextContent());

            powerValue = Integer.parseInt(element.getElementsByTagName("power").item(0).getTextContent());

            accelerationTime = Float.parseFloat(element.getElementsByTagName("acceleration").item(0).
                    getTextContent());

            fuelConsumptionCityValue = Float.parseFloat(element.getElementsByTagName("fuelConsumptionCity").
                    item(0).getTextContent());
            fuelConsumptionRouteValue = Float.parseFloat(element.getElementsByTagName("fuelConsumptionRoute").
                    item(0).getTextContent());

            transmissionName = element.getElementsByTagName("transmission").item(0).getTextContent();
            if (element.getElementsByTagName("ABS").item(0).hasChildNodes())
                absExist = element.getElementsByTagName("ABS").item(0).getTextContent().charAt(0);
            if (element.getElementsByTagName("cruiseControl").item(0).hasChildNodes())
                cruiseControlExist = element.getElementsByTagName("cruiseControl").item(0).getTextContent().
                        charAt(0);
            interiorName = element.getElementsByTagName("interior").item(0).getTextContent();
            if (element.getElementsByTagName("climateControl").item(0).hasChildNodes())
                climateControlExist = element.getElementsByTagName("climateControl").item(0).getTextContent().
                        charAt(0);
            if (element.getElementsByTagName("airbagsFront").item(0).hasChildNodes())
                airbagsFrontExist = element.getElementsByTagName("airbagsFront").item(0).getTextContent().
                        charAt(0);
            if (element.getElementsByTagName("airbagsSide").item(0).hasChildNodes())
                airbagsSideExist = element.getElementsByTagName("airbagsSide").item(0).getTextContent().
                        charAt(0);

            colorName = element.getElementsByTagName("color").item(0).getTextContent();
            dateOfManufacture = element.getElementsByTagName("dateOfManufacture").item(0).getTextContent();
            vinEngine = element.getElementsByTagName("vinEngine").item(0).getTextContent();
            vinChassis = element.getElementsByTagName("vinChassis").item(0).getTextContent();
            vinBody = element.getElementsByTagName("vinBody").item(0).getTextContent();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

}

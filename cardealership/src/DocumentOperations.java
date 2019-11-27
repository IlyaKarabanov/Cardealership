import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public abstract class DocumentOperations {
    protected String markName;
    protected String modelName;
    protected char categoryLetter;
    protected String dateOfManufacture;
    protected String vinEngine;
    protected String vinChassis;
    protected String vinBody;
    protected String colorName;
    protected int powerValue;
    protected float engineCapicity;
    protected int weightValue;
    protected int maxWeightValue;

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public char getCategoryLetter() {
        return categoryLetter;
    }

    public void setCategoryLetter(char categoryLetter) {
        this.categoryLetter = categoryLetter;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getVinEngine() {
        return vinEngine;
    }

    public void setVinEngine(String vinEngine) {
        this.vinEngine = vinEngine;
    }

    public String getVinChassis() {
        return vinChassis;
    }

    public void setVinChassis(String vinChassis) {
        this.vinChassis = vinChassis;
    }

    public String getVinBody() {
        return vinBody;
    }

    public void setVinBody(String vinBody) {
        this.vinBody = vinBody;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(int powerValue) {
        this.powerValue = powerValue;
    }

    public float getEngineCapicity() {
        return engineCapicity;
    }

    public void setEngineCapicity(float engineCapicity) {
        this.engineCapicity = engineCapicity;
    }

    public int getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(int weightValue) {
        this.weightValue = weightValue;
    }

    public int getMaxWeightValue() {
        return maxWeightValue;
    }

    public void setMaxWeightValue(int maxWeightValue) {
        this.maxWeightValue = maxWeightValue;
    }

    public void automobilesDataBase() {}

    public void download(String xmlPath) {}

    public boolean validation(String xmlPath, String schemaPath) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            File schemaFile = new File(schemaPath);
            File xmlFile = new File (xmlPath);
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFile);
            validator.validate(source);
            return true;
        } catch (SAXException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

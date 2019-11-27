import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FilenameFilter;
import java.sql.*;
import java.util.Scanner;

public class SaleForm extends JFrame {
    private JLabel labelSurname = new JLabel("Surname");
    private JTextField textFieldSurname = new JTextField();
    private JLabel labelName = new JLabel("Name");
    private JTextField textFieldName = new JTextField();
    private JLabel labelPatronymic = new JLabel("Patronymic");
    private JTextField textFieldPatronymic = new JTextField();
    private JLabel labelBirthDate = new JLabel("BirthDate");
    private JLabel labelBirthYear = new JLabel("Year");
    private JSpinner spinnerYear = new JSpinner();
    private JLabel labelBirthMonth = new JLabel("Month");
    private JSpinner spinnerMonth = new JSpinner();
    private JLabel labelBirthDay = new JLabel("Day");
    private JSpinner spinnerDay = new JSpinner();
    private JLabel labelPassport = new JLabel("Passport");
    private JTextField textFieldPassport = new JTextField();
    private JLabel labelId = new JLabel("AutomobileId");
    private JSpinner spinnerId = new JSpinner();
    private JButton buttonOk = new JButton("Ok");
    private JButton buttonSave = new JButton("Save");

    public SaleForm() {
        this.setLayout(new GridLayout(0,2));

        this.add(labelSurname);
        this.add(textFieldSurname);
        this.add(textFieldSurname);
        this.add(labelName);
        this.add(textFieldName);
        this.add(labelPatronymic);
        this.add(textFieldPatronymic);
        this.add(labelBirthDate);

        JPanel panel1 = new JPanel(new GridLayout(1,6));
        panel1.add(labelBirthYear);
        panel1.add( spinnerYear);
        panel1.add(labelBirthMonth);
        panel1.add(spinnerMonth);
        panel1.add(labelBirthDay);
        panel1.add(spinnerDay);
        this.add(panel1);

        this.add(labelPassport);
        this.add(textFieldPassport);

        JPanel panel2 = new JPanel(new GridLayout(1,2));
        panel2.add(labelId);
        panel2.add(spinnerId);
        this.add(panel2);

        JPanel panel3 = new JPanel(new GridLayout(1,2));
        panel3.add(buttonOk);
        panel3.add(buttonSave);
        this.add(panel3);

        buttonOk.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                Scanner in = null;

                if (textFieldSurname.getText().trim().length()>0 &
                        textFieldName.getText().trim().length()>0 &
                        textFieldPatronymic.getText().trim().length()>0 &
                        textFieldPassport.getText().trim().length() == 11 &
                        Integer.parseInt(spinnerDay.getValue().toString())>0 &
                        Integer.parseInt(spinnerDay.getValue().toString())<=31 &
                        Integer.parseInt(spinnerMonth.getValue().toString())>0 &
                        Integer.parseInt(spinnerMonth.getValue().toString())<=12 &
                        Integer.parseInt(spinnerYear.getValue().toString())>1900 &
                        Integer.parseInt(spinnerYear.getValue().toString())<=2018)
                {
                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

                        Sale sale = new Sale();

                        sale.setClientSurname(textFieldSurname.getText());
                        sale.setClientName(textFieldName.getText());
                        sale.setClientPatronymic(textFieldPatronymic.getText());

                        String date =  spinnerYear.getValue() + "-" + spinnerMonth.getValue() + "-" + spinnerDay.getValue();

                        sale.setClientBirthDate(date);
                        sale.setPassport(Long.parseLong(textFieldPassport.getText()));

                        String user = "root";
                        String password = "root";
                        String url = "jdbc:mysql://localhost:3306/cardealership?useSSL=false";

                        try(Connection conn = DriverManager.getConnection(url,user,password);
                            Statement statement = conn.createStatement()) {
                            ResultSet resultSet = statement.executeQuery("select * from automobiles where id = " +
                                    spinnerId.getValue().toString());
                            while (resultSet.next())
                            {
                                sale.setMarkName(resultSet.getString("mark"));
                                sale.setModelName(resultSet.getString("model"));
                                sale.setCategoryLetter(resultSet.getString("category").charAt(0));
                                sale.setDateOfManufacture(resultSet.getString("date_of_manufacture"));
                                sale.setVinEngine(resultSet.getString("vin_engine"));
                                sale.setVinChassis(resultSet.getString("vin_chassis"));
                                sale.setVinBody(resultSet.getString("vin_body"));
                                sale.setColorName(resultSet.getString("color"));
                                sale.setPowerValue(Integer.parseInt(resultSet.getString("power")));
                                sale.setEngineCapicity(Float.parseFloat(resultSet.getString("engine")));
                                sale.setWeightValue(Integer.parseInt(resultSet.getString("weight")));
                                sale.setMaxWeightValue(Integer.parseInt(resultSet.getString("maximum_weight")));

                                System.out.println("mark");
                                System.out.println(resultSet.getString("mark"));
                                System.out.println(resultSet.getString("model"));
                            }

                        }catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        sale.saveXML(fileChooser.getSelectedFile().getAbsolutePath());
                        sale.download(fileChooser.getSelectedFile().getAbsolutePath());
                        sale.automobilesDataBase();

                        String filename = fileChooser.getSelectedFile().toString();
                        if (!filename.endsWith(".xml"))
                            filename += ".xml";
                        fileChooser.setName(filename);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Incorrect values");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        buttonSave.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                Scanner in = null;

                if (textFieldSurname.getText().trim().length()>0 &
                        textFieldName.getText().trim().length()>0 &
                        textFieldPatronymic.getText().trim().length()>0 &
                        textFieldPassport.getText().trim().length() == 11 &
                        Integer.parseInt(spinnerDay.getValue().toString())>0 &
                        Integer.parseInt(spinnerDay.getValue().toString())<=31 &
                        Integer.parseInt(spinnerMonth.getValue().toString())>0 &
                        Integer.parseInt(spinnerMonth.getValue().toString())<=12 &
                        Integer.parseInt(spinnerYear.getValue().toString())>1900 &
                        Integer.parseInt(spinnerYear.getValue().toString())<=2018)
                {
                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

                        Sale sale = new Sale();

                        sale.setClientSurname(textFieldSurname.getText());
                        sale.setClientName(textFieldName.getText());
                        sale.setClientPatronymic(textFieldPatronymic.getText());

                        String date =  spinnerYear.getValue() + "-" + spinnerMonth.getValue() + "-" + spinnerDay.getValue();

                        sale.setClientBirthDate(date);
                        sale.setPassport(Long.parseLong(textFieldPassport.getText()));

                        String user = "root";
                        String password = "root";
                        String url = "jdbc:mysql://localhost:3306/cardealership?useSSL=false";

                        try(Connection conn = DriverManager.getConnection(url,user,password);
                            Statement statement = conn.createStatement()) {
                            ResultSet resultSet = statement.executeQuery("select * from automobiles where id = " +
                                    spinnerId.getValue().toString());
                            while (resultSet.next())
                            {
                                sale.setMarkName(resultSet.getString("mark"));
                                sale.setModelName(resultSet.getString("model"));
                                sale.setCategoryLetter(resultSet.getString("category").charAt(0));
                                sale.setDateOfManufacture(resultSet.getString("date_of_manufacture"));
                                sale.setVinEngine(resultSet.getString("vin_engine"));
                                sale.setVinChassis(resultSet.getString("vin_chassis"));
                                sale.setVinBody(resultSet.getString("vin_body"));
                                sale.setColorName(resultSet.getString("color"));
                                sale.setPowerValue(Integer.parseInt(resultSet.getString("power")));
                                sale.setEngineCapicity(Float.parseFloat(resultSet.getString("engine")));
                                sale.setWeightValue(Integer.parseInt(resultSet.getString("weight")));
                                sale.setMaxWeightValue(Integer.parseInt(resultSet.getString("maximum_weight")));

                                System.out.println("mark");
                                System.out.println(resultSet.getString("mark"));
                                System.out.println(resultSet.getString("model"));
                            }

                        }catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        sale.saveXML(fileChooser.getSelectedFile().getAbsolutePath());

                        String filename = fileChooser.getSelectedFile().toString();
                        if (!filename.endsWith(".xml"))
                            filename += ".xml";
                        fileChooser.setName(filename);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Incorrect values");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

public class Application extends JFrame{
    private JMenuBar menuBar = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JButton buttonDownload = new JButton("Download");
    private JButton buttonSale = new JButton("Sale");
    private JToolBar toolBarMain = new JToolBar();
    private JButton buttonSearch = new JButton("search");
    private JTextField textFieldSearch = new JTextField();
    private JToolBar toolBar = new JToolBar();
    private JPanel panel = new JPanel();

    public void dataBase(JPanel panel){


        String user = "root";
        String password = "1111";
        String url = "jdbc:mysql://localhost:3306/cardealership";
        try(Connection conn = DriverManager.getConnection(url,user,password);
            Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from automobiles");
            ResultSetMetaData rsmt = resultSet.getMetaData();
            int columnCount = rsmt.getColumnCount();
            Vector column = new Vector (columnCount);
            for (int i = 1; i <= columnCount; i++) {
                column.add(rsmt.getColumnName(i));
            }
            Vector data = new Vector();
            Vector row = new Vector();
            while (resultSet.next()) {
                row = new Vector(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }
                data.add(row);
            }
            JTable table = new JTable(data,column){
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            };

            table.getColumnModel().getColumn(27).setMinWidth(140);
            table.getColumnModel().getColumn(28).setMinWidth(140);
            table.getColumnModel().getColumn(29).setMinWidth(140);

            table.getTableHeader().setReorderingAllowed(false);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            JScrollPane jsp = new JScrollPane(table);
            panel.setLayout(new BorderLayout());
            panel.add(jsp,BorderLayout.CENTER);
            this.add(panel,BorderLayout.CENTER);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Application() {
        this.setJMenuBar(menuBar);
        menuBar.add(file);

        toolBarMain.add(buttonDownload);
        toolBarMain.add(buttonSale);
        toolBarMain.setFloatable(false);
        this.add(toolBarMain,BorderLayout.NORTH);

        buttonDownload.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new
                        JFileChooser("documents");
                Scanner in = null;

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

                    Supply supply = new Supply();

                    if (supply.validation(fileChooser.getSelectedFile().getAbsolutePath(),
                            "validation/supply.xsd")){
                        supply.download(fileChooser.getSelectedFile().getAbsolutePath());
                        supply.automobilesDataBase();
                    } else {
                        Sale sale = new Sale();

                        if (supply.validation(fileChooser.getSelectedFile().getAbsolutePath(),
                                "validation/sale.xsd"))
                        {
                            sale.download(fileChooser.getSelectedFile().getAbsolutePath());
                            sale.automobilesDataBase();
                        } else {
                            JOptionPane.showMessageDialog(null,"Invalid file");
                        }
                    }


                }
                panel.invalidate();
                panel.validate();
                panel.repaint();
                //repaint();
                dataBase(panel);
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

        buttonSale.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SaleForm saleForm = new SaleForm();
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

        dataBase(panel);

        this.setSize(640,480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
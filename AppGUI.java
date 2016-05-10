import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Przemek on 2016-05-09.
 */
public class AppGUI {
    //ON CREATION:
    OrderDB orderDB = new OrderDB();
    private JTabbedPane tabbedPane;
    private JPanel mainJPanel;
    private JPanel staffPane;
    private JPanel mechanicPane;
    private JPanel clientPane;
    private JList staffOrdersList;
    private JTextArea staffProblemTextArea;
    private JTextField staffNameTextField;
    private JTextField staffPhoneTextField;
    private JTextField staffEmailTextField;
    private JTextField staffSurnameTextField;
    private JTextField staffModelTextField;
    private JTextField staffProductionTextField;
    private JTextField staffMileageTextField;
    private JTextField staffMakeTextField;
    private JComboBox staffWorkersComboBox;
    private JButton staffAssignWorkerButton;
    private JLabel staffOrdersLabel;
    private JLabel staffNameLabel;
    private JLabel staffPhoneLabel;
    private JLabel staffEmailLabel;
    private JLabel staffSurnameLabel;
    private JLabel staffMakeLabel;
    private JLabel staffModelLabel;
    private JLabel staffProductionLabel;
    private JLabel staffMileageLabel;
    private JComboBox mechanicSelectWorkerComboBox;
    private JList mechanicOrdersList;
    private JTextField mechanicMakeTextField;
    private JTextField mechanicModelTextField;
    private JTextField mechanicYearTextField;
    private JTextField mechanicMileageTextField;
    private JTextArea mechanicProblemTextArea;
    private JTable mechanicFixesTable;
    private JTextField mechanicTotalPriceTextField;
    private JButton mechanicSavePriceButton;
    private JComboBox mechanicChangeStatusComboBox;
    private JTextField clientNameTextField;
    private JTextField clientSurnameTextField;
    private JTextField clientEmailTextField;
    private JTextField clientPhoneTextField;
    private JTextField clientMakeTextField;
    private JTextField clientModelTextField;
    private JTextField clientYearTextField;
    private JTextField clientMileageTextField;
    private JButton clientNewOrderButton;
    private JTextArea clientProblemTextArea;
    private JPanel clientNewOrderPanel;
    private JButton clientCheckOrderButton;
    private JTextField clientStatusTextField;
    private JTextField clientPriceTextField;
    private JTextArea clientRepairTextArea;
    private JTextField clientOrderIDTextField;
    private JLabel mechanicMakeLabel;
    private JLabel mechanicModelLabel;
    private JLabel mechanicYearLabel;
    private JLabel mechanicMileageLabel;
    private JScrollPane mechanicOrdersListScrollPane;
    private JScrollPane mechanicProblemTextAreaScrollPane;
    private JPanel mechanicFixesTablePanel;
    private JPanel mechanicBottomMenuPanel;
    private JLabel mechanicTotalPriceLabel;
    private JLabel mechanicChangeStatusLabel;
    private JPanel clientCheckOrderPanel;
    private JLabel clientNameLabel;
    private JLabel clientSurnameLabel;
    private JLabel clientEmailLabel;
    private JLabel clientPhoneLabel;
    private JLabel clientMakeLabel;
    private JLabel clientModelLabel;
    private JLabel clientProductionYearLabel;
    private JLabel clientMileageLabel;
    private JLabel clientProblemLabel;
    private JScrollPane clientProblemTextAreaScrollPane;
    private JLabel clientStatusLabel;
    private JLabel clientPriceLabel;
    private JLabel clientRepairLabel;
    private JLabel clientOrderIDLabel;
    private JScrollPane clientRepairTextAreaScrollPane;
    private JScrollPane mechanicFixesTableScrollPane;


    public AppGUI() {
        staffAssignWorkerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //staff tab -> klikniecie przycisku assign worker (przypisz wybrane zamowienie do wybranego z listy workera)
            }
        });
        staffOrdersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //staff tab -> wybranie elementu z listy order (wypelnij pola szczegolami z wybranego zamowienia)
            }
        });
        mechanicSelectWorkerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mechanic -> wybrano mechanika z comboboxa, dodać do listy pod comboboxem listę orderów dla tego mechanika. dać na czerwono te, które nie są skończone
            }
        });
        mechanicOrdersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //mechanic -> wybrano z listy orderow jakis order, wyswietlic jego szczegoly w polach obok + jezeli ma okreslone to rowniez total price i co jest naprawiane odpowiednio ustawic
            }
        });

        mechanicFixesTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int column = e.getColumn();
                int row = e.getFirstRow();
                int price = (int)mechanicFixesTable.getValueAt(row,column-1);
                String oldPriceString = mechanicTotalPriceTextField.getText();
                int oldPrice;
                if(oldPriceString.isEmpty()){
                    oldPrice = 0;
                } else {
                    oldPrice = Integer.parseInt(oldPriceString);
                }

                int newPrice;

                if((boolean)mechanicFixesTable.getValueAt(row,column)){
                     newPrice = price + oldPrice;
                } else {
                    newPrice = oldPrice - price;
                }
                mechanicTotalPriceTextField.setText(String.valueOf(newPrice));
            }
        });
        mechanicSavePriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mechanic -> klikniecie w przycisk saveprice, pobiera cene z total price i zapisuje w zamowieniu.
                // pobiera rowniez te checkboxy ktore sa zaznaczone i zapisuje w opisie zamowienia co i za ile jest naprawiane oraz jaki aktualnie ma stan
            }
        });
        mechanicChangeStatusComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mechanic -> zmieniono stan zamowienia, pobrac wybrany stan i zapisac w szczegolach zamowienia
            }
        });

        clientNewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //client -> kliknieto New Order. Mozna sprawdzic czy wypelniono wszystkie dane, utworzyc nowe zamowienie z tymi danymi. Uzytkownikowi wyswietlic id tego zamowienia.
                //powinno zaktualizować listę Order'ów w zakładce Staff
                // nie wiem czy patryk tworzy to id jakos czy nie, w kazdym razie dzieki niemu bedzie mozna znalezc to zamowienie potem

                //Adding new order
                Client client = new Client(
                        clientNameTextField.getText(),
                        clientSurnameTextField.getText(),
                        clientEmailLabel.getText(),
                        clientPhoneLabel.getText(),
                        orderDB);

                int id = client.addOrder(
                        clientMakeTextField.getText(),
                        clientModelTextField.getText(),
                        clientYearTextField.getText(),
                        clientMileageTextField.getText(),
                        clientProblemTextArea.getText());
                JOptionPane.showMessageDialog(new JFrame(), "Write down your order ID: " + id);

                //Updating orders in StaffPane
                ArrayList<Order> orderList = orderDB.getDatabase();
                System.out.print(orderList);
                DefaultListModel<String> model = new DefaultListModel<>();

                for(Order order : orderList){
                    model.addElement(Integer.toString(order.getId()));
                    System.out.print(model);
                }
                staffOrdersList.setModel(model);
            }
        });

        clientCheckOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //client -> kliknieto Check Order. Pobrac wpisany order ID, znalezc odpowiednie zamowienie. Jesli jest to pobrac jego dane i wyswietlic w odpowiednich polach.
                Order order = orderDB.getOrderByID(Integer.parseInt(clientOrderIDTextField.getText()));
                clientStatusTextField.setText(order.getStatus());
                clientPriceTextField.setText(Integer.toString(order.getCost()));
                clientRepairTextArea.setText(order.getRepairDetails());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Warsztat Samochodowy GRAT");
        frame.setContentPane(new AppGUI().mainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible(true);



    }

    private void createUIComponents() {
        /*
        TUTAJ JEST PRZYKLADOWO ZROBIONE TWORZENIE COMBOBOXA, TRZEBA WZIAC BAZE WORKEROW I DODAC ICH DO LISTY, dalem wektor bo wlasciwie na zwyklej liscie nie chcialo dzialac
         */
        Vector<String> workersList = new Vector<>();
        workersList.add("Worker 1");
        workersList.add("Worker 2");
        staffWorkersComboBox = new JComboBox(workersList);

        /*
        Tak samo jak wyzej, tutaj tez trzeba wrzucic workerow z bazy. W zasadzie jak wczytasz ich do workersList to ponizej nic nie musisz zmieniac.
         */

        mechanicSelectWorkerComboBox = new JComboBox(workersList);

        /*
        tworzenie tabeli w ktorej beda wszystkie dostepne w warsztacie naprawy, trzeba zmienić object rowdata tak aby wczytywala z bazy dostepnych napraw (nazwe i cene)
         */

        Object rowData[][] = { { "Engine repair",100, Boolean.FALSE }, { "Tires change",200, Boolean.FALSE }, { "Nazwa 3",300, Boolean.FALSE },
                { "Nazwa 4",400,Boolean.FALSE }, { "Nazwa 5",500,Boolean.FALSE }, };

        String columnNames[] = { "Repair Name", "Price", "Add to order" };
        TableModel dataModel = new DefaultTableModel(rowData,columnNames);
        mechanicFixesTable = new JTable(dataModel){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                if(column==0 || column==1)return false;
                return true;
            }
        };
        mechanicFixesTableScrollPane = new JScrollPane(mechanicFixesTable);
        TableColumn tc = mechanicFixesTable.getColumnModel().getColumn(2);
        tc.setCellEditor(mechanicFixesTable.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(mechanicFixesTable.getDefaultRenderer(Boolean.class));

        /*
        Tworzenie comboboxa z lista statusow. Trzeba zmienic aby wczytywalo z bazy dostepnych statusow.
         */

        Vector<String> statusList = new Vector<>();
        statusList.add("Status 1");
        statusList.add("Status 2");
        mechanicChangeStatusComboBox = new JComboBox(statusList);

    }
}

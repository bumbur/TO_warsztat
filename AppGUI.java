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
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * Created by Przemek on 2016-05-09.
 */

public class AppGUI {
    private static final Logger log = Logger.getLogger(Logger.class.getName());
    //ON CREATION:
    private static final OrderDB orderDB = new OrderDB();
    private static final MechanicDB mechanicDB = new MechanicDB();
    private static Staff staff = new Staff("Pan", "Kierownik", orderDB, mechanicDB);
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
                Mechanic mechanic = (Mechanic) mechanicSelectWorkerComboBox.getSelectedItem();
                int selectedOrderId = Integer.parseInt(staffOrdersList.getSelectedValue().toString());
                Order selectedOrder = orderDB.getOrderByID(selectedOrderId);

                staff.setMechanicToOrder(selectedOrder, mechanic);

                //staff tab -> klikniecie przycisku assign worker (przypisz wybrane zamowienie do wybranego z listy workera)
            }
        });

        staffOrdersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedOrderId = Integer.parseInt(staffOrdersList.getSelectedValue().toString());
                log.info("Selected order ID: " + selectedOrderId);
                Order selectedOrder = orderDB.getOrderByID(selectedOrderId);
                Client customer = selectedOrder.getCustomer();

                staffNameTextField.setText(customer.getName());
                staffSurnameTextField.setText(customer.getSurname());
                staffPhoneTextField.setText(customer.getPhone());
                staffEmailTextField.setText(customer.getEmail());

                staffMakeTextField.setText(selectedOrder.getMark());
                staffModelTextField.setText(selectedOrder.getModel());
                staffProductionTextField.setText(selectedOrder.getYearOfCar());
                staffMileageTextField.setText(selectedOrder.getMileage());
                staffProblemTextArea.setText(selectedOrder.getDescription());

                //staff tab -> wybranie elementu z listy order (wypelnij pola szczegolami z wybranego zamowienia)
            }
        });

        mechanicSelectWorkerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mechanic -> wybrano mechanika z comboboxa, dodać do listy pod comboboxem listę orderów dla tego mechanika. dać na czerwono te, które nie są skończone
                Mechanic mechanic = (Mechanic) mechanicSelectWorkerComboBox.getSelectedItem();
                ArrayList<Order> orderList = mechanic.getOrdersForMechanic();
                DefaultListModel<String> model = new DefaultListModel<>();

                for (Order order : orderList) {
                    model.addElement(Integer.toString(order.getId()));
                }
                mechanicOrdersList.setModel(model);


            }
        });

        mechanicOrdersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //mechanic -> wybrano z listy orderow jakis order, wyswietlic jego szczegoly w polach obok + jezeli ma okreslone to rowniez total price i co jest naprawiane odpowiednio ustawic
                int selectedOrderId = Integer.parseInt(mechanicOrdersList.getSelectedValue().toString());
                Order selectedOrder = orderDB.getOrderByID(selectedOrderId);

                mechanicMakeTextField.setText(selectedOrder.getMark());
                mechanicModelTextField.setText(selectedOrder.getModel());
                mechanicYearTextField.setText(selectedOrder.getYearOfCar());
                mechanicMileageTextField.setText(selectedOrder.getMileage());
                mechanicProblemTextArea.setText(selectedOrder.getDescription());

            }
        });

        mechanicFixesTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int column = e.getColumn();
                int row = e.getFirstRow();
                int price = (int) mechanicFixesTable.getValueAt(row, column - 1);
                String oldPriceString = mechanicTotalPriceTextField.getText();
                int oldPrice;
                if (oldPriceString.isEmpty()) {
                    oldPrice = 0;
                } else {
                    oldPrice = Integer.parseInt(oldPriceString);
                }

                int newPrice;

                if ((boolean) mechanicFixesTable.getValueAt(row, column)) {
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
                        clientEmailTextField.getText(),
                        clientPhoneTextField.getText(),
                        orderDB);

                int id = client.addOrder(
                        clientMakeTextField.getText(),
                        clientModelTextField.getText(),
                        clientYearTextField.getText(),
                        clientMileageTextField.getText(),
                        clientProblemTextArea.getText(),
                        client);
                JOptionPane.showMessageDialog(new JFrame(), "Write down your order ID: " + id);

                //Updating orders in StaffPane
                ArrayList<Order> orderList = orderDB.getDatabase();
                DefaultListModel<String> model = new DefaultListModel<>();

                for (Order order : orderList) {
                    model.addElement(Integer.toString(order.getId()));
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
        createSomeStuff();
        JFrame frame = new JFrame("Warsztat Samochodowy GRAT");
        frame.setContentPane(new AppGUI().mainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible(true);
    }

    private static void createSomeStuff() {
        //MechanicDB mechanicDB = new MechanicDB();
        mechanicDB.addMechanic(new Mechanic("Popek", "Król", staff.getAssignedOrdersToMechanics()));
        mechanicDB.addMechanic(new Mechanic("Andrzej", "Niedenerwujsie", staff.getAssignedOrdersToMechanics()));
        mechanicDB.addMechanic(new Mechanic("Mateusz", "Wajcheprzełóż", staff.getAssignedOrdersToMechanics()));
        Client c1 = new Client("Julek", "Kret", "ja@ja.ja", "123456", orderDB);
        c1.addOrder("Ford", "Mustang", "1978", "100k", "Do not start.", c1);
        c1.addOrder("Mercedes", "G", "1999", "20k", "Gearbox not shifting smooothly", c1);
        c1.addOrder("Bugatti", "Veyron", "2015", "40k", "Engine controll shows.", c1);
        Client c2 = new Client("Wojtek", "Marek", "ja@xd.lol", "6732623", orderDB);
        c2.addOrder("Ford", "Transit", "2005", "200k", "Damaged", c2);
    }

    private void createUIComponents() {
        /*
        TUTAJ JEST PRZYKLADOWO ZROBIONE TWORZENIE COMBOBOXA, TRZEBA WZIAC BAZE WORKEROW I DODAC ICH DO LISTY,
        dalem wektor bo wlasciwie na zwyklej liscie nie chcialo dzialac
         */


        ArrayList<Mechanic> mechanicList = mechanicDB.getDatabase();
        Vector<Mechanic> mech = new Vector<>(mechanicList);

        staffWorkersComboBox = new JComboBox(mech);
        mechanicSelectWorkerComboBox = new JComboBox(mech);
//        for(Mechanic mechanic : mechanicList){
//            staffWorkersComboBox.addItem(mechanic.getName() + " " + mechanic.getSurname());
//        }

        /*
        tworzenie tabeli w ktorej beda wszystkie dostepne w warsztacie naprawy, trzeba zmienić object rowdata tak aby wczytywala z bazy dostepnych napraw (nazwe i cene)
         */

        Object rowData[][] = {{"Engine repair", 600, Boolean.FALSE},
                {"Tires change", 200, Boolean.FALSE},
                {"Oil replacement", 300, Boolean.FALSE},
                {"Gearbox repair", 400, Boolean.FALSE},
                {"AC refreshment", 100, Boolean.FALSE},};

        String columnNames[] = {"Repair Name", "Price", "Add to order"};
        TableModel dataModel = new DefaultTableModel(rowData, columnNames);
        mechanicFixesTable = new JTable(dataModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1) return false;
                return true;
            }
        };
        mechanicFixesTableScrollPane = new JScrollPane(mechanicFixesTable);
        TableColumn tc = mechanicFixesTable.getColumnModel().getColumn(2);
        tc.setCellEditor(mechanicFixesTable.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(mechanicFixesTable.getDefaultRenderer(Boolean.class));

        /*
        Tworzenie comboboxa z lista statusow.
         */

        Vector<String> statusList = new Vector<>();
        for (OrderState w : EnumSet.allOf(OrderState.class)) {
            statusList.add(w.toString());
        }
        mechanicChangeStatusComboBox = new JComboBox(statusList);

    }
}

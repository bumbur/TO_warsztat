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
import java.util.*;

public class AppGUI {
    //ON CREATION:
    private static final OrderDB orderDB = new OrderDB();
    private static final MechanicDB mechanicDB = new MechanicDB();
    private static Staff staff = new Staff("Pan", "Kierownik", orderDB, mechanicDB);
    private JTabbedPane tabbedPane;
    private JPanel mainJPanel;
    private JPanel staffPane;
    private JPanel mechanicPane;
    private JPanel clientPane;
    private JList<String> staffOrdersList;
    private JTextArea staffProblemTextArea;
    private JTextField staffNameTextField;
    private JTextField staffPhoneTextField;
    private JTextField staffEmailTextField;
    private JTextField staffSurnameTextField;
    private JTextField staffModelTextField;
    private JTextField staffProductionTextField;
    private JTextField staffMileageTextField;
    private JTextField staffMakeTextField;
    private JComboBox<Mechanic> staffWorkersComboBox;
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
    private JComboBox<Mechanic> mechanicSelectWorkerComboBox;
    private JList<String> mechanicOrdersList;
    private JTextField mechanicMakeTextField;
    private JTextField mechanicModelTextField;
    private JTextField mechanicYearTextField;
    private JTextField mechanicMileageTextField;
    private JTextArea mechanicProblemTextArea;
    private JTable mechanicFixesTable;
    private JTextField mechanicTotalPriceTextField;
    private JButton mechanicSavePriceButton;
    private JComboBox<String> mechanicChangeStatusComboBox;
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
        /**
         * STAFF TAB
         * On click button "Assign worker"
         * Assigns Mechanic to Order.
         */
        staffAssignWorkerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mechanic mechanic = (Mechanic) staffWorkersComboBox.getSelectedItem();
                int selectedOrderId = Integer.parseInt(staffOrdersList.getSelectedValue().toString());
                Order selectedOrder = orderDB.getOrderByID(selectedOrderId);
                staff.setMechanicToOrder(selectedOrder, mechanic);
            }
        });
        /**
         * STAFF TAB
         * On choose element from Orders list
         * Shows orders details.
         */
        staffOrdersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedOrderId = Integer.parseInt(staffOrdersList.getSelectedValue().toString());
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
            }
        });
        /**
         * MECHANIC TAB
         * On choose Mechanic from ComboBox
         * Shows unfinished Orders assigned to this Mechanic
         */
        mechanicSelectWorkerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mechanic mechanic = (Mechanic) mechanicSelectWorkerComboBox.getSelectedItem();
                ArrayList<Order> orderList = mechanic.getOrdersForMechanic();
                DefaultListModel<String> model = new DefaultListModel<>();

                for (Order order : orderList) {
                    if (order.getState() != OrderState.DONE) {
                        model.addElement(Integer.toString(order.getId()));
                    }
                }
                mechanicOrdersList.setModel(model);
            }
        });
        /**
         * MECHANIC TAB
         * On choose Order from Order list
         * Display Order Details
         */
        mechanicOrdersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (mechanicOrdersList.getSelectedValue() != null) {
                    int selectedOrderId = Integer.parseInt(mechanicOrdersList.getSelectedValue().toString());
                    Order selectedOrder = orderDB.getOrderByID(selectedOrderId);

                    mechanicMakeTextField.setText(selectedOrder.getMark());
                    mechanicModelTextField.setText(selectedOrder.getModel());
                    mechanicYearTextField.setText(selectedOrder.getYearOfCar());
                    mechanicMileageTextField.setText(selectedOrder.getMileage());
                    mechanicProblemTextArea.setText(selectedOrder.getDescription());
                    mechanicTotalPriceTextField.setText(String.valueOf(selectedOrder.getCost()));

                }
            }
        });
        /**
         * MECHANIC TAB
         * Table with repairs.
         */
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
        /**
         * MECHANIC TAB
         * On click "Save Price"
         * Save Price and Repairs in Order details.
         */
        mechanicSavePriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedOrderId = Integer.parseInt(mechanicOrdersList.getSelectedValue().toString());
                Order selectedOrder = orderDB.getOrderByID(selectedOrderId);
                int price = Integer.parseInt(mechanicTotalPriceTextField.getText());
                selectedOrder.setCost(price);

                HashMap<String, Integer> repairs = new HashMap<String, Integer>();
                for (int row = 0; row < mechanicFixesTable.getRowCount(); row++) {
                    if ((boolean) mechanicFixesTable.getValueAt(row, 2)) {
                        int cost = (int) mechanicFixesTable.getValueAt(row, 1);
                        String work = mechanicFixesTable.getValueAt(row, 0).toString();
                        repairs.put(work, cost);
                    }
                }
                selectedOrder.setRepairs(repairs);
            }
        });
        /**
         * MECHANIC TAB
         * On OrderState change - changes OrderState.
         */
        mechanicChangeStatusComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderState state = OrderState.valueOf(mechanicChangeStatusComboBox.getSelectedItem().toString());
                int selectedOrderId = Integer.parseInt(mechanicOrdersList.getSelectedValue().toString());
                Order selectedOrder = orderDB.getOrderByID(selectedOrderId);
                selectedOrder.setState(state);
            }
        });
        /**
         * CLIENT TAB
         * On click "New Order"
         * Create new Order with provided details.
         * Refreshes Orders list in staff tab.
         */
        clientNewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        /**
         * CLIENT TAB
         * On click "Chceck Order"
         * Return info fo order with provided ID
         */
        clientCheckOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order = orderDB.getOrderByID(Integer.parseInt(clientOrderIDTextField.getText()));
                clientStatusTextField.setText(order.getStatus());
                clientPriceTextField.setText(Integer.toString(order.getCost()));
                clientRepairTextArea.setText(order.getRepairs().toString());
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
        mechanicDB.addMechanic(new Mechanic("Andrzej", "Klucz", staff.getAssignedOrdersToMechanics()));
        mechanicDB.addMechanic(new Mechanic("Janusz", "Wiertarka", staff.getAssignedOrdersToMechanics()));
        mechanicDB.addMechanic(new Mechanic("Jan", "Nowak", staff.getAssignedOrdersToMechanics()));
        Client c1 = new Client("Julek", "Kret", "ja@ja.ja", "123456", orderDB);
        c1.addOrder("Ford", "Mustang", "1978", "100k", "Do not start.", c1);
        c1.addOrder("Mercedes", "G", "1999", "20k", "Gearbox not shifting smooothly", c1);
        c1.addOrder("Bugatti", "Veyron", "2015", "40k", "Engine controll shows.", c1);
        Client c2 = new Client("Wojtek", "Marek", "ja@xd.lol", "6732623", orderDB);
        c2.addOrder("Ford", "Transit", "2005", "200k", "Damaged", c2);
    }

    private void createUIComponents() {
        ArrayList<Mechanic> mechanicList = mechanicDB.getDatabase();
        Vector<Mechanic> mech = new Vector<>(mechanicList);

        staffWorkersComboBox = new JComboBox<Mechanic>(mech);
        mechanicSelectWorkerComboBox = new JComboBox<Mechanic>(mech);
//        for(Mechanic mechanic : mechanicList){
//            staffWorkersComboBox.addItem(mechanic.getName() + " " + mechanic.getSurname());
//        }

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

        /**
         * Creating combox with status list.
         */
        Vector<String> statusList = new Vector<>();
        for (OrderState w : EnumSet.allOf(OrderState.class)) {
            statusList.add(w.toString());
        }
        mechanicChangeStatusComboBox = new JComboBox<String>(statusList);

        /**
         * Creating list of orders for staff tab.
         */

        DefaultListModel<String> ordersListModel = new DefaultListModel<>();
        staffOrdersList = new JList(ordersListModel);
        for(Order order : orderDB.getDatabase()){
            ordersListModel.addElement(String.valueOf(order.getId()));
        }
    }
}

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by Przemek on 2016-05-09.
 */
public class AppGUI {
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Warsztat Samochodowy GRAT");
        frame.setContentPane(new AppGUI().mainJPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible(true);
    }

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
                //mechanic -> wybrano z listy orderow jakis order, wyswietlic jego szczegoly w polach obok
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        /*
        TUTAJ JEST PRZYKLADOWO ZROBIONE TWORZENIE COMBOBOXA, TRZEBA WZIAC BAZE WORKEROW I DODAC ICH DO LISTY
        Z BAZY, A POTEM WRZUCIC DO COMBOBOXA.
         */
        Vector<String> workersList = new Vector<>();
        workersList.add("Worker 1");
        workersList.add("Worker 2");
        staffWorkersComboBox = new JComboBox(workersList);

        /*
        Tak samo jak wyzej, tutaj tez trzeba wrzucic workerow z bazy.
         */

        mechanicSelectWorkerComboBox = new JComboBox(workersList);

        /*
        tworzenie tabeli checkboxow w ktorej beda wszystkie dostepne w warsztacie naprawy
         */

        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() { return 3; }
            public int getRowCount() { return 100;}
            public Object getValueAt(int row, int col) { return new Integer(row*col); }
        };
        mechanicFixesTable = new JTable(new AbstractTableModel() {

            Object rowData[][] = { { "Engine repair",100, Boolean.FALSE }, { "2",200, Boolean.FALSE }, { "3",300, Boolean.FALSE },
                    { "4",400,Boolean.FALSE }, { "5",500,Boolean.FALSE }, };

            String columnNames[] = { "Repair Name", "Price", "Boolean" };

            @Override
            public int getRowCount() {
                return rowData.length;
            }

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return rowData[rowIndex][columnIndex];
            }

            public String getColumnName(int column) {
                return columnNames[column];
            }
        });

        mechanicFixesTableScrollPane = new JScrollPane(mechanicFixesTable);

    }
}

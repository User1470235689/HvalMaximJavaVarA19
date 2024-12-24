package bsu_rfe_java_group7_HvalMaxim.lab2_varA19;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class FunctionCalculator extends JFrame {
    public FunctionCalculator() {
        setLayout(new BorderLayout());

        // Добавление меню
        addMenuBar();

        // Создание и добавление таблицы
        Object[][] data = {
                {"1", "123.321"}, // Симметричный
                {"2", "12.21"},   // Симметричный
                {"3", "123.123"}, // Несимметричный
                {"4", "100.001"}, // Симметричный
                {"5", "50.005"},  // Симметричный
                {"6", "111.011"}, // Несимметричный
                {"7", "300.000"}, // Сумма дробной части = 0, делится на 10
                {"8", "450.123"}, // Сумма дробной части = 6, не делится на 10
                {"9", "567.992"}, // Сумма дробной части = 20,  делится на 10
                {"10", "123.406"}, // Сумма дробной части = 10,  делится на 10
                {"11", "321.654"}, // Сумма дробной части = 15, не делится на 10
                {"12", "90.010"}   // Сумма дробной части = 1, не делится на 10
        };

        JTable table = new JTable(new PolynomialTableModel(data));

        // Применение визуализатора ячеек
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());

        // Настройка и добавление таблицы на панель
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Метод для добавления меню
    private void addMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Добавление пункта "Справка"
        JMenu helpMenu = new JMenu("Справка");
        JMenuItem aboutItem = new JMenuItem("О программе");

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Автор: Грушко Владислав, группа 7, курс 2"));

        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }
}
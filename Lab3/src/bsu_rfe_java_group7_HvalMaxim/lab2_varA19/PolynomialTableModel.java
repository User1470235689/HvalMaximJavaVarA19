package bsu_rfe_java_group7_HvalMaxim.lab2_varA19;

import javax.swing.table.AbstractTableModel;

// Кастомная модель таблицы
public class PolynomialTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Полином", "Ограниченная симметрия"};
    private final Object[][] data;

    public PolynomialTableModel(Object[][] data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; // Количество колонок
    }

    @Override
    public int getRowCount() {
        return data.length; // Количество строк
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 2) {
            String polynomial = (String) data[row][1];
            return isSymmetric(polynomial); // Логика проверки симметрии
        }
        return data[row][col];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col]; // Названия колонок
    }

    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 2) {
            return Boolean.class; // Отображение в виде флажков
        }
        return super.getColumnClass(col);
    }

    // Метод для проверки симметрии дробной части полинома
    private boolean isSymmetric(String polynomial) {
        if (!polynomial.contains(".")) return false; // Если нет дробной части, не симметрично

        String[] parts = polynomial.split("\\.");
        if (parts.length != 2 || parts[1].isEmpty()) return false; // Проверка корректности формата

        String left = parts[0];
        String right = parts[1];
        return new StringBuilder(left).reverse().toString().equals(right); // Проверка симметрии
    }
}
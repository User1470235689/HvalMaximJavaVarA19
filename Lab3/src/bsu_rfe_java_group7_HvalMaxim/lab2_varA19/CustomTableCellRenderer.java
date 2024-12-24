package bsu_rfe_java_group7_HvalMaxim.lab2_varA19;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// Кастомный визуализатор ячеек
public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int col) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        if (col == 1 && value instanceof String) { // Обработка только для колонок с полиномами
            String polynomial = (String) value;
            if (isSumDivisibleByTen(polynomial)) {
                cell.setBackground(new java.awt.Color(173, 216, 230)); // Голубой цвет для ячеек
            } else {
                cell.setBackground(java.awt.Color.WHITE); // Обычный цвет для остальных
            }
        } else {
            cell.setBackground(java.awt.Color.WHITE); // Сброс цвета для других ячеек
        }

        return cell;
    }

    // Метод для проверки, делится ли сумма цифр дробной части на 10
    private boolean isSumDivisibleByTen(String polynomial) {
        if (!polynomial.contains(".")) return false; // Нет дробной части

        String[] parts = polynomial.split("\\.");
        if (parts.length != 2 || parts[1].isEmpty()) return false; // Проверка корректности дробной части

        int sum = 0;
        for (char c : parts[1].toCharArray()) {
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c); // Подсчет суммы цифр дробной части
            }
        }

        return sum % 10 == 0; // Проверка делимости на 10
    }
}
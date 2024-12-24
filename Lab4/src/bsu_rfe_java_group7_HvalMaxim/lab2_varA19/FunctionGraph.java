package bsu_rfe_java_group7_HvalMaxim.lab2_varA19;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class FunctionGraph {

    private ArrayList<Point> points; // Точки графика
    private double zoomFactor = 1.0; // Коэффициент масштабирования
    private double minX = -10, maxX = 10, minY = -2, maxY = 2; // Диапазоны графика

    public FunctionGraph() {
        points = new ArrayList<>();
        // Генерация точек графика
        for (double x = minX; x <= maxX; x += 0.1) {
            double y = Math.sin(x); // Пример функции
            points.add(new Point(x, y));
        }
    }

    // Метод для отображения графика
    public void draw(Graphics2D g2d, int width, int height) {
        // Рисуем оси с делениями
        drawAxes(g2d, width, height);

        // Рисуем линии графика
        drawGraph(g2d, width, height);

        // Рисуем маркеры
        drawMarkers(g2d, width, height);
    }

    // Рисуем оси координат с делениями
    private void drawAxes(Graphics2D g2d, int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));

        // Ось X
        g2d.drawLine(0, centerY, width, centerY);
        // Ось Y
        g2d.drawLine(centerX, 0, centerX, height);

        // Делаем деления
        g2d.setStroke(new BasicStroke(1));
        double stepX = (maxX - minX) / 100;
        double stepY = (maxY - minY) / 100;

        for (int i = 0; i <= 100; i++) {
            int x = (int) (centerX + i * stepX * zoomFactor * 50);
            int y = (int) (centerY - i * stepY * zoomFactor * 50);

            // Каждое 5-е деление длиннее
            if (i % 5 == 0) {
                g2d.drawLine(x, centerY - 5, x, centerY + 5); // Горизонтальное
                g2d.drawLine(centerX - 5, y, centerX + 5, y); // Вертикальное
            } else {
                g2d.drawLine(x, centerY - 3, x, centerY + 3);
                g2d.drawLine(centerX - 3, y, centerX + 3, y);
            }
        }
    }

    // Рисуем график
    private void drawGraph(Graphics2D g2d, int width, int height) {
        // Устанавливаем стиль линии (тире/точка/тире маленькое/точка/тире)
        float[] dashPattern = {2, 5, 2, 10, 5, 2};
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dashPattern, 0));
        g2d.setColor(Color.BLUE);

        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);

            g2d.draw(new Line2D.Double(
                    toScreenX(p1.x, width), toScreenY(p1.y, height),
                    toScreenX(p2.x, width), toScreenY(p2.y, height)
            ));
        }
    }

    // Рисуем маркеры
    // Рисуем маркеры
    private void drawMarkers(Graphics2D g2d, int width, int height) {
        for (Point p : points) {
            int x = toScreenX(p.x, width);
            int y = toScreenY(p.y, height);

            g2d.setColor(Color.GREEN); // Устанавливаем зеленый цвет

            // Рисуем круг маркера
            int diameter = 10; // Диаметр круга
            g2d.fillOval(x - diameter / 2, y - diameter / 2, diameter, diameter);
        }
    }


    // Преобразуем координаты X в экранные
    private int toScreenX(double x, int width) {
        int centerX = width / 2;
        return centerX + (int) (x * zoomFactor * 50);
    }

    // Преобразуем координаты Y в экранные
    private int toScreenY(double y, int height) {
        int centerY = height / 2;
        return centerY - (int) (y * zoomFactor * 50);
    }

    // Панель для отображения графика
    public static class GraphPanel extends JPanel {

        private final FunctionGraph calculator;

        public GraphPanel(FunctionGraph calculator) {
            this.calculator = calculator;

            // Обработчик колеса мыши для масштабирования
            addMouseWheelListener(e -> {
                if (e.getWheelRotation() < 0) { // Приближение
                    calculator.zoomFactor *= 1.1;
                } else { // Отдаление
                    calculator.zoomFactor /= 1.1;
                }
                repaint();
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            calculator.draw((Graphics2D) g, getWidth(), getHeight());
        }
    }

    // Точка входа в программу
    public static void main(String[] args) {
        FunctionGraph calculator = new FunctionGraph();

        JFrame frame = new JFrame("График функции");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new GraphPanel(calculator));
        frame.setVisible(true);
    }

    // Класс для хранения точек
    public static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
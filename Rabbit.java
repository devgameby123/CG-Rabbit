
import java.awt.*;
import javax.swing.*;
import java.util.Random;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public class Rabbit {
    public static void main(String[] args) {
        GraphicsSwing m = new GraphicsSwing();

        JFrame f = new JFrame();

        f.add(m);
        f.setTitle("First Swing");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}

class GraphicsSwing extends JPanel {

    public void paintComponent(Graphics g) {

        BufferedImage buffer = new BufferedImage(601, 601, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffer.createGraphics();

        g2.setColor(Color.white);
        g2.fillRect(0, 0, 600, 600);

        // sky
        GradientPaint GP = new GradientPaint(0, 0, new Color(50, 105, 102), 300, 400, new Color(253, 213, 92));
        GradientPaint GP3 = new GradientPaint(600, 0, new Color(50, 105, 102), 300, 400, new Color(253, 213, 92));
        g2.setPaint(GP);
        g2.fillRect(0, 0, 300, 375);
        g2.setPaint(GP3);
        g2.fillRect(300, 0, 300, 367);
        g2.setColor(Color.black);

        // stars
        RandomPlot(g2, 150);

        // moon รอทำแบบอื่น
        g2.setColor(new Color(253, 213, 92));
        g2.fillOval(81, 55, 410, 410);

        // in moon รอหาจุดพรอตแม่นๆ
        g2.setColor(new Color(246, 189, 58));
        int xPoly[] = { 430, 440, 442, 441, 446, 434 };
        int yPoly[] = { 150, 150, 147, 150, 155, 157 };
        Polygon polygon = new Polygon(xPoly, yPoly, 6);
        g2.drawPolygon(polygon);

        // mountain
        g2.setColor(new Color(4, 34, 17));
        brzierCurve(g2, 0, 425, 175, 325, 450, 325, 600, 425, 1);

        buffer = FloodFill(buffer, 100, 550, Color.white, new Color(4, 34, 17));
        buffer = FloodFill(buffer, 300, 450, new Color(253, 213, 92), new Color(4, 34, 17));

        buffer = FloodFill(buffer, 0, 410, Color.white, new Color(12, 166, 255));

        buffer = FloodFill(buffer, 550, 370, Color.white, new Color(12, 166, 255));

        g2.setColor(new Color(6, 46, 26));
        brzierCurve(g2, 0, 425 + 90, 175, 325 + 90, 450, 325 + 90, 600 + 90, 425 + 90, 1);
        buffer = FloodFill(buffer, 550, 550, new Color(4, 34, 17), new Color(6, 46, 26));

        g2.setColor(new Color(8, 61, 34));
        brzierCurve(g2, 0, 425 + 170, 175, 325 + 170, 450, 325 + 170, 600 + 170, 425 + 170, 1);
        buffer = FloodFill(buffer, 550, 550, new Color(6, 46, 26), new Color(8, 61, 34));

        // Grass{
        // left
        g2.setColor(new Color(4, 34, 17));
        Bresenham(g2, 150, 400, 200, 344, 3);
        Bresenham(g2, 150, 400, 180, 344, 3);
        Bresenham(g2, 150, 400, 168, 340, 3);
        Bresenham(g2, 150, 400, 152, 344, 3);
        Bresenham(g2, 150, 400, 130, 340, 3);
        Bresenham(g2, 150, 400, 122, 350, 3);
        Bresenham(g2, 150, 400, 100, 350, 3);
        Bresenham(g2, 150, 400, 86, 370, 3);
        Bresenham(g2, 150, 400, 70, 378, 3);
        Bresenham(g2, 60, 400, 50, 368, 3);
        Bresenham(g2, 60, 400, 38, 364, 3);
        Bresenham(g2, 30, 450, 28, 385, 3);
        Bresenham(g2, 30, 450, 20, 390, 3);
        Bresenham(g2, 30, 450, 10, 388, 3);

        // mid
        Bresenham(g2, 300, 400, 225, 335, 3);
        Bresenham(g2, 300, 400, 240, 335, 3);
        Bresenham(g2, 300, 400, 255, 320, 3);
        Bresenham(g2, 300, 400, 270, 325, 3);
        Bresenham(g2, 300, 400, 299, 322, 3);
        Bresenham(g2, 300, 400, 320, 333, 3);
        Bresenham(g2, 300, 400, 350, 333, 3);
        Bresenham(g2, 300, 400, 370, 340, 3);

        // right
        Bresenham(g2, 400, 400, 400, 335, 3);
        Bresenham(g2, 400, 400, 420, 335, 3);
        Bresenham(g2, 400, 400, 450, 335, 3);
        Bresenham(g2, 400, 400, 480, 355, 3);
        Bresenham(g2, 480, 400, 500, 355, 3);
        Bresenham(g2, 480, 400, 512, 360, 3);
        Bresenham(g2, 480, 400, 525, 367, 3);
        Bresenham(g2, 480, 400, 570, 377, 3);
        Bresenham(g2, 520, 400, 555, 360, 3);
        Bresenham(g2, 550, 400, 570, 390, 3);
        Bresenham(g2, 570, 450, 580, 392, 3);
        // } end
        g.drawImage(buffer, 0, 0, null);

    }

    public void RandomPlot(Graphics g, int cnt) {
        Random rand = new Random();
        g.setColor(Color.white);
        for (int i = 0; i < cnt; i++) {
            int xRandom = rand.nextInt(599);
            int yRandom = rand.nextInt(400);
            int sizeRandom = rand.nextInt(3) + 1;
            g.fillOval(xRandom, yRandom, sizeRandom, sizeRandom);
        }
    }

    public void naiveLine(Graphics g, int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        double m = dy / dx;
        double b = y1 - m * x1;

        for (int x = x1; x <= x2; x++) {
            int y = (int) Math.round(m * x + b);
            plot(g, x, y, 1);
        }
    }

    public void ddaLine(Graphics g, int x1, int y1, int x2, int y2) {

        int dx = x2 - x1;
        int dy = y2 - y1;

        int step;

        if (Math.abs(dx) > Math.abs(dy))
            step = Math.abs(dx);
        else
            step = Math.abs(dy);

        double x_incr = (double) dx / step;
        double y_incr = (double) dy / step;

        float x = x1;
        float y = y1;

        for (int i = 0; i < step; i++) {
            x += x_incr;
            y += y_incr;
            plot(g, (int) x, (int) y, 1);
        }
    }

    public void Bresenham(Graphics g, int x1, int y1, int x2, int y2, int size) {
        double dx = Math.abs(x2 - x1);
        double dy = Math.abs(y2 - y1);
        double x, y;

        double sx = (x1 < x2) ? 1 : -1;
        double sy = (y1 < y2) ? 1 : -1;

        boolean isSwap = false;

        if (dy > dx) {
            double tmp = dy;
            dy = dx;
            dx = tmp;
            isSwap = true;
        }
        double D = 2 * (dy - dx);
        x = x1;
        y = y1;

        for (int i = 1; i < dx; i++) {
            plot(g, (int) x, (int) y, size);
            if (D >= 0) {
                if (isSwap) {
                    x += sx;

                } else {
                    y += sy;
                }

                D -= 2 * dx;
            }
            if (isSwap) {
                y += sy;
            } else {
                x += sx;
            }
            D += 2 * dy;
        }
    }

    public void brzierCurve(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        for (int i = 0; i <= 1000; i++) {
            double t = i / 1000.0;

            int x = (int) (Math.pow((1 - t), 3) * x1 + 3 * t * Math.pow((1 - t), 2) * x2
                    + 3 * Math.pow((t), 2) * (1 - t) * x3 + Math.pow(t, 3) * x4);

            int y = (int) (Math.pow((1 - t), 3) * y1 + 3 * t * Math.pow((1 - t), 2) * y2
                    + 3 * Math.pow((t), 2) * (1 - t) * y3 + Math.pow(t, 3) * y4);

            plot(g, x, y, 1);
        }
    }

    public void brzierCurve(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int size) {
        for (int i = 0; i <= 1000; i++) {
            double t = i / 1000.0;

            int x = (int) (Math.pow((1 - t), 3) * x1 + 3 * t * Math.pow((1 - t), 2) * x2
                    + 3 * Math.pow((t), 2) * (1 - t) * x3 + Math.pow(t, 3) * x4);

            int y = (int) (Math.pow((1 - t), 3) * y1 + 3 * t * Math.pow((1 - t), 2) * y2
                    + 3 * Math.pow((t), 2) * (1 - t) * y3 + Math.pow(t, 3) * y4);

            plot(g, x, y, size);
        }
    }

    public BufferedImage FloodFill(BufferedImage m, int x, int y, Color targetColour, Color replacementColor) {
        Graphics2D g2 = m.createGraphics();
        Queue<Point> q = new LinkedList<>();

        if (m.getRGB(x, y) == targetColour.getRGB()) {
            g2.setColor(replacementColor);
            plot(g2, x, y, 1);
            q.add(new Point(x, y));
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            // s
            if (p.y < 600 && m.getRGB(p.x, p.y + 1) == targetColour.getRGB()) {
                g2.setColor(replacementColor);
                plot(g2, p.x, p.y + 1, 1);
                q.add(new Point(p.x, p.y + 1));
            }
            // n
            if (p.y > 0 && m.getRGB(p.x, p.y - 1) == targetColour.getRGB()) {
                g2.setColor(replacementColor);
                plot(g2, p.x, p.y - 1, 1);
                q.add(new Point(p.x, p.y - 1));
            }
            // e
            if (p.x < 600 && m.getRGB(p.x + 1, p.y) == targetColour.getRGB()) {
                g2.setColor(replacementColor);
                plot(g2, p.x + 1, p.y, 1);
                q.add(new Point(p.x + 1, p.y));
            }
            // w
            if (p.x > 0 && m.getRGB(p.x - 1, p.y) == targetColour.getRGB()) {
                g2.setColor(replacementColor);
                plot(g2, p.x - 1, p.y, 1);
                q.add(new Point(p.x - 1, p.y));
            }
        }
        return m;
    }

    private void plot(Graphics g, int x, int y, int size) {
        g.fillRect(x, y, size, size);
    }

    public BufferedImage FloodFill(BufferedImage m, int x, int y, Color targetColour, Color replacementColor,
            boolean test) {
        Graphics2D g2 = m.createGraphics();
        Queue<Point> q = new LinkedList<>();

        if (m.getRGB(x, y) != targetColour.getRGB()) {
            g2.setColor(replacementColor);
            plot(g2, x, y, 1);
            q.add(new Point(x, y));
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            // s
            if (p.y < 600 && !(m.getRGB(p.x, p.y + 1) == targetColour.getRGB())) {
                g2.setColor(replacementColor);
                plot(g2, p.x, p.y + 1, 1);
                q.add(new Point(p.x, p.y + 1));
            }
            // n
            if (p.y > 0 && !(m.getRGB(p.x, p.y - 1) == targetColour.getRGB())) {
                g2.setColor(replacementColor);
                plot(g2, p.x, p.y - 1, 1);
                q.add(new Point(p.x, p.y - 1));
            }
            // e
            if (p.x < 600 && !(m.getRGB(p.x + 1, p.y) == targetColour.getRGB())) {
                g2.setColor(replacementColor);
                plot(g2, p.x + 1, p.y, 1);
                q.add(new Point(p.x + 1, p.y));
            }
            // w
            if (p.x > 0 && !(m.getRGB(p.x - 1, p.y) == targetColour.getRGB())) {
                g2.setColor(replacementColor);
                plot(g2, p.x - 1, p.y, 1);
                q.add(new Point(p.x - 1, p.y));
            }
        }
        return m;
    }
}
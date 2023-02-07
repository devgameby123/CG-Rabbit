import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.*;

class Assignment1_64050004_64050108 extends JPanel {
    public static void main(String[] args) {
        Assignment1_64050004_64050108 m = new Assignment1_64050004_64050108();

        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Week3");
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        BufferedImage buffer = new BufferedImage(601, 601, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffer.createGraphics();

        g2.setColor(Color.white);
        g2.fillRect(0, 0, 600, 600);

        // sky
        GradientPaint GP = new GradientPaint(0, 0, new Color(50, 105, 102), 300, 500, new Color(253, 213, 92));
        GradientPaint GP3 = new GradientPaint(600, 0, new Color(50, 105, 102), 300, 500, new Color(253, 213, 92));
        g2.setPaint(GP);
        g2.fillRect(0, 0, 300, 500);
        g2.setPaint(GP3);
        g2.fillRect(300, 0, 300, 500);

        // stars
        RandomPlot(g2, 200);

        // moon
        Color moon = new Color(253, 213, 92);
        g2.setColor(moon);
        for (int i = 195; i >= 1; i--) {
            midpointCircle(g2, 300, 238, i);

        }

        // moon detail
        Color moonDetail = new Color(240, 187, 56);
        g2.setColor(moonDetail);
        int[] xCircle = { 348, 337, 330, 387 };
        int[] yCircle = { 112, 88, 100, 183 };
        int[] rCircle = { 7, 5, 4, 6 };

        for (int i = 0; i < xCircle.length; i++) {
            midpointCircle(g2, xCircle[i], yCircle[i], rCircle[i]);
            FloodFill(buffer, xCircle[i], yCircle[i], moon, moonDetail);
        }

        int[] xEllipse = { 425, 368, 394, 385, 446, 450, 441, 368, 397, 370, 153, 457, 392 };
        int[] yEllipse = { 323, 323, 333, 358, 317, 234, 251, 96, 119, 119, 345, 286, 129 };
        int[] aEllipse = { 11, 9, 5, 12, 5, 10, 8, 6, 8, 4, 10, 4, 4 };
        int[] bEllipse = { 15, 13, 8, 13, 7, 15, 10, 4, 10, 6, 12, 5, 5 };

        for (int i = 0; i < xEllipse.length; i++) {
            midpointEllipse(g2, xEllipse[i], yEllipse[i], aEllipse[i], bEllipse[i]);
            FloodFill(buffer, xEllipse[i], yEllipse[i], moon, moonDetail);
        }

        // ground
        Color ground = new Color(4, 30, 17);
        g2.setColor(ground);
        bezierCurve(g2, 0, 450, 200, 380, 400, 380, 600, 450, 1);
        buffer = FloodFill2(buffer, 300, 599, ground, ground);

        // Grass
        int[][] xyGrass = { { 1, 447 }, { 24, 420 }, { 15, 437 }, { 40, 415 }, { 34, 425 }, { 63, 400 }, { 54, 416, },
                { 82, 397 },
                { 71, 413 }, { 109, 384 }, { 95, 403 }, { 132, 376 }, { 118, 392 }, { 150, 376 }, { 139, 388 },
                { 179, 368 }, { 164, 382 },
                { 197, 366 }, { 187, 376 }, { 220, 360 }, { 207, 372 }, { 245, 356 }, { 233, 367 }, { 266, 358 },
                { 257, 366 }, { 297, 359 },
                { 291, 366 }, { 318, 361 }, { 313, 365 }, { 351, 362 }, { 341, 367 }, { 389, 365 }, { 380, 371 },
                { 415, 370 }, { 407, 376 },
                { 440, 375 }, { 430, 380 }, { 470, 381 }, { 456, 388 }, { 495, 393 }, { 484, 397 }, { 522, 401 },
                { 507, 405 }, { 534, 407 },
                { 525, 411 }, { 546, 412 }, { 540, 417 }, { 568, 423 }, { 560, 427 }, { 576, 430 }, { 600, 435 } };

        for (int i = 0; i < xyGrass.length - 1; i++) {
            bresenhamLine(g2, xyGrass[i][0], xyGrass[i + 1][0], xyGrass[i][1], xyGrass[i + 1][1], 3);

        }
        buffer = FloodFill2(buffer, 283, 374, ground, ground);

        // body
        int c = 20;
        bezierCurve(g2, 294, 265, 284, 280, 252, 320, 250, 360, 3);
        bezierCurve(g2, 340 + c, 265, 360 + c, 295, 355 + c, 315, 340 + c, 325, 3);
        bezierCurve(g2, 340 + c, 325, 355 + c, 330, 352 + c, 345, 355 + c, 370, 3);

        // head
        bezierCurve(g2, 300, 190, 300, 220, 260, 220, 294, 265, 3);
        bezierCurve(g2, 300, 190, 289, 170, 290, 140, 310, 128, 3);
        bezierCurve(g2, 310, 128, 324, 145, 320, 165, 325, 190, 3);
        bresenhamLine(g2, 325, 335, 190, 190, 3);
        bezierCurve(g2, 335, 190, 335, 170, 335, 135, 345, 128, 3);
        bezierCurve(g2, 345, 128, 365, 134, 370, 160, 360, 190, 3);
        bezierCurve(g2, 360, 190, 360, 205, 400, 245, 340 + c, 265, 3);
        buffer = FloodFill2(buffer, 280, 300, new Color(4, 30, 17), new Color(4, 30, 17));

        bezierCurve(g2, 280, 300, 270, 280, 258, 267, 239, 268, 2);
        makeFlower(g2, buffer, 8, 239, 260, new Color(4, 30, 17));
        bezierCurve(g2, 460, 390, 470, 380, 465, 370, 495, 360, 2);
        makeFlower(g2, buffer, 8, 495, 355, new Color(4, 30, 17));
        bezierCurve(g2, 110, 400, 110, 390, 120, 370, 70, 350, 2);
        makeFlower(g2, buffer, 8, 65, 345, new Color(4, 30, 17));
        g.drawImage(buffer, 0, 0, null);

    }

    public BufferedImage makeFlower(Graphics g, BufferedImage m, int size, int posx, int posy, Color TargerC) {
        for (int i = 0; i <= size; i += 2) {
            midpointCircle(g, posx, posy, i);
        }
        midpointEllipse(g, posx, posy, 8, 20);
        midpointEllipse(g, posx, posy, 20, 8);
        m = FloodFill2(m, posx, posy + 15, TargerC, TargerC);
        m = FloodFill2(m, posx, posy - 15, TargerC, TargerC);
        m = FloodFill2(m, posx + 15, posy, TargerC, TargerC);
        m = FloodFill2(m, posx - 15, posy, TargerC, TargerC);
        return m;

    }

    private void plot(Graphics g, double x, double y, int size) {
        g.fillRect((int) x, (int) y, size, size);
    }

    public void RandomPlot(Graphics g, int cnt) {
        g.setColor(Color.white);
        Random rand = new Random();
        for (int i = 0; i < cnt; i++) {
            int xRandom = rand.nextInt(599);
            int yRandom = rand.nextInt(400);
            int sizeRandom = rand.nextInt(3) + 1;
            g.fillOval(xRandom, yRandom, sizeRandom, sizeRandom);
        }
    }

    public void bresenhamLine(Graphics g, int x1, int x2, int y1, int y2, int size) {
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
            plot(g, x, y, size);
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

    public void bezierCurve(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int size) {
        for (int i = 0; i <= 1000; i++) {
            double t = i / 1000.0;
            int x = (int) (Math.pow((1 - t), 3) * x1
                    + 3 * t * Math.pow((1 - t), 2) * x2
                    + 3 * Math.pow((t), 2) * (1 - t) * x3 + Math.pow(t, 3) * x4);

            int y = (int) (Math.pow((1 - t), 3) * y1
                    + 3 * t * Math.pow((1 - t), 2) * y2
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

    public BufferedImage FloodFill2(BufferedImage m, int x, int y, Color targetColour, Color replacementColor) {
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
            if (p.y < 600 && m.getRGB(p.x, p.y + 1) != targetColour.getRGB()) {
                g2.setColor(replacementColor);
                plot(g2, p.x, p.y + 1, 1);
                q.add(new Point(p.x, p.y + 1));
            }
            // n
            if (p.y > 0 && m.getRGB(p.x, p.y - 1) != targetColour.getRGB()) {
                g2.setColor(replacementColor);
                plot(g2, p.x, p.y - 1, 1);
                q.add(new Point(p.x, p.y - 1));
            }
            // e
            if (p.x < 600 && m.getRGB(p.x + 1, p.y) != targetColour.getRGB()) {
                g2.setColor(replacementColor);
                plot(g2, p.x + 1, p.y, 1);
                q.add(new Point(p.x + 1, p.y));
            }
            // w
            if (p.x > 0 && m.getRGB(p.x - 1, p.y) != targetColour.getRGB()) {
                g2.setColor(replacementColor);
                plot(g2, p.x - 1, p.y, 1);
                q.add(new Point(p.x - 1, p.y));
            }
        }
        return m;
    }

    public void midpointCircle(Graphics g, int xc, int yc, int r) {
        int x = 0;
        int y = r;
        int d = 1 - r;
        int dx = 2 * x;
        int dy = 2 * y;
        while (x <= y) {
            plot(g, x + xc, y + yc, 3);
            plot(g, -x + xc, y + yc, 3);
            plot(g, x + xc, -y + yc, 3);
            plot(g, -x + xc, -y + yc, 3);
            plot(g, y + xc, x + yc, 3);
            plot(g, -y + xc, x + yc, 3);
            plot(g, y + xc, -x + yc, 3);
            plot(g, -y + xc, -x + yc, 3);

            x++;
            dx += 2;
            d = d + dx + 1;

            if (d >= 0) {
                y--;
                dy -= 2;
                d = d - dy;
            }
        }
    }

    public void midpointEllipse(Graphics g, int xc, int yc, int a, int b) {
        int x, y, d;
        // r1
        x = 0;
        y = b;
        d = Math.round(b * b - a * a * b + a * a / 4);

        while (b * b * x <= a * a * y) {
            plot(g, x + xc, y + yc, 3);
            plot(g, -x + xc, y + yc, 3);
            plot(g, x + xc, -y + yc, 3);
            plot(g, -x + xc, -y + yc, 3);

            x++;
            d = d + 2 * b * b * x + b * b;

            if (d >= 0) {
                y--;
                d = d - 2 * a * a * y;
            }
        }
        // r2

        x = a;
        y = 0;
        d = Math.round(a * a - b * b * a + b * b / 4);
        while (b * b * x >= a * a * y) {
            plot(g, x + xc, y + yc, 3);
            plot(g, -x + xc, y + yc, 3);
            plot(g, x + xc, -y + yc, 3);
            plot(g, -x + xc, -y + yc, 3);

            y++;
            d = d + 2 * a * a * y + a * a;

            if (d >= 0) {
                x--;
                d = d - 2 * b * b * x;
            }
        }
    }

}

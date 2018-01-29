package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main extends JPanel {

    static int r, dx, dy, dt, t, t0, i, w, h;
    static double x, y, k, g;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("input.txt"));
        ArrayList<String> lines = new ArrayList<String>();
        for (int j = 0; j < 8; j++) {
            lines.add(sc.nextLine());
        }
        r = Integer.valueOf(lines.get(0));
        x = Double.valueOf(lines.get(1));
        y = Double.valueOf(lines.get(2));

        dx = Integer.valueOf(lines.get(3));
        dy = Integer.valueOf(lines.get(4));

        dt = Integer.valueOf(lines.get(5));

        t = Integer.valueOf(lines.get(6)) * 1000;
        t0 = t;

        k = Double.valueOf(lines.get(7));

        g = 9.80665;
        i = 0;

        JFrame frame = new JFrame("Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        frame.setResizable(false);
        frame.setContentPane(new Main());
        frame.setVisible(true);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval((int)(x - r), (int)(y - r), (int)r*2, (int)r*2);
    }

    public Main() {
        Thread thread = new Thread() {
            public void run (){
                while (t > 0) {

                    w = 996;
                    h = 470;
                    double x0 = x;
                    double y0 = y;
                    // x += dx;
                    //y += dy;
                    if ((x - r + dx < 0) || (x + r + dx > w ))
                        dx = -dx;
                    if ((y - r + dy < 0) || (y + r + dx > h ))
                        dy = -dy;

                    x += dx;
                    y += dy;
                    repaint();

                    t -= dt;
                    dy += g / 2;
                    System.out.println("t" + i + ": (" + x0 + "," + y0 + ") -> " + "(" + x + "," + y + ")");
                    i++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                }

            }
        };
        thread.start();


    }
}

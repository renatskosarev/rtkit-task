package com.skosarev;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.exit(0);
        }

        switch (args[0]) {
            case "help" -> printHelp();
            case "mem" -> {
                if (args.length < 3 || args.length > 5) {
                    System.out.println("Invalid arguments. See help");
                    System.exit(0);
                }
                int color = 0;
                if (args.length >= 4) {
                    try {
                        color = Integer.parseInt(args[3]);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: color should be numeric value");
                    }
                }
                String position = args.length == 5 ? args[4] : "bottom";
                addTextToImage(args[1], args[2], color, position);

            }
            default -> System.out.println("Error: Unknown command");
        }
    }

    private static void printHelp() {
        System.out.println("""
                Help with using the program:
                    help — print this message
                    mem <file path> <text> <color> <position> — add text to image
                    (color and position are optional; color should be numeric (rgb) value; available positions: top, center, bottom)""");
    }

    private static void addTextToImage(String filePath, String text, int rgbColor, String position) {
        // чтение файла
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println("Error while reading file");
        }

        // установка шрифта и цвета
        Graphics g = image.getGraphics();
        g.setColor(new Color(rgbColor));
        g.setFont(Font.getFont("Arial"));
        int fontSize = image.getHeight() / 10;
        g.setFont(g.getFont().deriveFont(Font.PLAIN, fontSize));

        // установка положения текста
        int x = 20;
        int y = 0;
        switch (position) {
            case "top" -> y = 40 + fontSize;
            case "center" -> y = image.getHeight() / 2 + fontSize / 2;
            case "bottom" -> y = image.getHeight() - 40;
            default -> {
                System.out.println("Error: Invalid position");
                System.exit(0);
            }
        }

        // отрисовка
        g.drawString(text, x, y);
        g.dispose();

        // запись в файл
        File file = new File(filePath);
        String formatName = filePath.substring(filePath.lastIndexOf(".") + 1);
        try {
            ImageIO.write(image, formatName, file);
        } catch (IOException e) {
            System.out.println("Error while saving file");
        }

    }
}
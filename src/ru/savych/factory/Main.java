package ru.savych.factory;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Profile> profiles = new ArrayList<>();
    public static void main(String[] args) {
        profiles = (ArrayList<Profile>) deserData("profiles");
        System.out.println(profiles.size());
        Profile profile = new Profile();
        profile.setName(JOptionPane.showInputDialog(null, "Введите ваше имя"));
        profile.setFirstName(JOptionPane.showInputDialog(null,"Введите вашу фамилию"));
        profiles.add(profile);

        for (Profile p : profiles)
            System.out.println(p.getName() + " " + p.getFirstName());
        System.out.println(profiles.size());
        serData("profiles", profiles);
    }

    private static void serData(String file_name, Object obj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file_name + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            fileOut.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
            System.exit(2);
        }
    }

    private static Object deserData(String file_name) {
        Object returnObject = null;
        try {
            FileInputStream fileIn = new FileInputStream(file_name + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            returnObject = in.readObject();
            fileIn.close();
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Ошибка ввода");
            System.exit(2);
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
            System.exit(3);
        }
        return returnObject;
    }
}

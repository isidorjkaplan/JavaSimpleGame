package co.amscraft.admin;

/**
 * Created by Izzy on 2017-04-23.
 */

import co.amscraft.cloudside.Class;
import com.sun.javafx.tools.ant.FileSet;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Class knight = new Class(0, "Knight");
        Class horse = new Class(1, "Horse");
        saveClasses.add(knight);
        saveClasses.add(horse);
        save(new File("classes.txt"));

    }
    static ArrayList<Class> saveClasses = new ArrayList<Class>();

    private static void save(File file) {
        ArrayList<String> save = new ArrayList<String>();
        for (Class object: saveClasses) {
            try {
                save.add(toString(object));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.write(Paths.get(file.getName()), save, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String toString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

}

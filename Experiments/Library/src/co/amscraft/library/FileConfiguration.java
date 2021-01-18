package co.amscraft.library;

import com.sun.javafx.tools.ant.FileSet;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Izzy on 2017-04-19.
 */
public class FileConfiguration {
    public static void main(String[] args) {
        FileConfiguration file = new FileConfiguration(new File("test.txt"));
    }
    private final File file;
    private FileSection root;
    private HashMap<String, FileSection> lines = new HashMap<String, FileSection>();
    public FileConfiguration(File file) {
        this.file = file;
        root = new FileSection("");
        if (!file.canRead()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        load();
    }


    private void load() {
        lines = new HashMap<String, FileSection>();
        List<String> fileData = null;
        try {
            fileData = Files.readAllLines(Paths.get(file.getName()));
            if (!fileData.get(0).equals("")) {
                lines = (HashMap<String, FileSection>) this.fromString(fileData.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void save() {
        //Create code
        Path path = Paths.get(file.getName());
        ArrayList<String> strings = new ArrayList<String>();

        try {
            strings.add(this.toString(lines));
            Files.write(path, strings, Charset.forName("UTF-8"));
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    private static Object fromString( String s ) throws IOException ,
            ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }
    private static String toString( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
    public void set(String path, Serializable object) {
        //Create code
        String[] paths = path.split("/");
        FileSection section = root;
        for (int i = 0; i < paths.length-1; i++) {
                section = section.getChild(paths[i]);
        }
        section.addItem(paths[paths.length-1], object);

    }
    public String getString(String path) {
        return lines.get(path).toString();
    }
    public int getInt(String path) {
        return Integer.parseInt(lines.get(path).toString());
    }
    public long getLong(String path) {
        return Long.parseLong(lines.get(path).toString());
    }
    public double getDouble(String path) {
        return Double.parseDouble(lines.get(path).toString());
    }
    public boolean getBoolean(String path) {
        return Boolean.parseBoolean(lines.get(path).toString());
    }
    public Map getMap(String path) {
        return (Map) lines.get(path);
    }
    public Object getObject(String path) {
        return lines.get(path);
    }
    public List getList(String path) {
        return (List) this.getObject(path);
    }
}

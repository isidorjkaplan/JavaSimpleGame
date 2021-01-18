package co.amscraft.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Izzy on 2017-04-20.
 */
class FileSection implements Serializable{
    private FileSection parent = null;
    private String key;
    private ArrayList<FileSection> children = new ArrayList<FileSection>();
    private HashMap<Object, Object> items = new HashMap<Object, Object>();
    public FileSection(String key) {
        this.key = key;
    }
    public void addItem(Object key, Object item) {
        items.put(key, item);
    }
    public int getParentCount() {
        int count = 0;
        if (this.hasParent()) {
            FileSection checking = this.getParent();
            count++;
            while (checking.hasParent()) {
                count++;
                checking = checking.getParent();
            }
        }
        return count;
    }
    public boolean containsChild(String childName) {
        for (FileSection child: children) {
            if (child.getKey().equals(childName)) {
                return true;
            }
        }
        return false;
    }
    public FileSection getChild(String key) {
        if (!this.containsChild(key)) {
            children.add(new FileSection(key));
        }
        for (FileSection child: children) {
            if (child.getKey().equals(key)) {
                return child;
            }
        }

        throw new NullPointerException();
    }
    public String getKey() {
        return this.key;
    }
    public boolean hasParent() {
        return (parent != null);
    }
    public FileSection getParent() {
        if (!this.hasParent()) {
            throw new NullPointerException();
        }
        return parent;
    }
}

package com.tbonas.assignment2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A collection of lenses and methods to get and add lenses to that collection
 */

public class LensManager implements Iterable<Lens>{
    private List<Lens> lenses = new ArrayList<>();
    private static LensManager instance;

    private LensManager() {
        // Private constructor for singleton support
    }

    // Singleton support
    public static LensManager getInstance() {
        if (instance == null) {
            instance = new LensManager();
        }
        return instance;
    }

    public void add(Lens lens) {
        lenses.add(lens);
    }
    public Lens at(int index) {
        if (index < 0 || index > lenses.size() - 1 ) {
            return null;
        }
        return (lenses.get(index));
    }
    public int len() {
        return lenses.size();
    }

    // Allows iteration if needed
    @Override
    public Iterator<Lens> iterator() {
        return lenses.iterator();
    }
}

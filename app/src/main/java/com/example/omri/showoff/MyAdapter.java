package com.example.omri.showoff;

import java.util.List;

/**
 * Created by Asaf on 25-Jul-15.
 */
public interface MyAdapter<T extends ShowoffItem> {
    void getResults(List<T> list);
}

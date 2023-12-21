package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map {
    ArrayList<ArrayList<String>> map = new ArrayList<>();

    public Map(String mapString) {
        for (String line: mapString.split("\n")) {
            ArrayList<String> lineArr = new ArrayList<>();
            for (char item: line.toCharArray()) {
                lineArr.add(String.valueOf(item));
            }

            map.add(lineArr);
        }
    }


    public String getItem(int x, int y) {
        return map.get(y).get(x);
    }

    public int getHeight() {
        return map.size();
    }

    public int getWidth(){
        return map.get(0).size();
    }
}

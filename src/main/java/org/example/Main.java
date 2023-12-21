package org.example;

import java.util.ArrayList;

public class Main {
    public static Map map = new Map(PuzzleInput.testInput);
    public static ArrayList<Beam> beams = new ArrayList<>();
    public static ArrayList<ArrayList<String>> drawnMap = new Map(PuzzleInput.testInput).map;

    public static void main(String[] args) throws InterruptedException {
        Beam startingBeam = new Beam(map, BeamDirection.RIGHT);
        while (!beams.isEmpty()) {
            for (int bN = 0; bN < beams.size(); bN++) {
                beams.get(bN).move();
            }
            Thread.sleep(1000);
        }

        drawMap();
    }

    public static void drawMap() {
        for (ArrayList<String> line: drawnMap) {
            System.out.println(String.join("", line));
        }
    }
}
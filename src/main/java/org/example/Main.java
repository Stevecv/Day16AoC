package org.example;

import java.util.ArrayList;

public class Main {
    public static Map map = new Map(PuzzleInput.testInput);
    public static ArrayList<Beam> beams = new ArrayList<>();
    public static ArrayList<ArrayList<String>> drawnMap = new Map(PuzzleInput.testInput).map;

    public static void main(String[] args) throws InterruptedException {
        Beam startingBeam = new Beam(map, BeamDirection.RIGHT, 0, 0);
        while (!beams.isEmpty()) {
            for (int bN = 0; bN < beams.size(); bN++) {
                beams.get(bN).move();
            }
            drawMap(String.valueOf(beams.size()));
            Thread.sleep(1000);
        }
        drawMap("Done");
    }

    public static void drawMap(String message) {
        System.out.println("--------[ " + message + " ]--------");
        for (ArrayList<String> line: drawnMap) {
            System.out.println(String.join("", line));
        }
    }
}
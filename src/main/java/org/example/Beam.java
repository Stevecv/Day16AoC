package org.example;

import java.util.ArrayList;

public class Beam {
    BeamDirection direction;
    public int x;
    public int y;
    public Map map;
    public Beam(Map map, BeamDirection direction) {
        this.direction = direction;
        this.map = map;

        Main.beams.add(this);
    }

    public void move() {
        if (x >= map.getWidth() || y >= map.getHeight() || x < 0 || y < 0) {
            Main.beams.remove(this);
            Main.drawMap();
            return;
        }
        doItem();

        ArrayList<String> tempLine = Main.drawnMap.get(y);
        tempLine.set(x, "#");
        Main.drawnMap.set(y, tempLine);

        switch (direction) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
    }

    public String getItem() {
        return map.getItem(x, y);
    }

    public void doItem() {
        switch (getItem()) {
            case "/":
                rotateLeft();
            case "\\":
                rotateRight();
            case "|":
                if (direction == BeamDirection.LEFT || direction == BeamDirection.RIGHT) {
                    direction = BeamDirection.DOWN;
                    //new Beam(map, BeamDirection.UP);
                }
            case "-":
                if (direction == BeamDirection.UP || direction == BeamDirection.DOWN) {
                    direction = BeamDirection.LEFT;
                    //new Beam(map, BeamDirection.RIGHT);
                }
        }
    }

    public void rotateRight() {
        switch (direction) {
            case UP:
                direction = BeamDirection.RIGHT;
                return;
            case RIGHT:
                direction = BeamDirection.DOWN;
                return;
            case DOWN:
                direction = BeamDirection.LEFT;
                return;
            case LEFT:
                direction = BeamDirection.UP;
        }
    }

    public void rotateLeft() {
        switch (direction) {
            case UP:
                direction = BeamDirection.LEFT;
                return;
            case LEFT:
                direction = BeamDirection.DOWN;
                return;
            case DOWN:
                direction = BeamDirection.RIGHT;
                return;
            case RIGHT:
                direction = BeamDirection.UP;
        }
    }

    public void moveLeft() {
        System.out.println("Left");
        x -= 1;
    }
    public void moveRight() {
        System.out.println("Right");
        x += 1;
    }
    public void moveUp() {
        y -= 1;
        System.out.println("Up");
    }
    public void moveDown() {
        y += 1;
        System.out.println("Down");
    }
}

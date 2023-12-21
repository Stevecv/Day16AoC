package org.example;

import java.util.ArrayList;

public class Beam {
    BeamDirection direction;
    public int x;
    public int y;
    public Map map;
    public Beam(Map map, BeamDirection direction, int x, int y) {
        this.direction = direction;
        this.map = map;
        this.x = x;
        this.y = y;

        checkIfTooBig();

        Main.beams.add(this);
    }

    public boolean checkIfTooBig() {
        if (x >= map.getWidth() || y >= map.getHeight() || x < 0 || y < 0) {
            Main.beams.remove(this);
            return true;
        }
        return false;
    }

    public void move() {
        //System.out.println(direction + " (" + x + ", " + y + ")");
        if (checkIfTooBig()) {
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

    public void setDirection(BeamDirection dir) {
        direction = dir;
    }

    public void doItem() {
        String item = getItem();
        switch (item) {
            case "/":
                mirrorB();
                break;
            case "\\":
                mirrorA();
                break;
            case "|":
                if (direction == BeamDirection.LEFT || direction == BeamDirection.RIGHT) {
                    direction = BeamDirection.UP;
                    new Beam(map, BeamDirection.DOWN, x, y+1);
                    break;
                }
            case "-":
                if (direction == BeamDirection.UP || direction == BeamDirection.DOWN) {
                    direction = BeamDirection.LEFT;
                    new Beam(map, BeamDirection.RIGHT, x+1, y);
                    break;
                }
        }
    }

    public void mirrorA() {
        switch (direction) {
            case DOWN -> setDirection(BeamDirection.RIGHT);
            case UP -> setDirection(BeamDirection.LEFT);
            case RIGHT -> setDirection(BeamDirection.DOWN);
            case LEFT -> setDirection(BeamDirection.UP);
        }
    }

    public void mirrorB() {
        switch (direction) {
            case DOWN -> setDirection(BeamDirection.LEFT);
            case UP -> setDirection(BeamDirection.RIGHT);
            case RIGHT -> setDirection(BeamDirection.UP);
            case LEFT -> setDirection(BeamDirection.DOWN);
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
        x -= 1;
    }
    public void moveRight() {
        x += 1;
    }
    public void moveUp() {
        y -= 1;
    }
    public void moveDown() {
        y += 1;
    }
}

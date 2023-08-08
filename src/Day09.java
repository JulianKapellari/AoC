import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day09 {

    static List<Point> knots = new ArrayList<>();
    static HashSet<Point> tailSteps = new HashSet<>();

    public static void main(String[] args) {

        for (int i = 0; i < 9; i++) {
            knots.add(new Point(0, 0));
        }

        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input09");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                String[] split = line.split(" ");
                switch (split[0]) {
                    case "D":
                        for (int i = 0; i < Integer.parseInt(split[1]); i++) {
                            knots.get(0).move(knots.get(0).x, knots.get(0).y + 1);
                            checkTail();
                        }
                        break;
                    case "U":
                        for (int i = 0; i < Integer.parseInt(split[1]); i++) {
                            knots.get(0).move(knots.get(0).x, knots.get(0).y - 1);
                            checkTail();
                        }
                        break;
                    case "R":
                        for (int i = 0; i < Integer.parseInt(split[1]); i++) {
                            knots.get(0).move(knots.get(0).x + 1, knots.get(0).y);
                            checkTail();
                        }
                        break;
                    case "L":
                        for (int i = 0; i < Integer.parseInt(split[1]); i++) {
                            knots.get(0).move(knots.get(0).x - 1, knots.get(0).y);
                            checkTail();
                        }
                        break;
                }
            }

            tailSteps.add(knots.get(8));
            System.out.println(tailSteps);
            System.out.println(tailSteps.size());

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void checkTail() {
        for (int i = 1; i < knots.size(); i++) {


            if (knots.get(i - 1).distance(knots.get(i)) >= 2) {
                if (i == knots.size() - 1) {
                    // System.out.println(knots.get(i - 1).distance(knots.get(i)));
                    tailSteps.add(new Point(knots.get(knots.size() - 1)));
                    // System.out.println(knots.get(knots.size() - 1));
                }
                if (knots.get(i - 1).x - knots.get(i).x == 2 && knots.get(i - 1).y - knots.get(i).y == 2) {
                    //move right
                    knots.get(i).move(knots.get(i - 1).x - 1, knots.get(i - 1).y - 1);
                } else if (knots.get(i - 1).x - knots.get(i).x == 2 && knots.get(i - 1).y - knots.get(i).y == -2) {
                    knots.get(i).move(knots.get(i - 1).x - 1, knots.get(i - 1).y + 1);
                    //move left
                } else if (knots.get(i - 1).x - knots.get(i).x == -2 && knots.get(i - 1).y - knots.get(i).y == -2) {
                    //move right
                    knots.get(i).move(knots.get(i - 1).x + 1, knots.get(i - 1).y + 1);
                    //move down
                } else if (knots.get(i - 1).x - knots.get(i).x == -2 && knots.get(i - 1).y - knots.get(i).y == 2) {
                    //move right
                    knots.get(i).move(knots.get(i - 1).x + 1, knots.get(i - 1).y - 1);
                    // move up
                } else if (knots.get(i - 1).x - knots.get(i).x >= 2) {
                    //move right
                    //System.out.println(knots.get(i-1));
                    knots.get(i).move(knots.get(i - 1).x - 1, knots.get(i - 1).y);
                } else if (knots.get(i - 1).x - knots.get(i).x <= -2) {
                    knots.get(i).move(knots.get(i - 1).x + 1, knots.get(i - 1).y);
                    //move left
                } else if (knots.get(i - 1).y - knots.get(i).y >= 2) {
                    knots.get(i).move(knots.get(i - 1).x, knots.get(i - 1).y - 1);
                    //move down
                } else if (knots.get(i - 1).y - knots.get(i).y <= -2) {
                    knots.get(i).move(knots.get(i - 1).x, knots.get(i - 1).y + 1);
                    // move up
                }

            }
            System.out.println(knots);

        }

    }

}
/*
 if (head.distance(tail) >= 2) {
                if (head.x - tail.x >= 2) {
                    //move right
                    tailSteps.add(new Point(tail));
                    tail.move(head.x - 1, head.y);
                } else if (head.x - tail.x <= -2) {
                    tailSteps.add(new Point(tail));
                    tail.move(head.x + 1, head.y);
                    //move left
                } else if (head.y - tail.y >= 2) {
                    tailSteps.add(new Point(tail));
                    tail.move(head.x, head.y - 1);
                    //move down
                } else if (head.y - tail.y <= -2) {
                    tailSteps.add(new Point(tail));
                    tail.move(head.x, head.y + 1);
                    // move up
                }
            }
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day12 {


    public static void main(String[] args) {

        List<char[]> list = generateList();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                System.out.print(list.get(i)[j]);
            }
            System.out.println();
        }

        Tree start = generateTree(list);

        start.traverseInOrder();

    }

    private static Tree generateTree(List<char[]> list) {

        Tree start = new Tree('a', 0, 0, null);

        start.setChildren(findPossibleWays(start, list));
        System.out.println(start.getChildren());
        return start;

    }

    private static List<Tree> findPossibleWays(Tree tree, List<char[]> list) {

        List<Tree> children = new ArrayList<>();

        for (int i = tree.getX() - 1; i <= tree.getX() + 1; i += 2) {
            try {
                if (list.get(tree.getY())[i] <= tree.getSize() + 1) {
                    if (tree.getParent() == null || tree.getParent().getX() != i) {
                        System.out.println(tree.getParent() + " added Child " + list.get(tree.getY())[i] + " / " + i + " / " + tree.getY());
                        children.add(new Tree(list.get(tree.getY())[i], i, tree.getY(), tree));
                    }
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        for (int i = tree.getY() - 1; i <= tree.getY() + 1; i += 2) {
            try {
                if (list.get(i)[tree.getX()] <= list.get(tree.getY())[tree.getX()] + 1) {
                    if (tree.getParent() == null || tree.getParent().getY() != i) {
                        System.out.println(tree.getParent() + " added Child " + list.get(tree.getY())[i] + " / " + tree.getX() + " / " + i);
                        children.add(new Tree(list.get(i)[tree.getX()], tree.getX(), i, tree));
                    }
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }

        for (Tree t : children) {

            t.setChildren(findPossibleWays(t, list));
            t.setVisited(true);
        }
        return children;
    }

    private static List<char[]> generateList() {
        List<char[]> list = new ArrayList<>();
        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input12");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                if (line.contains("E")) {
                    line = line.replace('E', 'z');
                }
                if (line.contains("S")) {
                    line = line.replace('S', 'a');
                }
                list.add(line.toCharArray());
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return list;
    }
}

class Tree {

    private char size;
    private List<Tree> children;
    private Tree parent;
    private int x, y;
    private boolean visited;

    public Tree(char size, int x, int y, Tree parent) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.size = size;
        children = new ArrayList<>();
        visited = false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void traverseInOrder() {

        for (Tree t : this.children) {
            System.out.println(t);
            t.traverseInOrder();
        }

    }

    public int getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Node{" +
                ", size=" + size +
                '}';
    }
}

    /*public static void findWay(CustomPoint start, CustomPoint end) {
        System.out.println(start);
        if (start.getPoint().getLocation() != end.getPoint().getLocation()) {
            steps++;
            List<CustomPoint> possibleWays = new ArrayList<>();
            int currentHeight = list.get(start.getPoint().x).get(start.getPoint().y);
            for (int i = -1; i <= 1; i += 2) {
                try {
                    if (list.get(start.getPoint().x + i).get(start.getPoint().y) <= currentHeight + 1) {
                        possibleWays.add(new Point(start.getPoint().x + i, start.getPoint().y));
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
            for (int i = -1; i <= 1; i += 2) {
                try {
                    if (list.get(start.getPoint().x).get(start.getPoint().y + i) <= currentHeight + 1) {
                        possibleWays.add(new Point(start.getPoint().x, start.getPoint().y + i));
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }

            for (CustomPoint p : possibleWays) {
                findWay(p, end);
            }
        }
    }

}
/*
  List<CustomPoint> temp = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'S') {
                        start = new CustomPoint(new Point(i, list.size()));
                        temp.add(0);
                    } else if (line.charAt(i) == 'E') {
                        temp.add(25);
                        end = new CustomPoint(new Point(i, list.size()));
                    } else {
                        temp.add(line.charAt(i) - 97);
                    }
                }
                list.add(temp);
 */

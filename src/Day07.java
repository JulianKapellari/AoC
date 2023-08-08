import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day07 {

    static int count = 0;
    static String fileName = "";

    public static void main(String[] args) {

        Stack<Node> pwd = new Stack<>();
        Node root = new Node("/", 0);
        pwd.push(root);

        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input07");
        String line = null;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            while ((line = fileReader.readLine()) != null) {
                String[] command = line.split(" ");
                if (line.startsWith("$")) {
                    if (command[1].equals("cd")) {
                        if (command[2].equals("..")) {
                            pwd.pop();
                        } else {
                            pwd.push(pwd.peek().findChildByName(command[2]));
                        }
                    }
                } else {
                    try {
                        pwd.peek().insert(command[1], Integer.parseInt(command[0]));
                    } catch (NumberFormatException e) {
                        pwd.peek().insert(command[1], 0);
                    }
                }

            }
            count = Node.getSizeOfDirectory(root);
            int neededSpace = 30000000 - (70000000 - Node.getSizeOfDirectory(root));
            System.out.println(neededSpace);

            traverse(root);
            System.out.println(count);
            System.out.println(fileName);
        } catch (IOException e) {
            System.out.println(e);
        }


    }

    public static void traverse(Node node) {
        for (Node n : node.getChildren()) {
            int size = Node.getSizeOfDirectory(n);
            if (8690120 <= size && count > size) {
                count = size;
                fileName = n.getName();
            }
            traverse(n);
        }
    }
}

class Node {

    private String name;
    private int size;
    private List<Node> children;

    public Node(String name, int size) {
        this.name = name;
        this.size = size;
        children = new ArrayList<>();
    }

    public boolean isFile() {
        return this.size > 0;
    }

    public void insert(String name, int size) {
        this.children.add(new Node(name, size));
    }

    public void traverseInOrder() {

        for (Node n : this.children) {
            System.out.println(n);
            n.traverseInOrder();
        }

    }

    public static int getSizeOfDirectory(Node node) {

        int c = 0;

        for (Node n : node.children) {
            c += n.getSize() + getSizeOfDirectory(n);
        }

        return c;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }

    public Node findChildByName(String dirName) {
        return this.children.stream().filter(element -> element.getName().equals(dirName)).findAny().get();
    }
}


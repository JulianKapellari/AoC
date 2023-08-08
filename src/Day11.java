import javax.management.OperationsException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Day11 {

    public static void main(String[] args) {

        List<Monkey> monkeys = initializeMonkeys();

        for (int i = 1; i <= 10000; i++) {


            for (Monkey monkey : monkeys) {
                List<List<Integer>> removeItems = new ArrayList<>();

                for (List<Integer> item : monkey.getItemList()) {
                    Integer newItem = monkey.getOperation().apply(item.get(0));

                    int sum = 0;
                    for (Integer integer : item) {
                        sum += integer % monkey.getDivisible();
                    }
                    String monkeyNameToThrow;
                    if (sum + (newItem % monkey.getDivisible()) != 0) {
                        monkeyNameToThrow = monkey.getIfFalseMonkey();
                    } else {
                        monkeyNameToThrow = monkey.getIfTrueMonkey();
                    }

                    int indexOfMonkey = monkeys.indexOf(monkeys.stream().filter(e -> e.getName().equalsIgnoreCase(monkeyNameToThrow)).findAny().get());
                    List<Integer> newList = new ArrayList<>(item);
                    newList.add(newItem);
                    monkeys.get(indexOfMonkey).addItem(newList);
                    removeItems.add(item);
                    monkey.setInspectedItemsAmount(monkey.getInspectedItemsAmount() + 1);
                }
                monkey.removeItems(removeItems);
            }
            if (i == 20) {
                System.out.println("After " + i + " rounds...");
                for (Monkey monkey : monkeys) {
                    System.out.println(monkey.getName() + " - inspected " + monkey.getInspectedItemsAmount() + " times.");
                }
                System.out.println();
            }
            if (i % 1000 == 0) {
                System.out.println("After " + i + " rounds...");
                for (Monkey monkey : monkeys) {
                    System.out.println(monkey.getName() + " - inspected " + monkey.getInspectedItemsAmount() + " times.");
                }
                System.out.println();
            }
        }
        System.out.println("After " + 10000 + " rounds...");
        for (Monkey monkey : monkeys) {
            System.out.println(monkey.getName() + " - inspected " + monkey.getInspectedItemsAmount() + " times.");
        }
        System.out.println();


    }

    private static Function<Integer, Integer> getOperation(String[] array) throws OperationsException {
        Integer number = null;
        try {
            number = Integer.parseInt(array[2]);
        } catch (NumberFormatException e) {

        }
        Integer finalNumber = number;
        switch (array[1]) {
            case "+":
                return (e -> e);
            case "*":
                return number != null ? (e -> finalNumber) : (e -> e);
            default:
                throw new OperationsException("Wrong Operation (*/+)");
        }

    }

    private static List<Monkey> initializeMonkeys() {
        List<Monkey> monkeyList = new ArrayList<>();
        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input11");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line = null;

            String name;

            Function<Integer, Integer> operation;
            Integer divisible;
            while ((line = fileReader.readLine()) != null) {
                List<List<Integer>> items = new ArrayList<>();

                name = line.split(":")[0];
                Arrays.stream(fileReader.readLine().split(":")[1].split(","))
                        .forEach(item -> {
                            List<Integer> temp = new ArrayList<>();
                            temp.add(Integer.parseInt(item.trim()));
                            items.add(new ArrayList<>(temp));
                        });

                String[] array = fileReader.readLine().split("=")[1].trim().split(" ");
                operation = getOperation(array);
                String[] array2 = fileReader.readLine().split(" ");
                divisible = Integer.parseInt(array2[array2.length - 1]);
                String[] ifTrue = fileReader.readLine().split(" ");
                String trueThrowMonkeyName = ifTrue[ifTrue.length - 2] + " " + ifTrue[ifTrue.length - 1];
                String[] ifFalse = fileReader.readLine().split(" ");
                String falseThrowMonkeyName = ifFalse[ifFalse.length - 2] + " " + ifFalse[ifFalse.length - 1];

                monkeyList.add(new Monkey(name, items, operation, divisible, falseThrowMonkeyName, trueThrowMonkeyName));

                fileReader.readLine();
            }


        } catch (IOException e) {
            System.out.println(e);
        } catch (OperationsException e) {
            e.printStackTrace();
        }

        return monkeyList;
    }

}

class Monkey {

    private int inspectedItemsAmount = 0;
    private String name;
    private List<List<Integer>> itemList;
    private Function<Integer, Integer> operation;
    private Integer divisible;
    private String ifFalseMonkey;
    private String ifTrueMonkey;

    public Monkey(String name, List<List<Integer>> itemList, Function<Integer, Integer> operation, Integer divisible, String ifFalseMonkey, String ifTrueMonkey) {
        this.name = name;
        this.itemList = itemList;
        this.operation = operation;
        this.divisible = divisible;
        this.ifFalseMonkey = ifFalseMonkey;
        this.ifTrueMonkey = ifTrueMonkey;
    }

    public void removeItems(List<List<Integer>> item) {
        this.itemList.removeAll(item);
    }

    public void addItem(List<Integer> item) {
        this.itemList.add(item);
    }

    public String getName() {
        return name;
    }


    public List<List<Integer>> getItemList() {
        return itemList;
    }


    public Function<Integer, Integer> getOperation() {
        return operation;
    }

    public Integer getDivisible() {
        return divisible;
    }

    public String getIfFalseMonkey() {
        return ifFalseMonkey;
    }

    public String getIfTrueMonkey() {
        return ifTrueMonkey;
    }

    public int getInspectedItemsAmount() {
        return inspectedItemsAmount;
    }

    public void setInspectedItemsAmount(int inspectedItemsAmount) {
        this.inspectedItemsAmount = inspectedItemsAmount;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "name='" + name + '\'' +
                ", itemList=" + itemList +
                ", operation=" + operation +
                '}';
    }
}
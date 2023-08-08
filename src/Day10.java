import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day10 {

    static int x = 1;
    static int cycle = 0;
    static int sum = 0;

    public static void main(String[] args) {

        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input10");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                String[] split = line.split(" ");

                if (split[0].equals("noop")) {
                    cycle++;
                    extracted();
                } else {
                    cycle++;
                    extracted();
                    cycle++;
                    extracted();
                    x += Integer.parseInt(split[1]);
                }
            }


        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private static void extracted() {
        // TODO Problem with cycle == 40
        if ((cycle % 40 >= x && cycle % 40 < x + 3)) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
        if (cycle % 40 == 0) {
            sum += cycle * x;
            System.out.println();
        }
    }
}


/*
   String[] split = line.split(" ");

                if(split[0].equals("noop")){
                    cycle++;
                    if(cycle % 20 == 0 && (cycle / 20) % 2 == 1){
                        System.out.println(cycle + " / " + x);
                        sum += cycle*x;
                    }
                }else{cycle++;
                    if(cycle % 20 == 0 && (cycle / 20) % 2 == 1){
                        System.out.println(cycle + " / " + x);
                        sum += cycle*x;
                    }
                    cycle++;
                    if(cycle % 20 == 0 && (cycle / 20) % 2 == 1){
                        System.out.println(cycle + " / " + x);
                        sum += cycle*x;
                    }
                    x+= Integer.parseInt(split[1]);
                }
 */
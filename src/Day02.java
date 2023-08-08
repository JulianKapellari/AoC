import java.io.*;

public class Day02 {

    public static void main(String[] args) {

        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input02");
        int sum = 0;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = fileReader.readLine())!= null){
                String[] round = line.split(" ");
                sum+=(manipulate(round[0],round[1]));
            }

        }catch (IOException e){
            System.out.println(e);
        }

        System.out.println(sum);
    }

    public static int checkWinner(String elf, String self){
        if(elf.equals("A")){
            switch (self){
                case "X":
                    return 3;
                case "Y":
                    return 6;
                case "Z":
                    return 0;
            }
        }else if(elf.equals("B")){
            switch (self){
                case "X":
                    return 0;
                case "Y":
                    return 3;
                case "Z":
                    return 6;
            }
        }else{
            switch (self){
                case "X":
                    return 6;
                case "Y":
                    return 0;
                case "Z":
                    return 3;
            }
        }
        return 0;
    }

    public static int manipulate(String elf, String self){
        if(elf.equals("A")){
            switch (self){
                case "X":
                    return 3;
                case "Y":
                    return 4;
                case "Z":
                    return 8;
            }
        }else if(elf.equals("B")){
            switch (self){
                case "X":
                    return 1;
                case "Y":
                    return 5;
                case "Z":
                    return 9;
            }
        }else{
            switch (self){
                case "X":
                    return 2;
                case "Y":
                    return 6;
                case "Z":
                    return 7;
            }
        }
        return 0;
    }

}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by naughtybit on 7/24/17.
 */
public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("Please choose method of input:\t1)from file\t2)by entering in console\t3)from args");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int typeOfInput = selectVar(1, 3, reader);

        System.out.println("select method of sorting: \t1)by alphabet\t2)by length");

        int typeOfSort = selectVar(1, 2, reader);


        switch (typeOfInput){
            case 1:
                System.out.println("enter path to file");
                Path path = null;
                while (!Files.exists(path = Paths.get(reader.readLine()))){
                    System.out.println("Enter valid path");
                };

                try(BufferedReader fileReader = new BufferedReader(new FileReader(path + ""))) {
                    ArrayList<String> list = new ArrayList<>();
                    while (true){
                        String line = fileReader.readLine();
                        if (line==null)break;
                        if (line.equals(""))break;
                        list.add(line);
                    }
                    sorting(typeOfSort, list.toArray(new String[list.size()]));
                    System.out.println();
                }catch (IOException e){
                }

                break;
            case 2:
                System.out.println("enter lines while it would not be equals \"exit\"");
                String line = "";
                ArrayList<String> list = new ArrayList<>();
                while (!(line = reader.readLine()).equals("exit")){
                    list.add(line);
                }
                sorting(typeOfSort, list.toArray(new String[list.size()]));
                break;
            case 3:
                sorting(typeOfSort, args);
                break;
        }

        System.out.println("end of program");
    }

    private static int selectVar(int min, int max, BufferedReader reader){
        int typeOfInput = -1;

        try {
            typeOfInput = Integer.parseInt(reader.readLine());
            if (typeOfInput > max || typeOfInput < min){
                throw  new NumberFormatException();
            }else return typeOfInput;
        }catch (NumberFormatException e){
            System.out.println("Not a valid type! Select another");
            return selectVar(min, max, reader);
        }catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }
    private static void sorting(int type, String[] str){
        long start = System.currentTimeMillis();
        if (type == 1){
            Arrays.sort(str);
            for (int i = 0; i < str.length; i++){
                System.out.println(str[i]);
            }        } else if (type == 2){
            Comparator<String> comparator = (o1, o2) -> o1.length() - o2.length();
            Arrays.sort(str, comparator);
            for (String s : str){
                System.out.println(s);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("time of sorting: " + (end - start) +" mlsec");
    }

}

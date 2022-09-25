import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class RenameReports {
    public int findIndexOfPostfix(String name) {
        int postfixMRDR = 0;
        for (int i = 0; i < name.length(); i++) {
            //find index number of "_S" in filename (example_684325_S -> 14)
            if (String.valueOf(name.charAt(i)).equals("_") && String.valueOf(name.charAt(i + 1)).equals("S")) {
                postfixMRDR = i;
            }
        }
        return postfixMRDR;
    }
    public String newName(String plant, String name) {
        String mrdr = "";
        int postfixMRDR = findIndexOfPostfix(name);
            for (int index = 6; index > 0; index--) {    //read mrdr number before "_S"
                mrdr += String.valueOf(name.charAt(postfixMRDR - index));
            }
        String newName = mrdr + "_" + plant + "_" + name;
            return newName;
    }

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please write Plant name you want to add to description:");
        String plant = sc.nextLine();

        RenameReports rename = new RenameReports();
        //Creating a File object for directory
        File directoryPath = new File("./reports");
        //List of all files and directories
        File filesList[] = directoryPath.listFiles();
        System.out.println("List of files and directories in the specified directory:");
        for(File file : filesList) {
            System.out.println("File name: "+file.getName());
            System.out.println("File path: "+file.getAbsolutePath());
            System.out.println("Size :"+file.getTotalSpace());
            System.out.println(" ");
            System.out.println("Name length: " + file.getName().length());

            if(file.getName().contains("_S") && file.getName().length() > 11) { //including ".zip":)
                String newName = rename.newName(plant, file.getName());
                System.out.println("New name: " + newName);
                File newFile = new File("./reports/" + newName);

                if (file.renameTo(newFile)) {
                    System.out.println("File renamed");
                } else {
                    System.out.println("Sorry! the file can't be renamed");
                }
            } else {
                System.out.println("Name too short");
            }
        }
    }
}

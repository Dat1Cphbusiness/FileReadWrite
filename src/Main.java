/*
Denne kode viser hvordan man kan indlæse data om nogle teams til at oprette Team objekter
Efter manipulation med Team objekterne (ændring af deres score), gemmer vi data ved at overskrive den indlæste data.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Team> teams = new ArrayList<>();

    public static void main(String[] args){
        File file = new File("data/teams.csv");
        String header = null;// vi skal bruge headeren til når vi gemmer tilstanden i saveData metoden

        try {
             Scanner scan = new Scanner(file);

             header = scan.nextLine();//skip header

            while(scan.hasNextLine()){
                String line = scan.nextLine();            // line er nu en String med hele linjen: "De uoverindelige, 1, 34"
                String[] values  = line.split(","); // values er nu et array med alle værdierne i linjen: ["De uovervindelige", "1", "34"]
                String name = values[0];                  // tager fat i første værdi
                int groupID = Integer.parseInt(values[1].trim());// tager fat i 2. værdi
                int score = Integer.parseInt(values[2].trim());  // tager fat i 3. værdi

                Team t = new Team(name,groupID, score);
                teams.add(t);
            }

        }catch (FileNotFoundException e){
            System.out.println("file not found");

        }

        //print Teams, så vi kan se at data er blevet korrekt loadet ind
        for (Team t:teams) {
            System.out.println(t);
        }



        //For at teste persistens koden (saveData), simulerer vi her at der sker ændringer i tilstanden:
        teams.get(2).setScore(10);
        teams.get(1).setScore(-10);
        teams.get(0).setScore(50);


        //Gemmer den nye tilstand
        try {
            saveData(header);
        } catch (IOException e) {
            System.out.println("hov, det gik galt med at gemme...");
        }
    }

    private static void saveData(String header) throws IOException{

            FileWriter writer = new FileWriter("data/teams.csv");
            writer.write(header+"\n");
            for (Team t: teams) {
                writer.write(t.toCSVString() + "\n");
            }
          writer.close();


    }

    public static void readCountries(){

       File file = new File("data/data.csv");
       try{
           Scanner scan = new Scanner(file);
           Scanner userscan = new Scanner(System.in);
           String userinput = userscan.nextLine();
           scan.nextLine();//  skip header
           while(scan.hasNextLine()){
               String line = scan.nextLine();//  "France, Paris, t"
               String[] values = line.split(","); //values[0]="France", values[1]="Paris", values[2] ="t"

               String country =   values[0];
               String capital =  values[1];

               Boolean euMember = Boolean.parseBoolean(values[2].trim()); // " true"  true
               //  System.out.println("country: "+ country + " capital: "+capital+ " is member of EU:"+euMember);


               if (userinput.equals(country)) {
                   System.out.println("the capital of " + userinput + " is " + capital);

               }

           }
       }catch(FileNotFoundException e){

           System.out.println("filen blev ikke fundet");

       }catch(Exception e){


       }

   }





}
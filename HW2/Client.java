import java.util.Scanner;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.Date;

//Main class (driver)
public class Client {
    public static void main(String[] args) {
        //Begin logging to file for process, overwrites anything currently in the file
        try{
            FileWriter fw=new FileWriter("brady_cornett_database.txt",false);
            Date date= new Date();long time = date.getTime();Timestamp ts = new Timestamp(time);
            fw.write(ts + "+++++++++++++++++++++++++++++\n");
            fw.write(ts + ":Starting run of Homework 2:\n");
            fw.write(ts + "+++++++++++++++++++++++++++++\n");fw.close();
        }catch(Exception e){System.out.println(e);}
        //Array of data for storing to database
        String[] data = {"ssn","name","state"};
        //Menu continuity
        while(true) {
            DBtype database;
            System.out.println("What type of Database Storage do you wish to utilize?");
            System.out.println("OPTIONS: Relational, NoSQL, Graph");
            System.out.print("(exit to terminate): ");
            Scanner input = new Scanner(System.in); String rawInput = input.nextLine(); String refInput = rawInput.toLowerCase();
            //Decisioning for which DB type is picked, appends that database option to file
            switch (refInput){
                case "relational":
                    try{
                        FileWriter fw=new FileWriter("brady_cornett_database.txt",true);
                        Date date= new Date(); long time = date.getTime(); Timestamp ts = new Timestamp(time);
                        fw.write(ts +" Relational Database.\n");fw.close();
                    }catch(Exception e){System.out.println(e);}
                    database = new Relational();
                    database.Store(data);
                    database.askStrat();
                    database.Store(data);
                    break;
                case "nosql":
                    try{
                        FileWriter fw=new FileWriter("brady_cornett_database.txt",true);
                        Date date= new Date(); long time = date.getTime(); Timestamp ts = new Timestamp(time);
                        fw.write(ts +" NoSQL Database.\n");fw.close();
                    }catch(Exception e){System.out.println(e);}
                   database = new NoSQL();
                   database.Store(data);
                   database.askStrat();
                   database.Store(data);
                    break;
                case "graph":
                    try{
                        FileWriter fw=new FileWriter("brady_cornett_database.txt",true);
                        Date date= new Date();long time = date.getTime();Timestamp ts = new Timestamp(time);
                        fw.write(ts +" Graph Database.\n");fw.close();
                    }catch(Exception e){System.out.println(e);}
                    database = new Graph();
                    database.Store(data);
                    database.askStrat();
                    database.Store(data);
                    break;
                case "exit":
                    try{
                        FileWriter fw=new FileWriter("brady_cornett_database.txt",true);
                        Date date= new Date();long time = date.getTime();Timestamp ts = new Timestamp(time);
                        fw.write(ts + " SYSTEM EXITING");fw.close();
                    }catch(Exception e){System.out.println(e);}
                    System.out.println("\nExiting.");
                    System.exit(0);
                default:
                    System.out.println("\nEnter an option provided.\n");
            }
        }
    }
}
//Parent DB Class
class DBtype {
    //Stores which interface is currently implemented
    public iStore datastoreplan;
    //
    public void Store(String[] inputData){
        datastoreplan.dbstore(inputData);
    }
    public void setStoreStrategy(iStore strategy){
        System.out.print("\nChanging Database Method...\n");
        datastoreplan = strategy;
    }
    //Method for asking the user what they want to change their database method to
    public void askStrat(){
        boolean run = true;
        while(run) {
            System.out.println("What storage algorithm do you want to use?");
            System.out.println("OPTIONS: Table, Document, Node");
            System.out.print("(back to return to initial menu): ");
            Scanner input = new Scanner(System.in);String rawData = input.nextLine();String refData = rawData.toLowerCase();
            switch (refData) {
                case "table":
                    setStoreStrategy(new table_store());
                    run = false;break;
                case "document":
                    setStoreStrategy(new document_store());
                    run = false;break;
                case "node":
                    setStoreStrategy(new node_store());
                    run = false;break;
                case "back":
                    System.out.println("\nReturning back to main menu...\n");
                   run = false;break;
                default:
                    System.out.println("\nEnter an option provided.\n");
            }
        }
    }
}

//Relational database extension, defaults the method to table
class Relational extends DBtype {
    public Relational(){
        datastoreplan = new table_store();
    }
}

//NoSQL database extension, defaults the method to document
class NoSQL extends DBtype {
    public NoSQL(){
        datastoreplan = new document_store();
    }
}

//Graph database extension, defaults the method to node
class Graph extends DBtype{
    public Graph(){
        datastoreplan = new node_store();
    }
}

//Table store class that implements the iStore interface
class table_store implements iStore{
    @Override
    public void dbstore(String[] inputData){
        System.out.println("\nUsing Table Store.\n");
        try{
            FileWriter fw=new FileWriter("brady_cornett_database.txt",true);
            Date date= new Date(); long time = date.getTime(); Timestamp ts = new Timestamp(time);
            for(int i = 0; i < inputData.length; i++){fw.write(ts + " " + inputData[i] + " \n");}
            fw.write(ts + " Written using TABLE store.\n\n");fw.close();
        }catch(Exception e){System.out.println(e);}
    }
}

//Document class method that implements the iStore interface
class document_store implements iStore{
    @Override
    public void dbstore(String[] inputData){
        System.out.println("\nUsing Document Store.\n");
        try{
            FileWriter fw=new FileWriter("brady_cornett_database.txt", true);
            Date date= new Date(); long time = date.getTime(); Timestamp ts = new Timestamp(time);
            for(int i = 0; i < inputData.length; i++){fw.write(ts + " " + inputData[i] + " \n");}
            fw.write(ts + " Written using DOCUMENT store.\n\n");fw.close();
        }catch(Exception e){System.out.println(e);}
    }
}

//Node store class that implements the iStore interface
class node_store implements iStore{
    @Override
    public void dbstore(String[] inputData){
        System.out.println("\nUsing Node Store.\n");
        try{
            FileWriter fw=new FileWriter("brady_cornett_database.txt", true);
            Date date= new Date(); long time = date.getTime(); Timestamp ts = new Timestamp(time);
            for(int i = 0; i < inputData.length; i++){fw.write(ts + " " + inputData[i] + " \n");}
            fw.write(ts + " Written using NODE store.\n\n");fw.close();
        }catch(Exception e){System.out.println(e);}
    }
}

//Interface implemented by the *_store methods
interface iStore {
        void dbstore(String[] inputData);
}

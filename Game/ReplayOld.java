

package Game;

import Inputs.Inputs;
import java.util.ArrayList;
import java.io.*;


public class ReplayOld {
    
    public static String fileName = "Save Replay.txt";
    public static ArrayList<String> data = new ArrayList<String>();
    public static ArrayList<String> moveList = new ArrayList<String>();
    public static boolean replay;
    public static String line;
    public static int currentMove;
    public static String scanString;
    public static int scanStringIndex;
    
    public static void saveReplay() {
        
        try {
            
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for (int i = 0; i < data.size(); i++) {
                
                if (data.get(i) != null)
                    bufferedWriter.write(data.get(i).toString());
                else
                    bufferedWriter.write("null");
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
        }
        
        catch (IOException ex) {
            
            ex.printStackTrace();
        }
        
        System.out.println("Replay has been saved.");
    }
    
    public static void record(String action) {
        
        data.add(action);
        
        if (data.size() >= 4) {
            
            //remove multiple select squares in a row
            if (action.equals("select square") && data.get(data.size() - 4).equals("select square")) {
                data.remove(data.size() - 4);
                data.remove(data.size() - 3);
                data.remove(data.size() - 2);
            }
        }
    }
    
    public static void loadReplay() {
        
        moveList.clear();
        
        try {
            
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while ((line = bufferedReader.readLine()) != null)
                moveList.add(line);
            
            bufferedReader.close();
        }
        
        catch (IOException ex) {
            
            ex.printStackTrace();
        }
        
        replay = true;
        
        System.out.println("Replay has been loaded.");
    }
    
//    public static void nextMove() {
//        
//        if (currentMove < moveList.size()) {
//            
//            if (moveList.get(currentMove).equals("confirm")) {
//                
//                Inputs.confirm();
//                currentMove++;
//            }
//            
//            else if (moveList.get(currentMove).equals("end phase")) {
//                
//                Inputs.endPhase();
//                currentMove++;
//            }
//            
//            else if (moveList.get(currentMove).equals("move")) {
//                
//                Inputs.move();
//                currentMove++;
//            }
//            
//            else if (moveList.get(currentMove).equals("attack")) {
//                
//                Inputs.attack();
//                currentMove++;
//            }
//            
//            else if (moveList.get(currentMove).equals("change direction")) {
//                
//                Inputs.changeDirection();
//                currentMove++;
//            }
//            
//            else if (moveList.get(currentMove).equals("increase attack number")) {
//                
//                Inputs.increaseAttackNumber();
//                currentMove++;
//            }
//            
//            else if (moveList.get(currentMove).equals("select unit")) {
//                
//                Inputs.selectUnit(moveList.get(currentMove + 1), moveList.get(currentMove + 2));
//                currentMove += 3;
//            }
//            
//            else if (moveList.get(currentMove).equals("select square")) {
//                
//                Inputs.selectSquare(Integer.parseInt(moveList.get(currentMove + 1)), Integer.parseInt(moveList.get(currentMove + 2)));
//                currentMove += 3;
//            }
//        }
//        
//        else
//            System.out.println("Replay has ended.");
//    }
    
    public static void nextMove() {
        
        if (currentMove < moveList.size()) {
            
            scanString = moveList.get(currentMove);
        
            scanStringIndex = 0;
            
            if (scanString.length() >= "activate units".length()) {
            
                if (scanString.substring(0, "activate units".length()).equals("activate units")) {

                    scanStringIndex += "activate units".length() + 1;

                    while (scanStringIndex < scanString.length()) {

                        Inputs.selectUnit(scanString.substring(scanStringIndex, scanString.indexOf(" ", scanStringIndex + 1)), scanString.substring(scanString.indexOf(" ", scanStringIndex) + 1, scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1 + 1)));
                        scanStringIndex = scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1) + 1;
                    }

                    Inputs.confirm();
                }
            }
            
            if (scanString.length() >= "change direction".length()) {
            
                if (scanString.substring(0, "change direction".length()).equals("change direction")) {

                    scanStringIndex += "change direction".length() + 1;

                    Inputs.changeDirection();

                    Inputs.selectUnit(scanString.substring(scanStringIndex, scanString.indexOf(" ", scanStringIndex + 1)), scanString.substring(scanString.indexOf(" ", scanStringIndex) + 1, scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1 + 1)));
                    scanStringIndex = scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1) + 1;

                    Inputs.selectSquare(Integer.parseInt(scanString.substring(scanStringIndex, scanString.indexOf(" ", scanStringIndex + 1))), Integer.parseInt(scanString.substring(scanString.indexOf(" ", scanStringIndex) + 1, scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1 + 1))));

                    Inputs.confirm();
                }
            }
            
            if (scanString.length() >= "move".length()) {
            
                if (scanString.substring(0, "move".length()).equals("move")) {

                    scanStringIndex += "move".length() + 1;

                    Inputs.move();

                    Inputs.selectUnit(scanString.substring(scanStringIndex, scanString.indexOf(" ", scanStringIndex + 1)), scanString.substring(scanString.indexOf(" ", scanStringIndex) + 1, scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1 + 1)));
                    scanStringIndex = scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1) + 1;

                    Inputs.selectSquare(Integer.parseInt(scanString.substring(scanStringIndex, scanString.indexOf(" ", scanStringIndex + 1))), Integer.parseInt(scanString.substring(scanString.indexOf(" ", scanStringIndex) + 1, scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1 + 1))));

                    Inputs.confirm();
                }
            }
            
            if (scanString.length() >= "attack".length()) {
            
                if (scanString.substring(0, "attack".length()).equals("attack")) {

                    scanStringIndex += "attack".length() + 1;

                    Inputs.attack();

                    Inputs.selectUnit(scanString.substring(scanStringIndex, scanString.indexOf(" ", scanStringIndex + 1)), scanString.substring(scanString.indexOf(" ", scanStringIndex) + 1, scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1 + 1)));
                    scanStringIndex = scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1) + 1;
                    
                    Inputs.attackNumber = Integer.parseInt(scanString.substring(scanStringIndex, scanString.indexOf(" ", scanStringIndex + 1)));
                    scanStringIndex = scanString.indexOf(" ", scanStringIndex) + 1;

                    Inputs.selectSquare(Integer.parseInt(scanString.substring(scanStringIndex, scanString.indexOf(" ", scanStringIndex + 1))), Integer.parseInt(scanString.substring(scanString.indexOf(" ", scanStringIndex) + 1, scanString.indexOf(" ", scanString.indexOf(" ", scanStringIndex) + 1 + 1))));

                    Inputs.confirm();
                }
            }
            
            if (scanString.length() >= "end phase".length()) {
            
                if (scanString.substring(0, "end phase".length()).equals("end phase")) {

                    Inputs.endPhase();
                }
            }
            
            currentMove++;
        }
        
        else
            System.out.println("Replay has ended.");
    }
}

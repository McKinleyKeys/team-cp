

package Game;

import Engine.GameDisplay;
import Engine.Physics;
import GameObject.Archer;
import GameObject.Builder;
import GameObject.Builder_HealBot;
import GameObject.Builder_Statue;
import GameObject.Healer;
import GameObject.Knight;
import GameObject.Mage;
import GameObject.Scout;
import GameObject.Spy;
import GameObject.TNTGuy;
import GameObject.Unit;
import Inputs.Inputs;
import Maps.Map;
import java.util.ArrayList;
import java.util.Arrays;


public class Game {
    
    public static Map map;
    public static Archer blueArcher = new Archer("blue", 0, 0, 0, 0);
    public static Builder blueBuilder = new Builder("blue", 0, 0, 0, 0);
    public static Builder_HealBot blueHealBot = new Builder_HealBot("blue", 0, 0, 0, 0);
    public static Builder_Statue blueStatue = new Builder_Statue("blue", 0, 0, 0, 0);
    public static Healer blueHealer = new Healer("blue", 0, 0, 0, 0);
    public static Knight blueKnight = new Knight("blue", 0, 0, 0, 0);
    public static Mage blueMage = new Mage("blue", 0, 0, 0, 0);
    public static Scout blueScout = new Scout("blue", 0, 0, 0, 0);
    public static Spy blueSpy = new Spy("blue", 0, 0, 0, 0, 0, 0);
    public static TNTGuy blueTNTGuy = new TNTGuy("blue", 0, 0, 0, 0);
    public static Archer redArcher = new Archer("red", 0, 0, 0, 0);
    public static Builder redBuilder = new Builder("red", 0, 0, 0, 0);
    public static Builder_HealBot redHealBot = new Builder_HealBot("red", 0, 0, 0, 0);
    public static Builder_Statue redStatue = new Builder_Statue("red", 0, 0, 0, 0);
    public static Healer redHealer = new Healer("red", 0, 0, 0, 0);
    public static Knight redKnight = new Knight("red", 0, 0, 0, 0);
    public static Mage redMage = new Mage("red", 0, 0, 0, 0);
    public static Scout redScout = new Scout("red", 0, 0, 0, 0);
    public static Spy redSpy = new Spy("red", 0, 0, 0, 0, 0, 0);
    public static TNTGuy redTNTGuy = new TNTGuy("red", 0, 0, 0, 0);
    public static ArrayList<Unit> units;
    public static ArrayList<Unit> aliveUnits;
    public static ArrayList<Unit> deadUnits = new ArrayList<Unit>();
    public static Board board;
    public static ArrayList<int[]> obstacles;
    public static int[] round = {1, 1, 0};
    public static String playerTurn = "blue";
    public static boolean doNotAddSquare;
    public static ArrayList blueView = new ArrayList();
    public static ArrayList redView = new ArrayList();
    public static int[] teamViewAdd = new int[2];
    public static ArrayList<Unit> activeUnits = new ArrayList();
    public static ArrayList<Unit> getActiveUnits = new ArrayList();
    public static int blueScore = 0;
    public static int redScore = 0;
    public static boolean game = true;
    public static boolean cpBlueControlled = false;
    public static boolean cpRedControlled = false;
    
    public Game() {
        
        units = new ArrayList<Unit>(Arrays.asList(blueArcher, blueBuilder, blueHealBot, blueStatue, blueHealer, blueKnight, blueMage, blueScout, blueSpy, blueTNTGuy, redArcher, redBuilder, redHealBot, redStatue, redHealer, redKnight, redMage, redScout, redSpy, redTNTGuy));
        //aliveUnits = new ArrayList<Unit>(units);
        aliveUnits = new ArrayList<Unit>();
        //board = new Board(obstacles, 10, 10, 0, 0);
        
    }
    
//    public static void nextTurn() {
//        
//        while (game) {
//            
//            if (!Replay.skipTurn) {
//            
//                round[2]++;
//                if (round[2] >= 3) {
//
//                    round[1]++;
//                    round[2] = 1;
//                }
//                if (round[1] >= 4) {
//
//                    round[0]++;
//                    round[1] = 1;
//
//                    //start of new round stuff
//                    for (Unit unit : units)
//                        unit.resetActivated();
//                }
//            }
//            
//            if (round[2] == 1)
//                playerTurn = "blue";
//            else
//                playerTurn = "red";
//            
//            GameDisplay.changePlayerTurn();
//            
//            if (!Replay.skipTurn) {
//                
//                //update all units and make sure they are deactivated
//                for (int i = 0; i < units.size(); i++) {
//
//                    units.get(i).active = false;
//                    units.get(i).update(round);
//                }
//                
//                //set all units' movement speed to 1 so GameDisplay doesn't say that they are finished
//                for (int i = 0; i < aliveUnits.size(); i++)
//                    aliveUnits.get(i).movesLeft = 1;
//            }
//            
//            getActiveUnits = Inputs.getActiveUnits();
//            
//            if (!Inputs.skipActivateUnits && !Replay.skipTurn) {
//                
//                activeUnits.clear();
//                
//                System.out.println("clear at Game");
//                
//                for (int i = 0; i < getActiveUnits.size(); i++) {
//                    
//                    activeUnits.add(getActiveUnits.get(i));
//                }
//            }
//            
//            if (!Inputs.skipActivateUnits && !Replay.skipTurn) {
//            
//                //set all active units to full movement speed and activate them
//                for (int i = 0; i < activeUnits.size(); i++) {
//
//                    activeUnits.get(i).movesLeft = activeUnits.get(i).movement;
//                    activeUnits.get(i).attacksLeft = 1;
//                    activeUnits.get(i).changeDirectionsLeft = 1;
//                    activeUnits.get(i).active = true;
//                    activeUnits.get(i).activated = true;
//
//                    if (activeUnits.get(i).unitClass == "Healer")
//                        activeUnits.get(i).freeAttacksLeft = 0;
//                }
//
//                //set all non-active units' movement to 1
//                for (int i = 0; i < aliveUnits.size(); i++)
//                    if (!(activeUnits.contains(aliveUnits.get(i))) && aliveUnits.get(i).teamName == playerTurn) {
//
//                        aliveUnits.get(i).movesLeft = 1;
//                        aliveUnits.get(i).attacksLeft = 0;
//                        aliveUnits.get(i).changeDirectionsLeft = 0;
//                        aliveUnits.get(i).active = false;
//
//                        //set non-active healers' freeAttacksLeft to 1
//                        if (aliveUnits.get(i).unitClass == "Healer")
//                            aliveUnits.get(i).freeAttacksLeft = 1;
//                    }
//            }
//            
//            Replay.skipTurn = false;
//            
//            Inputs.skipActivateUnits = false;
//
//            Inputs.changeDirectionPhase();
//
//            Inputs.moveAndAttackPhase();
//            
//            if (!Replay.skipTurn)
//                endOfTurn();
//        }
//    }
    
    public static void nextTurn() {
        
        while (game) {
            
            if (!Replay.skipToPhase && Inputs.phase.equals("start of turn")) {
                
                startOfTurn();
                
                Inputs.phase = "activate units";
            }

            if (Inputs.phase.equals("activate units")) {
                
                Replay.skipToPhase = false;
                
                activateUnitsPhase();
                
                if (!Replay.skipToPhase)
                    //Inputs.phase = "change direction";
                    Inputs.phase = "move and attack";
            }
            
//            if (Inputs.phase.equals("change direction")) {
//                
//                Replay.skipToPhase = false;
//                
//                Inputs.changeDirectionPhase();
//                
//                if (!Replay.skipToPhase)
//                    Inputs.phase = "move and attack";
//            }
            
            if (Inputs.phase.equals("move and attack")) {
                
                Replay.skipToPhase = false;
                
                Inputs.moveAndAttackPhase();
                
                if (!Replay.skipToPhase)
                    Inputs.phase = "end of turn";
            }
            
            if (!Replay.skipToPhase && Inputs.phase.equals("end of turn")) {
                
                endOfTurn();
                
                Inputs.phase = "start of turn";
            }
        }
    }
    
    public static void updatePlayerView(String teamName) {
        
        if (teamName == "blue")
            blueView.clear();
        else
            redView.clear();
        
        for (int i = 0; i < aliveUnits.size(); i++)
            if (aliveUnits.get(i).teamName == teamName)
                for (int x = board.x1; x <= board.x1 + board.sizeX - 1; x++)
                    for (int y = board.y1; y <= board.y1 + board.sizeY - 1; y++) {
                        
                        doNotAddSquare = false;
                        
                        if (x == aliveUnits.get(i).posX && y == aliveUnits.get(i).posY) {
                            
                            if (teamName == "blue") {
                                
                                //check if square is already there
                                for (int a = 0; a < blueView.size(); a += 2) {
                                    
                                    if (x == (int)blueView.get(a) && y == (int)blueView.get(a + 1))
                                        doNotAddSquare = true;
                                }
                                
                                if (doNotAddSquare == false) {
                                    blueView.add(x);
                                    blueView.add(y);
                                }
                            }
                            else {
                                
                                //check if square is already there
                                for (int a = 0; a < redView.size(); a += 2) {
                                    
                                    if (x == (int)redView.get(a) && y == (int)redView.get(a + 1))
                                        doNotAddSquare = true;
                                }
                                
                                if (doNotAddSquare == false) {
                                    redView.add(x);
                                    redView.add(y);
                                }
                            }
                        }
                        
                        else if ((double)Physics.checkRange(aliveUnits.get(i).posX, aliveUnits.get(i).posY, x, y, aliveUnits.get(i).FOV, aliveUnits.get(i).direction, teamName, false).get(0) > 0d) {
                            
                            if (teamName == "blue") {
                                
                                //check if square is already there
                                for (int a = 0; a < blueView.size(); a += 2) {
                                    
                                    if (x == (int)blueView.get(a) && y == (int)blueView.get(a + 1))
                                        doNotAddSquare = true;
                                }
                                
                                if (doNotAddSquare == false) {
                                    blueView.add(x);
                                    blueView.add(y);
                                }
                            }
                            else {
                                
                                //check if square is already there
                                for (int a = 0; a < redView.size(); a += 2) {
                                    
                                    if (x == (int)redView.get(a) && y == (int)redView.get(a + 1))
                                        doNotAddSquare = true;
                                }
                                
                                if (doNotAddSquare == false) {
                                    redView.add(x);
                                    redView.add(y);
                                }
                            }
                        }
                    }
        
        //camera
        if (teamName == "blue" && blueSpy.cameraPlaced) {
            
            for (int x = blueSpy.camPosX - 1; x <= blueSpy.camPosX + 1; x++)
                for (int y = blueSpy.camPosY - 1; y <= blueSpy.camPosY + 1; y++) {
                    
                    doNotAddSquare = false;
                    
                    //check if square is already there
                    for (int a = 0; a < blueView.size(); a += 2) {

                        if (x == (int)blueView.get(a) && y == (int)blueView.get(a + 1))
                            doNotAddSquare = true;
                    }

                    if (doNotAddSquare == false) {
                        blueView.add(x);
                        blueView.add(y);
                    }
                }
        }
        
        else if (teamName == "red" && redSpy.cameraPlaced) {
            
            for (int x = redSpy.camPosX - 1; x <= redSpy.camPosX + 1; x++)
                for (int y = redSpy.camPosY - 1; y <= redSpy.camPosY + 1; y++) {
                    
                    doNotAddSquare = false;
                    
                    //check if square is already there
                    for (int a = 0; a < redView.size(); a += 2) {

                        if (x == (int)redView.get(a) && y == (int)redView.get(a + 1))
                            doNotAddSquare = true;
                    }

                    if (doNotAddSquare == false) {
                        redView.add(x);
                        redView.add(y);
                    }
                }
        }
    }
    
    public static void startOfTurn() {
            
        round[2]++;
        if (round[2] >= 3) {

            round[1]++;
            round[2] = 1;
        }
        if (round[1] >= 4) {

            round[0]++;
            round[1] = 1;

            //start of new round stuff
            for (Unit unit : units)
                unit.resetActivated();
        }

        if (round[2] == 1)
            playerTurn = "blue";
        else
            playerTurn = "red";

        GameDisplay.changePlayerTurn();

        //update all units and make sure they are deactivated
        for (int i = 0; i < units.size(); i++) {

            units.get(i).active = false;
            units.get(i).update(round);
        }

        //set all units' movement speed to 1 so GameDisplay doesn't say that they are finished
        for (int i = 0; i < aliveUnits.size(); i++)
            aliveUnits.get(i).movesLeft = 1;
    }
    
    public static void endOfTurn() {
        
        //if at start of round
        if (round[1] == 3 && round[2] == 2) {
            
            //control point scoring
            
            cpBlueControlled = false;
            cpRedControlled = false;

            for (int x = Board.x1; x < Board.sizeX; x++)
                for (int y = Board.y1; y < Board.sizeY; y++) {

                    if (Board.checkSquare(x, y) == 3 && Board.checkSquareUnit(x, y) instanceof Unit) {

                        if (Board.checkSquareUnit(x, y).teamName == "blue" && Board.checkSquareUnit(x, y).invis == false)
                            cpBlueControlled = true;
                        else if (Board.checkSquareUnit(x, y).invis == false)
                            cpRedControlled = true;
                    }
                }

            if (cpBlueControlled && !cpRedControlled) {
                
                blueScore++;
                GameDisplay.consoleAddMessage("Blue scored 1 point", "both");
            }

            else if (cpRedControlled && !cpBlueControlled) {
                
                redScore++;
                GameDisplay.consoleAddMessage("Red scored 1 point", "both");
            }
            
            //health pads
            for (int x = 1; x <= board.sizeX; x++)
                for (int y = 1; y <= board.sizeY; y++)
                    if (board.checkSquare(x, y) == 2 && board.checkSquareUnit(x, y) != null)
                        board.checkSquareUnit(x, y).heal(1, false);
        }
    }
    
    public static void activateUnitsPhase() {
        
        getActiveUnits = Inputs.getActiveUnits();
        
        if (!Replay.skipToPhase) {
        
            activeUnits.clear();

            for (int i = 0; i < getActiveUnits.size(); i++) {

                activeUnits.add(getActiveUnits.get(i));
            }

            //set all active units to full movement speed and activate them
            for (int i = 0; i < activeUnits.size(); i++) {

                activeUnits.get(i).movesLeft = activeUnits.get(i).movement;
                activeUnits.get(i).attacksLeft = 1;
                activeUnits.get(i).changeDirectionsLeft = 1;
                activeUnits.get(i).active = true;
                activeUnits.get(i).activated = true;

                if (activeUnits.get(i).unitClass == "Healer")
                    activeUnits.get(i).freeAttacksLeft = 0;
            }

            //set all non-active units' movement to 1
            for (int i = 0; i < aliveUnits.size(); i++)
                if (!(activeUnits.contains(aliveUnits.get(i))) && aliveUnits.get(i).teamName == playerTurn) {

                    aliveUnits.get(i).movesLeft = 1;
                    aliveUnits.get(i).attacksLeft = 0;
                    aliveUnits.get(i).changeDirectionsLeft = 0;
                    aliveUnits.get(i).active = false;

                    //set non-active healers' freeAttacksLeft to 1
                    if (aliveUnits.get(i).unitClass == "Healer")
                        aliveUnits.get(i).freeAttacksLeft = 1;
                }
        }
    }
    
    public static void changeUnitList(Unit unit, String newList) {
        
        //dying
        if (newList == "dead") {
            
            aliveUnits.remove(unit);
            deadUnits.add(unit);
        }
        
        //respawning
        else {
            
            deadUnits.remove(unit);
            aliveUnits.add(unit);
        }
    }
    
    public static void rand() {
        
//        for (int i = 0; i < units.size(); i++) {
//            
//            units.get(i).FOV = 90;
//            units.get(i).direction = 225;
//        }
//        blueSpy.direction = 225;
//        blueSpy.posX = 3;
//        blueSpy.posY = 3;
//        updatePlayerView("blue");
//        for (int a = 0; a < blueView.size(); a = a + 2) {
//            
//            System.out.println(blueView.get(a) + ", " + blueView.get(a + 1));
//        }
        //System.out.println(Physics.checkRange(1, 5, 1, 1, 360, 0, "blue", true));
        //System.out.println(Physics.checkRange(1, 1, 3, 3, 0, 45, "blue", false));
        //System.out.println(Physics.checkPath(3, 1, 2, 3, 3, "blue"));
//        System.out.println("Archer: " + blueArcher.movesLeft + ", " + blueArcher.activated);
//        System.out.println("Spy: " + blueSpy.movesLeft + ", " + blueSpy.activated);
//        System.out.println("TNTGuy: " + blueTNTGuy.movesLeft + ", " + blueTNTGuy.activated);
//        nextTurn();
//        System.out.println("Archer: " + blueArcher.movesLeft + ", " + blueArcher.activated);
//        System.out.println("Spy: " + blueSpy.movesLeft + ", " + blueSpy.activated);
//        System.out.println("TNTGuy: " + blueTNTGuy.movesLeft + ", " + blueTNTGuy.activated);
        //blueSpy.update(round);
    }
}
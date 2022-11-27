

package Inputs;

import Engine.Main;
import Game.Game;
import Game.Replay;
import Game.ReplayOld;
import GameObject.Unit;
import java.util.ArrayList;


public class Inputs {
    
    public static ArrayList<Unit> activeUnits = new ArrayList<Unit>();
    public static boolean unitAlreadyActive = false;
    public static Unit selectedUnit = null;
    public static int[] selectedSquare = new int[2];
    public static String action;
    public static int attackNumber = 1;
    public static String phase = "start of turn";
    public static boolean confirm = false;
    public static boolean endPhase = false;
    public static boolean activeUnitAlive;
    public static boolean skipActivateUnits;
    public static boolean skipChangeDirection;
    public static boolean skipMoveAndAttack;
    public static String recordString;
    
    public static ArrayList<Unit> getActiveUnits() {
        
        activeUnits.clear();
        
        phase = "activate units";
        selectedUnit = null;
        
        if (!Replay.skipNextRecord)
            Replay.record();
        Replay.skipNextRecord = false;
        
        while (confirm == false && endPhase == false && !skipActivateUnits && !Replay.skipToPhase) {
            
            Main.display.getInput();
        }
        
        if (endPhase == true)
            activeUnits.clear();
        
        recordString = "activate units ";
        
        for (int i = 0; i < activeUnits.size(); i++)
            recordString += activeUnits.get(i).unitClass + " " + activeUnits.get(i).teamName + " ";
        
        //ReplayOld.record(recordString);
        
        confirm = false;
        endPhase = false;
        
        selectedSquare[0] = 0;
        selectedSquare[1] = 0;
        
        action = null;
        
        return activeUnits;
    }
    
    public static void changeDirectionPhase() {
        
        phase = "change direction";
        
        if (!Replay.skipNextRecord)
            Replay.record();
        Replay.skipNextRecord = false;
        
        while (endPhase == false && !skipChangeDirection && !Replay.skipToPhase) {
            
            Main.display.getInput();
            
            if (confirm == true && selectedUnit != null && action == "change direction" && selectedSquare[0] != 0 && selectedSquare[1] != 0) {
                
                selectedUnit.changeDirection(selectedSquare[0], selectedSquare[1]);
                Game.updatePlayerView(Game.playerTurn);
                confirm = false;
                
                //ReplayOld.record("change direction " + selectedUnit.unitClass + " " + selectedUnit.teamName + " " + selectedSquare[0] + " " + selectedSquare[1] + " ");
                if (!Replay.skipNextRecord)
                    Replay.record();
                Replay.skipNextRecord = false;
            }
            
            else if (confirm == true)
                confirm = false;
        }
        
        skipChangeDirection = false;
        
        confirm = false;
        endPhase = false;
        
        selectedSquare[0] = 0;
        selectedSquare[1] = 0;
        
        action = null;
        
        selectedUnit = null;
    }
    
    public static void moveAndAttackPhase() {
        
        phase = "move and attack";
        
        if (!Replay.skipNextRecord)
            Replay.record();
        Replay.skipNextRecord = false;
        
        while (endPhase == false && !skipMoveAndAttack && !Replay.skipToPhase) {
            
            Main.display.getInput();
            
            //movement
            if (confirm == true && selectedUnit != null && action == "move" && selectedSquare[0] != 0 && selectedSquare[1] != 0) {
                
                selectedUnit.move(selectedSquare[0], selectedSquare[1], true);
                confirm = false;
                Game.updatePlayerView(Game.playerTurn);
                
                //ReplayOld.record("move " + selectedUnit.unitClass + " " + selectedUnit.teamName + " " + selectedSquare[0] + " " + selectedSquare[1] + " ");
                if (!Replay.skipNextRecord)
                    Replay.record();
                Replay.skipNextRecord = false;
            }
            
            //attack
            if (confirm == true && selectedUnit != null && action == "attack" && selectedSquare[0] != 0 && selectedSquare[1] != 0) {
                
                if (attackNumber == 0)
                    selectedUnit.freeAttack(selectedSquare[0], selectedSquare[1]);
                else if (attackNumber == 1)
                    selectedUnit.attack1(selectedSquare[0], selectedSquare[1]);
                else if (attackNumber == 2)
                    selectedUnit.attack2(selectedSquare[0], selectedSquare[1]);
                else if (attackNumber == 3)
                    selectedUnit.attack3(selectedSquare[0], selectedSquare[1]);
                confirm = false;
                Game.updatePlayerView(Game.playerTurn);
                
                //ReplayOld.record("attack " + selectedUnit.unitClass + " " + selectedUnit.teamName + " " + attackNumber + " " + selectedSquare[0] + " " + selectedSquare[1] + " ");
                if (!Replay.skipNextRecord)
                    Replay.record();
                Replay.skipNextRecord = false;
            }
            
            //change direction
            if (confirm == true && selectedUnit != null && action == "change direction" && selectedSquare[0] != 0 && selectedSquare[1] != 0) {
                
                selectedUnit.changeDirection(selectedSquare[0], selectedSquare[1]);
                Game.updatePlayerView(Game.playerTurn);
                confirm = false;
                
                //ReplayOld.record("change direction " + selectedUnit.unitClass + " " + selectedUnit.teamName + " " + selectedSquare[0] + " " + selectedSquare[1] + " ");
                if (!Replay.skipNextRecord)
                    Replay.record();
                Replay.skipNextRecord = false;
            }
            
            else if (confirm == true)
                confirm = false;
        }
        
        skipMoveAndAttack = false;
        
        confirm = false;
        endPhase = false;
        
        selectedSquare[0] = 0;
        selectedSquare[1] = 0;
        
        action = null;
        
        selectedUnit = null;
    }
    
    public static void selectUnit(String unitClass, String teamName) {
        
        selectedUnit = null;
        
        if (phase == "change direction" || phase == "move and attack")
            for (int i = 0; i < Game.aliveUnits.size(); i++) {
                
                if (Game.aliveUnits.get(i).unitClass.equals(unitClass) && Game.aliveUnits.get(i).teamName.equals(teamName)) {
                    
                    selectedUnit = Game.aliveUnits.get(i);
                }
            }
        
        if (phase == "activate units")
            activateUnit(unitClass, teamName);
        
        selectedSquare[0] = 0;
        selectedSquare[1] = 0;
        
        //Replay.record("select unit");
        //Replay.record(unitClass);
        //Replay.record(teamName);
    }
    
    public static void selectSquare(int x, int y) {
        
        selectedSquare[0] = x;
        selectedSquare[1] = y;
        
        //Replay.record("select square");
        //Replay.record(x + "");
        //Replay.record(y + "");
    }
    
    public static void activateUnit(String unitClass, String teamName) {
        
        unitAlreadyActive = false;
        
        activeUnitAlive = false;
        
        //make sure it is alive
        for (int i = 0; i < Game.aliveUnits.size(); i++)
            if (Game.aliveUnits.get(i).unitClass.equals(unitClass) && Game.aliveUnits.get(i).teamName.equals(teamName))
                activeUnitAlive = true;
        
        if (activeUnitAlive == true) {
            
            //check if unit is already in activeUnits
            for (int i = 0; i < activeUnits.size(); i++)
                if (activeUnits.get(i).unitClass.equals(unitClass) && activeUnits.get(i).teamName.equals(teamName)) {

                    activeUnits.remove(i);
                    unitAlreadyActive = true;
                    break;
                }
            
            if (unitAlreadyActive == false)
                for (int i = 0; i < Game.aliveUnits.size(); i++) {

                    if (Game.aliveUnits.get(i).unitClass.equals(unitClass) && Game.aliveUnits.get(i).teamName.equals(teamName) && Game.aliveUnits.get(i).activated == false) {

                        if (activeUnits.size() >= 3)
                            activeUnits.remove(0);

                        activeUnits.add(Game.aliveUnits.get(i));
                        break;
                    }
                }
        }
    }
    
    public static void confirm() {
        
        confirm = true;
        
        //Replay.record("confirm");
    }
    
    public static void endPhase() {
        
        endPhase = true;
        
        //ReplayOld.record("end phase");
    }
    
    public static int getSelectedUnit() {
        
        if (selectedUnit == null)
            return 0;
        if (selectedUnit.unitClass == "Archer")
            return 1;
        if (selectedUnit.unitClass == "Builder")
            return 2;
        if (selectedUnit.unitClass == "Healer")
            return 3;
        if (selectedUnit.unitClass == "Knight")
            return 4;
        if (selectedUnit.unitClass == "Mage")
            return 5;
        if (selectedUnit.unitClass == "Scout")
            return 6;
        if (selectedUnit.unitClass == "Spy")
            return 7;
        if (selectedUnit.unitClass == "TNTGuy")
            return 8;
        return 0;
    }
    
    public static int getAttackNumber() {
        
        return attackNumber;
    }
    
    public static String getAttackName() {
        
        if (selectedUnit == null)
            return "Attack " + attackNumber;
        
        if (attackNumber == 0)
            return selectedUnit.attack0Name;
        if (attackNumber == 1)
            return selectedUnit.attack1Name;
        if (attackNumber == 2)
            return selectedUnit.attack2Name;
        if (attackNumber == 3)
            return selectedUnit.attack3Name;
        
        return null;
    }
    
    public static void increaseAttackNumber() {
        
        attackNumber++;
        if (attackNumber >= 4)
            attackNumber = 0;
        
        //Replay.record("increase attack number");
    }
    
    public static void move() {
        
        action = "move";
        
        //Replay.record("move");
    }
    
    public static void attack() {
        
        action = "attack";
        
        //Replay.record("attack");
    }
    
    public static void changeDirection() {
        
        action = "change direction";
        
        //Replay.record("change direction");
    }
}

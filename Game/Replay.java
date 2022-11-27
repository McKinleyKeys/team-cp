

package Game;

import Engine.GameDisplay;
import Inputs.Inputs;
import java.util.ArrayList;
import java.io.*;


public class Replay {
    
    public static String fileName = "Save Replay Second Game.txt";
    public static ArrayList<ArrayList> replay = new ArrayList<ArrayList>();
    public static ArrayList<ArrayList> replayData = new ArrayList<ArrayList>();
    public static ArrayList loadData = new ArrayList();
    public static int infoSize = 578;
    public static String var;
    public static boolean replayOn = true;
    public static String line;
    public static int currentMove;
    public static String scanString;
    public static int scanStringIndex;
    public static String InputsNewPhase;
    public static boolean skipTurn = false;
    public static boolean skipToPhase;
    public static boolean skipNextRecord;
    
    public static String blueHealBotHealLog0;
    public static String blueHealBotHealLog1;
    public static String blueHealBotHealLog2;
    public static String blueHealerHealLog0;
    public static String blueHealerHealLog1;
    public static String blueHealerHealLog2;
    public static String redHealBotHealLog0;
    public static String redHealBotHealLog1;
    public static String redHealBotHealLog2;
    public static String redHealerHealLog0;
    public static String redHealerHealLog1;
    public static String redHealerHealLog2;
    
    public static void saveReplay() {
        
        try {
            
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for (int i = 0; i < replayData.size(); i++) {
                
                for (int a = 0; a < replayData.get(i).size(); a++) {
                    
                    if (replayData.get(i).get(a) != null)
                        bufferedWriter.write(replayData.get(i).get(a).toString());
                    else
                        bufferedWriter.write("null");
                    bufferedWriter.newLine();
                }
            }
            
            bufferedWriter.close();
        }
        
        catch (IOException ex) {
            
            ex.printStackTrace();
        }
        
        System.out.println("Replay has been saved.");
    }
    
    public static void record() {
        
        if (currentMove < replayData.size() - 1) {
            
            for (int i = currentMove + 1; i < replayData.size(); i++)
                replayData.remove(i);
        }
        
        if (true) {
        
            ArrayList data = new ArrayList();

            data.add(Inputs.phase + "");

            for (int i = 0; i < Game.units.size(); i++) {

                data.add(Game.units.get(i).posX + "");
                data.add(Game.units.get(i).posY + "");
                data.add(Game.units.get(i).health + "");
                data.add(Game.units.get(i).invuln + "");
                data.add(Game.units.get(i).invis + "");
                data.add(Game.units.get(i).freeAttacksLeft + "");
                data.add(Game.units.get(i).attacksLeft + "");
                data.add(Game.units.get(i).movesLeft + "");
                data.add(Game.units.get(i).active + "");
                data.add(Game.units.get(i).activated + "");
                data.add(Game.units.get(i).alive + "");
                data.add(Game.units.get(i).respawnTime + "");
                data.add(Game.units.get(i).respawnDate[0] + "");
                data.add(Game.units.get(i).respawnDate[1] + "");
                data.add(Game.units.get(i).respawnDate[2] + "");
                data.add(Game.units.get(i).poisonTime + "");
                data.add(Game.units.get(i).poisonDate[0] + "");
                data.add(Game.units.get(i).poisonDate[1] + "");
                data.add(Game.units.get(i).poisonDate[2] + "");
                data.add(Game.units.get(i).direction + "");
                data.add(Game.units.get(i).changeDirectionsLeft + "");
            }

            data.add(Game.blueArcher.bowCharge + "");

            data.add(Game.blueBuilder.healBotCooldown + "");
            data.add(Game.blueBuilder.healBotCooldownTime + "");
            data.add(Game.blueBuilder.healBotCooldownDate[0] + "");
            data.add(Game.blueBuilder.healBotCooldownDate[1] + "");
            data.add(Game.blueBuilder.healBotCooldownDate[2] + "");
            data.add(Game.blueBuilder.statueCooldown + "");
            data.add(Game.blueBuilder.statueCooldownTime + "");
            data.add(Game.blueBuilder.statueCooldownDate[0] + "");
            data.add(Game.blueBuilder.statueCooldownDate[1] + "");
            data.add(Game.blueBuilder.statueCooldownDate[2] + "");

            data.add(Game.blueHealBot.built + "");
            data.add(Game.blueHealBot.buildDate[0] + "");
            data.add(Game.blueHealBot.buildDate[1] + "");
            data.add(Game.blueHealBot.buildDate[2] + "");
            if (Game.blueHealBot.healLog.get(0) != null)
                data.add(Game.blueHealBot.healLog.get(0).teamClass + "");
            else
                data.add(null + "");
            if (Game.blueHealBot.healLog.get(1) != null)
                data.add(Game.blueHealBot.healLog.get(1).teamClass + "");
            else
                data.add(null + "");
            if (Game.blueHealBot.healLog.get(2) != null)
                data.add(Game.blueHealBot.healLog.get(2).teamClass + "");
            else
                data.add(null + "");
            data.add(Game.blueHealBot.maxHealth + "");

            data.add(Game.blueStatue.built + "");
            data.add(Game.blueStatue.buildDate[0] + "");
            data.add(Game.blueStatue.buildDate[1] + "");
            data.add(Game.blueStatue.buildDate[2] + "");
            data.add(Game.blueStatue.attackCharge + "");
            data.add(Game.blueStatue.maxHealth + "");

            data.add(Game.blueHealer.mana + "");
            data.add(Game.blueHealer.superCharge + "");
            data.add(Game.blueHealer.superChargeTime + "");
            data.add(Game.blueHealer.superChargeEndDate[0] + "");
            data.add(Game.blueHealer.superChargeEndDate[1] + "");
            data.add(Game.blueHealer.superChargeEndDate[2] + "");
            data.add(Game.blueHealer.superChargeHealedThisTurn + "");
            if (Game.blueHealer.healLog.get(0) != null)
                data.add(Game.blueHealer.healLog.get(0).teamClass + "");
            else
                data.add(null + "");
            if (Game.blueHealer.healLog.get(1) != null)
                data.add(Game.blueHealer.healLog.get(1).teamClass + "");
            else
                data.add(null + "");
            if (Game.blueHealer.healLog.get(2) != null)
                data.add(Game.blueHealer.healLog.get(2).teamClass + "");
            else
                data.add(null + "");

            data.add(Game.blueKnight.chargeAttackCooldownTime + "");
            data.add(Game.blueKnight.chargeAttackCooldownDate[0] + "");
            data.add(Game.blueKnight.chargeAttackCooldownDate[1] + "");
            data.add(Game.blueKnight.chargeAttackCooldownDate[2] + "");

            data.add(Game.blueSpy.decloakDate[0] + "");
            data.add(Game.blueSpy.decloakDate[1] + "");
            data.add(Game.blueSpy.decloakDate[2] + "");
            data.add(Game.blueSpy.cloakDate[0] + "");
            data.add(Game.blueSpy.cloakDate[1] + "");
            data.add(Game.blueSpy.cloakDate[2] + "");
            data.add(Game.blueSpy.invisCanAttack + "");
            data.add(Game.blueSpy.camPosX + "");
            data.add(Game.blueSpy.camPosY + "");
            data.add(Game.blueSpy.cameraPlaced + "");

            data.add(Game.blueTNTGuy.tntPos[0] + "");
            data.add(Game.blueTNTGuy.tntPos[1] + "");
            data.add(Game.blueTNTGuy.tntPos[2] + "");
            data.add(Game.blueTNTGuy.tntPos[3] + "");
            data.add(Game.blueTNTGuy.tntPos[4] + "");
            data.add(Game.blueTNTGuy.tntPos[5] + "");
            data.add(Game.blueTNTGuy.tntPos[6] + "");
            data.add(Game.blueTNTGuy.tntPos[7] + "");
            data.add(Game.blueTNTGuy.tntPlaced[0] + "");
            data.add(Game.blueTNTGuy.tntPlaced[1] + "");
            data.add(Game.blueTNTGuy.tntPlaced[2] + "");
            data.add(Game.blueTNTGuy.tntPlaced[3] + "");
            data.add(Game.blueTNTGuy.tntPrimed[0] + "");
            data.add(Game.blueTNTGuy.tntPrimed[1] + "");
            data.add(Game.blueTNTGuy.tntPrimed[2] + "");
            data.add(Game.blueTNTGuy.tntPrimed[3] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate1[0] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate1[1] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate1[2] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate2[0] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate2[1] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate2[2] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate3[0] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate3[1] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate3[2] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate4[0] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate4[1] + "");
            data.add(Game.blueTNTGuy.tntPrimeDate4[2] + "");



            data.add(Game.redArcher.bowCharge + "");

            data.add(Game.redBuilder.healBotCooldown + "");
            data.add(Game.redBuilder.healBotCooldownTime + "");
            data.add(Game.redBuilder.healBotCooldownDate[0] + "");
            data.add(Game.redBuilder.healBotCooldownDate[1] + "");
            data.add(Game.redBuilder.healBotCooldownDate[2] + "");
            data.add(Game.redBuilder.statueCooldown + "");
            data.add(Game.redBuilder.statueCooldownTime + "");
            data.add(Game.redBuilder.statueCooldownDate[0] + "");
            data.add(Game.redBuilder.statueCooldownDate[1] + "");
            data.add(Game.redBuilder.statueCooldownDate[2] + "");

            data.add(Game.redHealBot.built + "");
            data.add(Game.redHealBot.buildDate[0] + "");
            data.add(Game.redHealBot.buildDate[1] + "");
            data.add(Game.redHealBot.buildDate[2] + "");
            if (Game.redHealBot.healLog.get(0) != null)
                data.add(Game.redHealBot.healLog.get(0).teamClass + "");
            else
                data.add(null + "");
            if (Game.redHealBot.healLog.get(1) != null)
                data.add(Game.redHealBot.healLog.get(1).teamClass + "");
            else
                data.add(null + "");
            if (Game.redHealBot.healLog.get(2) != null)
                data.add(Game.redHealBot.healLog.get(2).teamClass + "");
            else
                data.add(null + "");
            data.add(Game.redHealBot.maxHealth + "");

            data.add(Game.redStatue.built + "");
            data.add(Game.redStatue.buildDate[0] + "");
            data.add(Game.redStatue.buildDate[1] + "");
            data.add(Game.redStatue.buildDate[2] + "");
            data.add(Game.redStatue.attackCharge + "");
            data.add(Game.redStatue.maxHealth + "");

            data.add(Game.redHealer.mana + "");
            data.add(Game.redHealer.superCharge + "");
            data.add(Game.redHealer.superChargeTime + "");
            data.add(Game.redHealer.superChargeEndDate[0] + "");
            data.add(Game.redHealer.superChargeEndDate[1] + "");
            data.add(Game.redHealer.superChargeEndDate[2] + "");
            data.add(Game.redHealer.superChargeHealedThisTurn + "");
            if (Game.redHealer.healLog.get(0) != null)
                data.add(Game.redHealer.healLog.get(0).teamClass + "");
            else
                data.add(null + "");
            if (Game.redHealer.healLog.get(1) != null)
                data.add(Game.redHealer.healLog.get(1).teamClass + "");
            else
                data.add(null + "");
            if (Game.redHealer.healLog.get(2) != null)
                data.add(Game.redHealer.healLog.get(2).teamClass + "");
            else
                data.add(null + "");

            data.add(Game.redKnight.chargeAttackCooldownTime + "");
            data.add(Game.redKnight.chargeAttackCooldownDate[0] + "");
            data.add(Game.redKnight.chargeAttackCooldownDate[1] + "");
            data.add(Game.redKnight.chargeAttackCooldownDate[2] + "");

            data.add(Game.redSpy.decloakDate[0] + "");
            data.add(Game.redSpy.decloakDate[1] + "");
            data.add(Game.redSpy.decloakDate[2] + "");
            data.add(Game.redSpy.cloakDate[0] + "");
            data.add(Game.redSpy.cloakDate[1] + "");
            data.add(Game.redSpy.cloakDate[2] + "");
            data.add(Game.redSpy.invisCanAttack + "");
            data.add(Game.redSpy.camPosX + "");
            data.add(Game.redSpy.camPosY + "");
            data.add(Game.redSpy.cameraPlaced + "");

            data.add(Game.redTNTGuy.tntPos[0] + "");
            data.add(Game.redTNTGuy.tntPos[1] + "");
            data.add(Game.redTNTGuy.tntPos[2] + "");
            data.add(Game.redTNTGuy.tntPos[3] + "");
            data.add(Game.redTNTGuy.tntPos[4] + "");
            data.add(Game.redTNTGuy.tntPos[5] + "");
            data.add(Game.redTNTGuy.tntPos[6] + "");
            data.add(Game.redTNTGuy.tntPos[7] + "");
            data.add(Game.redTNTGuy.tntPlaced[0] + "");
            data.add(Game.redTNTGuy.tntPlaced[1] + "");
            data.add(Game.redTNTGuy.tntPlaced[2] + "");
            data.add(Game.redTNTGuy.tntPlaced[3] + "");
            data.add(Game.redTNTGuy.tntPrimed[0] + "");
            data.add(Game.redTNTGuy.tntPrimed[1] + "");
            data.add(Game.redTNTGuy.tntPrimed[2] + "");
            data.add(Game.redTNTGuy.tntPrimed[3] + "");
            data.add(Game.redTNTGuy.tntPrimeDate1[0] + "");
            data.add(Game.redTNTGuy.tntPrimeDate1[1] + "");
            data.add(Game.redTNTGuy.tntPrimeDate1[2] + "");
            data.add(Game.redTNTGuy.tntPrimeDate2[0] + "");
            data.add(Game.redTNTGuy.tntPrimeDate2[1] + "");
            data.add(Game.redTNTGuy.tntPrimeDate2[2] + "");
            data.add(Game.redTNTGuy.tntPrimeDate3[0] + "");
            data.add(Game.redTNTGuy.tntPrimeDate3[1] + "");
            data.add(Game.redTNTGuy.tntPrimeDate3[2] + "");
            data.add(Game.redTNTGuy.tntPrimeDate4[0] + "");
            data.add(Game.redTNTGuy.tntPrimeDate4[1] + "");
            data.add(Game.redTNTGuy.tntPrimeDate4[2] + "");
            
            for (int i = 0; i < GameDisplay.consoleMessagesBlue.size(); i++) {
                
                data.add(GameDisplay.consoleMessagesBlue.get(i));
            }
            
            for (int i = 0; i < GameDisplay.consoleMessagesRed.size(); i++) {
                
                data.add(GameDisplay.consoleMessagesRed.get(i));
            }

            data.add(Game.blueScore + "");
            data.add(Game.redScore + "");

            data.add(Game.round[0] + "");
            data.add(Game.round[1] + "");
            data.add(Game.round[2] + "");



            data.add("end");
            
            if (replayData.size() < 1) {
                
                replayData.add(data);

                currentMove = replayData.size() - 1;
            }
            
            //if position is the same as the one before it
            else if (!data.equals(replayData.get(replayData.size() - 1))) {

                replayData.add(data);

                currentMove = replayData.size() - 1;
            }
        }
    }
    
    public static void loadReplay() {
        
        replayData.clear();
        loadData.clear();
        
        try {
            
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while ((line = bufferedReader.readLine()) != null) {
                
                loadData.add(line);
            }
            
            for (int i = 0; i < loadData.size();) {
                
                ArrayList position = new ArrayList();
                
                for (int a = 0; i + a < loadData.size(); a++) {
                    
                    position.add(loadData.get(i + a));
                    
                    if (loadData.get(i + a).equals("end")) {
                        
                        i = i + a + 1;
                        break;
                    }
                }
                
                replayData.add(position);
            }
            
            bufferedReader.close();
        }
        
        catch (IOException ex) {
            
            ex.printStackTrace();
        }
        
        replayOn = true;
        
        currentMove = 0;
        
        loadPosition(replayData.get(0));
        
        System.out.println("Replay has been loaded.");
    }
    
    public static void nextMove() {
        
        if (currentMove < replayData.size() - 1) {
            
            currentMove++;
            loadPosition(replayData.get(currentMove));
        }
        
        else
            System.out.println("Replay has ended!");
    }
    
    public static void lastMove() {
        
        if (currentMove > 0) {
            
            currentMove--;
            loadPosition(replayData.get(currentMove));
        }
        
        else
            System.out.println("You cannot rewind any further!");
    }
    
    public static void firstMove() {
        
        currentMove = 0;
        loadPosition(replayData.get(currentMove));
    }
    
    public static void finalMove() {
        
        currentMove = replayData.size() - 1;
        loadPosition(replayData.get(currentMove));
    }
    
    public static void loadPosition(ArrayList position) {
        
        skipTurn = false;
        
        InputsNewPhase = (String)position.get(0);
            
            if (Inputs.phase == "activate units" && InputsNewPhase.equals("change direction")) {
                
                Inputs.skipActivateUnits = true;
            }
            
            else if (Inputs.phase == "activate units" && InputsNewPhase.equals("move and attack")) {
                
                Inputs.skipActivateUnits = true;
                Inputs.skipChangeDirection = true;
            }
            
            else if (Inputs.phase == "change direction" && InputsNewPhase.equals("move and attack")) {
                
                Inputs.skipChangeDirection = true;
            }
            
            else if (Inputs.phase == "change direction" && InputsNewPhase.equals("activate units")) {
                
                Inputs.skipChangeDirection = true;
                Inputs.skipMoveAndAttack = true;
                
                skipTurn = true;
            }
            
            else if (Inputs.phase == "move and attack" && InputsNewPhase.equals("activate units")) {
                
                Inputs.skipMoveAndAttack = true;
                
                skipTurn = true;
            }
            
            else if (Inputs.phase == "move and attack" && InputsNewPhase.equals("change direction")) {
                
                Inputs.skipMoveAndAttack = true;
                Inputs.skipActivateUnits = true;
                
                skipTurn = true;
            }
            
            
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                Game.units.get(i).posX = Integer.parseInt((String)position.get(1 + i * 21));
                Game.units.get(i).posY = Integer.parseInt((String)position.get(2 + i * 21));
                Game.units.get(i).health = Integer.parseInt((String)position.get(3 + i * 21));
                Game.units.get(i).invuln = Boolean.parseBoolean((String)position.get(4 + i * 21));
                Game.units.get(i).invis = Boolean.parseBoolean((String)position.get(5 + i * 21));
                Game.units.get(i).freeAttacksLeft = Integer.parseInt((String)position.get(6 + i * 21));
                Game.units.get(i).attacksLeft = Integer.parseInt((String)position.get(7 + i * 21));
                Game.units.get(i).movesLeft = Integer.parseInt((String)position.get(8 + i * 21));
                Game.units.get(i).active = Boolean.parseBoolean((String)position.get(9 + i * 21));
                Game.units.get(i).activated = Boolean.parseBoolean((String)position.get(10 + i * 21));
                Game.units.get(i).alive = Boolean.parseBoolean((String)position.get(11 + i * 21));
                Game.units.get(i).respawnTime = Integer.parseInt((String)position.get(12 + i * 21));
                Game.units.get(i).respawnDate[0] = Integer.parseInt((String)position.get(13 + i * 21));
                Game.units.get(i).respawnDate[1] = Integer.parseInt((String)position.get(14 + i * 21));
                Game.units.get(i).respawnDate[2] = Integer.parseInt((String)position.get(15 + i * 21));
                Game.units.get(i).poisonTime = Integer.parseInt((String)position.get(16 + i * 21));
                Game.units.get(i).poisonDate[0] = Integer.parseInt((String)position.get(17 + i * 21));
                Game.units.get(i).poisonDate[1] = Integer.parseInt((String)position.get(18 + i * 21));
                Game.units.get(i).poisonDate[2] = Integer.parseInt((String)position.get(19 + i * 21));
                Game.units.get(i).direction = Double.parseDouble((String)position.get(20 + i * 21));
                Game.units.get(i).changeDirectionsLeft = Integer.parseInt((String)position.get(21 + i * 21));
            }
            
            Game.blueArcher.bowCharge = Integer.parseInt((String)position.get(22 + (Game.units.size() - 1) * 21));
            
            Game.blueBuilder.healBotCooldown = Boolean.parseBoolean((String)position.get(23 + (Game.units.size() - 1) * 21));
            Game.blueBuilder.healBotCooldownTime = Integer.parseInt((String)position.get(24 + (Game.units.size() - 1) * 21));
            Game.blueBuilder.healBotCooldownDate[0] = Integer.parseInt((String)position.get(25 + (Game.units.size() - 1) * 21));
            Game.blueBuilder.healBotCooldownDate[1] = Integer.parseInt((String)position.get(26 + (Game.units.size() - 1) * 21));
            Game.blueBuilder.healBotCooldownDate[2] = Integer.parseInt((String)position.get(27 + (Game.units.size() - 1) * 21));
            Game.blueBuilder.statueCooldown = Boolean.parseBoolean((String)position.get(28 + (Game.units.size() - 1) * 21));
            Game.blueBuilder.statueCooldownTime = Integer.parseInt((String)position.get(29 + (Game.units.size() - 1) * 21));
            Game.blueBuilder.statueCooldownDate[0] = Integer.parseInt((String)position.get(30 + (Game.units.size() - 1) * 21));
            Game.blueBuilder.statueCooldownDate[1] = Integer.parseInt((String)position.get(31 + (Game.units.size() - 1) * 21));
            Game.blueBuilder.statueCooldownDate[2] = Integer.parseInt((String)position.get(32 + (Game.units.size() - 1) * 21));

            Game.blueHealBot.built = Boolean.parseBoolean((String)position.get(33 + (Game.units.size() - 1) * 21));
            Game.blueHealBot.buildDate[0] = Integer.parseInt((String)position.get(34 + (Game.units.size() - 1) * 21));
            Game.blueHealBot.buildDate[1] = Integer.parseInt((String)position.get(35 + (Game.units.size() - 1) * 21));
            Game.blueHealBot.buildDate[2] = Integer.parseInt((String)position.get(36 + (Game.units.size() - 1) * 21));
            blueHealBotHealLog0 = (String)position.get(37);
            blueHealBotHealLog1 = (String)position.get(38);
            blueHealBotHealLog2 = (String)position.get(39);
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealBotHealLog0)
                    Game.blueHealBot.healLog.set(0, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.blueHealBot.healLog.set(0, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealBotHealLog1)
                    Game.blueHealBot.healLog.set(1, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.blueHealBot.healLog.set(1, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealBotHealLog2)
                    Game.blueHealBot.healLog.set(2, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.blueHealBot.healLog.set(2, null);
            }
            
            Game.blueHealBot.maxHealth = Integer.parseInt((String)position.get(40 + (Game.units.size() - 1) * 21));

            Game.blueStatue.built = Boolean.parseBoolean((String)position.get(41 + (Game.units.size() - 1) * 21));
            Game.blueStatue.buildDate[0] = Integer.parseInt((String)position.get(42 + (Game.units.size() - 1) * 21));
            Game.blueStatue.buildDate[1] = Integer.parseInt((String)position.get(43 + (Game.units.size() - 1) * 21));
            Game.blueStatue.buildDate[2] = Integer.parseInt((String)position.get(44 + (Game.units.size() - 1) * 21));
            Game.blueStatue.attackCharge = Integer.parseInt((String)position.get(45 + (Game.units.size() - 1) * 21));
            Game.blueStatue.maxHealth = Integer.parseInt((String)position.get(46 + (Game.units.size() - 1) * 21));

            Game.blueHealer.mana = Integer.parseInt((String)position.get(47 + (Game.units.size() - 1) * 21));
            Game.blueHealer.superCharge = Boolean.parseBoolean((String)position.get(48 + (Game.units.size() - 1) * 21));
            Game.blueHealer.superChargeTime = Integer.parseInt((String)position.get(49 + (Game.units.size() - 1) * 21));
            Game.blueHealer.superChargeEndDate[0] = Integer.parseInt((String)position.get(50 + (Game.units.size() - 1) * 21));
            Game.blueHealer.superChargeEndDate[1] = Integer.parseInt((String)position.get(51 + (Game.units.size() - 1) * 21));
            Game.blueHealer.superChargeEndDate[2] = Integer.parseInt((String)position.get(52 + (Game.units.size() - 1) * 21));
            Game.blueHealer.superChargeHealedThisTurn = Boolean.parseBoolean((String)position.get(53 + (Game.units.size() - 1) * 21));
            blueHealerHealLog0 = (String)position.get(54);
            blueHealerHealLog1 = (String)position.get(55);
            blueHealerHealLog2 = (String)position.get(56);
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealerHealLog0)
                    Game.blueHealer.healLog.set(0, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.blueHealer.healLog.set(0, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealerHealLog1)
                    Game.blueHealer.healLog.set(1, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.blueHealer.healLog.set(1, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealerHealLog2)
                    Game.blueHealer.healLog.set(2, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.blueHealer.healLog.set(2, null);
            }

            Game.blueKnight.chargeAttackCooldownTime = Integer.parseInt((String)position.get(57 + (Game.units.size() - 1) * 21));
            Game.blueKnight.chargeAttackCooldownDate[0] = Integer.parseInt((String)position.get(58 + (Game.units.size() - 1) * 21));
            Game.blueKnight.chargeAttackCooldownDate[1] = Integer.parseInt((String)position.get(59 + (Game.units.size() - 1) * 21));
            Game.blueKnight.chargeAttackCooldownDate[2] = Integer.parseInt((String)position.get(60 + (Game.units.size() - 1) * 21));

            Game.blueSpy.decloakDate[0] = Integer.parseInt((String)position.get(61 + (Game.units.size() - 1) * 21));
            Game.blueSpy.decloakDate[1] = Integer.parseInt((String)position.get(62 + (Game.units.size() - 1) * 21));
            Game.blueSpy.decloakDate[2] = Integer.parseInt((String)position.get(63 + (Game.units.size() - 1) * 21));
            Game.blueSpy.cloakDate[0] = Integer.parseInt((String)position.get(64 + (Game.units.size() - 1) * 21));
            Game.blueSpy.cloakDate[1] = Integer.parseInt((String)position.get(65 + (Game.units.size() - 1) * 21));
            Game.blueSpy.cloakDate[2] = Integer.parseInt((String)position.get(66 + (Game.units.size() - 1) * 21));
            Game.blueSpy.invisCanAttack = Boolean.parseBoolean((String)position.get(67 + (Game.units.size() - 1) * 21));
            Game.blueSpy.camPosX = Integer.parseInt((String)position.get(68 + (Game.units.size() - 1) * 21));
            Game.blueSpy.camPosY = Integer.parseInt((String)position.get(69 + (Game.units.size() - 1) * 21));
            Game.blueSpy.cameraPlaced = Boolean.parseBoolean((String)position.get(70 + (Game.units.size() - 1) * 21));

            Game.blueTNTGuy.tntPos[0] = Integer.parseInt((String)position.get(71 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPos[1] = Integer.parseInt((String)position.get(72 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPos[2] = Integer.parseInt((String)position.get(73 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPos[3] = Integer.parseInt((String)position.get(74 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPos[4] = Integer.parseInt((String)position.get(75 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPos[5] = Integer.parseInt((String)position.get(76 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPos[6] = Integer.parseInt((String)position.get(77 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPos[7] = Integer.parseInt((String)position.get(78 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPlaced[0] = Boolean.parseBoolean((String)position.get(79 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPlaced[1] = Boolean.parseBoolean((String)position.get(80 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPlaced[2] = Boolean.parseBoolean((String)position.get(81 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPlaced[3] = Boolean.parseBoolean((String)position.get(82 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimed[0] = Boolean.parseBoolean((String)position.get(83 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimed[1] = Boolean.parseBoolean((String)position.get(84 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimed[2] = Boolean.parseBoolean((String)position.get(85 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimed[3] = Boolean.parseBoolean((String)position.get(86 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate1[0] = Integer.parseInt((String)position.get(87 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate1[1] = Integer.parseInt((String)position.get(88 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate1[2] = Integer.parseInt((String)position.get(89 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate2[0] = Integer.parseInt((String)position.get(90 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate2[1] = Integer.parseInt((String)position.get(91 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate2[2] = Integer.parseInt((String)position.get(92 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate3[0] = Integer.parseInt((String)position.get(93 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate3[1] = Integer.parseInt((String)position.get(94 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate3[2] = Integer.parseInt((String)position.get(95 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate4[0] = Integer.parseInt((String)position.get(96 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate4[1] = Integer.parseInt((String)position.get(97 + (Game.units.size() - 1) * 21));
            Game.blueTNTGuy.tntPrimeDate4[2] = Integer.parseInt((String)position.get(98 + (Game.units.size() - 1) * 21));


            

            Game.redArcher.bowCharge = Integer.parseInt((String)position.get(99 + (Game.units.size() - 1) * 21));

            Game.redBuilder.healBotCooldown = Boolean.parseBoolean((String)position.get(100 + (Game.units.size() - 1) * 21));
            Game.redBuilder.healBotCooldownTime = Integer.parseInt((String)position.get(101 + (Game.units.size() - 1) * 21));
            Game.redBuilder.healBotCooldownDate[0] = Integer.parseInt((String)position.get(102 + (Game.units.size() - 1) * 21));
            Game.redBuilder.healBotCooldownDate[1] = Integer.parseInt((String)position.get(103 + (Game.units.size() - 1) * 21));
            Game.redBuilder.healBotCooldownDate[2] = Integer.parseInt((String)position.get(104 + (Game.units.size() - 1) * 21));
            Game.redBuilder.statueCooldown = Boolean.parseBoolean((String)position.get(105 + (Game.units.size() - 1) * 21));
            Game.redBuilder.statueCooldownTime = Integer.parseInt((String)position.get(106 + (Game.units.size() - 1) * 21));
            Game.redBuilder.statueCooldownDate[0] = Integer.parseInt((String)position.get(107 + (Game.units.size() - 1) * 21));
            Game.redBuilder.statueCooldownDate[1] = Integer.parseInt((String)position.get(108 + (Game.units.size() - 1) * 21));
            Game.redBuilder.statueCooldownDate[2] = Integer.parseInt((String)position.get(109 + (Game.units.size() - 1) * 21));

            Game.redHealBot.built = Boolean.parseBoolean((String)position.get(110 + (Game.units.size() - 1) * 21));
            Game.redHealBot.buildDate[0] = Integer.parseInt((String)position.get(111 + (Game.units.size() - 1) * 21));
            Game.redHealBot.buildDate[1] = Integer.parseInt((String)position.get(112 + (Game.units.size() - 1) * 21));
            Game.redHealBot.buildDate[2] = Integer.parseInt((String)position.get(113 + (Game.units.size() - 1) * 21));
            redHealBotHealLog0 = (String)position.get(114);
            redHealBotHealLog1 = (String)position.get(115);
            redHealBotHealLog2 = (String)position.get(116);
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealBotHealLog0)
                    Game.redHealBot.healLog.set(0, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.redHealBot.healLog.set(0, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealBotHealLog1)
                    Game.redHealBot.healLog.set(1, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.redHealBot.healLog.set(1, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealBotHealLog2)
                    Game.redHealBot.healLog.set(2, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.redHealBot.healLog.set(2, null);
            }
            
            Game.redHealBot.maxHealth = Integer.parseInt((String)position.get(117 + (Game.units.size() - 1) * 21));

            Game.redStatue.built = Boolean.parseBoolean((String)position.get(118 + (Game.units.size() - 1) * 21));
            Game.redStatue.buildDate[0] = Integer.parseInt((String)position.get(119 + (Game.units.size() - 1) * 21));
            Game.redStatue.buildDate[1] = Integer.parseInt((String)position.get(120 + (Game.units.size() - 1) * 21));
            Game.redStatue.buildDate[2] = Integer.parseInt((String)position.get(121 + (Game.units.size() - 1) * 21));
            Game.redStatue.attackCharge = Integer.parseInt((String)position.get(122 + (Game.units.size() - 1) * 21));
            Game.redStatue.maxHealth = Integer.parseInt((String)position.get(123 + (Game.units.size() - 1) * 21));

            Game.redHealer.mana = Integer.parseInt((String)position.get(124 + (Game.units.size() - 1) * 21));
            Game.redHealer.superCharge = Boolean.parseBoolean((String)position.get(125 + (Game.units.size() - 1) * 21));
            Game.redHealer.superChargeTime = Integer.parseInt((String)position.get(126 + (Game.units.size() - 1) * 21));
            Game.redHealer.superChargeEndDate[0] = Integer.parseInt((String)position.get(127 + (Game.units.size() - 1) * 21));
            Game.redHealer.superChargeEndDate[1] = Integer.parseInt((String)position.get(128 + (Game.units.size() - 1) * 21));
            Game.redHealer.superChargeEndDate[2] = Integer.parseInt((String)position.get(129 + (Game.units.size() - 1) * 21));
            Game.redHealer.superChargeHealedThisTurn = Boolean.parseBoolean((String)position.get(130 + (Game.units.size() - 1) * 21));
            redHealerHealLog0 = (String)position.get(131);
            redHealerHealLog1 = (String)position.get(132);
            redHealerHealLog2 = (String)position.get(133);
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealerHealLog0)
                    Game.redHealer.healLog.set(0, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.redHealer.healLog.set(0, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealerHealLog1)
                    Game.redHealer.healLog.set(1, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.redHealer.healLog.set(1, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealerHealLog2)
                    Game.redHealer.healLog.set(2, Game.units.get(i + (Game.units.size() - 1) * 21));
                else
                    Game.redHealer.healLog.set(2, null);
            }

            Game.redKnight.chargeAttackCooldownTime = Integer.parseInt((String)position.get(134 + (Game.units.size() - 1) * 21));
            Game.redKnight.chargeAttackCooldownDate[0] = Integer.parseInt((String)position.get(135 + (Game.units.size() - 1) * 21));
            Game.redKnight.chargeAttackCooldownDate[1] = Integer.parseInt((String)position.get(136 + (Game.units.size() - 1) * 21));
            Game.redKnight.chargeAttackCooldownDate[2] = Integer.parseInt((String)position.get(137 + (Game.units.size() - 1) * 21));

            Game.redSpy.decloakDate[0] = Integer.parseInt((String)position.get(138 + (Game.units.size() - 1) * 21));
            Game.redSpy.decloakDate[1] = Integer.parseInt((String)position.get(139 + (Game.units.size() - 1) * 21));
            Game.redSpy.decloakDate[2] = Integer.parseInt((String)position.get(140 + (Game.units.size() - 1) * 21));
            Game.redSpy.cloakDate[0] = Integer.parseInt((String)position.get(141 + (Game.units.size() - 1) * 21));
            Game.redSpy.cloakDate[1] = Integer.parseInt((String)position.get(142 + (Game.units.size() - 1) * 21));
            Game.redSpy.cloakDate[2] = Integer.parseInt((String)position.get(143 + (Game.units.size() - 1) * 21));
            Game.redSpy.invisCanAttack = Boolean.parseBoolean((String)position.get(144 + (Game.units.size() - 1) * 21));
            Game.redSpy.camPosX = Integer.parseInt((String)position.get(145 + (Game.units.size() - 1) * 21));
            Game.redSpy.camPosY = Integer.parseInt((String)position.get(146 + (Game.units.size() - 1) * 21));
            Game.redSpy.cameraPlaced = Boolean.parseBoolean((String)position.get(147 + (Game.units.size() - 1) * 21));

            Game.redTNTGuy.tntPos[0] = Integer.parseInt((String)position.get(148 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPos[1] = Integer.parseInt((String)position.get(149 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPos[2] = Integer.parseInt((String)position.get(150 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPos[3] = Integer.parseInt((String)position.get(151 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPos[4] = Integer.parseInt((String)position.get(152 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPos[5] = Integer.parseInt((String)position.get(153 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPos[6] = Integer.parseInt((String)position.get(154 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPos[7] = Integer.parseInt((String)position.get(155 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPlaced[0] = Boolean.parseBoolean((String)position.get(156 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPlaced[1] = Boolean.parseBoolean((String)position.get(157 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPlaced[2] = Boolean.parseBoolean((String)position.get(158 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPlaced[3] = Boolean.parseBoolean((String)position.get(159 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimed[0] = Boolean.parseBoolean((String)position.get(160 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimed[1] = Boolean.parseBoolean((String)position.get(161 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimed[2] = Boolean.parseBoolean((String)position.get(162 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimed[3] = Boolean.parseBoolean((String)position.get(163 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate1[0] = Integer.parseInt((String)position.get(164 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate1[1] = Integer.parseInt((String)position.get(165 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate1[2] = Integer.parseInt((String)position.get(166 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate2[0] = Integer.parseInt((String)position.get(167 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate2[1] = Integer.parseInt((String)position.get(168 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate2[2] = Integer.parseInt((String)position.get(169 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate3[0] = Integer.parseInt((String)position.get(170 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate3[1] = Integer.parseInt((String)position.get(171 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate3[2] = Integer.parseInt((String)position.get(172 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate4[0] = Integer.parseInt((String)position.get(173 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate4[1] = Integer.parseInt((String)position.get(174 + (Game.units.size() - 1) * 21));
            Game.redTNTGuy.tntPrimeDate4[2] = Integer.parseInt((String)position.get(175 + (Game.units.size() - 1) * 21));
            
            for (int i = 0; i < GameDisplay.consoleMessagesBlue.size(); i++) {
                
                GameDisplay.consoleAddMessage(position.get(176 + (Game.units.size() - 1) * 21 + i).toString(), "blue");
            }
            
            for (int i = 0; i < GameDisplay.consoleMessagesRed.size(); i++) {
                
                GameDisplay.consoleAddMessage(position.get(176 + (Game.units.size() - 1) * 21 + GameDisplay.consoleMessagesBlue.size() + i).toString(), "red");
            }
            
            
            
            
            Game.blueScore = Integer.parseInt((String)position.get(176 + (Game.units.size() - 1) * 21 + GameDisplay.consoleMessagesBlue.size() + GameDisplay.consoleMessagesRed.size()));
            Game.redScore = Integer.parseInt((String)position.get(177 + (Game.units.size() - 1) * 21 + GameDisplay.consoleMessagesBlue.size() + GameDisplay.consoleMessagesRed.size()));
            
            Game.round[0] = Integer.parseInt((String)position.get(178 + (Game.units.size() - 1) * 21 + GameDisplay.consoleMessagesBlue.size() + GameDisplay.consoleMessagesRed.size()));
            Game.round[1] = Integer.parseInt((String)position.get(179 + (Game.units.size() - 1) * 21 + GameDisplay.consoleMessagesBlue.size() + GameDisplay.consoleMessagesRed.size()));
            Game.round[2] = Integer.parseInt((String)position.get(180 + (Game.units.size() - 1) * 21 + GameDisplay.consoleMessagesBlue.size() + GameDisplay.consoleMessagesRed.size()));
            
            if (Game.round[2] == 1 && Game.playerTurn == "red") {
                
                Game.playerTurn = "blue";
                GameDisplay.changePlayerTurn();
                
                skipTurn = true;
            }
            
            else if (Game.round[2] == 2 && Game.playerTurn == "blue") {
                
                Game.playerTurn = "red";
                GameDisplay.changePlayerTurn();
                
                skipTurn = true;
            }
            
            skipToPhase = true;
            
            skipNextRecord = true;
            
            Inputs.phase = InputsNewPhase;
            
            Inputs.skipActivateUnits = false;
            Inputs.skipChangeDirection = false;
            Inputs.skipMoveAndAttack = false;
            
            
            
            
            //update alive units
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).alive && !Game.aliveUnits.contains(Game.units.get(i)))
                    Game.changeUnitList(Game.units.get(i), "alive");
                
                else if (!Game.units.get(i).alive && !Game.deadUnits.contains(Game.units.get(i)))
                    Game.changeUnitList(Game.units.get(i), "dead");
            }
            
            Game.activeUnits.clear();
            Inputs.activeUnits.clear();
            
            //update active units
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).active && !Game.activeUnits.contains(Game.units.get(i)))
                    Game.activeUnits.add(Game.units.get(i));
                
                if (Game.units.get(i).active && !Inputs.activeUnits.contains(Game.units.get(i)))
                    Inputs.activeUnits.add(Game.units.get(i));
            }
    }
}



package Game;

import Inputs.Inputs;
import java.io.*;
import java.util.ArrayList;


public class Position {
    
    public static ArrayList data = new ArrayList();
    
    public static String fileName = "Save Position.txt";
    
    public static String InputsNewPhase;
    
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
    
    public static void savePosition() {
        
        data.clear();
        
        data.add(Inputs.phase);
        
        for (int i = 0; i < Game.units.size(); i++) {
            
            data.add(Game.units.get(i).posX);
            data.add(Game.units.get(i).posY);
            data.add(Game.units.get(i).health);
            data.add(Game.units.get(i).invuln);
            data.add(Game.units.get(i).invis);
            data.add(Game.units.get(i).freeAttacksLeft);
            data.add(Game.units.get(i).attacksLeft);
            data.add(Game.units.get(i).movesLeft);
            data.add(Game.units.get(i).active);
            data.add(Game.units.get(i).activated);
            data.add(Game.units.get(i).alive);
            data.add(Game.units.get(i).respawnTime);
            data.add(Game.units.get(i).respawnDate[0]);
            data.add(Game.units.get(i).respawnDate[1]);
            data.add(Game.units.get(i).respawnDate[2]);
            data.add(Game.units.get(i).poisonTime);
            data.add(Game.units.get(i).poisonDate[0]);
            data.add(Game.units.get(i).poisonDate[1]);
            data.add(Game.units.get(i).poisonDate[2]);
            data.add(Game.units.get(i).direction);
            data.add(Game.units.get(i).changeDirectionsLeft);
        }
        
        data.add(Game.blueArcher.bowCharge);
        
        data.add(Game.blueBuilder.healBotCooldown);
        data.add(Game.blueBuilder.healBotCooldownTime);
        data.add(Game.blueBuilder.healBotCooldownDate[0]);
        data.add(Game.blueBuilder.healBotCooldownDate[1]);
        data.add(Game.blueBuilder.healBotCooldownDate[2]);
        data.add(Game.blueBuilder.statueCooldown);
        data.add(Game.blueBuilder.statueCooldownTime);
        data.add(Game.blueBuilder.statueCooldownDate[0]);
        data.add(Game.blueBuilder.statueCooldownDate[1]);
        data.add(Game.blueBuilder.statueCooldownDate[2]);
        
        data.add(Game.blueHealBot.built);
        data.add(Game.blueHealBot.buildDate[0]);
        data.add(Game.blueHealBot.buildDate[1]);
        data.add(Game.blueHealBot.buildDate[2]);
        if (Game.blueHealBot.healLog.get(0) != null)
            data.add(Game.blueHealBot.healLog.get(0).teamClass);
        else
            data.add(null);
        if (Game.blueHealBot.healLog.get(1) != null)
            data.add(Game.blueHealBot.healLog.get(1).teamClass);
        else
            data.add(null);
        if (Game.blueHealBot.healLog.get(2) != null)
            data.add(Game.blueHealBot.healLog.get(2).teamClass);
        else
            data.add(null);
        data.add(Game.blueHealBot.maxHealth);
        
        data.add(Game.blueStatue.built);
        data.add(Game.blueStatue.buildDate[0]);
        data.add(Game.blueStatue.buildDate[1]);
        data.add(Game.blueStatue.buildDate[2]);
        data.add(Game.blueStatue.attackCharge);
        data.add(Game.blueStatue.maxHealth);
        
        data.add(Game.blueHealer.mana);
        data.add(Game.blueHealer.superCharge);
        data.add(Game.blueHealer.superChargeTime);
        data.add(Game.blueHealer.superChargeEndDate[0]);
        data.add(Game.blueHealer.superChargeEndDate[1]);
        data.add(Game.blueHealer.superChargeEndDate[2]);
        data.add(Game.blueHealer.superChargeHealedThisTurn);
        if (Game.blueHealer.healLog.get(0) != null)
            data.add(Game.blueHealer.healLog.get(0).teamClass);
        else
            data.add(null);
        if (Game.blueHealer.healLog.get(1) != null)
            data.add(Game.blueHealer.healLog.get(1).teamClass);
        else
            data.add(null);
        if (Game.blueHealer.healLog.get(2) != null)
            data.add(Game.blueHealer.healLog.get(2).teamClass);
        else
            data.add(null);
        
        data.add(Game.blueKnight.chargeAttackCooldownTime);
        data.add(Game.blueKnight.chargeAttackCooldownDate[0]);
        data.add(Game.blueKnight.chargeAttackCooldownDate[1]);
        data.add(Game.blueKnight.chargeAttackCooldownDate[2]);
        
        data.add(Game.blueSpy.decloakDate[0]);
        data.add(Game.blueSpy.decloakDate[1]);
        data.add(Game.blueSpy.decloakDate[2]);
        data.add(Game.blueSpy.cloakDate[0]);
        data.add(Game.blueSpy.cloakDate[1]);
        data.add(Game.blueSpy.cloakDate[2]);
        data.add(Game.blueSpy.invisCanAttack);
        data.add(Game.blueSpy.camPosX);
        data.add(Game.blueSpy.camPosY);
        data.add(Game.blueSpy.cameraPlaced);
        
        data.add(Game.blueTNTGuy.tntPos[0]);
        data.add(Game.blueTNTGuy.tntPos[1]);
        data.add(Game.blueTNTGuy.tntPos[2]);
        data.add(Game.blueTNTGuy.tntPos[3]);
        data.add(Game.blueTNTGuy.tntPos[4]);
        data.add(Game.blueTNTGuy.tntPos[5]);
        data.add(Game.blueTNTGuy.tntPos[6]);
        data.add(Game.blueTNTGuy.tntPos[7]);
        data.add(Game.blueTNTGuy.tntPlaced[0]);
        data.add(Game.blueTNTGuy.tntPlaced[1]);
        data.add(Game.blueTNTGuy.tntPlaced[2]);
        data.add(Game.blueTNTGuy.tntPlaced[3]);
        data.add(Game.blueTNTGuy.tntPrimed[0]);
        data.add(Game.blueTNTGuy.tntPrimed[1]);
        data.add(Game.blueTNTGuy.tntPrimed[2]);
        data.add(Game.blueTNTGuy.tntPrimed[3]);
        data.add(Game.blueTNTGuy.tntPrimeDate1[0]);
        data.add(Game.blueTNTGuy.tntPrimeDate1[1]);
        data.add(Game.blueTNTGuy.tntPrimeDate1[2]);
        data.add(Game.blueTNTGuy.tntPrimeDate2[0]);
        data.add(Game.blueTNTGuy.tntPrimeDate2[1]);
        data.add(Game.blueTNTGuy.tntPrimeDate2[2]);
        data.add(Game.blueTNTGuy.tntPrimeDate3[0]);
        data.add(Game.blueTNTGuy.tntPrimeDate3[1]);
        data.add(Game.blueTNTGuy.tntPrimeDate3[2]);
        data.add(Game.blueTNTGuy.tntPrimeDate4[0]);
        data.add(Game.blueTNTGuy.tntPrimeDate4[1]);
        data.add(Game.blueTNTGuy.tntPrimeDate4[2]);
        
        
        
        data.add(Game.redArcher.bowCharge);
        
        data.add(Game.redBuilder.healBotCooldown);
        data.add(Game.redBuilder.healBotCooldownTime);
        data.add(Game.redBuilder.healBotCooldownDate[0]);
        data.add(Game.redBuilder.healBotCooldownDate[1]);
        data.add(Game.redBuilder.healBotCooldownDate[2]);
        data.add(Game.redBuilder.statueCooldown);
        data.add(Game.redBuilder.statueCooldownTime);
        data.add(Game.redBuilder.statueCooldownDate[0]);
        data.add(Game.redBuilder.statueCooldownDate[1]);
        data.add(Game.redBuilder.statueCooldownDate[2]);
        
        data.add(Game.redHealBot.built);
        data.add(Game.redHealBot.buildDate[0]);
        data.add(Game.redHealBot.buildDate[1]);
        data.add(Game.redHealBot.buildDate[2]);
        if (Game.redHealBot.healLog.get(0) != null)
            data.add(Game.redHealBot.healLog.get(0).teamClass);
        else
            data.add(null);
        if (Game.redHealBot.healLog.get(1) != null)
            data.add(Game.redHealBot.healLog.get(1).teamClass);
        else
            data.add(null);
        if (Game.redHealBot.healLog.get(2) != null)
            data.add(Game.redHealBot.healLog.get(2).teamClass);
        else
            data.add(null);
        data.add(Game.redHealBot.maxHealth);
        
        data.add(Game.redStatue.built);
        data.add(Game.redStatue.buildDate[0]);
        data.add(Game.redStatue.buildDate[1]);
        data.add(Game.redStatue.buildDate[2]);
        data.add(Game.redStatue.attackCharge);
        data.add(Game.redStatue.maxHealth);
        
        data.add(Game.redHealer.mana);
        data.add(Game.redHealer.superCharge);
        data.add(Game.redHealer.superChargeTime);
        data.add(Game.redHealer.superChargeEndDate[0]);
        data.add(Game.redHealer.superChargeEndDate[1]);
        data.add(Game.redHealer.superChargeEndDate[2]);
        data.add(Game.redHealer.superChargeHealedThisTurn);
        if (Game.redHealer.healLog.get(0) != null)
            data.add(Game.redHealer.healLog.get(0).teamClass);
        else
            data.add(null);
        if (Game.redHealer.healLog.get(1) != null)
            data.add(Game.redHealer.healLog.get(1).teamClass);
        else
            data.add(null);
        if (Game.redHealer.healLog.get(2) != null)
            data.add(Game.redHealer.healLog.get(2).teamClass);
        else
            data.add(null);
        
        data.add(Game.redKnight.chargeAttackCooldownTime);
        data.add(Game.redKnight.chargeAttackCooldownDate[0]);
        data.add(Game.redKnight.chargeAttackCooldownDate[1]);
        data.add(Game.redKnight.chargeAttackCooldownDate[2]);
        
        data.add(Game.redSpy.decloakDate[0]);
        data.add(Game.redSpy.decloakDate[1]);
        data.add(Game.redSpy.decloakDate[2]);
        data.add(Game.redSpy.cloakDate[0]);
        data.add(Game.redSpy.cloakDate[1]);
        data.add(Game.redSpy.cloakDate[2]);
        data.add(Game.redSpy.invisCanAttack);
        data.add(Game.redSpy.camPosX);
        data.add(Game.redSpy.camPosY);
        data.add(Game.redSpy.cameraPlaced);
        
        data.add(Game.redTNTGuy.tntPos[0]);
        data.add(Game.redTNTGuy.tntPos[1]);
        data.add(Game.redTNTGuy.tntPos[2]);
        data.add(Game.redTNTGuy.tntPos[3]);
        data.add(Game.redTNTGuy.tntPos[4]);
        data.add(Game.redTNTGuy.tntPos[5]);
        data.add(Game.redTNTGuy.tntPos[6]);
        data.add(Game.redTNTGuy.tntPos[7]);
        data.add(Game.redTNTGuy.tntPlaced[0]);
        data.add(Game.redTNTGuy.tntPlaced[1]);
        data.add(Game.redTNTGuy.tntPlaced[2]);
        data.add(Game.redTNTGuy.tntPlaced[3]);
        data.add(Game.redTNTGuy.tntPrimed[0]);
        data.add(Game.redTNTGuy.tntPrimed[1]);
        data.add(Game.redTNTGuy.tntPrimed[2]);
        data.add(Game.redTNTGuy.tntPrimed[3]);
        data.add(Game.redTNTGuy.tntPrimeDate1[0]);
        data.add(Game.redTNTGuy.tntPrimeDate1[1]);
        data.add(Game.redTNTGuy.tntPrimeDate1[2]);
        data.add(Game.redTNTGuy.tntPrimeDate2[0]);
        data.add(Game.redTNTGuy.tntPrimeDate2[1]);
        data.add(Game.redTNTGuy.tntPrimeDate2[2]);
        data.add(Game.redTNTGuy.tntPrimeDate3[0]);
        data.add(Game.redTNTGuy.tntPrimeDate3[1]);
        data.add(Game.redTNTGuy.tntPrimeDate3[2]);
        data.add(Game.redTNTGuy.tntPrimeDate4[0]);
        data.add(Game.redTNTGuy.tntPrimeDate4[1]);
        data.add(Game.redTNTGuy.tntPrimeDate4[2]);
        
        
        
        data.add(Game.round[0]);
        data.add(Game.round[1]);
        data.add(Game.round[2]);
        
        
        
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
        
        System.out.println("Game has been saved.");
    }
    
    public static void loadPosition() {
        
        try {
            
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            InputsNewPhase = bufferedReader.readLine();
            
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
            }
            
            else if (Inputs.phase == "move and attack" && InputsNewPhase.equals("activate units")) {
                
                Inputs.skipMoveAndAttack = true;
            }
            
            else if (Inputs.phase == "move and attack" && InputsNewPhase.equals("change direction")) {
                
                Inputs.skipMoveAndAttack = true;
                Inputs.skipActivateUnits = true;
            }
            
            
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                Game.units.get(i).posX = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).posY = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).health = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).invuln = Boolean.parseBoolean(bufferedReader.readLine());
                Game.units.get(i).invis = Boolean.parseBoolean(bufferedReader.readLine());
                Game.units.get(i).freeAttacksLeft = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).attacksLeft = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).movesLeft = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).active = Boolean.parseBoolean(bufferedReader.readLine());
                Game.units.get(i).activated = Boolean.parseBoolean(bufferedReader.readLine());
                Game.units.get(i).alive = Boolean.parseBoolean(bufferedReader.readLine());
                Game.units.get(i).respawnTime = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).respawnDate[0] = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).respawnDate[1] = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).respawnDate[2] = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).poisonTime = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).poisonDate[0] = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).poisonDate[1] = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).poisonDate[2] = Integer.parseInt(bufferedReader.readLine());
                Game.units.get(i).direction = Double.parseDouble(bufferedReader.readLine());
                Game.units.get(i).changeDirectionsLeft = Integer.parseInt(bufferedReader.readLine());
            }
        
            Game.blueArcher.bowCharge = Integer.parseInt(bufferedReader.readLine());

            Game.blueBuilder.healBotCooldown = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueBuilder.healBotCooldownTime = Integer.parseInt(bufferedReader.readLine());
            Game.blueBuilder.healBotCooldownDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueBuilder.healBotCooldownDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueBuilder.healBotCooldownDate[2] = Integer.parseInt(bufferedReader.readLine());
            Game.blueBuilder.statueCooldown = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueBuilder.statueCooldownTime = Integer.parseInt(bufferedReader.readLine());
            Game.blueBuilder.statueCooldownDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueBuilder.statueCooldownDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueBuilder.statueCooldownDate[2] = Integer.parseInt(bufferedReader.readLine());

            Game.blueHealBot.built = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueHealBot.buildDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueHealBot.buildDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueHealBot.buildDate[2] = Integer.parseInt(bufferedReader.readLine());
            blueHealBotHealLog0 = bufferedReader.readLine();
            blueHealBotHealLog1 = bufferedReader.readLine();
            blueHealBotHealLog2 = bufferedReader.readLine();
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealBotHealLog0)
                    Game.blueHealBot.healLog.set(0, Game.units.get(i));
                else
                    Game.blueHealBot.healLog.set(0, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealBotHealLog1)
                    Game.blueHealBot.healLog.set(1, Game.units.get(i));
                else
                    Game.blueHealBot.healLog.set(1, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealBotHealLog2)
                    Game.blueHealBot.healLog.set(2, Game.units.get(i));
                else
                    Game.blueHealBot.healLog.set(2, null);
            }
            
            Game.blueHealBot.maxHealth = Integer.parseInt(bufferedReader.readLine());

            Game.blueStatue.built = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueStatue.buildDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueStatue.buildDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueStatue.buildDate[2] = Integer.parseInt(bufferedReader.readLine());
            Game.blueStatue.attackCharge = Integer.parseInt(bufferedReader.readLine());
            Game.blueStatue.maxHealth = Integer.parseInt(bufferedReader.readLine());

            Game.blueHealer.mana = Integer.parseInt(bufferedReader.readLine());
            Game.blueHealer.superCharge = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueHealer.superChargeTime = Integer.parseInt(bufferedReader.readLine());
            Game.blueHealer.superChargeEndDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueHealer.superChargeEndDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueHealer.superChargeEndDate[2] = Integer.parseInt(bufferedReader.readLine());
            Game.blueHealer.superChargeHealedThisTurn = Boolean.parseBoolean(bufferedReader.readLine());
            blueHealerHealLog0 = bufferedReader.readLine();
            blueHealerHealLog1 = bufferedReader.readLine();
            blueHealerHealLog2 = bufferedReader.readLine();
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealerHealLog0)
                    Game.blueHealer.healLog.set(0, Game.units.get(i));
                else
                    Game.blueHealer.healLog.set(0, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealerHealLog1)
                    Game.blueHealer.healLog.set(1, Game.units.get(i));
                else
                    Game.blueHealer.healLog.set(1, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == blueHealerHealLog2)
                    Game.blueHealer.healLog.set(2, Game.units.get(i));
                else
                    Game.blueHealer.healLog.set(2, null);
            }

            Game.blueKnight.chargeAttackCooldownTime = Integer.parseInt(bufferedReader.readLine());
            Game.blueKnight.chargeAttackCooldownDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueKnight.chargeAttackCooldownDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueKnight.chargeAttackCooldownDate[2] = Integer.parseInt(bufferedReader.readLine());

            Game.blueSpy.decloakDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueSpy.decloakDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueSpy.decloakDate[2] = Integer.parseInt(bufferedReader.readLine());
            Game.blueSpy.cloakDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueSpy.cloakDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueSpy.cloakDate[2] = Integer.parseInt(bufferedReader.readLine());
            Game.blueSpy.invisCanAttack = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueSpy.camPosX = Integer.parseInt(bufferedReader.readLine());
            Game.blueSpy.camPosY = Integer.parseInt(bufferedReader.readLine());
            Game.blueSpy.cameraPlaced = Boolean.parseBoolean(bufferedReader.readLine());

            Game.blueTNTGuy.tntPos[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPos[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPos[2] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPos[3] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPos[4] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPos[5] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPos[6] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPos[7] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPlaced[0] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueTNTGuy.tntPlaced[1] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueTNTGuy.tntPlaced[2] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueTNTGuy.tntPlaced[3] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimed[0] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimed[1] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimed[2] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimed[3] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate1[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate1[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate1[2] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate2[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate2[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate2[2] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate3[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate3[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate3[2] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate4[0] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate4[1] = Integer.parseInt(bufferedReader.readLine());
            Game.blueTNTGuy.tntPrimeDate4[2] = Integer.parseInt(bufferedReader.readLine());


            

            Game.redArcher.bowCharge = Integer.parseInt(bufferedReader.readLine());

            Game.redBuilder.healBotCooldown = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redBuilder.healBotCooldownTime = Integer.parseInt(bufferedReader.readLine());
            Game.redBuilder.healBotCooldownDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redBuilder.healBotCooldownDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redBuilder.healBotCooldownDate[2] = Integer.parseInt(bufferedReader.readLine());
            Game.redBuilder.statueCooldown = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redBuilder.statueCooldownTime = Integer.parseInt(bufferedReader.readLine());
            Game.redBuilder.statueCooldownDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redBuilder.statueCooldownDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redBuilder.statueCooldownDate[2] = Integer.parseInt(bufferedReader.readLine());

            Game.redHealBot.built = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redHealBot.buildDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redHealBot.buildDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redHealBot.buildDate[2] = Integer.parseInt(bufferedReader.readLine());
            redHealBotHealLog0 = bufferedReader.readLine();
            redHealBotHealLog1 = bufferedReader.readLine();
            redHealBotHealLog2 = bufferedReader.readLine();
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealBotHealLog0)
                    Game.redHealBot.healLog.set(0, Game.units.get(i));
                else
                    Game.redHealBot.healLog.set(0, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealBotHealLog1)
                    Game.redHealBot.healLog.set(1, Game.units.get(i));
                else
                    Game.redHealBot.healLog.set(1, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealBotHealLog2)
                    Game.redHealBot.healLog.set(2, Game.units.get(i));
                else
                    Game.redHealBot.healLog.set(2, null);
            }
            
            Game.redHealBot.maxHealth = Integer.parseInt(bufferedReader.readLine());

            Game.redStatue.built = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redStatue.buildDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redStatue.buildDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redStatue.buildDate[2] = Integer.parseInt(bufferedReader.readLine());
            Game.redStatue.attackCharge = Integer.parseInt(bufferedReader.readLine());
            Game.redStatue.maxHealth = Integer.parseInt(bufferedReader.readLine());

            Game.redHealer.mana = Integer.parseInt(bufferedReader.readLine());
            Game.redHealer.superCharge = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redHealer.superChargeTime = Integer.parseInt(bufferedReader.readLine());
            Game.redHealer.superChargeEndDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redHealer.superChargeEndDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redHealer.superChargeEndDate[2] = Integer.parseInt(bufferedReader.readLine());
            Game.redHealer.superChargeHealedThisTurn = Boolean.parseBoolean(bufferedReader.readLine());
            redHealerHealLog0 = bufferedReader.readLine();
            redHealerHealLog1 = bufferedReader.readLine();
            redHealerHealLog2 = bufferedReader.readLine();
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealerHealLog0)
                    Game.redHealer.healLog.set(0, Game.units.get(i));
                else
                    Game.redHealer.healLog.set(0, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealerHealLog1)
                    Game.redHealer.healLog.set(1, Game.units.get(i));
                else
                    Game.redHealer.healLog.set(1, null);
            }
            
            for (int i = 0; i < Game.units.size(); i++) {
                
                if (Game.units.get(i).teamName == redHealerHealLog2)
                    Game.redHealer.healLog.set(2, Game.units.get(i));
                else
                    Game.redHealer.healLog.set(2, null);
            }

            Game.redKnight.chargeAttackCooldownTime = Integer.parseInt(bufferedReader.readLine());
            Game.redKnight.chargeAttackCooldownDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redKnight.chargeAttackCooldownDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redKnight.chargeAttackCooldownDate[2] = Integer.parseInt(bufferedReader.readLine());

            Game.redSpy.decloakDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redSpy.decloakDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redSpy.decloakDate[2] = Integer.parseInt(bufferedReader.readLine());
            Game.redSpy.cloakDate[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redSpy.cloakDate[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redSpy.cloakDate[2] = Integer.parseInt(bufferedReader.readLine());
            Game.redSpy.invisCanAttack = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redSpy.camPosX = Integer.parseInt(bufferedReader.readLine());
            Game.redSpy.camPosY = Integer.parseInt(bufferedReader.readLine());
            Game.redSpy.cameraPlaced = Boolean.parseBoolean(bufferedReader.readLine());

            Game.redTNTGuy.tntPos[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPos[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPos[2] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPos[3] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPos[4] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPos[5] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPos[6] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPos[7] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPlaced[0] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redTNTGuy.tntPlaced[1] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redTNTGuy.tntPlaced[2] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redTNTGuy.tntPlaced[3] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimed[0] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimed[1] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimed[2] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimed[3] = Boolean.parseBoolean(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate1[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate1[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate1[2] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate2[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate2[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate2[2] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate3[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate3[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate3[2] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate4[0] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate4[1] = Integer.parseInt(bufferedReader.readLine());
            Game.redTNTGuy.tntPrimeDate4[2] = Integer.parseInt(bufferedReader.readLine());



            
            Game.round[0] = Integer.parseInt(bufferedReader.readLine());
            Game.round[1] = Integer.parseInt(bufferedReader.readLine());
            Game.round[2] = Integer.parseInt(bufferedReader.readLine());
            
            
            

            bufferedReader.close();
            
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
        
        catch (Exception ex) {
            
            ex.printStackTrace();
        }
        
        System.out.println("Game has been loaded.");
    }
}

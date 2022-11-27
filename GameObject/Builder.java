

package GameObject;

import Engine.GameDisplay;
import Engine.Physics;
import Game.Board;
import Game.Game;


public class Builder extends Unit {
    
    public String unitClass = "Builder";
    public double FOV = 90;
    public String attack0Name = "Free Attack";
    public String attack1Name = "Repair";
    public String attack2Name = "Build HealBot";
    public String attack3Name = "Build Statue";
    public double[] attack0Range = new double[0];
    public double[] attack1Range = {1.8};
    public double[] attack2Range = {1.8};
    public double[] attack3Range = {1.8};
    public double attack0FOV = 90;
    public double attack1FOV = 90;
    public double attack2FOV = 90;
    public double attack3FOV = 90;
    public boolean poisonable = true;
    public boolean healBotCooldown;
    public int healBotCooldownTime;
    public int[] healBotCooldownDate = new int[3];
    public boolean statueCooldown;
    public int statueCooldownTime;
    public int[] statueCooldownDate = new int[3];
    
    public int maxHealth = 6;
    public int movement = 2;
    
    public Builder(String teamName, int posX, int posY, int respawnPosX, int respawnPosY) {
        
        init(teamName, unitClass, posX, posY, maxHealth, movement, respawnPosX, respawnPosY, FOV, attack0Name, attack1Name, attack2Name, attack3Name, poisonable, attack0Range, attack1Range, attack2Range, attack3Range, attack0FOV, attack1FOV, attack2FOV, attack3FOV);
    }
    
    @Override
    public void update(int[] round) {
        
        super.update(round);
        
        if (healBotCooldownDate[1] == round[1] && healBotCooldownDate[2] == round[2] && healBotCooldown)
            healBotCooldownTime--;
        if (statueCooldownDate[1] == round[1] && statueCooldownDate[2] == round[2] && statueCooldown)
            statueCooldownTime--;
        
        if (healBotCooldownTime == 0 && healBotCooldown) {
            
            healBotCooldown = false;
            
            GameDisplay.consoleAddMessage(teamClass + " can now build a Heal Bot", teamName);
        }
        
        if (statueCooldownTime == 0 && statueCooldown) {
            
            statueCooldown = false;
            
            GameDisplay.consoleAddMessage(teamClass + " can now build a Statue", teamName);
        }
    }
    
    public void healBotDestroyed() {
        
        healBotCooldown = true;
        healBotCooldownTime = 2;
        healBotCooldownDate[0] = Game.round[0] + healBotCooldownTime;
        healBotCooldownDate[1] = Game.round[1];
        healBotCooldownDate[2] = Game.round[2];
    }
    
    public void statueDestroyed() {
        
        statueCooldown = true;
        statueCooldownTime = 2;
        statueCooldownDate[0] = Game.round[0] + statueCooldownTime;
        statueCooldownDate[1] = Game.round[1];
        statueCooldownDate[2] = Game.round[2];
    }
    
    @Override
    public void freeAttack(int x, int y) {
        
        GameDisplay.consoleAddMessage("This unit does not have that attack", teamName);
    }
    
    @Override
    public void attack1(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            if (Board.checkSquareUnit(x, y) instanceof Unit) {
                
                target = Board.checkSquareUnit(x, y);
                
                if (target.buildingClass) {
                    
                    if (!target.EMP)
                        giveHeal(3, target.posX, target.posY, attack1Range[0], attack1FOV, " repaired ", false);
                    
                    else {
                        
                        target.EMP = false;
                        
                        GameDisplay.consoleAddMessage(teamClass + " removed EMP from " + target.unitClass, teamName);
                    }
                    
                    attacksLeft--;
                }
                
//                else {
//                    
//                    attack(2, target.posX, target.posY, 1, 90, null);
//                }
            }
        }
    }
    
    @Override
    public void attack2(int x, int y) {
        
        if (attacksLeft >= 1 && !healBotCooldown) {
            
            if (Physics.checkFOV(x - posX, y - posY, FOV, direction)) {
                
                if ((double)Physics.checkRange(posX, posY, x, y, attack2FOV, direction, teamName, false).get(0) > 0 && (double)Physics.checkRange(posX, posY, x, y, attack2FOV, direction, teamName, false).get(0) <= attack2Range[0]) {
            
                    if (!(Board.checkSquareUnit(x, y) instanceof Unit) && Board.checkSquare(x, y) != 1) {

                        if (teamName == "blue") {

                            Game.blueHealBot.posX = x;
                            Game.blueHealBot.posY = y;
                            Game.blueHealBot.build();
                        }

                        else {

                            Game.redHealBot.posX = x;
                            Game.redHealBot.posY = y;
                            Game.redHealBot.build();
                        }

                        GameDisplay.consoleAddMessage(teamClass + " is constructing a HealBot", teamName);

                        attacksLeft--;
                    }
                }
            }
        }
    }
    
    @Override
    public void attack3(int x, int y) {
        
        if (attacksLeft >= 1 && !statueCooldown) {
            
            if (Physics.checkFOV(x - posX, y - posY, FOV, direction)) {
                
                if ((double)Physics.checkRange(posX, posY, x, y, attack3FOV, direction, teamName, false).get(0) > 0 && (double)Physics.checkRange(posX, posY, x, y, attack3FOV, direction, teamName, false).get(0) <= attack3Range[0]) {
            
                    if (!(Board.checkSquareUnit(x, y) instanceof Unit) && Board.checkSquare(x, y) != 1) {

                        if (teamName == "blue") {

                            Game.blueStatue.posX = x;
                            Game.blueStatue.posY = y;
                            Game.blueStatue.build();
                        }

                        else {

                            Game.redStatue.posX = x;
                            Game.redStatue.posY = y;
                            Game.redStatue.build();
                        }

                        GameDisplay.consoleAddMessage(teamClass + " is constructing a Statue", teamName);

                        attacksLeft--;
                    }
                }
            }
        }
    }
}

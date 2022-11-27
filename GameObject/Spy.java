

package GameObject;

import Engine.GameDisplay;
import Engine.Physics;
import Game.Game;


public class Spy extends Unit {
    
    public String unitClass = "Spy";
    public int[] decloakDate = new int[3];
    public int[] cloakDate = new int[3];
    //invisCanAttack = false means spy can't attack
    public boolean invisCanAttack = true;
    public int camPosX;
    public int camPosY;
    public boolean cameraPlaced;
    public double FOV = 90;
    public double EMPRange = 4;
    public String attack0Name = "Place Camera";
    public String attack1Name = "Backstab";
    public String attack2Name = "Cloak";
    public String attack3Name = "EMP";
    public double[] attack0Range = {1.8};
    public double[] attack1Range = {1.8};
    public double[] attack2Range = new double[0];
    public double[] attack3Range = {EMPRange};
    public double attack0FOV = 360;
    public double attack1FOV = 90;
    public double attack2FOV = 90;
    public double attack3FOV = 360;
    public boolean poisonable = true;
    
    public int maxHealth = 4;
    public int movement = 2;
    
    
    public Spy(String teamName, int posX, int posY, int respawnPosX, int respawnPosY, int camPosX, int camPosY) {
        
        init(teamName, unitClass, posX, posY, maxHealth, movement, respawnPosX, respawnPosY, FOV, attack0Name, attack1Name, attack2Name, attack3Name, poisonable, attack0Range, attack1Range, attack2Range, attack3Range, attack0FOV, attack1FOV, attack2FOV, attack3FOV);
        cameraPlaced = false;
    }
    
    @Override
    public void update(int[] round) {
        
        super.update(round);
        
        //cloaking
        if (cloakDate[0] == round[0] && cloakDate[1] == round[1] && cloakDate[2] == round[2])
            //invisCanAttack is set to false when cloaking starts
            invis = true;
        //uncloaking
        if (decloakDate[0] == round[0] && decloakDate[1] == round[1] && decloakDate[2] == round[2]) {
            
            //make sure invis = false
            invis = false;
            invisCanAttack = true;
        }
    }
    
    @Override
    public void damage(int amt) {
        
        super.damage(amt);
        reveal();
    }
    
    public void reveal() {
        
        //reveal invisible spy or cloaking spy
        if (invis == true || cloakDate[0] > Game.round[0] || (cloakDate[0] == Game.round[0] && cloakDate[1] > Game.round[1]) || (cloakDate[0] == Game.round[0] && cloakDate[1] == Game.round[1] && cloakDate[2] > Game.round[2])) {
            
            invis = false;
            //make sure invisCanAttack is false
            invisCanAttack = false;
            cloakDate[0] = Game.round[0] + 1;
            cloakDate[1] = Game.round[1];
            cloakDate[2] = Game.round[2];
        }
    }
    
    @Override
    public void poison(int length) {
        
        super.poison(length);
        reveal();
    }
    
    public void cloak() {
        
        //cannot attack while cloaking
        invisCanAttack = false;
        //set cloak date
        cloakDate[0] = Game.round[0] + 1;
        cloakDate[1] = Game.round[1];
        cloakDate[2] = Game.round[2];
    }
    
    public void decloak() {
        
        //start decloak, reveal spy
        invis = false;
        //set decloak date
        decloakDate[0] = Game.round[0] + 1;
        decloakDate[1] = Game.round[1];
        decloakDate[2] = Game.round[2];
    }
    
    @Override
    public void freeAttack(int x, int y) {
        
        if ((double)Physics.checkRange(posX, posY, x, y, 360, direction, teamName, false).get(0) > 0d && (double)Physics.checkRange(posX, posY, x, y, 360, direction, teamName, false).get(0) <= 1.8d) {
            
            camPosX = x;
            camPosY = y;
            cameraPlaced = true;
        }
    }
    
    @Override
    public void attack1(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            if ((double)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, true).get(0) > 0d && (double)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, true).get(0) <= attack1Range[0]) {

                for (int i = 0; i < Game.aliveUnits.size(); i++) {

                    if (Game.aliveUnits.get(i).posX == (int)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, true).get(1) && Game.aliveUnits.get(i).posY == (int)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, true).get(2)) {

                        target = Game.aliveUnits.get(i);

                        if ((target.direction >= direction - 90 && target.direction <= direction + 90) || (target.direction >= direction - 90 - 360 && target.direction <= direction + 90 - 360) || (target.direction >= direction - 90 + 360 && target.direction <= direction + 90 + 360)) {

                            GameDisplay.consoleAddMessage(teamClass + " backstabbed " + target.teamClass, "both");
                            target.damage(100);
                            break;
                        }

                        else {

                            damageTarget(2, target, null);
                            break;
                        }
                    }
                }
            }
            
            attacksLeft--;
        }
    }
    
    @Override
    public void attack2(int x, int y) {
        
        if (attacksLeft >= 0) {
            
            if (invisCanAttack) {

                cloak();
                GameDisplay.consoleAddMessage(teamClass + " is cloaking", teamName);
            }

            else {

                decloak();
                GameDisplay.consoleAddMessage(teamClass + " is decloaking", teamName);
            }
            
            attacksLeft--;
        }
    }
    
    @Override
    public void attack3(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            if (invisCanAttack) {
                
                for (int i = 0; i < Game.aliveUnits.size(); i++) {
                    
                    if (Game.aliveUnits.get(i).buildingClass && Game.aliveUnits.get(i).teamName != teamName) {
                        
                        //check distance
                        if (Math.sqrt(Math.pow(Game.aliveUnits.get(i).posX - posX, 2) + Math.pow(Game.aliveUnits.get(i).posY - posY, 2)) <= EMPRange) {
                            
                            Game.aliveUnits.get(i).EMP = true;
                            
                            GameDisplay.consoleAddMessage(Game.aliveUnits.get(i).teamClass + " has been affected by EMP", "both");
                        }
                    }
                }
                
                attacksLeft--;
            }
        }
    }
}

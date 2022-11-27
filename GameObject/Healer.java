

package GameObject;

import Engine.GameDisplay;
import Game.Board;
import Game.Game;
import java.util.ArrayList;
import java.util.Arrays;


public class Healer extends Unit {
    
    public String unitClass = "Healer";
    public int mana;
    public double healRange = 3;
    public boolean superCharge;
    public int superChargeTime;
    public int[] superChargeEndDate = new int[3];
    public boolean superChargeHealedThisTurn;
    public ArrayList<Unit> healLog = new ArrayList(Arrays.asList(null, null, null));
    public double FOV = 90;
    public String attack0Name = "Free Heal";
    public String attack1Name = "Heal";
    public String attack2Name = "Super Charge";
    public String attack3Name = "Inverse Heal";
    public double[] attack0Range = {healRange};
    public double[] attack1Range = {healRange};
    public double[] attack2Range = {healRange};
    public double[] attack3Range = {healRange};
    public double attack0FOV = 180;
    public double attack1FOV = 180;
    public double attack2FOV = 180;
    public double attack3FOV = 180;
    public boolean poisonable = true;
    
    public int maxHealth = 7;
    public int movement = 2;
    
    public Healer(String teamName, int posX, int posY, int respawnPosX, int respawnPosY) {
        
        init(teamName, unitClass, posX, posY, maxHealth, movement, respawnPosX, respawnPosY, FOV, attack0Name, attack1Name, attack2Name, attack3Name, poisonable, attack0Range, attack1Range, attack2Range, attack3Range, attack0FOV, attack1FOV, attack2FOV, attack3FOV);
    }
    
    @Override
    public void update(int[] round) {
        
        super.update(round);
        
        //remove invuln from target if it wasn't healed. check at the start of the enemy's turn
        if (superCharge && round[2] != superChargeEndDate[2]) {
            
            if (!superChargeHealedThisTurn) {
                
                //remove invuln from team
                for (int i = 0; i < Game.aliveUnits.size(); i++)
                    if (Game.aliveUnits.get(i).teamName == this.teamName)
                        Game.aliveUnits.get(i).invuln = false;

                invuln = true;
            }
            
            superChargeHealedThisTurn = false;
        }
        
        //superCharge
        if (superChargeEndDate[2] == Game.round[2] && superCharge)
            
            //mana counts down three times a round. total of 4 rounds
            mana--;
        
        //superCharge ending
        if (mana == 0 && superCharge) {
            
            //remove invuln from healer and heal target
            for (int i = 0; i < Game.aliveUnits.size(); i++)
                if (Game.aliveUnits.get(i).teamName == this.teamName)
                    Game.aliveUnits.get(i).invuln = false;
            
            superCharge = false;
            
            GameDisplay.consoleAddMessage(teamClass + "'s Super Charge has ended", teamName);
        }
        
        if ((round[2] == 1 && teamName.equals("blue")) || (round[2] == 2 && teamName.equals("red"))) {
        
            healLog.remove(0);
            healLog.add(null);
        }
    }
    
    public void superCharge() {
        
        superCharge = true;
        superChargeTime = 4;
        superChargeEndDate[0] = Game.round[0] + superChargeTime;
        superChargeEndDate[1] = Game.round[1];
        superChargeEndDate[2] = Game.round[2];

        GameDisplay.consoleAddMessage(teamClass + "'s Super Charge has been activated", teamName);
    }
    
    @Override
    public void freeAttack(int x, int y) {
        
        if (freeAttacksLeft >= 1) {
            
            if (superCharge == true) {
                
                if (Board.checkSquareUnit(x, y) instanceof Unit) {
                    
                    if (!Board.checkSquareUnit(x, y).buildingClass) {
                        
                        if (giveHeal(1, x, y, healRange, 360, null, true)) {
                        
                            Board.checkSquareUnit(x, y).invuln = true;
                            freeAttacksLeft--;
                            
                            //remove invuln from team
                            for (int i = 0; i < Game.aliveUnits.size(); i++)
                                if (Game.aliveUnits.get(i).teamName == this.teamName)
                                    Game.aliveUnits.get(i).invuln = false;
                            
                            superChargeHealedThisTurn = true;
                        }
                    }
                }

                invuln = true;
            }
            
            else if (!(Board.checkSquareUnit(x, y) == healLog.get(0) && Board.checkSquareUnit(x, y) == healLog.get(1))) {
                
                if (!Board.checkSquareUnit(x, y).buildingClass) {
                    
                    if (giveHeal(1, x, y, healRange, attack0FOV, null, true)) {
                        
                        freeAttacksLeft--;
                        
                        healLog.set(2, Board.checkSquareUnit(x, y));
                    }
                }
            }
        }
    }
    
    @Override
    public void attack1(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            if (superCharge == true) {
                
                if (Board.checkSquareUnit(x, y) instanceof Unit) {
                    
                    if (!Board.checkSquareUnit(x, y).buildingClass) {
                        
                        if (giveHeal(1, x, y, healRange, attack1FOV, null, true)) {
                        
                            Board.checkSquareUnit(x, y).invuln = true;
                            attacksLeft--;
                            
                            //remove invuln from team
                            for (int i = 0; i < Game.aliveUnits.size(); i++)
                                if (Game.aliveUnits.get(i).teamName == this.teamName)
                                    Game.aliveUnits.get(i).invuln = false;
                            
                            superChargeHealedThisTurn = true;
                        }
                    }
                }

                invuln = true;
            }
            
            else {
                
                if (!(Board.checkSquareUnit(x, y) == healLog.get(0) && Board.checkSquareUnit(x, y) == healLog.get(1))) {
                    
                    if (!Board.checkSquareUnit(x, y).buildingClass) {
                        
                        if (giveHeal(1, x, y, healRange, attack1FOV, null, true)) {
                            
                            attacksLeft--;
                            
                            healLog.set(2, Board.checkSquareUnit(x, y));

                            if (mana < 12) {

                                mana++;
                            }
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void attack2(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            if (!superCharge && mana == 12) {
                
                superCharge();
            }
            
            if (superCharge) {
                
                if (Board.checkSquareUnit(x, y) instanceof Unit) {
                    
                    if (!Board.checkSquareUnit(x, y).buildingClass) {
                        
                        if (giveHeal(1, x, y, healRange, attack2FOV, null, true)) {
                        
                            Board.checkSquareUnit(x, y).invuln = true;
                            attacksLeft--;
                            
                            //remove invuln from team
                            for (int i = 0; i < Game.aliveUnits.size(); i++)
                                if (Game.aliveUnits.get(i).teamName == this.teamName)
                                    Game.aliveUnits.get(i).invuln = false;
                            
                            superChargeHealedThisTurn = true;
                        }
                    }
                }

                invuln = true;
            }
        }
    }
    
    @Override
    public void attack3(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            attack(2, x, y, healRange, attack3FOV, "inverse healed");
        }
    }
}

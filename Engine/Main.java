

package Engine;

import Game.Board;
import Game.Game;
import Game.Replay;
import Maps.controlPoint1;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    
    public static Board board;
    public static Game game;
    public static GameDisplay display;
    public static controlPoint1 map = new controlPoint1();
    
    public static void main(String[] args) {
        
        board = new Board(map.getObstacles(), (int)map.getMapInfo().get(0), (int)map.getMapInfo().get(1), (int)map.getMapInfo().get(2), (int)map.getMapInfo().get(3));
        game = new Game();
        
        Game.blueArcher.respawnPosX = map.getBlueSpawn().get(0)[0];
        Game.blueArcher.respawnPosY = map.getBlueSpawn().get(0)[1];
        Game.blueArcher.respawn();
        Game.blueBuilder.respawnPosX = map.getBlueSpawn().get(1)[0];
        Game.blueBuilder.respawnPosY = map.getBlueSpawn().get(1)[1];
        Game.blueBuilder.respawn();
        Game.blueHealer.respawnPosX = map.getBlueSpawn().get(2)[0];
        Game.blueHealer.respawnPosY = map.getBlueSpawn().get(2)[1];
        Game.blueHealer.respawn();
        Game.blueKnight.respawnPosX = map.getBlueSpawn().get(3)[0];
        Game.blueKnight.respawnPosY = map.getBlueSpawn().get(3)[1];
        Game.blueKnight.respawn();
        Game.blueMage.respawnPosX = map.getBlueSpawn().get(4)[0];
        Game.blueMage.respawnPosY = map.getBlueSpawn().get(4)[1];
        Game.blueMage.respawn();
        Game.blueScout.respawnPosX = map.getBlueSpawn().get(5)[0];
        Game.blueScout.respawnPosY = map.getBlueSpawn().get(5)[1];
        Game.blueScout.respawn();
        Game.blueSpy.respawnPosX = map.getBlueSpawn().get(6)[0];
        Game.blueSpy.respawnPosY = map.getBlueSpawn().get(6)[1];
        Game.blueSpy.respawn();
        Game.blueTNTGuy.respawnPosX = map.getBlueSpawn().get(7)[0];
        Game.blueTNTGuy.respawnPosY = map.getBlueSpawn().get(7)[1];
        Game.blueTNTGuy.respawn();
        Game.blueHealBot.alive = false;
        Game.blueStatue.alive = false;
        
        Game.redArcher.respawnPosX = map.getRedSpawn().get(0)[0];
        Game.redArcher.respawnPosY = map.getRedSpawn().get(0)[1];
        Game.redArcher.respawn();
        Game.redBuilder.respawnPosX = map.getRedSpawn().get(1)[0];
        Game.redBuilder.respawnPosY = map.getRedSpawn().get(1)[1];
        Game.redBuilder.respawn();
        Game.redHealer.respawnPosX = map.getRedSpawn().get(2)[0];
        Game.redHealer.respawnPosY = map.getRedSpawn().get(2)[1];
        Game.redHealer.respawn();
        Game.redKnight.respawnPosX = map.getRedSpawn().get(3)[0];
        Game.redKnight.respawnPosY = map.getRedSpawn().get(3)[1];
        Game.redKnight.respawn();
        Game.redMage.respawnPosX = map.getRedSpawn().get(4)[0];
        Game.redMage.respawnPosY = map.getRedSpawn().get(4)[1];
        Game.redMage.respawn();
        Game.redScout.respawnPosX = map.getRedSpawn().get(5)[0];
        Game.redScout.respawnPosY = map.getRedSpawn().get(5)[1];
        Game.redScout.respawn();
        Game.redSpy.respawnPosX = map.getRedSpawn().get(6)[0];
        Game.redSpy.respawnPosY = map.getRedSpawn().get(6)[1];
        Game.redSpy.respawn();
        Game.redTNTGuy.respawnPosX = map.getRedSpawn().get(7)[0];
        Game.redTNTGuy.respawnPosY = map.getRedSpawn().get(7)[1];
        Game.redTNTGuy.respawn();
        Game.redHealBot.alive = false;
        Game.redStatue.alive = false;
        
        Game.redArcher.direction = 180;
        Game.redBuilder.direction = 180;
        Game.redHealer.direction = 180;
        Game.redKnight.direction = 180;
        Game.redMage.direction = 180;
        Game.redScout.direction = 180;
        Game.redSpy.direction = 180;
        Game.redTNTGuy.direction = 180;
        Game.updatePlayerView("blue");
        Game.updatePlayerView("red");
        
        display = new GameDisplay(71, 190, 50, 50, (int)map.getMapInfo().get(0), (int)map.getMapInfo().get(1), 0, 0, 0, 0);
        Game.nextTurn();
    }
}

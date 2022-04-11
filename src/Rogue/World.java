package Rogue;

/**
 * Produce the battle world map, checks whether movements are valid and runs the combat loop.
 * @author : Cheah Jia Huei, jiahueic@student.unimelb.edu.au, 1078203.
 *
 */

public class World {
    private int height;
    private int width;
    final int MAP_HEIGHT=4;
    final int MAP_WIDTH=6;
    World(){
        this.height=MAP_HEIGHT;
        this.width=MAP_WIDTH;
    }
    public void printMap(Player player, Monster monster){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(i==player.getStartPosY() && j==player.getStartPosX()){
                    char playerInitials=player.getName().charAt(0);
                    System.out.print(Character.toUpperCase(playerInitials));
                }
                else if(i==monster.getStartPosY() && j==monster.getStartPosX()){
                    char monsterIntials=monster.getName().charAt(0);
                    System.out.print(Character.toLowerCase(monsterIntials));
                }
                else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.print("\n");
    }
    public void movement(String move, Player player, Monster monster){
        int newPosY;
        int newPosX;
        if(move.equals("w")){
            newPosY=player.getStartPosY()-1;
            player.setStartPosY(newPosY);
            if(checkMovement(player)){
                if(!checkCombat(player,monster)){
                    printMap(player, monster);
                }
            }
            else{
                newPosY=player.getStartPosY()+1;
                player.setStartPosY(newPosY);
                printMap(player, monster);
            }
        }
        else if(move.equals("a")){
            newPosX=player.getStartPosX()-1;
            player.setStartPosX(newPosX);
            if(checkMovement(player)){
                if(!checkCombat(player,monster)){
                    printMap(player, monster);
                }
            }
            else{
                newPosX=player.getStartPosX()+1;
                player.setStartPosX(newPosX);
                printMap(player, monster);
            }

        }
        else if(move.equals("s")){
            newPosY=player.getStartPosY()+1;
            player.setStartPosY(newPosY);
            if(checkMovement(player)){
                if(!checkCombat(player,monster)){
                    printMap(player,monster);
                }
            }
            else{
                newPosY=player.getStartPosY()-1;
                player.setStartPosY(newPosY);
                printMap(player,monster);
            }
        }
        else if(move.equals("d")){
            newPosX=player.getStartPosX()+1;
            player.setStartPosX(newPosX);
            if(checkMovement(player)){
                if(!checkCombat(player,monster)){
                    printMap(player,monster);
                }
            }
            else{
                newPosX=player.getStartPosX()-1;
                player.setStartPosX(newPosX);
                printMap(player,monster);
            }
        }


    }
    public boolean checkMovement(Player player){
        int xCo=player.getStartPosX();
        int yCo=player.getStartPosY();
        // checks if the movement is within the border of the map
        if(xCo>=1 && xCo<this.width-1){
            if(yCo>=1 && yCo<this.height-1){
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean checkCombat(Player player, Monster monster){
        return player.getStartPosX()==monster.getStartPosX() &&
                player.getStartPosY()==monster.getStartPosY();
    }
    public void runBattleLoop(Player player, Monster monster){
        System.out.println(player.getName()+" encountered a "+monster.getName()+"!");
        System.out.println();
        // the loop stops when either the monster or player is defeated (i.e.their current health reaches 0 or less)
        while(player.getMaxHealth()>0 && monster.getCurrentHealth()>0){
            System.out.print(player.getName()+" "+player.getMaxHealth()+"/");
            System.out.print(player.computeMaxHealth()+" | "+monster.getName()+" "+monster.getCurrentHealth()+"/"+monster.getMaxHealth());
            player.attackMonster(monster);
            System.out.println();
            System.out.println(player.getName()+" attacks "+monster.getName()+" for "+player.getDamage()+" damage.");
            if(monster.getCurrentHealth()>0){
                monster.attackPlayer(player);
                System.out.println(monster.getName()+" attacks "+player.getName()+" for "+monster.getDamage()+" damage.");
            }
            System.out.println();
        }
        if(player.getMaxHealth()>0){
            System.out.print(player.getName()+" wins!");
        }
        else if(monster.getCurrentHealth()>0){
            System.out.print(monster.getName()+" wins!");
        }
        System.out.println();
        System.out.println();
        System.out.println("(Press enter key to return to main menu)");

    }

}

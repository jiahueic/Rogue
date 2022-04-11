package Rogue;

/**
 * Stores attributes for player and methods to modify them.
 * @author: Cheah Jia Huei, jiahueic@student.unimelb.edu.au, 1078203.
 *
 */
public class Player {
    private String name;
    private int maxHealth; //maxHealth means currentHealth in player, the "actual max health" obtained from computeMaxHealth() method
    private int damage;
    final int LEVEL=1;
    private int startPosX;
    private int startPosY;
    final int PLAYER_START_POSITION_X=1;
    final int PLAYER_START_POSITION_Y=1;
    Player(){
        this("anonymous");
    }
    Player(String name){
        setName(name);
        this.maxHealth=computeMaxHealth();
        this.damage=1+LEVEL;
        this.startPosX=PLAYER_START_POSITION_X;
        this.startPosY=PLAYER_START_POSITION_Y;
    }
    public void attackMonster(Monster monster){
        int iniHealth=monster.getCurrentHealth();
        int attHealth=iniHealth-this.damage;
        monster.setCurrentHealth(attHealth);

    }
    public void setName(String newName){
        this.name=newName;
    }
    public void setMaxHealth(int newMaxHealth){
        this.maxHealth=newMaxHealth;
    }
    public String getName(){
        return this.name;
    }
    public int getDamage(){
        return this.damage;
    }
    public int getMaxHealth(){
        return this.maxHealth;
    }
    public int computeMaxHealth(){
        int initialMaxHealth=17+LEVEL*3;
        return initialMaxHealth;
    }
    public int getStartPosX(){
        return this.startPosX;
    }
    public int getStartPosY(){
        return this.startPosY;
    }
    public void setStartPosX(int newStartPosX){
        this.startPosX=newStartPosX;
    }
    public void setStartPosY(int newStartPosY){
        this.startPosY=newStartPosY;
    }
}

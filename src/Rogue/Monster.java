package Rogue;

/**
 * Store attributes for monster and methods to modify them.
 * Damage attribute is the damage of monster towards player.
 * @author : Cheah Jia Huei, jiahueic@student.unimelb.edu.au, 1078203.
 *
 */
public class Monster {
    private String name;
    private int maxHealth;
    private int damage;
    private int currentHealth;
    private int startPosX;
    private int startPosY;
    final int MONSTER_START_POSITION_X=4;
    final int MONSTER_START_POSITION_Y=2;
    Monster(){
        this("anonymous",0,0);
    }
    Monster(String name, int maxHealth, int damage){
        setName(name);
        setMaxHealth(maxHealth);
        setDamage(damage);
        setCurrentHealth(maxHealth);
        this.startPosX=MONSTER_START_POSITION_X;
        this.startPosY=MONSTER_START_POSITION_Y;
    }
    public void attackPlayer(Player player){
        // player's health level before attack
        int iniHealth=player.getMaxHealth();
        // player's health after attack by monster
        int attHealth= iniHealth-this.damage;
        player.setMaxHealth(attHealth);
    }
    public void setName(String newName){
        this.name=newName;
    }
    public void setDamage(int newDamage){
        this.damage=newDamage;
    }
    public String getName(){
        return this.name;
    }
    public int getMaxHealth(){
        return this.maxHealth;
    }
    public int getDamage(){
        return this.damage;
    }
    public void setMaxHealth(int newMaxHealth){
        this.maxHealth= newMaxHealth;
    }
    public void setCurrentHealth(int newCurrentHealth){
        this.currentHealth=newCurrentHealth;
    }
    public int getCurrentHealth(){
        return this.currentHealth;
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

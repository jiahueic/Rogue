package Rogue;

/**
 * GameEngine runs the maingameloop
 * integrates other Player, Monster, and World class into the system.
 * @author : Cheah Jia Huei, jiahueic@student.unimelb.edu.au, 1078203.
 *
 */
import java.util.Scanner;
public class GameEngine {
    final int PLAYER_START_POSITION_X=1;
    final int PLAYER_START_POSITION_Y=1;
    final int MONSTER_START_POSITION_X=4;
    final int MONSTER_START_POSITION_Y=2;
    public static void main(String[] args) {
        Player player=new Player();
        Monster monster=new Monster();
        World world=new World();
        Scanner keyboard= new Scanner(System.in);

        // TODO: Some starter code has been provided below.
        // Edit this method as you find appropriate.

        // Creates an instance of the game engine.
        GameEngine gameEngine = new GameEngine();

        // Runs the main game loop.
        gameEngine.runGameLoop(player, monster,world,keyboard);

    }


    /*
     *  Logic for running the main game loop.
     */
    private void runGameLoop(Player player, Monster monster, World world, Scanner keyboard) {
        displayTitleText(player, monster);
        System.out.print("> ");
        String myEntry=keyboard.nextLine();
        checkCommand(myEntry,player, monster,world,keyboard);
        boolean isExit=myEntry.equals("exit");
        outerLoop:
        while(! isExit){
            System.out.print("> ");
            myEntry=keyboard.next();
            checkCommand(myEntry,player, monster,world,keyboard);
            isExit=myEntry.equals("exit");
        }


    }

    /*
     *  Displays the title text.
     */
    private void displayTitleText(Player player, Monster monster) {

        String titleText = " ____                        \n" +
                "|  _ \\ ___   __ _ _   _  ___ \n" +
                "| |_) / _ \\ / _` | | | |/ _ \\\n" +
                "|  _ < (_) | (_| | |_| |  __/\n" +
                "|_| \\_\\___/ \\__, |\\__,_|\\___|\n" +
                "COMP90041   |___/ Assignment ";

        System.out.println(titleText);
        System.out.println();
        if(player.getName().equals("anonymous")){
            System.out.print("Player: [None]");
        }
        else {
            System.out.print("Player: "+player.getName()+" "+player.getMaxHealth()+"/"+player.computeMaxHealth());
        }
        if(monster.getName().equals("anonymous")){
            System.out.print("  | Monster: [None]");
        }
        else {
            System.out.print("  | Monster: "+monster.getName()+" "+ monster.getCurrentHealth()+"/"+monster.getMaxHealth());
        }
        System.out.println();
        System.out.println();
        System.out.println("Please enter a command to continue.");
        System.out.println("Type 'help' to learn how to get started.\n");

    }
    private void checkCommand(String myEntry, Player player, Monster monster, World world, Scanner keyboard){
        if(myEntry.equals("help")){
            System.out.println("Type 'commands' to list all available commands");
            System.out.println("Type 'start' to start a new game");
            System.out.println("Create a character, battle monsters, and find treasure!\n");

        }
        else if(myEntry.equals("commands")){
            System.out.println("help");
            System.out.println("player");
            System.out.println("monster");
            System.out.println("start");
            System.out.println("exit\n");
        }
        else if(myEntry.equals("player")){
            if(player.getName().equals("anonymous")){
                System.out.println("What is your character's name?");
                String name=keyboard.nextLine();
                player.setName(name);
                System.out.print("Player '");
                System.out.print(player.getName());
                System.out.print("' created.\n");
                System.out.println();
                System.out.println("(Press enter key to return to main menu)");
                String enterkey=keyboard.nextLine();
                checkEnter(enterkey,player,monster,world,keyboard);
            }
            else{
                System.out.println(player.getName()+ " (Lv. 1)");
                System.out.println("Damage: "+player.getDamage());
                System.out.println("Health: "+player.getMaxHealth()+"/"+player.computeMaxHealth());
                System.out.println();
                System.out.println("(Press enter key to return to main menu)");
                String enterkey=keyboard.nextLine();
                checkEnter(enterkey,player,monster,world,keyboard);
            }

        }
        else if(myEntry.equals("monster")){
            System.out.print("Monster name: ");
            String monsName=keyboard.next();
            System.out.print("Monster health: ");
            int health=keyboard.nextInt();
            System.out.print("Monster damage: ");
            int damage=keyboard.nextInt();
            monster.setName(monsName);
            monster.setMaxHealth(health); // need to store max health as a variable somewhere for later use
            monster.setCurrentHealth(health);
            monster.setDamage(damage);
            System.out.print("Monster '");
            System.out.print(monster.getName());
            System.out.print("' created.");
            System.out.println();
            System.out.println();
            System.out.println("(Press enter key to return to main menu)");
            String enterkey=keyboard.nextLine();
            checkEnter(enterkey,player,monster,world,keyboard);

        }


        else if(myEntry.equals("exit")){
            System.out.println("Thank you for playing Rogue!");
        }

        else if(myEntry.equals("start")){
            if(player.getName().equals("anonymous")){
                System.out.println("No player found, please create a player with 'player' first.");
                System.out.println();
                System.out.println("(Press enter key to return to main menu)");
                String enterkey=keyboard.nextLine();
                checkEnter(enterkey,player,monster,world,keyboard);
            }
            else if(monster.getName().equals("anonymous")){
                System.out.println("No monster found, please create a monster with 'monster' first.");
                System.out.println();
                System.out.println("(Press enter key to return to main menu)");
                String enterkey=keyboard.nextLine();
                checkEnter(enterkey,player,monster,world,keyboard);
            }
            //healing the player and monster to full health when a new game is triggered
            else if(player.getMaxHealth()!=player.computeMaxHealth() || monster.getCurrentHealth()!=monster.getMaxHealth()){
                player.setMaxHealth(player.computeMaxHealth());
                monster.setCurrentHealth(monster.getMaxHealth());
                // also, restore player and monster to their original position
                player.setStartPosX(PLAYER_START_POSITION_X);
                player.setStartPosY(PLAYER_START_POSITION_Y);
                monster.setStartPosX(MONSTER_START_POSITION_X);
                monster.setStartPosY(MONSTER_START_POSITION_Y);
                runStartCommand(player,monster,world,keyboard);
            }
            else{
                runStartCommand(player,monster,world,keyboard);
            }
        }
    }

    public void checkEnter(String myEntry,Player player, Monster monster, World world, Scanner keyboard){
        if(myEntry.equals("")){
            displayTitleText(player, monster);
        }
    }
    // home is invalid before the start command, therefore we need to create a separate function outside checkCommand
    public void checkHome(String myEntry, Player player, Monster monster, World world, Scanner keyboard){
        if(myEntry.equals("home")){
            System.out.println("Returning home...");
            System.out.println();
            System.out.println("(Press enter key to return to main menu)");
            String enterkey=keyboard.nextLine();
            checkEnter(enterkey,player,monster,world,keyboard);

        }
    }


    public void runStartCommand(Player player, Monster monster, World world, Scanner keyboard){
        world.printMap(player,monster);
        boolean secondExitCheck=false;
        while(!world.checkCombat(player,monster)&& !secondExitCheck){
            System.out.print("> ");
            String move=keyboard.next();
            checkHome(move,player,monster,world,keyboard);
            checkCommand(move,player,monster,world,keyboard);
            secondExitCheck=move.equals("exit");
            world.movement(move,player,monster);
        }
        if(world.checkCombat(player,monster)){
            world.runBattleLoop(player,monster);
            String enterkey=keyboard.nextLine();
            checkEnter(enterkey,player,monster,world,keyboard);
        }
        else if(secondExitCheck){
            System.exit(0);
        }

    }



}





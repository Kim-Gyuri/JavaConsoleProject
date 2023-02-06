package console.adventure.domain;

import console.adventure.domain.enemy.Enemy;
import console.adventure.domain.item.Item;
import console.adventure.domain.player.*;
import console.adventure.repository.ArcherAttack;
import console.adventure.repository.WarriorAttack;
import console.adventure.repository.WizardAttack;
import console.adventure.resource.Image;

import java.util.HashMap;

/*
 Game System >
 classesOfHero() : 게임에 있는 영웅 캐릭터를 세팅한다.
 guide() : 게임 가이드

 thanks(), logo(), getImage() : 이미지 출력한다.

 chooseEnemy() : 던전 몬스터 세팅
 chooseHero : 하고 싶은 캐릭터를 선택한다.
 userInfo() : 캐릭터 정보 출력
 checkStatusWindow() : 플레이어와 몬스터의 HP MP를 확인한다.
 checkDropRate_bounty() : 몬스터를 물리친 후, 획득한 아이템(물약/불사조눈물) or 현상금을 확인한다.
 */

public class GameSystem {

    private static final String BASIC_ATTACK = "basic_attack";
    private static final String ULTIMATE = "ultimate";

    private HashMap<String, Player> heroes;
    private PatchNote patchNote;
    private Image image;
    private Player user;

    public GameSystem() {
        this.patchNote = new PatchNote();
        this.heroes = new HashMap<>();
        classesOfHero();
        this.image = new Image();
    }

    public void classesOfHero() {
        Player warrior = new Warrior();
        warrior.skillTree(new WarriorAttack());

        Player wizard = new Wizard();
        wizard.skillTree(new WizardAttack());

        Player archer = new Archer();
        archer.skillTree(new ArcherAttack());

        heroes.put(Hero.WARRIOR.getName(), warrior);
        heroes.put(Hero.WIZARD.getName(), wizard);
        heroes.put(Hero.ARCHER.getName(), archer);
    }

    public void guide() {
        System.out.println();
        System.out.println();
        System.out.println("      The Black Volcano Dungeon is open from the start of the subjugation phase, named \"Anton Raid\"");
        System.out.println("      you are participating in the Anton Raid.");
        System.out.println("      In the Black Volcano Dungeon, You hunt down monsters and get bounty and rare items.");
        System.out.println("      Rare items include potions and phoenix tears.");
        System.out.println("      when use potion, heals the player for " + Item.Potion.getHealAmount() + " health point.");
        System.out.println("      when use phoenix tears, completely heals the player for " + Item.TearsOfPhoenix.getHealAmount() + " health point.");
        System.out.println("      Defeat enemies for a chance to get one.");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void thanks() {
        image.thanks();
    }

    public void logo() {
        image.gameLogo();
    }

    public Enemy chooseEnemy() {
        return patchNote.setMonster();
    }


    public void chooseHero(String key) {
        this.user = heroes.get(key);
    }

    public Player userInfo() {
        return this.user;
    }

    public void checkStatusWindow(Player player, Enemy enemy) {
        System.out.print("      [" + player.getOccupation().getName() + "'s status]");
        System.out.print("  HP: " + player.getHealthPoint());
        System.out.print(", MP: " + player.getMagicPoint()+"\n");
        System.out.println("      [" + enemy.getName() +"'s status]  HP: " + enemy.getHealthPoint());
    }

    public void checkDropRate_bounty(Player player, Enemy enemy) {
        System.out.println();
        System.out.println();
        System.out.println("      " + enemy.getName() + " was defeated.");
        System.out.println("      You have " + player.getHealthPoint() + " HP left.");
        System.out.println("      You have earned a " + enemy.getBounty() + " bounty.");

        player.earned(enemy.getBounty());
        System.out.println("      Now, you have " + player.getMoney() +".");
        System.out.println("      Does the monster have potion? " + enemy.isHavePotion());
        System.out.println("      Does the monster have tears of phoenix? " + enemy.isHaveTearsOfPhoenix());
        System.out.println();
        System.out.println();
        if (enemy.isHavePotion()) player.getInventory().putPotion();
        if (enemy.isHaveTearsOfPhoenix()) player.getInventory().putTearsOfPhoenix();
        enemy.resetHP();
    }

    public void getImage(String name) {
        if (name.equals(Hero.WARRIOR.getName())) image.warrior();
        else if (name.equals(Hero.WIZARD.getName())) image.wizard();
        else if (name.equals(Hero.ARCHER.getName())) image.archer();
        else if (name.equals(Enemy.Monster.DRAGON.getName())) image.dragon();
        else if (name.equals(Enemy.Monster.GOBLIN.getName())) image.goblin();
        else if (name.equals(Enemy.Monster.SNAKE.getName())) image.snake();
        else if (name.equals(Enemy.Monster.MANTICORE.getName())) image.menticore();
    }

    public void getImage(String hero, String enemy) {
        if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.DRAGON.getName())) image.warrior_And_dragon();
        else if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.GOBLIN.getName())) image.warrior_And_goblin();
        else if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.SNAKE.getName())) image.warrior_And_snake();
        else if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.MANTICORE.getName())) image.warrior_And_menticore();

        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.DRAGON.getName())) image.wizard_And_dragon();
        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.GOBLIN.getName())) image.wizard_And_goblin();
        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.SNAKE.getName())) image.wizard_And_snake();
        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.MANTICORE.getName())) image.wizard_And_menticore();

        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.DRAGON.getName())) image.archer_And_dragon();
        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.GOBLIN.getName())) image.archer_And_goblin();
        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.SNAKE.getName())) image.archer_And_snake();
        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.MANTICORE.getName())) image.archer_And_menticore();
    }

    public void getImage(String hero, String enemy, String skill) {
        if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.DRAGON.getName()) && skill.equals(BASIC_ATTACK)) image.warrior_And_dragon_basicAttack();
        else if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.DRAGON.getName()) && skill.equals(ULTIMATE)) image.warrior_And_dragon_ultimate();

        else if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(BASIC_ATTACK)) image.warrior_And_goblin_basicAttack();
        else if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(ULTIMATE)) image.warrior_And_goblin_ultimate();

        else if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.SNAKE.getName()) && skill.equals(BASIC_ATTACK)) image.warrior_And_snake_basicAttack();
        else if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.SNAKE.getName()) && skill.equals(ULTIMATE)) image.warrior_And_snake_ultimate();

        else if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(BASIC_ATTACK)) image.warrior_And_menticore_basicAttack();
        else if (hero.equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(ULTIMATE)) image.warrior_And_menticore_ultimate();

        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.DRAGON.getName()) && skill.equals(BASIC_ATTACK)) image.wizard_And_dragon_basicAttack();
        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.DRAGON.getName()) && skill.equals(ULTIMATE)) image.wizard_And_dragon_ultimate();

        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(BASIC_ATTACK)) image.wizard_And_goblin_basicAttack();
        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(ULTIMATE)) image.wizard_And_goblin_ultimate();

        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.SNAKE.getName()) && skill.equals(BASIC_ATTACK)) image.wizard_And_snake_basicAttack();
        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.SNAKE.getName()) && skill.equals(ULTIMATE)) image.wizard_And_snake_ultimate();

        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(BASIC_ATTACK)) image.wizard_And_menticore_basicAttack();
        else if (hero.equals(Hero.WIZARD.getName()) && enemy.equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(ULTIMATE)) image.wizard_And_menticore_ultimate();

        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.DRAGON.getName()) && skill.equals(BASIC_ATTACK)) image.archer_And_dragon_basicAttack();
        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.DRAGON.getName()) && skill.equals(ULTIMATE)) image.archer_And_dragon_ultimate();

        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(BASIC_ATTACK)) image.archer_And_goblin_basicAttack();
        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(ULTIMATE)) image.archer_And_goblin_ultimate();

        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.SNAKE.getName()) && skill.equals(BASIC_ATTACK)) image.archer_And_snake_basicAttack();
        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.SNAKE.getName()) && skill.equals(ULTIMATE)) image.archer_And_snake_ultimate();

        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(BASIC_ATTACK)) image.archer_And_menticore_basicAttack();
        else if (hero.equals(Hero.ARCHER.getName()) && enemy.equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(ULTIMATE)) image.archer_And_menticore_ultimate();
    }


}

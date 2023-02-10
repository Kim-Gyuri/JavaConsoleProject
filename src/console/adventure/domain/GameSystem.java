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
 # system 운영에 필요한 것
    private HashMap<String, Player> heroes;
    private Player user;
    private Enemy enemy;

 # 값 세팅
 classesOfHero() : 게임에 있는 영웅 캐릭터를 세팅한다.
 chooseEnemy() : 던전 몬스터 세팅
 chooseHero : 하고 싶은 캐릭터를 선택한다.

 # Player 메소드 사용
 # 예외처리
 # player -> system -> view
 basicAttack()
 ultimate()
 useTearsOfPhoenix()
 drinkPotion()
 run_away()

 # 게임 도중 필요한 정보를 만든다.
 checkDropRate_bounty() : 몬스터를 물리친 후, 획득한 아이템(물약/불사조눈물) or 현상금을 확인한다.
 checkStatusWindow() : 플레이어와 몬스터의 HP MP를 확인한다.

 # 게임 예외처리에 필요한 체크포인트
 checkPlayerHP() : HP 최솟값에서 벗어났는지?
 checkPlayerPower() : 궁극기 사용할 MP가 있는지?
 checkPlayerMP() : MP 최솟값에서 벗어났는지?
 checkPlayerIsAlive() : 던전사냥을 계속하기 위한 Player HP가 남아있는지?
 checkEnemyHP() : 몬스터가 죽었는지?


 # guide() : 게임 가이드

# View 필요한 Player/Enemy 정보
 enemyName()
 showEnemyInfo()
 showPlayerInfo()

 # 이미지 출력한다.
 thanks() : 게임 종료 시 문구 출력
 logo() : 게임 로고
 getHeroImage(String name) : Hero 선택지에 필요한 이미지 출력
 getEnemyImage() : 던전 몬스터 출력
 getHeroAndEnemyImage() : 던전 사냥 중일 때 이미지
 getAttackImage(String skill) : Hero 공격모션 이미지 출력

 */

public class GameSystem {

    private static final String BASIC_ATTACK = "basic_attack";
    private static final String ULTIMATE = "ultimate";

    private HashMap<String, Player> heroes;
    private PatchNote patchNote;
    private Image image;
    private Player user;
    private Enemy enemy;

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

    public void chooseHero(String key) {
        this.user = heroes.get(key);
    }

    public void chooseEnemy() {
       this.enemy = patchNote.setMonster();
    }

    public void basicAttack() {
        getAttackImage(BASIC_ATTACK);
        user.basicAttack(user, enemy);

        if (checkPlayerHP()) user.resetHP(patchNote.zero());
    }

    public void ultimate() {
        if (checkPlayerPower()) {
            System.out.println("      There are not enough Magic Point.");
            return;
        }
        getAttackImage(ULTIMATE);
        user.ultimate(user, enemy);

        if (checkPlayerHP()) user.resetHP(patchNote.zero());
        if (checkPlayerMP()) user.resetMP(patchNote.zero());
    }

    public void useTearsOfPhoenix() {
        if (user.getInventory().findTearsOfPhoenix() < 1) {
            System.out.println("      You have no tears of phoenix left! Defeat enemies for a chance to get one.");
        } else {
            user.useTearsOfPhoenix();
            System.out.println(
                    "      You user tears of phoenix, healing yourself for " + Item.TearsOfPhoenix.getHealAmount() +"," +
                    "\n      You now have " + user.getHealthPoint() + "HP and " + user.getMagicPoint() +"MP" +
                    "\n      You have " + user.getInventory().findTearsOfPhoenix() + " tears of phoenix left. \n");
        }
    }

    public void drinkPotion() {
        if (user.getInventory().findPotion() < patchNote.onePoint())  {
            System.out.println("      You have no potions left! Defeat enemies for a chance to get one.");
        } else {
            user.drinkPotion();

            if (user.getHealthPoint() > patchNote.getMaxHP()) user.resetHP(patchNote.getMaxHP());
            if (user.getMagicPoint() > patchNote.getMaxMP()) user.resetMP(patchNote.getMaxMP());

            System.out.println(
                    "      You drink a potion, healing yourself for " + Item.Potion.getHealAmount() +"," +
                    "\n      You now have " + user.getHealthPoint() + "HP and " + user.getMagicPoint() +"MP" +
                    "\n      You have " + user.getInventory().findPotion() + " health potions left. \n");
        }
    }

    public void run_away() {
        user.run_away(enemy);
    }

    public void checkStatusWindow() {
        System.out.print("      [" + user.getName() + "'s status]");
        System.out.print("  HP: " + user.getHealthPoint());
        System.out.print(", MP: " + user.getMagicPoint()+"\n");
        System.out.println("      [" + enemy.getName() +"'s status]  HP: " + enemy.getHealthPoint());
    }

    public void checkDropRate_bounty() {
        System.out.println();
        System.out.println();
        System.out.println("      " + enemy.getName() + " was defeated.");
        System.out.println("      You have " + user.getHealthPoint() + " HP left.");
        System.out.println("      You have earned a " + enemy.getBounty() + " bounty.");

        user.earned(enemy.getBounty());
        System.out.println("      Now, you have " + user.getMoney() +".");
        System.out.println("      Does the monster have potion? " + enemy.isHavePotion());
        System.out.println("      Does the monster have tears of phoenix? " + enemy.isHaveTearsOfPhoenix());
        System.out.println();
        System.out.println();
        if (enemy.isHavePotion()) user.getInventory().putPotion();
        if (enemy.isHaveTearsOfPhoenix()) user.getInventory().putTearsOfPhoenix();
        enemy.resetHP();
    }

    public boolean checkPlayerHP() {
        return (user.getHealthPoint() < patchNote.onePoint());
    }

    public boolean checkPlayerPower() {
        return (user.getMagicPoint() < user.getPower());
    }

    public boolean checkPlayerMP() {
        return (user.getMagicPoint() < patchNote.zero());
    }

    public boolean checkPlayerIsAlive() {
        return (user.getHealthPoint() > patchNote.zero());
    }

    public boolean checkEnemyHP() {
        return (enemy.getHealthPoint() > patchNote.zero());
    }

    public String enemyName() {
        return enemy.getName();
    }

    public String showPlayerInfo() {
        return user.toString();
    }

    public String showEnemyInfo() {
        return enemy.toString();
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

    public void getHeroImage(String name) {
        if (name.equals(Hero.WARRIOR.getName())) image.warrior();
        else if (name.equals(Hero.WIZARD.getName())) image.wizard();
        else if (name.equals(Hero.ARCHER.getName())) image.archer();
    }

    public void getEnemyImage() {
        if (enemy.getName().equals(Enemy.Monster.DRAGON.getName())) image.dragon();
        else if (enemy.getName().equals(Enemy.Monster.GOBLIN.getName())) image.goblin();
        else if (enemy.getName().equals(Enemy.Monster.SNAKE.getName())) image.snake();
        else if (enemy.getName().equals(Enemy.Monster.MANTICORE.getName())) image.menticore();
    }

    public void getHeroAndEnemyImage() {
        if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.DRAGON.getName())) image.warrior_And_dragon();
        else if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.GOBLIN.getName())) image.warrior_And_goblin();
        else if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.SNAKE.getName())) image.warrior_And_snake();
        else if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.MANTICORE.getName())) image.warrior_And_menticore();

        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.DRAGON.getName())) image.wizard_And_dragon();
        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.GOBLIN.getName())) image.wizard_And_goblin();
        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.SNAKE.getName())) image.wizard_And_snake();
        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.MANTICORE.getName())) image.wizard_And_menticore();

        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.DRAGON.getName())) image.archer_And_dragon();
        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.GOBLIN.getName())) image.archer_And_goblin();
        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.SNAKE.getName())) image.archer_And_snake();
        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.MANTICORE.getName())) image.archer_And_menticore();
    }

    public void getAttackImage(String skill) {
        if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.DRAGON.getName()) && skill.equals(BASIC_ATTACK)) image.warrior_And_dragon_basicAttack();
        else if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.DRAGON.getName()) && skill.equals(ULTIMATE)) image.warrior_And_dragon_ultimate();

        else if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(BASIC_ATTACK)) image.warrior_And_goblin_basicAttack();
        else if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(ULTIMATE)) image.warrior_And_goblin_ultimate();

        else if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.SNAKE.getName()) && skill.equals(BASIC_ATTACK)) image.warrior_And_snake_basicAttack();
        else if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.SNAKE.getName()) && skill.equals(ULTIMATE)) image.warrior_And_snake_ultimate();

        else if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.getName().equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(BASIC_ATTACK)) image.warrior_And_menticore_basicAttack();
        else if (user.getName().equals(Hero.WARRIOR.getName()) && enemy.equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(ULTIMATE)) image.warrior_And_menticore_ultimate();

        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.DRAGON.getName()) && skill.equals(BASIC_ATTACK)) image.wizard_And_dragon_basicAttack();
        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.DRAGON.getName()) && skill.equals(ULTIMATE)) image.wizard_And_dragon_ultimate();

        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(BASIC_ATTACK)) image.wizard_And_goblin_basicAttack();
        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(ULTIMATE)) image.wizard_And_goblin_ultimate();

        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.SNAKE.getName()) && skill.equals(BASIC_ATTACK)) image.wizard_And_snake_basicAttack();
        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.SNAKE.getName()) && skill.equals(ULTIMATE)) image.wizard_And_snake_ultimate();

        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(BASIC_ATTACK)) image.wizard_And_menticore_basicAttack();
        else if (user.getName().equals(Hero.WIZARD.getName()) && enemy.getName().equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(ULTIMATE)) image.wizard_And_menticore_ultimate();

        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.DRAGON.getName()) && skill.equals(BASIC_ATTACK)) image.archer_And_dragon_basicAttack();
        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.DRAGON.getName()) && skill.equals(ULTIMATE)) image.archer_And_dragon_ultimate();

        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(BASIC_ATTACK)) image.archer_And_goblin_basicAttack();
        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.GOBLIN.getName()) && skill.equals(ULTIMATE)) image.archer_And_goblin_ultimate();

        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.SNAKE.getName()) && skill.equals(BASIC_ATTACK)) image.archer_And_snake_basicAttack();
        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.SNAKE.getName()) && skill.equals(ULTIMATE)) image.archer_And_snake_ultimate();

        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(BASIC_ATTACK)) image.archer_And_menticore_basicAttack();
        else if (user.getName().equals(Hero.ARCHER.getName()) && enemy.getName().equals(Enemy.Monster.MANTICORE.getName()) && skill.equals(ULTIMATE)) image.archer_And_menticore_ultimate();
    }

}

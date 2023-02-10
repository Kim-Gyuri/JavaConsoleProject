package console.adventure.domain.player;

// 처음 직업을 고르면,
// 포션 2개를 줌 , 무기는 세팅되는 것

import console.adventure.domain.Inventory;
import console.adventure.domain.PatchNote;
import console.adventure.domain.enemy.Enemy;
import console.adventure.domain.item.Item;
import console.adventure.repository.AttackRepository;

public abstract class Player {

    private Hero hero;
    private int healthPoint;
    private int magicPoint;
    private int power;
    private int basicAttack;
    private int ultimate;
    private Inventory inventory;
    private int money;

    private PatchNote patchNote;
    private AttackRepository attackRepository;


    public Player(Hero hero) {
        this.patchNote = new PatchNote();
        this.hero = hero;
        this.healthPoint = patchNote.setPlayerHP();
        this.magicPoint = patchNote.setPlayerMP();
        this.basicAttack = patchNote.setPlayerBasicAttack();  //평타
        this.power = patchNote.setPlayerUltimateMP();       //(궁극기 썼을 때 마나 사용량)
        this.ultimate = patchNote.setPlayerUltimate();      //궁극기
        this.inventory = new Inventory();
    }

    /*
    Player Business Logic >
    # player 동작 속성
    basicAttack()
    ultimate()
    earned()
    damageDealt()
    useMagicPoint()
    run_away()
    drinkPotion()
    useTearsOfPhoenix()

    # GameSystem 예외처리 과정에서 사용한다.
    # player HP/MP 예외처리
    resetHP()
    resetMP()
     */

    public void skillTree(AttackRepository attackRepository) {
        this.attackRepository = attackRepository;
    }

    public void basicAttack(Player player, Enemy enemy) {
        attackRepository.basicAttack(player, enemy);
        System.out.println("      You strike the " + enemy.getName() + " for " + player.getBasicAttack());
        System.out.println("      You receive " + enemy.getPower() + " in retaliation" );
    }

    public void ultimate(Player player, Enemy enemy) {
        attackRepository.ultimate(player, enemy);
        System.out.println("      You strike the " + enemy.getName() + " for " + player.getUltimate());
        System.out.println("      You receive " + enemy.getPower() + " in retaliation" );
    }

    public void earned(int money) {
        this.money += money;
    }

    public void damageDealt(int damage) {
        this.healthPoint -= damage;
    }

    public void useMagicPoint() {
        this.magicPoint -= power;
    }

    public void run_away(Enemy enemy) {
        System.out.println("      You run away from the " + enemy.getName() + "\n      but...");
    }

    public void drinkPotion() {
        inventory.pickPotion();
        this.healthPoint += Item.Potion.getHealAmount();
        this.magicPoint += Item.Potion.getHealAmount();
    }

    public void useTearsOfPhoenix() {
        inventory.pickTearsOfPhoenix();
        this.healthPoint = Item.TearsOfPhoenix.getHealAmount();
        this.magicPoint = Item.TearsOfPhoenix.getHealAmount();
    }

    public void resetHP(int point) {
        this.healthPoint = point;
    }

    public void resetMP(int point) {
        this.magicPoint = point;
    }


    /*
    getter, toString()
     */

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return hero.getName();
    }

    public int getMoney() {
        return money;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getMagicPoint() {
        return magicPoint;
    }

    public int getBasicAttack() {
        return basicAttack;
    }

    public int getUltimate() {
        return ultimate;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        System.out.println();
        System.out.println();
        return "      [Player info]" +
               "\n      Name: " + hero.getName() +
               "\n      Weapon: " + hero.getWeapon() +
               "\n      Health Point: " + healthPoint +
               "\n      Magic Point: " + magicPoint +
               "\n      Basic Attack Power: " + basicAttack +
               "\n      Ultimate Power: " + ultimate +
               "\n      Inventory: " + inventory.toString();
    }
}

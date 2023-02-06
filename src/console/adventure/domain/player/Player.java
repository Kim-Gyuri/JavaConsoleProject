package console.adventure.domain.player;

// 처음 직업을 고르면,
// 포션 2개를 줌 , 무기는 세팅되는 것
// 던전 퀘스트를 할것인지 물어봄. / 상점을 이용할 것인지?


//던전---
//List<몬스터> -> 정보 확인
// 전투

// 레벨
//몬스터를 잡았을 때
// 단계별 경험치 획득량
// 레벨별 마나 / 체력 증가 / 공격력 증가
//

import console.adventure.domain.Inventory;
import console.adventure.domain.PatchNote;
import console.adventure.domain.enemy.Enemy;
import console.adventure.domain.item.Item;
import console.adventure.repository.AttackRepository;

public abstract class Player {

    private Hero hero;
    private boolean isAlive;
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
        this.isAlive = patchNote.setPlayerHeart();
        this.power = patchNote.setPlayerUltimateMP();       //(궁극기 썼을 때 마나 사용량)
        this.ultimate = patchNote.setPlayerUltimate();      //궁극기
        this.inventory = new Inventory();
    }

    /*
    Player Business Logic >
    basicAttack()
    ultimate()


    earned()
    damageDealt()
    useMagicPoint()
    run_away()
    drinkPotion()
    useTearsOfPhoenix()
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
        if (this.healthPoint < 0) this.healthPoint = 0;
    }

    public void useMagicPoint() {
        //MP 없을 때 예외처리
        if (magicPoint < power) {
            System.out.println("      There are not enough Magic Point.");
        } else {
            this.magicPoint -= power;
        }
    }

    public void run_away(Enemy enemy) {
        System.out.println("      You run away from the " + enemy.getName() + "\n      but...");
    }

    public void drinkPotion() {
        if (inventory.findPotion() < 1)  {
            System.out.println("      You have no potions left! Defeat enemies for a chance to get one.");
        } else {
            inventory.pickPotion();
            this.healthPoint += Item.Potion.getHealAmount();
            this.magicPoint += Item.Potion.getHealAmount();

            if (healthPoint > patchNote.getMaxHP()) this.healthPoint = patchNote.getMaxHP();
            if (magicPoint > patchNote.getMaxMP()) this.magicPoint = patchNote.getMaxMP();

            System.out.println(
                    "      You drink a potion, healing yourself for " + Item.Potion.getHealAmount() +"," +
                    "\n      You now have " + this.healthPoint + "HP and" + this.magicPoint +"MP" +
                    "\n      You have " + inventory.findPotion() + " health potions left. \n");
        }
    }

    public void useTearsOfPhoenix() {
        if (inventory.findTearsOfPhoenix() < 1) {
            System.out.println("      You have no tears of phoenix left! Defeat enemies for a chance to get one.");
        } else {
            inventory.pickTearsOfPhoenix();
            this.healthPoint = Item.TearsOfPhoenix.getHealAmount();
            this.magicPoint = Item.TearsOfPhoenix.getHealAmount();

            System.out.println(
                    "      You user tears of phoenix, healing yourself for " + Item.TearsOfPhoenix.getHealAmount() +"," +
                    "\n      You now have " + this.healthPoint + "HP and" + this.magicPoint +"MP" +
                    "\n      You have " + inventory.findTearsOfPhoenix() + " tears of phoenix left. \n");
        }
    }

    /*
    getter, toString()
     */

    public Inventory getInventory() {
        return inventory;
    }

    public Hero getOccupation() {
        return hero;
    }

    public String getName() {
        return hero.getName();
    }

    public boolean isAlive() {
        return isAlive;
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

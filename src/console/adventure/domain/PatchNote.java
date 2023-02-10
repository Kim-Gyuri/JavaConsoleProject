package console.adventure.domain;

import console.adventure.domain.enemy.Enemy;

import java.util.Collections;
import java.util.Stack;

/*
 PATCH NOTE >
 게임 시스템에 적용할 수치값을 업데이트한다.
 Stack<Enemy> enemies : Stack<>에 던전에 있는 모든 몬스터 정보를 넣고, 섞은 후 peek()으로 랜덤 선택되도록 세팅.

  # 던전에서 마주칠 몬스터 랜덤 선택.
  generateMonster() : 던전에 있는 모든 몬스터 정보 세팅
  setMonster() :  던전에서 마주칠 몬스터 랜덤 선택

  # 플레이어/몬스터 세팅에 필요한 수치값을 업데이트
  setPlayerHP() : 플레이어 최대 HP
  setPlayerMP() : 플레이어 최대 MP
  setPlayerBasicAttack() : 플레이어 평타 공격력
  setPlayerUltimate() : 플레이어 궁극기 공격력
  setPlayerUltimateMP() : 플레이어가 궁극기를 사용했을 때, 소모되는 MP.
  setPotionDropChance() : 몬스터가 포션을 가지고 있는지?
  setTearsOfPhoenixDropChance() : 몬스터가 불사조 눈물을 가지고 있는지?

 */
public class PatchNote {

    private static final int MAX_PLAYER_BASIC_ATTACK_DAMAGE = 20;
    private static final int MAX_PLAYER_ULTIMATE_ATTACK_DAMAGE = 40;
    private static final int MINIMUM_DAMAGE = 10;

    private static final int HEALTH_POINT_DROP_CHANCE = 30;
    private static final int TEARS_OF_PHOENIX_DROP_CHANCE = 3;
    private static final int MAX_PERCENT = 100;
    private static final int ONE = 1;
    private static final int ZERO = 0;

    private static final int MAX_PLAYER_HEALTH_POINT = 100;
    private static final int MAX_PLAYER_MAGIC_POINT = 100;


    private Stack<Enemy> enemies;
    private double randomMachine;

    public PatchNote() {
        this.enemies = this.generateMonster();
        this.randomMachine = (int) (Math.random()*MAX_PERCENT + ONE);
    }

    public Enemy setMonster() {
        Collections.shuffle(enemies);
        return this.enemies.peek();
    }

    private Stack<Enemy> generateMonster() {
        Stack<Enemy> enemies = new Stack<>();
        for (Enemy.Monster monster : Enemy.Monster.values()) {
            Enemy enemy = new Enemy(monster, setPotionDropChance(), setTearsOfPhoenixDropChance());
            enemies.push(enemy);
        }
        return enemies;
    }

    public int setPlayerHP() {
        return MAX_PLAYER_HEALTH_POINT;
    }

    public int setPlayerMP() {
        return MAX_PLAYER_MAGIC_POINT;
    }

    public int setPlayerBasicAttack() {
        return (int) (Math.random()*MAX_PLAYER_BASIC_ATTACK_DAMAGE + MINIMUM_DAMAGE);
    }

    public int setPlayerUltimate() {
        return (int) (Math.random()*MAX_PLAYER_ULTIMATE_ATTACK_DAMAGE+ MAX_PLAYER_BASIC_ATTACK_DAMAGE);
    }

    public int setPlayerUltimateMP() {
        return MAX_PLAYER_ULTIMATE_ATTACK_DAMAGE;
    }

    private boolean setPotionDropChance() {
        return ONE <= randomMachine && randomMachine <= HEALTH_POINT_DROP_CHANCE;
    }

    private boolean setTearsOfPhoenixDropChance() {
        return ONE <= randomMachine && randomMachine <= TEARS_OF_PHOENIX_DROP_CHANCE;
    }

    /*
    getter() >
    getMaxHP() : 플레이어 최대 체력
    getMaxMP() : 플레이어 최대 마나
    onePoint() : 수치 1
    zero       : 수치 0

    toString() >
    던전에 있는 모든 몬스터 정보를 출력
     */
    public int getMaxHP() {
        return MAX_PLAYER_HEALTH_POINT;
    }

    public int getMaxMP(){
        return MAX_PLAYER_MAGIC_POINT;
    }

    public int onePoint() {
        return ONE;
    }

    public int zero() {
        return ZERO;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Enemy enemy : enemies) {
            sb.append(enemy.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}

    /*
    public int setEnemyHP() {
        return (int) (Math.random()*MAX_ENEMY_HEALTH_POINT+ MINIMUM_HEALTH_POINT);
    }


    public int setEnemyPower() {
        return (int) (Math.random()*MAX_ENEMY_ATTACK_DAMAGE+ MINIMUM_DAMAGE);
    }
     */

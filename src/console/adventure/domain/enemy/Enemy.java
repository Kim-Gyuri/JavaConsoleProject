package console.adventure.domain.enemy;


public class Enemy {
    private Monster monster;
    private int healthPoint;
    private boolean havePotion;
    private boolean haveTearsOfPhoenix;

    public Enemy(Monster monster, boolean havePotion, boolean haveTearsOfPhoenix) {
        this.monster = monster;
        this.healthPoint = monster.healthPoint;
        this.havePotion = havePotion;
        this.haveTearsOfPhoenix = haveTearsOfPhoenix;
    }

    /*
    Enemy Business Logic >
    damageDealt()
    resetHP() :
     */
    public void damageDealt(int damage) {
        this.healthPoint -= damage;
    }

    public void resetHP() {
        this.healthPoint = this.monster.healthPoint;
    }

    /*
     getter
     toString()
     */
    public int getHealthPoint() {
        return healthPoint;
    }

    public String getName() {
        return monster.name;
    }

    public int getBounty() {
        return monster.bounty;
    }

    public int getPower() {
        return monster.power;
    }

    public boolean isHavePotion() {
        return havePotion;
    }

    public boolean isHaveTearsOfPhoenix() {
        return haveTearsOfPhoenix;
    }

    @Override
    public String toString() {
        return "      [Enemy info]" +
               "\n      name: " + monster.name +
               "    Health Point: " + healthPoint +
               "    power: " + monster.power +
               "    bounty: " + monster.bounty;
    }


    /*
     enum type Monster
     */
    public enum Monster {

        SNAKE("Basilisk",30,10,1000),
        GOBLIN("Goblin",30,10,1000),
        MANTICORE("Manticore",45,17,1100),
        DRAGON("Dragon",50, 25,3000);

        private String name;
        private int healthPoint;
        private int power;
        private int bounty;

        Monster(String name, int healthPoint, int power, int bounty) {
            this.name = name;
            this.healthPoint = healthPoint;
            this.power = power;
            this.bounty = bounty;
        }

        public String getName() {
            return name;
        }

    }
}

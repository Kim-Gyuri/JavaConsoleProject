package console.adventure.domain.player;

public enum Hero {
    ARCHER("ash", "Bow And Arrow", "I'll settle the world with one arrow."),
    WARRIOR("Karen", "Sword", "My sword and heart belong to Demacia!"),
    WIZARD("syndra", "Wand","Infinite power overflows within me!");

    private String name;
    private String weapon;
    private String info;

    Hero(String name, String weapon, String info) {
        this.name = name;
        this.weapon = weapon;
        this.info = info;
    }

    /*
    getter
    toString()
     */
    public String getName() {
        return name;
    }

    public String getWeapon() {
        return weapon;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "      [Hero info]" +
               "\n      Name: " + name +
               "\n      Weapon: " + weapon +
               "\n      " + info;
    }
}

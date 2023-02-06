package console.adventure.domain.item;

public enum Item {

    Potion(30),
    TearsOfPhoenix(100);

    private int healAmount;

    Item(int HealAmount) {
        this.healAmount = HealAmount;
    }

    public int getHealAmount() {
        return healAmount;
    }
}

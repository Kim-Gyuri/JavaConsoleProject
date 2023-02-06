package console.adventure.domain;

import console.adventure.domain.item.Item;

import java.util.SortedMap;
import java.util.TreeMap;

/*
플레이어 가방 > Inventory
가방에는 아이템(Item)을 넣을 수 있다.

SortedMap<Item, Integer> inventory;

# 해당 Item 수량을 확인
findPotion() : 포션 수량 확인
findTearsOfPhoenix() : 불사조 눈물 수량 확인

# 해당 Item 사용
pickPotion()         : 포션을 꺼내 사용
pickTearsOfPhoenix() : 불사조 눈물을 꺼내 사용

# 해당 Item 획득
putPotion() : 포션 획득
putTearsOfPhoenix() : 불사조 눈물 획득
 */
public class Inventory {
    private SortedMap<Item, Integer> inventory;

    public Inventory() {
        inventory = new TreeMap<>();
        inventory.put(Item.Potion, 2);
        inventory.put(Item.TearsOfPhoenix, 0);
    }

    public void pickPotion() {
        int stockQuantity = inventory.get(Item.Potion);
        stockQuantity--;
        inventory.put(Item.Potion, stockQuantity);
    }

    public void pickTearsOfPhoenix() {
        Integer stockQuantity = inventory.get(Item.TearsOfPhoenix);
        stockQuantity--;
        inventory.put(Item.TearsOfPhoenix, stockQuantity);
    }

    public void putPotion() {
        int stockQuantity = inventory.get(Item.Potion);
        stockQuantity++;
        inventory.put(Item.Potion, stockQuantity);
    }

    public void putTearsOfPhoenix() {
        Integer stockQuantity = inventory.get(Item.TearsOfPhoenix);
        stockQuantity++;
        inventory.put(Item.TearsOfPhoenix, stockQuantity);
    }

    public int findPotion() {
        return inventory.get(Item.Potion);
    }

    public int findTearsOfPhoenix() {
        return inventory.get(Item.TearsOfPhoenix);
    }

    public SortedMap<Item, Integer> getInventory() {
        return inventory;
    }


    @Override
    public String toString() {
        return "[number of potions: " + inventory.get(Item.Potion) +
               ", number of phoenix tears: " + inventory.get(Item.TearsOfPhoenix) + "]";
    }
}

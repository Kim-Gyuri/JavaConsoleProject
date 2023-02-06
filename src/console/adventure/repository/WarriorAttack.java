package console.adventure.repository;

import console.adventure.domain.enemy.Enemy;
import console.adventure.domain.player.Player;

/*
WarriorAttack : 전사(플레이어)의 공격 전략
 */
public class WarriorAttack implements AttackRepository{
    @Override
    public void basicAttack(Player player, Enemy enemy) {
        System.out.println("      [Karen]: wields a sword.");
        player.damageDealt(enemy.getPower());
        enemy.damageDealt(player.getBasicAttack());
    }

    @Override
    public void ultimate(Player player, Enemy enemy) {
        System.out.println("      [Karen]: Summon the power of Demasia.");
        player.damageDealt(enemy.getPower());
        player.useMagicPoint();
        enemy.damageDealt(player.getUltimate());
    }
}

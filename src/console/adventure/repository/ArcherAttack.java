package console.adventure.repository;

import console.adventure.domain.enemy.Enemy;
import console.adventure.domain.player.Player;

/*
ArcherAttack : 궁수(플레이어)의 공격 전략
 */
public class ArcherAttack implements AttackRepository {

    @Override
    public void basicAttack(Player player, Enemy enemy) {
        System.out.println("      [ash]: a frost arrow");
        player.damageDealt(enemy.getPower());
        enemy.damageDealt(player.getBasicAttack());
    }

    @Override
    public void ultimate(Player player, Enemy enemy) {
        System.out.println("      [ash]: Magic Crystal Arrow");
        player.damageDealt(enemy.getPower());
        player.useMagicPoint();
        enemy.damageDealt(player.getUltimate());

    }
}

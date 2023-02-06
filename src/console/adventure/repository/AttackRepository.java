package console.adventure.repository;

import console.adventure.domain.enemy.Enemy;
import console.adventure.domain.player.Player;

/*
 # AttackRepository : 공격 전략 인터페이스
 basicAttack() : 평타
 ultimate : 궁극기
 */
public interface AttackRepository {

    public void basicAttack(Player player, Enemy enemy);
    public void ultimate(Player player, Enemy enemy);
}

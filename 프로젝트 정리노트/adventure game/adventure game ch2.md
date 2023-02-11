> 작성 중에 잦은 버퍼링이 생겨서, 나눠서 작성하려고 합니다.

[2. 코드분석](#2-코드분석) <br>
[3. 생각정리](#3-생각정리) <br>
[4. 전체코드](#4-전체코드) <br>
[5. 참고자료](#5-참고자료) <br>

## 2. 코드분석
### 역할과 구현을 분리한다.
```
  PatchNote → GameSystem → View → Main(코드 실행)
```

> PatchNote, GameSystem, View의 역할은 다음과 같다.
+ PatchNote에서 플레이어/몬스터 세팅에 필요한 수치값과 몬스터 랜덤 선택 기능을 업데이트한다.
+ GameSystem에 PatchNote 업데이트 내용을 적용한다.
+ GameSystem은 게임운영에 필요한 Player, Enemy 정보를 관리한다.
+ GameSystem에서 Player 동작 메소드에 대한 예외처리를 추가한다.
+ View는 GameSystem에서 정보를 받아 게임을 실행한다.

 <br> <br>
 
#### PatchNote
 PatchNote에서 플레이어/몬스터 세팅에 필요한 수치값과 몬스터 랜덤 선택 기능을 업데이트한다. <br>
 Math.random()을 통해 확률 퍼센트와 수치값을 처리. <br>
 randomMachine을 만들어, 몬스터의 아이템드랍확률을 계산했다. <br>
```java
     public int setPlayerBasicAttack() {
        return (int) (Math.random()*MAX_PLAYER_BASIC_ATTACK_DAMAGE + MINIMUM_DAMAGE);
    }

    public int setPlayerUltimate() {
        return (int) (Math.random()*MAX_PLAYER_ULTIMATE_ATTACK_DAMAGE+ MAX_PLAYER_BASIC_ATTACK_DAMAGE);
    }
 
     private boolean setPotionDropChance() {
        return ONE <= randomMachine && randomMachine <= HEALTH_POINT_DROP_CHANCE;
    }

    private boolean setTearsOfPhoenixDropChance() {
        return ONE <= randomMachine && randomMachine <= TEARS_OF_PHOENIX_DROP_CHANCE;
    }
 ...
```
 
> `PatchNote 클래스의 메소드` <br> 
>  generateMonster() : 던전에 있는 모든 몬스터 정보 세팅 <br> 
>  setMonster() :  던전에서 마주칠 몬스터 랜덤 선택 기능 <br> 
> <br> 
> setPlayerHP() : 플레이어 최대 HP <br> 
> setPlayerMP() : 플레이어 최대 MP <br> 
> setPlayerBasicAttack() : 플레이어 평타 공격력 <br> 
> setPlayerUltimate() : 플레이어 궁극기 공격력 <br> 
> setPlayerUltimateMP() : 플레이어가 궁극기를 사용했을 때, 소모되는 MP. <br> 
> setPotionDropChance() : 몬스터가 포션을 가지고 있는지? <br> 
> setTearsOfPhoenixDropChance() : 몬스터가 불사조 눈물을 가지고 있는지? <br> 
  
 <br>
 
패치노트에서 업데이트한 플레이어 세팅값을 Player 객체에 넣어 적용했다. <br>
![player 속성값을 패치노트로 적용](https://user-images.githubusercontent.com/57389368/217842102-6d430e71-a22c-4474-a761-aa2d23c7e9cb.png) <br>
> 처음에 해당 코드 부분을 구상했을 때 어려웠다. <br>
> GameSystem classesOfHero()메소드에서 "HashMap<String, Player> 만드는 과정 속에서 patchNote 업데이트를 적용해야 할까?" 고민했다. <br>
> 처음 생각한 방식은 "PatchNote -> GameSystem -> Player"로 흐름상 이상적인 설계처럼 보였다. <br>
> 하지만, (1)방식은 setter() 매개변수가 길어져서 별로였고, <br>
> (1) 과정을 매개변수 1개씩 쪼개서 여러 개의 setter()를 만들면 코드가 지저분해 보였다. <br>
> 그래서 Player 속성값을 PatchNote에서 직접 업데이트했다. 
 
```
 # 처음에 구상했던 것
 
 (1) Player 클래스에 setter()를 만든다.
       public void setter(int healthPoint, int magicPoint, int basicAttack, boolean isAlive, int power, int ultimate) {
        this.healthPoint = healthPoint;
        this.magicPoint = magicPoint;
        this.basicAttack = basicAttack; 
        this.isAlive = isAlive;
        this.power = power;
        this.ultimate = ultimate;    
    }

 (2) GameSystem에서 patchNote
     public void classesOfHero() {
        Player warrior = new Warrior();
        warrior.skillTree(new WarriorAttack());
      ->warrior.setter(patchNote.setPlayerHP(), patchNote.setPlayerMP(), patchNote.setPlayerBasicAttack(), ...);
        
        Player wizard = new Wizard();
        wizard.skillTree(new WizardAttack());
      ->wizard.setter(patchNote.setPlayerHP(), patchNote.setPlayerMP(), patchNote.setPlayerBasicAttack(), ...);

        Player archer = new Archer();
        archer.skillTree(new ArcherAttack());
       ->archer.setter(patchNote.setPlayerHP(), patchNote.setPlayerMP(), patchNote.setPlayerBasicAttack(), ...);
 
        heroes.put(Hero.WARRIOR.getName(), warrior);
        heroes.put(Hero.WIZARD.getName(), wizard);
        heroes.put(Hero.ARCHER.getName(), archer);
    }
``` 
 
 <br><br><br>
 
#### GameSystem
+ GameSystem은 게임 운영에 필요한 것을 세팅한다. <br>
![system은 patchNote를 적용한다](https://user-images.githubusercontent.com/57389368/218248999-9d96f963-b4ef-4cfc-936b-272c3f2034b5.png) <br>
![GameSystem 유저 몬스터 정보 세팅](https://user-images.githubusercontent.com/57389368/218249051-01e3cb76-b377-4b92-9340-05caa56197df.PNG) <br>
> classesOfHero() : 게임 Player Hero 속성을 세팅한다. <br>
> chooseEnemy() : 던전 몬스터 세팅, (PatchNote 몬스터 랜덤 기능을 적용) <br>
> chooseHero : 하고 싶은 캐릭터를 선택한다. <br>

<br><br>

+ Player 동작 메소드(아이템사용, 공격동작)의 예외처리를 GameSystem에서 추가하도록 한다. <br> View에서 GameSystem에서 가공한 메소드로만 적용되도록 한다. <br>
Player 동작 메소드에서 부가적인 것을 빼고, 최대한 역할만 들어가도록 설계했다. <br>

`Player 아이템 사용 메소드` <br>
> Player가 Item을 사용하는 동작 메소드 <br>
![use item](https://user-images.githubusercontent.com/57389368/218249312-bc1dfe5d-2125-4cbe-92e7-7c2c47890f9c.PNG) <br>
> <br>
> GameSystem에서 drinkPotion()에 대한 예외처리를 해준다.  <br>
![player drink potion ](https://user-images.githubusercontent.com/57389368/218247882-36bb0c8d-6d61-4c58-8db5-029c71d12d25.png) <br>
> <br>
> GameSystem에서 useTearsOfPhoenix()에 대한 예외처리를 해준다. <br>
> ![player use tears of phoenix](https://user-images.githubusercontent.com/57389368/218248204-96c83f82-6c32-4ca3-b3fe-fe03618aefaf.png) <br>

<br>

`Player 공격 메소드` <br>
> Player 공격 동작 메소드 <br> 
> ![player 메서드 공격](https://user-images.githubusercontent.com/57389368/218249346-1b8df8e1-51d8-4946-9aa9-f853e0e36463.PNG) <br>
> <br>
> GameSystem에서 basicAttack(),ultimate()에 대한 예외처리를 해준다.  <br>
> ![system code player attack ](https://user-images.githubusercontent.com/57389368/218248611-7d07cff7-5bd4-434b-adea-827d5b7499b0.png) <br>

<br><br> 

+ 예외처리 과정에 필요한 체크포인트 메소드를 만든다.
> checkPlayerHP() : HP 최솟값에서 벗어났는지? <br>
> checkPlayerPower() : 궁극기 사용할 MP가 있는지? <br>
> checkPlayerMP() : MP 최솟값에서 벗어났는지? <br>
> checkPlayerIsAlive() : 던전사냥을 계속하기 위한 Player HP가 남아있는지? <br>
> checkEnemyHP() : 몬스터가 죽었는지? <br>

<br><br> 

+ GameSystem에서 게임 이용자에게 보여줄 view 자료를 만든다.
> guide() : 게임 가이드  <br> 
> thanks() : 게임 종료 시 이미지 <br>
> logo() : 게임 시작 이미지 <br>
> <br>
> getHeroImage(String name) : Hero 선택지에 필요한 이미지 출력  <br>
> getEnemyImage() : 던전 몬스터 출력  <br>
> getHeroAndEnemyImage() : 던전 사냥 중일 때 이미지  <br>
> getAttackImage(String skill) : Hero 공격모션 이미지 출력  <br>
>  <br> 
> checkStatusWindow() : 플레이어와 몬스터의 HP MP를 확인한다.  <br> 
> checkDropRate_bounty() : 몬스터를 물리친 후, 획득한 아이템(물약/불사조눈물) or 현상금을 확인한다.  <br> 
> <br>
> <br>
> `View에 Player, Enemy 객체로 노출하지 않고 접근하기 위한 정보` <br>
> enemyName() : 몬스터 이름, (view에서 몬스터 이름을 key로 사용하기 위해서) <br>
> showEnemyInfo() : 몬스터 정보를 출력한다. <br>
> showPlayerInfo() : 플레이어 정보를 출력한다. <br>

<br><br><br> 

#### View
+ GameSystem과 게임 이용자의 입력에 따른 결과를 출력한다. 
+ while(), switch(), if()을 사용하여 게임 선택지를 구현한다.
+ 최대한 메소드당 역할을 1개만 같도록 extract method()를 많이 하려고 했다.

`게임 이용자가 Hero를 고를 수 있는 선택지` <br>

![view 캐릭터 선택](https://user-images.githubusercontent.com/57389368/217841796-0591165f-805e-44f3-bc98-78093ab2c270.png) <br>

<br><br>

`게임 이용자가 던전사냥 게임과정에서 고를 수 있는 선택지`  <br>

![view 게임 선택지](https://user-images.githubusercontent.com/57389368/218244101-001bb0ee-5a90-48e0-b91c-4962d4f2e1dc.png) <br>
![view 게임 선택지 2](https://user-images.githubusercontent.com/57389368/218244378-a7f25b26-9c53-4614-8f36-71a5a3614817.png) <br>

<br>

> 게임과정 선택지()에 들어가는 세부코드
![view 게임 선택지 one 세부](https://user-images.githubusercontent.com/57389368/218246209-261f298f-e9b0-44aa-a8ca-1c7a4899b594.png) <br>
![view 게임 선택지 2 세부코드](https://user-images.githubusercontent.com/57389368/218247391-fbb451ac-61d3-4654-a9c1-281bd598314b.png)
![view 게임 선택지 three 세부](https://user-images.githubusercontent.com/57389368/218247478-7eb329f4-8612-4bc3-8902-3fe7fcdf8692.png)


<br><br>

#### Main
![Main](https://user-images.githubusercontent.com/57389368/217857016-433d0938-1f8e-4036-a36e-dec722faee88.png) <br>
+ GameSystem을 통해 게임 플레이(Hero선택하여 던전사냥하기)가 가능하도록 했다.
+ GameSystem에서 게임정보를 가져와서, View를 통해 게임 이용자의 입력에 따른 화면을 출력해준다.

## 3. 생각정리
이번 프로젝트를 만들면서 최대한 객체지향 설계를 하고 싶었고, 역할과 구현을 분리해보려고 노력했다. <br>
(사실 좋은 코드인지는 아직도 모르겠지만, 나중에 테스트 코드를 만들면서 더 고민해야 겠다.) <br>

<br>

게임 프로젝트를 하는 과정에서 많은 걸 배운 것같다. <br>
게임 구현에 필요한 객체, 기능, 게임 시스템 구조 만들기, 예외처리 등등 생각할 사항이 많았다. (시간이 가장 많이 걸린 프로젝트일듯...) <br>
프로젝트 보고서를 쓰면서, 프로젝트의 부족한 부분을 계속 생각하게 되니까... 계속 코드를 고치는 과정을 반복하다보니 시간이 많이 걸린 것같다. <br>

`내가 추가한 예외처리` <br>
"아이템 사용" 선택지에서 발생할 수 있는 예외를 처리해야 한다.
+ Player가 "회복 포션"이 없을 때 예외처리
+ Player가 "불사조 눈물"이 없을 때 예외처리
+ 아이템을 사용했을 때, 최대 HP,MP를 초과했는지?

<br>

"기본 공격" 선택지에서 발생할 수 있는 예외를 처리해야 한다.
+ 공격 받았을 때, HP는 0 이하로 내려갈 수 없다.
+ 사냥 도중 플레이어가 죽었을 때 예외처리

<br>

"궁극기 공격" 선택지에서 발생할 수 있는 예외를 처리해야 한다.
+ 공격 받았을 때, HP는 0 이하로 내려갈 수 없다.
+ Player MP는 0이하로 내려갈 수 없다.
+ 궁극기 사용하기 위한 최소 MP값이 있는지?
+ 사냥 도중 플레이어가 죽었을 때 예외처리

<br>

몬스터를 물리쳤을 때 몬스터 HP를 리셋한다.
+ 다음 진행을 위해 몬스터 값(HP)을 리셋해야 한다.

<br>

잘못된 입력에 대한 예외처리
+ 잘못된 입력인 경우, 다시 입력받아야 한다.


## 4. 전체코드
[코드](https://github.com/Kim-Gyuri/JavaConsoleProject/tree/master/src/console/adventure)

## 5. 참고자료
[JAVA 객체지향 디자인패턴 스트래티지 패턴](https://m.blog.naver.com/1ilsang/221119257326) <br>
[enum타입과 리팩토리 과정](https://jojoldu.tistory.com/73) <br>
[일급 컬렉션 사용](https://jojoldu.tistory.com/412)

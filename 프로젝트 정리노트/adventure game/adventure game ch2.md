> 작성 중에 잦은 버퍼링이 생겨서, 나눠서 작성하려고 합니다.

[2. 코드분석](#2-코드분석) <br>
[3. 생각정리](#3-생각정리) <br>
[4. 전체코드](#4-전체코드) <br>
[5. 참고자료](#5-참고자료) <br>

## 2. 코드분석
### 역할과 구현을 분리한다.
```
  PatchNote → GameSystem → View → Main
```
 
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
 
 <br>
 
![player 속성값을 패치노트로 적용](https://user-images.githubusercontent.com/57389368/217842102-6d430e71-a22c-4474-a761-aa2d23c7e9cb.png) <br>
+ 패치노트에서 업데이트한 플레이어 세팅값을 Player 객체에 넣어 적용했다.
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
 
> `PatchNote 클래스의 메소드` <br> 
>  generateMonster() : 던전에 있는 모든 몬스터 정보 세팅 <br> 
>  setMonster() :  던전에서 마주칠 몬스터 랜덤 선택 기능 <br> 
> <br> 
> setPlayerHeart() : 플레이어가 살아있는지? <br> 
> setPlayerHP() : 플레이어 최대 HP <br> 
> setPlayerMP() : 플레이어 최대 MP <br> 
> setPlayerBasicAttack() : 플레이어 평타 공격력 <br> 
> setPlayerUltimate() : 플레이어 궁극기 공격력 <br> 
> setPlayerUltimateMP() : 플레이어가 궁극기를 사용했을 때, 소모되는 MP. <br> 
> setPotionDropChance() : 몬스터가 포션을 가지고 있는지? <br> 
> setTearsOfPhoenixDropChance() : 몬스터가 불사조 눈물을 가지고 있는지? <br> 
 
 <br>
 
#### GameSystem
GameSystem에 PatchNote 몬스터 랜덤 기능을 적용한다.<br>
![system은 patchNote를 적용한다](https://user-images.githubusercontent.com/57389368/217840390-3ee7bb5f-6fdc-48f6-aaf3-3f2a63d5720f.png) <br>
![패치노트 통해 시스템에서 몬스터 뽑기 시스템 코드](https://user-images.githubusercontent.com/57389368/217850897-42b5fb41-98f2-4215-9e1f-0a0a96c69650.png) <br> 

> `GameSystem 클래스의 메소드` <br> 
> classesOfHero() : 게임에 있는 영웅 캐릭터를 세팅한다.  <br> 
> guide() : 게임 가이드  <br> 
>  <br> 
> thanks(), logo(), getImage() : 이미지 출력한다.  <br> 
>  <br> 
> chooseEnemy() : 던전 몬스터 세팅  <br> 
> chooseHero : 하고 싶은 캐릭터를 선택한다.  <br> 
> userInfo() : 캐릭터 정보 출력  <br> 
> checkStatusWindow() : 플레이어와 몬스터의 HP MP를 확인한다.  <br> 
> checkDropRate_bounty() : 몬스터를 물리친 후, 획득한 아이템(물약/불사조눈물) or 현상금을 확인한다.  <br> 

+ GameSystem과 게임 이용자의 입력에 따른 결과를 출력한다. 
+ while(), switch(), if()을 사용하여 게임 선택지를 구현한다.

`게임 이용자가 Hero를 선택한다.` <br>

![view 캐릭터 선택](https://user-images.githubusercontent.com/57389368/217841796-0591165f-805e-44f3-bc98-78093ab2c270.png) <br>

<br>

`게임 이용자가 던전사냥 게임과정에서 고를 수 있는 선택지`  <br>

![view 게임 선택지1](https://user-images.githubusercontent.com/57389368/217852341-f88ed3e5-8d68-4eb5-9894-7af73087ea03.png)
![view 게임 선택지 2](https://user-images.githubusercontent.com/57389368/217852518-c48e4937-f144-4c30-9784-f4488a7ae543.png)

<br><br>

#### Main
![Main](https://user-images.githubusercontent.com/57389368/217857016-433d0938-1f8e-4036-a36e-dec722faee88.png) <br>
+ GameSystem을 통해 게임 플레이(Hero선택하여 던전사냥하기)가 가능하도록 했다.
+ GameSystem에서 게임정보를 가져와서, View를 통해 게임 이용자의 입력에 따른 화면을 출력해준다.

<br><br><br>

### 예외처리
#### 아이템 예외처리
![view 게임 선택지 2 세부코드](https://user-images.githubusercontent.com/57389368/217858107-74a52905-6a61-4d84-98e2-99f270ef6da7.png) <br>

View에서 "아이템 사용" 선택지에서 발생할 수 있는 예외를 처리해야 한다.
+ Player가 "회복 포션"이 있는지?
+ Player가 "불사조 눈물"이 있는지?
+ 아이템을 사용했을 때, 최대 HP,MP를 초과했는지?

<br>

Player의 dinkPotion() 메소드는 다음과 같다. <br>
![player drink potion](https://user-images.githubusercontent.com/57389368/217858798-3cd78311-3485-408b-a148-d55c4c41f8f2.png) <br>
+ 포션이 없는 경우, "포션이 없어서 사용할 수 없다"라는 문구를 출력한다.
+ 포션을 사용했을 때 최대 HP,MP를 초과했을 경우, PatchNote에서 업데이트한 최대 수치값으로 바꿔준다.

<br>

Player의 useTearsOfPhoenix() 메소드는 다음과 같다. <br>
![player use tears of phoenix](https://user-images.githubusercontent.com/57389368/217861481-3ddb8ee5-944e-4d39-a624-fea49374de77.png) <br>
+ 불사조 눈물이 없는 경우, "불사조 눈물이 없어서 사용할 수 없다"라는 문구를 출력한다.

<br><br>

#### Player HP,MP 최솟값 예외처리
![view 선택지 1의 세부코드](https://user-images.githubusercontent.com/57389368/217862967-ff0b9c1e-1bb9-42b8-b2f9-09bd5aa3fb39.png)

View에서 "기본 공격" 선택지에서 발생할 수 있는 예외를 처리해야 한다.
+ 공격 받았을 때, HP는 0 이하로 내려갈 수 없다.
+ 던전사냥 도중 플레이어 HP가 1보다 작을 경우 예외처리
+ 사냥 도중 플레이어가 죽었을 때 예외처리

HP/MP는 최소값 예외처리 
(공격 받았을 때, 0 이하로 내려갈 수 없다.)
(마나를 사용했을 때 0이하로 내려갈 수 없다.)
(사냥 도중 플레이어 HP가 1보다 작을 경우 예외처리)
(사냥 도중 플레이어가 죽었을 때 예외처리)



몬스터를 물리쳤을 때 몬스터 HP를 리셋한다.
(다음 진행을 위해 값을 리셋한다.)

잘못된 입력에 대한 예외처리

 
## 3. 생각정리

## 4. 전체코드
[코드](https://github.com/Kim-Gyuri/JavaConsoleProject/tree/master/src/console/adventure)

## 5. 참고자료
[JAVA 객체지향 디자인패턴 스트래티지 패턴](https://m.blog.naver.com/1ilsang/221119257326) <br>
[enum타입과 리팩토리 과정](https://jojoldu.tistory.com/73) <br>
[일급 컬렉션 사용](https://jojoldu.tistory.com/412)

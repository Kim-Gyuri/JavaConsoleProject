# :pushpin: 자바 콘솔 프로그램 만들기 - adventure game
객체지향을 이해하기 위한 자바 콘솔 게임 만들기 <br> (데이터베이스, JSP를 전혀 사용하지 않고 Java와 객체에 좀 더 집중할 예정) <br><br>
[0. 실행화면](#0-실행화면) <br>
[1. 요구사항](#1-요구사항) <br>
[2. 코드분석](#2-코드분석) <br>
[3. 생각정리](#3-생각정리) <br>
[4. 전체코드](#4-전체코드) <br>
[5. 참고자료](#5-참고자료) <br>

<br><br>

## 0. 실행화면
### Hero 선택화면
![ConsoleProject-–-12023-02-08-16-23-56-_online-video-cutter com_](https://user-images.githubusercontent.com/57389368/217464336-c04c7e40-c8be-4fd0-baa4-e1ba2b5685f8.gif)

### Hero 전사를 선택했을 경우
![전사버전-_online-video-cutter com_-_1_](https://user-images.githubusercontent.com/57389368/217468226-d9fbe419-b9ce-4d35-b68e-2f562b1cf970.gif)

### Hero 마법사를 선택했을 경우
![마법사-_online-video-cutter com_](https://user-images.githubusercontent.com/57389368/217495729-eb06d10a-36cf-4f17-b3ba-49f338bcb9d7.gif)

### Hero 궁수를 선택했을 경우
![궁수-_online-video-cutter com_-_1_](https://user-images.githubusercontent.com/57389368/217503733-0b23a322-eaa0-47ce-a6e5-58914cb60850.gif)


## 1. 요구사항
던전 사냥게임을 만들어 보자. <br> <br>
`기능 요구 사항` <br>
+ 게임 이용자는 "전사" "마법사" "궁수" 중에서 하나를 고를 수 있다.
+ 플레이어는 속성(HP, MP, 기본공격, 궁극기, 돈, 인벤토리)을 가진다.
+ 인벤토리에 아이템(회복포션, 불사조 눈물 등)을 넣을 수 있다.
+ 던전에는 여러 종류의 몬스터가 존재한다.
+ 플레이어는 몬스터와 싸울 수 있다.
+ 해당 몬스터를 물리쳤을 때, 현상금을 획득할 수 있다.
+ 해당 몬스터를 물리쳤을 때, 확률에 따라 아이템을 획득할 수 있다. 
+ 상황에 맞게 그림도 같이 출력한다.

<br>

`프로그램 요구사항` <br>
+ 입력  : Scanner 사용
+ 객체의 속성과 행위가 우선순위인 설계
+ 추상 클래스로 플레이어를 만들고, 추상 클래스를 상속받은 "전사" "마법사" "궁수" 객체를 만든다.
+ 추상 클래스(플레이어)에 공격 전략 인터페이스를 연결하고, 해당 인터페이스들의 아래에 "전사" "마법사" "궁수" 공격 전략을 구현한다.
+ 패치노트 클래스를 만들어, 해당 패치노트에서 플레이어/몬스터 세팅에 필요한 수치값을 업데이트한다.
+ 게임시스템 클래스를 통해 패치노트를 사용할 수 있도록 한다.
+ 일급 컬렉션 사용

## 2. 코드분석
`전체적인 구상도` <br>
![전체적 구상도](https://user-images.githubusercontent.com/57389368/217026676-c0569bdc-c4bd-4120-bc02-65b670a97535.png) 

 <br> <br> 
 
### 주요 객체 
+ 플레이어
+ 몬스터
+ 인벤토리
+ 아이템
+ 게임 패치노트
+ 게임 시스템

### 주요 객체들의 속성과 역할
+ 플레이어
  + Hero 속성으로 "마법사" "전사" "궁수"가 있다.
  + 몬스터에게 공격(기본공격/궁극기) 한다.
  + 몬스터에게서 현상금을 얻다.
  + 몬스터에게서 도망가다.
  + 인벤토리에서 아이템을 꺼낸다.
  + 인벤토리에 아이템을 넣는다.
  + 아이템을 사용한다.

+ 몬스터
  + 플레이어를 공격한다.
  + 아이템을 가질 수 있다.
 
+ 인벤토리
  + 아이템 수량 확인
  + 아이템 저장/꺼내기 

+ 아이템
  + 회복포션, 불사조 눈물이 포함된다.

+ 게임 패치노트 
  + 몬스터의 아이템드랍 확률 업데이트
  + 던전에서 마주칠 몬스터 랜덤 선택기능 업데이트
  + 플레이어 최대 HP,MP 수치 업데이트
  + 플레이어 기본 공격 수치 업데이트
  + 플레이어 궁극기 공격 수치 업데이트
  + 플레이어가 궁극기를 사용했을 때 소모되는 MP 업데이트
 
+ 게임 시스템
  + 던전에 존재하는 몬스터 세팅
  + 플레이어 Hero 속성 세팅
  + 플레이어 Hero 선택기능 세팅
  + 플레이어 정보 출력
  + 플레이어와 몬스터의 HP,MP를 확인한다.
  + 몬스터를 물리친 후, 획득한 아이템(포션/불사조눈물) or 현상금을 확인한다.
  + 게임 가이드
  + 게임 화면 세팅

<br>

### 추상화
> 위의 "객체들의 속성과 역할"을 도식화 하면 아래와 같다.

![어드벤처 게임 - 추상화된 코드구현](https://user-images.githubusercontent.com/57389368/217448840-f278e650-5e00-41dd-883c-df1828dfa0fd.png) <br>

<br><br> <br>

### 객체지향 디자인패턴
'전사' '마법사' '궁수' 객체에 따른 공격전략을 OCP 법칙에 맞게 설계해보자. <br> 객체의 공격 행동을 스트래티지 패턴으로 구현해보자. <br>
인터페이스를 활용하여 변화에 유연한 캡슐화 처리를 해주기 때문에, 전략에 따라 쉽게 바꿀 수 있다. <br>

`객체 관계도` <br>
![플레이어 설계](https://user-images.githubusercontent.com/57389368/217029556-ab5ef066-adaa-4bd3-849b-cfefeee839b6.png) <br> <br>
![객체 관계 22](https://user-images.githubusercontent.com/57389368/217033350-0c40bac1-7dfd-42c1-aec8-a93b8b6faa60.png) <br>

<br><br> <br>

공격 전략을 인터페이스로 캡슐화 해주면 된다. 
 
+ 공격전략 인터페이스 <br>
![객체지향 인터페이스](https://user-images.githubusercontent.com/57389368/217244904-ab42052e-5d51-42b5-873c-2f4f6927db0d.png)

+ 공격전략 구현체 <br>
![마법사 스킬트리](https://user-images.githubusercontent.com/57389368/217243267-c9087e5d-39fc-45d4-a77b-fa896ca83cfc.png) <br>
![전사 스킬트리](https://user-images.githubusercontent.com/57389368/217243401-87df92bd-527f-49c5-85a2-0c7c08cb22bd.png) <br>
![궁수 스킬트리](https://user-images.githubusercontent.com/57389368/217244048-8fabe97f-e947-4615-bb38-c533a3993be3.png) <br>

<br>

+ 부모 클래스 <br>
![부모객체 생성자](https://user-images.githubusercontent.com/57389368/217238924-3fea7174-4f7e-4ca1-bb27-acbab8d20a77.png) <br>
![player 객체지향](https://user-images.githubusercontent.com/57389368/217249471-6aa4e22c-42f7-41a9-aaf6-56c952cce0a0.png)

<br>

+ 자식 클래스 <br>
![마법사 객체](https://user-images.githubusercontent.com/57389368/217250561-1b437b2b-ab1e-4ee0-8d44-69c3af9d0d4c.png) <br>
![궁수 객체](https://user-images.githubusercontent.com/57389368/217250663-b4a7cec5-5b3d-4f5b-8075-d524f1b33607.png) <br>
![전사 객체](https://user-images.githubusercontent.com/57389368/217250731-3150bc8a-7ac0-4716-bb81-4d22a5d1436f.png) <br>
 

우리는 다음 조건을 만족해야 한다. <br>
```
 게임 이용자는 "전사" "궁수" "마법사" 중에서 하나를 선택할 수 있다.
 "전사" "궁수" "마법사"는 자신만의 공격전략(기본공격, 궁극기)을 가졌다.
```

player 클래스에 공격전략 인터페이스를 연결하고, 해당 인터페이스들의 아래에 "전사" "궁수" "마법사" 각각의 여러 전략들을 기술해 놓는다. <br>
이렇게 되면 player 코드는 변화하지 않고 상황에 따라 '전사 스킬트리' '마법사 스킬트리' '궁수 스킬트리' 를 바꿔 적용할 수있다. <br>

<br>

코드가 길어보이지만 부가적인 코드들이 조금 있을 뿐이지 전체적인 로직을 쉽게 이해할 수 있다. <br>
중요한 포인트는 이제 신규 챔피언의 스킬트리를 업데이트 해야 하는 상황이 생겨도, 기존의 코드에는 아무런 영향을 주지 않는다. <br>
신규 챔피언을 업데이트할 때도, 해당 챔피언의 스킬트리 구현체를 만들어 적용하면 된다. <br>

<br>

또한 여러 클래스가 생성되어 적용된 상태에서 "궁수 스킬트리"전략의 내용이 바뀌어도, 해당 클래스들은 문제가 없다. <br>
궁수 스킬트리 구현체만 바꿔주면, 궁수 스킬트리를 쓰는 모든 클래스가 바뀐다. <br>

<br>

게임에서 한 캐릭터가 여러 스킬, 무기 등을 사용하며 계속적인 전략 변화가 필요할 때, 이 스트래티지 패턴을 사용하면 고통없이 작업할 수 있다. <br>

<br><br>

이제 객체에 맞게 구현체를 연결하여 사용하면 된다. <br>
![객체지향 걸기](https://user-images.githubusercontent.com/57389368/217253027-4cdce536-6804-4e25-8070-e00aac66df7b.png) <br>
![어드벤처 게임 - 패턴 실행문](https://user-images.githubusercontent.com/57389368/217260629-a4cbdada-deb2-46ba-980a-19eca7b21988.png)

> GameSystem 클래스 안에서 "전사" "마법사" "궁수" 객체를 만들었다. <br> GameSystem을 통해서만 게임세팅이 되도록 설계하였다.

<br><br>

공격 속성 제외한, 게임에 필요한 플레이어의 공통 기본동작을 부모 클래스에 정의해둔다. <br>
![어드벤처 게임 _ 부모 메서드](https://user-images.githubusercontent.com/57389368/217255591-d6df38f0-f863-4011-9507-2ee2134cdf6d.png)

>earned() :현상금을 획득. <br>
>    damageDealt() :몬스터에게 공격을 받다. <br>
>    useMagicPoint() :궁극기 사용 중 발생하는 마나소모.<br>
>    run_away() :몬스터에게서 도망가다.<br>
>    drinkPotion() :회복포션을 마시다.<br>
>    useTearsOfPhoenix() :불사조 눈물을 사용하다. <br>

<br><br> <br>

### enum 타입을 활용
![player는 hero 속성과 인벤토리를 가진다](https://user-images.githubusercontent.com/57389368/217800376-c3d24476-2d6d-4ff4-a786-a4a35f0c95f8.png) <br>
![Hero 코드](https://user-images.githubusercontent.com/57389368/217801154-397eaa34-d7a8-4b41-af25-f1df51573243.png)

> Player에 사용되는 Hero를 enum을 사용하여 만들었다. Player외에는 사용되는 곳이 없기에 inner type으로 선언하려고 했지만,  <br>
> Player 코드가 너무 길어질 것 같아서 player 패키지로 묶어두어 만들었다. <br>

Hero 속성으로 마법사/ 궁수/전사 에 대한 정보를 정의했다.  <br>
3가지 종류 Hero의 정보로 들어가는  String name, String weapon, String info로 선언된 부분을 전부 enum에 담았다.  <br>
이로 인해 부모 클래스인 Player 생성자 코드가 훨씬 간결해졌다.  <br>
자식 클래스(ARCHER, WARRIOR, WIZARD)는 어떤 Hero 값을 사용할지 넣기만 하면 된다.   <br>

<br><br>

![Enemy](https://user-images.githubusercontent.com/57389368/217801669-c5cccc99-1ac2-4bd8-8c21-768c3f797a3a.png) <br>
![Enemy의 몬스터 타입](https://user-images.githubusercontent.com/57389368/217801803-b2af9d1c-3fea-4b96-8387-c04f4c071528.png) <br>

 또한, 던전에 존재하는 몬스터 정보를 enum타입으로 정의했다. <br>
 던전에  존재하는 Monster의 정보로 들어가는 String name, int healthPoint, int power, int bounty로 선언된 부분을 전부 enum에 담았다. <br>
 이로 인해 Enemy 클래스의 생성자 코드가 훨씬 간결해졌다. <br>

> Enemy의 기본로직으로 damageDealt(), resetHp()를 넣었다. <br>
> damageDealt() : 플레이어와 전투 중 입은 피해 <br>
> resetHp() : 전투에서 죽은 후, 다음 진행을 위해 HP를 리셋한다. <br>
> 처음에는 "Enemy가 damageDealt(), resetHp() 로직을 갖고 있는데 역할에 맞지 않은 설계가 아닐까?" <br>
> "두 로직을 GameSystem 클래스에 정의해두고 사용해야  맞지 않을까?"라고 생각했었다. <br>
> 고민끝에 두 로직은 setter() 같은 역할 메서드이니까, Enemy에 정의해두고, system에서만 사용하도록 설계하자고 정리했다. 

<br>

![Item code](https://user-images.githubusercontent.com/57389368/217802531-abd785f9-401b-45e6-95ac-3bf0da52d0cc.png) <br>

 던전에서 얻을 수 있는 회복 아이템 정보를 enum타입으로 정의했다. <br>
 아이템 정보로 들어가는 int healAmount로 선언된 부분을 enum에 담았다. <br>
 이제 아이템을 명시적으로 Potion(회복포션), TearsOfPhoenix(불사조눈물) 단위로 사용할 수 있게 되었다. <br>

 <br> <br>
 
 ### 일급컬렉션 사용
 Collection을 Wrapping하면서, 그 외 다른 멤버 변수가 없는 상태를 일급 컬렉션이라 한다. <br> Wrapping 함으로써 객체지향적으로 설계해보자.

 `Map<>` <br>
 
 ![인벤토리](https://user-images.githubusercontent.com/57389368/217801465-fa2a94b6-9128-4f35-ae88-352f64b65f84.png) <br>
  "Player는 Inventory를 가진다."라고 정의했다. <br>
  Inventory는 Item을 담을 수 있도록, Map<Item, Integer>로 정의해 Item 수량 정보를 담았다. <br>
  Map에서 get+put를 사용하면, 해당 Item을 조회하여 처리한다는 뜻이 되기도해서 적절한 것 같다.  <br>
  
> Inventory에서 기본로직 put(),pick(),find()를 만들었고, Player가 Inventory를 사용할 수 있도록 했다. <br>
> findPotion() : 포션 수량 확인, findTearsOfPhoenix() : 불사조 눈물 수량 확인 <br>
> pickPotion() : 포션을 꺼내 사용, pickTearsOfPhoenix() : 불사조 눈물을 꺼내 사용 <br>
> putPotion() : 포션 획득, putTearsOfPhoenix() : 불사조 눈물 획득

 <br>
 
 ![게임시스템](https://user-images.githubusercontent.com/57389368/217803407-b9279668-9927-45e6-9e71-138f68f4589c.png) <br>
 GameSystem 클래스도 마찬가지로 HashMap<String, Player>로 정의해 게임 속 "전사" "궁수" "마법사" 객체를 담았다.  <br>
 게임을 시작했을 때, 게임 이용자가 선택한 Hero.name을 key로 사용하여 Map에서 해당 객체를 꺼낸다. <br>
 
 <br><br>
 
 `Stack<>` <br>
 
 ![패치노트](https://user-images.githubusercontent.com/57389368/217804604-65f50796-fd51-4381-bf53-f06fed55c069.png) <br>
 
 patchNote에서 던전에 존재하는 몬스터 정보 업데이트를 할 수 있다고 가정했었다. <br>
 patchNote에 Stack<Enemy> 컬렉션을 wrapping해보자. <br>
 던전에서 만날 수 있는 몬스터를 랜덤 뽑기 시스템을 통해 이루어지도록 설계했다. <br>
 매번 랜덤하게 몬스터를 뽑는 것이 포인트다. <br>

 <br>
 
![enum values()를 사용하여, Enemy Monster 정보를](https://user-images.githubusercontent.com/57389368/217805051-b078c010-3689-441b-acb3-382f95f9ae45.png) <br>

 enum.values()를 사용하여, Enemy.Monster 정보를 모두 꺼내 Enemy 객체를 생성할 때 넣는다. <br>
 이때 pathchNote에서 얻은 정보(몬스터가 포션을 가졌는지? 불사조눈물을 가졌는지?)를 같이 넣어준다. <br>
 Stack<>에 생성한 Enemy를 넣는다. <br>
 
 Stack<Enemy>를 생성하는 시점에서 Stack 리스트를 랜덤정렬하였다. <br>
 이렇게 하면 이미 랜덤정렬이 된 리스트 안에서 맨위부터 차례로 뽑기만 해도 된다. <br>

+  Collections.shuffle()를 추가하여 랜덤정렬.
+ peek()을 통해 뽑도록 한다.
 
>  Stack의 peek메소드를 사용하면 값을 제거하지 않으면서 값을 확인할 수 있다,  <br> 
> 그래서 리스트 안에서 계속 데이터를 추첨할 수 있다. <br> 이렇게 코드를 짜면, 의도했던 대로 '던전에서 랜덤픽으로 몬스터를 발견'할 수 있다.
 
<br><br><br>
 
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

<br>
 
#### View 
GameSystem과 게임 이용자의 입력에 따른 결과를 출력한다. <br>
![view 캐릭터 선택](https://user-images.githubusercontent.com/57389368/217841796-0591165f-805e-44f3-bc98-78093ab2c270.png) <br>
+ while(), switch(), if()을 사용하여 게임 선택지를 구현한다.

![view 게임 선택지1](https://user-images.githubusercontent.com/57389368/217852341-f88ed3e5-8d68-4eb5-9894-7af73087ea03.png)
![view 게임 선택지 2](https://user-images.githubusercontent.com/57389368/217852518-c48e4937-f144-4c30-9784-f4488a7ae543.png)

 
## 3. 생각정리

## 4. 전체코드
[코드](https://github.com/Kim-Gyuri/JavaConsoleProject/tree/master/src/console/adventure)

## 5. 참고자료
[JAVA 객체지향 디자인패턴 스트래티지 패턴](https://m.blog.naver.com/1ilsang/221119257326) <br>
[enum타입과 리팩토리 과정](https://jojoldu.tistory.com/73) <br>
[일급 컬렉션 사용](https://jojoldu.tistory.com/412)
 


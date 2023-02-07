# :pushpin: 자바 콘솔 프로그램 만들기 - adventure game
객체지향을 이해하기 위한 자바 콘솔 게임 만들기 <br><br>
[1. 요구사항](#1-요구사항) <br>
[2. 코드분석](#2-코드분석) <br>
[3. 생각정리](#3-생각정리) <br>
[4. 전체코드](#4-전체코드) <br>
[5. 참고자료](#5-참고자료) <br>

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

 <br> <br> <br> <br>
 
`객체 관계도` <br>
![플레이어 설계](https://user-images.githubusercontent.com/57389368/217029556-ab5ef066-adaa-4bd3-849b-cfefeee839b6.png) <br> <br>
![객체 관계 22](https://user-images.githubusercontent.com/57389368/217033350-0c40bac1-7dfd-42c1-aec8-a93b8b6faa60.png) <br>

<br>

### 객체지향 디자인패턴
'전사' '마법사' '궁수' 객체에 따른 공격전략을 OCP 법칙에 맞게 설계해보자. <br> 객체의 공격 행동을 스트래티지 패턴으로 구현해보자. <br>
인터페이스를 활용하여 변화에 유연한 캡슐화 처리를 해주기 때문에, 전략에 따라 쉽게 바꿀 수 있다. <br>

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


## 3. 생각정리

## 4. 전체코드
[코드](https://github.com/Kim-Gyuri/JavaConsoleProject/tree/master/src/console/adventure)

## 5. 참고자료
[JAVA 객체지향 디자인패턴 스트래티지 패턴](https://m.blog.naver.com/1ilsang/221119257326) <br>

> 작성 중에 잦은 버퍼링이 생겨서, 나눠서 작성하려고 합니다.

[2. 코드분석](#2-코드분석) <br>
[3. 생각정리](#3-생각정리) <br>
[4. 전체코드](#4-전체코드) <br>
[5. 참고자료](#5-참고자료) <br>

## 2. 코드분석
### 역할과 구현을 분리한다.
#### View 
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

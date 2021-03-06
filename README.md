# :pushpin: 자바 콘솔 프로그램 만들기
## Table of contents 목차
[1. 로또 게임](#1-로또-게임)<br>
[2. 계산기](#2-계산기)<br>
[3. 학교 관리 시스템](#3-학교-관리-시스템)<br>
[4. 학생 관리 시스템](#4-학생-관리-시스템)<br>
[5. 콘솔 어드벤처 게임](#5-콘솔-어드벤처-게임)<br>


## 1. 로또 게임
### 로또 구입 시스템
+ 기능 요구 사항
  + 구입할 로또 개수를 입력 -> 랜덤으로 고른 로또를 받는다.

+ 프로그램 요구사항
  + 입력  :Scanner 사용
  + 메소드 호출  :start() -->객체를 생성하고 참조변수 lotto를 이용해서 메서드(매개값)형태로 호출한다.
  + 메서드 생성 : lottoNum() -> Random(), for() 
+ 출력화면 <br>
![로또버전1캡처](https://user-images.githubusercontent.com/57389368/152634899-8ec651e2-2e6b-4ef7-8fdb-a8b14dcac329.JPG)
 <br> <br>
-------------------

### 로또 번호 추첨 시스템 만들기
+ 기능 요구 사항
  + 로또 번호 추첨 (보너스볼까지) 발표한다.

+ 프로그램 요구사항
  + 외부 클래스 메서드 호출
  + 로또 자동 생성은 Collections.shuffle() 메소드 활용한다.
  + ArrayList의 contains() 메소드를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.
+ 출력화면 <br>
![로또버전2캡처](https://user-images.githubusercontent.com/57389368/152634923-aa962536-3cc5-42f2-a183-9f856c24015c.JPG)
 <br> <br>
-------------------
### 로또 구매 + 번호 추첨 + 상금확인 시스템 만들기
+ 기능 요구 사항
  + 구입할 로또 장수를 입력하면, 랜덤으로 생성된 N개의 로또를 발급받는다.
  +  로또 번호의 범위는 1~45이다.
  +  각 로또의 번호 개수는 6개이다.
  +  발급된 N개의 로또 번호들을 모두 출력한다.
  +  이번 주 당첨 번호는 랜덤으로 생성된다.
  +  당첨 계산을 한다. 
    + 일치된 개수를 센다.
    + 총 상금을 계산한다.
  
+ 프로그램 요구사항
  +  각 로직에 대한 테스트 코드를 작성한다.
  +  로또 숫자를 객체로 만든다.
  +  일급컬렉션을 알아보자.
  +  메소드 분리 + 클래스 분리
  

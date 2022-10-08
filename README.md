# 자동차 경주 게임

## 진행 방법

* 자동차 경주 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

## 과제 제출 과정

* [과제 제출 방법](https://github.com/next-step/nextstep-docs/tree/master/precourse)

## 기능 요구 사항

주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.

## 기능 구현 목록 TODO-list

### Domain Models

#### Car

[x] Client는, 5자 이하의 자동차 이름을 부여하여 자동차 개체를 생성할 수 있다.

- [x] 주어진 자동차 이름이 Null일경우, ERROR문구와 함께 IAE가 발생한다.
- [x] 주어진 자동차 이름이 Empty일경우, ERROR문구와 함께 IAE가 발생한다.
- [x] 주어진 자동차 이름이 5자 이상일경우, ERROR문구와 함께 IAE가 발생한다.

[x] Client는, 다음의 조건을 통해, 1대의 자동차를 한칸 전진 또는 멈출 수 있다.

- [x] 자동차 전진 조건 : [0..9] 사이의 random값을 구한 후, random값이 4이상일경우 전진, 3이하의 값이면 멈춘다.
- [x] 또한, 테스트를 위해 랜덤값을 생성하는 개체를 자동차 개체 생성시 입력 받을 수 있다.
- [x] 이때, random값이 [0..9] 사이가 아니면 ERROR문구와 함께 IAE가 발생한다.

#### Cars (1급컬렉션)

[] Client는, 주어진 자동차 이름들을 통해, 해당 이름이 주어진 자동차 개체들을 생성 할 수 있다.

[] Client는, n번 횟수만큼, 생성된 자동차 개체들을 전진 또는 멈출 수 있다.

[] Client는, n번 횟수만큼, 생성된 자동차 개체들이 전진 또는 멈춘후, 가장 멀리 전진한 차들을 반환받을 수 있다.

### Contoller

[] InputView로부터 입력 받은 이름들을 통해, 해당 이름의 자동차 개체 들을 생성 할 수 있다.

[] InputView로부터 입력 받은 플레이 횟수만큼, 자동차 경주게임을 플레이 할 수 있다.

[] 자동차경주게임가 끝나면, 가장 멀리 전진한 차들을 우승자로서 OutPutView에 전달 할 수 있다.

### View

#### InputView

[] 사용자로부터, 경주 할 자동차 이름을, 쉼표 기준으로 입력받을 수 있다.

- [] 이때, 사용자가 잘못된 값을 입력할 경우, IllegalArgumentException를 발생시키며, [ERROR]로 시작하는 에러메시지를 출력 할 수 있다.
- [] 또한, 잘못된 부분부터 입력을 다시 받을 수 있다.

[] 사용자로부터, 몇번의 이동을 시도 할 것인지 입력받을 수 있다.

- [] 이때, 사용자가 잘못된 값을 입력할 경우, IllegalArgumentException를 발생시키며, [ERROR]로 시작하는 에러메시지를 출력 할 수 있다.
- [] 또한, 잘못된 부분부터 입력을 다시 받을 수 있다.

#### OutputView

[] 각 회수별, 각 자동차의 전진 실행 상태를 이름과 함께 출력할 수 있다.

[] 자동차 경주 게임이 끝나면, 우승자를 출력할 수 있다.

- [] 우승자가 다수 존재할경우, 쉼표로 이름 구분해 출력.

### Others

[] [ERROR] Exception Message Prefix 한군데로 모으기

[] Custom Exception to recover?

## NOTE (What I learned so far)

### Review : IAE vs ISE

- IAE : Invalid 인자값
- ISE : Invalid 타이밍에 메소드 호출

### Review : Role of @FunctionalInterface

- @FunctionalInterface 어노테이션은 함수형 인터페이스 부합하는지를 확인하기 위한 어노테이션 타입
- 둘 이상의 추상 메서드가 존재한다면, 이는 컴파일 오류를 발생
- static이나 default 선언이 붙은 메서드의 경우 추상메서드가 아니기에 카운트 하지 않는다

### Review : Condition of First Class Citizen

First Class Citizen 은 아래의 속성들을 모주 만족해야 한다.

1. 변수에 값을 할당할 수 있어야 한다
2. 메서드의 인자로로 넘겨줄 수 있어야 한다
3. 메서드의 반환값이 될 수 있어야 한다 메서드는, 위 조건의 모두를 만족하지 않으므로 일급객체가 아니다. 하지만, Functional Object를 이용하여, 유사한 동작은
   흉내 가능하다

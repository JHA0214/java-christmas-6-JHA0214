## 기능 목록

- 사용자에게 입력 받은 주문을 저장한다. <OrderManager>
  - 사용자의 방문 날짜를 저장한다.
  - 입력 받은 주문을 정리하여 저장한다.
  - 할인 내역과 총주문 금액 등 주문에 대한 데이터들을 저장한다.

- 사용자가. 받을 수 있는 할인 혜택을 확인한다. <PromotionManager>
  - 디데이 할인 계산
  - 평일 할인 계산
  - 주말 할인 계산
  - 특별 할인 계산
  - 증정 이벤트 계산

- 메뉴판 클래스 <MenuManager>
  - 각 메뉴의 가격들을 저장하고 있다.
  - 사용자가 입력한 주문 중 메뉴와 관련된 예외처리를 한다. (없는 메뉴 주문, 음료만 주문)

- 사용자의 입력을 관리한다. <UserInputManager>
  - 사용자의 입력 값을 확인하여 예외처리 한다.
  - 숫자 입력 상황에서 문자 입력시 예외처리 한다.
  - 주문수가 1보다 작은 상환, 중복 주문 입력, 총주문 수가 20이상일 경우 예외처리 한다.
  - 입력받은 값을 필요한 곳에 반환한다.

- 이벤트 달력 <EventCalendar>
  - 주말과 평일, 증정이벤트 날짜가 입력된 달력이 저장되어있다.
  - 사용자가 입력한 날짜를 전달받으먼 그 날짜에 대한 이벤트 정보를 반환한다.

- 전체 결과 출력 <PromotionView>
  - 주문에 대한 결과를 출력한다.

- 프로그램의 전체 로직을 관리한다. <ChristmasPromotion>
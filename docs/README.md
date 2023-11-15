## 우아한 테크코스 프리코스 크리스마스 프로모션

### 진행방식
- 시작 메시지 출력
- 방문 날짜 입력
- 주문 메뉴와 개수 입력
- 입력 요일 이벤트 메시지 출력
- 이벤트 혜택 출력
  - 주문 메뉴
  - 할인 전 총 주문 금액
  - 증정 메뉴 여부
  - 혜택 내역 여부
  - 할인 후 예상 결제 금액
  - 12월 이벤트 배지 여부

### 주요 구현 사항
- [x] 입력 뷰 구현
- [x] 출력 뷰 구현

- [x] 날짜 입력 및 저장 객체 구현
- [x] 날짜 입력 시 날짜관련 해당 이벤트 항목 저장
  - [x] 크리스마스 디데이 총 금액 할인 항목
  - [x] 평일/주말 구분 할인 타입 항복
  - [x] 날짜나 달력의 별에 따라 스페셜 할인 항목
- [x] 날짜 관련 이벤트 반환 DTO 반환

- [x] 주문 메뉴와 개수 입력 및 저장 객체 구현
  - [x] 각 메뉴를 기본 금액과 메뉴 명으로 Enum 으로 관리
  - [x] 주문한 메뉴 입력에 대한 유효성 검사 진행 후 저장 객체에 저장
- [x] 주문 메뉴 내역 반환 DTO 반환

- [x] 날짜 이벤트와 주문 메뉴 DTO 를 통한 이벤트 혜택 적용
- [x] 이벤트 적용 DTO 반환 및 출력
  - [x] 할인 전 금액을 출력
  - [x] 할인 전 금액에 따른 증정 여부 출력
    - 증정 여부가 없다면 '없음' 출력
  - [x] 혜택 내역 출력
    - 적용된 혜택 없다면 '없음' 출력
  - [x] 혜택 금액 출력
  - [x] 증정 혜택 금액을 제외한 할인 후 예상 금액 출력
  - [x] 총 혜택 금액에 따른 이벤트 배지 부여 여부 출력
    - 이벤트 배지가 없다면 '없음' 출력

- 추가 구현 사항
  - 이벤트 결과 도메인의 정보를 출력 문자열으로 변경하여 Builder DTO 생성/반환
  - 잘못된 입력을 통한 과정은 예외 메시지 출력 및 재입력
  - 총 주문 메뉴 금액이 10,000원 이하일 경우 모든 할인 금액 0으로 지정

### 예외 처리
- 날짜 입력 예외
  - [x] 잘못된 날짜 입력의 경우
  - [x] 날짜가 정수형으로 변경이 안될 때
- 메뉴 입력 예외
  - [x] 메뉴 입력 시 정규표현식 패턴이 다를 경우 (TextProcessor 처리)
  - [x] 중복 메뉴명 입력의 경우 (TextProcessor 처리)
  - [x] 없는 메뉴명 입력의 경우
  - [x] 주문 메뉴의 개수가 1보다 작을 때
  - [x] 주문 메뉴가 20개를 넘을 때
  - [x] 음료만 주문 시 주문 불가능

### 클래스 구조
- InputView : 화면 입력을 담당하는 뷰
  - InputViewMessage : 입력을 위한 메시지를 관리하는 Enum
- OutputView : 화면 출력을 담당하는 뷰
  - OutputViewMessage : 출력을 위한 메시지를 관리하는 Enum
- PromotionRun : 뷰와 컨트롤러를 연결하는 클래스로 요청 전달
- PromotionController : 비즈니스 로직을 실행하기 위해 요청을 전달하는 컨트롤러
- PromotionService : 프로모션 유효성 검증을 거친 객체 생성과 비즈니스 로직을 실행할 서비스
- TextProcessor : 입력받은 문자열을 특정 타입으로 반환하기 위한 서비스
- OrderMenus : 주문 메뉴와 개수 저장하는 클래스
  - MenuItem : 메뉴 이름과 가격, 메뉴 종류를 저장하는 Enum
  - MenuCategory : 메뉴 종류를 구분하는 Enum
- ReservationDateEvent : 날짜 관련 이벤트 저장 클래스
  - ReservationDate : 입력된 날짜를 저장하기 위한 클래스
  - ChristmasDiscount : 크리스마스 D-day 할인 관리 클래스
  - WeekDiscountType : 주말 할인, 평일 할인을 구분하는 Enum
  - SpecialDayDiscount : 특별 할인 일자가 저장되어 있는 Enum
- EventResult : 적용 중인 모든 이벤트를 저장하는 클래스
  - DiscountDetail : 주문 금액과 할인, 예상 금액 등을 보관한 클래스
  - GiftEvent : 증정 상품 여부를 관리하는 Enum
  - RewardBadge : 이벤트 배지를 관리하는 Enum
- PromotionException : 지정된 Enum 예외 메시지를 통해 값를 담아 IllegalArgumentException 생성
  - ExceptionMessage : 예외 발생 메시지를 보관한 Enum Class
- PromotionConverter : Service 에서 Domain to DTO 컨버터
- EventResultTextFactory : EventResultDTO 반환을 위한 문자열 조작 서비스
  - EventResultText : EventResultDTO 에 필요한 문자열 Enum
- 테스트 : 클래스별 메소드 단위 테스트 진행

### 이번에 프로젝트를 진행하면서 고민한 요소
- 해당 기능을 구현하기 위한 구조 설계
  - 이후 필요한 클래스들을 추가하며 설계 변경
- 동일한 입력과 출력을 하는 뷰는 싱글톤으로 구현
- 입력에 대한 유효성 검사와 객체 생성에 대한 유효성 검사 구분에 대한 고민
- 이벤트 적용 시 이벤트 내역과 주문 내역을 사용해 값을 저장하는 방법을 Service 에서 진행할지 객체 내부에서 진행할지 고민
- 출력을 위한 DTO 생성에 대한 고민
  - DTO 룰 출력하고 해당 DTO 통해 다시 이벤트 내역 확인하는데 있어 DTO 값을 사용하는 방법
  - EventResultDTO 뷰에 출력할 문자열을 가지고 있을 DTO 생성 방법 고민 후 Builder, EventResultTextFactory 사용
- 잘못된 입력에 대한 메서드 재실행 로직은 지난주 [장혁수님](https://github.com/zangsu) 코드리뷰로 배운 Supplier 반복 사용
- 테스트 코드 mock 객체에 대한 사용방법에 대한 고민
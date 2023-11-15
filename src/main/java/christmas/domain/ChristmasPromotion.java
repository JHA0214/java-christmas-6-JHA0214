package christmas.domain;

public class ChristmasPromotion {
    enum PromotionPhrases {
        GREETING_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
        DATE_QUERY("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
        ORDER_MENU_PROMPT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
        EVENT_PREVIEW_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기! \n");

        private final String phrase;

        PromotionPhrases(String phrase) {
            this.phrase = phrase;
        }

        public String getPhrase() {
            return phrase;
        }
    }

    private UserInputManager userInputManager;
    private OrderManager orderManager;
    private MenuManager menuManager;
    private EventCalendar eventCalendar;
    private PromotionManager promotionManager;
    private PromotionView promotionView;

    public ChristmasPromotion() {
        userInputManager = new UserInputManager();
        orderManager = new OrderManager();
        menuManager = new MenuManager();
        eventCalendar = new EventCalendar();
        promotionManager = new PromotionManager();
        promotionView = new PromotionView();
    }

    public void startPromotion() {
        int visitDate;

        visitDate = inputVisitDate();

        inputOrder();

        DayType dayType = eventCalendar.checkVisitDate(visitDate); //방문날짜에 해당되는 이벤트 확인

        if (orderManager.checkEventEligibility()) //총주문 금액이 이벤트 참여 조건에 만족하는지 확인
            orderManager.startDiscountProcess(promotionManager, dayType, visitDate); // 이벤트 적용

        System.out.printf(PromotionPhrases.EVENT_PREVIEW_MESSAGE.getPhrase(), visitDate);
        promotionView.printOrderResult(promotionManager, orderManager);//최종 결과 출력
    }

    public int inputVisitDate() {
        int visitDate = 0;
        do {
            System.out.println(PromotionPhrases.GREETING_MESSAGE.getPhrase());
            System.out.println(PromotionPhrases.DATE_QUERY.getPhrase());
            visitDate = userInputManager.checkVisitdate(userInputManager.input()); // 방문날짜 입력
        } while (visitDate == 0);

        return visitDate;
    }

    public void inputOrder() {
        String order = "";
        boolean orderCheckStatus = true;
        do {
            System.out.println(PromotionPhrases.ORDER_MENU_PROMPT.getPhrase());
            order = userInputManager.input(); //  주문메뉴 입력
            orderCheckStatus = userInputManager.checkOrder(order); //주문수량에 숫자가 아닌 잘못된 값이 있는지 확인
            if (orderCheckStatus == true) {
                orderManager.compileOrderList(order); // 메뉴 정리
                orderManager.calculateOrderPrices(menuManager); // 가격 계산
                orderCheckStatus = menuManager.checkIfOnlyDrinks(); //음료만 주문했는지 확인
            }
        } while (!orderCheckStatus);
    }
}

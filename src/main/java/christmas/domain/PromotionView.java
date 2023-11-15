package christmas.domain;

import java.util.Map;

public class PromotionView {
    enum CheckoutMessages {
        ORDER_MENU("\n<주문 메뉴>"),
        TOTAL_ORDER_AMOUNT("\n<할인 전 총주문 금액>"),
        COMPLIMENTARY_ITEM("\n<증정 메뉴>"),
        BENEFIT_DETAILS("\n<혜택 내역>"),
        TOTAL_DISCOUNT_AMOUNT("\n<총혜택 금액>"),
        EXPECTED_PAYMENT_AFTER_DISCOUNT("\n<할인 후 예상 결제 금액>"),
        DECEMBER_EVENT_BADGE("\n<12월 이벤트 배지>");

        private final String messages;

        CheckoutMessages(String messages) {
            this.messages = messages;
        }

        public String getmessages() {
            return messages;
        }
    }

    public PromotionView() {
    }

    public void printOrderResult(PromotionManager promotionManager, OrderManager orderManager) {
        printOrderMenu(orderManager.getOrderList());
        printDiscountDetails(orderManager.getTotalOrderAmount(), promotionManager.checkChampagne());
        printDiscounthistory(promotionManager.getDiscountHistory(), orderManager.getTotalOrderAmount());
        printOrderAfterDiscount(orderManager.getTotalOrderAmount(), orderManager.getTotalDiscountAmount(), orderManager.getEventBadge());
    }

    public void printOrderMenu(Map<String, Integer> orderList) { //할인 적용 전 주문 내역 출력
        System.out.println(CheckoutMessages.ORDER_MENU.getmessages());
        for (Map.Entry<String, Integer> entry : orderList.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + "개");
        }
    }

    public void printDiscountDetails(int totalOrderAmount, String checkChampagne) {//적용된 할인혜택 출력
        System.out.println(CheckoutMessages.TOTAL_ORDER_AMOUNT.getmessages() + "\n" + totalOrderAmount);
        System.out.println(CheckoutMessages.COMPLIMENTARY_ITEM.getmessages() + "\n" + checkChampagne);
        System.out.println(CheckoutMessages.BENEFIT_DETAILS.getmessages());
    }

    public void printOrderAfterDiscount(int totalOrderAmount, int totalDiscountAmount, String eventBadge) {//할인 적용 후 주문내역 출력
        System.out.println(CheckoutMessages.TOTAL_DISCOUNT_AMOUNT.getmessages() + "\n" + totalDiscountAmount * -1);
        System.out.println(CheckoutMessages.EXPECTED_PAYMENT_AFTER_DISCOUNT.getmessages() + "\n" + (totalOrderAmount - totalDiscountAmount));
        System.out.println(CheckoutMessages.DECEMBER_EVENT_BADGE.getmessages() + "\n" + eventBadge);
    }

    public void printDiscounthistory(Map<String, Integer> discountHistory, int totalOrderAmount) { //할인 내역서 출력
        if (totalOrderAmount < 10000) {
            System.out.println("없음");
            return;
        }
        for (Map.Entry<String, Integer> discountEntry : discountHistory.entrySet()) {
            System.out.println(discountEntry.getKey() + ": " + discountEntry.getValue() + "원");
        }
    }
}

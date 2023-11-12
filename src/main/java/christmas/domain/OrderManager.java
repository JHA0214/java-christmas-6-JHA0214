package christmas.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/* ChristmasPromotion
 * 사용자의 방문 날짜를 저장한다. <visitDate>
 * 입력 받은 주문을 정리하여 저장한다.
 * 음료만 주문할 수 없다.
 * 메뉴는 한 번에 최대 20개까지만 주문이 가능하다.
 * 할인 내역과 총주문 금액 등 주문에 대한 데이터들을 저장한다.
 * */
public class OrderManager {
    private Map<String, Integer> orderList = new LinkedHashMap<>();
    private int visitDate;
    private int totalOrderAmount;
    private int totalDiscountAmount;

    public void compileOrderList(String userOrder) {
        String[] items = userOrder.split(",");

        for (String item : items) {
            String[] parts = item.split("-");
            String menu = parts[0];
            int quantity = Integer.parseInt(parts[1]);

            orderList.put(menu, quantity);
        }
    }


}

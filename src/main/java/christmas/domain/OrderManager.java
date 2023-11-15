package christmas.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* OrderManager
 * 주문에 관련된 정보를 저장하고 있다.
 * */

enum FoodType {
    APPETIZER, MAIN, DESSERT, DRINK
}

public class OrderManager {
    private Map<String, Integer> orderList = new LinkedHashMap<>(); //메뉴별 주문수
    private Map<FoodType, Integer> foodtypeQuantities = new LinkedHashMap<>(); //메뉴 종류별 주문수

    private int totalOrderAmount; //할인 전 총주문 금액
    private int totalDiscountAmount; //총혜택 금액
    private String eventBadge = "없음";

    public OrderManager() {
    }

    public Map<String, Integer> compileOrderList(String userOrder) { //입력받은 주문을 orderList에 메뉴이름과 주문수로 정리하여 저장한다.
        String[] orders = userOrder.split(",");

        for (String order : orders) {
            String[] parts = order.split("-");
            String menu = parts[0];
            int quantity = Integer.parseInt(parts[1]);

            orderList.put(menu, quantity);
        }
        return orderList;
    }

    public void calculateOrderPrices(MenuManager menuManager) { //총주문 금액을 계산한다.
        int total = menuManager.calculateTotalOrderCost(orderList);
        totalOrderAmount = total;
        setOrderquantity(menuManager);
    }

    public void setOrderquantity(MenuManager menuManager) { //메뉴 종류별 주문수를 계산하여 저장한다.
        List<Integer> orderQuantity = menuManager.getOrderQuantity();
        int orderQuantityNumber = 0;
        for (FoodType type : FoodType.values()) {
            foodtypeQuantities.put(type, orderQuantity.get(orderQuantityNumber));
            orderQuantityNumber++;
        }
    }

    public boolean checkEventEligibility() { //총주문 금액이 이벤트 적용 조건인 10000원 이상인지 확인한다.
        return totalOrderAmount > 10000;
    }

    public void startDiscountProcess(PromotionManager promotionManager, DayType dayType, int visitDate) { //할인혜택 적용
        totalDiscountAmount += promotionManager.christmasDiscount(visitDate);
        totalDiscountAmount += promotionManager.checkEligibleDiscounts(dayType, foodtypeQuantities);
        totalDiscountAmount += promotionManager.champagneGift(totalOrderAmount);
        if (totalDiscountAmount > 5000)
            eventBadge = determineEventBadge();
    }

    public String determineEventBadge() {
        if (totalDiscountAmount > 10000)
            return treeOrSanta();
        return "별";
    }

    public String treeOrSanta() {
        if (totalDiscountAmount > 15000)
            return "산타";
        return "트리";
    }

    public Map<String, Integer> getOrderList() {
        return orderList;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public String getEventBadge() {
        return eventBadge;
    }
}

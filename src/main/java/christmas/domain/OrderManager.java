package christmas.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* OrderManager
 * 입력 받은 주문을 정리하여 저장한다.
 * 할인 내역과 총주문 금액 등 주문에 대한 데이터들을 저장한다.
 * */

enum FoodType {
    APPETIZER, MAIN, DESSERT, DRINK
}

public class OrderManager {
    private Map<String, Integer> orderList = new LinkedHashMap<>();
    private Map<FoodType, Integer> foodtypeQuantities = new LinkedHashMap<>();

    private int totalOrderAmount;
    private int totalDiscountAmount;
    private String eventBadge = "없음";

    public OrderManager() {
    }

    public void setOrderquantity(MenuManager menuManager) {
        List<Integer> orderQuantity = menuManager.getOrderQuantity();
        int orderQuantityNumber = 0;
        for (FoodType type : FoodType.values()) {
            foodtypeQuantities.put(type, orderQuantity.get(orderQuantityNumber));
            orderQuantityNumber++;
        }
    }

    public Map<String, Integer> compileOrderList(String userOrder) {
        String[] orders = userOrder.split(",");

        for (String order : orders) {
            String[] parts = order.split("-");
            String menu = parts[0];
            int quantity = Integer.parseInt(parts[1]);

            orderList.put(menu, quantity);
        }
        return orderList;
    }

    public void calculateOrderPrices(MenuManager menuManager) {
        int total = menuManager.calculateTotalOrderCost(orderList);
        totalOrderAmount = total;
        setOrderquantity(menuManager);
    }

    public boolean checkEventEligibility() {
        return totalOrderAmount > 10000;
    }

    public void startDiscountProcess(PromotionManager promotionManager, DayType dayType, int visitDate) {
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

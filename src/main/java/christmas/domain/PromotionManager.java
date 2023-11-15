package christmas.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class PromotionManager {
    private Map<String, Integer> discountHistory = new LinkedHashMap<>();

    public PromotionManager() { //할인 내역서
        discountHistory.put("크리스마스 디데이 할인", 0);
        discountHistory.put("평일 할인", 0);
        discountHistory.put("특별 할인", 0);
        discountHistory.put("증정 이벤트", 0);
    }

    public int checkEligibleDiscounts(DayType dayType, Map<FoodType, Integer> foodtypeQuantities) {
        if (dayType.equals(DayType.EVENTDAY))
            return specialDiscount();
        return checkWeekdayOrWeekendDiscount(dayType, foodtypeQuantities);
    }

    public int checkWeekdayOrWeekendDiscount(DayType dayType, Map<FoodType, Integer> foodtypeQuantities) {
        if (dayType.equals(DayType.WEEKDAY))
            return weekdayDiscount(foodtypeQuantities);
        return weekendDiscount(foodtypeQuantities);
    }

    public int weekdayDiscount(Map<FoodType, Integer> foodtypeQuantities) {
        //평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
        int discount = 0;
        int dessertQuantity = foodtypeQuantities.get(FoodType.DESSERT);
        discount = dessertQuantity * 2023;
        discountHistory.put("평일 할인", discount);

        return discount;
    }

    public int weekendDiscount(Map<FoodType, Integer> foodtypeQuantities) {
        //주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
        int discount = 0;
        int dessertQuantity = foodtypeQuantities.get(FoodType.MAIN);
        discount = dessertQuantity * 2023;
        discountHistory.put("주말 할인", discount);

        return discount;
    }

    public int specialDiscount() {
        //특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
        int discount = 1000;
        discountHistory.put("특별 할인", discount);

        return discount;
    }

    public int christmasDiscount(int visitDate) {
        //크리스마스 디데이 할인
        int discount = 1000;
        discount += visitDate * 100;
        discountHistory.put("크리스마스 디데이 할인", discount);
        return discount;
    }

    public int champagneGift(int totalOrderAmount) {
        //증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
        if (totalOrderAmount < 120000)
            return 0;

        int discount = 25000;
        discountHistory.put("증정 이벤트", discount);
        return discount;
    }

    public String checkChampagne() {
        if (discountHistory.get("증정 이벤트") == 25000)
            return "샴페인 1개";
        return "없음";
    }

    public Map<String, Integer> getDiscountHistory() {
        return discountHistory;
    }
}

package christmas.domain;

import camp.nextstep.edu.missionutils.Console;

import java.util.*;

enum ErrorMessage {
    ERROR("[ERROR]"),
    VISITDATE(" 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ORDER(" 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String messages;

    ErrorMessage(String messages) {
        this.messages = messages;
    }

    public String getmessages() {
        return messages;
    }
}

public class UserInputManager {
    public UserInputManager() {
    }

    public String input() {
        String input = Console.readLine();
        return input;
    }

    public int checkVisitdate(String input) { //방문날짜에 관련된 예외처리
        try {
            checkInt(input);
            checkVisitdateInRange(Integer.parseInt(input));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + ErrorMessage.VISITDATE.getmessages());
            return 0;
        }
        return Integer.parseInt(input);
    }

    public void checkVisitdateInRange(int input) { //입력한 방문날짜의 범위 확인
        if (input < 1 || input > 31)
            throw new IllegalArgumentException(ErrorMessage.ERROR.getmessages());
    }

    public boolean checkOrder(String inputOrder) { //주문에 관련된 예외처리
        int totalOrderQuantity = 0;
        List<String> ordermenu = new ArrayList<>();
        String[] orders = inputOrder.split(",");
        for (String order : orders) {
            String[] parts = order.split("-");
            ordermenu.add(parts[0]);
            if (!checkOrderQuantity(parts[1]))
                return false;
            totalOrderQuantity += Integer.parseInt(parts[1]);
        }
        if (!checkForDuplicateMenuItems(ordermenu) || !checkTotalOrderQuantity(totalOrderQuantity)) //총주문 수 확인, 중복 주문 확인
            return false;

        return true;
    }

    public boolean checkOrderQuantity(String inputOrderquantity) { //주문 수량 확인(각 메뉴의 수가 1 이상, 문자가 아닌 숫자 입력)
        try {
            checkInt(inputOrderquantity);
            if (Integer.parseInt(inputOrderquantity) < 1)
                throw new IllegalArgumentException(ErrorMessage.ERROR.getmessages());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + ErrorMessage.ORDER.getmessages());
            return false; //잘못된 값 확인시 false 반환
        }
        return true;
    }

    public boolean checkTotalOrderQuantity(int totalOrderQuantity) { //총주문 수가 20이 넘는지 확인
        try {
            if (totalOrderQuantity > 20)
                throw new IllegalArgumentException(ErrorMessage.ERROR.getmessages());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + ErrorMessage.ORDER.getmessages());
            return false; //잘못된 값 확인시 false 반환
        }
        return true;
    }

    public boolean checkForDuplicateMenuItems(List<String> ordermenu) { //중복된 주문이 있는지 확인
        Set<String> set = new HashSet<>(ordermenu);
        try {
            if (set.size() < ordermenu.size())
                throw new IllegalArgumentException(ErrorMessage.ERROR.getmessages());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + ErrorMessage.ORDER.getmessages());
            return false; //잘못된 값 확인시 false 반환
        }
        return true;
    }

    public void checkInt(String input) { //입력값이 숫자인지 확인
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.ERROR.getmessages());
        }
    }
}

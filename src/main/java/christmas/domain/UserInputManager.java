package christmas.domain;

import camp.nextstep.edu.missionutils.Console;

enum ErrorMessage {
    visitdate(" 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    order(" 유효하지 않은 주문입니다. 다시 입력해 주세요.");

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

    public int checkVisitdate(String input) {
        try {
            checkInt(input);
            checkVisitdateInRange(Integer.parseInt(input));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + ErrorMessage.visitdate.getmessages());
            return 0;
        }
        return Integer.parseInt(input);
    }

    public void checkVisitdateInRange(int input) { //입력한 방문날짜의 범위 확인
        if (input < 1 || input > 31)
            throw new IllegalArgumentException("[ERROR]");
    }

    public void checkInt(String input) { //입력값이 숫자인지 확인
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR]");
        }
    }


}

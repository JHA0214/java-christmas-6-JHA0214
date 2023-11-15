package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputManagerTest {
    private UserInputManager userInputManager;

    @BeforeEach
    void setUp() {
        userInputManager = new UserInputManager();
    }

    @Test
    void 문자_입력시_예외발생() {
        assertThrows(IllegalArgumentException.class, () -> {
            userInputManager.checkInt("a");
        });
    }

    @Test
    void 방문날짜가_1보다_작거나_31_이상일경우_예외발생() {
        assertThrows(IllegalArgumentException.class, () -> {
            userInputManager.checkVisitdateInRange(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            userInputManager.checkVisitdateInRange(32);
        });
    }

    @Test
    void 주문수에_문자_입력시_false_반환() {
        String userOrder = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-a";
        assertFalse(userInputManager.checkOrder(userOrder));
    }

    @Test
    void 중복된_메뉴_입력시_false_반환() {
        String userOrder = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1,티본스테이크-1";
        assertFalse(userInputManager.checkOrder(userOrder));
    }

    @Test
    void 총주문_수가_20이상이면_false_반환() {
        String userOrder = "티본스테이크-1,바비큐립-100";
        assertFalse(userInputManager.checkOrder(userOrder));
    }
}
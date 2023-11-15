package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MenuManagerTest {
    private MenuManager menuManager;

    @BeforeEach
    void setUp() {
        menuManager = new MenuManager();
    }

    @Test
    void 존재하지_않는_메뉴_입력시_예외발생() {
        String userOrder = "티본스테이크-1,없는메뉴-1,초코케이크-2,제로콜라-a";
        assertThrows(IllegalArgumentException.class, () -> {
            menuManager.validate(userOrder);
        });
    }

    @Test
    void 총주문_계산() {
        Map<String, Integer> userOrder = Map.of(
                "티본스테이크", 1,
                "바비큐립", 1,
                "초코케이크", 2,
                "제로콜라", 1
        );
        assertEquals(menuManager.calculateTotalOrderCost(userOrder), 142000);
    }
}
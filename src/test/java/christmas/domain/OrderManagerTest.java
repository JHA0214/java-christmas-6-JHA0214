package christmas.domain;

import christmas.domain.OrderManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {
    private OrderManager orderManager;

    @BeforeEach
    void setUp() {
        orderManager = new OrderManager();
    }

    @Test
    void 입력받은_주문_저장_기능_확인() {
        String userOrder = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        Map<String, Integer> result = orderManager.compileOrderList(userOrder);

        assertEquals(1, result.get("티본스테이크"));
        assertEquals(1, result.get("바비큐립"));
        assertEquals(2, result.get("초코케이크"));
        assertEquals(1, result.get("제로콜라"));
    }
}
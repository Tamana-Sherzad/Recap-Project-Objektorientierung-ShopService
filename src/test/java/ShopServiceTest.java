import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.stream.Collectors;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), Order.OrderStatus.COMPLETED);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        assertNull(actual);
    }

    @Test

    void returnOrderListByStatusTest_whenPresent_thenReturnOrderList() {

        // GIVEN

        ShopService shopService = new ShopService();
        List<String> productId = List.of("1");
        Set<Order> expectedOrders = new HashSet<>();

        expectedOrders.add(shopService.addOrder(productId));

        //WHEN

        List<Order> actual = shopService.returnOrderListByStatus(Order.OrderStatus.COMPLETED);
        Set<Order> actualSet = new HashSet<>(actual);

        // THEN

        assertEquals(actualSet,expectedOrders);

    }

    @Test

    void returnOrderByStatusTest_whenNotPresent_thenReturnEmptyList(){
        // GIVEN
        ShopService shopService = new ShopService();
        //WHEN
        List<Order> actual = shopService.returnOrderListByStatus(Order.OrderStatus.COMPLETED);
        //THEN
        assertEquals(List.of(),actual);

    }


}

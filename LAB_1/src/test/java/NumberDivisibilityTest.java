import org.example.algorithm.NumberDivisibility;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NumberDivisibilityTest {
    private final NumberDivisibility divisibility = new NumberDivisibility();

    @Test
    void test_should_work(){
        assertTrue(true);
    }

    @Test
    void should_be_divisible_by_2() {
        List<Integer> notDivisibleBy2 = List.of(-2, 0, 4, 6, 100, 222, 888);

        notDivisibleBy2.forEach(number -> {
            assertTrue(divisibility.isDivisibleBy2(number), "For number " + number + " test has failed.");
        });
    }

    @Test
    void should_not_be_divisible_by_2() {
        List<Integer> notDivisibleBy2 = List.of(-3, 1, 3, 5, 7, 9, 11, 13, 17);

        notDivisibleBy2.forEach(number -> {
            assertFalse(divisibility.isDivisibleBy2(number), "For number " + number + " test has failed.");
        });
    }

    @Test
    void should_be_divisible_by_2_other_assert_method(){
        assertEquals(true, divisibility.isDivisibleBy2(200000));
    }

    @Test
    void should_not_be_divisible_by_2_other_assert_method(){
        assertNotEquals(true, divisibility.isDivisibleBy2(1111111));
    }

    @Test
    void should_not_be_null(){
        boolean isDivisible = divisibility.isDivisibleBy2(11111111);

        assertNotNull(isDivisible);
    }

    @Test
    void divisible_by_3_should_work_as_expected(){
        assertAll("Multiple assertions for divisible by 3",
                () -> assertTrue(divisibility.isDivisibleBy3(3)),
                () -> assertFalse(divisibility.isDivisibleBy3(4))
        );
    }

    @Test
    void divisibleBy_should_check_if_is_divide_by_5(){
        assertAll("Multiple assertions for divisible by 5",
                () -> assertTrue(divisibility.isDivisibleBy(100, 5)),
                () -> assertFalse(divisibility.isDivisibleBy(222, 5))
        );
    }

    @Test
    void divisibleBy_should_work_as_expected_if_divide_by_3(){
        assertAll("Multiple assertions for divisible by 3",
                () -> assertTrue(divisibility.isDivisibleBy(3333333, 3)),
                () -> assertFalse(divisibility.isDivisibleBy(2222, 3))
        );
    }

    @Test
    void divisibleBy_should_return_error(){
        assertThrows(IllegalArgumentException.class, () -> {
            divisibility.isDivisibleBy(1,1);
        });
    }
}

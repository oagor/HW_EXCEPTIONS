package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    @Test
    void shouldRemoveExistingProduct() {
        ShopRepository repository = new ShopRepository();

        Product product1 = new Product(1, "Milk", 100);
        Product product2 = new Product(2, "Bread", 50);
        Product product3 = new Product(3, "Eggs", 120);

        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        repository.removeById(2);

        Product[] expected = {product1, product3};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenRemoveMissingProduct() {
        ShopRepository repository = new ShopRepository();

        Product product1 = new Product(1, "Milk", 100);
        Product product2 = new Product(2, "Bread", 50);

        repository.add(product1);
        repository.add(product2);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> repository.removeById(5)
        );

        assertEquals("Element with id: 5 not found", exception.getMessage());
    }
}


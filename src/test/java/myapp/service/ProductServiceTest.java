package myapp.service;

import myapp.domain.Product;
import myapp.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import myapp.domain.enumeration.ProductStatus;
import java.math.BigDecimal;
import java.time.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    // Função auxiliar para criação de um produto
    public static Product createProductSample(String title, String keywords, String description, Integer rating, BigDecimal price, Integer quantityInStock, ProductStatus status, Double weight, String dimensions, Instant DateAdded, Instant DateModified) {
        return new Product()
            .title(title)
            .keywords(keywords)
            .description(description)
            .rating(rating)
            .price(price)
            .quantityInStock(quantityInStock)
            .status(status)
            .weight(weight)
            .dimensions(dimensions)
            .dateAdded(DateAdded)
            .dateModified(DateModified);
    }

    ///////////////////////////////////////////////////////
    //                                                   //
    // 5 casos de teste cujo resultado esperado é válido //
    //                                                   //
    ///////////////////////////////////////////////////////
    @Test
    public void testeValido_1() {
        assertDoesNotThrow(() -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeValido_2() {
        assertDoesNotThrow(() -> {
            // Arrange:
            Product product = createProductSample(
                "ABCD",
                "A",
                "ABCDEFGHIJK",
                Integer.valueOf(1),
                BigDecimal.valueOf(0.01),
                Integer.valueOf(1),
                ProductStatus.valueOf("Esgotado"),
                Double.valueOf(0.01),
                "A",
                Instant.now(),
                null
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeValido_3() {
        assertDoesNotThrow(() -> {
            // Arrange:
            Product product = createProductSample(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTU",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQ",
                null,
                Integer.valueOf(4),
                BigDecimal.valueOf(0.01),
                null,
                ProductStatus.valueOf("Descontinuado"),
                null,
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVW",
                Instant.now(),
                null
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeValido_4() {
        assertDoesNotThrow(() -> {
            // Arrange:
            Product product = createProductSample(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUV",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQR",
                null,
                Integer.valueOf(5),
                BigDecimal.valueOf(0.01),
                null,
                ProductStatus.valueOf("Descontinuado"),
                null,
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWX",
                Instant.now(),
                null
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeValido_5() {
        assertDoesNotThrow(() -> {
            // Arrange:
            Product product = createProductSample(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUV",
                null,
                null,
                null,
                BigDecimal.valueOf(0.01),
                null,
                ProductStatus.valueOf("Descontinuado"),
                null,
                null,
                Instant.now(),
                null
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    //////////////////////////////////////////////////////////
    //                                                      //
    // 25 casos de teste cujo resultado esperado é inválido //
    //                                                      //
    //////////////////////////////////////////////////////////

    @Test
    public void testeInvalido_1() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "AB",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_2() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVW",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_3() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                null,
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_4() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRS",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_5() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHI",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_6() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(-1),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_7() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(6),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_8() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf("3.5"),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_9() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(-0.01),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_10() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                null,
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_11() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(Long.valueOf("a")),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_12() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(-1),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_13() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf("2.5"),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_14() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Outro"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_15() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                null,
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_16() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(-0.01),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_17() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf("a"),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_18() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXY",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_19() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now().minus(Duration.ofMinutes(1)),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_20() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now().plus(Duration.ofMinutes(1)),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_21() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                null,
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_22() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now().minus(Duration.ofMinutes(1))
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_23() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now().plus(Duration.ofMinutes(1))
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_24() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.now()
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }

    @Test
    public void testeInvalido_25() {
        assertThrows(Exception.class, () -> {
            // Arrange:
            Product product = createProductSample(
                "ABC",
                "",
                "ABCDEFGHIJ",
                Integer.valueOf(0),
                BigDecimal.valueOf(0.00),
                Integer.valueOf(0),
                ProductStatus.valueOf("Disponível"),
                Double.valueOf(0.00),
                "",
                Instant.now(),
                Instant.parse("a")
            );
            when(productRepository.save(product)).thenReturn(product);

            // Act:
            Product result = productService.save(product);

            // Assert:
            assertEquals(product, result);
        });
    }
}

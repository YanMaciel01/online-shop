package myapp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import myapp.domain.Product;
import myapp.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import myapp.domain.enumeration.ProductStatus;
import java.math.BigDecimal;
import java.util.Optional;
import java.time.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;




    // @Test
    // public void saveProduct_success(Long id, String title, String keywords, String description, int rating, int quantityInStock, String dimensions, Instant DateAdded, Instant DateModified) {
    //     // Arrange: 
    //     Product product = createProductSample(id, title, keywords, description, rating, quantityInStock, dimensions);
    //     when(productRepository.save(product)).thenReturn(product);

    //     // Act:
    //     Product result = productService.save(product);

    //     // Assert:
    //     assertEquals(expected:id, result.getId());
    //     assertEquals(expected:title, result.getTitle());
    //     assertEquals(expected:keywords, result.getKeywords());
    //     assertEquals(expected:description, result.getDescription());
    //     assertEquals(expected:rating, result.getRating());
    //     assertEquals(expected:quantityInStock, result.getQuantityInStock());
    //     assertEquals(expected:dimensions, result.getDimensions());
    //     assertEquals(expected:DateAdded, result.getDateAdded());
    //     assertEquals(expected:DateModified, result.getDateModified());
    // }


    // Funcao auxiliar
    public static Product createProductSample(String title, Optional<String> keywords, Optional<String> description, Optional<Integer> rating, BigDecimal price, Optional<Integer> quantityInStock, ProductStatus status, Optional<Double> weight, Optional<String> dimensions, Instant DateAdded, Optional<Instant> DateModified) {
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
            .DateAdded(DateAdded)
            .DateModified(DateModified);
    }



    ///////////////////////////////////////////////////////
    //                                                   //
    // 5 casos de teste cujo resultado esperado é válido //
    //                                                   //
    ///////////////////////////////////////////////////////
    @Test
    public void teste_valido_1() {
        // Arrange
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, new BigDecimal(0.00), 0, Enum.valueOf(enumerarion.ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);

        // Assert:
        assertEquals(product, result);
    }

    @Test
    public void teste_valido_2() {
        // Arrange
        Product product = createProductSample("ABCD", "A", "ABCDEFGHIJK", 1, new BigDecimal(0.01), 1, Enum.valueOf(ProductStatus, "OUT_OF_STOCK"), 0.01, "A", Instant.now(), null);
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);

        // Assert:
        assertEquals(product, result);
    }

    @Test
    public void teste_valido_3() {
        // Arrange
        Product product = createProductSample("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTU", "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQ", null, 4, new BigDecimal(0.01), null, Enum.valueOf(ProductStatus, "DISCONTINUED"), null, "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVW", Instant.now(), null);
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);

        // Assert:
        assertEquals(product, result);
    }

    @Test
    public void teste_valido_4() {
        // Arrange
        Product product = createProductSample("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUV", "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQR", null, 5, new BigDecimal(0.01), null, Enum.valueOf(ProductStatus, "DISCONTINUED"), null, "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWX", Instant.now(), null);
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);

        // Assert:
        assertEquals(product, result);
    }

    @Test
    public void teste_valido_5() {
        // Arrange
        Product product = createProductSample("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUV", null, null, null, new BigDecimal(0.01), null, Enum.valueOf(ProductStatus, "DISCONTINUED"), null, null, Instant.now(), null);
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);

        // Assert:
        assertEquals(product, result);
    }






    //////////////////////////////////////////////////////////
    //                                                      //
    // 25 casos de teste cujo resultado esperado é inválido //
    //                                                      //
    ////////////////////////////////////////////////////////// 

    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_1() {
        // Arrange: 
        Product product = createProductSample("AB", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
        

        
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_2() {
        // Arrange: 
        Product product = createProductSample("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVW", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_3() {
        // Arrange: 
        Product product = createProductSample(null, "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_4() {
        // Arrange: 
        Product product = createProductSample("ABC", "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRS", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_5() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHI", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_6() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", -1, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_7() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 6, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_8() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 3.5, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_9() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, -0.01, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_10() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, null, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_11() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, "a", 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_12() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, -1, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_13() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 2.5, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_14() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, "Outro", 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_15() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, null, 0.00, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_16() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), -0.01, "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_17() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), "a", "", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_18() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXY", Instant.now(), Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_19() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now() - 1, Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_20() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now() + 1, Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_21() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", null, Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_22() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", "a", Instant.now());
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_23() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now().minusMinutes(1));
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_24() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), Instant.now().plusMinutes(1));
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }
    @Test(expected = IllegalArgumentException.class)
    public void teste_invalido_25() {
        // Arrange: 
        Product product = createProductSample("ABC", "", "ABCDEFGHIJ", 0, 0.00, 0, Enum.valueOf(ProductStatus, "IN_STOCK"), 0.00, "", Instant.now(), "a");
        when(productRepository.save(product)).thenReturn(product);

        // Act:
        Product result = productService.save(product);
        fail();
  
    }

}

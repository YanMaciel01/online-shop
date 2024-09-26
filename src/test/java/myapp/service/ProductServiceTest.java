package myapp.service;

import static org.junit.jupiter.api.Assertion.assertEquals;
import static org.mockito.Mockito.when;

import myapp.domain.Product;
import myapp.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


  
    /////////////////////////////////
    //                             //
    // INSERIR TESTES AQUI EMBAIXO //
    //                             //
    /////////////////////////////////
    @Test
    public void saveProduct_success() {}

    @Test
    public void teste2() {}

    @Test
    public void teste3() {}
    ///////////////////////////////////
    ///////////////////////////////////
    ///////////////////////////////////

}

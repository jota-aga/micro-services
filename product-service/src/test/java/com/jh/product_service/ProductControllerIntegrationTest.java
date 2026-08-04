package com.jh.product_service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {
	
	/**@Container
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");
	
	private static String url = "/api/product";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@BeforeEach
	public void setUp() {
		productRepository.deleteAll();
	}
	
	@Test
	public void shouldCreateProduct() throws JacksonException, Exception {
		ProductRequest productRequest = new ProductRequest("name", "description", new BigDecimal(10));
		
		mockMvc.perform(MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productRequest)))
		.andExpect(MockMvcResultMatchers.status().isCreated());
		
		List<Product> products =productRepository.findAll();
	
		assertEquals(products.size(), 1);
	}
	
	@Test
	public void shouldReturnAllProducts() throws JacksonException, Exception {
		Product p1 = Product.builder()
				.name("name 1")
				.descrption("description 1")
				.price(new BigDecimal(10))
				.build();
		
		Product p2 = Product.builder()
				.name("name 2")
				.descrption("description 2")
				.price(new BigDecimal(10))
				.build();
		
		productRepository.saveAll(List.of(p1, p2));
		
		mockMvc.perform(MockMvcRequestBuilders.get(url))
		.andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
		
	}**/
}

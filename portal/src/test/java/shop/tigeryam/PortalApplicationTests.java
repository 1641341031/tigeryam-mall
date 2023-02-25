package shop.tigeryam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.tigeryam.entity.PmsProduct;
import shop.tigeryam.service.IPmsProductService;

import java.util.List;

@SpringBootTest
@Slf4j
class PortalApplicationTests {
    @Autowired
    private IPmsProductService pmsProductService;

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("12345");
        log.info("encode {}", encode);
    }

    @Test
    void testProduct(){
        List<PmsProduct> procutById = pmsProductService.getProcutById(1);
        procutById.forEach(pmsProduct -> System.out.println(pmsProduct));
    }

}

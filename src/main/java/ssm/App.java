package ssm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ssm.dao.TestDao;
import ssm.entity.TestEntity;

//使用spring5的测试
@SpringJUnitConfig(locations = "file:src/main/resources/spring-config.xml")
public class App {
    @Autowired
    private TestDao testdao;

    @Test
    void getData1() throws Exception{
        TestEntity data = testdao.getData();
        System.out.println(data);
    }

}

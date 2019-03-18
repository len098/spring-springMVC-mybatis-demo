package ssm.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data //use lombok
@Component //注册为bean
public class TestEntity implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
}

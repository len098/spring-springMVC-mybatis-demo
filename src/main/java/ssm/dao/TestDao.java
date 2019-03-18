package ssm.dao;

import org.springframework.stereotype.Repository;
import ssm.entity.TestEntity;

@Repository //注册为持久层的bean
public interface TestDao {
        /**
         * 查询数据库信息
         */
        TestEntity getData();
}
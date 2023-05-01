package ink.heshang.testdemo.dao.mapper;

import ink.heshang.testdemo.dao.model.ToDoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/4/27 09:12
 * version: 1.0
 */
@Repository
public interface ToDoEntityMapper {
    public int insert(ToDoEntity entity);

    public List<ToDoEntity> select(Long id);

    public int update(ToDoEntity entity);
}

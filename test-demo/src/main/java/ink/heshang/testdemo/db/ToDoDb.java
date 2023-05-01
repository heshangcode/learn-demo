package ink.heshang.testdemo.db;

import ink.heshang.testdemo.dao.mapper.ToDoEntityMapper;
import ink.heshang.testdemo.dao.model.ToDoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/4/27 09:15
 * version: 1.0
 */
@Service
public class ToDoDb {
    @Autowired
    private ToDoEntityMapper mapper;

    public void insert(ToDoEntity entity) {
        mapper.insert(entity);
    }
    // select
    public Optional<List<ToDoEntity>> select(long id) {
        return Optional.ofNullable(mapper.select(id));
    }
    public void update(ToDoEntity entity) {
        mapper.update(entity);
    }
}

package ink.heshang.testdemo;

import ink.heshang.testdemo.dao.model.ToDoEntity;
import ink.heshang.testdemo.db.ToDoDb;
import ink.heshang.testdemo.vo.ToDoAddReq;
import ink.heshang.testdemo.vo.ToDoDoneReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/4/27 09:19
 * version: 1.0
 */
@Service
public class ToDoService {

    @Autowired
    private ToDoDb toDoDb;

    public void addToDo(ToDoAddReq req) {
//        转换
//        toDoDb.insert(req);
    }
    public void modifyToDo(ToDoDoneReq req) {
//        去修改
    }
    private List<ToDoEntity> getToDoList() {
//        去查
        return null;
    }

    public List<ToDoEntity> getToDoList(long id) {
//        去查
        return null;
    }

}

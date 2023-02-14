package cn.heshang.application.orgmng.empservice;

/**
 * TODO
 *
 * @author 微信公众号《和尚的破功之路》
 * @date 2023/2/7 09:33
 * version: 1.0
 */

import cn.heshang.domain.orgmng.emp.Emp;
import cn.heshang.domain.orgmng.emp.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// imports ...

/**
 * 应用服务类
 */
@Service
public class EmpService {
    private final EmpRepository empRepository;
    private final EmpAssembler assembler;

    @Autowired
    public EmpService(EmpRepository empRepository
            , EmpAssembler assembler) {
        this.empRepository = empRepository;
        this.assembler = assembler;
    }

    @Transactional
    public EmpResponse addEmp(CreateEmpRequest request, User user) {
        Emp emp = assembler.fromCreateRequest(request, user);

        empRepository.save(emp);
        return assembler.toResponse(emp);
    }

}

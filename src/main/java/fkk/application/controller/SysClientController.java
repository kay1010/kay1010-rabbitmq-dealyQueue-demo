package fkk.application.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色相关接口
 *
 * @author owen 624191343@qq.com
 */
@RestController
@SuppressWarnings("all")
public class SysClientController {
    private static Logger logger= LoggerFactory.getLogger(SysClientController.class);

    @Autowired
    private TryService tryService;

    private int count;
    @GetMapping("test")
    public void cacheProduct(String productCode) throws Exception {
            tryService.cacheProduct();

    }
    @GetMapping("task")
    public void test(int param) throws Exception {
        for(int i=0;i<param;i++){
            tryService.doTask(i);

        }
    }




}

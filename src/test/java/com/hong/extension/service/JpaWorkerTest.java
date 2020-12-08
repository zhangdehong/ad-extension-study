package com.hong.extension.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.extension.dao.WorkDaoDefinition;
import com.hong.extension.dao.WorkerDao;
import com.hong.extension.entity.Worker;
import com.hong.extension.util.MapToEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.ldap.PagedResultsControl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * <h1>worker jpa功能测试</h1>
 *
 * @Author: ZhangDehong
 * @Describe: TODO
 * @Date Create in  11:16 下午 2020/10/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaWorkerTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WorkerDao workerDao;

    @Autowired
    private WorkDaoDefinition daoDefinition;

    @Test
    public void testOpWorkerWithName () {
        logger.info("ok");
        logger.info("{}", JSON.toJSONString(workerDao.findByName("dhx")));
        logger.info("{}", JSON.toJSONString(daoDefinition.findByName("dhx")));
        logger.info("{}", JSON.toJSONString(workerDao.readByName("dhx")));
        logger.info("{}", JSON.toJSONString(workerDao.queryByName("dhx")));
        logger.info("{}", JSON.toJSONString(workerDao.getByName("dhx")));
        logger.info("{}", JSON.toJSONString(workerDao.streamByName("dhx")));

    }

    @Test
    public void testMoreConditionQuery () {
        logger.info("{}", JSON.toJSONString(workerDao.findByNameAndCity("dhx", "上海市")));
        logger.info("{}", JSON.toJSONString(
                workerDao.findByNameAndSalaryIsGreaterThan("dhx", 1000000L))
        );
    }

    @Test
    public void testQueryOp () {
        logger.info("{}", JSON.toJSONString(workerDao.getMaxIdWorker()));
    }

    @Test
    public void testQueryByParamOp () {
        logger.info("first {}", JSON.toJSONString(workerDao.findWorkerByFirstParam("dhx", 1000L)));
        logger.info("second {}", JSON.toJSONString(workerDao.findWorkerBySecondParam("dhx", 1000L)));
    }

    @Test
    public void testQueryColumn () {
        logger.info("{}", JSON.toJSONString(workerDao.getWorkerNameAndSalaryInfo()));
    }

    @Test
    public void testNativeQueryOp () throws InstantiationException, IllegalAccessException {
        //logger.info("{}",JSON.toJSONString(workerDao.findAllNativeQuery()));
        List<Map<String, Object>> result = workerDao.getWorkerNameAndSalaryInfoByNativeQuery();
        List<Worker> workers = new ArrayList<>(result.size());
        for (Map<String, Object> worker : result) {
            workers.add(
                    MapToEntity.mapToEntity(worker, Worker.class)
            );
        }
        logger.info("{}", workers.size());
        logger.info("{}", JSON.toJSONString(workers));
    }

    @Test
    public void testModify () {
        //Worker worker = workerDao.getMaxIdWorker();
        //worker.setSalary(100L);
        //workerDao.save(worker);
        logger.info("{}", workerDao.updateSalaryByName(10000L, "dhx"));

    }

    @Test
    public void testSortAndPageable () {
        Sort sort01 = new Sort(Sort.Direction.DESC, "id");
        Sort sort02 = new Sort(Sort.Direction.DESC, "salary", "id");
        Sort sort03 = new Sort(Sort.Direction.DESC, Arrays.asList("salary", "id"));

        List<Worker> workers1 = workerDao.findAll(sort01);
        List<Worker> workers2 = workerDao.findAll(sort02);
        List<Worker> workers3 = workerDao.findAll(sort03);

        List<Worker> workers4 = workerDao.findAllBySalaryGreaterThanEqual(1000L, sort01);
        List<Worker> workers5 = workerDao.findAllBySalaryGreaterThanEqual(100L, sort03);
        logger.info("workers1  -> {}", JSON.toJSONString(workers1, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat));
        logger.info("workers2  -> {}", JSON.toJSONString(workers2));
        logger.info("workers3  -> {}", JSON.toJSONString(workers3));
        logger.info("workers4  -> {}", JSON.toJSONString(workers4));
        logger.info("workers5  -> {}", JSON.toJSONString(workers5));
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info("workers6  -> {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workers5));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // page: -从0开始的所有页
        // size: -返回的页面大小
        // sort: -排序
        //new PageRequest(
        //int page, int size, Sort sort
        //);
        // 每一页有两个元素，取第一页的两个
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Order.desc("salary")));
        Page<Worker> workerPage = workerDao.findAll(pageable);
        logger.info("总页数：{}", workerPage.getTotalPages());
        logger.info("当前页索引(第几页):{}", workerPage.getTotalElements());
        logger.info("查询的结果信息:{}", workerPage.getContent());
        logger.info("查询的结果信息:{}", workerPage.getContent());
        logger.info("当前页上的元素数量:{}", workerPage.getNumberOfElements());
        logger.info("请求当前页的Pageable:{}", workerPage.getPageable());
        logger.info("页大小:{}", workerPage.getSize());
        logger.info("页的排序参数:{}", workerPage.getSort());
        logger.info("页面上是否有内容:{}", workerPage.hasContent());
        logger.info("是否有下一页:{}", workerPage.hasNext());
        logger.info("是否有上一页:{}", workerPage.hasPrevious());
        logger.info("当前页是否是最后一个:{}", workerPage.nextPageable());
        logger.info("下一页的Pageable:{}", workerPage.isLast());
        logger.info("上一页的Pageable:{}", workerPage.previousPageable());
    }
}

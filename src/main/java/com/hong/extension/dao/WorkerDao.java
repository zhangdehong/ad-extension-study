package com.hong.extension.dao;

import com.hong.extension.entity.Worker;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <h1>worker dao 接口定义</h1>
 *
 * @Author: ZhangDeHong
 * @Describe: TODO
 * @Date Create in  10:56 下午 2020/8/23
 */
@SuppressWarnings("all")
public interface WorkerDao extends JpaRepository<Worker, Long> {

    /**
     * <h2>只要方法名称符合JPA规范，就不需要写sql语句</h2>
     * 简单条件查询：查询方法以 find | read | get | query | stream 开头，他们都是同义词
     * delete | remove
     *
     * @param name
     * @return
     */
    Worker findByName (String name);

    Worker getByName (String name);

    Worker readByName (String name);

    Worker queryByName (String name);

    Worker streamByName (String name);

    /**
     * <h2>多条件查询 使用And进行连接</h2>
     * 条件的属性名称与个数要与参数的位置和个数一一对应
     *
     * @param name
     * @param city
     * @return
     */
    Worker findByNameAndCity (String name, String city);

    /**
     * 按工资条件  大于查询
     *
     * @param name
     * @param salary
     * @return
     */
    Worker findByNameAndSalaryIsGreaterThan (String name, Long salary);

    /**
     * 如果 JPA 定义的查询关键字不能满足我们的要求，可以使用@Query自定义查询SQL
     */
    /**
     * 查找最大id的worker对象
     *
     * @return
     */
    @Query("SELECT w FROM Worker w WHERE id = (SELECT MAX(id) FROM Worker )")
    Worker getMaxIdWorker ();

    /**
     * 如果查询有参数 采纳数有两种传递方式
     * <h2>第一种查询参数的传递方式</h2>
     */
    @Query("SELECT w FROM Worker w WHERE name = ?1 AND salary >= ?2")
    List<Worker> findWorkerByFirstParam (String name, Long salary);

    /**
     * <h2>第二种查询参数的传递方式</h2>
     *
     * @param name
     * @param salary
     * @return
     */
    @Query("SELECT w FROM Worker w WHERE name = :name AND salary >= :salary")
    List<Worker> findWorkerBySecondParam (@Param("name") String name, @Param("salary") Long salary);

    /**
     * <h2>查询指定的字段</h2>
     *
     * @return
     */
    @Query("SELECT new Worker (w.name,w.salary) FROM Worker w")
    List<Worker> getWorkerNameAndSalaryInfo ();

    // 原生查询
    @Query(value = "SELECT * FROM worker", nativeQuery = true)
    List<Worker> findAllNativeQuery ();

    @Query(value = "SELECT w.name,w.salary FROM worker w", nativeQuery = true)
    List<Map<String, Object>> getWorkerNameAndSalaryInfoByNativeQuery ();

    @Modifying
    @Transactional(readOnly = false)
    @Query(value = "UPDATE Worker SET salary = :salary WHERE name = :name")
    int updateSalaryByName (@Param("salary") Long salary, @Param("name") String name);

    List<Worker> findAllBySalaryGreaterThanEqual (Long salary, Sort sort);
}

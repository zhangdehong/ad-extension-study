package com.hong.extension.dao;

import com.hong.extension.entity.Worker;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @Author: ZhangDeHong
 * @Describe: TODO
 * @Date Create in  11:00 下午 2020/8/23
 */
@RepositoryDefinition(domainClass = Worker.class, idClass = Long.class)
public interface WorkDaoDefinition {

    Worker findByName (String name);

}

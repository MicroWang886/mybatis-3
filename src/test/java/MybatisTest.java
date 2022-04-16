/*
 *    Copyright 2009-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.submitted.permissions.Resource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 类名: MybatisTest
 * 说明: TODO
 * 时间: 2021-11-21 12:19
 * 作者: 王钟游
 **/
public class MybatisTest {

  @Test
  public void test1() throws IOException {
    //读取配置文件并转化成字符输入流，此时还没有进行真正的解析
    InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
    //解析配置文件封装到configuration对象中，生成defaultSqlSessionFactory并返回
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    //创建了DefaultSqlSession实例对象  设置事务不自动提交  完成executor对象的创建
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //根据statementId来从Configuration中获取对应的mappedStated对象，将查询任务委派给executor执行器
    List<Object> objects = sqlSession.selectList("namespace.id");
  }

  @Test
  public void test2() throws IOException {
    InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
    mapper.getAllUser();
  }

}

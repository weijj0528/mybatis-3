/**
 * Copyright 2009-2016 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ibatis.executor.statement;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 首先通过 ParameterHandler 完成 SQL 语句的实参绑定(如需要)，
 * 然后通过 java.sql.Statement 对象执行 SQL 语句并得到结果集，
 * 最后通过 ResultSetHandler 完成结果集的映射，得到结果对象并返回。
 *
 * @author Clinton Begin
 */
public interface StatementHandler {

  /**
   * Prepare statement.
   * 准备
   * 确认Statement
   * 设置超时时间
   * 设置缓存
   *
   * @param connection         the connection
   * @param transactionTimeout the transaction timeout
   * @return the statement
   * @throws SQLException the sql exception
   */
  Statement prepare(Connection connection, Integer transactionTimeout)
    throws SQLException;

  /**
   * Parameterize.
   * 参数设置
   * 通过 ParameterHandler 完成参数的绑定
   *
   * @param statement the statement
   * @throws SQLException the sql exception
   */
  void parameterize(Statement statement)
    throws SQLException;

  /**
   * Batch.
   * 批量操作
   *
   * @param statement the statement
   * @throws SQLException the sql exception
   */
  void batch(Statement statement)
    throws SQLException;

  /**
   * Update int.
   *
   * @param statement the statement
   * @return the int
   * @throws SQLException the sql exception
   */
  int update(Statement statement)
    throws SQLException;

  /**
   * Query list.
   *
   * @param <E>           the type parameter
   * @param statement     the statement
   * @param resultHandler the result handler
   * @return the list
   * @throws SQLException the sql exception
   */
  <E> List<E> query(Statement statement, ResultHandler resultHandler)
    throws SQLException;

  /**
   * Query cursor cursor.
   *
   * @param <E>       the type parameter
   * @param statement the statement
   * @return the cursor
   * @throws SQLException the sql exception
   */
  <E> Cursor<E> queryCursor(Statement statement)
    throws SQLException;

  /**
   * Gets bound sql.
   * 获得 SQL
   *
   * @return the bound sql
   */
  BoundSql getBoundSql();

  /**
   * Gets parameter handler.
   * 获取 ParameterHandler
   *
   * @return the parameter handler
   */
  ParameterHandler getParameterHandler();

}

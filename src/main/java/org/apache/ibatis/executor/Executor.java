/**
 * Copyright 2009-2015 the original author or authors.
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
package org.apache.ibatis.executor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Executor.
 *
 * @author Clinton Begin
 */
public interface Executor {

  /**
   * 空 ResultHandler 对象的枚举
   */
  ResultHandler NO_RESULT_HANDLER = null;

  /**
   * 更新 or 插入 or 删除，由传入的 MappedStatement 的 SQL 所决定
   *
   * @param ms        the ms
   * @param parameter the parameter
   * @return int
   * @throws SQLException the sql exception
   */
  int update(MappedStatement ms, Object parameter) throws SQLException;

  /**
   * 查询，带 ResultHandler + CacheKey + BoundSql
   *
   * @param <E>           the type parameter
   * @param ms            the ms
   * @param parameter     the parameter
   * @param rowBounds     the row bounds
   * @param resultHandler the result handler
   * @param cacheKey      the cache key
   * @param boundSql      the bound sql
   * @return list
   * @throws SQLException the sql exception
   */
  <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey cacheKey, BoundSql boundSql) throws SQLException;

  /**
   * Query list.
   * 查询，带 ResultHandler
   *
   * @param <E>           the type parameter
   * @param ms            the ms
   * @param parameter     the parameter
   * @param rowBounds     the row bounds
   * @param resultHandler the result handler
   * @return the list
   * @throws SQLException the sql exception
   */
  <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;

  /**
   * Query cursor cursor.
   * 查询，返回值为 Cursor
   *
   * @param <E>       the type parameter
   * @param ms        the ms
   * @param parameter the parameter
   * @param rowBounds the row bounds
   * @return the cursor
   * @throws SQLException the sql exception
   */
  <E> Cursor<E> queryCursor(MappedStatement ms, Object parameter, RowBounds rowBounds) throws SQLException;

  /**
   * Flush statements list.
   * 刷入批处理语句
   *
   * @return the list
   * @throws SQLException the sql exception
   */
  List<BatchResult> flushStatements() throws SQLException;

  /**
   * Commit.
   * 提交事务
   *
   * @param required the required
   * @throws SQLException the sql exception
   */
  void commit(boolean required) throws SQLException;

  /**
   * Rollback.
   * 回滚事务
   *
   * @param required the required
   * @throws SQLException the sql exception
   */
  void rollback(boolean required) throws SQLException;

  /**
   * Create cache key cache key.
   * 创建 CacheKey 对象
   *
   * @param ms              the ms
   * @param parameterObject the parameter object
   * @param rowBounds       the row bounds
   * @param boundSql        the bound sql
   * @return the cache key
   */
  CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql);

  /**
   * Is cached boolean.
   * 判断是否缓存
   *
   * @param ms  the ms
   * @param key the key
   * @return the boolean
   */
  boolean isCached(MappedStatement ms, CacheKey key);

  /**
   * Clear local cache.
   * 清除本地缓存
   */
  void clearLocalCache();

  /**
   * Defer load.
   * 延迟加载
   *
   * @param ms           the ms
   * @param resultObject the result object
   * @param property     the property
   * @param key          the key
   * @param targetType   the target type
   */
  void deferLoad(MappedStatement ms, MetaObject resultObject, String property, CacheKey key, Class<?> targetType);

  /**
   * Gets transaction.
   * 获得事务
   *
   * @return the transaction
   */
  Transaction getTransaction();

  /**
   * Close.
   * 关闭事务
   *
   * @param forceRollback the force rollback
   */
  void close(boolean forceRollback);

  /**
   * Is closed boolean.
   * 判断事务是否关闭
   *
   * @return the boolean
   */
  boolean isClosed();

  /**
   * Sets executor wrapper.
   * 设置包装的 Executor 对象
   *
   * @param executor the executor
   */
  void setExecutorWrapper(Executor executor);

}

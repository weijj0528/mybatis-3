/**
 * Copyright 2009-2019 the original author or authors.
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
package org.apache.ibatis.executor.resultset;

import org.apache.ibatis.cursor.Cursor;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * The interface Result set handler.
 *
 * @author Clinton Begin
 */
public interface ResultSetHandler {

  /**
   * Handle result sets list.
   * 处理 {@link java.sql.ResultSet} 成映射的对应的结果
   *
   * @param <E>  the type parameter
   * @param stmt the stmt
   * @return the list
   * @throws SQLException the sql exception
   */
  <E> List<E> handleResultSets(Statement stmt) throws SQLException;

  /**
   * Handle cursor result sets cursor.
   * 处理 {@link java.sql.ResultSet} 成 Cursor 对象
   *
   * @param <E>  the type parameter
   * @param stmt the stmt
   * @return the cursor
   * @throws SQLException the sql exception
   */
  <E> Cursor<E> handleCursorResultSets(Statement stmt) throws SQLException;

  /**
   * Handle output parameters.
   *
   * @param cs the cs
   * @throws SQLException the sql exception
   */
  void handleOutputParameters(CallableStatement cs) throws SQLException;

}

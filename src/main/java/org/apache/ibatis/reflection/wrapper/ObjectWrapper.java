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
package org.apache.ibatis.reflection.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;

import java.util.List;

/**
 * 对象包装器接口，基于 MetaClass 工具类，定义对指定对象的各种操作。
 * 或者可以说，ObjectWrapper 是 MetaClass 的指定类的具象化。代码如下：
 *
 * @author Clinton Begin
 */
public interface ObjectWrapper {
  /**
   * 获得值
   *
   * @param prop PropertyTokenizer 对象，相当于键
   * @return 值 object
   */
  Object get(PropertyTokenizer prop);

  /**
   * Set.
   * 设置值
   *
   * @param prop  the prop
   * @param value the value
   */
  void set(PropertyTokenizer prop, Object value);

  /**
   * Find property string.
   * {@link MetaClass#findProperty(String, boolean)}
   *
   * @param name                the name
   * @param useCamelCaseMapping the use camel case mapping
   * @return the string
   */
  String findProperty(String name, boolean useCamelCaseMapping);

  /**
   * Get getter names string [ ].
   * {@link MetaClass#getGetterNames()}
   *
   * @return the string [ ]
   */
  String[] getGetterNames();

  /**
   * Get setter names string [ ].
   * {@link MetaClass#getSetterNames()}
   *
   * @return the string [ ]
   */
  String[] getSetterNames();

  /**
   * Gets setter type.
   * {@link MetaClass#getSetterType(String)}
   *
   * @param name the name
   * @return the setter type
   */
  Class<?> getSetterType(String name);

  /**
   * Gets getter type.
   * {@link MetaClass#getGetterType(String)}
   *
   * @param name the name
   * @return the getter type
   */
  Class<?> getGetterType(String name);

  /**
   * Has setter boolean.
   * {@link MetaClass#hasSetter(String)}
   *
   * @param name the name
   * @return the boolean
   */
  boolean hasSetter(String name);

  /**
   * Has getter boolean.
   * {@link MetaClass#hasGetter(String)}
   *
   * @param name the name
   * @return the boolean
   */
  boolean hasGetter(String name);

  /**
   * Instantiate property value meta object.
   * {@link MetaObject#forObject(Object, ObjectFactory, ObjectWrapperFactory, ReflectorFactory)}
   *
   * @param name          the name
   * @param prop          the prop
   * @param objectFactory the object factory
   * @return the meta object
   */
  MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory);

  /**
   * Is collection boolean.
   * 是否为集合
   *
   * @return the boolean
   */
  boolean isCollection();

  /**
   * Add.
   * 添加元素到集合
   *
   * @param element the element
   */
  void add(Object element);

  /**
   * Add all.
   * 添加多个元素到集合
   *
   * @param <E>     the type parameter
   * @param element the element
   */
  <E> void addAll(List<E> element);

}

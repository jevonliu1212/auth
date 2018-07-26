package com.v5.utils;

import com.v5.entity.User;

public class ContextHolder {
	
  private static ThreadLocal<User> userContext = new ThreadLocal<>();
  
  
  /**
   * 设置用户上下文
   * 
   * @param userContext
   *            void
   */
  public static void set(User user) {
	  userContext.set(user);
  }

  /**
   * 获取用户上下文
   * 
   * @return UserContextDTO
   */
  public static User get() {
      return userContext.get();
  }

  /**
   * 删除用户上下文
   * 
   * void
   */
  public static void remove() {
	  userContext.remove();
  }

  
  
}

package com.xiaoteng.context;

import com.xiaoteng.context.current.CurrentUser;
import org.springframework.stereotype.Component;

@Component
public class UserContext {
    
    private static final ThreadLocal<CurrentUser> USER_HOLDER = new ThreadLocal<>();
    
    public static void setCurrentUser(CurrentUser currentUser) {
        USER_HOLDER.set(currentUser);
    }
    
    public static CurrentUser getCurrentUser() {
        return USER_HOLDER.get();
    }
    
    /**
     * 在Context中，我们提供了获取当前用户ID的方法
     * 还提供了获取当前用户的用户名的方法
     * @return
     */
    
    public static Long getUserId() {
        CurrentUser currentUser = USER_HOLDER.get();
        return currentUser == null ? null : currentUser.getUserId();
    }
    
    public static String getUsername() {
        CurrentUser currentUser = USER_HOLDER.get();
        return currentUser == null ? null : currentUser.getUsername();
    }
    
    public static void clear() {
        USER_HOLDER.remove();
    }
}

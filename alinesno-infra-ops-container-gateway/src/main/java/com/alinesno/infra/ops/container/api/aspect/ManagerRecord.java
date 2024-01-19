package com.alinesno.infra.ops.container.api.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户操作记录注解
 * 
 * 该注解用于标识需要记录用户操作日志的方法。可以应用在方法上。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class中存在，运行时可通过反射获取
@Target(ElementType.METHOD) // 目标是方法
public @interface ManagerRecord {

	// 记录类型
	enum RecordType {

		LOGIN_GET("login_access", "登录页面"), // 登录
		LOGIN_POST("login_validate", "登录验证"), // 登录
		LOGIN_REGISTER("login_register", "注册页面"), // 注册页面
		LOGOUT("logout", "退出系统"), // 登出
		OPERATE_ACTION("operate_action", "操作内容"), // 操作
		OPERATE_DELETE("operate_delete", "操作删除"), // 操作
		OPERATE_UPDATE("operate_update", "操作更新"), // 操作
		OPERATE_SAVE("operate_save", "操作保存"), // 操作

		// 其他记录类型...

		; // 重置密码

		RecordType(String type, String label) {
			this.type = type;
			this.label = label;
		}

		private String type;
		private String label;

		RecordType(String type) {
			this.type = type;
		}

		public String value() {
			return type;
		}

		public String label() {
			return label;
		}

		/**
		 * 通过类型查询对象
		 * 
		 * @param type 记录类型
		 * @return 对应的记录类型对象
		 */
		public static RecordType queryLabel(String type) {
			RecordType ce = null;
			for (RecordType e : RecordType.values()) {
				if (e.value().equals(type)) {
					ce = e;
					break;
				}
			}
			return ce;
		}
	}

	/**
	 * 记录类型
	 * 
	 * @return 记录类型
	 */
	RecordType type() default RecordType.LOGIN_GET;

	/**
	 * 其他说明
	 * 
	 * @return 其他说明
	 */
	String value() default "";

}

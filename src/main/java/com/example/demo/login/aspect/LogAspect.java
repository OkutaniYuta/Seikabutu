package com.example.demo.login.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	//コントローラーログの出力
	@Around("execution(* *..*.*Controller.*(..))")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("メソッド開始 : " + jp.getSignature());
		
		try {
			//メソッド実行
			Object result = jp.proceed();
			System.out.println("メソッド終了 : " + jp.getSignature());
			return result;
			
		} catch(Exception e) {
			System.out.println("メソッド以上終了 : " + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
	
	//UserDaoクラスのログ出力
	@Around("execution(* *..*.*UserDao*.*(..))")
	public Object daoLog(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("メソッド開始 : " + jp.getSignature());
		
		try {
			Object result = jp.proceed();
			System.out.println("メソッド終了 : " + jp.getSignature());
			return result;
		} catch(Exception e) {
			System.out.println("メソッド以上終了 : " + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
}
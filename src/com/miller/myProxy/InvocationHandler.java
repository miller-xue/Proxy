package com.miller.myProxy;

import java.lang.reflect.Method;

public interface InvocationHandler {
	
	public void invoke(Object obj,Method method);
}

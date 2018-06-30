package com.miller.myProxy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

/**
 * 手写代码实现JDK动态代理的过程：
 * 1. 声明一段源码，这段源码动态生成我们的动态代理；
 * 2. 把源码生成Java文件；
 * 3. 获取系统的Java编译器（JavaCompiler类似与javac）；
 * 4. 利用文件管理者StandardJavaFileManager,获取需要编译的文件（Iterable）；
 * 5. 调用编译的任务（）；
 * 6. 进行编译；
 * 7. 编译完成后会生成class文件；
 * 8. 把class文件加载到内存中；
 * 9. 产生一个代理类的对象，并返回该对象；
 * 10. 在测试类中；
 * 11. 测试类中创建一个InvocationHandler（专门做事务处理）；
 * @author Miller
 *
 */
public class Proxy {
	
	public static Object newProxyInstance(Class infce,InvocationHandler h) throws IOException, ClassNotFoundException, Exception, SecurityException {
		//1. 声明一段源码，这段源码动态生成我们的动态代理；
		StringBuffer methodStr = new StringBuffer();
		for(Method m : infce.getMethods()) {
			methodStr.append("\r\n" + 
					"	@Override\r\n" + 
					"	public void "+ m.getName() +"() {\r\n" + 
					"		try{ \r\n" +
					"			Method md = " + infce.getName() + ".class.getMethod(\"" 
								+ m.getName() + "\");" + "\r\n" +
					" 			h.invoke(this,md);\r\n" + 
					"		}catch(Exception e){\r\n" + 
					"			e.printStackTrace();\r\n" + 
					"		}\r\n" +
					"	}\r\n");
		}
		
		
		String str = "package com.miller.myProxy;\r\n" + 
					 "import com.miller.myProxy.InvocationHandler; \r\n" +
					 "import java.lang.reflect.Method;\r\n" +
				"\r\n" + 
				"public class $Proxy0 implements "+ infce.getName() +" {\r\n" + 
				"\r\n" + 
				"	private InvocationHandler h;" +
				"	\r\n" + 
				"	\r\n" + 
				"	public $Proxy0(InvocationHandler h) {\r\n" + 
				"		super();\r\n" + 
				"		this.h = h;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				methodStr+ 
				"\r\n" + 
				"}\r\n" + 
				"";
		// 2.1 把源码生成Java文件；
		String fileName = System.getProperty("user.dir")+ "/bin/com/miller/myProxy/$Proxy0.java";
		// 2.2把源码写入到本地
		FileUtils.writeByteArrayToFile(new File(fileName), str.getBytes());
		
		// 3. 获取系统的Java编译器（JavaCompiler类似与javac）；
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		// 4. 利用文件管理者StandardJavaFileManager,获取需要编译的文件（Iterable）；
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		// 4.1  获得文件
		Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(fileName);
		// 5. 调用编译的任务（）；
		CompilationTask t = compiler.getTask(null, fileManager, null, null, null, units);
		// 6. 进行编译
		t.call();
		fileManager.close();
		
		
		// 获得一个类加载器
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		// 7. 把class文件加载到内存中；
		Class c = loader.loadClass("com.miller.myProxy.$Proxy0");
		System.out.println(c.getName());
		
		
		// 8.拿到代理对象关于接口的构造器
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		// 创建一个新的实例返回给对象
		return ctr.newInstance(h);
	}
	
}

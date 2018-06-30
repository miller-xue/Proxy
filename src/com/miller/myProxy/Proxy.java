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


public class Proxy {
	public static Object newProxyInstance(Class infce,InvocationHandler h) throws IOException, ClassNotFoundException, Exception, SecurityException {
		
		
		String methodStr = "";
		for(Method m : infce.getMethods()) {
			methodStr += "\r\n" + 
					"	@Override\r\n" + 
					"	public void "+ m.getName() +"() {\r\n" + 
						"try{ \r\n" +
					"		Method md = " + infce.getName() + ".class.getMethod(\"" 
							+ m.getName() + "\");" + "\r\n" +
					" 		h.invoke(this,md);\r\n" + 
							"}catch(Exception e){e.printStackTrace();}\r\n" +
					"	}\r\n";
		}
		
		
		String str = "package com.miller.myProxy;\r\n" + 
					 " import com.miller.myProxy.InvocationHandler; \r\n" +
					 	"import java.lang.reflect.Method;\r\n" +
				"\r\n" + 
				"public class $Proxy0 implements "+ infce.getName() +" {\r\n" + 
				"\r\n" + 
				"	private InvocationHandler	h;" +
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
		//�����µĴ������java�ļ�
		String fileName = System.getProperty("user.dir")+ "/bin/com/miller/myProxy/$Proxy0.java";
		File file = new File(fileName);
		//д�뵽����
		FileUtils.writeByteArrayToFile(file, str.getBytes());
		
		//1.�õ�������
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		//2.�ļ�������
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		//3.����ļ�
		Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(fileName);
		//4.��������
		CompilationTask t = compiler.getTask(null, fileManager, null, null, null, units);
		//5.���б���
		t.call();
		fileManager.close();
		
		
		//����õ�class load���ڴ���
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		Class c = loader.loadClass("com.miller.myProxy.$Proxy0");
		System.out.println(c.getName());
		
		
		// �õ����������ڽӿڵĹ�����
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		// ����һ���µ�ʵ�����ظ�����
		return ctr.newInstance(h);
	}
	
}

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
 * ��д����ʵ��JDK��̬����Ĺ��̣�
 * 1. ����һ��Դ�룬���Դ�붯̬�������ǵĶ�̬����
 * 2. ��Դ������Java�ļ���
 * 3. ��ȡϵͳ��Java��������JavaCompiler������javac����
 * 4. �����ļ�������StandardJavaFileManager,��ȡ��Ҫ������ļ���Iterable����
 * 5. ���ñ�������񣨣���
 * 6. ���б��룻
 * 7. ������ɺ������class�ļ���
 * 8. ��class�ļ����ص��ڴ��У�
 * 9. ����һ��������Ķ��󣬲����ظö���
 * 10. �ڲ������У�
 * 11. �������д���һ��InvocationHandler��ר������������
 * @author Miller
 *
 */
public class Proxy {
	
	public static Object newProxyInstance(Class infce,InvocationHandler h) throws IOException, ClassNotFoundException, Exception, SecurityException {
		//1. ����һ��Դ�룬���Դ�붯̬�������ǵĶ�̬����
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
		// 2.1 ��Դ������Java�ļ���
		String fileName = System.getProperty("user.dir")+ "/bin/com/miller/myProxy/$Proxy0.java";
		// 2.2��Դ��д�뵽����
		FileUtils.writeByteArrayToFile(new File(fileName), str.getBytes());
		
		// 3. ��ȡϵͳ��Java��������JavaCompiler������javac����
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		// 4. �����ļ�������StandardJavaFileManager,��ȡ��Ҫ������ļ���Iterable����
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		// 4.1  ����ļ�
		Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(fileName);
		// 5. ���ñ�������񣨣���
		CompilationTask t = compiler.getTask(null, fileManager, null, null, null, units);
		// 6. ���б���
		t.call();
		fileManager.close();
		
		
		// ���һ���������
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		// 7. ��class�ļ����ص��ڴ��У�
		Class c = loader.loadClass("com.miller.myProxy.$Proxy0");
		System.out.println(c.getName());
		
		
		// 8.�õ����������ڽӿڵĹ�����
		Constructor ctr = c.getConstructor(InvocationHandler.class);
		// ����һ���µ�ʵ�����ظ�����
		return ctr.newInstance(h);
	}
	
}

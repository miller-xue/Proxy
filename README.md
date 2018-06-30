慕课网模式的秘密-代理模式Code
https://www.imooc.com/learn/214

#手写代码实现JDK动态代理的过程：
1. 声明一段源码，这段源码动态生成我们的动态代理；
2. 把源码生成Java文件；
3. 获取系统的Java编译器（JavaCompiler类似与javac）；
4. 利用文件管理者StandardJavaFileManager,获取需要编译的文件（Iterable）；
5. 调用编译的任务（）；
6. 进行编译；
7. 编译完成后会生成class文件；
8. 把class文件加载到内存中；
9. 产生一个代理类的对象，并返回该对象；
10. 在测试类中；
11. 测试类中创建一个InvocationHandler（专门做事务处理）；

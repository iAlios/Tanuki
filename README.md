# Tanuki
图执行控制器，主要用来学习使用计算图的方式来模拟python控制台等运行过程中执行代码的过程。

## [功能列表]
* 基本的图的构建和搜索
* 图的深度和广度的节点搜索
* 根据公式创建执行图
* 通过给定参数, 能完成简单的四则运算操作
* 支持括号优先级功能

## [未实现]
* FOR 循环列表(对于 FOR 或 CATCH 等类似的操作采用子执行序列的方式进行)
* 添加 Matrix 及 Vector 数据类型的支持
* 添加方向求导等函数

## 常见示例
如下是如果通过 GraphEngine 来执行四则运算：

	GraphEngine engine = new GraphEngine();
	engine.appendField("a", AnyObject.valueOf(2));
	engine.appendField("b", AnyObject.valueOf(3));
	engine.appendField("c", AnyObject.valueOf(4));
	engine.appendField("d", AnyObject.valueOf(5));
	engine.dumpFieldList();
	String func = "a + b";
	System.out.println(String.format("%s = %s", func, engine.exec(func)));
	func = "a + b * c";
	System.out.println(String.format("%s = %s", func, engine.exec(func)));
	func = "( a + b ) * c";
	System.out.println(String.format("%s = %s", func, engine.exec(func)));
	func = "( ( a + b ) * c ) * d";
	System.out.println(String.format("%s = %s", func, engine.exec(func)));

如上所示，通过创建简单的字段，然后使用功能定义即可执行。
![执行图](http://upload-images.jianshu.io/upload_images/6504531-fd3703117e0b8053.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

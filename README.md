编译原理实验
------------

简介
>作为编译原理这门课程的实验，根据要求用java完成了三个，分别是：词法分析、LL（1）分析、逆波兰。其中key_word.txt与separator.txt分别为词法分析里的关键字和分隔符，关键字与分隔符通过文件导入

###词法分析
#####实验内容
用VC++/VB/JAVA语言实现对C语言子集的源程序进行词法分析。通过输入源程序从左到右对字符串进行扫描和分解，依次输出各个单词的内部编码及单词符号自身值；若遇到错误则显示“Error”，然后跳过错误部分继续显示 ；同时进行标识符登记符号表的管理。
以下是实现词法分析设计的主要工作：
1. 从源程序文件中读入字符。
* 统计行数和列数用于错误单词的定位。
* 删除空格类字符，包括回车、制表符空格。
* 按拼写单词，并用（内码，属性）二元式表示。(属性值——token的机内表示)
* 如果发现错误则报告出错
* 根据需要是否填写标识符表供以后各阶段使用。  
	
单词的基本分类：
1. 关键字：由程序语言定义的具有固定意义的标识符。也称为保留字例如 if、    for、while、printf ；   单词种别码为1。
* 标识符：用以表示各种名字，如变量名、数组名、函数名；
* 常数： 任何数值常数。如 125, 1,0.5,3.1416；
* 运算符：+、-、*、/；
* 关系运算符： <、<=、= 、>、>=、<>；
* 分界符： ；、，、（、）、[、]；
#####实验结果
![词法分析](/images/wordAnalysis.png)  

###LL（1）预测分析
#####实验内容
1. 根据某一文法编制调试 LL （ 1 ）分析程序，以便对任意输入的符号串进行分析。
* 构造预测分析表，并利用分析表和一个栈来实现对上述程序设计语言的分析程序。
* 分析法的功能是利用LL（1）控制程序根据显示栈栈顶内容、向前看符号以及LL（1）分析表，对输入符号串自上而下的分析过程。
#####实验结果
![LL（1）预测分析](/images/ll1.png)  

###逆波兰
#####实验内容
将非后缀式用来表示的算术表达式转换为用逆波兰式来表示的算术表达式，并计算用逆波兰式来表示的算术表达式的值。
#####实验结果
![逆波兰](/images/rpl.png)  



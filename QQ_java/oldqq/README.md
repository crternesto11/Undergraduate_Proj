中文版
 1.该程序只用作小组童年怀旧，不用作任何商业用途，其中oldqq程序主要涉及java网络编程+JavaGUI，QQZone则涉及HTML与JAVA，以及少量的
Javascript、css语言等

2.由于qq空间以及qq的主体我们放在两个项目完成，通过将qq空间tomcat发布的方式，通过将qq聊天窗体的用户名传给页面，页面接收到相应的参数，
使用该参数得到对应的用户对象，由此实现一个空间按钮到qq空间的跳转

3.我们使用的是mysql数据库，下载我们的sql文件，首先修改数据库配置文件oldqq.src.com.MyTools中，以及修改qqzone\src\main\java\util中的DBUtil文件，
将数据库数据表、root以及密码改成自己的账户，便可实现与数据库的连接
	// 数据库地址和密码
	public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/oldqq?useUnicode=true&characterEncoding=utf8
&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
	public static final String JDBC_USER = "root";
	public static final String JDBC_PWD = "123456";

4.oldqq的分为服务端和客户端两个入口，服务端入口为mainWindow.java，客户端入口为login.java

5.注：如果数据库连接不上，注意查看mysql-connector-java-8.0.27.jar包导入的正确性，另外该项目需要maven和tomcat的配置，相关配置过程不做详述

English Version
﻿1. The program is only used as a group childhood nostalgia, not for any commercial purposes, which oldqq program mainly involves java network programming + JavaGUI, QQZone involves HTML and JAVA, as well as a small amount of Javascript, css language, etc.

2. due to qq space as well as the body of qq we put in two projects to complete, through the qq space tomcat release by passing the user name of the qq chat form to the page, the page receives the corresponding parameters, the page receives the corresponding user object. Use this parameter to get the corresponding user object, thus realizing a space button to qq space jump!

3. We use mysql database, download our sql file, first modify the database configuration file oldqq.src.com.MyTools, as well as modify qqzone\src\main\java\util in the DBUtil file. Change the database table, root and password to your own account to connect to the database.
	// Database address and password
	public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/oldqq?useUnicode=true&characterEncoding=utf8
&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true";
	public static final String JDBC_USER = "root";
	public static final String JDBC_PWD = "123456";

4. oldqq is divided into server-side and client-side two entrances, the server-side entry for the mainWindow.java, client-side entry for the login.java

5. Note: If the database is not connected, pay attention to view mysql-connector-java-8.0.27.jar package import correctness, in addition, the project requires maven and tomcat configuration, the relevant configuration process does not go into detail!

# 1) 安装MySQL.

在终端输入 sudo apt-get install mysql-server mysql-client mysql-workbench

# 2) 登录MySQL

在终端输入 mysql -u root -p 接下来会提示你输入密码，输入正确密码，即可进入。

# 3) MySQL的一些简单管理：

启动MySQL服务： sudo start mysql

停止MySQL服务： sudo stop mysql

修改 MySQL 的管理员密码： sudo mysqladmin -u root password newpassword

设置远程访问(正常情况下，mysql占用的3306端口只是在IP 127.0.0.1上监听，拒绝了其他IP的访问（通过netstat可以查看到）。取消本地监听需要修改 my.cnf 文件：)：

sudo vi /etc/mysql/my.cnf

bind-address = 127.0.0.1 //找到此内容并且注释

# 4）MySQL安装后的目录结构分析(此结构只针对于使用apt-get install 在线安装情况)：

数据库存放目录： /var/lib/mysql/

相关配置文件存放目录： /usr/share/mysql

相关命令存放目录： /usr/bin(mysqladmin mysqldump等命令)

启动脚步存放目录： /etc/rc.d/init.d/

# 5) 查看数据库使用字符集的情况

show variables like 'character%' 

修改编码，创建一个新文件: /etc/mysql/conf.d/utf8_charset.cnf  有下面这些内容 

sudo vi /etc/mysql/conf.d/utf8_charset.cnf

[mysqld]
character-set-server=utf8
collation-server=utf8_general_ci

[client]
default-character-set=utf8




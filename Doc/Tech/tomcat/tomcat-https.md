#https原理

##一、 什么是HTTPS

在说HTTPS之前先说说什么是HTTP，HTTP就是我们平时浏览网页时候使用的一种协议。HTTP协议传输的数据都是未加密的，也就是明文的，因此使用HTTP协议传输隐私信息非常不安全。为了保证这些隐私数据能加密传输，于是网景公司设计了SSL（Secure Sockets Layer）协议用于对HTTP协议传输的数据进行加密，从而就诞生了HTTPS。SSL目前的版本是3.0，被IETF（Internet Engineering Task Force）定义在RFC 6101中，之后IETF对SSL 3.0进行了升级，于是出现了TLS（Transport Layer Security） 1.0，定义在RFC 2246。实际上我们现在的HTTPS都是用的TLS协议，但是由于SSL出现的时间比较早，并且依旧被现在浏览器所支持，因此SSL依然是HTTPS的代名词，但无论是TLS还是SSL都是上个世纪的事情，SSL最后一个版本是3.0，今后TLS将会继承SSL优良血统继续为我们进行加密服务。目前TLS的版本是1.2，定义在RFC 5246中，暂时还没有被广泛的使用。


##二、 Https的工作原理

HTTPS在传输数据之前需要客户端（浏览器）与服务端（网站）之间进行一次握手，在握手过程中将确立双方加密传输数据的密码信息。TLS/SSL协议不仅仅是一套加密传输的协议，更是一件经过艺术家精心设计的艺术品，TLS/SSL中使用了非对称加密，对称加密以及HASH算法。握手过程的简单描述如下：

1.浏览器将自己支持的一套加密规则发送给网站。

2.网站从中选出一组加密算法与HASH算法，并将自己的身份信息以证书的形式发回给浏览器。证书里面包含了网站地址，加密公钥，以及证书的颁发机构等信息。

3.获得网站证书之后浏览器要做以下工作：

a) 验证证书的合法性（颁发证书的机构是否合法，证书中包含的网站地址是否与正在访问的地址一致等），如果证书受信任，则浏览器栏里面会显示一个小锁头，否则会给出证书不受信的提示。

b) 如果证书受信任，或者是用户接受了不受信的证书，浏览器会生成一串随机数的密码，并用证书中提供的公钥加密。

c) 使用约定好的HASH计算握手消息，并使用生成的随机数对消息进行加密，最后将之前生成的所有信息发送给网站。

4.网站接收浏览器发来的数据之后要做以下的操作：

a) 使用自己的私钥将信息解密取出密码，使用密码解密浏览器发来的握手消息，并验证HASH是否与浏览器发来的一致。

b) 使用密码加密一段握手消息，发送给浏览器。

5.浏览器解密并计算握手消息的HASH，如果与服务端发来的HASH一致，此时握手过程结束，之后所有的通信数据将由之前浏览器生成的随机密码并利用对称加密算法进行加密。

这里浏览器与网站互相发送加密的握手消息并验证，目的是为了保证双方都获得了一致的密码，并且可以正常的加密解密数据，为后续真正数据的传输做一次测试。另外，HTTPS一般使用的加密与HASH算法如下：

非对称加密算法：RSA，DSA/DSS

对称加密算法：AES，RC4，3DES

HASH算法：MD5，SHA1，SHA256

其中非对称加密算法用于在握手过程中加密生成的密码，对称加密算法用于对真正传输的数据进行加密，而HASH算法用于验证数据的完整性。由于浏览器生成的密码是整个数据加密的关键，因此在传输的时候使用了非对称加密算法对其加密。非对称加密算法会生成公钥和私钥，公钥只能用于加密数据，因此可以随意传输，而网站的私钥用于对数据进行解密，所以网站都会非常小心的保管自己的私钥，防止泄漏。

TLS握手过程中如果有任何错误，都会使加密连接断开，从而阻止了隐私信息的传输。


#tomcat配置https方法


##1. 为服务器生成证书

```shell
keytool -genkey -v -alias "tomcat" -keyalg "RSA" -keystore ~/tools/apache-tomcat-7.0.70/tomcat.keystore -validity 36500
```

参数简要说明：
`-keystore` ~/tools/apache-tomcat-7.0.70/tomcat.keystore 含义是将证书文件的保存路径，证书文件名称是tomcat.keystore ；
`-validity` 36500 含义是证书有效期，36500表示100年，默认值是90天 
`-alias` “tomcat”为自定义证书名称

在命令行填写必要参数：
A、 输入keystore密码：此处需要输入大于6个字符的字符串。
B、 “您的名字与姓氏是什么？”这是必填项，并且必须是TOMCAT部署主机的域名或者IP[如：gbcom.com 或者 10.1.25.251]（就是你将来要在浏览器中输入的访问地址），否则浏览器会弹出警告窗口，提示用户证书与所在域不匹配。在本地做开发测试时，应填入“localhost”。
C、 你的组织单位名称是什么？”、“您的组织名称是什么？”、“您所在城市或区域名称是什么？”、“您所在的州或者省份名称是什么？”、“该单位的两字母国家代码是什么？”可以按照需要填写也可以不填写直接回车，在系统询问“正确吗？”时，对照输入信息，如果符合要求则使用键盘输入字母“y”，否则输入“n”重新填写上面的信息。
D、 输入<tomcat>的主密码，这项较为重要，会在tomcat配置文件中使用，建议输入与keystore的密码一致，设置其它密码也可以，完成上述输入后，直接回车则在你在第二步中定义的位置找到生成的文件。


##2. 为客户端生成证书

为浏览器生成证书，以便让服务器来验证它。为了能将证书顺利导入至IE和Firefox，证书格式应该是PKCS12，因此，使用如下命令生成：

```shell
keytool -genkey -v -alias clientKey -keyalg RSA -storetype PKCS12 -keystore ~/tools/apache-tomcat-7.0.70/clientKey.p12
```

对应的证书库存放在“~/tools/apache-tomcat-7.0.70/clientKey.p12”，客户端的CN可以是任意值。双击mykey.p12文件，即可将证书导入至浏览器（客户端）。


##3. 让服务器信任客户端证书

由于是双向SSL认证，服务器必须要信任客户端证书，因此，必须把客户端证书添加为服务器的信任认证。由于不能直接将PKCS12格式的证书库导入，必须先把客户端证书导出为一个单独的CER文件，使用如下命令：

```shell
keytool -export -alias clientKey -keystore ~/tools/apache-tomcat-7.0.70/clientKey.p12 -storetype PKCS12 -storepass password -rfc -file ~/tools/apache-tomcat-7.0.70/clientKey.cer 
```

(clientKey为自定义与客户端定义的clientKey要一致，password是你设置的密码)。
通过以上命令，客户端证书就被我们导出到“~/tools/apache-tomcat-7.0.70/mykey.cer”文件了。

下一步，是将该文件导入到服务器的证书库，添加为一个信任证书使用命令如下：

```shell
keytool -import -v -file ~/tools/apache-tomcat-7.0.70/clientKey.cer -keystore ~/tools/apache-tomcat-7.0.70/tomcat.keystore
```

通过list命令查看服务器的证书库，可以看到两个证书，一个是服务器证书，一个是受信任的客户端证书：

```shell
keytool -list -keystore ~/tools/apache-tomcat-7.0.70/tomcat.keystore
```

(tomcat为你设置服务器端的证书名)。


##4. 让客户端信任服务器证书

由于是双向SSL认证，客户端也要验证服务器证书，因此，必须把服务器证书添加到浏览的“受信任的根证书颁发机构”。由于不能直接将keystore格式的证书库导入，必须先把服务器证书导出为一个单独的CER文件，使用如下命令：

```shell
keytool -keystore ~/tools/apache-tomcat-7.0.70/tomcat.keystore -export -alias tomcat -file ~/tools/apache-tomcat-7.0.70/tomcat.cer
```
 
(tomcat为你设置服务器端的证书名)。
 
通过以上命令，服务器证书就被我们导出到“~/tools/apache-tomcat-7.0.70/tomcat.cer”文件了。

导入服务器端的证书生成客户端的证书：

```shell
keytool -import -v -alias tomcat -file ~/tools/apache-tomcat-7.0.70/tomcat.cer -keystore ~/tools/apache-tomcat-7.0.70/client.truststore
```


##5. 配置Tomcat服务器

打开Tomcat根目录下的/conf/server.xml，找到Connector port="8443"配置段，修改为如下：

```xml
	<Connector port="8443"
           protocol="HTTP/1.1"   
           minSpareThreads="150" maxSpareThreads="75"   
           enableLookups="true" disableUploadTimeout="true"     
           acceptCount="100" maxThreads="200"   
           scheme="https" secure="true" SSLEnabled="true"   
           clientAuth="false" sslProtocol="TLS"   
           keystoreFile="./tomcat.keystore"     
           keystorePass="111111"
           truststoreFile="./client.truststore"
           truststorePass="111111"   
  	/>
```

（tomcat要与生成的服务端证书名一致）

属性说明：

clientAuth:设置是否双向验证，默认为false，设置为true代表双向验证

keystoreFile:服务器证书文件路径

keystorePass:服务器证书密码

truststoreFile:用来验证客户端证书的根证书，此例中就是服务器证书

truststorePass:根证书密码










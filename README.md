this is the cs 352 project of Yiqian Li, Yifan Zhang and Guangyue Yu.

checklist:
去除while loop（这个是因为我没审题XD 测试的时候留着的话还挺好用的 就是最终上传之前要把client和server里的loop删掉）
本地连接 ✅
支持循环 ✅
支持多线程 ✅
支持指令：
    GET
    POST
    HEAD
    DELETE ✅
    PUT ✅
    LINK ✅
    UNLINK ✅
支持header line ✅
支持timeout
thread监管

代码进程：
  现在client基本完工了 主要是server还没写完 没写完的在上面checklist里面都有写QAQ thread监管这个东西我来负责就好了 别的拜托了！！

******Important******
**传输多行的秘诀！！！**
读的时候只需要再InFromClient.readLine()一次就可以了！
如果要往外输出多行的话 在一句话后面加一个回车符号'\n'然后再加上下一行 最后用'\n'结束就行

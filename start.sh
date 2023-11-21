#! /bin/bash
#! /bin/bash


#删除日志文件
file="nohup.out"
# -f 参数判断 $file 是否存在
if [  -f "$file" ]; then
 rm "$file"
fi
  COORDINATE_NAME=deyi-daxie-admin.jar
  COORDINATE_ProcNumber=`ps -ef |grep -w $COORDINATE_NAME|grep -v grep|wc -l`
if [ $COORDINATE_ProcNumber -le 0 ];then
		nohup java -Xms256m -Xmx512m -jar $COORDINATE_NAME --server.port=8089 &
else
   PID=`ps -ef | grep "$COORDINATE_NAME" | grep -v "grep" | awk '{print $2}'`
   echo  "$PID"
   echo "$COORDINATE_NAME is  running..$COORDINATE_ProcNumber"
	 kill -9 $PID
   echo "kill $COORDINATE_NAME success"
   nohup java -Xms256m -Xmx512m -jar $COORDINATE_NAME --server.port=8089 &
   echo "restart $COORDINATE_NAME now "
fi

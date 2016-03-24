We created this project to demonstrate a dependency conflict between spring-cloud-sleuth and spring-jms.

In order to reproduce the conflict:

1) Build the jar with `gradle build`

2) Execute the jar with `java -jar sleuth-jms-spike/build/libs/sleuth-spike-1.0-SNAPSHOT.jar`

3) You should see a stacktrace showing a `BeanCreationException: Error creating bean with name 'oracleAdvancedQueueListener'` with a root cause of: 

```
Caused by: org.aspectj.apache.bcel.classfile.ClassFormatException: Index 489 into constant pool (size:368) is invalid
	at org.aspectj.apache.bcel.classfile.ConstantPool.getConstant(ConstantPool.java:119)
	at org.aspectj.apache.bcel.classfile.ConstantPool.getConstantUtf8(ConstantPool.java:273)
	at org.aspectj.apache.bcel.classfile.Attribute.readAttribute(Attribute.java:113)
```


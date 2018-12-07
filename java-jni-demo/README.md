# Very simple Java JNI demo

## create JNI bindings
javac -h . Display.java

## compile c
gcc -I"/usr/lib/jvm/java-8-openjdk-armhf/include" -I"/usr/lib/jvm/java-8-openjdk-armhf/include/linux" -lc -shared -o libDisplay.so Display.c

## compile java
javac Display.java

## run test
java -Djava.library.path=. Display

## clean 
rm Display.h libDisplay.so Display.class


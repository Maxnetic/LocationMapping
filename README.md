# LocationMapping



### Compile to Library

Place the folder in `~/Documents/Processing/libraries`.

Compile and jar all Java Classes and the Font Files from `library/` (it isimportant to be in the correct directory to jar successfully) with the following commands:

```
$ javac ../src/locationmapping/*.java -d library/ -cp ../src/libs/core.jar:../src/libs/unfolding.jar:../src/libs/jodatime.jar

$ jar -cf locationmapping.jar locationmapping/ Courier.vlw FontAwesome.vlw
```

### Create JavaDocs

Javadocs should be created with the following command from the `src/` directory:
```
$ javadoc -d ../reference/ -link https://docs.oracle.com/javase/8/docs/api/ -link http://www.joda.org/joda-time/apidocs/ -link http://unfoldingmaps.org/javadoc/ -link http://processing.org/reference/javadoc/core/ -classpath libs/unfolding.jar:libs/core.jar:libs/jodatime.jar -sourcepath ./ locationmapping
```
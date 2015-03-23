# LocationMapping



### Compile to Library

Place the folder in `~/Documents/Processing/libraries`.

Compile all Java Classes in `src/locationmapping` with the following commands:

```
$ javac src/locationmapping/*.java -d library/ -cp src/libs/core.jar:src/libs/unfolding.jar
$ jar -cf library/loactionmapping.jar library/locationmapping/
$ javadoc src/locationmapping/*.java -d reference/
```
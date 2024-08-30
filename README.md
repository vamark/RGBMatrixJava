# RGB Matrix Java Wrapper
Java wrapper for the [rpi-rgb-led-matrix](https://github.com/hzeller/rpi-rgb-led-matrix) library

Build:
```mvn clean compile```

Test:
```mvn test```

Run:
```sudo java -cp target/classes/ -Djava.library.path=target/jni-wrapper/ io.vagvolgyi.rgbmatrix.Main```
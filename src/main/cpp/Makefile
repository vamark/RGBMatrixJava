JAVA_HOME ?= $(shell echo $$JAVA_HOME)
INCLUDE_DIRS = -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -Irpi-rgb-led-matrix/include
LIB_DIRS = -Lrpi-rgb-led-matrix/lib
LIBS = -lrgbmatrix
CXX = g++
CXXFLAGS = -c -fPIC -Wall -std=c++11
LDFLAGS = -shared -fPIC -lc
OUTPUT_DIR = ../../../target/jni-wrappers
TARGET = $(OUTPUT_DIR)/librgbmatrixjni.so
OBJECT = $(OUTPUT_DIR)/io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI.o
SOURCE = io_vagvolgyi_ledmatrix_jni_RGBMatrixJNI.cpp

all: $(TARGET)

$(OUTPUT_DIR):
	mkdir -p $(OUTPUT_DIR)

$(OBJECT): $(SOURCE) | $(OUTPUT_DIR)
	$(CXX) $(CXXFLAGS) $(INCLUDE_DIRS) $< -o $@

$(TARGET): $(OBJECT)
	$(CXX) $(LDFLAGS) $(LIB_DIRS) -o $@ $^ $(LIBS)

clean:
	rm -f $(OBJECT) $(TARGET)
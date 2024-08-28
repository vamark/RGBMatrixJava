JAVA_HOME ?= $(shell echo $$JAVA_HOME)
INCLUDE_DIRS = -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -Irpi-rgb-led-matrix/include
LIB_DIRS = -Lrpi-rgb-led-matrix/lib
LIBS = -lrgbmatrix
CXX = g++
CXXFLAGS = -c -fPIC -Wall -std=c++17 -MMD -MP
LDFLAGS = -shared -fPIC -lc
OUTPUT_DIR = ../../../target/jni-wrapper
TARGET = $(OUTPUT_DIR)/librgbmatrixjni.so
OBJECTS = $(patsubst %.cpp,$(OUTPUT_DIR)/%.o,$(SOURCES))
SOURCES = $(wildcard *.cpp converter/*.cpp bridge/*.cpp)
DEPS = $(OBJECTS:.o=.d)

all: $(TARGET)

$(OUTPUT_DIR):
	mkdir -p $(OUTPUT_DIR)/converter
	mkdir -p $(OUTPUT_DIR)/bridge

$(OUTPUT_DIR)/%.o: %.cpp | $(OUTPUT_DIR)
	$(CXX) $(CXXFLAGS) $(INCLUDE_DIRS) $< -o $@

$(TARGET): $(OBJECTS)
	$(CXX) $(LDFLAGS) $(LIB_DIRS) -o $@ $^ $(LIBS)

clean:
	rm -f $(OBJECTS) $(TARGET) $(DEPS)

-include $(DEPS)
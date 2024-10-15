JAVA_HOME ?= $(shell echo $$JAVA_HOME)
INCLUDE_DIRS = -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -Irpi-rgb-led-matrix/include
LIB_DIRS = -Lrpi-rgb-led-matrix/lib
LIBS = -lrgbmatrix
CXX = g++
CXXFLAGS = -c -fPIC -Wall -std=c++17 -MMD -MP
LDFLAGS = -shared -fPIC -lc
OUTPUT_DIR = ../../../target/classes
BUILD_DIR = ../../../target/jni
TARGET = $(OUTPUT_DIR)/librgbmatrixjni.so
OBJECTS = $(patsubst %.cpp,$(BUILD_DIR)/%.o,$(SOURCES))
SOURCES = $(wildcard *.cpp converter/*.cpp bridge/*.cpp)
DEPS = $(OBJECTS:.o=.d)

all: $(TARGET)

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)/converter
	mkdir -p $(BUILD_DIR)/bridge

$(OUTPUT_DIR):
	mkdir -p $(OUTPUT_DIR)

$(BUILD_DIR)/%.o: %.cpp | $(BUILD_DIR)
	$(CXX) $(CXXFLAGS) $(INCLUDE_DIRS) $< -o $@

$(TARGET): $(OBJECTS) | $(OUTPUT_DIR)
	$(CXX) $(LDFLAGS) $(LIB_DIRS) -o $@ $^ $(LIBS)

clean:
	rm -f $(OBJECTS) $(TARGET) $(DEPS)

-include $(DEPS)
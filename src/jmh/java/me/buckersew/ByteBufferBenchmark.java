package me.buckersew;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferBenchmark {
    ByteBuffer _nativeBufferLE;
    ByteBuffer _heapBufferLE;
    ByteBuffer _nativeBufferNativeOrder;
    ByteBuffer _heapBufferNativeOrder;

    @Setup
    public void createBuffers() {
        _nativeBufferLE = ByteBuffer.allocateDirect(1024).order(ByteOrder.LITTLE_ENDIAN);
        _heapBufferLE = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN);
        _nativeBufferNativeOrder = ByteBuffer.allocateDirect(1024).order(ByteOrder.nativeOrder());
        _heapBufferNativeOrder = ByteBuffer.allocate(1024).order(ByteOrder.nativeOrder());
    }

    @Benchmark
    public ByteBuffer benchmarkNativeLE() {
        _nativeBufferLE.putLong(345L);
        _nativeBufferLE.putInt(20);
        // by returning we are making sure the VM doesn't optimise this for dead code
        return _nativeBufferLE;
    }

    @Benchmark
    public ByteBuffer benchmarkHeapLE() {
        _heapBufferLE.putLong(345L);
        _heapBufferLE.putInt(20);
        // by returning we are making sure the VM doesn't optimise this for dead code
        return _heapBufferLE;
    }

    @Benchmark
    public ByteBuffer benchmarkNativeNativeOrder() {
        _nativeBufferNativeOrder.putLong(345L);
        _nativeBufferNativeOrder.putInt(20);
        // by returning we are making sure the VM doesn't optimise this for dead code
        return _nativeBufferNativeOrder;
    }

    @Benchmark
    public ByteBuffer benchmarkHeapNativeOrder() {
        _heapBufferNativeOrder.putLong(345L);
        _heapBufferNativeOrder.putInt(20);
        // by returning we are making sure the VM doesn't optimise this for dead code
        return _heapBufferNativeOrder;
    }
}

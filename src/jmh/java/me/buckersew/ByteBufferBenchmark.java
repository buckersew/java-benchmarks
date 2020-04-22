package me.buckersew;

import org.openjdk.jmh.annotations.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferBenchmark {
    private static final byte[] name = "TESTTEST".getBytes();

    @State(Scope.Benchmark) // shared across all threads - given only one thread irrelevant
    public static class BufferState {
        ByteBuffer nativeBufferLE;
        ByteBuffer heapBufferLE;
        ByteBuffer nativeBufferNativeOrder;
        ByteBuffer heapBufferNativeOrder;

        @Setup(Level.Trial) // once for each full run of the benchmark
        public void createBuffers() {
            nativeBufferLE = ByteBuffer.allocateDirect(1024).order(ByteOrder.LITTLE_ENDIAN);
            heapBufferLE = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN);
            nativeBufferNativeOrder = ByteBuffer.allocateDirect(1024).order(ByteOrder.nativeOrder());
            heapBufferNativeOrder = ByteBuffer.allocate(1024).order(ByteOrder.nativeOrder());
        }
    }
    @Benchmark
    public ByteBuffer benchmarkNativeLE(BufferState state) {
        state.nativeBufferLE.clear();
        state.nativeBufferLE.putLong(345L);
        state.nativeBufferLE.putInt(20);
        state.nativeBufferLE.put(name);
        state.nativeBufferLE.flip();
        // by returning we are making sure the VM doesn't optimise this for dead code
        return state.nativeBufferLE;
    }

    @Benchmark
    public ByteBuffer benchmarkHeapLE(BufferState state) {
        state.heapBufferLE.clear();
        state.heapBufferLE.putLong(345L);
        state.heapBufferLE.putInt(20);
        state.heapBufferLE.put(name);
        state.heapBufferLE.flip();
        // by returning we are making sure the VM doesn't optimise this for dead code
        return state.heapBufferLE;
    }

    @Benchmark
    public ByteBuffer benchmarkNativeNativeOrder(BufferState state) {
        state.nativeBufferNativeOrder.clear();
        state.nativeBufferNativeOrder.putLong(345L);
        state.nativeBufferNativeOrder.putInt(20);
        state.nativeBufferNativeOrder.put(name);
        state.nativeBufferNativeOrder.flip();
        // by returning we are making sure the VM doesn't optimise this for dead code
        return state.nativeBufferNativeOrder;
    }

    @Benchmark
    public ByteBuffer benchmarkHeapNativeOrder(BufferState state) {
        state.heapBufferNativeOrder.clear();
        state.heapBufferNativeOrder.putLong(345L);
        state.heapBufferNativeOrder.putInt(20);
        state.heapBufferNativeOrder.put(name);
        state.heapBufferNativeOrder.flip();
        // by returning we are making sure the VM doesn't optimise this for dead code
        return state.heapBufferNativeOrder;
    }
}

package me.buckersew;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.infra.Blackhole;

public class OverriddenBenchmark {

    @Benchmark
    public void benchmarkSingleImplementation(Blackhole blackhole) {
        Processor one = new Processor1();
        blackhole.consume(one.process(45));
    }

    @Benchmark
    public void benchmarkTwoImplementations(Blackhole blackhole) {
        Processor one = new Processor1();
        Processor two = new Processor1SubClass1();
        blackhole.consume(one.process(45));
        blackhole.consume(two.process(45));
    }
}

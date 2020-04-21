package me.buckersew;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.infra.Blackhole;

public class InterfaceBenchmark {

    @Benchmark
    public void benchmarkSingleImplementation(Blackhole blackhole) {
        Processor one = new Processor2();
        blackhole.consume(one.process(45));
    }

    @Benchmark
    public void benchmarkTwoImplementations(Blackhole blackhole) {
        Processor one = new Processor2();
        Processor two = new Processor3();
        blackhole.consume(one.process(45));
        blackhole.consume(two.process(45));
    }

    @Benchmark
    public void benchmarkThreeImplementations(Blackhole blackhole) {
        Processor one = new Processor2();
        Processor two = new Processor3();
        Processor three = new Processor4();
        blackhole.consume(one.process(45));
        blackhole.consume(two.process(45));
        blackhole.consume(three.process(45));
    }
}

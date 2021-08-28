package com.andybalham.orchestrator.core;

@FunctionalInterface
public interface IIsMatch<TCriteria> {
    boolean invoke(TCriteria criteria);
}

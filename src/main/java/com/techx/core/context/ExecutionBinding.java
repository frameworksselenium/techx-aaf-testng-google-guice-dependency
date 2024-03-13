package com.techx.core.context;

import com.google.inject.AbstractModule;


public class ExecutionBinding extends AbstractModule {

    @Override
    public void configure() {
    bind(IExecutionContext.class).to(ExecutionContext.class);
    }
}

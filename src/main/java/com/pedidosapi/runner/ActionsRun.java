package com.pedidosapi.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActionsRun {

    private final DatabaseSeeder databaseSeeder;

    public void dbSeed() {
        this.databaseSeeder.seed();
    }

    public void dbFresh(boolean seed) {
        this.databaseSeeder.fresh();

        if (seed) {
            dbSeed();
        }
    }

}

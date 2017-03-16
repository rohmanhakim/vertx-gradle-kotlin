package com.rohmanhakim.vertxgradle

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future

class SampleVerticle : AbstractVerticle() {

    override fun start(startFuture: Future<Void>) {
        vertx.createHttpServer()
                .requestHandler { r -> r.response().end("<h1>Hello World!</h1>") }
                .listen(8080) { result ->
                    if (result.succeeded()) {
                        startFuture.complete()
                    } else {
                        startFuture.fail(result.cause())
                    }
                }
    }
}

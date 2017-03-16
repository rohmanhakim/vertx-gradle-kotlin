package com.rohmanhakim.vertxgradle

import io.vertx.core.Vertx
import io.vertx.ext.unit.TestContext
import io.vertx.ext.unit.junit.VertxUnitRunner
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(VertxUnitRunner::class)
class SampleVerticleTest {

    private val vertx: Vertx = Vertx.vertx()

    @Before fun setUp(context: TestContext) {
        vertx.deployVerticle(SampleVerticle::class.java.name, context.asyncAssertSuccess())
    }

    @After fun tearDown(context: TestContext) {
        vertx.close(context.asyncAssertSuccess<Void>())
    }

    @Test fun start(context: TestContext) {
        val async = context.async()
        vertx.createHttpClient().getNow(8080, "localhost", "/") { response ->
            response.handler { body ->
                context.assertTrue(body.toString().contains("Hello"))
                async.complete()
            }
        }
    }

}
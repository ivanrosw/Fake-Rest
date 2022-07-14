package io.github.ivanrosw.fakerest.core.controller;

import io.github.ivanrosw.fakerest.core.model.ControllerData;
import io.github.ivanrosw.fakerest.core.model.ControllerSaveInfoMode;
import io.github.ivanrosw.fakerest.core.model.ControllerConfig;
import io.github.ivanrosw.fakerest.core.utils.HttpUtils;
import io.github.ivanrosw.fakerest.core.utils.JsonUtils;
import io.github.ivanrosw.fakerest.core.utils.SystemUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * Base class for all CRUD and Groovy controllers
 */
@Slf4j
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public abstract class FakeController implements BaseController {

    protected static final String KEY_ALREADY_EXIST = "key [%s] already exist";
    protected static final String DATA_NOT_JSON = "data [%s] is not json";
    protected static final String KEY_NOT_FOUND = "key [%s] not found";

    protected static final String DESCRIPTION_PARAM = "description";

    protected ControllerSaveInfoMode saveInfoMode;
    protected ControllerData controllerData;
    protected ControllerConfig controllerConfig;

    protected JsonUtils jsonUtils;
    protected HttpUtils httpUtils;
    protected SystemUtils systemUtils;

    protected void delay() {
        if (controllerConfig.getDelayMs() > 0) {
            try {
                systemUtils.sleep(controllerConfig.getDelayMs());
            } catch (Exception e) {
                log.error("Interrupt error", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}

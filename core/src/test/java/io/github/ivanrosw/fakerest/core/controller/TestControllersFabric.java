package io.github.ivanrosw.fakerest.core.controller;

import io.github.ivanrosw.fakerest.core.model.*;
import io.github.ivanrosw.fakerest.core.utils.HttpUtils;
import io.github.ivanrosw.fakerest.core.utils.JsonUtils;
import io.github.ivanrosw.fakerest.core.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
public class TestControllersFabric {

    @Autowired
    private ControllerData controllerData;
    @Autowired
    private JsonUtils jsonUtils;
    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    private SystemUtils systemUtils;

    public ReadController createStaticReadController(String uri, RequestMethod method, String answer, long delayMs) {
        ControllerConfig config = createControllerConfig(uri, method, ControllerFunctionMode.READ, answer, delayMs,
                false, null);
        return createReadController(config, ControllerSaveInfoMode.STATIC);
    }

    public ReadController createCollectionAllReadController(String uri, RequestMethod method, long delayMs) {
        ControllerConfig config = createControllerConfig(uri, method, ControllerFunctionMode.READ, null, delayMs,
                false, null);
        return createReadController(config, ControllerSaveInfoMode.COLLECTION_ALL);
    }

    public ReadController createCollectionOneReadController(String uri, RequestMethod method, long delayMs) {
        ControllerConfig config = createControllerConfig(uri, method, ControllerFunctionMode.READ, null, delayMs,
                false, null);
        return createReadController(config, ControllerSaveInfoMode.COLLECTION_ONE);
    }

    public DeleteController createStaticDeleteController(String uri, RequestMethod method, String answer, long delayMs) {
        ControllerConfig config = createControllerConfig(uri, method, ControllerFunctionMode.DELETE, answer, delayMs,
                false, null);
        return createDeleteController(config, ControllerSaveInfoMode.STATIC);
    }

    public DeleteController createCollectionOneDeleteController(String uri, RequestMethod method, long delayMs) {
        ControllerConfig config = createControllerConfig(uri, method, ControllerFunctionMode.DELETE, null, delayMs,
                false, null);
        return createDeleteController(config, ControllerSaveInfoMode.COLLECTION_ONE);
    }

    public CreateController createStaticCreateController(String uri, RequestMethod method, String answer, long delayMs) {
        ControllerConfig config = createControllerConfig(uri, method, ControllerFunctionMode.READ, answer, delayMs,
                false, null);
        return createCreateController(config, ControllerSaveInfoMode.STATIC);
    }

    public UpdateController createStaticUpdateController(String uri, RequestMethod method, String answer, long delayMs) {
        ControllerConfig config = createControllerConfig(uri, method, ControllerFunctionMode.READ, answer, delayMs,
                false, null);
        return createUpdateController(config, ControllerSaveInfoMode.STATIC);
    }

    private ControllerConfig createControllerConfig(String uri, RequestMethod method, ControllerFunctionMode functionMode,
                                                    String answer, long delayMs, boolean generateId, String groovyScript) {
        ControllerConfig config = new ControllerConfig();
        config.setUri(uri);
        config.setMethod(method);
        config.setFunctionMode(functionMode);
        config.setAnswer(answer);
        config.setDelayMs(delayMs);
        config.setGenerateId(generateId);
        config.setGroovyScript(groovyScript);
        List<String> idParams = httpUtils.getIdParams(uri);
        config.setIdParams(idParams);
        idParams.forEach(id -> config.getGenerateIdPatterns().put(id, GeneratorPattern.SEQUENCE));
        return config;
    }

    private ReadController createReadController(ControllerConfig config, ControllerSaveInfoMode saveInfoMode) {
        return ReadController.builder()
                .saveInfoMode(saveInfoMode)
                .controllerData(controllerData)
                .controllerConfig(config)
                .jsonUtils(jsonUtils)
                .httpUtils(httpUtils)
                .systemUtils(systemUtils).build();
    }

    private DeleteController createDeleteController(ControllerConfig config, ControllerSaveInfoMode saveInfoMode) {
        return DeleteController.builder()
                .saveInfoMode(saveInfoMode)
                .controllerData(controllerData)
                .controllerConfig(config)
                .jsonUtils(jsonUtils)
                .httpUtils(httpUtils)
                .systemUtils(systemUtils).build();
    }

    private CreateController createCreateController(ControllerConfig config, ControllerSaveInfoMode saveInfoMode) {
        return CreateController.builder()
                .saveInfoMode(saveInfoMode)
                .controllerData(controllerData)
                .controllerConfig(config)
                .jsonUtils(jsonUtils)
                .httpUtils(httpUtils)
                .systemUtils(systemUtils).build();
    }

    private UpdateController createUpdateController(ControllerConfig config, ControllerSaveInfoMode saveInfoMode) {
        return UpdateController.builder()
                .saveInfoMode(saveInfoMode)
                .controllerData(controllerData)
                .controllerConfig(config)
                .jsonUtils(jsonUtils)
                .httpUtils(httpUtils)
                .systemUtils(systemUtils).build();
    }

}

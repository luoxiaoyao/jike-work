package com.example.shop.config.init;

import com.example.shop.model.enums.BusinessTypes;
import com.example.shop.util.IdAgent;
import com.example.shop.util.IdUtil;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class ConfigurableApplication implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        IdUtil idUtil = new IdUtil();
        idUtil.setWorkerId(BusinessTypes.SHOP.getIndex());
        idUtil.setMachineId(environment.getProperty("machineId", int.class));
        IdAgent.setIdUtil(idUtil);
    }
}

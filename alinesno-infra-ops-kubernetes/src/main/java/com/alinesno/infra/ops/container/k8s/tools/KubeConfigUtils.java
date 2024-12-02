package com.alinesno.infra.ops.container.k8s.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Kubernetes配置文件工具类
 * 提供了将YAML和JSON格式的配置文件相互转换的方法
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
public class KubeConfigUtils {

    private static final Logger log = LoggerFactory.getLogger(KubeConfigUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Yaml yaml = new Yaml();

    /**
     * 将YAML格式的字符串转换为JSON格式的字符串
     * @param yamlString YAML格式的字符串
     * @return JSON格式的字符串
     * @throws JsonProcessingException 如果转换过程中发生JSON处理异常
     */
    public static String yamlToString(String yamlString) throws JsonProcessingException {
        Object obj = yaml.load(yamlString);
        // 将 YAML 转换为 JSON
        return yaml.dump(obj);
    }

    /**
     * 将JSON格式的字符串转换为YAML格式的对象
     * @param jsonFromDb JSON格式的字符串
     * @return 转换后的YAML对象
     * @throws JsonMappingException 如果转换过程中发生JSON映射异常
     * @throws JsonProcessingException 如果转换过程中发生JSON处理异常
     */
    public static Object stringToYaml(String jsonFromDb) throws JsonMappingException, JsonProcessingException {
        Object objFromDb = yaml.load(jsonFromDb);

        log.info("ObjFromDb = {}", objFromDb);

        return objFromDb;
    }
}

/*
 * Copyright (c) 2021 REPLACE_CUSTOMER_NAME. All rights reserved.
 *
 * This file is part of TestGitAzure3.
 *
 * TestGitAzure3 project and associated code cannot be copied
 * and/or distributed without a written permission of REPLACE_CUSTOMER_NAME,
 * and/or its subsidiaries.
 */
package com.abc.test.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import com.abc.commons.exception.ServiceException;
import com.abc.commons.utils.Adapter;
import com.abc.commons.utils.JsonUtils;
import com.abc.commons.utils.Strings;
import com.abc.test.error.TestErrors;

/**
 * An implementation of an experience model that captures and provides test data for "unit" and "integration" tests.
 *
 * @author Admin
 */
@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class TestData {
    /** Map containing the input data where the key is the input-name and the value is the input-data. */
    @Builder.Default
    private final Map<String, Object> data = new LinkedHashMap<>();

    /** Map containing any metadata for this test data. */
    @Builder.Default
    private final Map<String, Object> metadata = new LinkedHashMap<>();

    /**
     * This method attempts to formulate an input name based on the provided {@code targetType}, retrieves the value for
     * it and casts the value to the requested type.
     * <p>
     * The input name is generated by using {@code targetType.getClass().getSimpleName()} and converting to camel-case.
     * For example: if the {@code targetType} is specified as {@code CreatePlatform.class}, this method formulates the
     * input name as {@code createPlatform}.
     *
     * @param targetType
     *         Target type of the input data.
     * @param <T>
     *         Target type.
     *
     * @return An {@link Optional} instance containing the value casted to the requested type if the casting was
     * successful, else an empty {@link Optional} is returned.
     */
    public <T> Optional<T> get(final Class<T> targetType) {
        return get(Strings.uncapitalizedTypeName(targetType), targetType);
    }

    /**
     * This method attempts to convert the value of the specified input name (i.e. {@code inputName} field) to the
     * requested type.
     *
     * @param inputName
     *         Name of the input as defined in the JSON file (e.g. createPlatform, createFramework, etc.)
     * @param targetType
     *         Target type of the input data.
     * @param <T>
     *         Target type.
     *
     * @return An {@link Optional} instance containing the value casted to the requested type if the casting was
     * successful, else an empty {@link Optional} is returned.
     */
    public <T> Optional<T> get(final String inputName, final Class<T> targetType) {
        if (Objects.isNull(targetType) || StringUtils.isBlank(inputName) || data.isEmpty() || !data.containsKey(inputName)) {
            return Optional.empty();
        }
        return Optional.ofNullable(JsonUtils.OBJECT_MAPPER.convertValue(data.get(inputName), targetType));
    }

    /**
     * This method attempts to formulate an input name based on the provided {@code targetType}, retrieves the value for
     * it and casts the value to the requested type.
     * <p>
     * The input name is generated by using {@code targetType.getClass().getSimpleName()} and converts to camel-case.
     * For example: if the {@code targetType} is specified as {@code CreatePlatform.class}, this method formulates the
     * input name as {@code createPlatform}.
     * <p>
     * If the conversion is not possible, an exception is thrown.
     *
     * @param targetType
     *         Target type of the input data.
     * @param <T>
     *         Target type.
     *
     * @return Instance containing the value casted to the requested {@code targetType} if possible else an exception is
     * thrown.
     */
    public <T> T getOrThrow(final Class<T> targetType) {
        return getOrThrow(Strings.uncapitalizedTypeName(targetType), targetType);
    }

    /**
     * This method attempts to convert the value of the specified input name (i.e. {@code inputName} field) to the
     * requested type.
     * <p>
     * If the conversion is not possible, an exception is thrown.
     *
     * @param inputName
     *         Name of the input as defined in the JSON file (e.g. createPlatform, createFramework, etc.)
     * @param targetType
     *         Target type of the input data.
     * @param <T>
     *         Target type.
     *
     * @return Instance containing the value casted to the requested {@code targetType} if possible else an exception is
     * thrown.
     */
    public <T> T getOrThrow(final String inputName, final Class<T> targetType) {
        return get(inputName, targetType).orElseThrow(() -> ServiceException.instance(TestErrors.FAILED_TO_ADAPT_TO_REQUESTED_TYPE, targetType.getSimpleName()));
    }

    /**
     * This method attempts to formulate an input name based on the provided {@code targetType}, retrieves the value for
     * it, converts to a collection where each type of the element in the collection is casted to the specified {@code
     * targetType}.
     * <p>
     * If the value of the specified input name happens to be an object instead of a collection, this method casts the
     * object to the request type {@code targetType} and returns a singleton list of the same.
     * <p>
     * The input name is generated by using {@code targetType.getClass().getSimpleName()} and converts to camel-case.
     * For example: if the {@code targetType} is specified as {@code CreatePlatform.class}, this method formulates the
     * input name as {@code createPlatform}.
     *
     * @param targetType
     *         Target type of the input data.
     * @param <T>
     *         Target type.
     *
     * @return An {@link Optional} instance containing the value casted to the requested type if the casting was
     * successful, else an empty {@link Optional} is returned.
     */
    public <T> Collection<T> getMultiple(final Class<T> targetType) {
        return getMultiple(Strings.uncapitalizedTypeName(targetType), targetType);
    }

    /**
     * This method attempts to convert the value of the specified input name (i.e. {@code inputName} field) as a
     * collection and each type of the element in the collection is casted to the specified {@code targetType}.
     * <p>
     * If the value of the specified input name happens to be an object instead of a collection, this method casts the
     * object to the request type {@code targetType} and returns a singleton list of the same.
     *
     * @param inputName
     *         Name of the input as defined in the JSON file (e.g. createPlatform, createFramework, etc.)
     * @param targetType
     *         Target type of the input data.
     * @param <T>
     *         Target type.
     *
     * @return An {@link Optional} instance containing the value casted to the requested type if the casting was
     * successful, else an empty {@link Optional} is returned.
     */
    public <T> Collection<T> getMultiple(final String inputName, final Class<T> targetType) {
        if (Objects.isNull(targetType) || StringUtils.isBlank(inputName) || data.isEmpty() || !data.containsKey(inputName)) {
            return Collections.emptyList();
        }

        // Get the value.
        final Object inputValue = data.get(inputName);
        if (inputValue instanceof Collection) {
            final Collection<Object> inputValues = (Collection) inputValue;
            return inputValues.stream()
                    .filter(Objects::nonNull)
                    .map(valueToCast -> JsonUtils.OBJECT_MAPPER.convertValue(valueToCast, targetType))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        // Not a collection. Try to adapt the value to the specified target type and add to a singleton list before
        // returning.
        final T adaptedInstance = JsonUtils.OBJECT_MAPPER.convertValue(inputValue, targetType);
        return Objects.isNull(adaptedInstance)
                ? Collections.emptyList()
                : Collections.singletonList(adaptedInstance);
    }

    /**
     * This method attempts to formulate an input name based on the provided {@code targetType}, retrieves the value for
     * it, converts to a collection where each type of the element in the collection is casted to the specified {@code
     * targetType}.
     * <p>
     * If the value of the specified input name happens to be an object instead of a collection, this method casts the
     * object to the request type {@code targetType} and returns a singleton list of the same.
     * <p>
     * The input name is generated by using {@code targetType.getClass().getSimpleName()} and converts to camel-case.
     * For example: if the {@code targetType} is specified as {@code CreatePlatform.class}, this method formulates the
     * input name as {@code createPlatform}.
     * <p>
     * If the conversion is not possible, an exception is thrown.
     *
     * @param targetType
     *         Target type of the input data.
     * @param <T>
     *         Target type.
     *
     * @return Instance containing the value casted to the requested type {@code targetType} if possible else an
     * exception is thrown.
     */
    public <T> Collection<T> getMultipleOrThrow(final Class<T> targetType) {
        return getMultipleOrThrow(Strings.uncapitalizedTypeName(targetType), targetType);
    }

    /**
     * This method attempts to convert the value of the specified input name (i.e. {@code inputName} field) as a
     * collection and each type of the element in the collection is casted to the specified {@code targetType}.
     * <p>
     * If the value of the specified input name happens to be an object instead of a collection, this method casts the
     * object to the request type {@code targetType} and returns a singleton list of the same.
     *
     * @param inputName
     *         Name of the input as defined in the JSON file (e.g. createPlatform, createFramework, etc.)
     * @param targetType
     *         Target type of the input data.
     * @param <T>
     *         Target type.
     *
     * @return Collection of elements where each element in the returned collection is an instance of type {@code
     * targetType}.
     */
    public <T> Collection<T> getMultipleOrThrow(final String inputName, final Class<T> targetType) {
        if (Objects.isNull(targetType) || StringUtils.isBlank(inputName) || data.isEmpty() || !data.containsKey(inputName)) {
            return Collections.emptyList();
        }

        // Get the value.
        final Object inputValue = data.get(inputName);
        if (inputValue instanceof Collection) {
            final Collection<Object> inputValues = (Collection) inputValue;
            return inputValues.stream()
                    .filter(Objects::nonNull)
                    .map(valueToCast -> {
                        final T castedValue = JsonUtils.OBJECT_MAPPER.convertValue(valueToCast, targetType);
                        if (Objects.isNull(castedValue)) {
                            throw ServiceException.instance(TestErrors.FAILED_TO_ADAPT_TO_REQUESTED_TYPE, targetType.getSimpleName());
                        }

                        return castedValue;
                    })
                    .collect(Collectors.toList());
        }

        // Not a collection. Try to adapt the value to the specified target type and add to a singleton list before
        // returning.
        final T adaptedInstance = JsonUtils.OBJECT_MAPPER.convertValue(inputValue, targetType);
        if (Objects.isNull(adaptedInstance)) {
            throw ServiceException.instance(TestErrors.FAILED_TO_ADAPT_TO_REQUESTED_TYPE, targetType.getSimpleName());
        }
        return Collections.singletonList(adaptedInstance);
    }

    /**
     * For the provided key, this method attempts to retrieve the value from the {@code metadata} map and returns the
     * value by converting it as a {@link String}.
     *
     * @param key
     *         Key for which the value needs to be retrieved.
     *
     * @return Value for the specified key. Returns null if the key is not defined in the {@code metadata} map.
     */
    public String metadata(final String key) {
        return metadata(key, String.class);
    }

    /**
     * For the provided key, this method attempts to retrieve the value from the {@code metadata} map and casts the
     * value to the specified target type.
     *
     * @param key
     *         Key for which the value needs to be retrieved.
     * @param targetType
     *         Target type of the value.
     * @param <T>
     *         Target type of the value.
     *
     * @return Value for the specified key and casted to the specified type. Returns null if the value cannot be casted
     * to the specified type.
     */
    public <T> T metadata(final String key, final Class<T> targetType) {
        return Adapter.adaptTo(metadata.get(key), targetType);
    }
}

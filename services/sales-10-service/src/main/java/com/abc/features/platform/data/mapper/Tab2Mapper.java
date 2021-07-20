/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is \n * confidential and proprietary information of Innominds inc. You shall not disclose \n * Confidential Information and shall use it only in accordance with the terms \n *
 */
package com.abc.features.platform.data.mapper;

import com.abc.features.platform.data.model.experience.tab2.CreateTab2Request;
import com.abc.features.platform.data.model.experience.tab2.Tab2;
import com.abc.features.platform.data.model.experience.tab2.UpdateTab2Request;
import com.abc.features.platform.data.model.persistence.Tab2Entity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper contract that maps / transforms data from an instance of type {@link Tab2Entity to {@link Tab2 and vice-versa.
 *
 * @author Admin
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface Tab2Mapper {

    /**
     * This method transforms an instance of type {@link CreateTab2Request} to an instance of type
     * {@link Tab2Entity}.
     *
     * @param source Instance of type {@link CreateTab2Request} that needs to be transformed to
     *     {@link Tab2Entity}.
     * @return Instance of type {@link Tab2Entity}.
     */
    Tab2Entity transform(CreateTab2Request source);

    /**
     * This method transforms an instance of type {@link Tab2Entity} to an instance of type {@link
     * Tab2}.
     *
     * @param source Instance of type {@link Tab2Entity} that needs to be transformed to {@link
     *     Tab2}.
     * @return Instance of type {@link Tab2}.
     */
    Tab2 transform(Tab2Entity source);

    /**
     * This method converts / transforms the provided collection of {@link Tab2Entity} instances to
     * a collection of instances of type {@link Tab2}.
     *
     * @param source Instance of type {@link Tab2Entity} that needs to be transformed to {@link
     *     Tab2}.
     * @return Collection of instance of type {@link Tab2}.
     */
    default Collection<Tab2> transformListTo(Collection<Tab2Entity> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
    /**
     * This method transforms an instance of type {@link UpdateTab2Request} to an instance of type
     * {@link Tab2Entity}.
     *
     * <p>The provided instance ({@code target}) will be updated instead of creating a new instance.
     *
     * @param source Instance of type {@link UpdateTab2Request} that needs to be transformed to
     *     {@link Tab2Entity}.
     * @param target Instance of type {@link Tab2Entity} that will be updated instead of creating
     *     and returning a new instance.
     */
    void transform(UpdateTab2Request source, @MappingTarget Tab2Entity target);

    /**
     * This method transforms an instance of type {@link UpdateTab2Request} to an instance of type
     * {@link Tab2Entity}.
     *
     * @param source Instance of type {@link UpdateTab2Request} that needs to be transformed to
     *     {@link Tab2Entity}.
     * @return Instance of type {@link Tab2Entity}.
     */
    Tab2Entity transform(UpdateTab2Request source);

    /**
     * This method converts / transforms the provided collection of {@link UpdateTab2Request}
     * instances to a collection of instances of type {@link Tab2Entity}.
     *
     * @param source Instance of type {@link UpdateTab2Request} that needs to be transformed to
     *     {@link Tab2Entity}.
     * @return Instance of type {@link Tab2Entity}.
     */
    default Collection<Tab2Entity> transformList(Collection<UpdateTab2Request> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
}

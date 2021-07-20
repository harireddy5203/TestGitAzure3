/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is \n * confidential and proprietary information of Innominds inc. You shall not disclose \n * Confidential Information and shall use it only in accordance with the terms \n *
 */
package com.abc.features.platform.web.service;

import com.abc.commons.data.utils.PageUtils;
import com.abc.commons.instrumentation.Instrument;
import com.abc.features.platform.data.mapper.Tab2Mapper;
import com.abc.features.platform.data.model.experience.tab2.CreateTab2Request;
import com.abc.features.platform.data.model.experience.tab2.Tab2;
import com.abc.features.platform.data.model.experience.tab2.UpdateTab2Request;
import com.abc.features.platform.data.model.persistence.Tab2Entity;
import com.abc.features.platform.data.repository.Tab2Repository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service implementation that provides CRUD (Create, Read, Update, Delete) capabilities for
 * entities of type {@link Tab2Entity}.
 *
 * @author Admin
 */
@Slf4j
@Validated
@Service
public class Tab2Service {
    /** Repository implementation of type {@link Tab2Repository}. */
    private final Tab2Repository tab2Repository;

    /** Mapper implementation of type {@link Tab2Mapper} to transform between different types. */
    private final Tab2Mapper tab2Mapper;

    /**
     * Constructor.
     *
     * @param tab2Repository Repository instance of type {@link Tab2Repository}.
     * @param tab2Mapper Mapper instance of type {@link Tab2Mapper}.
     */
    public Tab2Service(final Tab2Repository tab2Repository, final Tab2Mapper tab2Mapper) {
        this.tab2Repository = tab2Repository;
        this.tab2Mapper = tab2Mapper;
    }

    /**
     * This method attempts to create an instance of type {@link Tab2Entity} in the system based on
     * the provided payload.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     Tab2Entity}.
     * @return An experience model of type {@link Tab2} that represents the newly created entity of
     *     type {@link Tab2Entity}.
     */
    @Instrument
    @Transactional
    public Tab2 createTab2(@Valid final CreateTab2Request payload) {
        // 1. Transform the experience model to a persistence model.
        final Tab2Entity tab2Entity = tab2Mapper.transform(payload);

        // 2. Save the entity.
        Tab2Service.LOGGER.debug("Saving a new instance of type - Tab2Entity");
        final Tab2Entity newInstance = tab2Repository.save(tab2Entity);

        // 3. Transform the created entity to an experience model and return it.
        return tab2Mapper.transform(newInstance);
    }

    /**
     * This method attempts to update an existing instance of type {@link Tab2Entity} using the
     * details from the provided input, which is an instance of type {@link UpdateTab2Request}.
     *
     * @param tab2Id Unique identifier of Tab2 in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Tab2, which needs to be
     *     updated in the system.
     * @return A instance of type {@link Tab2} containing the updated details.
     */
    @Instrument
    @Transactional
    public Tab2 updateTab2(final Integer tab2Id, @Valid final UpdateTab2Request payload) {
        // 1. Verify that the entity being updated truly exists.
        final Tab2Entity matchingInstance = tab2Repository.findByIdOrThrow(tab2Id);

        // 2. Transform the experience model to a persistence model and delegate to the save()
        // method.
        tab2Mapper.transform(payload, matchingInstance);

        // 3. Save the entity
        Tab2Service.LOGGER.debug("Saving the updated entity - Tab2Entity");
        final Tab2Entity updatedInstance = tab2Repository.save(matchingInstance);

        // 4. Transform updated entity to output object
        return tab2Mapper.transform(updatedInstance);
    }

    /**
     * This method attempts to find a {@link Tab2Entity} whose unique identifier matches the
     * provided identifier.
     *
     * @param tab2Id Unique identifier of Tab2 in the system, whose details have to be retrieved.
     * @return Matching entity of type {@link Tab2} if found, else returns null.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Tab2 findTab2(final Integer tab2Id) {
        // 1. Find a matching entity and throw an exception if not found.
        final Tab2Entity matchingInstance = tab2Repository.findByIdOrThrow(tab2Id);

        // 2. Transform the matching entity to the desired output.
        return tab2Mapper.transform(matchingInstance);
    }

    /**
     * This method attempts to find instances of type Tab2Entity based on the provided page
     * definition. If the page definition is null or contains invalid values, this method attempts
     * to return the data for the first page (i.e. page index is 0) with a default page size as 20.
     *
     * @return Returns a page of objects based on the provided page definition. Each object in the
     *     returned page is an instance of type {@link Tab2}.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Page<Tab2> findAllTab2s(final Pageable page) {
        // 1. Validate the provided pagination settings.
        final Pageable pageSettings = PageUtils.validateAndUpdatePaginationConfiguration(page);
        Tab2Service.LOGGER.debug(
                "Page settings: page number {}, page size {}",
                pageSettings.getPageNumber(),
                pageSettings.getPageSize());

        // 2. Delegate to the super class method to find the data (page settings are verified in
        // that method).
        final Page<Tab2Entity> pageData = tab2Repository.findAll(pageSettings);

        // 3. If the page has data, transform each element into target type.
        if (pageData.hasContent()) {
            final List<Tab2> dataToReturn =
                    pageData.getContent().stream()
                            .map(tab2Mapper::transform)
                            .collect(Collectors.toList());

            return PageUtils.createPage(dataToReturn, pageSettings, pageData.getTotalElements());
        }

        // Return empty page.
        return PageUtils.emptyPage(pageSettings);
    }

    /**
     * This method attempts to delete an existing instance of type {@link Tab2Entity} whose unique
     * identifier matches the provided identifier.
     *
     * @param tab2Id Unique identifier of Tab2 in the system, which needs to be deleted.
     * @return Unique identifier of the instance of type Tab2Entity that was deleted.
     */
    @Instrument
    @Transactional
    public Integer deleteTab2(final Integer tab2Id) {
        // 1. Delegate to our repository method to handle the deletion.
        return tab2Repository.deleteOne(tab2Id);
    }
}

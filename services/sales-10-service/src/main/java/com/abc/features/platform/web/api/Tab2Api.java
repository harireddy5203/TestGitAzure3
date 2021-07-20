/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is \n * confidential and proprietary information of Innominds inc. You shall not disclose \n * Confidential Information and shall use it only in accordance with the terms \n *
 */
package com.abc.features.platform.web.api;

import com.abc.commons.data.utils.PageUtils;
import com.abc.commons.web.api.AbstractApi;
import com.abc.commons.web.configuration.properties.ApiDocumentationSettings;
import com.abc.features.platform.data.model.experience.tab2.CreateTab2Request;
import com.abc.features.platform.data.model.experience.tab2.Tab2;
import com.abc.features.platform.data.model.experience.tab2.UpdateTab2Request;
import com.abc.features.platform.web.service.Tab2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of APIs that provide CRUD (Create, Read, Update and Delete) functionality for
 * persistence models of type {@link com.abc.features.platform.data.model.persistence.Tab2Entity}.
 *
 * @author Admin
 */
@Slf4j
@RestController
@RequestMapping(Tab2Api.rootEndPoint)
public class Tab2Api extends AbstractApi {
    /** Tag for this API. */
    public static final String API_TAG = "Tab2s";

    /** Root end point. */
    public static final String rootEndPoint = "/sales-10";

    /** Service implementation of type {@link Tab2Service}. */
    private final Tab2Service tab2Service;

    /**
     * Constructor.
     *
     * @param tab2Service Service instance of type {@link Tab2Service}.
     */
    public Tab2Api(final Tab2Service tab2Service) {
        this.tab2Service = tab2Service;
    }

    /**
     * This API provides the capability to add a new instance of type {@link
     * com.abc.features.platform.data.model.persistence.Tab2Entity} into the system.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     com.abc.features.platform.data.model.persistence.Tab2Entity}.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link Tab2}.
     */
    @Operation(
            method = "createTab2",
            summary = "Create a new Tab2.",
            description = "This API is used to create a new Tab2 in the system.",
            tags = {Tab2Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Successfully created a new Tab2 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/tab2s")
    public ResponseEntity<Tab2> createTab2(@Valid @RequestBody final CreateTab2Request payload) {
        // Delegate to the service layer.
        final Tab2 newInstance = tab2Service.createTab2(payload);

        // Build a response entity object and return it.
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstance);
    }

    /**
     * This API provides the capability to update an existing instance of type {@link
     * com.abc.features.platform.data.model.persistence.Tab2Entity} in the system.
     *
     * @param tab2Id Unique identifier of Tab2 in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Tab2, which needs to be
     *     updated in the system.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link Tab2}.
     */
    @Operation(
            method = "updateTab2",
            summary = "Update an existing Tab2.",
            description = "This API is used to update an existing Tab2 in the system.",
            tags = {Tab2Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully updated an existing Tab2 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/tab2s/{tab2Id}")
    public ResponseEntity<Tab2> updateTab2(
            @PathVariable(name = "tab2Id") final Integer tab2Id,
            @Valid @RequestBody final UpdateTab2Request payload) {
        // Delegate to the service layer.
        final Tab2 updatedInstance = tab2Service.updateTab2(tab2Id, payload);

        // Build a response entity object and return it.
        return ResponseEntity.ok(updatedInstance);
    }

    /**
     * This API provides the capability to retrieve the details of an existing {@link
     * com.abc.features.platform.data.model.persistence.Tab2Entity} in the system.
     *
     * @param tab2Id Unique identifier of Tab2 in the system, whose details have to be retrieved.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link Tab2}.
     */
    @Operation(
            method = "findTab2",
            summary = "Find an existing Tab2.",
            description = "This API is used to find an existing Tab2 in the system.",
            tags = {Tab2Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description =
                                "Successfully retrieved the details of an existing Tab2 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/tab2s/{tab2Id}")
    public ResponseEntity<Tab2> findTab2(@PathVariable(name = "tab2Id") final Integer tab2Id) {
        // Delegate to the service layer.
        final Tab2 matchingInstance = tab2Service.findTab2(tab2Id);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstance);
    }

    /**
     * This API provides the capability to retrieve all instances of type {@link
     * com.abc.features.platform.data.model.persistence.Tab2Entity} in the system in a paginated
     * manner.
     *
     * @param page Page number.
     * @param size Page size.
     * @return Response of type {@link ResponseEntity} that holds a page of instances of type Tab2
     *     based on the provided pagination settings.
     */
    @Operation(
            method = "findAllTab2s",
            summary = "Find all Tab2s.",
            description = "This API is used to find all Tab2s in the system.",
            tags = {Tab2Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description =
                                "Successfully retrieved the Tab2s in the system based on the provided pagination settings.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/tab2s")
    public ResponseEntity<Page<Tab2>> findAllTab2s(
            @RequestParam(name = "page", required = false, defaultValue = "0") final Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
                    final Integer size) {
        // Delegate to the service layer.
        final Pageable pageSettings = PageUtils.createPaginationConfiguration(page, size);
        final Page<Tab2> matchingInstances = tab2Service.findAllTab2s(pageSettings);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstances);
    }

    /**
     * This API provides the capability to delete an existing instance of type {@link
     * com.abc.features.platform.data.model.persistence.Tab2Entity} in the system.
     *
     * @param tab2Id Unique identifier of Tab2 in the system, which needs to be deleted.
     * @return Response of type {@link ResponseEntity} that holds the unique identifier of the
     *     {@link com.abc.features.platform.data.model.persistence.Tab2Entity} that was deleted from
     *     the system.
     */
    @Operation(
            method = "deleteTab2",
            summary = "Delete an existing Tab2.",
            description = "This API is used to delete an existing Tab2 in the system.",
            tags = {Tab2Api.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully deleted an existing Tab2 in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping(value = "/tab2s/{tab2Id}")
    public ResponseEntity<Integer> deleteTab2(@PathVariable(name = "tab2Id") final Integer tab2Id) {
        // Delegate to the service layer.
        final Integer deletedInstance = tab2Service.deleteTab2(tab2Id);

        // Build a response entity object and return it.
        return ResponseEntity.ok(deletedInstance);
    }
}

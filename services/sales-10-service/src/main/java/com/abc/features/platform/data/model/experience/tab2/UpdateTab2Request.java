/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is \n * confidential and proprietary information of Innominds inc. You shall not disclose \n * Confidential Information and shall use it only in accordance with the terms \n *
 */
package com.abc.features.platform.data.model.experience.tab2;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Implementation of an experience model that is meant to be used by the API Layer for communication
 * either with the front-end or to the service-layer.
 *
 * @author Admin
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class UpdateTab2Request {
    /** Reference to the id. */
    @NotNull(message = "tab2.id.not.null.message")
    private Integer id;

    /** Reference to the name. */
    @NotBlank(message = "tab2.name.not.blank.message")
    @Size(max = 20, message = "tab2.name.size.message")
    private String name;
}

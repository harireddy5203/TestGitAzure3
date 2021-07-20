/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is \n * confidential and proprietary information of Innominds inc. You shall not disclose \n * Confidential Information and shall use it only in accordance with the terms \n *
 */
package com.abc.features.platform.data.repository;

import com.abc.commons.data.jpa.repository.ExtendedJpaRepository;
import com.abc.features.platform.data.model.persistence.Tab2Entity;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to handle the operations pertaining to domain models of type "Tab2Entity".
 *
 * @author Admin
 */
@Repository
public interface Tab2Repository extends ExtendedJpaRepository<Tab2Entity, Integer> {
    // Any custom methods can be added here.
}

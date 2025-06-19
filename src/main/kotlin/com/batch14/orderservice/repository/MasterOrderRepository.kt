package com.batch14.orderservice.repository

import com.batch14.orderservice.domain.entity.MasterOrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MasterOrderRepository: JpaRepository<MasterOrderEntity, Int> {

}
package com.batch14.orderservice.repository

import com.batch14.orderservice.domain.entity.MasterPaymentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MasterPaymentRepository: JpaRepository<MasterPaymentEntity, Int> {
}
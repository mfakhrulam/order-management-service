package com.batch14.orderservice.service.impl

import com.batch14.orderservice.domain.Constant.Constant
import com.batch14.orderservice.domain.dto.request.ReqCreateOrderDto
import com.batch14.orderservice.domain.dto.response.ResGetOrderDto
import com.batch14.orderservice.domain.entity.MasterOrderEntity
import com.batch14.orderservice.exception.CustomException
import com.batch14.orderservice.repository.MasterOrderRepository
import com.batch14.orderservice.repository.MasterPaymentRepository
import com.batch14.orderservice.rest.ProductManagementClient
import com.batch14.orderservice.rest.UserManagementClient
import com.batch14.orderservice.service.MasterOrderService
import feign.FeignException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class MasterOrderServiceImpl(
    private val masterOrderRepository: MasterOrderRepository,
    private val userManagementClient: UserManagementClient,
    private val productManagementClient: ProductManagementClient,
    private val httpServletRequest: HttpServletRequest,
    private val masterPaymentRepository: MasterPaymentRepository

): MasterOrderService {
    override fun findAllOrders(): List<ResGetOrderDto> {
        val ordersEntity = masterOrderRepository.findAll()
        // butuh user, product, list payment
        val userIds = ordersEntity.mapNotNull { it.user }.distinct()
        val productIds = ordersEntity.mapNotNull { it.product }.distinct()
        val users = userManagementClient.getUsersByIds(userIds).body!!.data!!
        val products = productManagementClient.getProductByIds(productIds).body!!.data!!

        val result = ordersEntity.map { order ->
            ResGetOrderDto(
                id = order.id,
                amount = order.amount,
                isPaid = order.isPaid,
                paymentMethod = order.payment?.paymentMethod,
                username = users.find { it.id == order.user.toInt() }?.username,
                productName = products.find { it.id == order.product?.toInt() }?.name,
                createdAt = order.createdAt,
            )
        }
        return result
    }

    override fun createOrder(req: ReqCreateOrderDto): ResGetOrderDto {
        val roleUser = httpServletRequest.getHeader(Constant.HEADER_USER_ROLE)
        val idUser = httpServletRequest.getHeader(Constant.HEADER_USER_ID)
        var username = ""
        var productName = ""
        var amount = 0

        // cek apakah role USER
        if(!roleUser.equals("user")) {
            throw CustomException(
                "Forbidden",
                HttpStatus.FORBIDDEN.value()
            )
        }

        // cek apakah user ada
        try {
            val userResponse = userManagementClient.getUserById(idUser.toInt())
            username = userResponse.body!!.data!!.username
        } catch (e : FeignException) {
            if(e.status() == 400) {
                throw CustomException("User tidak ditemukan", 400)
            }
        }

        // cek apakah product ada
        try {
            val productResponse = productManagementClient.getProductById(req.productId)
            productName = productResponse.body!!.data!!.name
            amount = productResponse.body!!.data!!.price
        } catch (e : FeignException) {
            if(e.status() == 400) {
                throw CustomException("Produk tidak ditemukan", 400)
            }
        }

        val isPaid = req.isPaid ?: false

        val payment = masterPaymentRepository.findById(req.paymentId).orElseThrow {
            CustomException(
                "Payment dengan Id: ${req.paymentId} tidak ditemukan",
                400
            )
        }

        val orderRaw = MasterOrderEntity(
            payment = payment,
            isPaid = isPaid,
            amount = amount,
            product = req.productId,
            user = idUser.toInt(),
        )

        val order = masterOrderRepository.save(orderRaw)
        return ResGetOrderDto(
            id = order.id,
            amount = amount,
            isPaid = order.isPaid,
            paymentMethod = payment.paymentMethod,
            username = username,
            productName = productName,
            createdAt = order.createdAt
        )
    }
}
package com.alokomkar.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserStocksResponse(
    @Json(name = "client_id")
    val clientId: String,
    @Json(name = "data")
    val userStockDetails: List<UserStockDetailsResponse>,
    @Json(name = "error")
    val error: Any?,
    @Json(name = "response_type")
    val responseType: String,
    @Json(name = "timestamp")
    val timestamp: Long
)

@JsonClass(generateAdapter = true)
data class UserStockDetailsResponse(
    @Json(name = "avg_price")
    val avgPrice: String,
    @Json(name = "cnc_used_quantity")
    val cncUsedQuantity: Int,
    @Json(name = "collateral_qty")
    val collateralQty: Int,
    @Json(name = "collateral_type")
    val collateralType: String,
    @Json(name = "collateral_update_qty")
    val collateralUpdateQty: Int,
    @Json(name = "company_name")
    val companyName: String,
    @Json(name = "haircut")
    val haircut: Double,
    @Json(name = "holdings_update_qty")
    val holdingsUpdateQty: Int,
    @Json(name = "isin")
    val isin: String,
    @Json(name = "product")
    val product: String,
    @Json(name = "quantity")
    val quantity: Int,
    @Json(name = "symbol")
    val symbol: String,
    @Json(name = "t1_holding_qty")
    val t1HoldingQty: Int,
    @Json(name = "token_bse")
    val tokenBse: String,
    @Json(name = "token_nse")
    val tokenNse: String,
    @Json(name = "withheld_collateral_qty")
    val withheldCollateralQty: Int,
    @Json(name = "withheld_holding_qty")
    val withheldHoldingQty: Int,
    @Json(name = "ltp")
    val ltp: Double,
    @Json(name = "close")
    val close: Int
)
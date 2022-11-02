package book_vehicle_on_rent.model.payment

import book_vehicle_on_rent.enums.PaymentMode
import java.util.Date

class PaymentDetails(val paymentMode: PaymentMode, val dateOfPayment: Date, val paidAmt: Double) {
}